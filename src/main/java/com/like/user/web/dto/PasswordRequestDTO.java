package com.like.user.web.dto;

import lombok.Data;

@Data
public class PasswordRequestDTO {

	String userId;
	
	String beforePassword;
	
	String afterPassword;	
}
