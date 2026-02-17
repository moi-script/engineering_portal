package com.postgresql.projdb.model.EnrolledStudents.EnrolledRepository;

import com.postgresql.projdb.model.EnrolledStudents.EnrolledList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnrolledListRepository extends JpaRepository<EnrolledList, Long> {
    Optional<EnrolledList> findByAdminToken(String adminToken);
}
