package org.codejudge.sb.controller;

import javax.validation.Valid;

import org.codejudge.sb.input.LeadInput;
import org.codejudge.sb.input.MarkLeadInput;
import org.codejudge.sb.output.GenerateLeadOutput;
import org.codejudge.sb.output.LeadStatusOutput;
import org.codejudge.sb.service.LeadManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class LeadManagementController {

	@Autowired
	private LeadManagementService leadManagementService;
	
	@ApiOperation("This is generate lead api")
    @RequestMapping(value = "/api/leads/", produces = { "application/json" }, consumes = { "application/json" }, method = RequestMethod.POST)
	public ResponseEntity<GenerateLeadOutput> generateLead(@ApiParam(value = "") @Valid @RequestBody LeadInput leadInputBody) {
		log.info("leadInputBody:: {}", leadInputBody);
		return new ResponseEntity<GenerateLeadOutput>(leadManagementService.generateLead(leadInputBody), HttpStatus.CREATED);
	}
	
	@ApiOperation("This is fetch lead api")
    @RequestMapping(value = "/api/leads/{lead_id}", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<GenerateLeadOutput> fetchLead(@PathVariable(name = "lead_id", required = true) Integer leadId) {
		log.info("request params:: leadId {}", leadId);
		return new ResponseEntity<GenerateLeadOutput>(leadManagementService.retrieveLead(leadId), HttpStatus.OK);
	}
	
	@ApiOperation("This is update lead api")
    @RequestMapping(value = "/api/leads/{lead_id}", produces = { "application/json" }, consumes = { "application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<LeadStatusOutput> updateLead(@PathVariable(name = "lead_id", required = true) Integer leadId, @Valid @RequestBody LeadInput leadInputBody) {
		log.info("request params:: leadId {}", leadId);
		log.info("request body:: {}", leadInputBody);
		return new ResponseEntity<LeadStatusOutput>(leadManagementService.updateLead(leadId, leadInputBody), HttpStatus.ACCEPTED);
	}
	
	@ApiOperation("This is delete lead api")
    @RequestMapping(value = "/api/leads/{lead_id}", produces = { "application/json" }, method = RequestMethod.DELETE)
	public ResponseEntity<LeadStatusOutput> deleteLead(@PathVariable(name = "lead_id", required = true) Integer leadId) {
		log.info("request params:: leadId {}", leadId);
		return new ResponseEntity<LeadStatusOutput>(leadManagementService.deleteLead(leadId), HttpStatus.OK);
	}
	
	@ApiOperation("This is mark lead api")
    @RequestMapping(value = "/api/mark_lead/{lead_id}", produces = { "application/json" }, consumes = { "application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<LeadStatusOutput> markLead(@PathVariable(name = "lead_id", required = true) Integer leadId, @Valid @RequestBody MarkLeadInput markLeadInput) {
		log.info("request params:: leadId {}", leadId);
		log.info("request body:: {}", markLeadInput);
		return new ResponseEntity<LeadStatusOutput>(leadManagementService.markLead(leadId, markLeadInput), HttpStatus.ACCEPTED);
	}
}
