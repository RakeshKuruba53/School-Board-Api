package com.School.sba.responsedto;

import com.School.sba.enums.UserRole;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UserResponse {
	private int userId;
	private String userName;
	private String firstName;
	private String lastName;
	private long contactNo;
	private String email;
	private UserRole userRole;
	private boolean isDeleted;

}
