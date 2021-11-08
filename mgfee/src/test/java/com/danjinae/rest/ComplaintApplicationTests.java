package com.danjinae.rest;

import com.danjinae.rest.Complaint.DTO.NewComplaintRequest;
import com.danjinae.rest.Complaint.controller.ComplaintController;
import com.danjinae.rest.Complaint.service.ComplaintService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
class NoticeApplicationTests {
    @Test
    void contextLoads() {
    }

    @InjectMocks
    private ComplaintController complaintController;

    @Mock
    private ComplaintService complaintService;

    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(complaintController).build();
    }

    @Test
    @DisplayName("새로운 민원신고 테스트")
    void NewComplaintTest() {
        final NewComplaintRequest newComplaint = NewComplaint();
    }

    private NewComplaintRequest NewComplaint() {
        final NewComplaintRequest newComplaint = new NewComplaintRequest();
        newComplaint.setAptId(1);
        newComplaint.setUserId(1);
        newComplaint.setContent("TEST");
        return newComplaint;
    }
}
