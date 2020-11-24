package com.guestbook.model.repository;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.guestbook.Repository.UserRepository;
import com.guestbook.model.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository repo;	

	@Autowired
	private TestEntityManager entitymanager;
	
	@Test
	public void testCreateUser() {
		User user = new User();
		user.setEmail("mrunal@gmail.com");
		user.setPassword("mrunal2020");
		user.setFirstname("mrunal");
		user.setLastname("kshetri");	
		
		User saveduser =repo.save(user);
		User existuser= entitymanager.find(User.class,saveduser.getId());
		assertThat(existuser.getEmail()).isEqualTo(user.getEmail());
			
	}
	
	@Test
	public void testFindByUserByemail() {
		String email="mrunal@@gmail.com";
		
		User user=repo.findByEmail(email);
		assertThat(user).isNotNull();
				
	}
	
	
}

