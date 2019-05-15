package com.ttv.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="TICKETTYPE")
public class TicketType {

	@Id
	@Column(name="tt_id")
	@SequenceGenerator(sequenceName="tt_seq", name="tt_seq")
	@GeneratedValue(generator="tt_seq", strategy=GenerationType.SEQUENCE)
	private Long Id;
	
	@Column(name = "tt_type")
	private String type;
	
	@Column(name = "tt_price")
	private Double price;
	
	public TicketType() {
	}

	public TicketType(Long id, String type, Double price) {
		super();
		Id = id;
		this.type = type;
		this.price = price;
	}

	public TicketType(String type, Double price) {
		super();
		this.type = type;
		this.price = price;
	}

	
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "TicketType [Id=" + Id + ", type=" + type + ", price=" + price + "]";
	}
	
	

}
