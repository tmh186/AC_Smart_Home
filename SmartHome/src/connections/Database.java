package connections;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

public class Database {

	private static String DBPORT = "5432";
	private static String DBURL = "164.111.161.173";
	private static String pass = "4team4";
	private static String user = "team4";
	private static String db = "jdbc:postgresql://" + DBURL + ":" + DBPORT + "/" + user;

	/**
	 * Add the water and elec values to the appropriate record for the date provided
	 * 
	 * @param c,     Connection to database used
	 * @param d,     Date of record
	 * @param water, value to add to the water_usage column
	 * @param elec,  value to add to the electricity_usage column
	 * @throws SQLException
	 */
	public static void addtoBillRecord(Connection c, Date d, double water, double elec) throws SQLException {
		Statement stmt = c.createStatement();
		stmt.executeUpdate("UPDATE bill_archive SET water_usage=water_usage+" + water
				+ " ,electricity_usage=electricity_usage+" + elec + "WHERE bill_archive_date='" + d.toString() + "';");
		c.commit();
	}

	public static void createCurrentBillEntry(Connection c) throws SQLException {
		String cDate = Database.getCurrentDate();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery(
				"SELECT bill_archive_date FROM bill_archive" + " WHERE bill_archive_date='" + cDate + "';");
		if (rs.next()) {
			return;
		} else {
			stmt.executeUpdate("INSERT INTO bill_archive (bill_archive_date,water_usage,electricity_usage)"
					+ "VALUES ('" + cDate + "', 0.00, 0.00);");
			c.commit();
		}
		stmt.close();
		rs.close();
	}

	/**
	 * Retrieve a ArrayList of all Bills currently in the database from bill_archive
	 * table
	 * 
	 * @param c, Connection to the database used
	 * @return ArrayList of all Bills
	 * @throws SQLException
	 */
	public static ArrayList<Bill> getAllBills(Connection c) throws SQLException {
		ArrayList<Bill> bills = new ArrayList<Bill>();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM bill_archive;");
		while (rs.next()) {
			Date date = rs.getDate("bill_archive_date");
			double water = rs.getDouble("water_usage");
			double elec = rs.getDouble("electricity_usage");
			bills.add(new Bill(date, water, elec));
		}
		rs.close();
		stmt.close();
		return bills;
	}

	/**
	 * Retrieves all Devices in the device table in the database with all the
	 * information for each sorted at the end to ensure that the devices are in the
	 * same persistent order
	 * 
	 * @param c, connection to the database to be used
	 * @return ArrayList of Device objects
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static ArrayList<Device> getAllDevices(Connection c) throws SQLException, ClassNotFoundException {
		// return all devices in the database in a list
		// this will run at launch to get all device states
		// this will also be used to get all the connected devices
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
			deviceList.add(new Device(id, name, room, elecCost, waterCost, state));
		}
		Collections.sort(deviceList);
		rs.close();
		stmt.close();
		return deviceList;
	}

	/**
	 * Retrieves a ArrayList of all the rooms in the house
	 * 
	 * @param c, connection to the database that is used
	 * @return Arraylist of all rooms in the house
	 * @throws SQLException
	 */
	static ArrayList<String> getAllRooms(Connection c) throws SQLException {
		ArrayList<String> rooms = new ArrayList<String>();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT name FROM rooms;");
		while (rs.next()) {
			rooms.add(rs.getString("name"));
		}
		return rooms;
	}

	/**
	 * This will return the bill that is already in the database for this day or it
	 * will return a new bill for this current date
	 * 
	 * @param bills , list of all current bills in the database
	 * @return the appropriate bill for the day
	 * @throws SQLException
	 */
	public static Bill getCurrentBill(ArrayList<Bill> bills) throws SQLException {
		String cDate = Database.getCurrentDate();
		for (Bill curr : bills) {
			if (curr.getDate().equals(Date.valueOf(cDate))) {
				return curr;
			}
		}
		return new Bill(Date.valueOf(cDate), 0.00, 0.00);
	}

	/**
	 * Gets the current date as a string usable by the database
	 * 
	 * @return current date as a string
	 */
	public static String getCurrentDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}

	/**
	 * Get the external temperature from the database
	 * 
	 * @param c, Connection to be used
	 * @return external temperature
	 * @throws SQLException
	 */
	public static int getExternalTemp(Connection c) throws SQLException {
		return getTemp(c, "external_temp");
	}

	/**
	 * Get the internal temperature saved to the database
	 * 
	 * @param c, Connection to be used
	 * @return current internal temperature
	 * @throws SQLException
	 */
	public static int getInternalTemp(Connection c) throws SQLException {
		return getTemp(c, "internal_temp");
	}

	/**
	 * Returns a room name string from the database based on the given number
	 * 
	 * @param c,   Connection to the database used
	 * @param num, desired room number to get
	 * @return desired room name
	 * @throws SQLException
	 */
	static String getRoom(Connection c, int num) throws SQLException {
		Statement stmt = c.createStatement();
		String name = "";
		ResultSet rs = stmt.executeQuery("SELECT name FROM rooms WHERE id=" + num + ";");
		while (rs.next()) {
			name = rs.getString("name");
		}
		return name;
	}

	/**
	 * Get the set thermostat temperature
	 * 
	 * @param c, Connection to the database to be used
	 * @return the thermostat temperature
	 * @throws SQLException
	 */
	public static int getSetTemp(Connection c) throws SQLException {
		return getTemp(c, "set_temp");
	}

	/**
	 * Return the desired temperature based on the name provided
	 * 
	 * @param c,    Connection to be used
	 * @param name, to get the appropriate temperature value
	 * @return the temperature value
	 * @throws SQLException
	 */
	public static int getTemp(Connection c, String name) throws SQLException {
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT value FROM temp WHERE name='" + name + "';");
		rs.next();
		int value = rs.getInt("value");
		stmt.close();
		rs.close();
		return value;
	}

	// Create the connection for the application
	// close this connection when the program ends
	public static Connection initConnect() {
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

	/**
	 * This was used for testing purposes to confirm a connection to the database
	 * 
	 * @throws SQLException
	 */
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

	/**
	 * This function will either update the Bill record in the database to match the
	 * local Bill file or it will update the record in the database for that day
	 * that is already existing
	 * 
	 * @param c, Connection to database used
	 * @param b, current Bill information to update to in database
	 * @throws SQLException
	 */
	public static void updateBillArchive(Connection c, Bill b) throws SQLException {
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery(
				"SELECT bill_archive_date FROM bill_archive" + " WHERE bill_archive_date='" + b.getDate() + "';");
		if (rs.next()) { // if there is a record already, update it
			stmt.executeUpdate("UPDATE bill_archive SET water_usage = " + b.getTotalWater() + ", electricity_usage="
					+ b.getTotalElec() + "  WHERE bill_archive_date='" + b.getDate() + "';");
		} else {// if there is no record then create one with these values
			stmt.executeUpdate("INSERT INTO bill_archive (bill_archive_date,water_usage,electricity_usage)"
					+ "VALUES ('" + b.getDate() + "'," + b.getTotalWater() + "," + b.getTotalElec() + ");");
		}
		c.commit();
		stmt.close();
	}

	public static void updateExternalTemp(Connection c, int Temp) throws SQLException {
		updateTemp(c, Temp, "external_temp");
	}

	public static void updateInternalTemp(Connection c, int Temp) throws SQLException {
		updateTemp(c, Temp, "internal_temp");
	}

	public static void updateSetTemp(Connection c, int Temp) throws SQLException {
		updateTemp(c, Temp, "set_temp");
	}

	/**
	 * set the appropriate temperature to the given value
	 * 
	 * @param c,    Connection to be used
	 * @param Temp, the value for the tempertaure to be updated to
	 * @param name, the temp record to be updated
	 * @throws SQLException
	 */
	public static void updateTemp(Connection c, int Temp, String name) throws SQLException {
		Statement stmt = c.createStatement();
		stmt.executeUpdate("UPDATE temp SET value = " + Temp + "WHERE name='" + name + "';");
		c.commit();
		stmt.close();
	}
	
	public static EventTacking getEventTracking(Connection c, ArrayList<Device> a) throws SQLException {
		EventTacking curr = new EventTacking(a);
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM day_events;");
		while (rs.next()) { // if there is a record already, update it
			int id = rs.getInt("device_id");
			curr.EventTracking.add(new Event(a.get(id+1),rs.getTimestamp("event_time")));
		}
		return curr;
	}
	
	public static void removeEventDB(Connection c, int deviceid) throws SQLException {
		Statement stmt = c.createStatement();
		stmt.executeUpdate("DELETE FROM day_events WHERE device_id="+deviceid+";");
		c.commit();
		stmt.close();
	}

	/**
	 * This will update a device status in the database
	 * 
	 * @param c,         Connection to database used
	 * @param id,        id of the device to be updated in device table
	 * @param newStatus, the status to apply on the device in the table
	 * @throws SQLException
	 */
	public static void updateDeviceStatus(Connection c, int id, boolean newStatus) throws SQLException {
		// update a device status
		Statement stmt = c.createStatement();
		stmt.executeUpdate("UPDATE device SET state = " + newStatus + " WHERE devices_id=" + id + ";");
		c.commit();
		stmt.close();
	}
}
