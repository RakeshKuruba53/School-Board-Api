package com.School.sba.requestdto;

import com.School.sba.enums.UserRole;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UserRequest {
	private String userName;
	private String firstName;
	private String lastName;
	private String password;
	private long contactNo;
	private String email;
	private UserRole userRole;
	private boolean  isboolean;

}
