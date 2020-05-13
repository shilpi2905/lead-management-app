package org.codejudge.sb.service;

import org.codejudge.sb.input.LeadInput;
import org.codejudge.sb.input.MarkLeadInput;
import org.codejudge.sb.output.GenerateLeadOutput;
import org.codejudge.sb.output.LeadStatusOutput;

public interface LeadManagementService {

	GenerateLeadOutput generateLead(LeadInput leadInput);
	GenerateLeadOutput retrieveLead(Integer leadId);
	LeadStatusOutput updateLead(Integer leadId, LeadInput leadInput);
	LeadStatusOutput deleteLead(Integer leadId);
	LeadStatusOutput markLead(Integer leadId, MarkLeadInput markLeadInput);
}
