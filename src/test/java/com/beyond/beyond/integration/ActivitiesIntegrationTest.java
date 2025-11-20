package com.beyond.beyond.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@EnableConfigurationProperties
@AutoConfigureMockMvc
class ActivitiesIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String createdId;

    @BeforeEach
    void createSampleActivity() throws Exception {
        String json = """
            {
                "name": "Leitura",
                "description": "Realizar leitura de um livro ou artigo",
                "pointsValue": 10,
                "isDefault": true,
                "userId": null,
                "period": "HOUR",
                "activityType": "EARNING"
            }
        """;

        MvcResult result = mockMvc.perform(
                post("/api/activities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
            )
            .andExpect(status().isCreated())
            .andReturn();

        JsonNode responseJson =
                objectMapper.readTree(result.getResponse().getContentAsString());

        createdId = responseJson.get("id").asText();
    }

    @Test
    void shouldCreate() throws Exception {
        String json = """
            {
                "name": "Corrida",
                "description": "Correr por 20 minutos",
                "pointsValue": 20,
                "isDefault": false,
                "userId": null,
                "period": "HOUR",
                "activityType": "EARNING"
            }
        """;

        mockMvc.perform(post("/api/activities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Corrida"));
    }

    @Test
    void shouldGetById() throws Exception {
        mockMvc.perform(get("/api/activities/" + createdId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(createdId))
                .andExpect(jsonPath("$.name").value("Leitura"));
    }

    @Test
    void shouldListAll() throws Exception {
        mockMvc.perform(get("/api/activities"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").isNotEmpty());
    }

    @Test
    void shouldUpdate() throws Exception {
        String updateJson = """
            {
                "name": "Leitura Atualizada",
                "description": "Descrição atualizada",
                "pointsValue": 15,
                "isDefault": true,
                "userId": null,
                "period": "HOUR",
                "activityType": "EARNING"
            }
        """;

        mockMvc.perform(put("/api/activities/" + createdId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Leitura Atualizada"))
                .andExpect(jsonPath("$.pointsValue").value(15));
    }

    @Test
    void shouldDelete() throws Exception {
        mockMvc.perform(delete("/api/activities/" + createdId))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/activities/" + createdId))
                .andExpect(status().isNotFound());
    }
}
