package javahrms.hrms.core.adapters.abstracts;

import javahrms.hrms.entities.concretes.Candidate;

public interface CandidateValidationService {
	
	boolean isRealPerson(Candidate candidate);

}
