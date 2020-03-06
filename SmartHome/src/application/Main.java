package application;
	
import java.net.URL;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	
	public static Stage stage;
	
	@Override
	public void start(Stage stage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			URL loc = Main.class.getResource("../view/Dashboard.fxml");
			loader.setLocation(Main.class.getResource("../view/Dashboard.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
	        stage.setTitle("Smart Home Dashboard");
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
}
