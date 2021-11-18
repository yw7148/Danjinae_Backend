package com.capstone.danjinae;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.capstone.danjinae.Complaint.DTO.NewComplaintRequest;
import com.capstone.danjinae.Complaint.controller.ComplaintController;
import com.capstone.danjinae.Complaint.entity.Complaint;
import com.capstone.danjinae.Complaint.repository.ComplaintRepository;
import com.capstone.danjinae.Complaint.service.ComplaintService;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
class NoticeApplicationTests {
    @InjectMocks
    private ComplaintController complaintController;

    @InjectMocks
    private ComplaintService complaintService;

    @Mock
    private ComplaintRepository complaintRepository;

    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(complaintController).build();
    }

    @Test
    @DisplayName("새로운 민원신고 테스트")
    void NewComplaintTest() {
        final NewComplaintRequest newComplaint = NewComplaint();

        Boolean result = complaintController.addNewComplaint(newComplaint);
    }

    @Test
    @DisplayName("테스트")
    void ManagerGetComplaintListTest() {

    }

    private NewComplaintRequest NewComplaint() {
        final NewComplaintRequest newComplaint = new NewComplaintRequest();
        newComplaint.setAptId(1);
        newComplaint.setUserId(1);
        newComplaint.setContent("TEST");
        return newComplaint;
    }
}
