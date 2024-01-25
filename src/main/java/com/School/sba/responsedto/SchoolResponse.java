package com.School.sba.responsedto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SchoolResponse {
	private int schooId;
	private String schoolName;
	private long schoolContactNo;
	private String schoolEmail;


}
