package application;

public class Trap {
	int id;	// for the identity of the trap
	int x;	// position of trap - X axis
	int y;	// position of trap - Y axis
	int points;	// points that you lose
	String type; // type of the trap
	
	public Trap() {		// 1st constructor with no arguments
		id = 0;
		x = 0;
		y = 0;
		points = 0;
		type = null;
	}
	public Trap(Trap t) {	// 2nd constructor with an object of class Trap as an argument
		this.id = t.id;
		this.x = t.x;
		this.y = t.y;
		this.type = t.type;
		this.points = t.points;
	}
	public void setId(int id) {		//setters getters
		this.id = id;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getPoints() {
		return points;
	}
	public String getType() {
		return type;
	}
	
	public boolean IsInTheLimits(int inLimit) {	// this method checks if the object (trap) created is outside the FoodAreaLimits board
		if ((Math.abs(getX()) > inLimit) || (Math.abs(getY()) > inLimit))
			return true;
		else
			return false;
	}
}
