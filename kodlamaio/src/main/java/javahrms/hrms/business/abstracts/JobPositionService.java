package javahrms.hrms.business.abstracts;

import java.util.List;

import javahrms.hrms.core.utilites.results.DataResult;
import javahrms.hrms.core.utilites.results.Result;
import javahrms.hrms.entities.concretes.JobPosition;

public interface JobPositionService {
	
	DataResult<List<JobPosition>>getAll();
	
	DataResult<JobPosition> get(int id);
	
	Result add(JobPosition jobPosition);
		
	Result delete(int id);
	
	Result update(JobPosition jobPosition);
	
	boolean existsJobPositionByPositionName(String positionName);

}
