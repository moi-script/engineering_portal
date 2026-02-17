package com.postgresql.projdb.model.EnrolledStudents.Service;
// /C:\projdb\src\main\java\com\postgresql\projdb\model\EnrolledStudents\Service\EnrolledListService.java
import com.fasterxml.jackson.databind.ObjectMapper;
import com.postgresql.projdb.model.EnrolledStudents.EnrolledList;
import com.postgresql.projdb.model.EnrolledStudents.EnrolledListDTO;
import com.postgresql.projdb.model.EnrolledStudents.EnrolledRepository.EnrolledListRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.*;

@Service
public class EnrolledListService {

    @Autowired
    private EnrolledListRepository enrolledListRepository;

   @Transactional
public boolean addUserToAdminList(String adminToken, String userToken) {
    Optional<EnrolledList> enrolledListOpt = enrolledListRepository.findByAdminToken(adminToken);

    if (enrolledListOpt.isEmpty()) {
        // Create a new EnrolledList if it doesn't exist
        EnrolledList newEnrolledList = new EnrolledList();
        System.out.println("THIS IS THE ADMIN TOKEN" +  adminToken);
        newEnrolledList.setAdminToken(adminToken);


        // Initialize the userTokens list and add the userToken
        List<String> userTokens = new ArrayList<>();
        userTokens.add(userToken);
        newEnrolledList.setUserTokens(userTokens);
        System.out.println("THIS IS THE USER TOKEN" +  userTokens);

        System.out.println("THIS IS THE USER TOKEN AFTER ENROLLED" + newEnrolledList.getUserTokens());


        // Save the new list
        enrolledListRepository.save(newEnrolledList);
        return true;
    } else {
        // Handle updating an existing EnrolledList
        EnrolledList existingEnrolledList = enrolledListOpt.get();
        List<String> userTokens = existingEnrolledList.getUserTokens();

        if (!userTokens.contains(userToken)) {
            userTokens.add(userToken);
            existingEnrolledList.setUserTokens(userTokens);
            enrolledListRepository.save(existingEnrolledList);
            return true;
        }

        return false; // User is already enrolled
    }
}

}
