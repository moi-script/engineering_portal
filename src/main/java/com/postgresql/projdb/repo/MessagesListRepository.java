package com.postgresql.projdb.repo;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.postgresql.projdb.model.MessagesList;

public interface MessagesListRepository extends JpaRepository<MessagesList, Long> {
    Optional<MessagesList> findByAdmintoken(String admintoken);
    Optional<MessagesList> findByPersonConvoWithToken(String admintoken);


}