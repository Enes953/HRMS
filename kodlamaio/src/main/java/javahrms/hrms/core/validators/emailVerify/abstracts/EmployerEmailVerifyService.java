package javahrms.hrms.core.validators.emailVerify.abstracts;

public  abstract class EmployerEmailVerifyService implements EmailVerifyService {
	
	@Override 
	public boolean hasVerifyEmail(String email) {
		return true;
	}

}
