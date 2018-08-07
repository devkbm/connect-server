package com.like.team.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "jointeam")
public class JoinTeam {

	@Id 
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="team_id")
	private Team team;
	
	@ManyToOne
	@JoinColumn(name="member_id")
	private Member member;	
	
	protected JoinTeam() {}
	
	public JoinTeam(Team team, Member member) {
		this.team = team;
		this.member = member;
	}
	
	public void joinTeam(Team team, Member member) {
		this.team = team;
		this.member = member;
	}
	
	public void changeTeam(Team team) {
		this.team = team;		
	}
	
}
