package com.postgresql.projdb.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.postgresql.projdb.model.LoginSignInDTO;
import com.postgresql.projdb.model.User;


// 1. u.id, u.status, u.progressPerDays, u.totalProgress, u.activities, u.timeSpent, u.scores, 
// 2. u.admintokens, u.name, u.password, u.token ) FROM User u WHERE u.token = :token"
//@Query("SELECT new com.postgresql.projdb.model.LoginSignInDTO(u.name, u.password, u.token, u.admintokens) FROM User u WHERE u.token = :token")

public interface LoginSignInRepository extends JpaRepository<User, Long> {
    @Query("SELECT new com.postgresql.projdb.model.LoginSignInDTO(u.name, u.password, u.token, u.admintokens) FROM User u WHERE u.token = :token")
    LoginSignInDTO findNameAndPasswordByToken(@Param("token") String token);

}
