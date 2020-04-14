package view;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import application.Main;
import connections.Weather;

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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ViewController extends Main {
	
	@FXML
	public AnchorPane BaseAnchorPane;
	@FXML
	public MenuBar DashboardMenuBar;
	@FXML
	public Menu FileMenuOption;
	@FXML
	public MenuItem ExitOption;
	@FXML
	public Menu EditMenuOption;
	@FXML
	public MenuItem DebugOption;
	@FXML
	public Menu HelpMenuOption;
	@FXML
	public MenuItem AboutOption;
	@FXML
	public Menu LanguageMenuOption;
	@FXML
	public MenuItem EnglishOption;
	@FXML
	public MenuItem JapaneseOption;
	@FXML
	public SplitPane BaseSplitPane;
	
	/* Left View -- Default Home layout*/
	@FXML
	public AnchorPane LeftPartition;
	@FXML
	public ImageView FloorPlanImageView;
	@FXML
	public ImageView GarageDoorAopen;
	@FXML
	public ImageView GarageDoorAclosed;
	@FXML
	public Pane tempPane;
	@FXML
	public Label TempTitle;
	@FXML
	public Label IndoorTempTitle;
	@FXML
	public Label OutdoorTempTitle;
	@FXML
	public Label OutdoorTempLabel; //Displays current outdoor temp value
	@FXML
	public Label IndoorTempLabel; //Displays current indoor temp value
	@FXML
	public Pane energyPane;
	@FXML
	public Label EnergyUsageTitle;
	@FXML
	public ImageView energyicon;
	@FXML
	public Label EnergyLabel; //Displays current energy 
	@FXML
	public Pane theromstatPane;
	@FXML
	public Slider thermostatSlider;
	@FXML
	public Button RightPartitionButton;
	@FXML
	public Label LightKeyLabel;
	@FXML
	public Label LightOnLabel;
	@FXML
	public Label LightOffLabel;
	
	/* Right view -- graph */
	@FXML
	public AnchorPane RightPartition;
	@FXML
	public DatePicker DatePicker;
	@FXML
	final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
	final LineChart<String,Number> DashboardChart = new LineChart<String,Number>(xAxis,yAxis);
	@FXML
	public ButtonBar GraphSettingsButtons;
		@FXML
		public Button DayButton;
		@FXML
		public Button WeekButton;
		@FXML
		public Button MonthButton;
		@FXML
		public Button LifetimeButton;
	@FXML
	public Button BackButton;

	public Stage ControllerStage;
	
	/* Event handling */
		
	/*
	 * Language
	 */
	
	public void handleJapaneseOptionClick(ActionEvent e) throws InterruptedException {
		//changes all writen text on UI to Japanese
		setLocale(getLoc_JP());
		//setStageTitle(getWord("Title")); //null pointer
		
		FileMenuOption.setText(getWord("FileMenuOption"));
		ExitOption.setText(getWord("ExitOption"));
		EditMenuOption.setText(getWord("EditMenuOption"));
		DebugOption.setText(getWord("DebugOption"));
		HelpMenuOption.setText(getWord("HelpMenuOption"));
		AboutOption.setText(getWord("AboutOption"));
		LanguageMenuOption.setText(getWord("LanguageMenuOption"));
		EnglishOption.setText(getWord("EnglishOption"));
		JapaneseOption.setText(getWord("JapaneseOption"));
		
		EnergyUsageTitle.setText(getWord("EnergyUsageTitle"));
		TempTitle.setText(getWord("TempTitle"));
		IndoorTempTitle.setText(getWord("IndoorTempTitle"));
		OutdoorTempTitle.setText(getWord("OutdoorTempTitle"));
		LightKeyLabel.setText(getWord("LightKeyLabel"));
		LightOnLabel.setText(getWord("LightOnLabel"));
		LightOffLabel.setText(getWord("LightOffLabel"));
		RightPartitionButton.setText(getWord("RightPartitionButton"));
		BackButton.setText(getWord("BackButton"));
		
		DayButton.setText(getWord("DayButton"));
		WeekButton.setText(getWord("WeekButton"));
		MonthButton.setText(getWord("MonthButton"));
		LifetimeButton.setText(getWord("LifetimeButton"));
	}
	
	public void handleEnglishOptionClick(ActionEvent e) throws InterruptedException {
		//changes all writen text on UI to English
		setLocale(getLoc_EN());
		//setStageTitle(getWord("Title")); //null pointer
		
		FileMenuOption.setText(getWord("FileMenuOption"));
		ExitOption.setText(getWord("ExitOption"));
		EditMenuOption.setText(getWord("EditMenuOption"));
		DebugOption.setText(getWord("DebugOption"));
		HelpMenuOption.setText(getWord("HelpMenuOption"));
		AboutOption.setText(getWord("AboutOption"));
		LanguageMenuOption.setText(getWord("LanguageMenuOption"));
		EnglishOption.setText(getWord("EnglishOption"));
		JapaneseOption.setText(getWord("JapaneseOption"));
		
		EnergyUsageTitle.setText(getWord("EnergyUsageTitle"));
		TempTitle.setText(getWord("TempTitle"));
		IndoorTempTitle.setText(getWord("IndoorTempTitle"));
		OutdoorTempTitle.setText(getWord("OutdoorTempTitle"));
		LightKeyLabel.setText(getWord("LightKeyLabel"));
		LightOnLabel.setText(getWord("LightOnLabel"));
		LightOffLabel.setText(getWord("LightOffLabel"));
		RightPartitionButton.setText(getWord("RightPartitionButton"));
		BackButton.setText(getWord("BackButton"));
		
		DayButton.setText(getWord("DayButton"));
		WeekButton.setText(getWord("WeekButton"));
		MonthButton.setText(getWord("MonthButton"));
		LifetimeButton.setText(getWord("LifetimeButton"));
	}
	
	/*
	 * Help > About
	 */
	@FXML
	public void handleAboutOptionClick(ActionEvent e) throws InterruptedException {
		Alert aboutalert = new Alert(AlertType.INFORMATION);
		aboutalert.setTitle(getWord("AboutDashboard"));
		aboutalert.setHeaderText(getWord("AboutHeader"));
		aboutalert.setContentText(getWord("ProjectExplanation"));
		aboutalert.show();
		
	}
	
	/*
	 * File > Exit
	 */
	@FXML
	public void handleExit(ActionEvent e) throws InterruptedException {
		System.exit(0);
	}
	
	/*
	 * Handle switching back and forth between screens 
	 */
	@FXML
	public void handleGoToGraphButton(ActionEvent e) throws InterruptedException {
			BaseSplitPane.setDividerPosition(0, 0.0);
	}
	@FXML
	public void handleGoBackButton(ActionEvent e) throws InterruptedException {
			BaseSplitPane.setDividerPosition(0, 1.0);
	}
	
	/* 
	 * Debug window to make manual changes
	 */
	@FXML
	public void handleDebugOptionClick(ActionEvent ex) throws InterruptedException {		
		Stage debugStage = new Stage();
		debugStage.initModality(Modality.APPLICATION_MODAL);
		debugStage.setTitle(getWord("DebugOption"));
		debugStage.setMinWidth(300);
		debugStage.setMinHeight(400);
		Label label = new Label();
		label.setText(getWord("Garage"));
		Button openGarageA = new Button();
		openGarageA.setText(getWord("OpenGarageA"));
		Button closeGarageA = new Button();
		closeGarageA.setText(getWord("CloseGarageA"));
		
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
	 * Thermostat slider event handler
	 */
	@FXML
	public void handleThermostatChange(MouseEvent e) throws InterruptedException {
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
    }
	
	
}
