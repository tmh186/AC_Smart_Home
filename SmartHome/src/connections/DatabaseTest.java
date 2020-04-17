package connections;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		//must be connected through the VPN for this to work or will error
		Connection c = Database.initConnect();
		ArrayList<Device> a = Database.getAllDevices(c);
		System.out.println(a);
		System.out.println("Internal Temp: "+Database.getInternalTemp(c));
		Database.updateInternalTemp(c, 90);
		System.out.println("Internal Temp: "+Database.getInternalTemp(c));
		Database.updateInternalTemp(c, 0);
		System.out.println("External Temp: "+Database.getExternalTemp(c));
		System.out.println(Database.getAllBills(c));
		System.out.println(Database.getAllRooms(c));
		//Bill b = new Bill(Date.valueOf("2019-4-19"), 12.9, 12);
		//Database.updateBillArchive(c, b);
		//System.out.println(Database.getAllBills(c));
		c.close();
	}

}
