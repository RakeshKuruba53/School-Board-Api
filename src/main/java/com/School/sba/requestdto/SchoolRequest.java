package com.School.sba.requestdto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SchoolRequest {
	private String schoolName;
	private long schoolContactNo;
	private String schoolEmail;
}
