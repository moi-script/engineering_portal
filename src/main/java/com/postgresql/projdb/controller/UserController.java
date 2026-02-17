package com.postgresql.projdb.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.postgresql.projdb.model.GetInt;
import com.postgresql.projdb.model.LoginSignInDTO;
import com.postgresql.projdb.model.ProgressPerUser;
import com.postgresql.projdb.model.User;
import com.postgresql.projdb.repo.LoginSignInRepository;
import com.postgresql.projdb.repo.PersonRepo;
import com.postgresql.projdb.repo.TokenRepo;
import com.postgresql.projdb.repo.UserProgress;
import com.postgresql.projdb.userHolder.UserBody;

import com.postgresql.projdb.model.Token;

// import java.util.ArrayList;
import java.util.List;
// import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PatchMapping;
// import org.springframework.web.bind.annotation.PathVariable;
import com.postgresql.projdb.repo.FetchAllUser;
import com.postgresql.projdb.repo.GetIntRepo;


@RestController
@CrossOrigin(origins = { "http://localhost:5173", "http://localhost:3000" , "http://localhost:5174/", "https://engineering-portal-front.vercel.app/"}) // enable CORS for react vite
public class UserController {

    @Autowired
    private PersonRepo personRepo;

    @Autowired
    private UserProgress userProgress; 

    @Autowired
    private TokenRepo tokenRepo; 

    @Autowired
    private FetchAllUser fetchAlluser;

    @Autowired
    private GetIntRepo getIntScore;


    @Autowired
    private LoginSignInRepository loginSignInRepository;

    @PostMapping("/addPerson")
    public ResponseEntity<User> createPerson(@RequestBody User user) {
        User response = personRepo.save(user);
        return ResponseEntity.ok(response);
    }

    // @GetMapping("/{id}/name-password")
    // public ResponseEntity<LoginSignInDTO> getUserNameAndPassword(@PathVariable Long id) {
    //     LoginSignInDTO user = loginSignInRepository.findNameAndPasswordById(id);
    //     return ResponseEntity.ok(user);
    // }

    @PostMapping("/save-login-signIn")
    public ResponseEntity<String> saveNameAndPassword(@RequestBody LoginSignInDTO dto) {
       LoginSignInDTO existingUser = loginSignInRepository.findNameAndPasswordByToken(dto.getTokens()); // Assume you have a unique constraint on 'name' or 'email'

        User user = new User();
    
        if (existingUser != null) {
            // User already exists, return a message or proceed accordingly
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists!");
        }
    
        // Create a new user if they don't exist
        user.setName(dto.getName());
        user.setToken(dto.getTokens());
        user.setAdminTokens(dto.getAdminTokens());
        user.setPassword(dto.getPassword());
        user.setStatus("normal"); // Set default status
        user.setProgressPerDays(0); // Default progress
        user.setTotalProgress(0); // Default total progress
        user.setActivities(0); // Default activities
        user.setTimeSpent(0);
        user.setScores(0); 
    
        loginSignInRepository.save(user);
        return ResponseEntity.ok("User saved successfully!");
    }


    // updating this to posting 
    // we have a fetch for user or admin login
    // we have a fetchAll 

    @PostMapping("/user-progress")
    public ResponseEntity<String> saveUserProgress(@RequestBody ProgressPerUser dto) {
        ProgressPerUser existingUserDTO = userProgress.findUserByToken(dto.getToken());
        if (existingUserDTO != null) {
            personRepo.updateUserByToken(
                dto.getStatus(),
                dto.progressPerDays(),
                dto.totalProgress(),
                dto.activities(),
                dto.timeSpent(),
                dto.scores(),
                dto.getToken()
            );
            return ResponseEntity.ok("User progress updated successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User not found!");
        }
    }


    // to get all user info needs in client to update in there manually
    @GetMapping("/check-user-credentials")
    public ResponseEntity<Object> getUserCredentials(@RequestParam String token) {
        Token user = tokenRepo.checkUserCredential(token);
        if (user == null) {
            // Return a 404 or custom response
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/fetchAll")
    public ResponseEntity<UserBody> getAllUser(@RequestParam String token) {
        UserBody userList = fetchAlluser.getAllUserCheckToken(token);
        if (userList == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/getIntUser")
    public ResponseEntity<GetInt> getIntUser(@RequestBody GetInt getScore){
        GetInt getIntegerScore = getIntScore.getIntByToken(getScore.getToken());
        return ResponseEntity.ok(getIntegerScore);
    }

    
    @GetMapping("/fetchUser")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = loginSignInRepository.findAll(); // Fetch all users from the database
        return ResponseEntity.ok(users);
    }


    // @GetMapping("/fetchById/{id}")
    // public User getUser(@PathVariable Long id){
    // User user = personRepo.findById(id).orElse(null);
    // return user;
    // }

}

// 8080





