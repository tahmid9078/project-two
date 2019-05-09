package com.ttv.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Role")
public class Role {
	
	@Id
	@Column(name="r_id")
	@SequenceGenerator(sequenceName="r_seq", name="r_seq")
	@GeneratedValue(generator="r_seq", strategy=GenerationType.SEQUENCE)
	private Long Id;
	
	@Column(name = "r_name")
	private String roleName;
	
	public Role() {
		
	}

	public Role(Long id, String roleName) {
		super();
		Id = id;
		this.roleName = roleName;
	}

	public Role(String roleName) {
		super();
		this.roleName = roleName;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "Role [Id=" + Id + ", roleName=" + roleName + "]";
	}
	

}
