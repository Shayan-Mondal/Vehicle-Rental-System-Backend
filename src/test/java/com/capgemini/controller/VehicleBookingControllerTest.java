package com.capgemini.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.capgemini.entities.UserInfo;
import com.capgemini.entities.Vehicle;
import com.capgemini.entities.VehicleBooking;
import com.capgemini.entities.VehicleBrand;
import com.capgemini.repository.UserInfoRepository;
import com.capgemini.repository.VehicleBookingRepository;
import com.capgemini.repository.VehicleBrandRepository;
import com.capgemini.repository.VehicleRepository;

@WebMvcTest(VehicleBookingController.class)
class VehicleBookingControllerTest {

	@Autowired
	private MockMvc mockMvc;  //It encapsulates all web application beans and makes them available for testing.

	@MockBean //It allows to mock a class or an interface and to record and verify behaviors on it.
	private VehicleBookingRepository vehicleBookingRepository;

	@MockBean
	private VehicleRepository vehicleRepository;

	@MockBean
	private UserInfoRepository userInfoRepository;

	@MockBean
	VehicleBrandRepository vehicleBrandRepository;

	@Test
	void testFindAllBooking() throws Exception {

		UserInfo user = new UserInfo();
		user.setUserId(1);
		user.setUserName("Shubham");
		user.setUserEmail("shubham@gmail.com");
		user.setUserNumber("1234567890");
		user.setUserPassword("123@abc");
		user.setUserAdhaar("1234567890");
		user.setDeleted(false);

		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleId(1);
		vehicle.setVehiclePlateNumber("BR1234");
		vehicle.setVehicleName("swift");
		vehicle.setVehicleType("sports");
		vehicle.setVehicleColor("red");
		vehicle.setVehicleLocation("patna");
		vehicle.setNumberOfSeats(4);
		vehicle.setDailyPrice(1500);
		vehicle.setAvailable(true);
		vehicle.setDeleted(false);
		
		VehicleBrand vehicleBrand = new VehicleBrand();
		vehicleBrand.setBrand_id(1);
		vehicleBrand.setBrand_name("Honda");
		vehicleBrand.setDeleted(false);

		VehicleBooking vehicleBooking = new VehicleBooking();
		vehicleBooking.setBookingId(1);
		vehicleBooking.setBookingStartDate("02-05-2021");
		vehicleBooking.setBookingEndDate("28-05-2021");
		vehicleBooking.setCancelled(false);
		vehicleBooking.setUserInfo(user);
		vehicleBooking.setVehicle(vehicle);

		
		mockMvc.perform(get("/api/bookings/all")).andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	void testFindBookingById() throws Exception {

		UserInfo user = new UserInfo();
		user.setUserId(2);
		user.setUserName("Shubham");
		user.setUserEmail("shubham@gmail.com");
		user.setUserNumber("1234567890");
		user.setUserPassword("123@abc");
		user.setUserAdhaar("1234567890");
		user.setDeleted(false);

		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleId(3);
		vehicle.setVehiclePlateNumber("BR1234");
		vehicle.setVehicleName("swift");
		vehicle.setVehicleType("sports");
		vehicle.setVehicleColor("red");
		vehicle.setVehicleLocation("patna");
		vehicle.setNumberOfSeats(4);
		vehicle.setDailyPrice(1500);
		vehicle.setAvailable(true);
		vehicle.setDeleted(false);
		
		VehicleBrand vehicleBrand = new VehicleBrand();
		vehicleBrand.setBrand_id(1);
		vehicleBrand.setBrand_name("Honda");
		vehicleBrand.setDeleted(false);

		Optional<VehicleBooking> vehicleBooking = Optional.of(new VehicleBooking());
	/*	vehicleBooking.    //setBookingId(2);
		vehicleBooking.setBookingStartDate("02-05-2021");
		vehicleBooking.setBookingEndDate("28-05-2021");
		vehicleBooking.setCancelled(false);
		vehicleBooking.setUserInfo(user);
		vehicleBooking.setVehicle(vehicle);
*/
		Mockito.when(vehicleBookingRepository.findById(Mockito.anyInt())).thenReturn(vehicleBooking);
		mockMvc.perform(get("/api/booking/details/2")).andExpect(MockMvcResultMatchers.status().isOk());

	}

}
