package view;
import view.ViewController;
import connections.Bill;
import connections.Database;
import connections.Device;
import connections.EventTacking;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

public class DebugViewController {
	@FXML
	private AnchorPane headpane = new AnchorPane();
	@FXML
	private DatePicker datePicker = new DatePicker();
	@FXML
	private MenuButton roomMenuButton = new MenuButton();
	@FXML
		private MenuItem kitchenItem = new MenuItem();
	@FXML
		private MenuItem lrItem = new MenuItem();
	@FXML
		private MenuItem masterbrItem = new MenuItem();
	@FXML
		private MenuItem masterbathItem = new MenuItem();
	@FXML
		private MenuItem halfbathItem = new MenuItem();
	@FXML
		private MenuItem br1Item = new MenuItem();
	@FXML
		private MenuItem br2Item = new MenuItem();
	@FXML
		private MenuItem laundryItem = new MenuItem();
	@FXML
		private MenuItem garageItem = new MenuItem();
	
	@FXML
	private AnchorPane kitchenpane = new AnchorPane();
	@FXML
	private AnchorPane livingroompane = new AnchorPane();
	@FXML
	private AnchorPane masterbrpane = new AnchorPane();
	@FXML
	private AnchorPane masterbathpane = new AnchorPane();
	@FXML
	private AnchorPane halfbathpane = new AnchorPane();
	@FXML
	private AnchorPane br1pane = new AnchorPane();
	@FXML
	private AnchorPane br2pane = new AnchorPane();
	@FXML
	private AnchorPane laundrypane = new AnchorPane();
	@FXML
	private AnchorPane garagepane = new AnchorPane();
	
	/*
	 * Buttons
	 */
	@FXML
	private Button kitchenoverheadlightButton = new Button();
	@FXML
	private Button dishwasherButton = new Button();
	@FXML
	private Button stoveButton = new Button();
	@FXML
	private Button microwaveButton = new Button();
	@FXML
	private Button refrigeratorButton = new Button();
	@FXML
	private Button ovenButton = new Button();
	@FXML
	private Button lroverheadlightButton = new Button();
	@FXML
	private Button lrlampAButton = new Button();
	@FXML
	private Button lrlampBButton = new Button();
	@FXML
	private Button lrtvButton = new Button();
	@FXML
	private Button masterbroverheadlightButton = new Button();
	@FXML
	private Button masterbrlampAButton = new Button();
	@FXML
	private Button masterbrlampBButton = new Button();
	@FXML
	private Button masterbrtvButton = new Button();
	@FXML
	private Button masterbathroomfanButton = new Button();
	@FXML
	private Button halfbathoverheadlightButton = new Button();
	@FXML
	private Button halfbathfanButton = new Button();
	@FXML
	private Button br1overheadlightButton = new Button();
	@FXML
	private Button br1lampAButton = new Button();
	@FXML
	private Button br1lampBButton = new Button();
	@FXML
	private Button br2overheadlightButton = new Button();
	@FXML
	private Button br2lampAButton = new Button();
	@FXML
	private Button br2lampBButton = new Button();
	@FXML
	private Button washerButton = new Button();
	@FXML
	private Button dryerButton = new Button();
	@FXML
	private Button garagedoorAButton = new Button();
	@FXML
	private Button garagedoorBButton = new Button();
	
	
	/*
	 * Events for menu item selection
	 */
	@FXML
	private void kitchenSelectedFromMenu(ActionEvent ex) throws InterruptedException {
		kitchenpane.setVisible(true);
		livingroompane.setVisible(false);
		masterbrpane.setVisible(false);
		masterbathpane.setVisible(false);
		halfbathpane.setVisible(false);
		br1pane.setVisible(false);
		br2pane.setVisible(false);
		laundrypane.setVisible(false);
		garagepane.setVisible(false);

	}
	@FXML
	private void livingroomSelectedFromMenu(ActionEvent ex) throws InterruptedException {
		kitchenpane.setVisible(false);
		livingroompane.setVisible(true);
		masterbrpane.setVisible(false);
		masterbathpane.setVisible(false);
		halfbathpane.setVisible(false);
		br1pane.setVisible(false);
		br2pane.setVisible(false);
		laundrypane.setVisible(false);
		garagepane.setVisible(false);
	}
	@FXML
	private void masterBedRoomSelectedFromMenu(ActionEvent ex) throws InterruptedException {
		kitchenpane.setVisible(false);
		livingroompane.setVisible(false);
		masterbrpane.setVisible(true);
		masterbathpane.setVisible(false);
		halfbathpane.setVisible(false);
		br1pane.setVisible(false);
		br2pane.setVisible(false);
		laundrypane.setVisible(false);
		garagepane.setVisible(false);
	}
	@FXML
	private void masterBathSelectedFromMenu(ActionEvent ex) throws InterruptedException {
		kitchenpane.setVisible(false);
		livingroompane.setVisible(false);
		masterbrpane.setVisible(false);
		masterbathpane.setVisible(true);
		halfbathpane.setVisible(false);
		br1pane.setVisible(false);
		br2pane.setVisible(false);
		laundrypane.setVisible(false);
		garagepane.setVisible(false);
	}
	@FXML
	private void halfBathSelectedFromMenu(ActionEvent ex) throws InterruptedException {
		kitchenpane.setVisible(false);
		livingroompane.setVisible(false);
		masterbrpane.setVisible(false);
		masterbathpane.setVisible(false);
		halfbathpane.setVisible(true);
		br1pane.setVisible(false);
		br2pane.setVisible(false);
		laundrypane.setVisible(false);
		garagepane.setVisible(false);
	}
	@FXML
	private void br1SelectedFromMenu(ActionEvent ex) throws InterruptedException {
		kitchenpane.setVisible(false);
		livingroompane.setVisible(false);
		masterbrpane.setVisible(false);
		masterbathpane.setVisible(false);
		halfbathpane.setVisible(false);
		br1pane.setVisible(true);
		br2pane.setVisible(false);
		laundrypane.setVisible(false);
		garagepane.setVisible(false);
	}
	@FXML
	private void br2SelectedFromMenu(ActionEvent ex) throws InterruptedException {
		kitchenpane.setVisible(false);
		livingroompane.setVisible(false);
		masterbrpane.setVisible(false);
		masterbathpane.setVisible(false);
		halfbathpane.setVisible(false);
		br1pane.setVisible(false);
		br2pane.setVisible(true);
		laundrypane.setVisible(false);
		garagepane.setVisible(false);
	}
	@FXML
	private void laundryRoomSelectedFromMenu(ActionEvent ex) throws InterruptedException {
		kitchenpane.setVisible(false);
		livingroompane.setVisible(false);
		masterbrpane.setVisible(false);
		masterbathpane.setVisible(false);
		halfbathpane.setVisible(false);
		br1pane.setVisible(false);
		br2pane.setVisible(false);
		laundrypane.setVisible(true);
		garagepane.setVisible(false);
	}
	@FXML
	private void garageSelectedFromMenu(ActionEvent ex) throws InterruptedException {
		kitchenpane.setVisible(false);
		livingroompane.setVisible(false);
		masterbrpane.setVisible(false);
		masterbathpane.setVisible(false);
		halfbathpane.setVisible(false);
		br1pane.setVisible(false);
		br2pane.setVisible(false);
		laundrypane.setVisible(false);
		garagepane.setVisible(true);
	}
	
	Date myDateValue;
	/*
	 * user selected date event
	 */
	@FXML
	public void handleUserSelectedDate(ActionEvent ex) throws InterruptedException{
		System.out.println("User has selected to edit new day:" + datePicker.getValue());
		
		myDateValue = Date.valueOf(datePicker.getValue());
	}
	
	
	/*
	 * Events for buttons
	 */
	@FXML
	public void bedroom1overheadLightButton(ActionEvent ex) throws InterruptedException, SQLException {
		//get status, update ui and status in db
		//choose device, isState (true/false)
		
		System.out.println(a.get(1));
		
		if (a.get(1).isState() == false) //turn device which is off, on
		{
			//update db
			curr.turnDeviceOn(a.get(1));
			System.out.println("User turned device on");
			//update floorplan
		} else if (a.get(1).isState() == true){ //turn device which is on, off
			curr.turnDeviceOff(a.get(1), currBill);
			System.out.println("User turned device off");
		} else {
			System.out.println("an issue");
		}
		
	}
	@FXML
	public void bedroom1LampAButton(ActionEvent ex) throws InterruptedException, SQLException {
		//get status, update ui and status in db
		//choose device, isState (true/false)
		
		System.out.println(a.get(2));
		
		if (a.get(2).isState() == false) //turn device which is off, on
		{
			//update db
			curr.turnDeviceOn(a.get(2));
			System.out.println("User turned device on");
			//update floorplan
		} else if (a.get(2).isState() == true){ //turn device which is on, off
			curr.turnDeviceOff(a.get(2), currBill);
			System.out.println("User turned device off");
		} else {
			System.out.println("an issue");
		}
		
	}
	@FXML
	public void bedroom1LampBButton(ActionEvent ex) throws InterruptedException, SQLException {
		//get status, update ui and status in db
		//choose device, isState (true/false)
		
		System.out.println(a.get(3));
		
		if (a.get(3).isState() == false) //turn device which is off, on
		{
			//update db
			curr.turnDeviceOn(a.get(3));
			System.out.println("User turned device on");
			//update floorplan
		} else if (a.get(3).isState() == true){ //turn device which is on, off
			curr.turnDeviceOff(a.get(3), currBill);
			System.out.println("User turned device off");
		} else {
			System.out.println("an issue");
		}
		
	}
	@FXML
	public void bedroom2overheadLightButton(ActionEvent ex) throws InterruptedException, SQLException {
		//get status, update ui and status in db
		//choose device, isState (true/false)
		
		System.out.println(a.get(4));
		
		if (a.get(4).isState() == false) //turn device which is off, on
		{
			//update db
			curr.turnDeviceOn(a.get(4));
			System.out.println("User turned device on");
			//update floorplan
		} else if (a.get(4).isState() == true){ //turn device which is on, off
			curr.turnDeviceOff(a.get(4), currBill);
			System.out.println("User turned device off");
		} else {
			System.out.println("an issue");
		}
		
	}
	@FXML
	public void bedroom2LampAButton(ActionEvent ex) throws InterruptedException, SQLException {
		//get status, update ui and status in db
		//choose device, isState (true/false)
		
		System.out.println(a.get(5));
		
		if (a.get(5).isState() == false) //turn device which is off, on
		{
			//update db
			curr.turnDeviceOn(a.get(5));
			System.out.println("User turned device on");
			//update floorplan
		} else if (a.get(5).isState() == true){ //turn device which is on, off
			curr.turnDeviceOff(a.get(5), currBill);
			System.out.println("User turned device off");
		} else {
			System.out.println("an issue");
		}
		
	}
	@FXML
	public void bedroom2LamBButton(ActionEvent ex) throws InterruptedException, SQLException {
		//get status, update ui and status in db
		//choose device, isState (true/false)
		
		System.out.println(a.get(6));
		
		if (a.get(6).isState() == false) //turn device which is off, on
		{
			//update db
			curr.turnDeviceOn(a.get(6));
			System.out.println("User turned device on");
			//update floorplan
		} else if (a.get(6).isState() == true){ //turn device which is on, off
			curr.turnDeviceOff(a.get(6), currBill);
			System.out.println("User turned device off");
		} else {
			System.out.println("an issue");
		}
		
	}
	@FXML
	public void masterbedroomTVButton(ActionEvent ex) throws InterruptedException, SQLException {
		//get status, update ui and status in db
		//choose device, isState (true/false)
		
		System.out.println(a.get(7));
		
		if (a.get(7).isState() == false) //turn device which is off, on
		{
			//update db
			curr.turnDeviceOn(a.get(7));
			System.out.println("User turned device on");
			//update floorplan
		} else if (a.get(7).isState() == true){ //turn device which is on, off
			curr.turnDeviceOff(a.get(7), currBill);
			System.out.println("User turned device off");
		} else {
			System.out.println("an issue");
		}
		
	}
	@FXML
	public void masterbedroomOverheadLightButton(ActionEvent ex) throws InterruptedException, SQLException {
		//get status, update ui and status in db
		//choose device, isState (true/false)
		
		System.out.println(a.get(8));
		
		if (a.get(8).isState() == false) //turn device which is off, on
		{
			//update db
			curr.turnDeviceOn(a.get(8));
			System.out.println("User turned device on");
			//update floorplan
		} else if (a.get(8).isState() == true){ //turn device which is on, off
			curr.turnDeviceOff(a.get(8), currBill);
			System.out.println("User turned device off");
		} else {
			System.out.println("an issue");
		}
		
	}
	@FXML
	public void masterbedroomLampAButton(ActionEvent ex) throws InterruptedException, SQLException {
		//get status, update ui and status in db
		//choose device, isState (true/false)
		
		System.out.println(a.get(9));
		
		if (a.get(9).isState() == false) //turn device which is off, on
		{
			//update db
			curr.turnDeviceOn(a.get(9));
			System.out.println("User turned device on");
			//update floorplan
		} else if (a.get(9).isState() == true){ //turn device which is on, off
			curr.turnDeviceOff(a.get(9), currBill);
			System.out.println("User turned device off");
		} else {
			System.out.println("an issue");
		}
		
	}
	@FXML
	public void masterbedroomLampBButton(ActionEvent ex) throws InterruptedException, SQLException {
		//get status, update ui and status in db
		//choose device, isState (true/false)
		
		System.out.println(a.get(10));
		
		if (a.get(10).isState() == false) //turn device which is off, on
		{
			//update db
			curr.turnDeviceOn(a.get(10));
			System.out.println("User turned device on");
			//update floorplan
		} else if (a.get(10).isState() == true){ //turn device which is on, off
			curr.turnDeviceOff(a.get(10), currBill);
			System.out.println("User turned device off");
		} else {
			System.out.println("an issue");
		}
		
	}
	@FXML
	public void masterBathOverheadLightButton(ActionEvent ex) throws InterruptedException, SQLException {
		//get status, update ui and status in db
		//choose device, isState (true/false)
		
		System.out.println(a.get(11));
		
		if (a.get(11).isState() == false) //turn device which is off, on
		{
			//update db
			curr.turnDeviceOn(a.get(11));
			System.out.println("User turned device on");
			//update floorplan
		} else if (a.get(11).isState() == true){ //turn device which is on, off
			curr.turnDeviceOff(a.get(11), currBill);
			System.out.println("User turned device off");
		} else {
			System.out.println("an issue");
		}
		
	}
	@FXML
	public void masterBathFanButton(ActionEvent ex) throws InterruptedException, SQLException {
		//get status, update ui and status in db
		//choose device, isState (true/false)
		
		System.out.println(a.get(12));
		
		if (a.get(12).isState() == false) //turn device which is off, on
		{
			//update db
			curr.turnDeviceOn(a.get(12));
			System.out.println("User turned device on");
			//update floorplan
		} else if (a.get(12).isState() == true){ //turn device which is on, off
			curr.turnDeviceOff(a.get(12), currBill);
			System.out.println("User turned device off");
		} else {
			System.out.println("an issue");
		}
		
	}
	@FXML
	public void masterBathShowerButton(ActionEvent ex) throws InterruptedException, SQLException {
		//get status, update ui and status in db
		//choose device, isState (true/false)
		
		System.out.println(a.get(13));
		
		if (a.get(13).isState() == false) //turn device which is off, on
		{
			//update db
			curr.turnDeviceOn(a.get(13));
			System.out.println("User turned device on");
			//update floorplan
		} else if (a.get(13).isState() == true){ //turn device which is on, off
			curr.turnDeviceOff(a.get(13), currBill);
			System.out.println("User turned device off");
		} else {
			System.out.println("an issue");
		}
		
	}
	@FXML
	public void halfBathOverheadLightButton(ActionEvent ex) throws InterruptedException, SQLException {
		//get status, update ui and status in db
		//choose device, isState (true/false)
		
		System.out.println(a.get(14));
		
		if (a.get(14).isState() == false) //turn device which is off, on
		{
			//update db
			curr.turnDeviceOn(a.get(14));
			System.out.println("User turned device on");
			//update floorplan
		} else if (a.get(14).isState() == true){ //turn device which is on, off
			curr.turnDeviceOff(a.get(14), currBill);
			System.out.println("User turned device off");
		} else {
			System.out.println("an issue");
		}
		
	}
	@FXML
	public void halfBathFanButton(ActionEvent ex) throws InterruptedException, SQLException {
		//get status, update ui and status in db
		//choose device, isState (true/false)
		
		System.out.println(a.get(15));
		
		if (a.get(15).isState() == false) //turn device which is off, on
		{
			//update db
			curr.turnDeviceOn(a.get(15));
			System.out.println("User turned device on");
			//update floorplan
		} else if (a.get(15).isState() == true){ //turn device which is on, off
			curr.turnDeviceOff(a.get(15), currBill);
			System.out.println("User turned device off");
		} else {
			System.out.println("an issue");
		}
		
	}
	@FXML
	public void halfBathShower(ActionEvent ex) throws InterruptedException, SQLException {
		//get status, update ui and status in db
		//choose device, isState (true/false)
		
		System.out.println(a.get(16));
		
		if (a.get(16).isState() == false) //turn device which is off, on
		{
			//update db
			curr.turnDeviceOn(a.get(16));
			System.out.println("User turned device on");
			//update floorplan
		} else if (a.get(16).isState() == true){ //turn device which is on, off
			curr.turnDeviceOff(a.get(16), currBill);
			System.out.println("User turned device off");
		} else {
			System.out.println("an issue");
		}
		
	}
	@FXML
	public void washerButton(ActionEvent ex) throws InterruptedException, SQLException {
		//get status, update ui and status in db
		//choose device, isState (true/false)
		
		System.out.println(a.get(17));
		
		if (a.get(17).isState() == false) //turn device which is off, on
		{
			//update db
			curr.turnDeviceOn(a.get(17));
			System.out.println("User turned device on");
			//update floorplan
		} else if (a.get(17).isState() == true){ //turn device which is on, off
			curr.turnDeviceOff(a.get(17), currBill);
			System.out.println("User turned device off");
		} else {
			System.out.println("an issue");
		}
		
	}
	@FXML
	public void dryerButton(ActionEvent ex) throws InterruptedException, SQLException {
		//get status, update ui and status in db
		//choose device, isState (true/false)
		
		System.out.println(a.get(18));
		
		if (a.get(18).isState() == false) //turn device which is off, on
		{
			//update db
			curr.turnDeviceOn(a.get(18));
			System.out.println("User turned device on");
			//update floorplan
		} else if (a.get(18).isState() == true){ //turn device which is on, off
			curr.turnDeviceOff(a.get(18), currBill);
			System.out.println("User turned device off");
		} else {
			System.out.println("an issue");
		}
		
	}
	@FXML
	public void livingRoomTVButton(ActionEvent ex) throws InterruptedException, SQLException {
		//get status, update ui and status in db
		//choose device, isState (true/false)
		
		System.out.println(a.get(19));
		
		if (a.get(19).isState() == false) //turn device which is off, on
		{
			//update db
			curr.turnDeviceOn(a.get(19));
			System.out.println("User turned device on");
			//update floorplan
		} else if (a.get(19).isState() == true){ //turn device which is on, off
			curr.turnDeviceOff(a.get(19), currBill);
			System.out.println("User turned device off");
		} else {
			System.out.println("an issue");
		}
		
	}
	@FXML
	public void livingRoomOverheadLightButton(ActionEvent ex) throws InterruptedException, SQLException {
		//get status, update ui and status in db
		//choose device, isState (true/false)
		
		System.out.println(a.get(20));
		
		if (a.get(20).isState() == false) //turn device which is off, on
		{
			//update db
			curr.turnDeviceOn(a.get(20));
			System.out.println("User turned device on");
			//update floorplan
		} else if (a.get(20).isState() == true){ //turn device which is on, off
			curr.turnDeviceOff(a.get(20), currBill);
			System.out.println("User turned device off");
		} else {
			System.out.println("an issue");
		}
		
	}
	@FXML
	public void livingroomLampAButton(ActionEvent ex) throws InterruptedException, SQLException {
		//get status, update ui and status in db
		//choose device, isState (true/false)
		
		System.out.println(a.get(21));
		
		if (a.get(21).isState() == false) //turn device which is off, on
		{
			//update db
			curr.turnDeviceOn(a.get(21));
			System.out.println("User turned device on");
			//update floorplan
		} else if (a.get(21).isState() == true){ //turn device which is on, off
			curr.turnDeviceOff(a.get(21), currBill);
			System.out.println("User turned device off");
		} else {
			System.out.println("an issue");
		}
		
	}
	@FXML
	public void livingroomLampBButton(ActionEvent ex) throws InterruptedException, SQLException {
		//get status, update ui and status in db
		//choose device, isState (true/false)
		
		System.out.println(a.get(22));
		
		if (a.get(22).isState() == false) //turn device which is off, on
		{
			//update db
			curr.turnDeviceOn(a.get(22));
			System.out.println("User turned device on");
			//update floorplan
		} else if (a.get(22).isState() == true){ //turn device which is on, off
			curr.turnDeviceOff(a.get(22), currBill);
			System.out.println("User turned device off");
		} else {
			System.out.println("an issue");
		}
		
	}
	@FXML
	public void kitchenoverheadlightButton(ActionEvent ex) throws InterruptedException, SQLException {
		//get status, update ui and status in db
		//choose device, isState (true/false)
		
		System.out.println(a.get(23));
		
		if (a.get(23).isState() == false) //turn device which is off, on
		{
			//update db
			curr.turnDeviceOn(a.get(23));
			System.out.println("User turned device on");
			//update floorplan
		} else if (a.get(23).isState() == true){ //turn device which is on, off
			curr.turnDeviceOff(a.get(23), currBill);
			System.out.println("User turned device off");
		} else {
			System.out.println("an issue");
		}
		
	}
	@FXML
	public void stoveButton(ActionEvent ex) throws InterruptedException, SQLException {
		//get status, update ui and status in db
		//choose device, isState (true/false)
		
		System.out.println(a.get(24));
		
		if (a.get(24).isState() == false) //turn device which is off, on
		{
			//update db
			curr.turnDeviceOn(a.get(24));
			System.out.println("User turned device on");
			//update floorplan
		} else if (a.get(24).isState() == true){ //turn device which is on, off
			curr.turnDeviceOff(a.get(24), currBill);
			System.out.println("User turned device off");
		} else {
			System.out.println("an issue");
		}
		
	}
	@FXML
	public void ovenButton(ActionEvent ex) throws InterruptedException, SQLException {
		//get status, update ui and status in db
		//choose device, isState (true/false)
		
		System.out.println(a.get(25));
		
		if (a.get(25).isState() == false) //turn device which is off, on
		{
			//update db
			curr.turnDeviceOn(a.get(25));
			System.out.println("User turned device on");
			//update floorplan
		} else if (a.get(25).isState() == true){ //turn device which is on, off
			curr.turnDeviceOff(a.get(25), currBill);
			System.out.println("User turned device off");
		} else {
			System.out.println("an issue");
		}
		
	}
	@FXML
	public void microwaveButton(ActionEvent ex) throws InterruptedException, SQLException {
		//get status, update ui and status in db
		//choose device, isState (true/false)
		
		System.out.println(a.get(26));
		
		if (a.get(26).isState() == false) //turn device which is off, on
		{
			//update db
			curr.turnDeviceOn(a.get(26));
			System.out.println("User turned device on");
			//update floorplan
		} else if (a.get(26).isState() == true){ //turn device which is on, off
			curr.turnDeviceOff(a.get(26), currBill);
			System.out.println("User turned device off");
		} else {
			System.out.println("an issue");
		}
		
	}
	@FXML
	public void refrigeratorButton(ActionEvent ex) throws InterruptedException, SQLException {
		//get status, update ui and status in db
		//choose device, isState (true/false)
		
		System.out.println(a.get(27));
		
		if (a.get(27).isState() == false) //turn device which is off, on
		{
			//update db
			curr.turnDeviceOn(a.get(27));
			System.out.println("User turned device on");
			//update floorplan
		} else if (a.get(27).isState() == true){ //turn device which is on, off
			curr.turnDeviceOff(a.get(27), currBill);
			System.out.println("User turned device off");
		} else {
			System.out.println("an issue");
		}
		
	}
	@FXML
	public void dishwasherButton(ActionEvent ex) throws InterruptedException, SQLException {
		//get status, update ui and status in db
		//choose device, isState (true/false)
		
		System.out.println(a.get(28));
		
		if (a.get(28).isState() == false) //turn device which is off, on
		{
			//update db
			curr.turnDeviceOn(a.get(28));
			System.out.println("User turned device on");
			//update floorplan
		} else if (a.get(28).isState() == true){ //turn device which is on, off
			curr.turnDeviceOff(a.get(28), currBill);
			System.out.println("User turned device off");
		} else {
			System.out.println("an issue");
		}
		
	}
	@FXML
	public void hvacButton(ActionEvent ex) throws InterruptedException, SQLException {
		//get status, update ui and status in db
		//choose device, isState (true/false)
		
		System.out.println(a.get(29));
		
		if (a.get(29).isState() == false) //turn device which is off, on
		{
			//update db
			curr.turnDeviceOn(a.get(29));
			System.out.println("User turned device on");
			//update floorplan
		} else if (a.get(29).isState() == true){ //turn device which is on, off
			curr.turnDeviceOff(a.get(29), currBill);
			System.out.println("User turned device off");
		} else {
			System.out.println("an issue");
		}
		
	}
	@FXML
	public void hotWaterHeaterButton(ActionEvent ex) throws InterruptedException, SQLException {
		//get status, update ui and status in db
		//choose device, isState (true/false)
		
		System.out.println(a.get(30));
		
		if (a.get(30).isState() == false) //turn device which is off, on
		{
			//update db
			curr.turnDeviceOn(a.get(30));
			System.out.println("User turned device on");
			//update floorplan
		} else if (a.get(30).isState() == true){ //turn device which is on, off
			curr.turnDeviceOff(a.get(30), currBill);
			System.out.println("User turned device off");
		} else {
			System.out.println("an issue");
		}
		
	}
	
	
	
	/*
	 * Initialize method to do things before
	 * UI is shown
	 */
	
	ArrayList<Device> a = null;
	EventTacking curr = new EventTacking(a);
	//Bill currBill = new Bill(Date.valueOf(datePicker.getValue()), 0.00, 0.00);
	Bill currBill = new Bill(myDateValue, 0.00, 0.00);

	 @FXML
	 public void initialize() throws SQLException {
		 //connect to db and get devices
			Connection c = Database.initConnect();
			try {
				a = Database.getAllDevices(c);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			c.close();
			if (a == null) {
				System.out.println("null device list");
				System.exit(0);
			}
			//set default date
			datePicker.setValue(LocalDate.now());
			
			//for testing
			System.out.println("Default date on initialize: " + datePicker.getValue());
	 }
}
