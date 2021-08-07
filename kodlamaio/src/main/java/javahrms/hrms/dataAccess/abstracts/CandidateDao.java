package javahrms.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javahrms.hrms.entities.concretes.Candidate;

@Repository
public interface CandidateDao extends JpaRepository<Candidate,Integer> {

	boolean existsCandidateByIdentityNumber(String identityNumber);
	
	boolean existsCandidateByEmail(String email);

}
