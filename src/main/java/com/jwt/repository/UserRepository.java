package com.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwt.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

//	Optional<User>  findByMobileNo(String mobileNo);

//	Optional<User> findByMobileNo(String username);
//	User findByMobileNo(String username);
	
	
	User findByPhoneNumber(String phoneNumber);
}
