package com.School.sba.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.School.sba.entity.User;
import com.School.sba.enums.UserRole;
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	boolean existsByUserRole(UserRole userRole);

	Optional<User> findByUserName(String username);

}
