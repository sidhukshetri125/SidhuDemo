
package com.guestbook.Web;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.guestbook.Service.FeedbackService;
import com.guestbook.Service.UserService;
import com.guestbook.model.Feedback;
import com.guestbook.model.User;

/**
 * <h1>User FeedbackController Class</h1> The User can provide the feedbacks
 * based on text and image *
 * 
 * @author Sidhu Kshetri
 * 
 */

@Controller
public class FeedbackController {

	Logger logger = LoggerFactory.getLogger(FeedbackController.class);
	@Autowired
	private UserService userService;

	@Autowired
	private FeedbackService feedbackService;

	private String errorMessage;

	private String adminFeedbackDelete;

	private String adminFeedbackEdit;

	private String adminFeedbackApprove;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getAdminFeedbackDelete() {
		return adminFeedbackDelete;
	}

	public void setAdminFeedbackDelete(String adminFeedbackDelete) {
		this.adminFeedbackDelete = adminFeedbackDelete;
	}

	public String getAdminFeedbackEdit() {
		return adminFeedbackEdit;
	}

	public void setAdminFeedbackEdit(String adminFeedbackEdit) {
		this.adminFeedbackEdit = adminFeedbackEdit;
	}

	public String getAdminFeedbackApprove() {
		return adminFeedbackApprove;
	}

	public void setAdminFeedbackApprove(String adminFeedbackApprove) {
		this.adminFeedbackApprove = adminFeedbackApprove;
	}

	@GetMapping("/addfeedback")
	public String addFeedback(Model model) {
		Feedback feedback = new Feedback();
		logger.debug("addFeedback::" + feedback);
		model.addAttribute("feedback", feedback);
		return "feedback-form";
	}

	@PostMapping("/submitFeedback")
	public String submitFeedback(@RequestParam("feedbackImage") MultipartFile feedbackImage,
			@RequestParam("feedbackText") String feedbackText, Model model, RedirectAttributes redirectAttributes) {

		logger.debug("addFeedback::feedback");
		User user = userService.findByEmail(getAuthenticate());
		String filname = StringUtils.cleanPath(feedbackImage.getOriginalFilename());
		Feedback feedback = new Feedback();
		feedback.setFeedbackimagename(filname);
		feedback.setFeedbacktext(feedbackText);
		try {
			feedback.setFeedbackimage(feedbackImage.getBytes());
		} catch (IOException e) {

			e.printStackTrace();
		}
		feedback.setTimestamp(new Date());
		feedback.setFirstname(user.getFirstname());
		feedback.setUser(user);
		logger.debug("addFeedback::" + feedback);
		feedbackService.saveFeedback(feedback);
		return "redirect:/viewfeedbackuser";
	}

	@GetMapping("/viewfeedbackuser")
	public String viewFeedbackUser(Model model) {
		List<Feedback> feedbackList = null;
		feedbackList = feedbackService.findFeebackByUserId(getAuthenticate());
		logger.debug("feedbackList::", feedbackList);
		model.addAttribute(feedbackList);
		model.addAttribute(getAuthenticate());
		return "user-feedback";

	}
	
	@GetMapping("/viewFeedbackAdmin")
	public String viewFeedbackadmin(Model model) {
		List<Feedback> feedbackList = null;
		feedbackList = feedbackService.findAllFeedbacks();
		logger.debug("feedbackList::", feedbackList);
		model.addAttribute("feedbackList", feedbackList);
		model.addAttribute("getLoggedUserName()", getAuthenticate());
		return "admin-feedback";
	}
	private String getAuthenticate() {
		String userdetails = "";
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		logger.debug("authentication::", authentication);
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			userdetails = authentication.getName();
		}
		logger.debug("userdetails::", userdetails);
		return userdetails;
	}

}
