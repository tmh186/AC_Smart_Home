package view;
import view.ViewController;
import java.time.format.DateTimeFormatter;
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
	
	/*
	 * Events for buttons
	 */
	//associate chosen date + real time with each action
	@FXML
	public void kitchenoverheadlightButton(ActionEvent e) throws InterruptedException {
		//get status, update ui and status in db
		
	}
	
	
}
