package org.codejudge.sb.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.codejudge.sb.exception.handler.LeadManagementExceptionHandler;
import org.codejudge.sb.input.LeadInput;
import org.codejudge.sb.input.MarkLeadInput;
import org.codejudge.sb.output.GenerateLeadOutput;
import org.codejudge.sb.output.LeadStatusOutput;
import org.codejudge.sb.service.LeadManagementService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(LeadManagementController.class)
public class LeadManagementControllerTest {

	@InjectMocks
	private LeadManagementController controller;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private LeadManagementService leadManagementService;
	
	@Mock
	private LeadManagementExceptionHandler exceptionHandler;
	
	ObjectMapper mapper;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mapper = new ObjectMapper();
	}

	@Test
	public void generateLeadTest() throws Exception {
		String leadInput = "{\r\n" + 
				"    \"first_name\": \"Shilpi\",\r\n" + 
				"    \"last_name\": \"Gupta\",\r\n" + 
				"    \"mobile\": 9234567899,   \r\n" + 
				"    \"email\": \"sg1hj0j@gmail.com\",  \r\n" + 
				"    \"location_type\": \"Country\",\r\n" + 
				"    \"location_string\": \"ghjghjghj\"\r\n" + 
				"} ";
		LeadInput input = mapper.readValue(leadInput, LeadInput.class);
		when(leadManagementService.generateLead(any())).thenReturn(new GenerateLeadOutput());
		assertEquals(HttpStatus.CREATED, controller.generateLead(input).getStatusCode());
	}
	
	@Test
	public void generateLeadTestInvalidLocationType() throws Exception {
		String leadInput = "{\r\n" + 
				"    \"first_name\": \"Shilpi\",\r\n" + 
				"    \"last_name\": \"Gupta\",\r\n" + 
				"    \"mobile\": 9234567899,   \r\n" + 
				"    \"email\": \"sg1hj0j@gmail.com\",  \r\n" + 
				"    \"location_type\": \"ABC\",\r\n" + 
				"    \"location_string\": \"ghjghjghj\"\r\n" + 
				"} ";
		LeadInput input = mapper.readValue(leadInput, LeadInput.class);
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/api/leads/").contentType(MediaType.APPLICATION_JSON_UTF8)
						.accept(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsString(input)))
				.andReturn();
		assertNotNull(result);
		int status = result.getResponse().getStatus();
		assertEquals(HttpStatus.BAD_REQUEST.value(), status);
	}
	
	@Test
	public void generateLeadTestInvalidMobileNumber() throws Exception {
		String leadInput = "{\r\n" + 
				"    \"first_name\": \"Shilpi\",\r\n" + 
				"    \"last_name\": \"Gupta\",\r\n" + 
				"    \"mobile\": 923456789,   \r\n" + 
				"    \"email\": \"sg1hj0j@gmail.com\",  \r\n" + 
				"    \"location_type\": \"Country\",\r\n" + 
				"    \"location_string\": \"ghjghjghj\"\r\n" + 
				"} ";
		LeadInput input = mapper.readValue(leadInput, LeadInput.class);
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/api/leads/").contentType(MediaType.APPLICATION_JSON_UTF8)
						.accept(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsString(input)))
				.andReturn();
		assertNotNull(result);
		int status = result.getResponse().getStatus();
		assertEquals(HttpStatus.BAD_REQUEST.value(), status);
	}
	
	@Test
	public void fetchLeadTest() {
		when(leadManagementService.retrieveLead(any())).thenReturn(new GenerateLeadOutput());
		assertEquals(HttpStatus.OK, controller.fetchLead(1).getStatusCode());
	}
	
	@Test
	public void fetchLeadTestFail() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get("/api/leads/").accept(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();
		int status = result.getResponse().getStatus();
		assertEquals(HttpStatus.NOT_FOUND.value(), status);
	}
	
	@Test
	public void updateLeadTest() throws Exception {
		String leadInput = "{\r\n" + 
				"    \"first_name\": \"Shilpi\",\r\n" + 
				"    \"last_name\": \"Gupta\",\r\n" + 
				"    \"mobile\": 9234567899,   \r\n" + 
				"    \"email\": \"sg1hj0j@gmail.com\",  \r\n" + 
				"    \"location_type\": \"ABC\",\r\n" + 
				"    \"location_string\": \"ghjghjghj\"\r\n" + 
				"} ";
		LeadInput input = mapper.readValue(leadInput, LeadInput.class);
		when(leadManagementService.updateLead(any(),any())).thenReturn(new LeadStatusOutput());
		assertEquals(HttpStatus.ACCEPTED, controller.updateLead(1, input).getStatusCode());
	}
	
	@Test
	public void deleteLeadTest() {
		when(leadManagementService.deleteLead(any())).thenReturn(new LeadStatusOutput());
		assertEquals(HttpStatus.OK, controller.deleteLead(1).getStatusCode());
	}
	
	@Test
	public void markLeadTest() throws Exception {
		String markLeadInput = "{\r\n" + 
				"	\"communication\": \"BVV\"\r\n" + 
				"}";
		MarkLeadInput input = mapper.readValue(markLeadInput, MarkLeadInput.class);
		when(leadManagementService.markLead(any(), any())).thenReturn(new LeadStatusOutput());
		assertEquals(HttpStatus.ACCEPTED, controller.markLead(1, input).getStatusCode());
	}
	
	@Test
	public void markLeadTestFail() throws Exception {
		String markLeadInput = "{\r\n" + 
				"	\"communication\": null\r\n" + 
				"}";
		MarkLeadInput input = mapper.readValue(markLeadInput, MarkLeadInput.class);
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.put("/api/mark_lead/1").contentType(MediaType.APPLICATION_JSON_UTF8)
						.accept(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsString(input)))
				.andReturn();
		assertNotNull(result);
		int status = result.getResponse().getStatus();
		assertEquals(HttpStatus.BAD_REQUEST.value(), status);
	}
}
