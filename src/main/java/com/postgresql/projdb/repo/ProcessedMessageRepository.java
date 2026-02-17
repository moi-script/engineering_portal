package com.postgresql.projdb.repo;

import com.postgresql.projdb.model.ProcessedMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessedMessageRepository extends JpaRepository<ProcessedMessage, Long> {

    // Fetch every message where a given token is the sender
    List<ProcessedMessage> findBySenderToken(String senderToken);

    // Fetch every message where a given token is the receiver
    List<ProcessedMessage> findByReceiverToken(String receiverToken);

    // Fetch the full conversation thread between two tokens (both directions)
    List<ProcessedMessage> findBySenderTokenAndReceiverToken(String senderToken, String receiverToken);

    // Fetch by status (e.g. "sent", "delivered", "read")
    List<ProcessedMessage> findByStatus(String status);

    // Fetch all messages linked to a particular messages_list row
    List<ProcessedMessage> findByMessagesList_Id(Long messagesListId);
}
