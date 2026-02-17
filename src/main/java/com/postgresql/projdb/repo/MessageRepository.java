package com.postgresql.projdb.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.postgresql.projdb.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

}