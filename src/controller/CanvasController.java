package controller;

import java.util.HashMap;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Room;

public class CanvasController {
	private Canvas canvas;
	private GraphicsContext gc;
	private HashMap<Integer, Room> roomMap = new HashMap<Integer, Room>();
	
	//Konstruktor CanvasController, przyjmuje parametr typu Canvas i tworzy zmienną zawierająca GraphicsContext
	public CanvasController(Canvas canvas) {
		this.canvas = canvas;
		gc = this.canvas.getGraphicsContext2D();
		addRoom(1, 0, 0, 100, 100);
		addRoom(2, 100, 0, 100, 100);
		addRoom(3, 200, 0, 100, 100);
		addRoom(4, 300, 0, 100, 100);
	}
	
	//Tworzy pokój o określonym numerze i pozycji na planie biura, dodaje do hashmapy
	public void addRoom(int number, double x, double y, double width, double height) {
		Room newRoom = new Room(number, x, y, width, height);
		roomMap.put(number, newRoom);
	}
	
	//Wypełnia cały canvas na biało
	public void fillCanvas() {
		gc.setFill(Color.WHITE);
		gc.fillRect(0,  0,  canvas.getWidth(), canvas.getHeight());
		gc.setStroke(Color.BLACK);
	}
	
	//Rysuje kontur wybranego pokoju
	private void drawRoom(int roomNumber) {
		gc.setStroke(Color.BLACK);
		gc.strokeRect(
				roomMap.get(roomNumber).getX(), 
				roomMap.get(roomNumber).getY(), 
				roomMap.get(roomNumber).getWidth(), 
				roomMap.get(roomNumber).getHeight()
			);
	}
	
	//Wypełnia wybrany pokój kolorem
	public void fillRoom(int roomNumber) {
		gc.setFill(Color.LIME);
		gc.fillRect(
				roomMap.get(roomNumber).getX()+1, 
				roomMap.get(roomNumber).getY()+1, 
				roomMap.get(roomNumber).getWidth()-2, 
				roomMap.get(roomNumber).getHeight()-2
			);
	}	
	
	//Rysuje kontury wszystkich pokoi zawartych w mapie pokoi
	public void drawAllRooms() {
		roomMap.forEach((key, value) -> drawRoom(key));
	}

}
