package com.prozoro.app.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prozoro.app.demo.domain.SectionDto;
import com.prozoro.app.demo.service.ClassifiersService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(controllers = ClassifiersController.class)
@RunWith(SpringRunner.class)
public class ClassifiersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ClassifiersService classifiersService;

    @Test
    @WithMockUser(roles = "USER")
    public void getAllSection() throws Exception {

        List<SectionDto> list = Arrays.asList(
                SectionDto.builder()
                        .id("03111000-2")
                        .description("Насіння")
                        .parent("03")
                        .build(),
                SectionDto.builder()
                        .id("03111100-3")
                        .description("Зерно сої")
                        .parent("03")
                        .build());


        Mockito.when(classifiersService.findAllSectionDto()).thenReturn(list);

        String resultJson = mockMvc.perform(get("/v1/classifiers/section")
                .content(objectMapper.writeValueAsString(list))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<SectionDto> actualList = objectMapper.readValue(resultJson, List.class);
        Assertions.assertThat(list.size()).isEqualTo(actualList.size());

    }

    @Test
    @WithMockUser(roles = "VIEWER")
    public void getSection() throws Exception {
        SectionDto section = SectionDto.builder()
                .id("03111000-2")
                .description("Насіння")
                .parent("03")
                .build();

        Mockito.when(classifiersService.findSectionDto(section.getId())).thenReturn(section);

        String resultJson = mockMvc.perform(get("/v1/classifiers/section/{sectionId}", section.getId())
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        SectionDto actualSection = objectMapper.readValue(resultJson, SectionDto.class);
        Assertions.assertThat(section.getId()).isEqualTo(actualSection.getId());


    }

    @Test
    @WithMockUser(roles = "VIEWER")
    public void getSectionByParent() throws Exception {
        List<SectionDto> list = Arrays.asList(
                SectionDto.builder()
                        .id("03111000-2")
                        .description("Насіння")
                        .parent("03")
                        .build(),
                SectionDto.builder()
                        .id("03111100-3")
                        .description("Зерно сої")
                        .parent("03")
                        .build());

        Mockito.when(classifiersService.findSectionDtoByParent("03")).thenReturn(list);

        String resultJson = mockMvc.perform(get("/v1/classifiers/parent/{parentId}", "03")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<SectionDto> actualList = objectMapper.readValue(resultJson, List.class);
        Assertions.assertThat(list.size()).isEqualTo(actualList.size());

    }


}
