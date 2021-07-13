package com.cg.mts.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.mts.entities.Customer;
import com.cg.mts.entities.TicketBooking;

@Repository
public interface ITicketBookingRepository extends JpaRepository<TicketBooking, Integer> {

	public List<TicketBooking> findByCustomer(Customer customerId);
/*	public List<TicketBooking> findByDateOfVisiting(LocalDate dateOfVisiting);*/
	
}
