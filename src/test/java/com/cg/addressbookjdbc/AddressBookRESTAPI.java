package com.cg.addressbookjdbc;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cg.addressbookjdbc.AddressBookService.IOService;
import com.google.gson.Gson;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AddressBookRESTAPI {
	@Before
	public void Setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 3000;
	}

	@Test
	public void givenAddressInJSONServer_whenRetrieved_ShouldMatchTheCount() {
		AddressBookData[] arrayOfEmps = getAddressBookList();
		AddressBookService service = new AddressBookService(Arrays.asList(arrayOfEmps));
		long entries = service.countEntries();
		Assert.assertEquals(2, entries);
	}

	private AddressBookData[] getAddressBookList() {
		Response response = RestAssured.get("/persons");
		System.out.println("AddressBook ENTRIES IN JSONServer:\n" + response.asString());
		AddressBookData[] arrayOfBooks = new Gson().fromJson(response.asString(), AddressBookData[].class);
		return arrayOfBooks;
	}
} 