package javahrms.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import javahrms.hrms.entities.concretes.User;

public interface UserDao extends JpaRepository<User,Integer> {

	boolean existsUserByEmail(String email);
}
