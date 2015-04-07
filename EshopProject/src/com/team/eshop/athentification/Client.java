package com.team.eshop.athentification;

import java.io.Serializable;

import com.sun.xml.internal.ws.client.ClientSchemaValidationTube;

@SuppressWarnings("serial")
public class Client implements Serializable {

	private String firstName;
	private String secondName;
	private String telefonNumber;
	private String address;

	public Client() {

	}

	@Override
	public String toString() {
		return "Client [firstName=" + firstName + ", secondName=" + secondName
				+ ", telefonNumber=" + telefonNumber + ", address=" + address
				+ "]";
	}

	public Client(String firstName, String secondName, String telefonNumber,
			String address) {
		this.firstName = firstName;
		this.secondName = secondName;
		this.telefonNumber = telefonNumber;
		this.address = address;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getTelefonNumber() {
		return telefonNumber;
	}

	public void setTelefonNumber(String telefonNumber) {
		this.telefonNumber = telefonNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
