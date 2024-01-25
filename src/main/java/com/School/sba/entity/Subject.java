package com.School.sba.entity;

import org.hibernate.mapping.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Subject {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int subjectId;
	private String subjectName;
	@ManyToMany(mappedBy = "subjects")
	 private java.util.List<AcademicProgram> academicPrograms;
	@OneToMany(mappedBy = "subject")
	private java.util.List<ClassHour> classHours;
	@OneToMany(mappedBy = "subject")
	java.util.List<User> users;

}
