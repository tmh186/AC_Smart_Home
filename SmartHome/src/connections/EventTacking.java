package connections;

import java.sql.Connection;
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
	
	public void exportToDatabase(Connection c) throws SQLException {
		Statement stmt = c.createStatement();
		for (Event e : EventTracking) {
			e.updateDB(stmt);
		}
		c.commit();
		stmt.close();
	}
	
	public Event getEvent(Device curDevice) {
		for(int i = 0; i < EventTracking.size(); i++) {
			if (EventTracking.get(i).getDevice().getName().equals(curDevice.getName())) {
				return EventTracking.get(i);
			}
		}
		return null;
	}
	
	public Event getEvent(int i) {
		return EventTracking.get(i);
	}
	
	public void turnDeviceOn(Device curDevice) {
		if (curDevice.isState()) {
			return;
		}
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		curDevice.changeState();
		EventTracking.add(new Event(curDevice, timestamp));
	}
	
	public void turnDeviceOff(Device curDevice, Bill currentBill) {
		if (curDevice.isOff()) {
			return;
		}
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		curDevice.changeState();
		Event a = getEvent(curDevice);
		double time = (timestamp.getTime() - a.getTp().getTime()) /60000;
		currentBill.addElec(time * curDevice.getElecCost());
		//Debug statement
		//System.out.println(a.getDevice().getName() + "Minutes: "+time + "TotalElec "+time * curDevice.getElecCost());
		currentBill.addWater(time * curDevice.getWaterCost());
		EventTracking.remove(a);
	}

	@Override
	public String toString() {
		return "EventTracking=[" + EventTracking + "]";
	}
	
}
