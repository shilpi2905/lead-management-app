package org.codejudge.sb.service.impl;

import org.codejudge.sb.exception.model.CommonException;
import org.codejudge.sb.exception.model.Error;
import org.codejudge.sb.h2.entity.LeadEntity;
import org.codejudge.sb.h2.repository.LeadRepository;
import org.codejudge.sb.input.LeadInput;
import org.codejudge.sb.input.MarkLeadInput;
import org.codejudge.sb.output.GenerateLeadOutput;
import org.codejudge.sb.output.LeadStatusOutput;
import org.codejudge.sb.output.StatusCode;
import org.codejudge.sb.service.LeadManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LeadManagementServiceImpl implements LeadManagementService {
	
	@Autowired
	private LeadRepository leadRepository;
	
	@Override
	public GenerateLeadOutput generateLead(LeadInput leadInput) {
		LeadEntity entity = new LeadEntity();
		createLeadEntity(leadInput, entity);
		entity.setStatus(StatusCode.Created.toString());
		try {
			LeadEntity lead = leadRepository.save(entity);
			log.info("output:: {}", leadRepository.findById(lead.getLeadId()));
			return generateLeadOutput(lead, true);
		} catch (DataIntegrityViolationException ex) {
			throw new CommonException(
					new Error("failure", "Mobile or email provided for this lead is already registered"));
		}
	}

	private void createLeadEntity(LeadInput leadInput, LeadEntity entity) {
		entity.setFirstName(leadInput.getFirstName());
		entity.setLastName(leadInput.getLastName());
		entity.setMobile(leadInput.getMobile());
		entity.setEmail(leadInput.getEmail());
		entity.setLocationType(leadInput.getLocationType().toString());
		entity.setLocationString(leadInput.getLocation());
	}
	
	private GenerateLeadOutput generateLeadOutput(LeadEntity leadEntity, boolean isIdRequired) {
		GenerateLeadOutput output = new GenerateLeadOutput();
		if(isIdRequired) {
			output.setLeadId(leadEntity.getLeadId());
		}
		output.setFirstName(leadEntity.getFirstName());
		output.setLastName(leadEntity.getLastName());
		output.setMobile(leadEntity.getMobile());
		output.setEmail(leadEntity.getEmail());
		output.setLocationType(leadEntity.getLocationType());
		output.setLocation(leadEntity.getLocationString());
		output.setStatus(StatusCode.valueOf(leadEntity.getStatus()));
		return output;
		
	}
	
	@Override
	public GenerateLeadOutput retrieveLead(Integer leadId) {
		checkIfLeadExist(leadId);
		LeadEntity leadEntity = leadRepository.findById(leadId).get();
		return generateLeadOutput(leadEntity, false);
	}

	@Override
	public LeadStatusOutput updateLead(Integer leadId, LeadInput leadInput) {
		checkIfLeadExist(leadId);
		LeadEntity leadEntity = leadRepository.findById(leadId).get();
		createLeadEntity(leadInput, leadEntity);
		LeadEntity lead = leadRepository.save(leadEntity);
		log.info("updated lead:: {}", leadRepository.findById(lead.getLeadId()));
		LeadStatusOutput output = new LeadStatusOutput();
		output.setStatus("success");
		return output;
	}

	private void checkIfLeadExist(Integer leadId) {
		if(!leadRepository.findById(leadId).isPresent()) {
			throw new CommonException(
					new Error("failure", "Error: Lead with id: "+leadId+" does not exist"));
		}
	}
	
	@Override
	public LeadStatusOutput deleteLead(Integer leadId) {
		checkIfLeadExist(leadId);
		leadRepository.deleteById(leadId);
		LeadStatusOutput output = new LeadStatusOutput();
		output.setStatus("success");
		return output;
	}

	@Override
	public LeadStatusOutput markLead(Integer leadId, MarkLeadInput markLeadInput) {
		checkIfLeadExist(leadId);
		LeadEntity leadEntity = leadRepository.findById(leadId).get();
		leadEntity.setStatus(StatusCode.Contacted.toString());
		LeadStatusOutput output = new LeadStatusOutput();
		LeadEntity lead = leadRepository.save(leadEntity);
		log.info("marked lead:: {}", leadRepository.findById(lead.getLeadId()));
		output.setStatus("success");
		output.setCommunication(markLeadInput.getCommunication());
		return output;
	}

}
