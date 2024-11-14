package com.postgresql.projdb.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.postgresql.projdb.model.User;


@RepositoryRestResource
public interface PersonRepo extends JpaRepository<User, Long>{

    
} 