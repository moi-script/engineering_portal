package com.postgresql.projdb.model.Enroll;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
@Repository
public interface StudentListRepository extends JpaRepository<StudentList, Long> {
    List<StudentList> findByEnrolledList_Id(Long enrolledListId);

    // @Query("SELECT new package com.postgresql.projdb.model.Enroll.StudentListDTO(user_token) FROM student_list WHERE user_tokens = :user_tokens")
    // StudentListDTO findStudentByStudentToken(@Param("user_tokens") String user_tokens);
}
