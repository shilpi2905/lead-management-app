package org.codejudge.sb.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.codejudge.sb.exception.model.CommonException;
import org.codejudge.sb.h2.entity.LeadEntity;
import org.codejudge.sb.h2.repository.LeadRepository;
import org.codejudge.sb.input.LeadInput;
import org.codejudge.sb.input.LocationType;
import org.codejudge.sb.input.MarkLeadInput;
import org.codejudge.sb.output.GenerateLeadOutput;
import org.codejudge.sb.output.LeadStatusOutput;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LeadManagementServiceImplTest {

	@InjectMocks
	private LeadManagementServiceImpl service;
	
	@Mock
	private LeadRepository leadRepository;
	
	ObjectMapper mapper;
	
	LeadInput input =null;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mapper = new ObjectMapper();
		input = new LeadInput();
		input.setFirstName("Shilpi");
		input.setLastName("Gupta");
		input.setMobile(1234567890L);
		input.setEmail("abc@xyz.com");
		input.setLocationType(LocationType.CITY);
		input.setLocation("ABC XYZ");
	}
	
	@Test
	public void generateLeadTest() {
		LeadEntity entity = new LeadEntity();
		entity.setStatus("Created");
		when(leadRepository.save(any())).thenReturn(entity);
		GenerateLeadOutput output = service.generateLead(input);
		assertNotNull(output);
	}
	
	@Test(expected = CommonException.class)
	public void generateLeadTestFail() {
		when(leadRepository.save(any())).thenThrow(DataIntegrityViolationException.class);
		GenerateLeadOutput output = service.generateLead(input);
		assertNotNull(output);
	}
	
	@Test
	public void retrieveLeadTest() {
		LeadEntity entity = new LeadEntity();
		entity.setStatus("Created");
		when(leadRepository.findById(any())).thenReturn(Optional.of(entity));
		GenerateLeadOutput output = service.retrieveLead(1);
		assertNotNull(output);
	}
	
	@SuppressWarnings("static-access")
	@Test(expected = CommonException.class)
	public void retrieveLeadTestFail() {
		when(leadRepository.findById(any())).thenReturn(Optional.of(new LeadEntity()).empty());
		GenerateLeadOutput output = service.retrieveLead(1);
		assertNotNull(output);
	}
	
	@Test
	public void updateLeadTest() {
		LeadEntity entity = new LeadEntity();
		entity.setStatus("Created");
		entity.setLeadId(1);
		when(leadRepository.findById(any())).thenReturn(Optional.of(entity));
		when(leadRepository.save(any())).thenReturn(entity);
		LeadStatusOutput output = service.updateLead(1, input);
		assertNotNull(output);
	}
	
	@Test
	public void deleteLeadTest() {
		LeadEntity entity = new LeadEntity();
		entity.setStatus("Created");
		entity.setLeadId(1);
		when(leadRepository.findById(any())).thenReturn(Optional.of(entity));
		LeadStatusOutput output = service.deleteLead(1);
		assertNotNull(output);
	}
	
	@Test
	public void markLeadTest() {
		LeadEntity entity = new LeadEntity();
		entity.setStatus("Created");
		entity.setLeadId(1);
		when(leadRepository.findById(any())).thenReturn(Optional.of(entity));
		when(leadRepository.save(any())).thenReturn(entity);
		MarkLeadInput markLeadInput = new MarkLeadInput();
		markLeadInput.setCommunication("BVVD");
		LeadStatusOutput output = service.markLead(1, markLeadInput);
		assertNotNull(output);
	}
}
