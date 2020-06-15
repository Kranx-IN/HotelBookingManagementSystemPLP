package com.cg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;



@Entity
public class Hotel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="hotel_id")
	private Integer hotelId;
	@NotNull
	@Column(name="hotel_name")
	private String hotelName;
	@NotNull
	@Column(name="city")
	private String city;
	@NotNull
	@Column(name="address")
	private String address;
	@NotNull
	@Positive
	@Column(name="average_fare")
	private Double averageFare;
	@Email
	@Column(name="email")
	private String email;
	@Positive
	@Column(name="phone_number")
	private Long phoneNumber;
	@NotNull
	@Column(name="desrciption")
	private String desrciption;
	
	public Integer getHotelId() {
		return hotelId;
	}
	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Double getAverageFare() {
		return averageFare;
	}
	public void setAverageFare(Double averageFare) {
		this.averageFare = averageFare;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getDesrciption() {
		return desrciption;
	}
	public void setDesrciption(String desrciption) {
		this.desrciption = desrciption;
	}
	public Hotel() {
		super();
	}
	public Hotel(Integer hotelId, @NotNull String hotelName, @NotNull String city, @NotNull String address,
			@NotNull Double averageFare, @Email String email, @Positive Long phoneNumber, @NotNull String desrciption) {
		super();
		this.hotelId = hotelId;
		this.hotelName = hotelName;
		this.city = city;
		this.address = address;
		this.averageFare = averageFare;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.desrciption = desrciption;
	}
	
	public Hotel(@NotNull String hotelName, @NotNull String city, @NotNull String address, @NotNull Double averageFare,
			@Email String email, @Positive Long phoneNumber, @NotNull String desrciption) {
		super();
		this.hotelName = hotelName;
		this.city = city;
		this.address = address;
		this.averageFare = averageFare;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.desrciption = desrciption;
	}
	
	
	
	@Override
	public String toString() {
		return "Hotel [hotelId=" + hotelId + ", hotelName=" + hotelName + ", city=" + city + ", address=" + address
				+ ", averageFare=" + averageFare + ", email=" + email + ", phoneNumber=" + phoneNumber
				+ ", desrciption=" + desrciption + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(this == obj)
			return true;
		if(!(obj instanceof Hotel))
			return false;
		Hotel h = (Hotel)obj;
		return this.hotelId==h.getHotelId() && this.hotelName == h.getHotelName() &&
				this.city == h.getCity() && this.address == h.getAddress() && this.averageFare == h.getAverageFare() && 
				this.email == h.getEmail() && this.phoneNumber == h.getPhoneNumber() && this.desrciption == h.getDesrciption();
		
	}
	
	
	
		
}

