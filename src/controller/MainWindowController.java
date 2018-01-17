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
	private ObservableList<Employee> employeeList = FXCollections.observableArrayList();
		
	public void setMain(Main main) {
		this.main = main;
		canvasController = new CanvasController(officePlan);
		canvasController.fillCanvas();
	}
	
	public void setColumns() {
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
		roomColumn.setCellValueFactory(new PropertyValueFactory<>("room"));		
	}	
	
	public void loadData() {
		Scanner input = null;
		String name;
		String surname;
		int room;
		int timeStarted;
		int timeFinished;
		try {
			input = new Scanner(Paths.get("/home/kk/Documents/pracownicy.txt"));
			while(input.hasNext()) {
				name = input.next();
				surname = input.next();
				room = input.nextInt();
				timeStarted = input.nextInt(); 
				timeFinished = input.nextInt();
				employeeList.add(new Employee(name, surname, room, timeStarted, timeFinished));
			}
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(input != null) {
				input.close();
			}
		}
		dataTable.setItems(employeeList);
	}
	
	public void saveData() {
		PrintWriter output = null;
		try {
			output = new PrintWriter("/home/kk/Documents/pracownicy.txt");
			for(int i = 0; i < employeeList.size(); i++) {
				output.println(employeeList.get(i).toString());
			}			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(output != null)
				output.close();
		}
		
	}
	
	public void addData() {
		employeeList.add(new Employee(nameField.getText(), 
				surnameField.getText(), 
				Integer.parseInt(roomField.getText()), 
				Integer.parseInt(startField.getText()), Integer.parseInt(finishField.getText())));
		dataTable.setItems(employeeList);
	}
	
	public void createReport() {
		String reportPath = createReportPath();
		createReportFile(reportPath);
		writeReportData(reportPath);		
	}
	
	private String createReportPath() {
		Date today = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy");	
		return "/home/kk/Documents/raport_" + dateFormat.format(today);
	}
	
	private void createReportFile(String reportPath) {	 
		try {						
			File report = new File(reportPath);
			report.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void writeReportData(String reportPath) {
		PrintWriter output = null;
		try {
			output = new PrintWriter(reportPath);
			for(int i = 0; i < employeeList.size(); i++) {
				output.println(employeeList.get(i).toString());
			}			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(output != null)
				output.close();
		}
	}
	
	public void selectEmployee() {		  
		canvasController.fillRoom(dataTable.getSelectionModel().getSelectedItem().getRoom());
	}
}
