package com.prozoro.app.demo.repository;

import com.prozoro.app.demo.domain.SectionDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
public class ClassifiersRepositoryTest {

    @Autowired
    private ClassifiersRepository classifiersRepository;

    @Test
    public void findAllSectionDto() {
        List<SectionDto> list = classifiersRepository.findAllSectionDto();
        Assertions.assertNotNull(list);
    }

    @Test
    public void findSectionDto() {
        SectionDto section = classifiersRepository.findSectionDto("03");
        Assertions.assertNotNull(section);
    }



}
