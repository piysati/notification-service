package com.project.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9182526415261141967L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private short id;

	@Column
	private String name;

	@Column
	private String email;

	@Column
	private String phoneNumber;

	/**
	 * @return the id
	 */
	public short getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(short id) {
		this.id = id;
	}

	/**
	 * @return the NAME
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the NAME to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


}
