package com.beyond.beyond.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.beyond.beyond.dto.ActivityDTO;
import com.beyond.beyond.dto.PointsTransactionDTO;
import com.beyond.beyond.dto.UserDTO;
import com.beyond.beyond.utils.TestUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
public class PointsTransactionsIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private UUID activityId;
    private UUID userId;

    @BeforeEach
    void createSampleActivity() throws Exception {
        ActivityDTO activityDTO = new ActivityDTO(null, "Programar", "Programar um pouco todo dia irá me ajudar a construir um melhor raciocínio.", 10, false, null, "HOUR", "EARNING");


        String activityJson = TestUtils.toJson(activityDTO);

        MvcResult activityResult = mockMvc.perform(post("/api/activities")
                .contentType(MediaType.APPLICATION_JSON)
                .content(activityJson))
                .andExpect(status().isCreated())
                .andReturn();

        JsonNode activityResponse = objectMapper.readTree(activityResult.getResponse().getContentAsString());

        String activityIdString = activityResponse.get("id").asText();
        activityId = UUID.fromString(activityIdString);
    }

    @BeforeEach
    void createSampleUser() throws Exception {
        UserDTO userDTO = new UserDTO(null, "Caua de Mattos Pereira", "caua@gmail.com", "1234");


        String userJson = TestUtils.toJson(userDTO);

        MvcResult userResult = mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value("caua@gmail.com"))
                .andReturn();

        JsonNode userResponse = objectMapper.readTree(userResult.getResponse().getContentAsString());

        String userIdString = userResponse.get("id").asText();
        userId = UUID.fromString(userIdString);
    }

    @Test
    void shouldCreate() throws Exception {
        Integer periodAmount = 4;
        PointsTransactionDTO dto = new PointsTransactionDTO(null, activityId, userId, "HOUR", periodAmount, 10);
        String json = TestUtils.toJson(dto);

        mockMvc.perform(post("/api/pointsTransactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.activityId").value(activityId.toString()))
                .andExpect(jsonPath("$.userId").value(userId.toString()))
                .andExpect(jsonPath("$.periodAmount").value(periodAmount));

    }
}
