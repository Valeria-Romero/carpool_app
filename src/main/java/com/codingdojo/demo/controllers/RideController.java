package com.codingdojo.demo.controllers;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.demo.models.Ride;
import com.codingdojo.demo.services.AppService;

@Controller
public class RideController {

	private final AppService appService;
	public RideController(AppService service) {
		this.appService = service;
	}
	
//	CREATE RIDE
	@RequestMapping(value="/ride/create", method=RequestMethod.POST)
	public String createRide(@RequestParam(value="locationfrom") String locationfrom,
							 @RequestParam(value="locationto")String locationto,
							 @RequestParam(value="date")
	 						 @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
							 @RequestParam(value="time")String time,
							 @RequestParam(value="seats")Long seats,
							 @RequestParam(value="user_id")Long user_id,
							 HttpSession session,
							 RedirectAttributes redirectAttributes) {

	
		System.out.println("date is: "+date);
		System.out.println("time is: "+Time.valueOf(time+":00"));
		System.out.println("date is: "+seats);
		System.out.println("user id:" + user_id);
		
		appService.createRide(locationfrom, locationto, date, Time.valueOf(time+":00"), seats, user_id);
		return "redirect:/dashboard";
	}
	
//	SELECT RIDES
	@RequestMapping(value="/ride/search", method=RequestMethod.POST)
	public String searchRide(@RequestParam(value="locationfrom") String locationfrom,
			 				 @RequestParam(value="locationto")String locationto,
			 				 @RequestParam(value="date")
			 				 @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
			 				 HttpSession session,
							 RedirectAttributes redirectAttributes,
							 Model model) {
		List<Ride> rides = appService.getAllRidesDate(locationfrom, locationto, date);
		System.out.println(rides);
		Long user_id = (Long) session.getAttribute("id");
		model.addAttribute("user_id", user_id);
		model.addAttribute("rides", rides);
		return"search.jsp";
	}
}
