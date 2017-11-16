package com.like.user.infra.jparepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.like.user.domain.model.Authority;
import com.like.user.domain.model.QAuthority;
import com.like.user.domain.model.QUser;
import com.like.user.domain.model.User;
import com.like.user.domain.repository.UserRepository;
import com.like.user.infra.jparepository.springdata.JpaAuthority;
import com.like.user.infra.jparepository.springdata.JpaUser;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class UserJpaRepository implements UserRepository {

	@Autowired
	private JPAQueryFactory  queryFactory;
	
	@Autowired
	private JpaUser jpaUser;
	
	@Autowired
	private JpaAuthority jpaAuthority;
		
	private final QUser qUser = QUser.user;
	private final QAuthority qAuthority = QAuthority.authority;	
	
	@Override
	public User getUser(String userId) {
		return jpaUser.findOne(userId);
	}
	

	@Override
	public List<Authority> readAuthority(String userId) {

		return queryFactory.selectFrom(qAuthority)
				.where(qAuthority.userName.eq(userId)).fetch();
	}

	@Override
	public void createUser(User user) {
		jpaUser.save(user);
	}

	@Override
	public void createAuthority(Authority authority) {
		jpaAuthority.save(authority);
	}

	@Override
	public void deleteUser(String userId) {
		jpaUser.delete(userId);		
	}

	@Override
	public void deleteAuthority(String userId) {
		jpaAuthority.delete(userId);		
	}
}
