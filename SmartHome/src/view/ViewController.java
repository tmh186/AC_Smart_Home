package view;

import application.Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
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

public class ViewController extends Main {
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
	private Label TempTitle;
	@FXML
	private Label IndoorTempTitle;
	@FXML
	private Label OutdoorTempTitle;
	@FXML
	private Label OutdoorTempLabel; //Displays outdoor temp value
	@FXML
	private Label IndoorTempLabel; //Displays indoor temp value
	@FXML
	private Button RightPartitionButton;
	
	/* Right view -- graph */
	@FXML
	private AnchorPane RightPartition;
	@FXML
	private DatePicker DatePicker;
	@FXML
	private LineChart DashboardChart;
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
		aboutalert.setTitle(getWord("About_Dashboard"));
		aboutalert.setHeaderText(getWord("Header"));
		aboutalert.setContentText(getWord("Project_Explanation"));
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
		debugStage.setTitle(getWord("Admin_Button"));
		debugStage.setMinWidth(300);
		debugStage.setMinHeight(400);
		Label label = new Label();
		label.setText(Main.getWord("Garage"));
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
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label);
		layout.getChildren().addAll(openGarageA);
		layout.getChildren().addAll(closeGarageA);
		layout.setAlignment(Pos.CENTER);
		Scene debugScene = new Scene(layout);
		debugStage.setScene(debugScene);
		debugStage.showAndWait();
	}
}
