package com.postgresql.projdb.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.postgresql.projdb.model.ProgressPerUser;
import com.postgresql.projdb.model.User;


// Long id, String status, int progressPerDays, int totalProgress, int activities, int timeSpent, int scores,
//String admintokens, String name, String password, String token 

public interface UserProgress extends JpaRepository<User, Long>{
    @Query("SELECT new com.postgresql.projdb.model.ProgressPerUser(u.id, u.status, u.progressPerDays, u.totalProgress, u.activities, u.timeSpent, u.scores, u.admintokens, u.name, u.password, u.token ) FROM User u WHERE u.token = :token")
    ProgressPerUser findUserByToken(@Param("token") String token);

}
