package javahrms.hrms.business.abstracts;

import java.util.List;

import javahrms.hrms.core.utilites.results.DataResult;
import javahrms.hrms.core.utilites.results.Result;
import javahrms.hrms.entities.concretes.Employer;

public interface EmployerService {
	
	DataResult<List<Employer>>getAll();
	
	DataResult<Employer> get(int id);
	
	Result add(Employer employer);
	
	Result delete(int id);
	
	Result update(Employer employer);
	
	boolean existsEmployerByEmail(String email);
	
	boolean hasEmptyField(Employer employer);

}
