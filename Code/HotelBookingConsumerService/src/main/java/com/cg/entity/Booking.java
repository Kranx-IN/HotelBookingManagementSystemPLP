package com.cg.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="booking")
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="booking_id")
	private Integer bookingId;
	@Positive
	@Column(name="user_id")
	private Integer userId;
	@Positive
	@Column(name="room_id")
	private Integer roomId;
	@Positive
	@Column(name="hotel_id")
	private Integer hotelId;
	@NotNull
	@Column(name="hotel_name")
	private String hotelName;
	//@Future
	@Column(name="check_in_date")
	@JsonFormat(pattern = "yyyy,mm,dd")
	private LocalDate checkInDate;
	//@Future
	@Column(name="check_out_date")
	@JsonFormat(pattern = "yyyy,mm,dd")
	private LocalDate checkOutDate;
	@Positive
	@Column(name="no_of_adults")
	private Integer noOfAdults;
	@Positive
	@Column(name="no_of_children")
	private Integer noOfChildren;
	@Column(name="amount")
	private Double amount;
	
	// to validate checkin and check out dates
	@AssertTrue(message = "check-in date should be before check-out date")
	private boolean isValid() {
		if (checkInDate == null || checkOutDate == null)
			return false;
		if (checkInDate.isBefore(checkOutDate)) {
			return true;
		} else {
			return false;
		}
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public Integer getHotelId() {
		return hotelId;
	}

	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}

	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}

	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public Integer getNoOfAdults() {
		return noOfAdults;
	}

	public void setNoOfAdults(Integer noOfAdults) {
		this.noOfAdults = noOfAdults;
	}

	public Integer getNoOfChildren() {
		return noOfChildren;
	}

	public void setNoOfChildren(Integer noOfChildren) {
		this.noOfChildren = noOfChildren;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	

	public Booking() {
		super();
	}

	
	public Booking(Integer bookingId, @Positive Integer userId, @Positive Integer roomId, @Positive Integer hotelId,
			@NotNull String hotelName,  LocalDate checkInDate,  LocalDate checkOutDate,
			@Positive Integer noOfAdults, @Positive Integer noOfChildren, Double amount) {
		super();
		this.bookingId = bookingId;
		this.userId = userId;
		this.roomId = roomId;
		this.hotelId = hotelId;
		this.hotelName = hotelName;
		
		this.checkInDate = checkInDate;
		this.checkOutDate =checkOutDate;
		this.noOfAdults = noOfAdults;
		this.noOfChildren = noOfChildren;
		this.amount = amount;
	}
	

	public Booking(@Positive Integer userId, @Positive Integer roomId, @Positive Integer hotelId,
			@NotNull String hotelName,  LocalDate checkInDate,  LocalDate checkOutDate,
			@Positive Integer noOfAdults, @Positive Integer noOfChildren, Double amount) {
		super();
		this.userId = userId;
		this.roomId = roomId;
		this.hotelId = hotelId;
		this.hotelName = hotelName;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.noOfAdults = noOfAdults;
		this.noOfChildren = noOfChildren;
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", userId=" + userId + ", roomId=" + roomId + ", hotelId=" + hotelId
				+ ", hotelName=" + hotelName + ", checkInDate=" + checkInDate + ", checkOutDate=" + checkOutDate
				+ ", noOfAdults=" + noOfAdults + ", noOfChildren=" + noOfChildren + ", amount=" + amount + "]";
	}

	@Override
	public boolean equals(Object obj) {
		
		if(this == obj)
			return true;
		if(!(obj instanceof Booking))
			return false;
		Booking b = (Booking)obj;
		
		return this.bookingId == b.getBookingId()&&this.userId ==b.getUserId()&&
			this.roomId == b.getRoomId()&&this.hotelId == b.getHotelId() &&
			this.hotelName == b.getHotelName()&&this.checkInDate == b.getCheckInDate()&&
			this.checkOutDate ==b.getCheckOutDate()&&this.noOfAdults == b.getNoOfAdults()&&
			this.noOfChildren == b.getNoOfChildren()&&this.amount == b.getAmount();
		
	}
	
	
	
}

	
	