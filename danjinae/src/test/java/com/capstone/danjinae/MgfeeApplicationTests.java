package com.capstone.danjinae;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.capstone.danjinae.MgFee.DTO.NewMgFeeRequest;
import com.capstone.danjinae.MgFee.controller.MgFeeController;
import com.capstone.danjinae.MgFee.repository.MgFeeRepository;
import com.capstone.danjinae.MgFee.service.MgFeeService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
class MgfeeApplicationTests {
	@InjectMocks
	private MgFeeController mgFeeController;

	@InjectMocks
	private MgFeeService mgFeeService;

	@Mock
	private MgFeeRepository mgFeeRepository;

	private MockMvc mockMvc;

	@BeforeEach
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(mgFeeController).build();
	}

	@Test
	@DisplayName("관리비 청구 테스트")
	void NewMgFeeTest() {
		final NewMgFeeRequest newMgFee = new NewMgFeeRequest();
		newMgFee.setAptId(1);
		newMgFee.setCatId(0);
		newMgFee.setFee(1000);
		newMgFee.setUserId(0);

		Boolean result = mgFeeController.addNewMgFee(newMgFee);
	}
}
