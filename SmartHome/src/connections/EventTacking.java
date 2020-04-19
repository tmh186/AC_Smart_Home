package connections;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

public class EventTacking {
	
	ArrayList<Device> DeviceList;
	ArrayList<Event> EventTracking;

	public EventTacking(ArrayList<Device> devices) {
		// TODO Auto-generated constructor stub
		DeviceList = devices;
		EventTracking = new ArrayList<Event>();
	}
	/**
	 * Export all logged events to the database defined by Event.updateDB
	 * @param c, Connection to be used
	 * @throws SQLException
	 */
	public void exportToDatabase(Connection c) throws SQLException {
		for (Event e : EventTracking) {
			e.updateDB(c);
		}
		c.commit();
	}
	
	/**
	 * retrieve the event record of the device provided
	 * @param curDevice, device provided
	 * @return  event record
	 */
	public Event getEvent(Device curDevice) {
		for(int i = 0; i < EventTracking.size(); i++) {
			if (EventTracking.get(i).getDevice().getName().equals(curDevice.getName())) {
				return EventTracking.get(i);
			}
		}
		return null;
	}
	
	/**
	 * retrieve event record at the i position
	 * @param i, position of record
	 * @return event record
	 */
	public Event getEvent(int i) {
		return EventTracking.get(i);
	}
	
	/**
	 * Turns a device on and then creates a timestamp for the action and updates it
	 * @param curDevice , device provided
	 */
	public void turnDeviceOn(Connection c, Device curDevice) {
		if (curDevice.isState()) {
			return;
		}
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Event cur = new Event(curDevice, timestamp);
		curDevice.changeState();
		try {
			Database.updateDeviceStatus(c, curDevice.getNum(), true);
			cur.updateDB(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		EventTracking.add(cur);
	}
	
	/**
	 * Turns device off and then updates the currentBill to reflect the resources used
	 * after calculating the time used
	 * @param curDevice, Device to be turned off
	 * @param currentBill, Currentbill record inside the application
	 */
	public void turnDeviceOff(Connection c, Device curDevice, Date d) {
		if (curDevice.isOff()) {
			return;
		}
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		curDevice.changeState();
		Event a = getEvent(curDevice);
		double time = (double)(timestamp.getTime() - a.getTp().getTime()) /60000;
		double elec = time * curDevice.getElecCost();
		double water = time * curDevice.getWaterCost();
		System.out.println("Elec: "+ elec + " Water: "+water);
		try {
			Database.addtoBillRecord(c, d, water, elec);
			Database.updateDeviceStatus(c, curDevice.getNum(), false);
			Database.removeEventDB(c, curDevice.getNum());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		EventTracking.remove(a);
	}

	@Override
	public String toString() {
		return "EventTracking=[" + EventTracking + "]";
	}
	
}
