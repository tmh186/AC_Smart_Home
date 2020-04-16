package simulation;

import connections.Device;

public class Event {
	
	Device device;
	double hour;
	boolean status;
	
	public Event(Device d, double h, boolean s) {
		this.device = d;
		this.hour = h;
		this.status = s;
		
	}
	//Added to help see the SimulationTest results
	public String toString(){
		return device.toString() + "->" + hour+ "h->" + status;
		
	}
}
