package com.postgresql.projdb.model.Enroll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class EnrollService {

    private final EnrollEntRepository enrollEntRepository;
    private final StudentListRepository studentListRepository;

    @Autowired
    public EnrollService(EnrollEntRepository enrollEntRepository, StudentListRepository studentListRepository) {
        this.enrollEntRepository = enrollEntRepository;
        this.studentListRepository = studentListRepository;
    }

    // Method to convert DTO to entity
    private StudentList convertToStudentEntity(StudentDTO studentDTO, EnrollEnt enrollEnt) {
        if (studentDTO == null) {
            throw new IllegalArgumentException("StudentDTO cannot be null");
        }

        StudentList student = new StudentList();
        student.setUserToken(studentDTO.getUserToken());
        student.setEnrolledList(enrollEnt);

        return student;
    }

    // Method to add student to an existing enrollment or create new enrollment
    @Transactional
    public EnrollEnt addStudentToEnroll(String adminToken, StudentDTO studentDTO) {
        // Try to fetch existing enrollment
        EnrollEnt enroll = enrollEntRepository.findByAdminToken(adminToken)
                .orElseGet(() -> {
                    // If no existing enrollment, create a new one
                    EnrollEnt newEnroll = new EnrollEnt();
                    newEnroll.setAdminToken(adminToken);
                    System.out.println("ADMMINTOKEN OBJECT ______________________" + adminToken);

                     if (newEnroll.getStudentList() == null) {
                    newEnroll.setStudentList(new ArrayList<>());  // Initialize empty list
                    }
                    // Save the new EnrollEnt entity
                    System.out.println("STUDENT DTO OBJECT ______________________" + studentDTO);
    
                    // After saving, create a StudentList and link it to the new EnrollEnt
                    StudentList student = new StudentList();
                    student.setUserToken(studentDTO.getUserToken());  // Set the userToken from DTO

                    System.out.println("STUDENT LIST OBJECT ______________________" + student.getUserToken());

                    // Save the student to the student_list table
                    student.setEnrolledList(newEnroll);  // Set the association with the new enrollment
                    newEnroll.getStudentList().add(student);  // Add to the list
                    System.out.println("ENROLLMENT LIST OBJECT ______________________" + newEnroll.getStudentList());
                    enrollEntRepository.save(newEnroll);
                    studentListRepository.save(student);

                    return newEnroll;  // Return the newly created EnrollEnt
                });
    
        // Check if the student is already added to the enrollment list
        boolean alreadyExists = enroll.getStudentList().stream()
                .anyMatch(s -> s.getUserToken().equals(studentDTO.getUserToken()));
    
        if (!alreadyExists) {
            // If the student doesn't exist, create and add the student to the enrollment
            StudentList student = new StudentList();
            student.setUserToken(studentDTO.getUserToken());  // Set the userToken from DTO
            student.setEnrolledList(enroll);  // Associate the student with the current enrollment
    
            enroll.getStudentList().add(student);  // Add the student to the enroll list
    
            // Save the updated enrollment and associated students
            enrollEntRepository.save(enroll);  // Cascade save will persist the new student as well
        }
    
        return enroll;  // Return the updated or newly created enrollment
    }
    
    // Fetch all students for a given enrollment ID
    public List<StudentList> getStudentsByEnrollId(Long enrollId) {
        return studentListRepository.findByEnrolledList_Id(enrollId);
    }

    // Fetch EnrollEnt by admin token
    public Optional<EnrollEnt> getEnrollByAdminToken(String adminToken) {
        return enrollEntRepository.findByAdminToken(adminToken);
    }


     public EnrollDTO getEnrollWithStudentsByAdminToken(String adminToken) {
        // Fetch EnrollEnt by admin token
        EnrollEnt enrollEnt = enrollEntRepository.findByAdminToken(adminToken)
                .orElseThrow(() -> new IllegalArgumentException("Enrollment not found for admin token: " + adminToken));

        // Map EnrollEnt to EnrollDTO
        EnrollDTO enrollDTO = new EnrollDTO();
        enrollDTO.setId(enrollEnt.getId());
        enrollDTO.setAdminToken(enrollEnt.getAdminToken());
        
        // Map StudentList entities to StudentDTOs
        List<StudentDTO> studentDTOs = enrollEnt.getStudentList().stream().map(student -> {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setId(student.getId());
            studentDTO.setUserToken(student.getUserToken());
            return studentDTO;
        }).collect(Collectors.toList());
        
        enrollDTO.setStudents(studentDTOs);
        return enrollDTO;
    }
}