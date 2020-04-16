package connections;

import java.sql.Timestamp;

public class Event {

	private Timestamp tp;
	private Device device;

	public Event(Device a, Timestamp tp) {
		// TODO Auto-generated constructor stub
		this.device = a;
		this.tp = tp;
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

}
