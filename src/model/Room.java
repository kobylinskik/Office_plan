package model;

public class Room {
	private static int count = 0;
	private int number;
	private double x;
	private double y;
	private double width;
	private double height;
	
	public Room(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		number = ++count;
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
