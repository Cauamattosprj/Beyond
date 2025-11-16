package com.beyond.beyond.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ActivitiesIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateAnActivity() throws Exception {
        String json = """
            {
                "name": "Leitura",
                "description": "Realizar leitura de um livro ou artigo",
                "pointsValue": 10,
                "isDefault": true,
                "userId": null,
                "periodId": "HOUR",
                "activityTypeId": "EARNING"
            }
        """;
    }
}
