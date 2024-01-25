package com.School.sba.entity;

import java.util.*;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class School {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int schooId;
	private String schoolName;
	private long schoolContactNo;
	private String schoolEmail;
	private String address;
	
	@OneToOne(mappedBy = "school")
	private Schedule schedule;
	
	@OneToMany(mappedBy = "school")
	private List<AcademicProgram> programs;
	
	@OneToMany(mappedBy = "school")
	private List<User> users;
	
}
