package view;

import java.math.RoundingMode;
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
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
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
	public final LineChart<String,Number> DashboardChart = new LineChart<String,Number>(xAxis,yAxis);
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
	public Button BackButton;

	//Data Variables
	public Queue<Date> DayStorage = new LinkedList<>(); //stores dates for utilities graph
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
		
		generateDataLabel.setText(getWord("generateDataLabel"));
		TotalLabel.setText(getWord("TotalLabel"));
		ElectricityLabel.setText(getWord("ElectricityLabel"));
		WaterLabel.setText(getWord("WaterLabel"));
		LegendLabel.setText(getWord("LegendLabel"));
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
		ThermoSliderLabel.setText(getWord("ThermoSliderLabel"));
		
		DayButton.setText(getWord("DayButton"));
		WeekButton.setText(getWord("WeekButton"));
		MonthButton.setText(getWord("MonthButton"));
		LifetimeButton.setText(getWord("LifetimeButton"));
		
		generateDataLabel.setText(getWord("generateDataLabel"));
		TotalLabel.setText(getWord("TotalLabel"));
		ElectricityLabel.setText(getWord("ElectricityLabel"));
		WaterLabel.setText(getWord("WaterLabel"));
		LegendLabel.setText(getWord("LegendLabel"));
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
		
		//Updating the indoor temp label for testing purposes
		String thermostatTempUI = String.valueOf(thermostatTemp);
		IndoorTempLabel.setText(thermostatTempUI + "°F");
	}
	
	/*
	 * Generate 1 days worth of data for the graph
	 */
	@FXML
	public void handleDayButton(ActionEvent e) throws InterruptedException {
		
		//DayStorage only holds 6 months (180 days of data). Pops excess after limit.
		if (checkDayStorage()==true) {
			DayStorage.poll();
		}
		
		//CURRENTLY USING TEST FUNCTION TO GENERATE DATE DATA!!!
		Date testDate = generateDayTEST(tempDay, tempMonth, tempYear);
		DayStorage.add(testDate);
		//DayStorage.add(generateDay(tempDay, tempMonth, tempYear));
		
		//iterate day to avoid data conflict
		iterateDay();
		
		//add 1 day's worth of data for each bill series
		Water.getData().add(new XYChart.Data(testDate.dateToString(),testDate.getWater()));
		Electricity.getData().add(new XYChart.Data(testDate.dateToString(),testDate.getElectricity()));
		Total.getData().add(new XYChart.Data(testDate.dateToString(),testDate.getTotal()));
		
		//add all bill series to graph
		//DashboardChart.getData().addAll(Water, Electricity, Total);
		
		//Water.getData().add(new XYChart.Data("Day1",100.0));
		//Water.getData().add(new XYChart.Data("Day2",50.0));
		//Electricity.getData().add(new XYChart.Data("Day1",90.0));
		//Total.getData().add(new XYChart.Data("Day1",80.0));
		//DashboardChart.getData().add(Water);
		//DashboardChart.getData().add(Electricity);
		//DashboardChart.getData().add(Total);
		//Water.setData(Water.getData()+Water.getData().);
		//DashboardChart.getData().add(Water<"Day1",100.0>);
	}
	
	/*
	 * Put things here you'd like to happen before UI is shown to user
	 */
    @FXML
    public void initialize() {
    	//Left partition is default screen, move split pane out of way
    	BaseSplitPane.setDividerPosition(0, 1.0); //0, 1.0
    	
    	//Update outdoor temp label
    	String outdoortemp = String.valueOf(Weather.getCurrentWeather());
    	OutdoorTempLabel.setText(outdoortemp + "°F");
    	
    	//Update indoor temp label
    	IndoorTempLabel.setText("00.00" + "°F");
    	
    	//Thermostat and indoor temp are initially the same?
    	//thermostatTemp = getIndoorTemp
    	try {
			thermostatSlider.setValue(Database.getSetTemp(mainConnection));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	//Graph
    	xAxis.setLabel("Date");
    	yAxis.setLabel("Cost (USD)");
    	DashboardChart.setTitle("Utilitiy Bill Totals");
    	
    	//Bills
    	Water.setName("Water");
    	Electricity.setName("Electricity");
    	Total.setName("Total");
    	
    	//Water.getData().add(new XYChart.Data("Day1",100.0));
		//Water.getData().add(new XYChart.Data<String,Double>("Day2",50.0));
		//DashboardChart.getData().addAll(Water);
    	
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
    	//Empties dayStorage
    	DayStorage.clear();
    	DayStorageFull= false;
    }
    
    public void iterateDay() {
    	//iterates date variables for Graph
        switch(tempMonth) {
           case 1:
        	   if(tempDay==31) {
        		   tempDay=1;
        		   tempMonth=2;
        	   }
        	   tempDay++;
           case 2:
        	   //no leap years
        	   if(tempDay==28) { 
        		   tempDay=1;
        		   tempMonth=3;
        	   }
        	   tempDay++;
           case 3:
        	   if(tempDay==31) {
        		   tempDay=1;
        		   tempMonth=4;
        	   }
        	   tempDay++;
           case 4:
        	   if(tempDay==30) {
        		   tempDay=1;
        		   tempMonth=5;
        	   }
        	   tempDay++;
           case 5:
        	   if(tempDay==31) {
        		   tempDay=1;
        		   tempMonth=6;
        	   }
        	   tempDay++;
           case 6:
        	   if(tempDay==30) {
        		   tempDay=1;
        		   tempMonth=7;
        	   }
        	   tempDay++;
           case 7:
        	   if(tempDay==31) {
        		   tempDay=1;
        		   tempMonth=8;
        	   }
        	   tempDay++;
           case 8:
        	   if(tempDay==31) {
        		   tempDay=1;
        		   tempMonth=9;
        	   }
        	   tempDay++;
           case 9:
        	   if(tempDay==30) {
        		   tempDay=1;
        		   tempMonth=10;
        	   }
        	   tempDay++;
           case 10:
        	   if(tempDay==31) {
        		   tempDay=1;
        		   tempMonth=11;
        	   }
        	   tempDay++;
           case 11:
        	   if(tempDay==30) {
        		   tempDay=1;
        		   tempMonth=12;
        	   }
        	   tempDay++;
           case 12:
        	   if(tempDay==31) {
        		   tempDay=1;
        		   tempMonth=1;
        		   tempYear++;
        	   }
        	   tempDay++;
         }
      }
    }
