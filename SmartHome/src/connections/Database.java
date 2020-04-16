package connections;

import java.sql.*;
import java.util.ArrayList;

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
	public static Connection initConnect(){
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
	
	static String getRoom(Connection c,int num) throws SQLException {
		Statement stmt = c.createStatement();
		String name = "";
		ResultSet rs = stmt.executeQuery("SELECT name FROM rooms WHERE id="+num +";");
		while (rs.next()) {
			name = rs.getString("name");
		}
		return name;
	}
	
	static void updateBillArchive(Connection c, Bill b) throws SQLException {
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT bill_archive_date FROM bill_archive"
				+ " WHERE bill_archive_date='"+ b.getDate()+"';");
		if(rs.next()) {
			stmt.executeUpdate("UPDATE bill_archive SET water_usage = "+b.getTotalWater()+ 
					", electricity_usage="+ b.getTotalElec()+
					"  WHERE bill_archive_date='"+b.getDate()+"';");
		}
		else {
			stmt.executeUpdate("INSERT INTO bill_archive (bill_archive_date,water_usage,electricity_usage)"
					+ "VALUES ('" +b.getDate()+"',"+b.getTotalWater()+","+b.getTotalElec() +");");
		}
		c.commit();
		stmt.close();
	}
	
	public static ArrayList<Bill> getAllBills(Connection c) throws SQLException{
		ArrayList<Bill> bills = new ArrayList<Bill>();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM bill_archive;");
		while (rs.next()) {
			Date date = rs.getDate("bill_archive_date");
			double water = rs.getDouble("water_usage");
			double elec = rs.getDouble("electricity_usage");
			bills.add(new Bill(date,water,elec));
		}
		rs.close();
		stmt.close();
		return bills;
	}
	
	public static ArrayList<Device> getAllDevices(Connection c) throws SQLException, ClassNotFoundException {
		// return all devices in the database in a list
		//this will run at launch to get all device states
		//this will also be used to get all the connected devices
		ArrayList<Device> deviceList = new ArrayList<Device>();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM device;");
		while (rs.next()) {
			int id = rs.getInt("devices_id");
			String name = rs.getString("device_name").trim();
			String room = getRoom(c, rs.getInt("device_room"));
			float elecCost = rs.getFloat("cost_per_min_electricity");
			float waterCost = rs.getFloat("cost_per_min_water");
			boolean state = rs.getBoolean("state");
			deviceList.add(new Device(id,name,room,elecCost,waterCost, state));
		}
		rs.close();
		stmt.close();
		return deviceList;
	}

	public void updateDeviceStatus(Connection c, int id, boolean newStatus) throws SQLException {
		//update a device status
		Statement stmt = c.createStatement();
		stmt.executeUpdate("UPDATE devices SET state = "+newStatus+ " WHERE devices_id="+id+";");
		c.commit();
		stmt.close();
	}
	
	public static int getSetTemp(Connection c) throws SQLException {
		return getTemp(c, "set_temp");
	}
	
	public static int getInternalTemp(Connection c) throws SQLException {
		return getTemp(c, "internal_temp");
	}
	
	public static int getExternalTemp(Connection c) throws SQLException {
		return getTemp(c, "external_temp");
	}
	
	public static int getTemp(Connection c, String name) throws SQLException {
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT value FROM temp WHERE name='"+name+"';");
		rs.next();
		int value = rs.getInt("value");
		stmt.close();
		rs.close();
		return value;
	}
	
	public static void updateSetTemp(Connection c, int Temp) throws SQLException {
		updateTemp(c, Temp, "set_temp");
	}
	
	public static void updateExternalTemp(Connection c, int Temp) throws SQLException {
		updateTemp(c, Temp, "external_temp");
	}
	
	public static void updateInternalTemp(Connection c, int Temp) throws SQLException {
		updateTemp(c, Temp, "internal_temp");
	}
	
	public static void updateTemp(Connection c, int Temp, String name) throws SQLException {
		Statement stmt = c.createStatement();
		stmt.executeUpdate("UPDATE temp SET value = "+Temp+ "WHERE name='"+name+"';");
		c.commit();
		stmt.close();
	}
}
