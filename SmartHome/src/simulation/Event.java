package simulation;

public class Event {
	
	Device device;
	double hour;
	boolean status;
	
	public Event(Device d, double h, boolean s) {
		this.device = d;
		this.hour = h;
		this.status = s;
		
	}
}
