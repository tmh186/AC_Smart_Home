package connections;

public class Device implements Comparable< Device >{
	
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
	//migrated for use in the simulation package
	public Device(String string, String string2, boolean b) {
		// TODO Auto-generated constructor stub
		this.room = string;
		this.name = string2;
		this.state = b;
	}
	
<<<<<<< HEAD

=======
	/**
	 * Determines if a device is turned off
	 * @return whether or not a device is turned off
	 */
>>>>>>> 5c9d915f7e156f1a1e0177b82c1c94a0551e1329
	public boolean isOff() {
		if (this.state == false) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * changes a device state from on to off and vice versa
	 */
	public void changeState() {
		if (this.isOff()) {
			this.state = true;
		}
		else {
			this.state = false;
		}
	}
	
	
	//basic get and set methods as well as the toString methods
	public boolean isState() {
		return state;
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
	
	@Override
	public String toString() {
		if (this.room.equals("") ) {
			return this.name;
		}
		return this.room + " " + this.name;
	}
	
	@Override
	public int compareTo(Device o) {
		// TODO Auto-generated method stub
		return this.getNum() - o.getNum();
	}
	

}
