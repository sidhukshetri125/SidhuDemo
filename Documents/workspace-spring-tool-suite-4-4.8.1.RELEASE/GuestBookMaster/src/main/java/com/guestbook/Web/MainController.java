package com.guestbook.Web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
* <h1>User MainController Class</h1>
* Login , welcome user and admin user page 
*  
* 
* @author  Sidhu Kshetri
* 
*/
@Controller
public class MainController {
	
	//Login page
	@GetMapping("/login")
	public String login() {
		return "login-user";	
		 	
	}
	
	//Welcome Guest view 
	@GetMapping("/")
	public String home() {
		return "welcome-guest";
	}
	
	
	
	
}
