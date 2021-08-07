package javahrms.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javahrms.hrms.business.abstracts.UserService;
import javahrms.hrms.core.utilites.results.DataResult;
import javahrms.hrms.core.utilites.results.Result;
import javahrms.hrms.entities.concretes.User;

@RestController
@RequestMapping("/api/users")
public class UsersController {
	
	private UserService userSevice;
	
	@Autowired
	public UsersController(UserService userSevice) {
		super();
		this.userSevice = userSevice;
	}
	
	@GetMapping("/getall")
	public DataResult<List<User>> getAll(){
		return this.userSevice.getAll();
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody User user) {
		return this.userSevice.add(user);
		
	}
}
