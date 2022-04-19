package com.codingdojo.demo.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.codingdojo.demo.models.Carpooling;

public interface CarpoolingRepository extends CrudRepository<Carpooling,Long>{
	@Modifying
	@Transactional
	@Query( value = "INSERT INTO carpooling(ride_id, user_id)" +
					"VALUES(?1, ?2)", nativeQuery = true )
	void insertJoinRide( Long ride_id, Long user_id);
}
