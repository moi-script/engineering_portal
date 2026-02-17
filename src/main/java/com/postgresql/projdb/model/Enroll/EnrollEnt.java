package com.postgresql.projdb.model.Enroll;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "enrolled_list")
public class EnrollEnt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "admin_token", nullable = false)
    private String adminToken;

    @OneToMany(mappedBy = "enrolledList", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<StudentList> studentList;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdminToken() {
        return adminToken;
    }

    public void setAdminToken(String adminToken) {
        this.adminToken = adminToken;
    }

    public List<StudentList> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<StudentList> studentList) {
        this.studentList = studentList;
        for (StudentList student : studentList) {
            student.setEnrolledList(this); // Establish the bidirectional relationship
        }
    }
}
