package com.School.sba.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.School.sba.entity.School;

@Repository
public interface SchoolRepository extends  JpaRepository<School, Integer> {

	

}
