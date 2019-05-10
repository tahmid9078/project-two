package com.ttv.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

public class Ticket {

	@Id
	@Column(name="t_id")
	@SequenceGenerator(sequenceName="t_seq", name="t_seq")
	@GeneratedValue(generator="t_seq", strategy=GenerationType.SEQUENCE)
	private Long Id;
	
	@ManyToOne
	@JoinColumn(name = "t_account")
	private Account account;
		
	@ManyToOne
	@JoinColumn(name = "t_movie_showtime")
	private MovieShowTime movieShowTime;
	
	@Column(name = "t_payment_card_number")
	private String paymentCardNumber;
	
	@Column(name = "t_status")
	private String status;
	
	@Column(name = "t_seat_number")
	private String seatNumber;
	
	@ManyToOne
	@JoinColumn(name = "t_ticket_type")
	private TicketType ticketType;
	
	public Ticket() {
	}

	public Ticket(Long id, Account account, MovieShowTime movieShowTime, String paymentCardNumber, String status,
			TicketType ticketType) {
		super();
		Id = id;
		this.account = account;
		this.movieShowTime = movieShowTime;
		this.paymentCardNumber = paymentCardNumber;
		this.status = status;
		this.ticketType = ticketType;
	}

	public Ticket(Account account, MovieShowTime movieShowTime, String paymentCardNumber, String status,
			TicketType ticketType) {
		super();
		this.account = account;
		this.movieShowTime = movieShowTime;
		this.paymentCardNumber = paymentCardNumber;
		this.status = status;
		this.ticketType = ticketType;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public MovieShowTime getMovieShowTime() {
		return movieShowTime;
	}

	public void setMovieShowTime(MovieShowTime movieShowTime) {
		this.movieShowTime = movieShowTime;
	}

	public String getPaymentCardNumber() {
		return paymentCardNumber;
	}

	public void setPaymentCardNumber(String paymentCardNumber) {
		this.paymentCardNumber = paymentCardNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public TicketType getTicketType() {
		return ticketType;
	}

	public void setTicketType(TicketType ticketType) {
		this.ticketType = ticketType;
	}

	@Override
	public String toString() {
		return "Ticket [Id=" + Id + ", account=" + account + ", movieShowTime=" + movieShowTime + ", paymentCardNumber="
				+ paymentCardNumber + ", status=" + status + ", ticketType=" + ticketType + "]";
	}

}
