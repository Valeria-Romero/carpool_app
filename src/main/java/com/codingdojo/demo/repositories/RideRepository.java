package com.codingdojo.demo.repositories;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.codingdojo.demo.models.Ride;

public interface RideRepository extends CrudRepository<Ride,Long>{
	
	@Modifying
	@Transactional
	@Query( value = "INSERT INTO rides(locationfrom, locationto, date, time, seats, user_id) " +
					"VALUES(?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery = true )
	void insertRide( String locationfrom, String locationto, Date date, Time time, Long seats, Long user_id);
	
	@Query(value="SELECT * FROM rides WHERE locationfrom =?1 AND locationto=?2 AND date=?3", nativeQuery=true)
	List<Ride> selectAllRidesFromDate(String locationfrom, String locationto, Date date);
}
