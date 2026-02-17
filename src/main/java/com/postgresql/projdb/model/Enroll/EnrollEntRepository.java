package com.postgresql.projdb.model.Enroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnrollEntRepository extends JpaRepository<EnrollEnt, Long> {
    Optional<EnrollEnt> findByAdminToken(String adminToken);
}