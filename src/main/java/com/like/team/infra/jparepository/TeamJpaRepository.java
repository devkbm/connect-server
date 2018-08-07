package com.like.team.infra.jparepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.like.team.domain.model.JoinTeam;
import com.like.team.domain.model.Member;
import com.like.team.domain.model.QJoinTeam;
import com.like.team.domain.model.Team;
import com.like.team.domain.repository.TeamRepository;
import com.like.team.domain.repository.springdata.JpaJoinTeam;
import com.like.team.domain.repository.springdata.JpaMember;
import com.like.team.domain.repository.springdata.JpaTeam;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class TeamJpaRepository implements TeamRepository {

	@Autowired
	private JPAQueryFactory  queryFactory;
	
	@Autowired
	private JpaTeam jpaTeam;
	
	@Autowired
	private JpaMember jpaMember;
	
	@Autowired
	private JpaJoinTeam jpaJoinTeam;
	
	private final QJoinTeam qJoinTeam = QJoinTeam.joinTeam;
	
	@Override
	public Team getTeam(String teamId) {
		return jpaTeam.findOne(teamId);
	}

	@Override
	public List<Team> getTeamList() {
		return jpaTeam.findAll();
	}

	@Override
	public void saveTeam(Team team) {
		jpaTeam.save(team);
	}

	@Override
	public void deleteTeam(Team team) {
		jpaTeam.delete(team);
	}

	@Override
	public Member getMember(String memberId) {
		return jpaMember.findOne(memberId);
	}

	@Override
	public void saveMember(Member member) {
		jpaMember.save(member);
	}

	@Override
	public void deleteMember(Member member) {
		jpaMember.delete(member);
	}

	@Override
	public void saveJoinTeam(JoinTeam joinTeam) {
		jpaJoinTeam.save(joinTeam);

	}

	@Override
	public void deleteJoinTeam(JoinTeam joinTeam) {
		jpaJoinTeam.delete(joinTeam);
	}

	@Override
	public JoinTeam getJoinTeam(String teamId, String memberId) {
		return queryFactory.selectFrom(qJoinTeam)
							.where(qJoinTeam.team.teamId.eq(teamId)
								.and(qJoinTeam.member.memberId.eq(memberId)))
							.fetchOne();
	}

}
