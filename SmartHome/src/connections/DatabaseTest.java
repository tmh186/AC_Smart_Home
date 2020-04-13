package connections;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		//must be connected through the VPN for this to work or will error
		Connection c = Database.initConnect();
		Database.getAllDevices(c);
		System.out.println("Internal Temp: "+Database.getInternalTemp(c));
		Database.updateInternalTemp(c, 90);
		System.out.println("Internal Temp: "+Database.getInternalTemp(c));
		Database.updateInternalTemp(c, 0);
		System.out.println("External Temp: "+Database.getExternalTemp(c));
		c.close();
	}

}
