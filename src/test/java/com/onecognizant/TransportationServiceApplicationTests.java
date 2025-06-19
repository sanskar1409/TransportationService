package com.onecognizant;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@EnableWebMvc
@AutoConfigureMockMvc
class TransportationServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testFindSubscriptionById() throws Exception {
        mockMvc.perform(get("/api/subscriptions/1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
            
    }

    @Test
    void testGetAllTransportSubscriptionsById() throws Exception {
        mockMvc.perform(get("/api/transports/Pune"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
            
    }
    
    @Test
    void testCreateSubscription() throws Exception {
        
        String requestBody = "{ \"subscribedByEmployee\": \"5\", \"subscriptionStartDate\": \"2023-07-21\", \"subscriptionEndDate\": \"2023-12-21\", \"transportService\": { \"id\": 5 } }";
        
        
        mockMvc.perform(post("/api/subscriptions/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json("{ \"message\": \"Subscription created successfully\" }"));
    }

}
