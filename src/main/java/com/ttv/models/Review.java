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
@Table(name = "REVIEW")
public class Review {

	@Id
	@Column(name="re_id")
	@SequenceGenerator(sequenceName="re_seq", name="re_seq")
	@GeneratedValue(generator="re_seq", strategy=GenerationType.SEQUENCE)
	private Long Id;
	
	@ManyToOne
	@JoinColumn(name = "re_account")
	private Account account;
	
	@ManyToOne
	@JoinColumn(name = "re_movie")
	private Tmdb movie;
	
	@Column(name = "re_review")
	private String review;
	
	public Review() {
	}

	public Review(Long id, Account account, Tmdb movie, String review) {
		super();
		Id = id;
		this.account = account;
		this.movie = movie;
		this.review = review;
	}

	public Review(Account account, Tmdb movie, String review) {
		super();
		this.account = account;
		this.movie = movie;
		this.review = review;
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

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	@Override
	public String toString() {
		return "Review [Id=" + Id + ", account=" + account + ", movie=" + movie + ", review=" + review + "]";
	}
	

}
