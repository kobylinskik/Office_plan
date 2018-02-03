package model;

public class Room {
	private int number;
	private double x;
	private double y;
	private double width;
	private double height;
	
	public Room(int number, double x, double y, double width, double height) {
		this.number = number;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public int getNumber() {
		return number;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getWidth() {
		return width;
	}
	
	public double getHeight() {
		return height;
	}
}
