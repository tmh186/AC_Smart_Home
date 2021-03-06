package connections;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class Event {

	private Timestamp tp;
	private Device device;
	private double hour;
	private boolean status;

	public Event(Device a, Timestamp tp) {
		// TODO Auto-generated constructor stub
		this.device = a;
		this.tp = tp;
	}
	
	public Event(Device d, double h, boolean s) {
		this.device = d;
		this.hour = h;
		this.status = s;
	}
	
	/**
	 * updates the day_events table with all of the events that took place and update those that exist
	 * @param stmt, linked to the connection to the database
	 * @throws SQLException
	 */
	public void updateDB(Connection c) throws SQLException {
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM day_events WHERE device_id="+ this.getDevice().getNum());
		if (rs.next()) {
			stmt.executeUpdate("UPDATE day_events SET event_time="+ this.getTp().toString()
					+ " WHERE device_id="+ this.getDevice().getNum());
		}
		else {
			stmt.executeUpdate("INSERT INTO day_events (device_id, event_time, state_change) "
					      + "VALUES("+this.getDevice().getNum()+", '"+this.getTp().toString()+"', true)");
		}
		c.commit();
		rs.close();
		stmt.close();
	}

	public Timestamp getTp() {
		return tp;
	}

	public void setTp(Timestamp tp) {
		this.tp = tp;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}
	
	public double getHour() {
		return this.hour;
	}

	@Override
	public String toString() {
		return "Event [tp=" + tp + ", device=" + device + "]";
	}

}
