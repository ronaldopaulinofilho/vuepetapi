package com.vuepetapi.vuepetapi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vuepetapi.vuepetapi.domain.Dog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class DogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void returnAllDogs() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/dogs"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void saveDog() throws Exception {
        Dog dog = new Dog("ww", "SRD", 2, 2);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(dog);
        mockMvc
                .perform(MockMvcRequestBuilders.post("/dogs").contentType(MediaType.APPLICATION_JSON).content(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc
                .perform(MockMvcRequestBuilders.get("/dogs/{nome}", "ww"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("ww"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.idade").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.raca").value("SRD"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.peso").value(3));
    }
}




