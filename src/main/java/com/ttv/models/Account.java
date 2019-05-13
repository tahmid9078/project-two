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
@Table(name = "ACCOUNT")
public class Account {
	
	@Id
	@Column(name="a_id")
	@SequenceGenerator(sequenceName="a_seq", name="a_seq")
	@GeneratedValue(generator="a_seq", strategy=GenerationType.SEQUENCE)
	private Long Id;
	
	@Column(name = "a_username", unique = true)
	private String username;
	
	@Column(name = "a_password")
	private String password;
	
	@Column(name = "a_first_name")
	private String firstName;
	
	@Column(name = "a_last_name")
	private String lastName;
	
	@Column(name = "a_email", unique = true)
	private String email;
	
	@ManyToOne
	@JoinColumn(name = "a_role")
	private Role role;

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(Long id, String username, String password, String firstName, String lastName, String email,
			Role role) {
		super();
		Id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
	}

	public Account(String username, String password, String firstName, String lastName, String email, Role role) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Account [Id=" + Id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", role=" + role + "]";
	}
	
	

	
	
	

}
