package com.cg.mts.Service;

import java.time.LocalDate;
import java.util.List;

import com.cg.mts.Exception.ActivityNotFoundException;
import com.cg.mts.Exception.CustomerNotFoundException;
import com.cg.mts.Exception.TicketBookingNotFoundException;
import com.cg.mts.dto.TicketBookingDto;
import com.cg.mts.entities.Customer;
import com.cg.mts.entities.TicketBooking;

public interface ITicketBookingService {

	public TicketBooking bookTicket(TicketBookingDto ticketBookingDto)
			throws CustomerNotFoundException, ActivityNotFoundException, TicketBookingNotFoundException;

	public TicketBooking updateTicket(TicketBookingDto ticketBookingDto)
			throws CustomerNotFoundException, ActivityNotFoundException, TicketBookingNotFoundException;

	public List<TicketBooking> viewAllTickets() throws TicketBookingNotFoundException;

	public List<TicketBooking> viewAllTicketsCustomer(Customer id)
			throws CustomerNotFoundException, TicketBookingNotFoundException;
	
	/*public List<TicketBooking> viewAllTicketsDate(LocalDate dateOfVisiting) throws TicketBookingNotFoundException;*/
}