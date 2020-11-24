package com.guestbook.Web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.guestbook.Service.UserService;
import com.guestbook.Web.dto.UserRegistrationDto;


/**
* <h1>User Registration Class</h1>
* The User Registration program implements an application that
* simply register users and save into DB
* 
* @author  Sidhu Kshetri
* 
*/


@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
	
	private UserService userService;
	
	// Added LoggerFactory to log the details on every step
	Logger logger = LoggerFactory.getLogger(UserRegistrationController.class);

	
	public UserRegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
		
	}	
	
	@GetMapping
	public String showRegistrionForm() {			 
		logger.debug("Entering into registration form");	
		return "registration-form";
	}
	
	/**
	   * This method is used to registerUserAccount. This is
	   * a the simplest form of a class method, 
	   * 
	   * @param @ModelAttribute("user") This is the first paramter to registerUserAccount method
	   * @param UserRegistrationDto registrationDto  This is the second parameter to registerUserAccount method
	   * @return registration message
	   */
	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
		userService.save(registrationDto);		
		logger.debug("Saved registrationDto in userService",registrationDto);		
		return "redirect:/registration?success";		
	}

}
