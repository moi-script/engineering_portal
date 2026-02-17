package com.postgresql.projdb.controller;

import com.postgresql.projdb.Service.ProcessedMessageService;
import com.postgresql.projdb.model.ProcessedMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = { "http://localhost:5173", "http://localhost:3000", "http://localhost:5174/", "https://engineering-portal-front.vercel.app/" })
public class ProcessedMessageController {

    private final ProcessedMessageService processedMessageService;

    @Autowired
    public ProcessedMessageController(ProcessedMessageService processedMessageService) {
        this.processedMessageService = processedMessageService;
    }

    /**
     * POST /processed-messages
     * Save a new processed message.
     *
     * Body: { senderName, receiverName, senderToken, receiverToken, content,
     *         messageType?, status?, createdAt?, messagesListId? }
     */
    @PostMapping("/processed-messages")
    public ResponseEntity<ProcessedMessageDTO> saveProcessedMessage(
            @RequestBody ProcessedMessageDTO dto) {
        try {
            ProcessedMessageDTO saved = processedMessageService.saveProcessedMessage(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     * GET /processed-messages/by-sender?senderToken=...
     * Fetch all messages sent by a given token.
     */
    @GetMapping("/processed-messages/by-sender")
    public ResponseEntity<List<ProcessedMessageDTO>> getBySender(
            @RequestParam String senderToken) {
        List<ProcessedMessageDTO> result = processedMessageService.getMessagesBySenderToken(senderToken);
        return ResponseEntity.ok(result);
    }

    /**
     * GET /processed-messages/by-receiver?receiverToken=...
     * Fetch all messages received by a given token.
     */
    @GetMapping("/processed-messages/by-receiver")
    public ResponseEntity<List<ProcessedMessageDTO>> getByReceiver(
            @RequestParam String receiverToken) {
        List<ProcessedMessageDTO> result = processedMessageService.getMessagesByReceiverToken(receiverToken);
        return ResponseEntity.ok(result);
    }

    /**
     * GET /processed-messages/conversation?senderToken=...&receiverToken=...
     * Fetch the one-way thread from senderToken → receiverToken.
     * Call twice (swap params) to get the full two-way conversation.
     */
    @GetMapping("/processed-messages/conversation")
    public ResponseEntity<List<ProcessedMessageDTO>> getConversation(
            @RequestParam String senderToken,
            @RequestParam String receiverToken) {
        List<ProcessedMessageDTO> result = processedMessageService.getConversation(senderToken, receiverToken);
        return ResponseEntity.ok(result);
    }

    /**
     * GET /processed-messages/by-status?status=sent|delivered|read
     * Fetch all messages with a given delivery status.
     */
    @GetMapping("/processed-messages/by-status")
    public ResponseEntity<List<ProcessedMessageDTO>> getByStatus(
            @RequestParam String status) {
        List<ProcessedMessageDTO> result = processedMessageService.getMessagesByStatus(status);
        return ResponseEntity.ok(result);
    }

    /**
     * GET /processed-messages/by-conversation-list?messagesListId=...
     * Fetch all processed messages linked to a specific messages_list row.
     */
    @GetMapping("/processed-messages/by-conversation-list")
    public ResponseEntity<List<ProcessedMessageDTO>> getByMessagesListId(
            @RequestParam Long messagesListId) {
        List<ProcessedMessageDTO> result = processedMessageService.getMessagesByMessagesListId(messagesListId);
        return ResponseEntity.ok(result);
    }

    /**
     * PATCH /processed-messages/{id}/status?newStatus=delivered
     * Update the delivery status of a single message.
     */
    @PatchMapping("/processed-messages/{id}/status")
    public ResponseEntity<ProcessedMessageDTO> updateStatus(
            @PathVariable Long id,
            @RequestParam String newStatus) {
        try {
            ProcessedMessageDTO updated = processedMessageService.updateStatus(id, newStatus);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * DELETE /processed-messages/{id}
     * Delete a processed message by ID.
     */
    @DeleteMapping("/processed-messages/{id}")
    public ResponseEntity<String> deleteProcessedMessage(@PathVariable Long id) {
        try {
            processedMessageService.deleteProcessedMessage(id);
            return ResponseEntity.ok("Processed message deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Processed message not found");
        }
    }
}
