package javahrms.hrms.core.adapters.concretes;

import org.springframework.stereotype.Service;

import javahrms.hrms.core.adapters.abstracts.CandidateValidationService;
import javahrms.hrms.entities.concretes.Candidate;
import javahrms.hrms.mernis.FakeMernis;

@Service
public class MernisValidationAdapter implements CandidateValidationService {

	@Override
	public boolean isRealPerson(Candidate candidate) {
	
		return new FakeMernis().isRealPerson(candidate.getFirstName(), candidate.getLastName(),
				Long.parseLong(candidate.getIdentityNumber()));
	}

}
