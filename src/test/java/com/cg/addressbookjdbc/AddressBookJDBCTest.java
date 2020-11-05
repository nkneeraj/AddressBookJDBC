package com.cg.addressbookjdbc;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import com.cg.addressbookjdbc.AddressBookService.IOService;


public class AddressBookJDBCTest {
	@Test
    public void givenEmpPayrollDataInDB_ShouldMatchEmpCount() {
    	AddressBookService service = new AddressBookService();
    	List<AddressBookData> addList = service.readAddressBookData(IOService.DB_IO);
    	Assert.assertEquals(7, addList.size());
    }
	
	@Test 
    public void givenNewCity_WhenUpdated_shouldMatchWithDB() {
    	AddressBookService service = new AddressBookService();
    	service.readAddressBookData(IOService.DB_IO);
    	service.updateContactsCity("neeraj", "bhdra");
    	boolean result = service.checkAddressBookDataInSyncWithDB("neeraj","bhdra");
		Assert.assertTrue(result);
    }
}