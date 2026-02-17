package com.postgresql.projdb.repo;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.postgresql.projdb.model.AdminEntity;
import com.postgresql.projdb.model.AdminLoginDTO;

public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
    Optional<AdminEntity> findByAdmintokens(String admintokens);
    // somehow the error about the adminTokens
    
    @Query("SELECT new com.postgresql.projdb.model.AdminLoginDTO(name, password, admintokens) FROM AdminEntity WHERE admintokens = :admintokens")
AdminLoginDTO findAdminByAdminToken(@Param("admintokens") String admintokens);



}