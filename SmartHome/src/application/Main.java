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
	
	static Locale Loc_EN = new Locale("en", "US");
	static Locale Loc_JP = new Locale("ja", "JP");
	
	static Locale LocCurrent = Loc_JP;
	
	static ResourceBundle resEN = ResourceBundle.getBundle("application.Bundle_en_US", Loc_EN);
	static ResourceBundle resJP = ResourceBundle.getBundle("application.Bundle_ja_JP", Loc_JP);
	
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
	        stage.setResizable(false);
	        stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void swapLocale() {
		if (LocCurrent == Loc_EN) {
			LocCurrent = Loc_JP;
		} else {
			LocCurrent = Loc_EN;
		}
	}
	public static String getWord(String s) {
		if (LocCurrent == Loc_EN) {
			return resEN.getString(s);
		} else {
			return resJP.getString(s);
		}
	}
	
}
