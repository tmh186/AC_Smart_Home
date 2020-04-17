package threads;

import java.sql.Connection;
import java.sql.Date;

import connections.Database;
import connections.Device;

public class HvacNormal extends Thread {

	Connection mainC;
	Device hvac;
	boolean running;

	public HvacNormal(Connection c, Device hvac) {
		this.mainC = c;
		this.hvac = hvac;
	}
	/**
	 * This function runs a thread in the background to simulate the hvac for the home
	 */
	public void run() {
		try {
			for (;;) {
				double setTemp = Database.getSetTemp(mainC);
				double internalTemp = Database.getInternalTemp(mainC);
				if (setTemp - internalTemp > 2) {
					running = true;
					Database.updateInternalTemp(mainC, (int) internalTemp + 1);
					//ViewController.IndoorTempLabel.setText((int) internalTemp + 1+"°F");
					Thread.sleep(60000);
					Database.addtoBillRecord(mainC, Date.valueOf(Database.getCurrentDate()), hvac.getWaterCost(), hvac.getElecCost());
				}
				else if (internalTemp - setTemp > 2) {
					running = true;
					Database.updateInternalTemp(mainC, (int) internalTemp - 1);
					Thread.sleep(60000);
					Database.addtoBillRecord(mainC, Date.valueOf(Database.getCurrentDate()), hvac.getWaterCost(), hvac.getElecCost());
				}
				else {
					running = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
