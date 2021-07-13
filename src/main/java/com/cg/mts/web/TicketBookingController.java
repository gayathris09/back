package com.cg.mts.web;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.mts.Exception.ActivityNotFoundException;
import com.cg.mts.Exception.CustomerNotFoundException;
import com.cg.mts.Exception.TicketBookingNotFoundException;
import com.cg.mts.Service.TicketBookingServiceImp;
import com.cg.mts.dto.SuccessMessageDto;
import com.cg.mts.dto.TicketBookingDto;
import com.cg.mts.entities.Customer;
import com.cg.mts.entities.TicketBooking;
import com.cg.mts.util.TicketBookingConstants;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
// @RequestMapping("/ticketbooking")
public class TicketBookingController {

	@Autowired
	TicketBookingServiceImp ticketBookingServiceImp;

	/*
	 * Method Name : bookTicket Parameter : ticketBookingDto Return Type :
	 * TicketBooking Author Name: P Ramya Sree Created Date : 23-05-2021
	 */
	@RequestMapping(value = "/bookticket", method = RequestMethod.POST)
	public SuccessMessageDto bookTicket(@RequestBody TicketBookingDto ticketBookingDto)
			throws TicketBookingNotFoundException, CustomerNotFoundException, ActivityNotFoundException {
		TicketBooking booking = ticketBookingServiceImp.bookTicket(ticketBookingDto);
		return new SuccessMessageDto(TicketBookingConstants.TICKET_GENERATED + booking.getTicketBookingId());
	}

	/*
	 * Method Name : viewAllTickets Parameter : No Parameters Return Type : List
	 * Author Name: P Ramya Sree Created Date : 24-05-2021
	 */
	@GetMapping("/getalltickets")
	public ResponseEntity<List<TicketBooking>> viewAllTickets() throws TicketBookingNotFoundException {
		List<TicketBooking> ticketList = ticketBookingServiceImp.viewAllTickets();
		return new ResponseEntity<List<TicketBooking>>(ticketList, HttpStatus.OK);
	}

	/*
	 * Method Name : updateTicket Parameter : ticketBookingDto Return Type :
	 * TicketBooking Author Name: P Ramya Sree Created Date : 26-05-2021
	 */
	@PutMapping("/updateticket")
	public SuccessMessageDto updateTicket(@RequestBody TicketBookingDto ticketBookingDto)
			throws TicketBookingNotFoundException, CustomerNotFoundException, ActivityNotFoundException {
		TicketBooking ticketBooking = ticketBookingServiceImp.updateTicket(ticketBookingDto);
		return new SuccessMessageDto(TicketBookingConstants.TICKET_UPDATED + ticketBooking.getTicketBookingId());
	}

	@RequestMapping(value = "/customer/{customerId}")
	public List<TicketBooking> viewAllTicketsCustomer(@PathVariable("customerId") Customer customerId)
			throws CustomerNotFoundException, TicketBookingNotFoundException {
		return ticketBookingServiceImp.viewAllTicketsCustomer(customerId);
	}

	/*@RequestMapping(value = "/date")
	public List<TicketBooking> viewAllTicketsDate(@RequestParam("dateOfVisiting") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfVisiting) 
			throws TicketBookingNotFoundException {
		return ticketBookingServiceImp.viewAllTicketsDate(dateOfVisiting);
	}*/
}