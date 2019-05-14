package com.ttv.models;

import javax.persistence.CascadeType;
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
@Table(name = "MOVIESHOWTIME")
public class MovieShowTime {
	
	@Id
	@Column(name="st_id")
	@SequenceGenerator(sequenceName="st_seq", name="st_seq")
	@GeneratedValue(generator="st_seq", strategy=GenerationType.SEQUENCE)
	private Long Id;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "st_movie")
	private Tmdb movie;
	
	@Column(name = "st_movie_time")
	private String movieTime;
	
	public MovieShowTime() {
	}

	public MovieShowTime(Long id, Tmdb movie, String movieTime) {
		super();
		Id = id;
		this.movie = movie;
		this.movieTime = movieTime;
	}

	public MovieShowTime(Tmdb movie, String movieTime) {
		super();
		this.movie = movie;
		this.movieTime = movieTime;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Tmdb getMovie() {
		return movie;
	}

	public void setMovie(Tmdb movie) {
		this.movie = movie;
	}

	public String getMovieTime() {
		return movieTime;
	}

	public void setMovieTime(String movieTime) {
		this.movieTime = movieTime;
	}

	@Override
	public String toString() {
		return "MovieShowTime [Id=" + Id + ", movie=" + movie + ", movieTime=" + movieTime + "]";
	}
	

}
