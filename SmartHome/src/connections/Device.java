package connections;

public class Device {
	
	private int num;
	private String name;
	private String room;
	private float elecCost;
	private float waterCost;
	private boolean state;
	
	
	public Device(int num, String name, String room, float elecCost, float waterCost, boolean state) {
		// TODO Auto-generated constructor stub
		this.num = num;
		this.name = name;
		this.room = room;
		this.elecCost = elecCost;
		this.waterCost = waterCost;
		this.state = state;
	}
	//basic get and set methods as well as the toString methods
	
	public boolean isState() {
		return state;
	}
	@Override
	public String toString() {
		return "Device [num=" + num + ", name=" + name + ", room=" + room + ", elecCost=" + elecCost + ", waterCost="
				+ waterCost + ", state=" + state + "]";
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getRoom() {
		return room;
	}


	public void setRoom(String room) {
		this.room = room;
	}


	public float getElecCost() {
		return elecCost;
	}


	public void setElecCost(float elecCost) {
		this.elecCost = elecCost;
	}


	public float getWaterCost() {
		return waterCost;
	}


	public void setWaterCost(float waterCost) {
		this.waterCost = waterCost;
	}
	
	

}
