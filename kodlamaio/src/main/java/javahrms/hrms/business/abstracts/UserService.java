package javahrms.hrms.business.abstracts;

import java.util.List;

import javahrms.hrms.core.utilites.results.DataResult;
import javahrms.hrms.core.utilites.results.Result;
import javahrms.hrms.entities.concretes.User;

public interface UserService {
	
	DataResult<List<User>>getAll();
	
	DataResult<User> get(int id);
	
	Result add(User user);
	
	Result delete(int id);
	
	Result update(User user);
	
	boolean existsUserByEmail(String email);
	
	

}
