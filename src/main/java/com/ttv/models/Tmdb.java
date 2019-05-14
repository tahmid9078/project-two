package com.ttv.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name= "Tmdb")
public class Tmdb {
	
	@Id
	@Column(name="tm_id")
	@SequenceGenerator(sequenceName="tm_seq", name="tm_seq")
	@GeneratedValue(generator="tm_seq", strategy=GenerationType.SEQUENCE)
	private Long Id;
	
	@Column(name = "tm_movie_api_id")
	private String movieApiId;


	public Tmdb() {
	}

	public Tmdb(Long id, String movieApiId) {
		super();
		Id = id;
		this.movieApiId = movieApiId;
	}

	public Tmdb(String movieApiId) {
		super();
		this.movieApiId = movieApiId;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getMovieApiId() {
		return movieApiId;
	}

	public void setMovieApiId(String movieApiId) {
		this.movieApiId = movieApiId;
	}


	@Override
	public String toString() {
		return "Tmdb [Id=" + Id + ", movieApiId=" + movieApiId + "]";
	}

	
	

}
