package com.like.user.domain.repository.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
					
	List<String> authorityList = new ArrayList<String>();

	List<String> menuGroupList = new ArrayList<String>(); 
	
	public UserSaveDTO() {}
	
	public UserSaveDTO(User user) {
		this.createdDt = user.getCreatedDt();
		this.createdBy = user.getCreatedBy();
		this.modifiedDt = user.getModifiedDt();
		this.modifiedBy = user.getModifiedBy();
		this.userId = user.getUsername();
		this.name = user.getName();
		this.password = user.getPassword();
		this.accountNonExpired = user.isAccountNonExpired();
		this.accountNonLocked = user.isAccountNonLocked();
		this.credentialsNonExpired = user.isCredentialsNonExpired();
		this.enabled = user.isEnabled();
		this.authorityList = user.getAuthorityList()
								.stream()
								.map(auth -> auth.getAuthority())
								.collect(Collectors.toList());
		this.menuGroupList = user.getMenuGroupList()
								.stream()
								.map(menuGroup -> menuGroup.getMenuGroupCode())
								.collect(Collectors.toList());
	}
	

}
