package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Employee;

public class DataController {
		
	private TableView<Employee> dataTable;
	private TableColumn<Employee, String> nameColumn;
	private TableColumn<Employee, String> surnameColumn;
	private TableColumn<Employee, Integer> roomColumn;	
	private ObservableList<Employee> employeeList = FXCollections.observableArrayList(); //Lista pracowników, których dane znajdą się w tabeli
	private FileChooser fileChooser = new FileChooser();
	private File dataFile;

	//Kontstruktor DataController. Przyjmuje parametry TableView i trzy TableColumn, ustawia właściwości kolumn
	public DataController(TableView<Employee> dataTable, TableColumn<Employee, String> nameColumn, TableColumn<Employee, String> surnameColumn, TableColumn<Employee, Integer> roomColumn) {
		this.dataTable = dataTable;
		this.nameColumn = nameColumn;
		this.surnameColumn = surnameColumn;
		this.roomColumn = roomColumn;
		setColumns();
	}
	
	//Ustawia typy wartości w poszczególnych kolumnach
	private void setColumns() {
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
		roomColumn.setCellValueFactory(new PropertyValueFactory<>("room"));		
	}	
	
	//Otwiera plik przez FileChooser
	public void openDataFile(){
		dataFile = fileChooser.showOpenDialog(new Stage());		
	}
	
	//Ładuje dane z pliku do tabeli przy użyciu obiektu klasy Scanner
	public void loadData() {
		dataTable.getItems().clear();
		Scanner input = null;
		String name;
		String surname;
		int room;
		int timeStarted;
		int timeFinished;
		try {
			input = new Scanner(dataFile);
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
	
	//Zapisuje dane do pliku przy użyciu obiektu klasy PrintWriter
	public void saveData() {
		PrintWriter output = null;
		try {
			output = new PrintWriter(dataFile);
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

	//Tworzy nowy obiekt typu Employee i dodaje jego dane do tabeli
	public void addData(String name, String surname, int room, int startHour, int finishHour) {
		employeeList.add(new Employee(name, surname, room, startHour, finishHour));
		dataTable.setItems(employeeList);
	}
		
	//Tworzy plik raportu i zapisuje do niego dane z tabeli
	public void createReport() {		
		PrintWriter output = null;
		File reportFile = fileChooser.showSaveDialog(new Stage());
		Employee[] sortedArray = sortData();
		try {
			output = new PrintWriter(reportFile);
			for(int i = 0; i < sortedArray.length; i++) {
				output.println(sortedArray[i].toString());
			}			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(output != null)
				output.close();
		}
	}
	
	//Metoda zwracająca tablicę pracowników posortowanych rosnąco według czasu pracy
	private Employee[] sortData() {
		Employee[] sortedArray = new Employee[employeeList.size()];
		for(int i = 0; i < sortedArray.length; i++){
			sortedArray[i] = employeeList.get(i);
		}
		
		for(int i = 0; i < sortedArray.length - 1; i++){
			for(int j = 0; j < sortedArray.length - 1; j++){
				Employee temp;
				if(sortedArray[j].getTimeWorked() > sortedArray[j + 1].getTimeWorked()){
					temp = sortedArray[j+1];
					sortedArray[j+1] = sortedArray[j];
					sortedArray[j] = temp;
				}
			}			
		}
		return sortedArray;
	}
}
