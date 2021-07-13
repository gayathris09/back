package com.cg.mts.dto;

import java.time.LocalDate;

public class TicketBookingDto {

	private Integer ticketBookingId;
	private Integer activityId;
	private Integer customerId;
	private LocalDate dateOfVisiting;
	private Integer numberOfTickets;

	public TicketBookingDto() {
		super();
	}

	public TicketBookingDto(Integer ticketBookingId, Integer activityId, Integer customerId, LocalDate dateOfVisiting,
			Integer numberOfTickets) {
		super();
		this.ticketBookingId = ticketBookingId;
		this.activityId = activityId;
		this.customerId = customerId;
		this.dateOfVisiting = dateOfVisiting;
		this.numberOfTickets = numberOfTickets;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public LocalDate getDateOfVisiting() {
		return dateOfVisiting;
	}

	public void setDateOfVisiting(LocalDate dateOfVisiting) {
		this.dateOfVisiting = dateOfVisiting;
	}

	public Integer getTicketBookingId() {
		return ticketBookingId;
	}

	public void setTicketBookingId(Integer ticketBookingId) {
		this.ticketBookingId = ticketBookingId;
	}

	public Integer getNumberOfTickets() {
		return numberOfTickets;
	}

	public void setNumberOfTickets(Integer numberOfTickets) {
		this.numberOfTickets = numberOfTickets;
	}

}