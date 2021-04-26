package com.prozoro.app.demo.service;

import com.prozoro.app.demo.domain.SectionDto;
import com.prozoro.app.demo.repository.ClassifiersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
class ClassifiersServiceTest {

    @Autowired
    private ClassifiersService service;

    @MockBean
    private ClassifiersRepository repository;

    @Test
    void findAllSectionDto() {
        when(repository.findAllSectionDto()).thenReturn(
                Stream.of(
                        new SectionDto("03111000-2", "Насіння", "03"),
                        new SectionDto("03111100-3", "Зерно сої", "03"))
                        .collect(Collectors.toList()));
        assertEquals(2, service.findAllSectionDto().size());
    }

    @Test
    void findSectionDto() {
        String sectionId = "03111000-2";
        SectionDto sectionDto = new SectionDto("03111000-2", "Насіння", "03");
        when(repository.findSectionDto(sectionId))
                .thenReturn(sectionDto);
        assertEquals(sectionDto, service.findSectionDto(sectionId));
    }

    @Test
    void findSectionDtoByParent() {
        String parentId = "03";
        List<SectionDto> sectionDto = Stream.of(new SectionDto("03111000-2", "Насіння", "03"))
                .collect(Collectors.toList());
        when(repository.findSectionDtoByParent(parentId))
                .thenReturn(sectionDto);
        assertEquals(sectionDto, service.findSectionDtoByParent(parentId));
    }
}