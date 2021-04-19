package com.prozoro.app.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prozoro.app.demo.service.ClassifiersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = ClassifiersController.class)
public class ClassifiersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ClassifiersService classifiersService;

    @Test
    @WithMockUser(roles = "ADMIN")
    public void updateSection() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/classifiers/update")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser(roles = "VIEWER")
    public void getAllSection() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/classifiers/section")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser(roles = "VIEWER")
    public void getSection() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/classifiers/section/{sectionId}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser(roles = "VIEWER")
    public void getGroups() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/classifiers/groups/{sectionId}", 03)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

    }


}
