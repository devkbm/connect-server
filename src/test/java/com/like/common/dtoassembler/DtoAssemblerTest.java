package com.like.common.dtoassembler;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.like.board.domain.model.Board;
import com.like.common.domain.DTOConverter;
import com.like.common.domain.annotation.DtoField;

@RunWith(SpringRunner.class)
@SpringBootTest
//@Transactional
public class DtoAssemblerTest {

	class DTO {
		@DtoField(targetEntity=Entity.class, fieldName="id")		
		private String id;
		
		@DtoField(targetEntity=Entity.class, fieldName="pwd")
		private String pwd;

		public DTO(String id, String pwd) {			
			this.id = id;
			this.pwd = pwd;
		}		
		
		
	}
	
	class Entity {
		private String id;
		private String pwd;
		private String cmt;
		
		public Entity(String id, String pwd, String cmt) {			
			this.id = id;
			this.pwd = pwd;
			this.cmt = cmt;
		}
				
	}
	
	@Test
	public void test() throws Exception {
		DTO dto = new DTO("testid", "testpwd");
		Entity entity = new Entity(null, null, null);
		
		DTOConverter.ConvertDtoToEntity(dto, entity);
		
		assertThat(entity.id, is("testid"));
		assertThat(entity.pwd, is("testpwd"));
	}

}
