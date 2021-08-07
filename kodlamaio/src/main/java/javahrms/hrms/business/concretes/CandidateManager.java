package javahrms.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javahrms.hrms.business.abstracts.CandidateService;
import javahrms.hrms.core.adapters.abstracts.CandidateValidationService;
import javahrms.hrms.core.utilites.results.DataResult;
import javahrms.hrms.core.utilites.results.ErrorDataResult;
import javahrms.hrms.core.utilites.results.ErrorResult;
import javahrms.hrms.core.utilites.results.Result;
import javahrms.hrms.core.utilites.results.SuccessDataResult;
import javahrms.hrms.core.utilites.results.SuccessResult;
import javahrms.hrms.core.validators.emailRegex.abstracts.CandidateEmailRegexValidatorService;
import javahrms.hrms.core.validators.emailVerify.abstracts.CandidateEmailVerifyService;
import javahrms.hrms.dataAccess.abstracts.CandidateDao;
import javahrms.hrms.entities.concretes.Candidate;

@Service
public class CandidateManager implements CandidateService {
	
	
	private CandidateDao candidateDao;
	private CandidateEmailRegexValidatorService candidateEmailRegexValidatorService;
	private CandidateValidationService candidateValidationService;
	private CandidateEmailVerifyService CandidateEmailVerifyService;


	@Autowired
	public CandidateManager(CandidateDao candidateDao,
			CandidateEmailRegexValidatorService candidateEmailRegexValidatorService,
			CandidateValidationService candidateValidationService,
			CandidateEmailVerifyService candidateEmailVerifyService) {
		
		this.candidateDao = candidateDao;
		this.candidateEmailRegexValidatorService = candidateEmailRegexValidatorService;
		this.candidateValidationService = candidateValidationService;
		this.CandidateEmailVerifyService = candidateEmailVerifyService;
	}

	@Override
	public DataResult<List<Candidate>> getAll() {
		
		return new SuccessDataResult<List<Candidate>>
		("Adaylar başarıyla listelendi.",this.candidateDao.findAll());
	}

	@Override
	public DataResult<Candidate> get(int id) {
		if(this.candidateDao.findById(id).orElse(null)!=null) {
			return new SuccessDataResult<Candidate>
			("İş Arayan Bulundu.",this.candidateDao.findById(id).get());
			
		}else {
			return new ErrorDataResult<Candidate>("İş arayan mevcut değil.");
		}
	}

	@Override
	public Result add(Candidate candidate) {
		if(this.hasEmptyField(candidate)) {
			return new ErrorResult("Tüm alanlar zorunludur");
		} else if(!this.candidateValidationService.isRealPerson(candidate)) {
			return new ErrorResult("İş arayan gerçek değil");
		} else if(this.existsCandidateByIdentityNumber(candidate.getIdentityNumber())) {
			return new ErrorResult("Kimlik Numarası ile eşleşen iş arayan mevcuttur");
		} else if(!this.candidateEmailRegexValidatorService.isValidEmail(candidate.getEmail())) {
			return new ErrorResult("Bu Email geçerli değil");		
		} else if(this.existsCandidateByEmail(candidate.getEmail())) {
			return new ErrorResult("Bu Email ile eşleşen iş arayan mevcuttur");
		} else if(!this.CandidateEmailVerifyService.hasVerifyEmail(candidate.getEmail())) {
			return new ErrorResult("Email doğrulanamadı");
		} else {this.candidateDao.save(candidate);
			return new SuccessResult("iş arayan başarıyla kaydedildi.");
			
		}
	}

	@Override
	public Result delete(int id) {
		this.candidateDao.deleteById(id);
		return new SuccessResult("İş arayan başarıyla silindi");
	}

	@Override
	public Result update(Candidate candidate) {
		this.candidateDao.save(candidate);
			
		return new SuccessResult("İş arayan başarıyla güncellendi");
	}

	@Override
	public boolean existsCandidateByIdentityNumber(String identityNumber) {
		
		return this.candidateDao.existsCandidateByIdentityNumber(identityNumber);
	}

	@Override
	public boolean existsCandidateByEmail(String email) {
		
		return this.candidateDao.existsCandidateByEmail(email);
	}

	@Override
	public boolean hasEmptyField(Candidate candidate) {
		if(candidate.getFirstName().isEmpty()||candidate.getLastName().isEmpty()
				||candidate.getEmail().isEmpty()||candidate.getIdentityNumber().isEmpty()
				||candidate.getPassword().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
}