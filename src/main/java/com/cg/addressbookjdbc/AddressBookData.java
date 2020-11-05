package com.cg.addressbookjdbc;

public class AddressBookData {

	public String first_name;
	public String last_name;
	public String address;
	public String city;
	public String state;
	public String zip;
	public String phone;
	public String email;

	public AddressBookData(String first_name, String last_name, String address, String city, String state, String zip, String phone, String email) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
	}

	@Override
	public String toString() {
		return "AddressBookData [first_name=" + first_name + ", last_name=" + last_name + ", city=" + city + 
				", state=" + state +  ", phone=" + phone + "]";
	}
}
