package com.codingdojo.demo.controllers;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.demo.models.User;
import com.codingdojo.demo.services.AppService;

@Controller
public class UserController {
	
	private final AppService appService;
	public UserController(AppService service) {
		this.appService = service;
	}
	
//  LOAD SIGNIN / SIGNUP PAGE
	@RequestMapping( value = "/", method = RequestMethod.GET )
	public String index() {
		return "index.jsp";

	}
	
	
//	CREATE USER
	@RequestMapping( value = "/registerUser", method = RequestMethod.POST )
	public String registerUser( @RequestParam(value="name") String name,
								@RequestParam(value="email") String email,
								@RequestParam(value="password") String password,
								@RequestParam(value="passwordConfirmation") String passwordConfirmation,
								@RequestParam(value="typeofuser") Long typeofuser,
								RedirectAttributes redirectAttributes,
								HttpSession session) {
		
		List<User> user = appService.getUserByEmail(email);
		
		if( user.size() > 0 ) {
			redirectAttributes.addFlashAttribute("errorMessage", "That user email already exists!");
			return "redirect:/";
		}
		else {
			if( ! password.equals( passwordConfirmation ) ) {
				redirectAttributes.addFlashAttribute("errorMessage", "Password and password confirmation do not match");
				return "redirect:/";
			}
			
			if(name.isEmpty() || email.isEmpty() || password.isEmpty() || passwordConfirmation.isEmpty()) {
				redirectAttributes.addFlashAttribute("emptyspace", "You leaved an empty space");
				return "redirect:/";
			}
			else {
				appService.registerUser(name, email, password, typeofuser);
				session.setAttribute( "firstName", name );
				session.setAttribute("email", email);
				redirectAttributes.addFlashAttribute("success", "User created successfully");
				return "redirect:/";
			}
		}
	}
	
	
//	VALIDATE USER WHEN LOGIN
	@RequestMapping( value = "/login", method = RequestMethod.POST )
	public String login( @RequestParam(value="userEmail") String email,
						 @RequestParam(value="userPassword") String password,
						 RedirectAttributes redirectAttributes,
						 HttpSession session) {
		
		List<User> user = appService.getUserByEmail(email);
		if( user.size() == 0 || user.isEmpty())  {
			redirectAttributes.addFlashAttribute("loginErrorMessage", "Wrong credentials");
			return "redirect:/";
		}
		else {
			User currentUser = user.get(0);
			
			if( appService.validateUser(currentUser, password)) {
				session.setAttribute( "name", currentUser.getName() );
				session.setAttribute("email", currentUser.getEmail());
				session.setAttribute("id", currentUser.getId());
				session.setAttribute("userType", currentUser.getTypeofuser());
				return "redirect:/dashboard";
			}

			else {
				redirectAttributes.addFlashAttribute("loginErrorMessage", "Wrong password");
				return "redirect:/";
			}
		}
	}
	
//	LOAD USER DASHBOARD PAGE
	@RequestMapping( value = "/dashboard", method = RequestMethod.GET )
	public String dashboard(HttpSession session, Model model) {
		
		String currentUser = (String) session.getAttribute("name");
		if(currentUser == null) {
			return "redirect:/";
		}
		
		Long userType = (Long) session.getAttribute("userType");
		session.getAttribute("userType");
		
		Long user_id = (Long) session.getAttribute("id");
		model.addAttribute("user_id", user_id);
		model.addAttribute("userType",userType);
		return"dashboard.jsp";
	}
	
//	LOGOUT
	@RequestMapping( value = "/logout", method = RequestMethod.GET )
	public String logout( HttpSession session ) {
		session.removeAttribute("name");
		return "redirect:/";
	}
}