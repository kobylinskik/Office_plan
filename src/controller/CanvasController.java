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
	
	public CanvasController(Canvas canvas) {
		this.canvas = canvas;
		gc = this.canvas.getGraphicsContext2D();
		roomMap.put(1, new Room(20, 20, 150, 150));
	}
	
	public void fillCanvas() {
		gc.setFill(Color.WHITE);
		gc.fillRect(0,  0,  canvas.getWidth(), canvas.getHeight());
		gc.setStroke(Color.BLACK);
		gc.strokeRect(10, 10,  200,  200);
	}
	
	public void drawRoom(int roomNumber) {
		gc.setStroke(Color.BLACK);
		gc.strokeRect(
				roomMap.get(roomNumber).getX(), 
				roomMap.get(roomNumber).getY(), 
				roomMap.get(roomNumber).getWidth(), 
				roomMap.get(roomNumber).getHeight()
			);
	}
	
	public void fillRoom(int roomNumber) {
		gc.setFill(Color.LIME);
		gc.fillRect(
				roomMap.get(roomNumber).getX(), 
				roomMap.get(roomNumber).getY(), 
				roomMap.get(roomNumber).getWidth(), 
				roomMap.get(roomNumber).getHeight()
			);
	}	

}
