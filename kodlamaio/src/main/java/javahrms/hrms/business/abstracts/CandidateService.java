package javahrms.hrms.business.abstracts;

import java.util.List;

import javahrms.hrms.core.utilites.results.DataResult;
import javahrms.hrms.core.utilites.results.Result;
import javahrms.hrms.entities.concretes.Candidate;

public interface CandidateService {
	
	DataResult<List<Candidate>> getAll();
	
	DataResult<Candidate> get(int id);
	
	Result add(Candidate candidate);
	
	Result delete(int id);
	
	Result update(Candidate candidate);

	boolean existsCandidateByIdentityNumber(String identityNumber);
	
	boolean existsCandidateByEmail(String email);
	
	boolean hasEmptyField(Candidate candidate);
}
