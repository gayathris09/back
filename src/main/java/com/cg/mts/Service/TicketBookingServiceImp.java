package com.cg.mts.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.mts.Exception.ActivityNotFoundException;
import com.cg.mts.Exception.CustomerNotFoundException;
import com.cg.mts.Exception.TicketBookingNotFoundException;
import com.cg.mts.Repository.IActivityRepository;
import com.cg.mts.Repository.ICustomerRepository;
import com.cg.mts.Repository.ITicketBookingRepository;
import com.cg.mts.dto.TicketBookingDto;
import com.cg.mts.entities.Activity;
import com.cg.mts.entities.Customer;
import com.cg.mts.entities.TicketBooking;
import com.cg.mts.util.CustomerConstants;
import com.cg.mts.util.TicketBookingConstants;

@Service
public class TicketBookingServiceImp implements ITicketBookingService {

	@Autowired
	ICustomerRepository customerRepo;

	@Autowired
	IActivityRepository activityRepo;

	@Autowired
	ITicketBookingRepository ticketBookingRepository;

	/*
	 * Method Name : bookTicket Parameter : ticketBookingDto Return Type :
	 * TicketBooking Author Name: P Ramya Sree Created Date : 23-05-2021
	 */
	@Override
	public TicketBooking bookTicket(TicketBookingDto ticketBookingDto)
			throws CustomerNotFoundException, ActivityNotFoundException, TicketBookingNotFoundException {
		validateTicketBooking(ticketBookingDto);
		Optional<Customer> optCustomer = customerRepo.findById(ticketBookingDto.getCustomerId());
		if (!optCustomer.isPresent())
			throw new CustomerNotFoundException(TicketBookingConstants.CUSTOMER_NOT_FOUND);
		Optional<Activity> optActivity = activityRepo.findById(ticketBookingDto.getActivityId());
		if (!optActivity.isPresent())
			throw new ActivityNotFoundException(TicketBookingConstants.ACTIVITY_NOT_FOUND);
		TicketBooking ticketBooking = new TicketBooking();
		ticketBooking.setDateOfVisiting(ticketBookingDto.getDateOfVisiting());
		ticketBooking.setNumberOfTickets(ticketBookingDto.getNumberOfTickets());
		ticketBooking.setBill(ticketBooking.getNumberOfTickets() * optActivity.get().getCharges());
		ticketBooking.setDateOfBooking(LocalDate.now());
		ticketBooking.setCustomer(optCustomer.get());
		ticketBooking.setActivity(optActivity.get());

		return ticketBookingRepository.save(ticketBooking);
	}

	/*
	 * Method Name : validateTicketBooking Parameter : ticketBookingDto Return Type
	 * : boolean Author Name: P Ramya Sree Created Date : 28-05-2021
	 */
	public boolean validateTicketBooking(TicketBookingDto ticketBookingDto) throws TicketBookingNotFoundException {
		if (ticketBookingDto.getDateOfVisiting().isBefore(LocalDate.now()))
			throw new TicketBookingNotFoundException(TicketBookingConstants.INVALID_DATE);
		return true;
	}

	/*
	 * Method Name : updateTicket Parameter : ticketBookingDto Return Type :
	 * TicketBooking Author Name: P Ramya Sree Created Date : 26-05-2021
	 */
	@Override
	public TicketBooking updateTicket(TicketBookingDto ticketBookingDto)
			throws CustomerNotFoundException, ActivityNotFoundException, TicketBookingNotFoundException {
		validateTicketBooking(ticketBookingDto);
		Optional<TicketBooking> optTicket = ticketBookingRepository.findById(ticketBookingDto.getTicketBookingId());
		if (!optTicket.isPresent())
			throw new TicketBookingNotFoundException(TicketBookingConstants.TICKET_NOT_FOUND);
		Optional<Customer> optCustomer = customerRepo.findById(ticketBookingDto.getCustomerId());
		if (!optCustomer.isPresent())
			throw new CustomerNotFoundException(TicketBookingConstants.CUSTOMER_NOT_FOUND);
		Optional<Activity> optActivity = activityRepo.findById(ticketBookingDto.getActivityId());
		if (!optActivity.isPresent())
			throw new ActivityNotFoundException(TicketBookingConstants.ACTIVITY_NOT_FOUND);
		TicketBooking ticketBooking = optTicket.get();
		Customer customer = optCustomer.get();
		Activity activity = optActivity.get();
		ticketBooking.setActivity(activity);
		ticketBooking.setCustomer(customer);
		ticketBooking.setDateOfBooking(LocalDate.now());
		ticketBooking.setDateOfVisiting(ticketBookingDto.getDateOfVisiting());
		ticketBooking.setNumberOfTickets(ticketBookingDto.getNumberOfTickets());
		ticketBooking.setBill(ticketBooking.getNumberOfTickets() * optActivity.get().getCharges());
		return ticketBookingRepository.save(ticketBooking);
	}

	/*
	 * Method Name : viewAllTickets Parameter : No Parameters Return Type : List
	 * Author Name: P Ramya Sree Created Date : 24-05-2021
	 */
	@Override
	public List<TicketBooking> viewAllTickets() throws TicketBookingNotFoundException {
		List<TicketBooking> ticketList = ticketBookingRepository.findAll();
		if (ticketList.isEmpty())
			throw new TicketBookingNotFoundException(TicketBookingConstants.TICKET_NOT_FOUND);
		return ticketList;
	}

	@Override
	public List<TicketBooking> viewAllTicketsCustomer(Customer customerId)
			throws CustomerNotFoundException, TicketBookingNotFoundException {
		List<TicketBooking> ticketList = ticketBookingRepository.findByCustomer(customerId);
		if (ticketList.isEmpty())
			throw new TicketBookingNotFoundException(CustomerConstants.CUSTOMER_NOT_FOUND);
		return ticketList;
	}
	
	/*@Override
	public List<TicketBooking> viewAllTicketsDate(LocalDate dateOfVisiting) throws TicketBookingNotFoundException {
		List<TicketBooking> ticketList = ticketBookingRepository.findByDateOfVisiting(dateOfVisiting);
		if (ticketList.isEmpty())
			throw new TicketBookingNotFoundException(TicketBookingConstants.TICKET_NOT_FOUND);
		return ticketList;
	}*/
}