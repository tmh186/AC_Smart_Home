package view;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import application.Main;
import connections.Weather;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ViewController {
	@FXML
	private AnchorPane BaseAnchorPane;
	@FXML
	private MenuBar DashboardMenuBar;
	@FXML
	private Menu FileMenuOption;
		@FXML
		private MenuItem ExitOption;
	@FXML
	private Menu EditMenuOption;
		@FXML
		private MenuItem DebugOption;
	@FXML
	private Menu HelpMenuOption;
		@FXML
		private MenuItem AboutOption;
	@FXML
	private SplitPane BaseSplitPane;
	
	/* Left View -- Default Home layout*/
	@FXML
	private AnchorPane LeftPartition;
	@FXML
	private ImageView FloorPlanImageView;
	@FXML
	private ImageView GarageDoorAopen;
	@FXML
	private ImageView GarageDoorAclosed;
	@FXML
	private Pane tempPane;
	@FXML
	private Label TempTitle;
	@FXML
	private Label IndoorTempTitle;
	@FXML
	private Label OutdoorTempTitle;
	@FXML
	private Label OutdoorTempLabel; //Displays current outdoor temp value
	@FXML
	private Label IndoorTempLabel; //Displays current indoor temp value
	@FXML
	private Pane energyPane;
	@FXML
	private Label EnergyUsageTitle;
	@FXML
	private ImageView energyicon;
	@FXML
	private Label EnergyLabel; //Displays current energy 
	@FXML
	private Button RightPartitionButton;
	
	/* Right view -- graph */
	@FXML
	private AnchorPane RightPartition;
	@FXML
	private DatePicker DatePicker;
	@FXML
	final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
	final LineChart<String,Number> DashboardChart = new LineChart<String,Number>(xAxis,yAxis);
	@FXML
	private ButtonBar GraphSettingsButtons;
		@FXML
		private Button DayButton;
		@FXML
		private Button WeekButton;
		@FXML
		private Button MonthButton;
		@FXML
		private Button LifetimeButton;
	@FXML
	private Button BackButton;

	
	/* Event handling */
		
	/*
	 * Help > About
	 */
	@FXML
	private void handleAboutOptionClick(ActionEvent e) throws InterruptedException {
		Alert aboutalert = new Alert(AlertType.INFORMATION);
		aboutalert.setTitle("About Smart Home Dashboard");
		aboutalert.setHeaderText("Created by CS499 Team 4");
		aboutalert.setContentText("A dashboard for your smart home.");
		aboutalert.show();
		
	}
	
	/*
	 * File > Exit
	 */
	@FXML
	private void handleExit(ActionEvent e) throws InterruptedException {
		System.exit(0);
	}
	
	@FXML
	private void handleChangeUI(ActionEvent e) throws InterruptedException {
		
		RightPartitionButton.setOnAction(x -> {
			BaseSplitPane.setDividerPosition(0, 0.0);
		});
		BackButton.setOnAction(x -> {
			BaseSplitPane.setDividerPosition(0, 1.0);
		});
	}	
	/* 
	 * Debug window to make manual changes
	 */
	@FXML
	private void handleDebugOptionClick(ActionEvent ex) throws InterruptedException {		
		Stage debugStage = new Stage();
		debugStage.initModality(Modality.APPLICATION_MODAL);
		debugStage.setTitle("Debug Menu");
		debugStage.setMinWidth(300);
		debugStage.setMinHeight(400);
		Label label = new Label();
		label.setText("Garage");
		Button openGarageA = new Button();
		openGarageA.setText("Open garage a");
		Button closeGarageA = new Button();
		closeGarageA.setText("close garage a");
		
		//eventually should be a toggle
		// GarageDoor.setVisible(DetermineStateOfDoor())
		openGarageA.setOnAction(e -> { 
			GarageDoorAopen.setVisible(true);
			GarageDoorAclosed.setVisible(false);
		});
		
		closeGarageA.setOnAction(e -> { 
			GarageDoorAopen.setVisible(false);
			GarageDoorAclosed.setVisible(true);
		});
		//-- End of button actions--
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label);
		layout.getChildren().addAll(openGarageA);
		layout.getChildren().addAll(closeGarageA);
		layout.setAlignment(Pos.CENTER);
		Scene debugScene = new Scene(layout);
		debugStage.setScene(debugScene);
		debugStage.showAndWait();
	}
	
	/*
	 * Put things here you'd like to happen before UI is shown to user
	 */
    @FXML
    public void initialize() {
    	//Left partition is default screen, move split pane out of way
    	BaseSplitPane.setDividerPosition(0, 1.0);
    	
    	//Update outdoor temp label
    	String outdoortemp = String.valueOf(Weather.getCurrentWeather());
    	OutdoorTempLabel.setText(outdoortemp);
    }
	
	
}
