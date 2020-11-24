package com.guestbook.model;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {	
	@InjectMocks
	 User user;
	 @Test
	public void UserTst() {
		user.setEmail("sidkshetri@gmail.com");
		user.setFirstname("Sidhu");
		user.setLastname("kshetri");
		user.setId(1);
		user.setPassword("sidhu123");
		Collection<Role> roles = Arrays.asList(new Role("USER"));
		user.setRoles(roles );
		user.toString();
		}

}
