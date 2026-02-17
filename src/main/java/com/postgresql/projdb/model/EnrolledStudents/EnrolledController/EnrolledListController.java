package com.postgresql.projdb.model.EnrolledStudents.EnrolledController;

import com.postgresql.projdb.model.EnrolledStudents.EnrolledListDTO;
import com.postgresql.projdb.model.EnrolledStudents.Service.EnrolledListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = { "http://localhost:5173", "http://localhost:3000", "https://engineering-portal-front.vercel.app/" }) // enable CORS for react vite
public class EnrolledListController {
    private final EnrolledListService enrolledListService;

    public EnrolledListController(EnrolledListService enrolledListService) {
        this.enrolledListService = enrolledListService;
    }

    @PostMapping("/enrolled-user-with-admin")
    public ResponseEntity<String> addUserToEnrolledList(@RequestBody EnrolledListDTO enrolledListDTO) {
        System.out.println("This is the admin token: " + enrolledListDTO.getAdminToken() + "User token" +  enrolledListDTO.getUserToken());
        
        try {
            boolean response = enrolledListService.addUserToAdminList(enrolledListDTO.getAdminToken(), enrolledListDTO.getUserToken());
            return ResponseEntity.ok(Boolean.toString(response));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An unexpected error occurred");
        }
    }

    
}
