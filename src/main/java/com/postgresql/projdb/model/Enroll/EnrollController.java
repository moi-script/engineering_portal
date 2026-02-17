package com.postgresql.projdb.model.Enroll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = { "http://localhost:5173", "http://localhost:3000", "http://localhost:5174/", "https://engineering-portal-front.vercel.app/" })
public class EnrollController {

    @Autowired
    private EnrollService enrollService;

    @PostMapping("/add-student")
    public ResponseEntity<EnrollEnt> addStudentToEnroll(@RequestBody StudentDTO studentDTO) {
        try {
            // Call the service to add the student and return the updated enrollment
            EnrollEnt enroll = enrollService.addStudentToEnroll(studentDTO.getAdminToken(), studentDTO);
            return ResponseEntity.ok(enroll);  // Return the response with status 200 and enroll data

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);  
        }
    }

    @GetMapping("/admin-enrolled")
    public ResponseEntity<EnrollEnt> getEnrollByAdminToken(@RequestParam String adminToken) {
        Optional<EnrollEnt> enroll = enrollService.getEnrollByAdminToken(adminToken);
        return enroll.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{enrollId}/students")
    public ResponseEntity<List<StudentList>> getStudentsByEnrollId(@PathVariable Long enrollId) {
        List<StudentList> students = enrollService.getStudentsByEnrollId(enrollId);
        return ResponseEntity.ok(students);  
    }

    @GetMapping("/get-admin-enroll-by-token")
    public ResponseEntity<EnrollDTO> getEnrolledStudentsByAdminToken(@RequestParam String adminToken) {
        try {
            EnrollDTO enrollDTO = enrollService.getEnrollWithStudentsByAdminToken(adminToken);
            return ResponseEntity.ok(enrollDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    
}
