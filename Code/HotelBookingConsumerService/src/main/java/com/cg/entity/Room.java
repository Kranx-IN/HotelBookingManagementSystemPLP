package com.cg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
@Entity
@Table(name="room")
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="room_id")
	private Integer roomId;
	@NotNull
	@Column(name="hotel_id")
	private Integer hotelId;
	@NotNull
	@Column(name="room_type")
	private String roomType;
	@NotNull
	@Column(name="per_night_fare")
	private double perNightFare;
	@NotNull
	@Column(name="availability")
	private boolean availability;
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
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public double getPerNightFare() {
		return perNightFare;
	}
	public void setPerNightFare(double perNightFare) {
		this.perNightFare = perNightFare;
	}
	public boolean isAvailability() {
		return availability;
	}
	public void setAvailability(boolean availability) {
		this.availability = availability;
	}
	public Room() {
		super();
	}
	public Room(Integer roomId, @NotNull Integer hotelId, @NotNull String roomType, @NotNull double perNightFare,
			@NotNull boolean availability) {
		super();
		this.roomId = roomId;
		this.hotelId = hotelId;
		this.roomType = roomType;
		this.perNightFare = perNightFare;
		this.availability = availability;
	}
	public Room(@NotNull Integer hotelId, @NotNull String roomType, @NotNull double perNightFare,
			@NotNull boolean availability) {
		super();
		this.hotelId = hotelId;
		this.roomType = roomType;
		this.perNightFare = perNightFare;
		this.availability = availability;
	}
	@Override
	public String toString() {
		return "Room [roomId=" + roomId + ", hotelId=" + hotelId + ", roomType=" + roomType + ", perNightFare="
				+ perNightFare + ", availability=" + availability + "]";
	}
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(!(obj instanceof Room))
			return false;
		Room room =(Room)obj;
		return 	this.roomId ==room.getRoomId() &&	this.hotelId == room.getHotelId()&&
				this.roomType == room.getRoomType() && this.perNightFare == room.getPerNightFare()&&
				this.availability == room.isAvailability();
	}
	
	
}
