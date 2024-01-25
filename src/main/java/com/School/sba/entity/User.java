package com.School.sba.entity;

import java.util.List;

import com.School.sba.enums.UserRole;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	@Column(unique = true)
	private String userName;
	private String firstName;
	private String lastName;
	private String password;
	private long contactNo;
	@Column(unique = true)
	private String email;
	private UserRole userRole;
	private boolean isDeleted;
	@ManyToOne
	private School school;
	@OneToMany(mappedBy = "user")
	private List<ClassHour> classHours;
	@ManyToOne
	private Subject subject;
	@ManyToMany
	List<AcademicProgram> academicPrograms;
	
	

}
