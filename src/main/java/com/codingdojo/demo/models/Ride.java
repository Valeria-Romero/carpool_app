package com.codingdojo.demo.models;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="rides")
public class Ride {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(max=255)
	private String locationfrom;
	
	@NotNull
	@Size(max=255)
	private String locationto;
	
	@NotNull
	@Column(name = "date", columnDefinition = "DATE")
	private Date date;
	
	@NotNull
	@Column(name = "time", columnDefinition = "TIME")
	private Time time;
	
	@NotNull
	private Long seats;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user_id;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="carpooling",
			joinColumns=@JoinColumn(name="ride_id"),
			inverseJoinColumns=@JoinColumn(name="user_id")
			
	)
	private List<User> user;
	
	public Ride() {		
	}
	
	public Ride(String locationfrom, String locationto, Date date, Time time, Long seats, User user_id) {
		this.locationfrom = locationfrom;
		this.locationto = locationto;
		this.date = date;
		this.time = time;
		this.seats = seats;
		this.user_id = user_id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLocationfrom() {
		return locationfrom;
	}

	public void setLocationfrom(String locationfrom) {
		this.locationfrom = locationfrom;
	}

	public String getLocationto() {
		return locationto;
	}

	public void setLocationto(String locationto) {
		this.locationto = locationto;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Long getSeats() {
		return seats;
	}

	public void setSeats(Long seats) {
		this.seats = seats;
	}

	public User getUser_id() {
		return user_id;
	}

	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}
	
	
}
