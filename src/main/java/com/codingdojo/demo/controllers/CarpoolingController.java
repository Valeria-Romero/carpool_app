package com.codingdojo.demo.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.demo.services.AppService;

@Controller
public class CarpoolingController {
	
	private final AppService appService;
	public CarpoolingController(AppService service) {
		this.appService = service;}
	

	
//	JOIN RIDE
	@RequestMapping(value="/ride/ride/join/{id}", method=RequestMethod.POST)
	public String joinRide(@PathVariable Long id,
						   @RequestParam(value="user_id")Long user_id,
						   RedirectAttributes redirectAttributes,
						   HttpSession session) {
		appService.joinRide(id, user_id);		
		return"rides.jsp";
	}

}