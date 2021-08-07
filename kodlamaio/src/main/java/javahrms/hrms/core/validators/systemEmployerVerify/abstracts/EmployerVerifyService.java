package javahrms.hrms.core.validators.systemEmployerVerify.abstracts;

import javahrms.hrms.entities.concretes.Employer;

public interface EmployerVerifyService {
	
	boolean hasVerifyBySystemEmployee(Employer employer);

}
