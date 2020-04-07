	
	import javax.swing.JFrame;
	import javax.swing.JPanel;
	import javax.swing.JTabbedPane;
	 
	@SuppressWarnings("serial") // because the yellow line in eclipse looks terrible
	
	public class UI extends JFrame 
	
	{
	     private static JTabbedPane sht;
	     private static JPanel home;
	     private static JPanel util;
	     private static JPanel admin;
	     
	    public UI() 
	    {
	    	// this method is going to build out the tabs and the title of the UI that is implemented in LoginUI
	    	
	        setTitle("Team 4 Smart Home");
	        sht = new JTabbedPane();
	        getContentPane().add(sht);
	        home = new JPanel();
	        util = new JPanel();
	        admin = new JPanel();
	        sht.addTab("Home", home);
	        sht.addTab("Utilities", util);
	        sht.addTab("Admin", admin);
	    }
	}