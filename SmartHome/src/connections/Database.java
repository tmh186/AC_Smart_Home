package connections;

import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Database {

	private static String DBURL = "127.0.0.1";
	private static String DBPORT = "5432";
	private static String user = "team4";
	private static String pass = "4team4";

	static void testConnection() {
		//taken from https://www.tutorialspoint.com/postgresql/postgresql_java.htm
		//currently has a error from the database using the SSH Tunnel
		String db = "jdbc:postgresql://"+DBURL+":"+DBPORT+"/"+user;
		System.out.println(db);
		Connection c = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(db, user, pass);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
	}

	void getAllDevices() {
		// return all devices in the database
	}

	void updateDeviceStatus(String DeviceName, String newStatus) {
		// update a device status
	}
}
