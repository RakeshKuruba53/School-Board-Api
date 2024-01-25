package com.School.sba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.School.sba.entity.AcademicProgram;
@Repository
public interface AcademicProgramRepo extends JpaRepository<AcademicProgram, Integer>{

}
