package com.postgresql.projdb.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.postgresql.projdb.model.User;
import com.postgresql.projdb.userHolder.UserBody;

public interface FetchAllUser extends JpaRepository<User, Long> {
    
    @Query("SELECT new com.postgresql.projdb.userHolder.UserBody( status, progressPerDays, totalProgress, activities, timeSpent, scores, admintokens, name, password, token) FROM User WHERE token = :token")
      UserBody getAllUserCheckToken(@Param("token") String token);
     
}

/*
SELECT status, progressPerDays, totalProgress, status, progressPerDays, totalProgress
       "u.status, u.progressPerDays, u.totalProgress, " +
       "u.activities, u.timeSpent, u.scores, u.admintokens, " +
       "u.name, u.password, u.token) " +
       "FROM User u WHERE u.token = :token"
 */
