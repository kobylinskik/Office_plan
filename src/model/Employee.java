package model;

public class Employee {
	private String name;
	private String surname;
	private int room;
	private int timeStarted;
	private int timeFinished;
	
	public Employee(String name, String surname, int room, int timeStarted, int timeFinished) {
		this.name = name;
		this.surname = surname;
		this.room = room;
		this.timeStarted = timeStarted;
		this.timeFinished = timeFinished;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public int getRoom() {
		return room;
	}
	
	public int getTimeWorked() {
		return timeFinished - timeStarted;
	}
	
	public String toString() {
		return name + " " + surname + " " + room + " " + timeStarted + " " + timeFinished;
	}
}
