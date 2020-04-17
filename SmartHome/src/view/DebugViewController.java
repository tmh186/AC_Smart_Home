package view;

import view.ViewController;
import java.time.format.DateTimeFormatter;
<<<<<<< HEAD
=======
import java.util.ArrayList;
import java.util.Collections;
>>>>>>> branch 'master' of https://gitlab.cs.uab.edu/CS499S2020/Team4/SmartHome-project.git
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
<<<<<<< HEAD
	
	String formattedDateValue = "";
	/*
	 * user selected date event
	 */
	@FXML
	private void handleUserSelectedDate(ActionEvent ex) throws InterruptedException{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM/dd/yyyy",Locale.US);
		formattedDateValue = (datePicker.getValue()).format(formatter);
		System.out.println("User in admin panel has chosen to edit this day:" + formattedDateValue);
	}
	
=======

>>>>>>> branch 'master' of https://gitlab.cs.uab.edu/CS499S2020/Team4/SmartHome-project.git
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
<<<<<<< HEAD
	
=======

	Date myDateValue;

	/*
	 * user selected date event
	 */
	@FXML
	public void handleUserSelectedDate(ActionEvent ex) throws InterruptedException {
		System.out.println("User has selected to edit new day:" + datePicker.getValue());

		myDateValue = Date.valueOf(datePicker.getValue());
	}

>>>>>>> branch 'master' of https://gitlab.cs.uab.edu/CS499S2020/Team4/SmartHome-project.git
	/*
	 * Events for buttons
	 */
	//associate chosen date + real time with each action
	@FXML
<<<<<<< HEAD
	public void kitchenoverheadlightButton(ActionEvent e) throws InterruptedException {
		//get status, update ui and status in db
		
=======
	public void bedroom1overheadLightButton(ActionEvent ex) throws InterruptedException, SQLException {
		// get status, update ui and status in db
		// choose device, isState (true/false)
		buttonActions(1);
	}

	@FXML
	public void bedroom1LampAButton(ActionEvent ex) throws InterruptedException, SQLException {
		// get status, update ui and status in db
		// choose device, isState (true/false)
		buttonActions(2);
	}

	@FXML
	public void bedroom1LampBButton(ActionEvent ex) throws InterruptedException, SQLException {
		// get status, update ui and status in db
		// choose device, isState (true/false)
		buttonActions(3);
	}

	@FXML
	public void bedroom2overheadLightButton(ActionEvent ex) throws InterruptedException, SQLException {
		// get status, update ui and status in db
		// choose device, isState (true/false)
		buttonActions(4);
	}

	@FXML
	public void bedroom2LampAButton(ActionEvent ex) throws InterruptedException, SQLException {
		// get status, update ui and status in db
		// choose device, isState (true/false)
		buttonActions(5);
	}

	@FXML
	public void bedroom2LamBButton(ActionEvent ex) throws InterruptedException, SQLException {
		// get status, update ui and status in db
		// choose device, isState (true/false)
		buttonActions(6);
	}

	@FXML
	public void masterbedroomTVButton(ActionEvent ex) throws InterruptedException, SQLException {
		// get status, update ui and status in db
		// choose device, isState (true/false)
		buttonActions(7);
	}

	@FXML
	public void masterbedroomOverheadLightButton(ActionEvent ex) throws InterruptedException, SQLException {
		// get status, update ui and status in db
		// choose device, isState (true/false)
		buttonActions(8);
	}

	@FXML
	public void masterbedroomLampAButton(ActionEvent ex) throws InterruptedException, SQLException {
		// get status, update ui and status in db
		// choose device, isState (true/false)
		buttonActions(9);
	}

	@FXML
	public void masterbedroomLampBButton(ActionEvent ex) throws InterruptedException, SQLException {
		// get status, update ui and status in db
		// choose device, isState (true/false)
		buttonActions(10);
	}

	@FXML
	public void masterBathOverheadLightButton(ActionEvent ex) throws InterruptedException, SQLException {
		// get status, update ui and status in db
		// choose device, isState (true/false)
		buttonActions(11);
	}

	@FXML
	public void masterBathFanButton(ActionEvent ex) throws InterruptedException, SQLException {
		// get status, update ui and status in db
		// choose device, isState (true/false)
		buttonActions(12);
	}

	@FXML
	public void masterBathShowerButton(ActionEvent ex) throws InterruptedException, SQLException {
		// get status, update ui and status in db
		// choose device, isState (true/false)
		buttonActions(13);
	}

	@FXML
	public void halfBathOverheadLightButton(ActionEvent ex) throws InterruptedException, SQLException {
		// get status, update ui and status in db
		// choose device, isState (true/false)
		buttonActions(14);
	}

	@FXML
	public void halfBathFanButton(ActionEvent ex) throws InterruptedException, SQLException {
		// get status, update ui and status in db
		// choose device, isState (true/false)
		buttonActions(15);
	}

	@FXML
	public void halfBathShower(ActionEvent ex) throws InterruptedException, SQLException {
		// get status, update ui and status in db
		// choose device, isState (true/false)
		buttonActions(16);
	}

	@FXML
	public void washerButton(ActionEvent ex) throws InterruptedException, SQLException {
		// get status, update ui and status in db
		// choose device, isState (true/false)
		buttonActions(17);
	}

	@FXML
	public void dryerButton(ActionEvent ex) throws InterruptedException, SQLException {
		// get status, update ui and status in db
		// choose device, isState (true/false)
		buttonActions(18);
	}

	@FXML
	public void livingRoomTVButton(ActionEvent ex) throws InterruptedException, SQLException {
		// get status, update ui and status in db
		// choose device, isState (true/false)
		buttonActions(19);
	}

	@FXML
	public void livingRoomOverheadLightButton(ActionEvent ex) throws InterruptedException, SQLException {
		// get status, update ui and status in db
		// choose device, isState (true/false)
		buttonActions(20);
	}

	@FXML
	public void livingroomLampAButton(ActionEvent ex) throws InterruptedException, SQLException {
		// get status, update ui and status in db
		// choose device, isState (true/false)
		buttonActions(21);
	}

	@FXML
	public void livingroomLampBButton(ActionEvent ex) throws InterruptedException, SQLException {
		// get status, update ui and status in db
		// choose device, isState (true/false)
		buttonActions(22);
	}

	@FXML
	public void kitchenoverheadlightButton(ActionEvent ex) throws InterruptedException, SQLException {
		// get status, update ui and status in db
		// choose device, isState (true/false)
		buttonActions(23);
	}

	@FXML
	public void stoveButton(ActionEvent ex) throws InterruptedException, SQLException {
		// get status, update ui and status in db
		// choose device, isState (true/false)
		buttonActions(24);
	}

	@FXML
	public void ovenButton(ActionEvent ex) throws InterruptedException, SQLException {
		// get status, update ui and status in db
		// choose device, isState (true/false)
		buttonActions(25);
	}

	@FXML
	public void microwaveButton(ActionEvent ex) throws InterruptedException, SQLException {
		// get status, update ui and status in db
		// choose device, isState (true/false)
		buttonActions(26);
	}

	@FXML
	public void refrigeratorButton(ActionEvent ex) throws InterruptedException, SQLException {
		// get status, update ui and status in db
		// choose device, isState (true/false)
		buttonActions(27);
	}

	@FXML
	public void dishwasherButton(ActionEvent ex) throws InterruptedException, SQLException {
		// get status, update ui and status in db
		// choose device, isState (true/false)
		buttonActions(28);
	}

	@FXML
	public void hvacButton(ActionEvent ex) throws InterruptedException, SQLException {
		// get status, update ui and status in db
		// choose device, isState (true/false)
		buttonActions(29);
	}

	@FXML
	public void hotWaterHeaterButton(ActionEvent ex) throws InterruptedException, SQLException {
		// get status, update ui and status in db
		// choose device, isState (true/false)
		buttonActions(30);
	}

	public void buttonActions(int i) {
		System.out.println(a.get(i));

		if (a.get(i).isState() == false) // turn device which is off, on
		{
			// update db
			curr.turnDeviceOn(a.get(i));
			System.out.println("User turned device on");
			// update floorplan
		} else if (a.get(i).isState() == true) { // turn device which is on, off
			curr.turnDeviceOff(a.get(i), currBill);
			System.out.println("User turned device off");
		} else {
			System.out.println("an issue");
		}
>>>>>>> branch 'master' of https://gitlab.cs.uab.edu/CS499S2020/Team4/SmartHome-project.git
	}
<<<<<<< HEAD
	
	
=======

	/*
	 * Initialize method to do things before UI is shown
	 */

	ArrayList<Device> a = null;
	EventTacking curr = null;
	Bill currBill = null;

	@FXML
	public void initialize() throws SQLException {
		// connect to db and get devices
		Connection c = Database.initConnect();
		try {
			a = Database.getAllDevices(c);
			Collections.sort(a);
			System.out.println(a);
			currBill = Bill.getCurrentBill(Database.getAllBills(c));
			curr = new EventTacking(a);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.close();
		if (a == null) {
			System.out.println("null device list");
			System.exit(0);
		}
		// set default date
		datePicker.setValue(LocalDate.now());

		// for testing
		System.out.println("Default date on initialize: " + datePicker.getValue());
	}
>>>>>>> branch 'master' of https://gitlab.cs.uab.edu/CS499S2020/Team4/SmartHome-project.git
}
