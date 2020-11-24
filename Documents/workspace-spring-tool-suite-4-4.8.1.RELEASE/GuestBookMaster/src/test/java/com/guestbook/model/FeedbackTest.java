package com.guestbook.model;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FeedbackTest {	
	@InjectMocks
	 Feedback feedback;
	 @Test
	public void FeedbackTst() {
		 feedback.setFeedbacktext("Good Morning...Have Nice Day");
		 feedback.setFirstname("Sidhu");
		 feedback.setFeedbackimagename("abc.jpg");		
		
		Collection<Role> roless = Arrays.asList(new Role("USER"));
		 
		feedback.toString();
		}

}
