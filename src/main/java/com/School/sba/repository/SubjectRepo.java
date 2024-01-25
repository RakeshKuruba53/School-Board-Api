package com.School.sba.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.School.sba.entity.AcademicProgram;
import com.School.sba.entity.Subject;

@Repository
public interface SubjectRepo extends JpaRepository<Subject, Integer>{

	Optional<Subject> findBySubjectName(String name);
	
	
 

}
