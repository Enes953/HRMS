package javahrms.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javahrms.hrms.business.abstracts.JobPositionService;
import javahrms.hrms.core.utilites.results.DataResult;
import javahrms.hrms.core.utilites.results.ErrorDataResult;
import javahrms.hrms.core.utilites.results.ErrorResult;
import javahrms.hrms.core.utilites.results.Result;
import javahrms.hrms.core.utilites.results.SuccessDataResult;
import javahrms.hrms.core.utilites.results.SuccessResult;
import javahrms.hrms.dataAccess.abstracts.JobPositionDao;
import javahrms.hrms.entities.concretes.JobPosition;

@Service
public class JobPositionManager implements JobPositionService {
	
	
	private JobPositionDao jobPositionDao;

	@Autowired
	public JobPositionManager(JobPositionDao jobPositionDao) {
		this.jobPositionDao = jobPositionDao;
	}

	@Override
	public DataResult<List<JobPosition>> getAll() {
		
		return new SuccessDataResult<List<JobPosition>>
		("Pozisyon başarıyla listelendi.",this.jobPositionDao.findAll());
	}

	@Override
	public DataResult<JobPosition> get(int id) {
		if (this.jobPositionDao.findById(id).orElse(null) != null ) {
			
			return new SuccessDataResult<JobPosition>
			("İş pozisyonu başarıyla bulundu.",this.jobPositionDao.findById(id).get());
		}else {
			return new ErrorDataResult<JobPosition>("İş pozisyonu mevcut değil");
		}
	}

	@Override
	public Result add(JobPosition jobPosition) {
		if(this.jobPositionDao.existsJobPositionByPositionName(jobPosition.getPositionName())) {
			return new ErrorResult("Bu isimde bir iş pozisyonu yoktur.");
		}else {
			this.jobPositionDao.save(jobPosition);
			return new SuccessResult("İş pozisyonu başarıyla eklendi.");
		}
	}

	@Override
	public Result delete(int id) {
		this.jobPositionDao.deleteById(id);
		return new SuccessResult("İş pozisyonu başrıyla silindi");
	}

	@Override
	public Result update(JobPosition jobPosition) {
		this.jobPositionDao.save(jobPosition);
		return new SuccessResult("İş pozisyonu başarıyla güncellendi.");
	}

	@Override
	public boolean existsJobPositionByPositionName(String positionName) {
		
		return this.jobPositionDao.existsJobPositionByPositionName(positionName);
	}


}
