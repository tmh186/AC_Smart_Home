package view;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

import application.Date;
import application.Main;
import connections.Bill;
import connections.Database;
import connections.Device;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ViewController extends Main {
	// format decimals to money format
	DecimalFormat d = new DecimalFormat("#,###,##0.00");

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

	/* Left View -- Default Home layout */
	@FXML
	public AnchorPane LeftPartition;
	@FXML
	public Button refreshView;
	@FXML
	public ImageView FloorPlanImageView;
	/*
	 * Doors door open: set imageurl = ..\opendoor.png door closed: set
	 * imagelocation = ..\closeddoor.png
	 */

	@FXML
	public ImageView garage3door;
	@FXML
	public ImageView frontdoor;
	@FXML
	public ImageView backdoor;
	/*
	 * Lights on: setVisibility(true) off: setVisibility(false)
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
	 * Appliances on: set imageurl = ..\running.png off: set imageurl =
	 * ..\stopped.png
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
	public ImageView masterbathshower;
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
	public Label OutdoorTempLabel; // Displays current outdoor temp value
	@FXML
	public Label IndoorTempLabel; // Displays current indoor temp value
	@FXML
	public Pane hvacPane;
	@FXML
	public ImageView hvacFanIcon;
	@FXML
	public ImageView waterHeaterIcon;
	@FXML
	public Label hvacTitle;
	@FXML
	public Label waterHeaterTitle;
	@FXML
	public Label hvacStatusLabel; // Displays HVAC status
	@FXML
	public Label waterHeaterStatusLabel; // Displays water heater status
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
	public LineChart<String, Number> DashboardChart;
	final int MaxGraphSize = 180;
	// Bills
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
	@FXML
	public Label PredictionLable;
	@FXML
	public DialogPane PredictedElectric;
	@FXML
	public DialogPane PredictedWater;
	@FXML
	public DialogPane PredictedTotal;

	// Data Variables
	public Queue<Date> DayStorage = new LinkedList<>(); // what will show up on the graph
	public boolean DayStorageFull = false;
	public String currentDate;
	public int tempDay;
	public int tempMonth;
	public int tempYear;
	@FXML
	public Stage ControllerStage;
	public boolean HVACON;
	public boolean HeaterON;
	/* Event handling */

	/*
	 * Language
	 */

	public ViewController() {
		System.out.println("Testing controller .....");
	}

	public void handleJapaneseOptionClick(ActionEvent e) throws InterruptedException {
		// changes all writen text on UI to Japanese
		setLocale(getLoc_JP());
		// setStageTitle(getWord("Title")); //null pointer

		FileMenuOption.setText(getWord("FileMenuOption"));
		ExitOption.setText(getWord("ExitOption"));
		EditMenuOption.setText(getWord("EditMenuOption"));
		DebugOption.setText(getWord("DebugOption"));
		HelpMenuOption.setText(getWord("HelpMenuOption"));
		AboutOption.setText(getWord("AboutOption"));
		LanguageMenuOption.setText(getWord("LanguageMenuOption"));
		EnglishOption.setText(getWord("EnglishOption"));
		JapaneseOption.setText(getWord("JapaneseOption"));

		// EnergyUsageTitle.setText(getWord("EnergyUsageTitle"));
		TempTitle.setText(getWord("TempTitle"));
		IndoorTempTitle.setText(getWord("IndoorTempTitle"));
		OutdoorTempTitle.setText(getWord("OutdoorTempTitle"));
		LightKeyLabel.setText(getWord("LightKeyLabel"));
		LightOnLabel.setText(getWord("LightOnLabel"));
		LightOffLabel.setText(getWord("LightOffLabel"));
		RightPartitionButton.setText(getWord("RightPartitionButton"));
		BackButton.setText(getWord("BackButton"));
		ThermoSliderLabel.setText(getWord("ThermoSliderLabel"));

		// DayButton.setText(getWord("DayButton"));
		// WeekButton.setText(getWord("WeekButton"));
		// MonthButton.setText(getWord("MonthButton"));
		// LifetimeButton.setText(getWord("LifetimeButton"));
		ClearButton.setText(getWord("ClearButton"));
		DashboardChart.setTitle(getWord("GraphTitle"));

		// generateDataLabel.setText(getWord("generateDataLabel"));
		// TotalLabel.setText(getWord("TotalLabel"));
		// ElectricityLabel.setText(getWord("ElectricityLabel"));
		// WaterLabel.setText(getWord("WaterLabel"));
		// LegendLabel.setText(getWord("LegendLabel"));
		DashboardChart.setTitle(getWord("GraphTitle"));
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
		PredictionLable.setText(getWord("PredictionLable"));
		PredictedElectric.setHeaderText(getWord("PredictedElectric"));
		PredictedWater.setHeaderText(getWord("PredictedWater"));
		PredictedTotal.setHeaderText(getWord("PredictedTotal"));
		if (HVACON == true) {
			hvacStatusLabel.setText(getWord("Running"));
		} else {
			hvacStatusLabel.setText(getWord("Stopped"));
		}
		if (HeaterON == true) {
			waterHeaterStatusLabel.setText(getWord("Running"));
		} else {
			waterHeaterStatusLabel.setText(getWord("Stopped"));
		}
		refreshView.setText(getWord("RefreshView"));
		hvacTitle.setText(getWord("HVAC"));
		waterHeaterTitle.setText(getWord("Water"));
	}

	public void handleEnglishOptionClick(ActionEvent e) throws InterruptedException {
		// changes all written text on UI to English
		setLocale(getLoc_EN());
		// setStageTitle(getWord("Title")); //null pointer

		FileMenuOption.setText(getWord("FileMenuOption"));
		ExitOption.setText(getWord("ExitOption"));
		EditMenuOption.setText(getWord("EditMenuOption"));
		DebugOption.setText(getWord("DebugOption"));
		HelpMenuOption.setText(getWord("HelpMenuOption"));
		AboutOption.setText(getWord("AboutOption"));
		LanguageMenuOption.setText(getWord("LanguageMenuOption"));
		EnglishOption.setText(getWord("EnglishOption"));
		JapaneseOption.setText(getWord("JapaneseOption"));
		// TODO: energy usage title was removed, replaced with hvac and waterheater
		// EnergyUsageTitle.setText(getWord("EnergyUsageTitle"));
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

		// DayButton.setText(getWord("DayButton"));
		// WeekButton.setText(getWord("WeekButton"));
		// MonthButton.setText(getWord("MonthButton"));
		// LifetimeButton.setText(getWord("LifetimeButton"));

		// generateDataLabel.setText(getWord("generateDataLabel"));
		// TotalLabel.setText(getWord("TotalLabel"));
		// ElectricityLabel.setText(getWord("ElectricityLabel"));
		// WaterLabel.setText(getWord("WaterLabel"));
		// LegendLabel.setText(getWord("LegendLabel"));
		DashboardChart.setTitle(getWord("GraphTitle"));
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
		PredictionLable.setText(getWord("PredictionLable"));
		PredictedElectric.setHeaderText(getWord("PredictedElectric"));
		PredictedWater.setHeaderText(getWord("PredictedWater"));
		PredictedTotal.setHeaderText(getWord("PredictedTotal"));
		if (HVACON == true) {
			hvacStatusLabel.setText(getWord("Running"));
		} else {
			hvacStatusLabel.setText(getWord("Stopped"));
		}
		if (HeaterON == true) {
			waterHeaterStatusLabel.setText(getWord("Running"));
		} else {
			waterHeaterStatusLabel.setText(getWord("Stopped"));
		}
		refreshView.setText(getWord("RefreshView"));
		hvacTitle.setText(getWord("HVAC"));
		waterHeaterTitle.setText(getWord("Water"));
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

		fetchDatAndPlotGraph();
	}

	/**
	 * Connect and pull bills to plot graph
	 */
	private void fetchDatAndPlotGraph() {
		try {
			// sixMonthSimulation.main(null);
			List<Bill> bills = Database.getAllBills(mainConnection);
//			System.out.println();
//			System.out.println(bills);
//			//Water.getData()
			// for (Bill bill : bills) {
			// 30 x 6 = 180
			bills.sort(new Comparator<Bill>() {
				public int compare(Bill o1, Bill o2) {
					long d = o1.getDate().getTime() - o2.getDate().getTime();
					if (d > 0)
						return 1;
					else if (d < 0)
						return -1;
					return 0;
				};
			});

			for (int i = 0; i < bills.size(); i++) {
				// if(i%10==0) {
				Bill bill = bills.get(i);
				String date = bill.getDate().toString(); // bill.getDate().getDay()+"/"+(bill.getDate().getMonth()+1);
				// System.out.println(date);
				Water.getData().add(new XYChart.Data(date, bill.getTotalWater()));
				Electricity.getData().add(new XYChart.Data(date, bill.getTotalElec()));
				Total.getData().add(new XYChart.Data(date, bill.getTotal()));
				// }

			}

			// Calculate and display total bills for the last 6 months
			Double totalWater = 0.0;
			Double totalElectricity = 0.0;
			Double total = 0.0;

			for (Bill date : bills) {
				totalWater = totalWater + date.getTotalWater();
				totalElectricity = totalElectricity + date.getTotalElec();
				total = total + date.getTotal();
			}

			ElectricDialog.setContentText("$" + d.format(totalElectricity));
			WaterDialog.setContentText("$" + d.format(totalWater));
			TotalDialog.setContentText("$" + d.format(total));

			// Calculate and display new predicted bill costs for the next month (30 days)
			PredictedWater.setContentText("$" + d.format(getAvgWater(billarchive) * 30));
			PredictedElectric.setContentText("$" + d.format(getAvgElectricity(billarchive) * 30));
			PredictedTotal.setContentText("$" + d.format(getAvgTotal(billarchive) * 30));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		} catch (Exception e) {
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
	}

	/*
	 * Clear graph
	 */
	@FXML
	public void handleClearButton(ActionEvent e) throws InterruptedException {
		resetDayStorage();
	}

	Image STOPPED_IMAGE = new Image("stopped.png");
	Image RUNNING_IMAGE = new Image("running.png");
	Image OPENDOOR_IMAGE = new Image("opendoor.png");
	Image CLOSEDOOR_IMAGE = new Image("closeddoor.png");

	/*
	 * Update the imageview nodes of floorplan
	 */
	public void setDeviceUIOff(int i) {
		switch (i) {
		case 1:
			i = 0;
			br1overheadlamp.setVisible(false);
			System.out.println("br1ovrheadlamp was set invisible"); // for testing
			break;
		case 2:
			i = 1;
			br1lampA.setVisible(false);
			System.out.println("br1lampA was set invisble"); // for testing
			break;
		case 3:
			i = 2;
			br1lampB.setVisible(false);
			break;
		case 4:
			i = 3;
			br2overheadlamp.setVisible(false);
			break;
		case 5:
			i = 4;
			br2lampA.setVisible(false);
			break;
		case 6:
			i = 5;
			br2lampB.setVisible(false);
			break;
		case 7:
			i = 6;
			masterbrtv.setImage(STOPPED_IMAGE);
			break;
		case 8:
			i = 7;
			masterbedroomoverheadlamp.setVisible(false);
			break;
		case 9:
			i = 8;
			masterbedroomlampA.setVisible(false);
			break;
		case 10:
			i = 9;
			masterbedroomlampB.setVisible(false);
			break;
		case 11:
			i = 10;
			masterbathoverheadlamp.setVisible(false);
			break;
		case 12:
			i = 11;
			masterbathfan.setImage(STOPPED_IMAGE);
			break;
		case 13:
			i = 12;
			masterbathshower.setImage(STOPPED_IMAGE);
			break;
		case 14:
			i = 13;
			halfbathoverheadlamp.setVisible(false);
			break;
		case 15:
			i = 14;
			halfbathfan.setImage(STOPPED_IMAGE);
			break;
		case 16:
			i = 15;
			// TODO add something for half shower
			System.out.println("nothing yet");
			break;
		case 17:
			i = 16;
			washer.setImage(STOPPED_IMAGE);
			break;
		case 18:
			i = 17;
			dryer.setImage(STOPPED_IMAGE);
			break;
		case 19:
			i = 18;
			livingroomtv.setImage(STOPPED_IMAGE);
			break;
		case 20:
			i = 19;
			livingroomoverheadlamp.setVisible(false);
			break;
		case 21:
			i = 20;
			livingroomlampA.setVisible(false);
			break;
		case 22:
			i = 21;
			livingroomlampB.setVisible(false);
			break;
		case 23:
			i = 22;
			kitchenoverheadlamp.setVisible(false);
			break;
		case 24:
			i = 23;
			stove.setImage(STOPPED_IMAGE);
			break;
		case 25:
			i = 24;
			stove.setImage(STOPPED_IMAGE);
			break;
		case 26:
			i = 25;
			microwave.setImage(STOPPED_IMAGE);
			break;
		case 27:
			i = 26;
			fridge.setImage(STOPPED_IMAGE);
			break;
		case 28:
			i = 27;
			dishwasher.setImage(STOPPED_IMAGE);
			break;
		case 29:
			i = 28;
			hvacStatusLabel.setText(getWord("Stopped"));
			HVACON = false;
			break;
		case 30:
			i = 29;
			waterHeaterStatusLabel.setText(getWord("Stopped"));
			HeaterON = false;
			break;
		case 31:
			i = 30;
			frontdoor.setImage(CLOSEDOOR_IMAGE);
			break;
		case 32:
			i = 31;
			backdoor.setImage(CLOSEDOOR_IMAGE);
			break;
		case 33:
			i = 32;
			garage3door.setImage(CLOSEDOOR_IMAGE);
			break;
		default:
			System.out.println("Default i = " + i);
		}
	}

	public void setDeviceUIOn(int i) {
		switch (i) {
		case 1:
			i = 0;
			br1overheadlamp.setVisible(true);
			System.out.println("br1ovrheadlamp was set visible"); // for testing
			break;
		case 2:
			i = 1;
			br1lampA.setVisible(true);
			System.out.println("br1lampA was set visible"); // for testing
			break;
		case 3:
			i = 2;
			br1lampB.setVisible(true);
			break;
		case 4:
			i = 3;
			br2overheadlamp.setVisible(true);
			break;
		case 5:
			i = 4;
			br2lampA.setVisible(true);
			break;
		case 6:
			i = 5;
			br2lampB.setVisible(true);
			break;
		case 7:
			i = 6;
			masterbrtv.setImage(RUNNING_IMAGE);
			break;
		case 8:
			i = 7;
			masterbedroomoverheadlamp.setVisible(true);
			break;
		case 9:
			i = 8;
			masterbedroomlampA.setVisible(true);
			break;
		case 10:
			i = 9;
			masterbedroomlampB.setVisible(true);
			break;
		case 11:
			i = 10;
			masterbathoverheadlamp.setVisible(true);
			break;
		case 12:
			i = 11;
			masterbathfan.setImage(RUNNING_IMAGE);
			break;
		case 13:
			i = 12;
			masterbathshower.setImage(STOPPED_IMAGE);
			break;
		case 14:
			i = 13;
			halfbathoverheadlamp.setVisible(true);
			break;
		case 15:
			i = 14;
			halfbathfan.setImage(RUNNING_IMAGE);
			break;
		case 16:
			i = 15;
			// TODO add something for half shower
			System.out.println("nothing yet");
			break;
		case 17:
			i = 16;
			washer.setImage(RUNNING_IMAGE);
			break;
		case 18:
			i = 17;
			dryer.setImage(RUNNING_IMAGE);
			break;
		case 19:
			i = 18;
			livingroomtv.setImage(RUNNING_IMAGE);
			break;
		case 20:
			i = 19;
			livingroomoverheadlamp.setVisible(true);
			break;
		case 21:
			i = 20;
			livingroomlampA.setVisible(true);
			break;
		case 22:
			i = 21;
			livingroomlampB.setVisible(true);
			break;
		case 23:
			i = 22;
			kitchenoverheadlamp.setVisible(true);
			break;
		case 24:
			i = 23;
			stove.setImage(RUNNING_IMAGE);
			break;
		case 25:
			i = 24;
			stove.setImage(RUNNING_IMAGE);
			break;
		case 26:
			i = 25;
			microwave.setImage(RUNNING_IMAGE);
			break;
		case 27:
			i = 26;
			fridge.setImage(RUNNING_IMAGE);
			break;
		case 28:
			i = 27;
			dishwasher.setImage(RUNNING_IMAGE);
			break;
		case 29:
			i = 28;
			hvacStatusLabel.setText(getWord("Running"));
			HVACON = true;
			break;
		case 30:
			i = 29;
			waterHeaterStatusLabel.setText(getWord("Running"));
			HeaterON = true;
			break;
		case 31:
			i = 30;
			frontdoor.setImage(OPENDOOR_IMAGE);
			break;
		case 32:
			i = 31;
			backdoor.setImage(OPENDOOR_IMAGE);
			break;
		case 33:
			i = 32;
			garage3door.setImage(OPENDOOR_IMAGE);
			break;
		default:
			System.out.println("default i = " + i);
		}
	}

	/*
	 * Refresh button for refreshing the view with up to date data
	 */
	@FXML
	public void handleRefreshButton(ActionEvent e) throws InterruptedException, ClassNotFoundException, SQLException {
		// update ImageViews on floorplan
		ArrayList<Device> a = null;
		try {
			a = Database.getAllDevices(mainConnection);
			// Collections.sort(a);
		} catch (ClassNotFoundException | SQLException e2) {
			e2.printStackTrace();
		}

		for (int i = 0; i < a.size(); i++) {
			if (a.get(i).isState() != allDevices.get(i).isState()) {
				allDevices.get(i).changeState();
				if (a.get(i).isState() == false) { // device is off
					// update a(i)'s node on the floorplan
					setDeviceUIOff(i + 1);

				} else if (a.get(i).isState() == true) { // device is on
					// update a(i)'s node on the floorplan
					setDeviceUIOn(i + 1);
				} else {
					System.out.println("an issue");
				}
			}
		}

		// update outdoor temp:
		String outdoortemp = String.valueOf(Weather.getCurrentWeather());
		OutdoorTempLabel.setText(outdoortemp + "°F");

		// update indoor temp:
		try {
			IndoorTempLabel.setText(Database.getInternalTemp(mainConnection) + "°F");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	/*
	 * Put things here you'd like to happen before UI is shown to user
	 */
	@FXML
	public void initialize() {
		// start off by getting the current date to store future data points
		retrieveCurrentDate();

		// Left partition is default screen, move split pane out of way
		BaseSplitPane.setDividerPosition(0, 1.0); // 0, 1.0

		// Update outdoor temp label
		String outdoortemp = String.valueOf(Weather.getCurrentWeather());
		OutdoorTempLabel.setText(outdoortemp + "°F");

		// Update indoor temp label
		try {
			IndoorTempLabel.setText(Database.getInternalTemp(mainConnection) + "°F");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			thermostatSlider.setValue(Database.getSetTemp(mainConnection));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// update all the Devices
		ArrayList<Device> a = null;
		try {
			a = Database.getAllDevices(mainConnection);
			// Collections.sort(a);
		} catch (ClassNotFoundException | SQLException e2) {
			e2.printStackTrace();
		}

		for (int i = 0; i < a.size(); i++) {
			if (a.get(i).isState() == false) { // device is off
				// update a(i)'s node on the floorplan
				setDeviceUIOff(i + 1);

			} else if (a.get(i).isState() == true) { // device is on
				// update a(i)'s node on the floorplan
				setDeviceUIOn(i + 1);
			} else {
				System.out.println("an issue getting device state");
			}
		}

		// Graph
		xAxis.setLabel(getWord("xAxisLabel"));
		xAxis.autosize();
		xAxis.setAnimated(false);
		yAxis.setLabel(getWord("yAxisLabel"));
		yAxis.autosize();
		xAxis.setAnimated(false);
		DashboardChart.setTitle(getWord("GraphTitle"));
		DashboardChart.setLegendVisible(true);
		// Bills
		Water.setName(getWord("Water"));
		Electricity.setName(getWord("Electricity"));
		Total.setName(getWord("Total"));

		// add all bill series to graph
		DashboardChart.getData().addAll(Total, Water, Electricity);
	}

	// Back-End Data Manipulation
	public String retrieveCurrentDate() {
		// retrieves current local date for graph
		String dateString = java.time.LocalDate.now().toString();
		ArrayList<Integer> workingDate = new ArrayList<>();
		Scanner scanner = new Scanner(dateString);
		scanner.useDelimiter("-");
		while (scanner.hasNext()) {
			workingDate.add(scanner.nextInt());
		}
		scanner.close();
		// puts date as [yyyy,mm,dd]
		// set global variables:
		tempDay = workingDate.get(2);
		tempMonth = workingDate.get(1);
		tempYear = workingDate.get(0);
		return dateString;
	}

	// TESTING FUNCTION FOR GRAPH
	public Date generateDayTEST(int day, int month, int year) {
		// called by handleDayButton to add 1 day's worth of info to graph
		Date dayDate = new Date(day, month, year);
		Random r = new Random();
		Integer temp = r.nextInt(500);
		Integer temp2 = r.nextInt(500);
		// input day's bill data into date
		dayDate.setWater(Double.valueOf(temp));
		dayDate.setElectricity(Double.valueOf(temp2));
		dayDate.setTotal(dayDate.calcTotal());
		// iterate day to avoid data conflict
		iterateDay();
		return dayDate;
	}

	/*
	 * Calculate average water bill cost for one day using all previous dates' bills
	 */
	public Double getAvgWater(ArrayList<Bill> bills) {
		Double avg = 0.0;
		for (Bill bill : bills) {
			avg = avg + bill.getTotalWater();
		}
		avg = avg / bills.size();
		return avg;
	}

	/*
	 * Calculate average electricity bill cost for one day using all previous dates'
	 * bills
	 */
	public Double getAvgElectricity(ArrayList<Bill> bills) {
		Double avg = 0.0;
		for (Bill bill : bills) {
			avg = avg + bill.getTotalElec();
		}
		avg = avg / bills.size();
		return avg;
	}

	/*
	 * Calculate average total bill cost for one day using all previous dates' bills
	 */
	public Double getAvgTotal(ArrayList<Bill> bills) {
		Double avg = 0.0;
		for (Bill bill : bills) {
			avg = avg + bill.getTotal();
		}
		avg = avg / bills.size();
		return avg;
	}

	// Check to make sure graph only contains 180 points
	public boolean checkDayStorage() {
		// DayStorage only holds 6 months (180 days of data). Pops excess after limit.
		if (DayStorage.size() == 180) {
			DayStorageFull = true;
		}
		return DayStorageFull;
	}

	// Empties the graph points, and resets any calculations
	public void resetDayStorage() {
		// Empties dayStorage and resets the graph
		DayStorage.clear();
		DayStorageFull = false;
		Water.getData().clear();
		Electricity.getData().clear();
		Total.getData().clear();
		ElectricDialog.setContentText("");
		WaterDialog.setContentText("");
		TotalDialog.setContentText("");
		PredictedWater.setContentText("");
		PredictedElectric.setContentText("");
		PredictedTotal.setContentText("");
		retrieveCurrentDate();
	}

	// used to iterate the date from the current date (real time)
	public void iterateDay() {
		// iterates date variables for Graph
		switch (tempMonth) {
		case 1:
			if (tempDay == 31) {
				tempDay = 0;
				tempMonth++;
			}
			tempDay++;
			break;
		case 2:
			// no leap years
			if (tempDay == 28) {
				tempDay = 0;
				tempMonth++;
			}
			tempDay++;
			break;
		case 3:
			if (tempDay == 31) {
				tempDay = 0;
				tempMonth++;
			}
			tempDay++;
			break;
		case 4:
			if (tempDay == 30) {
				tempDay = 0;
				tempMonth++;
			}
			tempDay++;
			break;
		case 5:
			if (tempDay == 31) {
				tempDay = 0;
				tempMonth++;
			}
			tempDay++;
			break;
		case 6:
			if (tempDay == 30) {
				tempDay = 0;
				tempMonth++;
			}
			tempDay++;
			break;
		case 7:
			if (tempDay == 31) {
				tempDay = 0;
				tempMonth++;
			}
			tempDay++;
			break;
		case 8:
			if (tempDay == 31) {
				tempDay = 0;
				tempMonth++;
			}
			tempDay++;
			break;
		case 9:
			if (tempDay == 30) {
				tempDay = 0;
				tempMonth++;
			}
			tempDay++;
			break;
		case 10:
			if (tempDay == 31) {
				tempDay = 0;
				tempMonth++;
			}
			tempDay++;
			break;
		case 11:
			if (tempDay == 30) {
				tempDay = 0;
				tempMonth++;
			}
			tempDay++;
			break;
		case 12:
			if (tempDay == 31) {
				tempDay = 0;
				tempMonth = 0;
				tempYear++;
			}
			tempDay++;
			tempMonth++;
			break;
		}
	}
}
