package com.codingdojo.demo.services;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.codingdojo.demo.models.Ride;
import com.codingdojo.demo.models.User;
import com.codingdojo.demo.repositories.UserRepository;
import com.codingdojo.demo.repositories.CarpoolingRepository;
import com.codingdojo.demo.repositories.RideRepository;

@Service
public class AppService {

	private final UserRepository userRepository;
	private final RideRepository rideRepository;
	private final CarpoolingRepository carpoolingRepository;
	
	
	public AppService(UserRepository userRepository, RideRepository rideRepository, CarpoolingRepository carpoolingRepository) {
		this.userRepository = userRepository;
		this.rideRepository = rideRepository;
		this.carpoolingRepository = carpoolingRepository;
	}
	
	
//	--------------  USER SERVICE  --------------
	
// Get user email
	public List<User> getUserByEmail( String email ){
		return userRepository.selectUserByEmail(email);
	}

//	Insert user to database	
	public void registerUser( String name, String email, String password, Long typeofuser) {
		String encryptPassword = BCrypt.hashpw(password, BCrypt.gensalt());
		userRepository.insertUser(name, email, encryptPassword, typeofuser);
	}
	
//	Validate user
	public boolean validateUser( User currentUser, String password ) {
		return BCrypt.checkpw( password, currentUser.getPassword() );
	}
	
	
//	--------------  RIDE SERVICE  --------------
	
//	Insert ride
	public void createRide(String locationfrom, String locationto, Date date, Time time, Long seats, Long user_id) {
		rideRepository.insertRide(locationfrom, locationto, date, time, seats, seats);
	}
	
//	Search rides
	public List<Ride> getAllRidesDate(String locationfrom, String locationto, Date date){
		return rideRepository.selectAllRidesFromDate(locationfrom, locationto, date);
	}
	
//	CARPOOLING SERVICE
	public void joinRide(Long ride_id, Long user_id) {
		carpoolingRepository.insertJoinRide(ride_id, user_id);
	}
}
