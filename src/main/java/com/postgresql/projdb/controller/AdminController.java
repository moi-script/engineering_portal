package com.postgresql.projdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.postgresql.projdb.Service.AdminService;
import com.postgresql.projdb.model.AdminEntity;
import com.postgresql.projdb.model.AdminLoginDTO;
import com.postgresql.projdb.model.MessageDTO;
import com.postgresql.projdb.model.MessagesList;
import com.postgresql.projdb.model.MessagesListDTO;
import com.postgresql.projdb.repo.AdminRepository;
import com.postgresql.projdb.repo.MessagesListRepository;

import jakarta.transaction.Transactional;

@RestController
@CrossOrigin(origins = { "http://localhost:5173", "http://localhost:3000", "http://localhost:5174/", "https://engineering-portal-front.vercel.app/"}) // enable CORS for react vite
public class AdminController {

    @Autowired
    private AdminRepository adminrepo;
    private final MessagesListRepository messagesListRepository;

    @Autowired
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService, MessagesListRepository messagesListRepository) {
        this.adminService = adminService;
        this.messagesListRepository = messagesListRepository;
    }

    @GetMapping("/check-admin-credentials")
    public AdminLoginDTO getUserCredentials(@RequestParam String token) {
        AdminLoginDTO admin = adminrepo.findAdminByAdminToken(token);
        if (admin == null) {
            // Handle the case where the user is not found
            throw new RuntimeException("User not found with ID: " + token);
        }
        return admin;
    }

    @PostMapping("/save-login-signIn_admin")
    public ResponseEntity<String> saveAdminAccount(@RequestBody AdminLoginDTO admindto) {
        AdminLoginDTO existingAdmin = adminrepo.findAdminByAdminToken(admindto.getAdmintokens()); 
        AdminEntity adminEntity = new AdminEntity();

        if (existingAdmin != null) {
            // User already exists, return a message or proceed accordingly
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists!");
        }

        // Create a new user if they don't exist
        adminEntity.setName(admindto.getName());
        adminEntity.setAdminTokens(admindto.getAdmintokens());
        adminEntity.setPassword(admindto.getPassword());
        adminEntity.setStudentCounts(0); // set to default value

        adminrepo.save(adminEntity);
        return ResponseEntity.ok("User saved successfully!");
    }

    // @GetMapping("/fetch-message-list")
    // public ResponseEntity<List<MessagesListDTO>> getMessagesListsByToken(@RequestParam("token") String token) {
    //     try {
    //         List<MessagesListDTO> messageLists = adminService.getMessagesLists(token);
    //         return ResponseEntity.ok(messageLists);
    //     } catch (IllegalArgumentException e) {
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Return 404 if the token is invalid
    //     }
    // }

    // @PostMapping("/add-message-for-admin-token")
    // public ResponseEntity<String> addMessageForAdminToken(
    //         @RequestParam("token") String token,
    //         @RequestBody MessageDTO messageDTO) {
    //     try {
    //         adminService.addOrCreateMessageList(token, messageDTO);
    //         return ResponseEntity.ok("Message added successfully.");
    //     } catch (IllegalArgumentException e) {
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
    //     }
    // }


        // Fetch all message lists for a specific admin using the admin's token
    @GetMapping("/fetchAllAdminMessageByToken")
    public ResponseEntity<List<MessagesListDTO>> getMessagesLists(@RequestParam String token ) {
        try {
            List<MessagesListDTO> messagesLists = adminService.getMessagesLists(token);
            return new ResponseEntity<>(messagesLists, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN); // Invalid token
        }
    }

    // Fetch a conversation (message list) for a specific person by their token
    @GetMapping("/searchConversation")
    public ResponseEntity<MessagesListDTO> getConversationForPerson(@RequestParam String personConvoWithToken) {
        try {
            MessagesListDTO messagesListDTO = adminService.getConversationForPerson(personConvoWithToken);
            return new ResponseEntity<>(messagesListDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // Conversation not found
        }
    }

    // Add a new message or create a new message list for the provided admin token
    @PostMapping("/addMessageListForAdmin")
    public ResponseEntity<String> addOrCreateMessageList(@RequestParam String token, @RequestBody MessageDTO messageDTO) {
        try {
            adminService.addOrCreateMessageList(token, messageDTO);
            return new ResponseEntity<>("Message added successfully", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Invalid admin token or message data", HttpStatus.BAD_REQUEST);
        }
    }

    // Add a message to an existing conversation for a person by the admin's token
    @PostMapping("/addMessageToPerson")
    public ResponseEntity<String> addMessageToPersonConversation(
            @RequestParam String adminToken,
            @RequestParam String personToken,
            @RequestBody MessageDTO messageDTO) {
        try {
            adminService.addMessageToPersonConversation(adminToken, personToken, messageDTO);
            return new ResponseEntity<>("Message added to conversation", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Invalid data", HttpStatus.BAD_REQUEST);
        }
    }

}
