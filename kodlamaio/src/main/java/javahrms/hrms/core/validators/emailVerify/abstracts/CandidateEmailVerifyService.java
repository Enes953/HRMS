package javahrms.hrms.core.validators.emailVerify.abstracts;

public abstract class CandidateEmailVerifyService implements EmailVerifyService {

	@Override
	public boolean hasVerifyEmail(String email) {
		
		return true;
}

}	