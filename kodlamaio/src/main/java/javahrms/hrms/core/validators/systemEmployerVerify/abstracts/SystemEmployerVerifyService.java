package javahrms.hrms.core.validators.systemEmployerVerify.abstracts;

import javahrms.hrms.entities.concretes.Employer;

public abstract class SystemEmployerVerifyService implements EmployerVerifyService {

	@Override 
	public boolean hasVerifyBySystemEmployee(Employer employer) {
		return true;
	}
	
}
