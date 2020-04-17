package connections;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class Event {

	private Timestamp tp;
	private Device device;
	double h;
	boolean b;

	public Event(Device a, Timestamp tp) {
		// TODO Auto-generated constructor stub
		this.device = a;
		this.tp = tp;
	}
	
	public Event(Device device2, double hour, boolean b) {
		// TODO Auto-generated constructor stub
		this.device=device2;
		this.h = hour;
		this.b = b;
	}

	/**
	 * updates the day_events table with all of the events that took place and update those that exist
	 * @param stmt, linked to the connection to the database
	 * @throws SQLException
	 */
	public void updateDB(Statement stmt) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT * FROM day_events WHERE device_id="+ this.getDevice().getNum());
		if (rs.next()) {
			stmt.executeUpdate("UPDATE day_events SET event_time="+ this.getTp().toString()
					+ " WHERE device_id="+ this.getDevice().getNum());
		}
		else {
			stmt.executeUpdate("INSERT INTO day_events (device_id, event_time, state_change) "
					      + "VALUES("+this.getDevice().getNum()+", '"+this.getTp().toString()+"', true)");
		}
		rs.close();
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

	@Override
	public String toString() {
		return "Event [tp=" + tp + ", device=" + device + "]";
	}

	public double getHour() {
		// TODO Auto-generated method stub
		return this.h;
	}

}
