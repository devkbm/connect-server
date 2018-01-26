package com.like.user.domain.repository.dto;

import java.time.LocalDateTime;

import com.like.common.domain.annotation.DTOInfo;
import com.like.user.domain.model.User;

import lombok.Data;

@Data
public class UserSaveDTO {
	
	LocalDateTime createdDt;	
	
	String createdBy;
	
	LocalDateTime modifiedDt;
	
	String modifiedBy;
	
	@DTOInfo(targetEntity=User.class, fieldName="userId")
	String userId;
	
	@DTOInfo(targetEntity=User.class, fieldName="name")
	String name;
	
	@DTOInfo(targetEntity=User.class, fieldName="password")
	String password;	
		
	@DTOInfo(targetEntity=User.class, fieldName="isAccountNonExpired")
	Boolean accountNonExpired;
	
	@DTOInfo(targetEntity=User.class, fieldName="isAccountNonLocked")
	Boolean accountNonLocked;
	
	@DTOInfo(targetEntity=User.class, fieldName="isCredentialsNonExpired")
	Boolean credentialsNonExpired;
	
	@DTOInfo(targetEntity=User.class, fieldName="isEnabled")
	Boolean enabled;
					
}
