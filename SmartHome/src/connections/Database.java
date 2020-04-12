package connections;

import java.sql.*;

public class Database {

	private static String DBURL = "164.111.161.173";
	private static String DBPORT = "5432";
	private static String user = "team4";
	private static String pass = "4team4";
	private static String db = "jdbc:postgresql://" + DBURL + ":" + DBPORT + "/" + user;

	static void testConnection() throws SQLException {
		// taken from https://www.tutorialspoint.com/postgresql/postgresql_java.htm
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
		c.close();
	}
	
	//Create the connection for the application
	//close this connection when the program ends
	static Connection initConnect(){
		Connection c = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(db, user, pass);
			c.setAutoCommit(false);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
		return c;
	}

	static void getAllDevices(Connection c) throws SQLException, ClassNotFoundException {
		// return all devices in the database
		//will need to be updated when the device table is added
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM KITCHEN;");
		while (rs.next()) {
			String id = rs.getString("devices");
			int name = rs.getInt("value");
			System.out.println("NAME = " + id + ", VALUE = " + name);
		}
		rs.close();
		stmt.close();
	}

	void updateDeviceStatus(String DeviceName, boolean newStatus) {
		//update a device status
		//Will not work until the database is updated
	}
	
	static int getInternalTemp(Connection c) throws SQLException {
		return getTemp(c, "'internal_temp'");
	}
	
	static int getExternalTemp(Connection c) throws SQLException {
		return getTemp(c, "'external_temp'");
	}
	
	static int getTemp(Connection c, String name) throws SQLException {
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT value FROM TEMPERATURE WHERE name="+name+";");
		rs.next();
		int value = rs.getInt("value");
		stmt.close();
		rs.close();
		return value;
	}
	
	static void updateExternalTemp(Connection c, int Temp) throws SQLException {
		updateTemp(c, Temp, "'external_temp'");
	}
	
	static void updateInternalTemp(Connection c, int Temp) throws SQLException {
		updateTemp(c, Temp, "'internal_temp'");
	}
	
	static void updateTemp(Connection c, int Temp, String name) throws SQLException {
		Statement stmt = c.createStatement();
		stmt.executeUpdate("UPDATE temperature SET value = "+Temp+ "WHERE name="+name+";");
		c.commit();
		stmt.close();
	}
}
