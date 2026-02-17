// package com.postgresql.projdb.controller;

// import java.util.ArrayList;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.fasterxml.jackson.databind.JsonNode;
// import com.postgresql.projdb.model.User;
// import com.postgresql.projdb.repo.UserRepository;

// @RestController
// @RequestMapping("/messages")
// public class MessageController {

//     @Autowired
//     private UserRepository userRepository;

//     // POST Request to send a message to a user
//     // this userId should be a admin tokens to select them specifically
//     @PostMapping("/send/{userId}")
//     public User sendMessage(@PathVariable Long userId, @RequestBody JsonNode message) {
//         User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        
//         // Get current messages, or initialize an empty list
//         List<JsonNode> currentMessages = user.getMessages();
//         if (currentMessages == null) {
//             currentMessages = new ArrayList<>();
//         }

//         // Add the new message to the list
//         currentMessages.add(message);

//         // Update the user's messages and save the user
//         user.setMessages(currentMessages);
//         return userRepository.save(user);
//     }

//     // GET Request to retrieve all messages for a user
//     @GetMapping("/get/{userId}")
//     public List<JsonNode> getMessages(@PathVariable Long userId) {
//         User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//         return user.getMessages(); // Return the list of messages
//     }
// }
