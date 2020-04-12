package connections;

import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Database {
	
	private String DBURL  = "127.0.0.1";
	private String DBPORT = "5432";

	public Database() {
		// TODO Auto-generated constructor stub
	}
	
	void getAllDevices() {
		//return all devices in the database	
	}
	
	void updateDeviceStatus(String DeviceName, String newStatus) {
		//update a device status
	}
}
