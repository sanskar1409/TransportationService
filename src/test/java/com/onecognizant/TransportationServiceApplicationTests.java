package com.onecognizant;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class TransportationServiceApplicationTests {

    private final MockMvc mockMvc;

    @Autowired
    public TransportationServiceApplicationTests(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void testFindSubscriptionById() throws Exception {
        mockMvc.perform(get("/api/subscriptions/1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testFindSubscriptionById_NotFound() throws Exception {
        mockMvc.perform(get("/api/subscriptions/9999"))
            .andExpect(status().isNotFound());
    }

    @Test
    void testGetAllTransportSubscriptionsById() throws Exception {
        mockMvc.perform(get("/api/transports/Pune"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetAllTransportSubscriptionsById_NotFound() throws Exception {
        mockMvc.perform(get("/api/transports/UnknownCity"))
            .andExpect(status().isNotFound());
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

    @Test
    void testCreateSubscription_InvalidData() throws Exception {
        String requestBody = "{ \"subscribedByEmployee\": \"\", \"subscriptionStartDate\": \"\", \"subscriptionEndDate\": \"\", \"transportService\": { \"id\": null } }";
        mockMvc.perform(post("/api/subscriptions/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isBadRequest());
    }

    @Test
    void testDeleteSubscription() throws Exception {
        mockMvc.perform(delete("/api/subscriptions/1"))
            .andExpect(status().isOk());
    }

    @Test
    void testDeleteSubscription_NotFound() throws Exception {
        mockMvc.perform(delete("/api/subscriptions/9999"))
            .andExpect(status().isNotFound());
    }

    @Test
    void testAddNewTransportService() throws Exception {
        String requestBody = "{ \"serviceName\": \"Bus\", \"pickupPoint\": \"Station\", \"currentCapacity\": 10 }";
        mockMvc.perform(post("/api/transports/addNewService")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().string("New Service added successfully"));
    }

    @Test
    void testAddNewTransportService_InvalidData() throws Exception {
        String requestBody = "{ \"serviceName\": \"\", \"pickupPoint\": \"\", \"currentCapacity\": null }";
        mockMvc.perform(post("/api/transports/addNewService")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());
    }
}