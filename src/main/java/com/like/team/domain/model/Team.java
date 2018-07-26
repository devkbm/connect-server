package com.like.team.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;

@Getter
@Entity
@Table(name = "team")
public class Team {

	@Id	
	@Column(name="team_id")
	private String teamId;
	
	@Column(name="team_name")
	private String teamName;
	
	@OneToMany(mappedBy="member")
	private List<JoinTeam> memberList = new ArrayList<JoinTeam>();
			
	protected Team() {}
	
	public Team(String teamId, String teamName) {
		this.teamId = teamId;
		this.teamName = teamName;
	}
}
