package com.cg.addressbookjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressBookDBService {
	private static AddressBookDBService abService;
	private PreparedStatement preparedStatement;
	public static AddressBookDBService getInstance() {
		if(abService == null) {
			abService = new AddressBookDBService();
		}
		return abService;
	}

	private Connection getConnection() throws SQLException {
		String jdbcURL = "jdbc:mysql://localhost:3306/addressbook_service?useSSL=false";
		String userName = "root";
		String password = "Neeraj@123";
		Connection connection;
		connection = DriverManager.getConnection(jdbcURL, userName, password);
		return connection;
	}

	public List<AddressBookData> readData() {
		String sql = "select * from addressbook";
		List<AddressBookData> employeePayrollList = new ArrayList<>();
		try (Connection connection = this.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			employeePayrollList = this.getAddressBookData(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employeePayrollList;
	}

	private List<AddressBookData> getAddressBookData(ResultSet result) {
		List<AddressBookData> addressBookList = new ArrayList<>();
		try {
			while (result.next()) {
				String fname = result.getString("first_name");
				String lname = result.getString("last_name");
				String address = result.getString("address");
				String city = result.getString("city");
				String state = result.getString("state");
				String zip = result.getString("zip");
				String phone = result.getString("phone");
				String email = result.getString("email");
				addressBookList.add(new AddressBookData(fname, lname, address, city, state, zip,phone,email));
			}
			System.out.println(addressBookList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return addressBookList;
	}
	
	public int updateAddressBookData_Using_PreparedStatement(String fname, String city) {
		return this.updateAddressBookDataUsingPreparedStatement(fname, city);
	}
	
	private int updateAddressBookDataUsingPreparedStatement(String fname, String city) {
		String sql = String.format("update addressbook set city= '%s' where first_name = '%s';", city, fname);
		try (Connection connection = this.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(sql);
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public Map<String, Integer> getCountByCity() {
		String sql = "SELECT city, COUNT(city) AS count_city FROM addressbook GROUP BY city";
		Map<String, Integer> cityToContactsMap = new HashMap<>();
		try(Connection connection = this.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while(result.next()) {
				String city = result.getString("city");
				int count = result.getInt("count_city");
				cityToContactsMap.put(city, count);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return cityToContactsMap;
	}
}
