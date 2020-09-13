/*
 * PAPAFOTIOU THEODOROS 
 * 
 * TATLI DIMITRA
 * 
 */

package hungerGames2020;

public class Food {
	int id;	// for the identity of the food
	int x;	// position of food - X axis
	int y;	// position of food - Y axis
	int points;	// points you gain
	
	public Food() {		// 1st constructor with no arguments
		id = 0;
		x = 0;
		y = 0;
		points = 0;
	}
	/*
	public Food(Food b) {		// 2nd constructor with an object of class Food as an argument
		b.id = id;
		b.x = x;
		b.y = y;
		b.points = points;
	}
	*/
	public Food(Food f) {
		id = f.id;
		x = f.x;
		y = f.y;
		points = f.points;
	}
	public void setId(int id) {		// setters getters
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
	
	public boolean IsInTheLimits(int inLimit) {	// this method checks if the object (food) created is outside the WeaponAreaLimits board
		
		if ((Math.abs(getX()) > inLimit) || (Math.abs(getY()) > inLimit)) 
			return true;
		else 
			return false;
	}
}
