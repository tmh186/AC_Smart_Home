package application;
	
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import connections.Bill;
import connections.Database;
import connections.Device;
import connections.EventTacking;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import threads.HvacNormal;

public class Main extends Application {
	
	public static Stage stage;
	
	private static Locale Loc_EN = new Locale("en", "US");
	private static Locale Loc_JP = new Locale("ja", "JP");
	
	static Locale LocCurrent = getLoc_EN();
	
	static ResourceBundle resEN = ResourceBundle.getBundle("application.Bundle_en_US", getLoc_EN());
	static ResourceBundle resJP = ResourceBundle.getBundle("application.Bundle_ja_JP", getLoc_JP());
	
	public static Connection mainConnection;
	public static ArrayList<Device> allDevices;
	public static ArrayList<Bill> billarchive;
	public static Bill curBill;
	public static EventTacking eventTrack;
	public static Thread havcOp;
	
	@Override
	public void start(Stage stage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			URL loc = Main.class.getResource("../view/Dashboard.fxml");
			loader.setLocation(Main.class.getResource("../view/Dashboard.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
	        stage.setTitle(getWord("Title")); //"Smart Home Dashboard"
	        stage.setScene(scene);
	        //stage.setResizable(false);
	        stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//remove all through launch to remove database component
		try {
			//initializing all the components of the application needed
			mainConnection = Database.initConnect();
			allDevices = Database.getAllDevices(mainConnection);
			billarchive = Database.getAllBills(mainConnection);
			curBill = Bill.getCurrentBill(billarchive);
			eventTrack = new EventTacking(allDevices);
			havcOp = new HvacNormal(mainConnection, allDevices.get(28));
			havcOp.start();
		} catch (ClassNotFoundException| SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		launch(args);
	}
	
	public static void setLocale(Locale l) {
		LocCurrent = l;
	}
	
	public static String getWord(String s) {
		if (LocCurrent == getLoc_EN()) {
			return resEN.getString(s);
		} else {
			return resJP.getString(s);
		}
	}

	public static Locale getLoc_JP() {
		return Loc_JP;
	}

	public static void setLoc_JP(Locale loc_JP) {
		Loc_JP = loc_JP;
	}

	public static Locale getLoc_EN() {
		return Loc_EN;
	}

	public static void setLoc_EN(Locale loc_EN) {
		Loc_EN = loc_EN;
	}
	
	public void setStageTitle(String s) {
		stage.setTitle(s);
	}
}
