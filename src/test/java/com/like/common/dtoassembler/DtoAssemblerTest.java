package com.like.common.dtoassembler;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonValue;
import com.like.common.dto.DtoAssembler;
import com.like.common.dto.annotation.DtoField;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
//@Transactional
public class DtoAssemblerTest {

	public enum PasswordType {
		SHA224("SHA224","SHA2-224BIT"),
		SHA256("SHA256","SHA2-256BIT"),
		SHA384("SHA384","SHA2-384BIT"),
		SHA512("SHA512","SHA2-512BIT");
		
		private String code;
		private String name;
		
		private PasswordType(final String code, final String name) {
			this.code = code;
	        this.name = name;
		}
		
		@JsonValue
	    public String getCode() {
	        return code;
	    }
	    	
	    public String getName() {
	        return name;
	    }
	}
	
	class DTO {
		@DtoField(targetEntity=Entity.class, fieldName="id")		
		private String id;
		
		@DtoField(targetEntity=Entity.class, fieldName="pwd")
		private String pwd;

		@DtoField(targetEntity=Entity.class, fieldName="type")
		private PasswordType pwdType;
		
		public DTO(String id, String pwd, PasswordType pwdType) {			
			this.id = id;
			this.pwd = pwd;
			this.pwdType = pwdType;
		}				
		
	}
	
	class Entity {
		private String id;
		private String pwd;
		private String cmt;
		private PasswordType type;
		
		public Entity(String id, String pwd, String cmt, PasswordType type) {			
			this.id = id;
			this.pwd = pwd;
			this.cmt = cmt;
			this.type = type;
		}
				
	}
	
	@Test
	public void test() throws Exception {
		DTO dto = new DTO("testid", "testpwd", PasswordType.SHA512);
		Entity entity = new Entity(null, null, null, null);
		
		DtoAssembler.ConvertDtoToEntity(dto, entity);
		
		Field field = entity.getClass().getDeclaredField("type");
		field.setAccessible(true);
		
		assertThat(entity.id, is("testid"));
		assertThat(entity.pwd, is("testpwd"));
		assertThat(entity.type, is(PasswordType.SHA512));
	}

}
