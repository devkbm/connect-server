package com.like.team.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "member")
public class Member {

	@Id
	@Column(name="member_id")
	private String memberId;
	
	@Column(name="member_name")
	private String memberName;
	
	@OneToMany(mappedBy="team")
	private List<JoinTeam> teamList = new ArrayList<JoinTeam>();
		
	public Member(String memberId, String memberName) {
		this.memberId = memberId;
		this.memberName = memberName;	
	}
}
