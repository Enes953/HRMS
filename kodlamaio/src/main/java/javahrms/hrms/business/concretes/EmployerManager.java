package javahrms.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javahrms.hrms.business.abstracts.EmployerService;
import javahrms.hrms.core.utilites.results.DataResult;
import javahrms.hrms.core.utilites.results.ErrorDataResult;
import javahrms.hrms.core.utilites.results.ErrorResult;
import javahrms.hrms.core.utilites.results.Result;
import javahrms.hrms.core.utilites.results.SuccessDataResult;
import javahrms.hrms.core.utilites.results.SuccessResult;
import javahrms.hrms.core.validators.emailRegex.abstracts.EmployerEmailRegexValidatorService;
import javahrms.hrms.core.validators.emailVerify.abstracts.EmployerEmailVerifyService;
import javahrms.hrms.core.validators.systemEmployerVerify.abstracts.SystemEmployerVerifyService;
import javahrms.hrms.dataAccess.abstracts.EmployerDao;
import javahrms.hrms.entities.concretes.Employer;

@Service
public class EmployerManager implements EmployerService {

	
	private EmployerDao employerDao;
	private EmployerEmailRegexValidatorService employerEmailRegexValidatorService;
	private EmployerEmailVerifyService employerEmailVerifyService;
	private SystemEmployerVerifyService systemEmployerVerifyService;
	
	@Autowired
	public EmployerManager(EmployerDao employerDao,
			EmployerEmailRegexValidatorService employerEmailRegexValidatorService,
			EmployerEmailVerifyService employerEmailVerifyService,
			SystemEmployerVerifyService systemEmployerVerifyService) {
		
		this.employerDao = employerDao;
		this.employerEmailRegexValidatorService = employerEmailRegexValidatorService;
		this.employerEmailVerifyService = employerEmailVerifyService;
		this.systemEmployerVerifyService = systemEmployerVerifyService;
	}

	@Override
	public DataResult<List<Employer>> getAll() {
		
		return new SuccessDataResult<List<Employer>>
		("İş verenler listelendi.",this.employerDao.findAll());
	}

	@Override
	public DataResult<Employer> get(int id) {
		if(this.employerDao.findById(id).orElse(null) !=null) {
			return new SuccessDataResult<Employer>
			("İş pozisyonu başarıyla bulundu.",this.employerDao.findById(id).get());
		} else {
			return new ErrorDataResult<Employer>("İş pozisyonu mevcut değil.");
		}
	}

	@Override
	public Result add(Employer employer) {
		if(this.hasEmptyField(employer)) {
			return new ErrorResult("Tüm alanlar zorunludur");
		} else if(!this.employerEmailRegexValidatorService.isValidEmail
				(employer.getEmail(), employer.getWebAddress())) {
			return new ErrorResult("Email, web site ile aynı domain'e sahip olmalıdır.");
		} else if(this.existsEmployerByEmail(employer.getEmail())) {
			return new ErrorResult("email'e sahip bir işveren kaydı mevcuttur.");
			
		}else if(!this.employerEmailVerifyService.hasVerifyEmail(employer.getEmail())) {
			return new ErrorResult("Email doğrulanamadı");
		}else if(!this.systemEmployerVerifyService.hasVerifyBySystemEmployee(employer)) {
			return new ErrorResult("Sistem tarafından Doğrulanamadı");
		}else {
			this.employerDao.save(employer);
			return new SuccessResult("İşveren başarıyla kaydedildi.");
		}
	}

	@Override
	public Result delete(int id) {
		this.employerDao.deleteById(id);
		return new SuccessResult("İşveren başarıyla silindi.");
	}

	@Override
	public Result update(Employer employer) {
		this.employerDao.save(employer);
		return new SuccessResult("İşveren başarıyla güncellendi.");
	}

	@Override
	public boolean existsEmployerByEmail(String email) {
		return this.employerDao.existsEmployerByEmail(email);
	}

	@Override
	public boolean hasEmptyField(Employer employer) {
		if(employer.getCompanyName().isEmpty()||employer.getWebAddress().isEmpty()
				||employer.getEmail().isEmpty()||employer.getPassword().isEmpty()
				||employer.getPhoneNumber().isEmpty()) {
			return true;	
		}else {
			return false;
		}
	}
	
	
	
	

}
