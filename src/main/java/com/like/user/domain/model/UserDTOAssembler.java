package com.like.user.domain.model;

import java.util.List;
import java.util.stream.Collectors;

import com.like.menu.domain.model.MenuGroup;
import com.like.user.dto.UserDTO;
import com.like.user.dto.UserDTO.UserSave;

public class UserDTOAssembler {	
		
	public static User createEntity(UserDTO.UserSave dto, List<Authority> authorityList, List<MenuGroup> menuGroupList) {
		if (dto.getUserId() == null) {
			new IllegalArgumentException("유저 아이디가 없습니다.");
		}
		
		return User.builder()
					.userId(dto.getUserId())										
					.name(dto.getName())
					.password(dto.getPassword())
					.isEnabled(dto.getEnabled())	
					.authorities(authorityList)
					.menuGroupList(menuGroupList)
					.build();
	}
	
	public static User mergeEntity(User entity, UserDTO.UserSave dto) {
		
		entity.userId		= nvl(dto.getUserId(), 		entity.userId);
		entity.name			= nvl(dto.getName(),	 	entity.name);
		entity.password		= nvl(dto.getPassword(), 	entity.password);
		entity.isEnabled	= nvl(dto.getEnabled(),		entity.isEnabled);
		
		return entity;
	}	
	
	public static UserDTO.UserSave convertDTO(User entity) {					
		
		UserSave dto = UserSave.builder()
								.createdDt(entity.getCreatedDt())
								.createdBy(entity.getCreatedBy())
								.modifiedDt(entity.getModifiedDt())
								.modifiedBy(entity.getModifiedBy())
								.userId(entity.userId)
								.name(entity.name)
								.password(entity.password)
								.enabled(entity.isEnabled)	
								.authorityList(entity.getAuthorityList()
													.stream()
													.map(auth -> auth.getAuthority())
													.collect(Collectors.toList()))
								.menuGroupList(entity.getMenuGroupList()
													.stream()
													.map(menuGroup -> menuGroup.getMenuGroupCode())
													.collect(Collectors.toList()))
								.build();
		
		return dto;
	}
	
	/**
	 * 
	 * @param a
	 * @param b
	 * @return a가 NULL일 경우 b, 이외에는 a 리턴
	 */
	private static <T>T nvl(T a, T b) {		
		return a == null ? b : a;
	}
		
			
}
