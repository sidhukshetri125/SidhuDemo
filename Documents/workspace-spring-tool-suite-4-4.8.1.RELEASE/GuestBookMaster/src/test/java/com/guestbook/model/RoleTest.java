 package com.guestbook.model;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RoleTest {	
	@InjectMocks
	 Role role;
	
	@Test
	public void UserTst() {
		
		role.setId(3);
		role.setName("CREATOR");		
		
		Collection<Role> roles = Arrays.asList(new Role("CREATOR"));		 
		role.toString();
		}

}
