package com.like.team.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.team.domain.model.JoinTeam;
import com.like.team.domain.model.Member;
import com.like.team.domain.model.Team;

@Repository
public interface TeamRepository {

	Team getTeam(String teamId);
	
	List<Team> getTeamList();
	
	void saveTeam(Team team);
	
	void deleteTeam(Team team);
	
	
	Member getMember(String memberId);
	
	void saveMember(Member member);
	
	void deleteMember(Member member);
	
	
	void saveJoinTeam(JoinTeam joinTeam);
	
	void deleteJoinTeam(JoinTeam joinTeam);
	
}
