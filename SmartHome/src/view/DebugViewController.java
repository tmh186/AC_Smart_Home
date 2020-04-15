package view;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
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
	 * user selected date event
	 */
	@FXML
	private void handleUserSelectedDate(ActionEvent ex) throws InterruptedException{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM/dd/yyyy",Locale.US);
		String formattedDateValue = (datePicker.getValue()).format(formatter);
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
	
}
