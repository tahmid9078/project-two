package com.ttv.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name ="TICKET")
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
	@JoinColumn(name="t_movie_id")
	private Tmdb movie;
	
	@Column(name="t_movieShowTime")
	private String movieShowTime;
	
	@Column(name = "t_payment_card_number")
	private String paymentCardNumber;
	
	@ManyToOne
	@JoinColumn(name = "t_ticket_type")
	private TicketType ticketType;

	public Ticket() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ticket(Long id, Account account, Tmdb movie, String movieShowTime, String paymentCardNumber,
			TicketType ticketType) {
		super();
		Id = id;
		this.account = account;
		this.movie = movie;
		this.movieShowTime = movieShowTime;
		this.paymentCardNumber = paymentCardNumber;
		this.ticketType = ticketType;
	}

	public Ticket(Account account, Tmdb movie, String movieShowTime, String paymentCardNumber, TicketType ticketType) {
		super();
		this.account = account;
		this.movie = movie;
		this.movieShowTime = movieShowTime;
		this.paymentCardNumber = paymentCardNumber;
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

	public Tmdb getMovie() {
		return movie;
	}

	public void setMovie(Tmdb movie) {
		this.movie = movie;
	}

	public String getMovieShowTime() {
		return movieShowTime;
	}

	public void setMovieShowTime(String movieShowTime) {
		this.movieShowTime = movieShowTime;
	}

	public String getPaymentCardNumber() {
		return paymentCardNumber;
	}

	public void setPaymentCardNumber(String paymentCardNumber) {
		this.paymentCardNumber = paymentCardNumber;
	}

	public TicketType getTicketType() {
		return ticketType;
	}

	public void setTicketType(TicketType ticketType) {
		this.ticketType = ticketType;
	}

	@Override
	public String toString() {
		return "Ticket [Id=" + Id + ", account=" + account + ", movie=" + movie + ", movieShowTime=" + movieShowTime
				+ ", paymentCardNumber=" + paymentCardNumber + ", ticketType=" + ticketType + "]";
	}

	
	
	

}
