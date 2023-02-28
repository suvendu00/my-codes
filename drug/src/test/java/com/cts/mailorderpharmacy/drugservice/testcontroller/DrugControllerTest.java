package com.cts.mailorderpharmacy.drugservice.testcontroller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import com.cts.mailorderpharmacy.drugservice.controller.DrugController;
import com.cts.mailorderpharmacy.drugservice.entity.Drug;
import com.cts.mailorderpharmacy.drugservice.entity.DrugLocation;
import com.cts.mailorderpharmacy.drugservice.entity.Stock;
import com.cts.mailorderpharmacy.drugservice.service.DrugserviceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc
@SpringBootTest
public class DrugControllerTest {

	@Autowired
	DrugController controller;
	
	@MockBean
	DrugserviceImpl service;
	
	@Autowired
	WebApplicationContext context;
	
	@Autowired
	private MockMvc mockMvc;
	
	String date = "date";
	List<DrugLocation> list = new ArrayList<DrugLocation>();
	Drug drug = new Drug("PR1","paracetamol","suvendu",date, date, 10, 120, list);
	
	@Test
	@WithMockUser(username="suv", roles= {"USER","ADMIN"})
	void testgetAllDrugs()throws Exception {
		mockMvc.perform(get("/drugservice/getAllDrugs"))
		.andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username="suv", roles= {"USER","ADMIN"})
	void testGetDrugById() throws Exception {
		list.add(new DrugLocation("1", "bangalore", 10, null));	
		ObjectMapper objectMapper = new ObjectMapper();
		String expectedValue = objectMapper.writeValueAsString(drug);
		when(service.getDrugById( "PR1")).thenReturn(Optional.of(drug));
		MvcResult result = mockMvc.perform(get("/drugservice/searchDrugsById/PR1"))
				.andReturn();
		String actualValue = result.getResponse().getContentAsString();
		assertEquals(actualValue,expectedValue);

		
	}
	
	@Test
	@WithMockUser(username="suv", roles= {"USER","ADMIN"})
	void testGetDrugByName()throws Exception {
		list.add(new DrugLocation("1", "bangalore", 10, null));
		ObjectMapper objectMapper = new ObjectMapper();
		String expectedValue = objectMapper.writeValueAsString(drug);
		when(controller.getDrugsByName("paracetamol")).thenReturn(Optional.of(drug));
		MvcResult result = mockMvc.perform(get("/drugservice/searchDrugsByName/paracetamol"))
				.andReturn();
		String actualValue = result.getResponse().getContentAsString();
		assertEquals(expectedValue,actualValue);
	}
	
	@Test 
	@WithMockUser(username="suv", roles= {"USER","ADMIN"})
	void testGetDrugsByIdAndLocation()throws Exception {
		list.add(new DrugLocation("1", "bangalore", 10, null));
		Stock stock = new Stock("PR1","paracetamol", date,100);
		ObjectMapper objectMapper = new ObjectMapper();
		String expectedValue = objectMapper.writeValueAsString(stock);
		when(controller.getDrugsByIdAndLocation("PR1", "bangalore")).thenReturn(stock);
		MvcResult result = mockMvc.perform(get("/drugservice/searchDrugsByIdAndLocation/PR1/bangalore"))
				.andReturn();
		String actualValue = result.getResponse().getContentAsString();
		assertEquals(expectedValue,actualValue);

	}
	
	
	@Test
	@WithMockUser(username="suv", roles= {"USER","ADMIN"})
	void testUpdateQuantity() throws Exception{
		list.add(new DrugLocation("1", "bangalore", 10, null));
		String expectedValue = "Refill done Successfully";
		when(service.updateQuantity("Dolo", "Chennai", 10)).thenReturn(expectedValue);
		when(controller.updateQuantity("Dolo","Chennai",10)).thenReturn(expectedValue);
		MvcResult result = mockMvc.perform(get("/drugservice/updateDispatchableDrugStock/Dolo/Chennai/10"))
				.andReturn();
		String actualValue = result.getResponse().getContentAsString();
		assertEquals(expectedValue, actualValue);
		
	}
	
}
