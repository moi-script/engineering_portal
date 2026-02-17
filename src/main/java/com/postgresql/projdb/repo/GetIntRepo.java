package com.postgresql.projdb.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.postgresql.projdb.model.User;
import com.postgresql.projdb.model.GetInt;



public interface GetIntRepo extends JpaRepository<User, Long>  {
    @Query("SELECT new com.postgresql.projdb.model.GetInt(scores, token) FROM User WHERE token = :token")
    GetInt getIntByToken(@Param("token") String token);

}
