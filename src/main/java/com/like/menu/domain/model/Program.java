package com.like.menu.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.like.common.domain.AuditEntity;

@Entity
@Table(name = "cmprogram")
@EntityListeners(AuditingEntityListener.class)
public class Program extends AuditEntity implements Serializable{

	@Id
	@Column(name="program_code")
	private String programCode;
	
	@Column(name="program_name")
	private String programName; 
		
	@Column(name="url")
	private String url;
	
	@Column(name="description")
	private String description;
	
	@JsonIgnore
	@OneToMany(mappedBy = "program")          
    List<Menu> menuList = new ArrayList<Menu>();
}
