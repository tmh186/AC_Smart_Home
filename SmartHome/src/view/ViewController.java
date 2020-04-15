package view;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import application.Main;
import connections.Weather;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
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
	private Pane theromstatPane;
	@FXML
	private Slider thermostatSlider;
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
	
	/*
	 * Handle switching back and forth between screens 
	 */
	@FXML
	private void handleGoToGraphButton(ActionEvent e) throws InterruptedException {
			BaseSplitPane.setDividerPosition(0, 0.0);
	}
	@FXML
	private void handleGoBackButton(ActionEvent e) throws InterruptedException {
			BaseSplitPane.setDividerPosition(0, 1.0);
	}
	
	/* 
	 * Debug window to make manual changes
	 */
	@FXML
	private void handleDebugOptionClick(ActionEvent ex) throws InterruptedException {	             
		    try {
		        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DebugWindow.fxml"));
		        Parent root1 = (Parent) fxmlLoader.load();
		        Stage stage = new Stage();
		        stage.setScene(new Scene(root1));  
		        stage.show();
		    } catch(Exception e) {
		        e.printStackTrace();
		    }
		
	}
	
	/*
	 * Thermostat slider event handler
	 */
	@FXML
	private void handleThermostatChange(MouseEvent e) throws InterruptedException {
		Double thermostatTemp = thermostatSlider.getValue();
		
		//Updating the indoor temp label for testing purposes
		String thermostatTempUI = String.valueOf(thermostatTemp);
		IndoorTempLabel.setText(thermostatTempUI + "°F");
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
    	OutdoorTempLabel.setText(outdoortemp + "°F");
    	
    	//Update indoor temp label
    	IndoorTempLabel.setText("00.00" + "°F");
    	
    	//Thermostat and indoor temp are initially the same?
    	//thermostatTemp = getIndoorTemp
    	
    	//TODO Start database connection here
    }
	
	
}
