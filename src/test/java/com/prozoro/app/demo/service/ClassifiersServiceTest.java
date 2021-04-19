package com.prozoro.app.demo.service;

import com.prozoro.app.demo.repository.ClassifiersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
public class ClassifiersServiceTest {

    @Autowired
    private ClassifiersRepository classifiersRepository;

    @Autowired
    private ClassifiersService classifiersService;



}
