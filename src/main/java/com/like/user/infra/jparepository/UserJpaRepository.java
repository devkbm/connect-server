package com.like.user.infra.jparepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.like.user.domain.model.Authority;
import com.like.user.domain.model.QAuthority;
import com.like.user.domain.model.QUser;
import com.like.user.domain.model.User;
import com.like.user.domain.repository.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class UserJpaRepository implements UserRepository {

	@Autowired
	private JPAQueryFactory  queryFactory;
	
	private final QUser qUser = QUser.user;
	private final QAuthority qAuthority = QAuthority.authority;
	
	@Override
	public User readUser(String userName) {
		
		return queryFactory.selectFrom(qUser)
				.where(qUser.userName.eq(userName)).fetchOne();
	}

	@Override
	public List<Authority> readAuthority(String userName) {

		return queryFactory.selectFrom(qAuthority)
				.where(qAuthority.userName.eq(userName)).fetch();
	}

}
