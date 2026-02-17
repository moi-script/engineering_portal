package com.postgresql.projdb.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.postgresql.projdb.model.User;

import jakarta.transaction.Transactional;

@RepositoryRestResource
public interface PersonRepo extends JpaRepository<User, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.status = :status, u.progressPerDays = :progress, u.totalProgress = :totalProgress, u.activities = :activities, u.timeSpent = :timeSpent, u.scores = :scores WHERE u.token = :token")
    void updateUserByToken(
        @Param("status") String status,
        @Param("progress") int progress,
        @Param("totalProgress") int totalProgress,
        @Param("activities") int activities,
        @Param("timeSpent") int timeSpent,
        @Param("scores") int scores,
        @Param("token") String token
    );

    





    

}