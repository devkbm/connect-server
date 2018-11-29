package com.like.user.infra.jparepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.like.user.domain.model.Authority;
import com.like.user.domain.model.QAuthority;
import com.like.user.domain.model.QUser;
import com.like.user.domain.model.User;
import com.like.user.domain.repository.UserRepository;
import com.like.user.dto.AuthorityQueryDTO;
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
	public User getUser(String userId) throws UsernameNotFoundException {
		return jpaUser.findOne(userId);
	}
	
	@Override
	public List<User> getUserList() {
		return jpaUser.findAll();
	}

	@Override
	public void saveUser(User user) {
		jpaUser.save(user);
	}
	
	@Override
	public void deleteUser(String userId) {
		jpaUser.delete(userId);		
	}
		
	@Override
	public void addUserAuthority(String userId, Authority authority) { 
		User user = jpaUser.findOne(userId);
		
		user.addAuthoritiy(authority);
	}

	@Override
	public List<Authority> readAuthority(String userId) {			
		return jpaUser.findOne(userId).getAuthorityList();
	}

	@Override
	public List<Authority> getAuthorityList(AuthorityQueryDTO condition) {
		return queryFactory
				.selectFrom(qAuthority)
				.where(condition.getBooleanBuilder())
				.fetch();
	}
			
	@Override
	public List<Authority> getAuthorityList(List<String> authorityNameList) {
		return jpaAuthority.findAll(authorityNameList);
	}

	@Override
	public Authority getAuthority(String authorityName) {
		return jpaAuthority.findOne(authorityName);
	}

	@Override
	public void createAuthority(Authority authority) {
		jpaAuthority.save(authority);
	}

	@Override
	public void deleteAuthority(String authority) {
		jpaAuthority.delete(authority);		
	}
	
}
