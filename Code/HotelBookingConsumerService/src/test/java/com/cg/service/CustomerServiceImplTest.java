package com.cg.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.cg.entity.Booking;
import com.cg.entity.User;
import com.cg.repo.UserRepository;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerServiceImplTest {

	@MockBean
	private RestTemplate rest;
	@MockBean
	private UserRepository repo;


	@Autowired
	private CustomerService target;

	User user;
	Booking booking;
	
	@Before
	public void setup() {
		user = new User(1,"sashank", "alaska1234", 7894561230l, "customer","sasha@gmail.com");
		booking= new Booking(201, 1, 100, 301, "Taj",LocalDate.of(2020, 02, 12),LocalDate.of(2020, 02, 14), 1, 2,3000.0);
	}
	
	@Test
	public void testCustomerForAddBooking_Success() {
		//Booking b= new Booking(201, 1, 100, 301, "Taj",LocalDate.of(2020, 02, 12),LocalDate.of(2020, 02, 14), 1, 2,3000.0);
		Mockito.when(rest.postForObject("http://booking-service/booking/add", booking, Booking.class)).thenReturn(booking);
		assertEquals(booking, target.addBooking(booking).getBody());
	}
	
	@Test
	public void testCustomerForAddBooking_Fail() {
		Booking b= new Booking(201, 1, 100, 301, "Taj",LocalDate.of(2020, 02, 12),LocalDate.of(2020, 02, 14), 1, 2,3000.0);
		Mockito.when(rest.postForObject("http://booking-service/booking/add", b, Booking.class)).thenReturn(b);
		assertEquals(b, target.addBooking(b).getBody());
	}
	
	
	
}
