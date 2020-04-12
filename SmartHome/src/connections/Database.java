package connections;

import java.sql.*;

public class Database {

	private static String DBURL = "164.111.161.173";
	private static String DBPORT = "5432";
	private static String user = "team4";
	private static String pass = "4team4";

	static void testConnection() throws SQLException {
		//taken from https://www.tutorialspoint.com/postgresql/postgresql_java.htm
		//currently has a error from the database using the SSH Tunnel
		String db = "jdbc:postgresql://"+DBURL+":"+DBPORT+"/"+user;
		//System.out.println(db);
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

	static void getAllDevices() throws SQLException, ClassNotFoundException {
		// return all devices in the database
		Connection c = null;
		String db = "jdbc:postgresql://"+DBURL+":"+DBPORT+"/"+user;
	      Statement stmt = null;
	         Class.forName("org.postgresql.Driver");
	         try {
	        	 c = DriverManager.getConnection(db, user, pass);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");

	         stmt = c.createStatement();
	         ResultSet rs = stmt.executeQuery( "SELECT * FROM KITCHEN;" );
	         while ( rs.next() ) {
	             String id = rs.getString("devices");
	             int name = rs.getInt("value");
	             System.out.println("NAME = "+ id + ", VALUE = " + name);
	         }
	         rs.close();
	         stmt.close();
	         c.close();
	}

	void updateDeviceStatus(String DeviceName, String newStatus) {
		// update a device status
	}
}
