package com.prozoro.app.demo.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
public class UserRoleRepositoryTest {

    @Autowired
    private UserRoleRepository userRoleRepository;


    @Test
    public void loadUserByUserName() {
        UserDetails user = userRoleRepository.loadUserByUserName("jane");
        Assertions.assertNotNull(user);
        Assertions.assertTrue(user.getUsername().equals("jane"));

    }
}
