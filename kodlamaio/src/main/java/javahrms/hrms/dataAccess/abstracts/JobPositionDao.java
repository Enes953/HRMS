package javahrms.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import javahrms.hrms.entities.concretes.JobPosition;

public interface JobPositionDao extends JpaRepository<JobPosition, Integer> {
	
	
	boolean existsJobPositionByPositionName(String positionName);
	
}
