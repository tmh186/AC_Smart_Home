package connections;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class EventTrackingTest {

	public static void main(String[] args) throws InterruptedException, SQLException {
		ArrayList<Device> a = null;
		Connection c = Database.initConnect();
		try {
			a = Database.getAllDevices(c);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//c.close();
		if (a == null) {
			System.out.println("null device list");
			System.exit(0);
		}
		Bill curBill = new Bill(Date.valueOf("2019-4-19"), 0.00,0.00);
		EventTacking current = new EventTacking(a);
		System.out.println(a.get(1));
		System.out.println(a.get(24));
		current.turnDeviceOn(c, a.get(1));
		System.out.println(current.getEvent(0).getTp().toString());
		current.turnDeviceOn(c, a.get(24));
		//current.exportToDatabase(c);
		Thread.sleep(60000); //wait one minute
		current.turnDeviceOff(c,a.get(1), curBill.getDate());
		current.turnDeviceOff(c,a.get(24), curBill.getDate());
		System.out.println(curBill);
		System.out.println(current);//Should be empty by the end
		c.close();
	}
}
