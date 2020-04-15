package application;
	
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static Stage stage;
	
	private static Locale Loc_EN = new Locale("en", "US");
	private static Locale Loc_JP = new Locale("ja", "JP");
	
	static Locale LocCurrent = getLoc_EN();
	
	static ResourceBundle resEN = ResourceBundle.getBundle("application.Bundle_en_US", getLoc_EN());
	static ResourceBundle resJP = ResourceBundle.getBundle("application.Bundle_ja_JP", getLoc_JP());
	
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
