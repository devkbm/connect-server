package com.like.team.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.like.team.domain.model.JoinTeam;
import com.like.team.domain.model.Member;
import com.like.team.domain.model.Team;
import com.like.team.domain.repository.TeamRepository;

@Service
@Transactional
public class TeamService {

	@Autowired
	TeamRepository teamRepository;
	
	public Team getTeam(String teamId) {
		return teamRepository.getTeam(teamId);
	}
	
	public List<Team> getTeamList() {
		return teamRepository.getTeamList();
	}
	
	public void saveTeam(Team team) {
		teamRepository.saveTeam(team);
	}
	
	public void deleteTeam(Team team) {
		teamRepository.deleteTeam(team);
	}
	
	
	public Member getMember(String memberId) {
		return teamRepository.getMember(memberId);
	}
	
	public void saveMember(Member member) {
		teamRepository.saveMember(member);
	}
	
	public void deleteMember(Member member) {
		teamRepository.deleteMember(member);
	}
	
	
	public void saveJoinTeam(JoinTeam joinTeam) {
		teamRepository.saveJoinTeam(joinTeam);
	}
	
	public void deleteJoinTeam(JoinTeam joinTeam) {
		teamRepository.deleteJoinTeam(joinTeam);
	}
	
}
