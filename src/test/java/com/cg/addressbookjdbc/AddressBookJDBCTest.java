package com.cg.addressbookjdbc;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import com.cg.addressbookjdbc.AddressBookService.IOService;


public class AddressBookJDBCTest {
//	@Test
//    public void givenEmpPayrollDataInDB_ShouldMatchEmpCount() {
//    	AddressBookService service = new AddressBookService();
//    	List<AddressBookData> addList = service.readAddressBookData(IOService.DB_IO);
//    	Assert.assertEquals(8, addList.size());
//    }
	
//	@Test 
//    public void givenNewCity_WhenUpdated_shouldMatchWithDB() {
//    	AddressBookService service = new AddressBookService();
//    	service.readAddressBookData(IOService.DB_IO);
//    	service.updateContactsCity("neeraj", "bhdra");
//    	boolean result = service.checkAddressBookDataInSyncWithDB("neeraj","bhdra");
//		Assert.assertTrue(result);
//    }
//	
//	@Test
//	public void givenContactsData_WhenCountByCity_ShouldReturnProperValue() {
//		AddressBookService service = new AddressBookService();
//		service.readAddressBookData(IOService.DB_IO);
//		Map<String, Integer> countContactsByCity = service.readCountContactsByCity(IOService.DB_IO);
//		Assert.assertTrue(countContactsByCity.get("bhdra").equals(1));
//	}
//	
//	@Test
//	public void givenContactsData_WhenCountByState_ShouldReturnProperValue() {
//		AddressBookService service = new AddressBookService();
//		service.readAddressBookData(IOService.DB_IO);
//		Map<String, Integer> countContactsByState = service.readCountContactsByState(IOService.DB_IO);
//		Assert.assertTrue(countContactsByState.get("rajasthan").equals(3));
//	}
//	
//	@Test
//	public void givenNewContact_WhenAdded_ShouldSyncWithDB() {
//		AddressBookService service = new AddressBookService();
//		service.readAddressBookData(IOService.DB_IO);
//		service.addContact("Rakesh", "Mishra", "xyz", "abc", "y", "335501", "1234567890", "abc@gmail.com");
//		boolean result = service.checkAddressBookDataInSyncWithDB("Rakesh", "abc");
//		Assert.assertTrue(result);
//	}
	
	@Test 
    public void given3Contacts_WhenAdded_ShouldMatchContactsCount() {
    	AddressBookData[] addBookData = {
    			new AddressBookData("Rakesh1", "Mishra", "xyz", "abc", "y", "335501", "1234567890", "abc@gmail.com"),
    			new AddressBookData("Rakesh2", "Mishra", "xyz", "abc", "y", "335501", "1234567890", "abc@gmail.com"),
    			new AddressBookData("Rakesh3", "Mishra", "xyz", "abc", "y", "335501", "1234567890", "abc@gmail.com"),
    	};
    	AddressBookService addBookService = new AddressBookService();
    	addBookService.readAddressBookData(IOService.DB_IO);
    	Instant threadStart = Instant.now();
    	addBookService.addContactsWithThreads(Arrays.asList(addBookData));
    	Instant threadEnd = Instant.now();
    	System.out.println("Duration with thread : " + Duration.between(threadStart, threadEnd));
    	List<AddressBookData> addressBookData = addBookService.readAddressBookData(IOService.DB_IO);
    	System.out.println(addressBookData.size());
    	Assert.assertEquals(18, addressBookData.size());
    }
}