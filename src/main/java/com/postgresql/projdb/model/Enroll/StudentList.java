package com.postgresql.projdb.model.Enroll;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;


@Entity
@Table(name = "student_list")
public class StudentList {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_token")
    private String userToken;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enroll_id")
    @JsonIgnore
    private EnrollEnt enrolledList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public EnrollEnt getEnrolledList() {
        return enrolledList;
    }

    public void setEnrolledList(EnrollEnt enrolledList) {
        this.enrolledList = enrolledList;
    }
}
