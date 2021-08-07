package javahrms.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javahrms.hrms.business.abstracts.UserService;
import javahrms.hrms.core.utilites.results.DataResult;
import javahrms.hrms.core.utilites.results.ErrorDataResult;
import javahrms.hrms.core.utilites.results.Result;
import javahrms.hrms.core.utilites.results.SuccessDataResult;
import javahrms.hrms.core.utilites.results.SuccessResult;
import javahrms.hrms.dataAccess.abstracts.UserDao;
import javahrms.hrms.entities.concretes.User;

@Service
public class UserManager implements UserService {
	
	
	private UserDao userDao;

	@Autowired
	public UserManager(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	public DataResult<List<User>> getAll() {
		return new SuccessDataResult<List<User>>
		("Kullanıcılar listelendi",this.userDao.findAll());
	}

	@Override
	public DataResult<User> get(int id) {
		if(this.userDao.findById(id).orElse(null)!=null) {
			return new SuccessDataResult<User>
			("Kullanıcı başarıyla bulundu",this.userDao.findById(id).get());
		} else {
			return new ErrorDataResult<User>("Belirtilen Kullanıcı mevcut değil");
		}
	}

	@Override
	public Result add(User user) {
		this.userDao.save(user);
		return new SuccessResult("Kullanıcı başarıyla eklendi");
	}

	@Override
	public Result delete(int id) {
		this.userDao.deleteById(id);
		return new SuccessResult("Kullanıcı başarıyla silindi");
	}

	@Override
	public Result update(User user) {
		this.userDao.save(user);
		return new SuccessResult("Kullanıcı başarıyla güncellendi");
	}

	@Override
	public boolean existsUserByEmail(String email) {
		return this.userDao.existsUserByEmail(email);
	}

	
	
}
