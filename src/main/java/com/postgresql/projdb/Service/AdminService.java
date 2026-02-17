// package com.postgresql.projdb.Service;

// import java.util.List;
// import java.util.stream.Collectors;

// import org.springframework.stereotype.Service;

// import com.postgresql.projdb.model.AdminEntity;
// import com.postgresql.projdb.model.Message;
// import com.postgresql.projdb.model.MessageDTO;
// import com.postgresql.projdb.model.MessagesList;
// import com.postgresql.projdb.model.MessagesListDTO;
// import com.postgresql.projdb.repo.AdminRepository;
// import com.postgresql.projdb.repo.MessagesListRepository;

// import jakarta.transaction.Transactional;

// @Service
// public class AdminService {

//     private final AdminRepository adminRepository;
//     private final MessagesListRepository messagesListRepository;

//     public AdminService(AdminRepository adminRepository, MessagesListRepository messagesListRepository) {
//         this.adminRepository = adminRepository;
//         this.messagesListRepository = messagesListRepository;
//     }

//     // Fetch all message lists for a specific admin
//     public List<MessagesListDTO> getMessagesLists(String token) {
//         AdminEntity admin = adminRepository.findByAdmintokens(token)
//                 .orElseThrow(() -> new IllegalArgumentException("Invalid admin token"));

//         return admin.getMessagesList().stream().map(this::convertToMessagesListDTO).toList();
//     }

// @Transactional
// public void addMessageList(String token, MessagesListDTO messagesListDTO) {
//     AdminEntity admin = adminRepository.findByAdmintokens(token)
//         .orElseThrow(() -> new IllegalArgumentException("Invalid admin token"));

//     // Create the message list entity
//     MessagesList messagesList = new MessagesList();
//     messagesList.setName(messagesListDTO.getName());
//     messagesList.setAdminToken(token);
//     messagesList.setAdmin(admin);

//     // Validate and convert each message in the DTO to entity
//     List<Message> messages = messagesListDTO.getMessages().stream()
//             .map(this::convertToMessageEntity)
//             .filter(message -> message.getMessageContent() != null && !message.getMessageContent().isEmpty()) // Validation for null content
//             .collect(Collectors.toList());

//     if (messages.isEmpty()) {
//         throw new IllegalArgumentException("Message content cannot be null or empty.");
//     }

//     // Set the messages to the message list and save the list
//     messagesList.setContent(messages);
//     messages.forEach(message -> message.setMessagesList(messagesList));
//     messagesListRepository.save(messagesList);
// }

//     private MessagesListDTO convertToMessagesListDTO(MessagesList messagesList) {
//         List<MessageDTO> messageDTOs = messagesList.getContent().stream().map(this::convertToMessageDTO).toList();
//         return new MessagesListDTO(messagesList.getId(), messagesList.getName(), messagesList.getAdminToken(), messageDTOs);
//     }

//     private Message convertToMessageEntity(MessageDTO messageDTO) {
//        System.out.println("Converting MessageDTO: {} " + messageDTO.getMessageContent());
//         if (messageDTO == null) {
//             throw new IllegalArgumentException("MessageDTO cannot be null");
//         }

//         // System.out.println("Message Content: [{}]" + messageDTO.getMessageContent());
//         // System.out.println("Message Type: [{}]" + messageDTO.getMessageType());
//         // System.out.println("Timestamp: [{}]" + messageDTO.getTimeStamp());

//         Message message = new Message();

//         // Validate and set message content
//         if (messageDTO.getMessageContent() == null || messageDTO.getMessageContent().trim().isEmpty()) {
//             throw new IllegalArgumentException("Message content cannot be null or empty");
//         }
//         message.setMessageContent(messageDTO.getMessageContent());

//         // Set other fields
//         message.setMessageType(messageDTO.getMessageType());
//         message.setTimeStamp(messageDTO.getTimeStamp());

//         return message;
//     }

//     private MessageDTO convertToMessageDTO(Message message) {
//         System.out.println("This will be the message object" +message.getMessageContent() + message.getMessageType() + message.getTimeStamp() );
//         return new MessageDTO(message.getMessageContent(), message.getMessageType(), message.getTimeStamp());
//     }

//     @Transactional
//     public void addMessage(String adminToken, MessageDTO messageDTO) {
//         // Find the MessagesList by adminToken
//         System.out.println("This is the passed object " + messageDTO);
//         MessagesList messagesList = messagesListRepository.findByAdmintoken(adminToken)
//                 .orElseThrow(() -> new IllegalArgumentException("No MessagesList found for the provided adminToken"));

//         // Convert DTO to Message entity
//         Message newMessage = convertToMessageEntity(messageDTO);

//         // Validate the message content
//         if (newMessage.getMessageContent() == null || newMessage.getMessageContent().trim().isEmpty()) {
//             throw new IllegalArgumentException("Message content cannot be null or empty.");
//         }

//         newMessage.setMessagesList(messagesList);
//         messagesList.getContent().add(newMessage);

//         messagesListRepository.save(messagesList);
//     }

// }




// CHAPTER 2

// package com.postgresql.projdb.Service;

// import java.util.List;
// import java.util.stream.Collectors;

// import org.springframework.stereotype.Service;

// import com.postgresql.projdb.model.AdminEntity;
// import com.postgresql.projdb.model.Message;
// import com.postgresql.projdb.model.MessageDTO;
// import com.postgresql.projdb.model.MessagesList;
// import com.postgresql.projdb.model.MessagesListDTO;
// import com.postgresql.projdb.repo.AdminRepository;
// import com.postgresql.projdb.repo.MessagesListRepository;

// import jakarta.transaction.Transactional;

// @Service
// public class AdminService {

//     private final AdminRepository adminRepository;
//     private final MessagesListRepository messagesListRepository;

//     public AdminService(AdminRepository adminRepository, MessagesListRepository messagesListRepository) {
//         this.adminRepository = adminRepository;
//         this.messagesListRepository = messagesListRepository;
//     }

//     // Fetch all message lists for a specific admin
//     public List<MessagesListDTO> getMessagesLists(String token) {
//         AdminEntity admin = adminRepository.findByAdmintokens(token)
//                 .orElseThrow(() -> new IllegalArgumentException("Invalid admin token"));

//         return admin.getMessagesList().stream().map(this::convertToMessagesListDTO).toList();
//     }

//     // Add message to an existing or new message list for the provided admin token
//     @Transactional
//     public void addMessage(String token, MessageDTO messageDTO) {
//         // Fetch the admin entity by token
//         AdminEntity admin = adminRepository.findByAdmintokens(token)
//                 .orElseThrow(() -> new IllegalArgumentException("Invalid admin token"));

//         // Check if the message list exists for the provided token
//         MessagesList messagesList = messagesListRepository.findByAdmintoken(token)
//                 .orElseGet(() -> createNewMessageList(token, admin)); // Create a new list if none exists

//         // Convert the DTO to the Message entity
//         Message message = convertToMessageEntity(messageDTO);

//         // Add the message to the message list using the addContent method
//         messagesList.addContent(message);

//         // Save the updated or newly created message list
//         messagesListRepository.save(messagesList);
//     }

//     // Helper method to create a new message list if none exists for the admin token
//     private MessagesList createNewMessageList(String token, AdminEntity admin) {
//         MessagesList newMessagesList = new MessagesList();
//         newMessagesList.setName("New Message List for " + admin.getName()); // Set a default name or custom name
//         newMessagesList.setAdminToken(token);
//         newMessagesList.setAdmin(admin);
//         return newMessagesList;
//     }

//     // Helper method to convert MessageDTO to Message entity
//     private Message convertToMessageEntity(MessageDTO messageDTO) {
//         if (messageDTO == null) {
//             throw new IllegalArgumentException("MessageDTO cannot be null");
//         }

//         Message message = new Message();

//         // Validate message content
//         if (messageDTO.getMessageContent() == null || messageDTO.getMessageContent().trim().isEmpty()) {
//             throw new IllegalArgumentException("Message content cannot be null or empty");
//         }

//         message.setMessageContent(messageDTO.getMessageContent());
//         message.setMessageType(messageDTO.getMessageType());
//         message.setTimeStamp(messageDTO.getTimeStamp());

//         return message;
//     }

//     // Helper method to convert MessagesList to MessagesListDTO
//     private MessagesListDTO convertToMessagesListDTO(MessagesList messagesList) {
//         List<MessageDTO> messageDTOs = messagesList.getContent().stream().map(this::convertToMessageDTO)
//                 .collect(Collectors.toList());
//         return new MessagesListDTO(messagesList.getId(), messagesList.getName(), messagesList.getAdminToken(),
//                 messageDTOs);
//     }

//     // Helper method to convert Message to MessageDTO
//     private MessageDTO convertToMessageDTO(Message message) {
//         return new MessageDTO(message.getMessageContent(), message.getMessageType(), message.getTimeStamp());
//     }

//     @Transactional
//     public void addOrCreateMessageList(String token, MessageDTO messageDTO) {
//         // Fetch the admin by token
//         AdminEntity admin = adminRepository.findByAdmintokens(token)
//                 .orElseThrow(() -> new IllegalArgumentException("Invalid admin token"));

//         // Find the existing MessagesList by token or create a new one
//         MessagesList messagesList = messagesListRepository.findByAdmintoken(token)
//                 .orElseGet(() -> createNewMessagesList(token, admin));

//         // Convert the DTO to Message entity
//         Message newMessage = convertToMessageEntity(messageDTO);

//         // Add the message to the list using the `addContent()` method
//         messagesList.addContent(newMessage);

//         // Save the updated or newly created message list
//         messagesListRepository.save(messagesList);
//     }

//     private MessagesList createNewMessagesList(String token, AdminEntity admin) {
//         MessagesList newMessagesList = new MessagesList();
//         newMessagesList.setName("Default Message List");
//         newMessagesList.setAdminToken(token);
//         newMessagesList.setAdmin(admin);
//         return newMessagesList;
//     }

// }




// CHAPTER 3



package com.postgresql.projdb.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.postgresql.projdb.model.AdminEntity;
import com.postgresql.projdb.model.Message;
import com.postgresql.projdb.model.MessageDTO;
import com.postgresql.projdb.model.MessagesList;
import com.postgresql.projdb.model.MessagesListDTO;
import com.postgresql.projdb.repo.AdminRepository;
import com.postgresql.projdb.repo.MessagesListRepository;

import jakarta.transaction.Transactional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final MessagesListRepository messagesListRepository;

    public AdminService(AdminRepository adminRepository, MessagesListRepository messagesListRepository) {
        this.adminRepository = adminRepository;
        this.messagesListRepository = messagesListRepository;
    }

      // Fetch a conversation (message list) for a person based on the personConvoWithToken
      @Transactional
      public MessagesListDTO getConversationForPerson(String personConvoWithToken) {
          // Find the message list by personConvoWithToken
          MessagesList messagesList = messagesListRepository.findByPersonConvoWithToken(personConvoWithToken)
                  .orElseThrow(() -> new IllegalArgumentException("No conversation found for the provided person token"));
  
          // Convert the MessagesList entity to DTO for response
          return convertToMessagesListDTO(messagesList);
      }
  

    // Fetch all message lists for a specific admin
    public List<MessagesListDTO> getMessagesLists(String token) {
        AdminEntity admin = adminRepository.findByAdmintokens(token)
                .orElseThrow(() -> new IllegalArgumentException("Invalid admin token"));

        return admin.getMessagesList().stream()
                    .map(this::convertToMessagesListDTO)
                    .collect(Collectors.toList());
    }

    // Add a new message to an existing message list or create a new message list if none exists
    @Transactional
    public void addOrCreateMessageList(String token, MessageDTO messageDTO) {
        // Fetch the admin entity by token
        AdminEntity admin = adminRepository.findByAdmintokens(token)
                .orElseThrow(() -> new IllegalArgumentException("Invalid admin token"));

        // Check if the message list exists for the provided token
        MessagesList messagesList = messagesListRepository.findByAdmintoken(token)
                .orElseGet(() -> createNewMessagesList(token, admin));  // Create new if none exists

        // Convert the DTO to the Message entity
        Message newMessage = convertToMessageEntity(messageDTO);

        // Add the message to the message list using the addContent method
        messagesList.addContent(newMessage);

        // Save the updated or newly created message list
        messagesListRepository.save(messagesList);
    }

    // Helper method to create a new message list if none exists for the admin token
    @Transactional
    private MessagesList createNewMessagesList(String token, AdminEntity admin) {
        MessagesList newMessagesList = new MessagesList();
        newMessagesList.setName("Default Message List for " + admin.getName());  // Set a default or custom name
        newMessagesList.setAdminToken(token);
        newMessagesList.setAdmin(admin);
        return newMessagesList;
    }

    // Helper method to convert MessageDTO to Message entity
    @Transactional
    private Message convertToMessageEntity(MessageDTO messageDTO) {
        if (messageDTO == null) {
            throw new IllegalArgumentException("MessageDTO cannot be null");
        }

        Message message = new Message();

        // Validate message content
        if (messageDTO.getMessageContent() == null || messageDTO.getMessageContent().trim().isEmpty()) {
            throw new IllegalArgumentException("Message content cannot be null or empty");
        }

        message.setMessageContent(messageDTO.getMessageContent());
        message.setMessageType(messageDTO.getMessageType());
        message.setTimeStamp(messageDTO.getTimeStamp());

        return message;
    }

    // Helper method to convert MessagesList to MessagesListDTO
   // Helper method to convert MessagesList to MessagesListDTO
   private MessagesListDTO convertToMessagesListDTO(MessagesList messagesList) {
    return new MessagesListDTO(
            messagesList.getId(),
            messagesList.getName(),
            messagesList.getAdminToken(),
            messagesList.getPersonConvoWithToken(),
            messagesList.getContent().stream().map(this::convertToMessageDTO).toList()
    );
}

// Helper method to convert Message to MessageDTO
private MessageDTO convertToMessageDTO(Message message) {
    return new MessageDTO(message.getMessageContent(), message.getMessageType(), message.getTimeStamp());
}
    // Method to handle adding messages to an existing message list by personConvoWithToken
    @Transactional
    public void addMessageToPersonConversation(String adminToken, String personToken, MessageDTO messageDTO) {
        // Fetch the admin and verify token
        AdminEntity admin = adminRepository.findByAdmintokens(adminToken)
                .orElseThrow(() -> new IllegalArgumentException("Invalid admin token"));

        // Find or create a message list based on the personConvoWithToken
        MessagesList messagesList = messagesListRepository.findByPersonConvoWithToken(personToken)
                .orElseGet(() -> createNewMessagesListForPerson(admin, personToken));

        // Convert the DTO to Message entity
        Message newMessage = convertToMessageEntity(messageDTO);

        // Add the message to the list using the `addContent()` method
        messagesList.addContent(newMessage);

        // Save the updated or newly created message list
        messagesListRepository.save(messagesList);
    }

    // Helper method to create a new message list for a specific user token
    @Transactional
    private MessagesList createNewMessagesListForPerson(AdminEntity admin, String personToken) {
        MessagesList newMessagesList = new MessagesList();
        newMessagesList.setName("Message List with " + personToken);  // You could customize this
        newMessagesList.setAdminToken(admin.getAdminTokens());
        newMessagesList.setAdmin(admin);
        newMessagesList.setPersonConvoWithToken(personToken);  // Set the person token to link conversation
        return newMessagesList;
    }
}
