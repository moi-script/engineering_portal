package com.postgresql.projdb.Service;

import com.postgresql.projdb.model.MessagesList;
import com.postgresql.projdb.model.ProcessedMessage;
import com.postgresql.projdb.model.ProcessedMessageDTO;
import com.postgresql.projdb.repo.MessagesListRepository;
import com.postgresql.projdb.repo.ProcessedMessageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProcessedMessageService {

    private final ProcessedMessageRepository processedMessageRepository;
    private final MessagesListRepository messagesListRepository;

    @Autowired
    public ProcessedMessageService(ProcessedMessageRepository processedMessageRepository,
                                   MessagesListRepository messagesListRepository) {
        this.processedMessageRepository = processedMessageRepository;
        this.messagesListRepository = messagesListRepository;
    }

    // ── Create ─────────────────────────────────────────────────────────────────

    @Transactional
    public ProcessedMessageDTO saveProcessedMessage(ProcessedMessageDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("ProcessedMessageDTO cannot be null");
        }
        validateRequiredFields(dto);

        ProcessedMessage entity = toEntity(dto);
        ProcessedMessage saved = processedMessageRepository.save(entity);
        return toDTO(saved);
    }

    // ── Read ───────────────────────────────────────────────────────────────────

    public List<ProcessedMessageDTO> getMessagesBySenderToken(String senderToken) {
        return processedMessageRepository.findBySenderToken(senderToken)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<ProcessedMessageDTO> getMessagesByReceiverToken(String receiverToken) {
        return processedMessageRepository.findByReceiverToken(receiverToken)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<ProcessedMessageDTO> getConversation(String senderToken, String receiverToken) {
        return processedMessageRepository
                .findBySenderTokenAndReceiverToken(senderToken, receiverToken)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<ProcessedMessageDTO> getMessagesByStatus(String status) {
        return processedMessageRepository.findByStatus(status)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<ProcessedMessageDTO> getMessagesByMessagesListId(Long messagesListId) {
        return processedMessageRepository.findByMessagesList_Id(messagesListId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    // ── Update Status ──────────────────────────────────────────────────────────

    @Transactional
    public ProcessedMessageDTO updateStatus(Long id, String newStatus) {
        ProcessedMessage entity = processedMessageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ProcessedMessage not found with id: " + id));
        entity.setStatus(newStatus);
        return toDTO(processedMessageRepository.save(entity));
    }

    // ── Delete ─────────────────────────────────────────────────────────────────

    @Transactional
    public void deleteProcessedMessage(Long id) {
        if (!processedMessageRepository.existsById(id)) {
            throw new IllegalArgumentException("ProcessedMessage not found with id: " + id);
        }
        processedMessageRepository.deleteById(id);
    }

    // ── Helpers ────────────────────────────────────────────────────────────────

    private void validateRequiredFields(ProcessedMessageDTO dto) {
        if (dto.getSenderName() == null || dto.getSenderName().trim().isEmpty()) {
            throw new IllegalArgumentException("senderName cannot be null or empty");
        }
        if (dto.getReceiverName() == null || dto.getReceiverName().trim().isEmpty()) {
            throw new IllegalArgumentException("receiverName cannot be null or empty");
        }
        if (dto.getSenderToken() == null || dto.getSenderToken().trim().isEmpty()) {
            throw new IllegalArgumentException("senderToken cannot be null or empty");
        }
        if (dto.getReceiverToken() == null || dto.getReceiverToken().trim().isEmpty()) {
            throw new IllegalArgumentException("receiverToken cannot be null or empty");
        }
        if (dto.getContent() == null || dto.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("content cannot be null or empty");
        }
    }

    private ProcessedMessage toEntity(ProcessedMessageDTO dto) {
        ProcessedMessage entity = new ProcessedMessage();
        entity.setSenderName(dto.getSenderName());
        entity.setReceiverName(dto.getReceiverName());
        entity.setSenderToken(dto.getSenderToken());
        entity.setReceiverToken(dto.getReceiverToken());
        entity.setContent(dto.getContent());

        if (dto.getMessageType() != null) {
            entity.setMessageType(dto.getMessageType());
        }
        if (dto.getStatus() != null) {
            entity.setStatus(dto.getStatus());
        }
        if (dto.getCreatedAt() != null) {
            entity.setCreatedAt(dto.getCreatedAt());
        }

        // Optionally link to a messages_list conversation thread
        if (dto.getMessagesListId() != null) {
            MessagesList ml = messagesListRepository.findById(dto.getMessagesListId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "MessagesList not found with id: " + dto.getMessagesListId()));
            entity.setMessagesList(ml);
        }

        return entity;
    }

    private ProcessedMessageDTO toDTO(ProcessedMessage entity) {
        Long mlId = (entity.getMessagesList() != null) ? entity.getMessagesList().getId() : null;
        return new ProcessedMessageDTO(
                entity.getId(),
                entity.getSenderName(),
                entity.getReceiverName(),
                entity.getSenderToken(),
                entity.getReceiverToken(),
                entity.getContent(),
                entity.getMessageType(),
                entity.getStatus(),
                entity.getCreatedAt(),
                mlId
        );
    }
}
