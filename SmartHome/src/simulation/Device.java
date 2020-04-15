package simulation;

public class Device {
	
	private String room;
	private String device;
	private boolean status;
	
	public Device(String r, String d, boolean s) {
		this.room = r;
		this.device = d; 
		this.status = s;
	}
	
	
	public boolean isOff() {
		if (this.status == false) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void changeState() {
		if (this.isOff()) {
			this.status = true;
		}
		else {
			this.status = false;
		}
	}
	
	public String getRoom() {
		return this.room;
	}
	public String getDevice() {
		return this.device;
	}
	public boolean getStatus() {
		return this.status;
	}
	
	
	public void setRoom(String r) {
		this.room = r;
	}
	public void setDevice(String d) {
		this.device = d;
	}
	public void setStatus(boolean b) {
		this.status = b;
	}
}
