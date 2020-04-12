package connections;

import java.sql.SQLException;

public class DatabaseTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		//must be connected through the VPN for this to work or will error
		Database.getAllDevices();
	}

}
