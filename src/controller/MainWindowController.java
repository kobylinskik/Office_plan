package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Employee;

public class MainWindowController {
	
	@FXML private AnchorPane anchorPane;
	@FXML private TableView<Employee> dataTable;
	@FXML private TableColumn<Employee, String> nameColumn;
	@FXML private TableColumn<Employee, String> surnameColumn;
	@FXML private TableColumn<Employee, Integer> roomColumn;
	@FXML private TextField nameField;
	@FXML private TextField surnameField;
	@FXML private TextField roomField;
	@FXML private TextField startField;
	@FXML private TextField finishField;	
	@FXML private Canvas officePlan;
				
	private Main main;	
	private CanvasController canvasController;
	private DataController dataController;
		
	//Ustawia main dla kontrolera okna głównego, tworzy również kontrolery dla canvas i danych w tabeli
	public void setMain(Main main) {
		this.main = main;
		dataController = new DataController(dataTable, nameColumn, surnameColumn, roomColumn);
		canvasController = new CanvasController(officePlan);
		canvasController.fillCanvas();
		canvasController.drawAllRooms();
	}
		
	//Ładuje dane do tabeli po naciśnięciu przycisku Wczytaj
	public void handleLoadButton() {
		dataController.openDataFile();
		dataController.loadData();
	}
	
	//Zapisuje dane do pliku po naciśnięciu przycisku Zapisz
	public void handleSaveButton() {
		dataController.saveData();
	}
	
	//Tworzy obiekt pracownika o danych z pól tekstowych, dodaje jego dane do tabeli
	public void handleAddButton() {
		dataController.addData(nameField.getText(), 
				surnameField.getText(), 
				Integer.parseInt(roomField.getText()), 
				Integer.parseInt(startField.getText()), Integer.parseInt(finishField.getText()));
	}
	
	//Tworzy raport po naciśnięciu przycisku Raport
	public void handleReportButton() {
		dataController.createReport();
	}	
	
	//Podświetla pokój pracownika po zaznaczeniu go w tabeli
	public void selectEmployee() {	
		canvasController.fillCanvas();
		canvasController.drawAllRooms();
		canvasController.fillRoom(dataTable.getSelectionModel().getSelectedItem().getRoom());
	}
}
