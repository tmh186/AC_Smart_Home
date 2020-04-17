package view;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.swing.text.DateFormatter;

import com.sun.media.jfxmedia.events.NewFrameEvent;
import application.Date;
import application.Main;
import connections.Database;
import connections.Weather;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
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
	/*
	 * Doors
	 * door open: set imageurl = ..\opendoor.png
	 * door closed: set imagelocation = ..\closeddoor.png
	 */
	@FXML
	public ImageView garage1door;
	@FXML
	public ImageView garage2door;
	@FXML
	public ImageView garage3door;
	@FXML
	public ImageView frontdoor;
	@FXML
	public ImageView masterbedroomdoor;
	@FXML
	public ImageView halfbathdoor;
	@FXML
	public ImageView br1door;
	@FXML
	public ImageView br2door;
	@FXML
	public ImageView laundrydoor;
	@FXML
	public ImageView backdoor;
	/*
	 * Lights
	 * on: setVisibility(true)
	 * off: setVisibility(false)
	 */
	@FXML
	public ImageView br1lampA;
	@FXML
	public ImageView br1lampB;
	@FXML
	public ImageView br1overheadlamp;
	@FXML
	public ImageView br2lampA;
	@FXML
	public ImageView br2lampB;
	@FXML
	public ImageView br2overheadlamp;
	@FXML
	public ImageView kitchenoverheadlamp;
	@FXML
	public ImageView livingroomlampA;
	@FXML
	public ImageView livingroomoverheadlamp;
	@FXML
	public ImageView livingroomlampB;
	@FXML
	public ImageView masterbedroomlampA;
	@FXML
	public ImageView masterbedroomlampB;
	@FXML
	public ImageView masterbedroomoverheadlamp;
	@FXML
	public ImageView masterbathoverheadlamp;
	@FXML
	public ImageView halfbathoverheadlamp;
	/*
	 * Appliances
	 * on: set imageurl = ..\running.png
	 * off: set imageurl = ..\stopped.png
	 */
	@FXML
	public ImageView dishwasher;
	@FXML
	public ImageView microwave;
	@FXML
	public ImageView stove;
	@FXML
	public ImageView fridge;
	@FXML
	public ImageView livingroomtv;
	@FXML
	public ImageView masterbrtv;
	@FXML
	public ImageView halfbathfan;
	@FXML
	public ImageView washer;
	@FXML
	public ImageView dryer;
	@FXML
	public ImageView masterbathfan;
/*
 * End of floorplan
 */
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
	@FXML
	public Label DoorOpenLabel;
	@FXML
	public Label DoorClosedLabel;
	@FXML
	public Label ThermoSliderLabel;
	@FXML
	public Label generateDataLabel;
	@FXML
	public Label TotalLabel;
	@FXML
	public Label ElectricityLabel;
	@FXML
	public Label WaterLabel;
	@FXML
	public Label LegendLabel;
	
	/* Right view -- graph */
	@FXML
	public AnchorPane RightPartition;
	@FXML
	public DatePicker DatePicker;
	@FXML
	public CategoryAxis xAxis = new CategoryAxis();
    public NumberAxis yAxis = new NumberAxis();
	public LineChart<String,Number> DashboardChart;
	final int MaxGraphSize = 180;
	//Bills
	XYChart.Series Water = new XYChart.Series();
	XYChart.Series Electricity = new XYChart.Series();
	XYChart.Series Total = new XYChart.Series();
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
	public Button ClearButton;
	@FXML
	public Button BackButton;
	@FXML
	public Label TimeFrameLable;
	@FXML
	public DialogPane ElectricDialog;
	@FXML
	public DialogPane WaterDialog;
	@FXML
	public DialogPane TotalDialog;
	
	//Data Variables
	public Queue<Date> DayStorage = new LinkedList<>(); //what will show up on the graph
	public boolean DayStorageFull = false;
	public String currentDate;
	public int tempDay;
	public int tempMonth;
	public int tempYear;
	@FXML
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
		ThermoSliderLabel.setText(getWord("ThermoSliderLabel"));
		
		DayButton.setText(getWord("DayButton"));
		WeekButton.setText(getWord("WeekButton"));
		MonthButton.setText(getWord("MonthButton"));
		LifetimeButton.setText(getWord("LifetimeButton"));
		ClearButton.setText(getWord("ClearButton"));
		
		generateDataLabel.setText(getWord("generateDataLabel"));
		TotalLabel.setText(getWord("TotalLabel"));
		ElectricityLabel.setText(getWord("ElectricityLabel"));
		WaterLabel.setText(getWord("WaterLabel"));
		LegendLabel.setText(getWord("LegendLabel"));
		DashboardChart.setTitle("GraphTitle");
		xAxis.setLabel(getWord("xAxisLabel"));
    	yAxis.setLabel(getWord("yAxisLabel"));
    	Water.setName(getWord("Water"));
    	Electricity.setName(getWord("Electricity"));
    	Total.setName(getWord("Total"));
    	DoorOpenLabel.setText(getWord("DoorOpenLabel"));
    	DoorClosedLabel.setText(getWord("DoorClosedLabel"));
    	
    	TimeFrameLable.setText(getWord("TimeFrameLable"));
    	ElectricDialog.setHeaderText(getWord("ElectricDialog"));
    	WaterDialog.setHeaderText(getWord("WaterDialog"));
    	TotalDialog.setHeaderText(getWord("TotalDialog"));
	}
	
	public void handleEnglishOptionClick(ActionEvent e) throws InterruptedException {
		//changes all written text on UI to English 
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
		ThermoSliderLabel.setText(getWord("ThermoSliderLabel"));
		ClearButton.setText(getWord("ClearButton"));
		
		DayButton.setText(getWord("DayButton"));
		WeekButton.setText(getWord("WeekButton"));
		MonthButton.setText(getWord("MonthButton"));
		LifetimeButton.setText(getWord("LifetimeButton"));
		
		generateDataLabel.setText(getWord("generateDataLabel"));
		TotalLabel.setText(getWord("TotalLabel"));
		ElectricityLabel.setText(getWord("ElectricityLabel"));
		WaterLabel.setText(getWord("WaterLabel"));
		LegendLabel.setText(getWord("LegendLabel"));
		DashboardChart.setTitle("GraphTitle");
		xAxis.setLabel(getWord("xAxisLabel"));
    	yAxis.setLabel(getWord("yAxisLabel"));
    	Water.setName(getWord("Water"));
    	Electricity.setName(getWord("Electricity"));
    	Total.setName(getWord("Total"));
    	
    	DoorOpenLabel.setText(getWord("DoorOpenLabel"));
    	DoorClosedLabel.setText(getWord("DoorClosedLabel"));
    	
    	TimeFrameLable.setText(getWord("TimeFrameLable"));
    	ElectricDialog.setHeaderText(getWord("ElectricDialog"));
    	WaterDialog.setHeaderText(getWord("WaterDialog"));
    	TotalDialog.setHeaderText(getWord("TotalDialog"));
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
		Main.havcOp.interrupt();
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
	public void handleThermostatChange(MouseEvent e) throws InterruptedException {
		Double thermostatTemp = thermostatSlider.getValue();
		try {
			Database.updateSetTemp(mainConnection, thermostatTemp.intValue());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Updating the indoor temp label for testing purposes
//		String thermostatTempUI = String.valueOf(thermostatTemp);
//		IndoorTempLabel.setText(thermostatTempUI + "°F");
	}
	
	/*
	 * Generate 1 day's worth of data for the graph as 1 data point
	 */
	@FXML
	public void handleDayButton(ActionEvent e) throws InterruptedException {
		
		//DayStorage only holds 6 months (180 days of data). Pops excess after limit.
		if (checkDayStorage()==true) {
			DayStorage.poll();
		}
		
		//CURRENTLY USING TEST FUNCTION TO GENERATE DATE DATA!!!
		Date testDate = generateDayTEST(tempDay, tempMonth, tempYear);
		
		//Display the bill costs for the day in the "last polled"
    	ElectricDialog.setContentText("$" + testDate.getElectricity());
    	WaterDialog.setContentText("$" + testDate.getWater());
    	TotalDialog.setContentText("$" + testDate.getTotal());
    	
		DayStorage.add(testDate);
		//DayStorage.add(generateDay(tempDay, tempMonth, tempYear));
		
		//add 1 day's worth of data for each bill series

		Water.getData().add(new XYChart.Data(testDate.dateToString(),testDate.getWater()));
		Electricity.getData().add(new XYChart.Data(testDate.dateToString(),testDate.getElectricity()));
		Total.getData().add(new XYChart.Data(testDate.dateToString(),testDate.getTotal()));
		
		if (Water.getData().size()>MaxGraphSize) {
		    Water.getData().remove(0);
		}
		if (Electricity.getData().size()>MaxGraphSize) {
		    Electricity.getData().remove(0);
		}
		if (Total.getData().size()>MaxGraphSize) {
			Total.getData().remove(0);
		}
	}
	
	/*
	 * Generate the total costs of 7 day's worth of data for the graph as 1 data point. TEST FOR FUNCTIONAL STORAGE. DO NOT USE.
	 */
	@FXML
	public void handleWeekButtonTEST(ActionEvent e) throws InterruptedException {
		ArrayList<Date> tempArray = new ArrayList<>();
		Date paymentDate = new Date();
		//DayStorage only holds 6 months (180 days of data). Pops excess after limit.
		if (checkDayStorage()==true) {
			for(int i=0;i<7;i++) {
				DayStorage.poll();
			}
		}
		
		//add 1 week of data temp storage
		for(int i=0;i<7;i++) {
			//CURRENTLY USING TEST FUNCTION TO GENERATE DATE DATA!!!
			Date testDate = generateDayTEST(tempDay, tempMonth, tempYear);
			tempArray.add(testDate);
			}
		
		//get bill totals for all the dates and add it to 1 point
		for (Date date : tempArray) {
			paymentDate.setWater(paymentDate.getWater() + date.getWater());
			paymentDate.setElectricity(paymentDate.getElectricity() + date.getElectricity());
			paymentDate.setTotal(paymentDate.getTotal() + date.getTotal());
		}
		
		//set bill time frame
		paymentDate.setTimeFrameString(tempArray.get(0).dateToString() + "-" + tempArray.get(tempArray.size()).dateToString());
		
		//add point to graph
		//FunctionalStorage.add(paymentDate);
		//add 1 day's worth of data for each bill series
		Water.getData().add(new XYChart.Data(paymentDate.dateToString(),paymentDate.getWater()));
		Electricity.getData().add(new XYChart.Data(paymentDate.dateToString(),paymentDate.getElectricity()));
		Total.getData().add(new XYChart.Data(paymentDate.dateToString(),paymentDate.getTotal()));
		
		if (Water.getData().size()>MaxGraphSize) {
		    Water.getData().remove(0);
		}
		if (Electricity.getData().size()>MaxGraphSize) {
		    Electricity.getData().remove(0);
		}
		if (Total.getData().size()>MaxGraphSize) {
			Total.getData().remove(0);
		}
	}
	
	/*
	 * Generate the total costs of 7 day's worth of data.
	 */
	@FXML
	public void handleWeekButton(ActionEvent e) throws InterruptedException {
		ArrayList<Date> tempArray = new ArrayList<>(); //to store bill totals for time frame
		//DayStorage only holds 6 months (180 days of data). Pops excess after limit.
		if (checkDayStorage()==true) {
			for(int i=0;i<7;i++) {
				DayStorage.poll();
			}
		}
		
		//add 180 days of data to graph
		for(int i=0;i<7;i++) {
			//CURRENTLY USING TEST FUNCTION TO GENERATE DATE DATA!!!
			Date testDate = generateDayTEST(tempDay, tempMonth, tempYear);
			tempArray.add(testDate); //store in temp for time frame calculations
			DayStorage.add(testDate); //goes to actual storage as well
			//DayStorage.add(generateDay(tempDay, tempMonth, tempYear));
			
			//add 1 day's worth of data for each bill series
			Water.getData().add(new XYChart.Data(testDate.dateToString(),testDate.getWater()));
			Electricity.getData().add(new XYChart.Data(testDate.dateToString(),testDate.getElectricity()));
			Total.getData().add(new XYChart.Data(testDate.dateToString(),testDate.getTotal()));
			
			if (Water.getData().size()>MaxGraphSize) {
			    Water.getData().remove(0);
			}
			if (Electricity.getData().size()>MaxGraphSize) {
			    Electricity.getData().remove(0);
			}
			if (Total.getData().size()>MaxGraphSize) {
				Total.getData().remove(0);
			}
			}
		
		//Calculate and display total bills for the last 7 days
		Double totalWater=0.0;
		Double totalElectricity=0.0;
		Double total=0.0;
		
		for (Date date : tempArray) {
			totalWater=totalWater + date.getWater();
			totalElectricity=totalElectricity + date.getElectricity();
			total=total + date.getTotal();
		}
		
		ElectricDialog.setContentText("$" + totalElectricity);
    	WaterDialog.setContentText("$" + totalWater);
    	TotalDialog.setContentText("$" + total);
	}
	
	/*
	 * Generate 1 month's (30 days) worth of data for the graph
	 */
	@FXML
	public void handleMonthButton(ActionEvent e) throws InterruptedException {
		ArrayList<Date> tempArray = new ArrayList<>(); //to store bill totals for time frame
		//DayStorage only holds 6 months (180 days of data). Pops excess after limit.
		if (checkDayStorage()==true) {
			for(int i=0;i<30;i++) {
				DayStorage.poll();
			}
		}
		
		//add 180 days of data to graph
		for(int i=0;i<30;i++) {
			//CURRENTLY USING TEST FUNCTION TO GENERATE DATE DATA!!!
			Date testDate = generateDayTEST(tempDay, tempMonth, tempYear);
			tempArray.add(testDate); //store in temp for time frame calculations
			DayStorage.add(testDate);
			
			//DayStorage.add(generateDay(tempDay, tempMonth, tempYear));
			
			//add 1 day's worth of data for each bill series
			Water.getData().add(new XYChart.Data(testDate.dateToString(),testDate.getWater()));
			Electricity.getData().add(new XYChart.Data(testDate.dateToString(),testDate.getElectricity()));
			Total.getData().add(new XYChart.Data(testDate.dateToString(),testDate.getTotal()));
			
			if (Water.getData().size()>MaxGraphSize) {
			    Water.getData().remove(0);
			}
			if (Electricity.getData().size()>MaxGraphSize) {
			    Electricity.getData().remove(0);
			}
			if (Total.getData().size()>MaxGraphSize) {
				Total.getData().remove(0);
			}
			}
		
		//Calculate and display total bills for the last 30 days
		Double totalWater=0.0;
		Double totalElectricity=0.0;
		Double total=0.0;
				
		for (Date date : tempArray) {
			totalWater=totalWater + date.getWater();
			totalElectricity=totalElectricity + date.getElectricity();
			total=total + date.getTotal();
		}
				
		ElectricDialog.setContentText("$" + totalElectricity);
		WaterDialog.setContentText("$" + totalWater);
		TotalDialog.setContentText("$" + total);
	}
	
	/*
	 * Generate 6 months (180 days) worth of data for the graph
	 */
	@FXML
	public void handleLifeTimeButton(ActionEvent e) throws InterruptedException {
		ArrayList<Date> tempArray = new ArrayList<>(); //to store bill totals for time frame
		//DayStorage only holds 6 months (180 days of data). Pops excess after limit.
		if (checkDayStorage()==true) {
			for(int i=0;i<180;i++) {
				DayStorage.poll();
			}
		}
		
		//add 180 day's of data to graph
		for(int i=0;i<180;i++) {
			//CURRENTLY USING TEST FUNCTION TO GENERATE DATE DATA!!!
			Date testDate = generateDayTEST(tempDay, tempMonth, tempYear);
			tempArray.add(testDate); //store in temp for time frame calculations
			DayStorage.add(testDate);
			//DayStorage.add(generateDay(tempDay, tempMonth, tempYear));
			
			//add 1 day's worth of data for each bill series
			Water.getData().add(new XYChart.Data(testDate.dateToString(),testDate.getWater()));
			Electricity.getData().add(new XYChart.Data(testDate.dateToString(),testDate.getElectricity()));
			Total.getData().add(new XYChart.Data(testDate.dateToString(),testDate.getTotal()));
			
			if (Water.getData().size()>MaxGraphSize) {
			    Water.getData().remove(0);
			}
			if (Electricity.getData().size()>MaxGraphSize) {
			    Electricity.getData().remove(0);
			}
			if (Total.getData().size()>MaxGraphSize) {
				Total.getData().remove(0);
			}
			}
		
		//Calculate and display total bills for the last 7 days
		Double totalWater=0.0;
		Double totalElectricity=0.0;
		Double total=0.0;
				
		for (Date date : tempArray) {
			totalWater=totalWater + date.getWater();
			totalElectricity=totalElectricity + date.getElectricity();
			total=total + date.getTotal();
		}
				
		ElectricDialog.setContentText("$" + totalElectricity);
		WaterDialog.setContentText("$" + totalWater);
		TotalDialog.setContentText("$" + total);
	}
	
	/*
	 * Clear graph
	 */
	@FXML
	public void handleClearButton(ActionEvent e) throws InterruptedException {
		resetDayStorage();
	}
	
	/*
	 * Put things here you'd like to happen before UI is shown to user
	 */
	
    @FXML
    public void initialize(){
    	//start off by getting the current date to store future data points
    	retrieveCurrentDate();
    	
    	//Left partition is default screen, move split pane out of way
    	BaseSplitPane.setDividerPosition(0, 1.0); //0, 1.0
    	
    	//Update outdoor temp label
    	String outdoortemp = String.valueOf(Weather.getCurrentWeather());
    	OutdoorTempLabel.setText(outdoortemp + "°F");
    	
    	//Update indoor temp label
    	try {
			IndoorTempLabel.setText(Database.getInternalTemp(mainConnection) + "°F");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	//Thermostat and indoor temp are initially the same?
    	//thermostatTemp = getIndoorTemp
    	try {
			thermostatSlider.setValue(Database.getSetTemp(mainConnection));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//Graph
    	
    	xAxis.setLabel(getWord("xAxisLabel"));
    	xAxis.autosize();
    	xAxis.setAnimated(false);
    	yAxis.setLabel(getWord("yAxisLabel"));
    	yAxis.autosize();
    	xAxis.setAnimated(false);
    	DashboardChart.setTitle(getWord("GraphTitle"));
    	DashboardChart.setLegendVisible(true);
    	//Bills
    	Water.setName(getWord("Water"));
    	Electricity.setName(getWord("Electricity"));
    	Total.setName(getWord("Total"));
    	
    	//add all bill series to graph
    	DashboardChart.getData().addAll(Total, Water, Electricity);
    }
    
    //Back-End Data Manipulation
    public String retrieveCurrentDate() {
		//retrieves current local date for graph
		String dateString = java.time.LocalDate.now().toString();
		ArrayList<Integer> workingDate = new ArrayList<>();
		Scanner scanner = new Scanner(dateString);
		scanner.useDelimiter("-");
		while(scanner.hasNext()) {
			workingDate.add(scanner.nextInt());
		}
		scanner.close();
		//puts date as [yyyy,mm,dd]
		//set global variables:
		tempDay = workingDate.get(2);
		tempMonth = workingDate.get(1);
		tempYear = workingDate.get(0);
		return dateString;
	}
    
    public Date generateDay(int day, int month, int year) {
    	//called by handleDayButton to add 1 day's worth of info to graph
    	Date dayDate = new Date(day, month, year);
    	//iterate day to avoid data conflict
    	iterateDay();
    	//to do:  Put daily bill data here:
    	
    	/*
    	dayDate.setWater();
    	dayDate.setElectricity();
    	//total already calculated, but here's the setter:
    	dayDate.setTotal();
    	*/
    	
		return dayDate;
    }
    
    //TESTING FUNCTION FOR GRAPH
    public Date generateDayTEST(int day, int month, int year) {
    	//called by handleDayButton to add 1 day's worth of info to graph
    	Date dayDate = new Date(day, month, year);
    	
    	//to do:  Put daily bill data here:
    	
    	Random r = new Random();
    	Integer temp = r.nextInt(500);
    	Integer temp2 = r.nextInt(500);
    	dayDate.setWater(Double.valueOf(temp));
    	dayDate.setElectricity(Double.valueOf(temp2));
    	dayDate.setTotal(dayDate.calcTotal());
    	//iterate day to avoid data conflict
    	iterateDay();
		return dayDate;
    }
    
    public boolean checkDayStorage() {
    	//DayStorage only holds 6 months (180 days of data). Pops excess after limit.
    	if(DayStorage.size()==180) {
    		DayStorageFull= true;
    	}
    	return DayStorageFull;
    }
    
    public void resetDayStorage() {
    	//Empties dayStorage and resets the graph
    	DayStorage.clear();
    	DayStorageFull= false;
    	ElectricDialog.setContentText("");
    	WaterDialog.setContentText("");
    	TotalDialog.setContentText("");
    	DashboardChart.getData().clear();;
    }
    
    public void iterateDay() {
    	//iterates date variables for Graph
        switch(tempMonth) {
           case 1:
        	   if(tempDay==31) {
        		   tempDay=0;
        		   tempMonth++;
        	   }
        	   tempDay++;
           case 2:
        	   //no leap years
        	   if(tempDay==28) { 
        		   tempDay=0;
        		   tempMonth++;
        	   }
        	   tempDay++;
           case 3:
        	   if(tempDay==31) {
        		   tempDay=0;
        		   tempMonth++;
        	   }
        	   tempDay++;
           case 4:
        	   if(tempDay==30) {
        		   tempDay=0;
        		   tempMonth++;
        	   }
        	   tempDay++;
           case 5:
        	   if(tempDay==31) {
        		   tempDay=0;
        		   tempMonth++;
        	   }
        	   tempDay++;
           case 6:
        	   if(tempDay==30) {
        		   tempDay=0;
        		   tempMonth++;
        	   }
        	   tempDay++;
           case 7:
        	   if(tempDay==31) {
        		   tempDay=0;
        		   tempMonth++;
        	   }
        	   tempDay++;
           case 8:
        	   if(tempDay==31) {
        		   tempDay=0;
        		   tempMonth++;
        	   }
        	   tempDay++;
           case 9:
        	   if(tempDay==30) {
        		   tempDay=0;
        		   tempMonth++;
        	   }
        	   tempDay++;
           case 10:
        	   if(tempDay==31) {
        		   tempDay=0;
        		   tempMonth++;
        	   }
        	   tempDay++;
           case 11:
        	   if(tempDay==30) {
        		   tempDay=0;
        		   tempMonth++;
        	   }
        	   tempDay++;
           case 12:
        	   if(tempDay==31) {
        		   tempDay=0;
        		   tempMonth=0;
        		   tempYear++;
        	   }
        	   tempDay++;
        	   tempMonth++;
         }
      }
    }