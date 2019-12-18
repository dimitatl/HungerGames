/*
 * PAPAFOTIOU THEODOROS - AEM: 9708 - PHONE: 6977021300 - EMAIL: papafotit@ece.auth.gr
 * 
 * TATLI DIMITRA - AEM: 9802 - PHONE: 6971881071 - EMAIL: dimitatl@ece.auth.gr
 * 
 */

package hungerGames2020;

public class Weapon {
	int id;	// for the identity of the weapon
	int x;	// position of weapon - X axis
	int y;	// position of weapon - Y axis
	int playerId;	// identity of the player that can pick the specific weapon
	String type;	// type of the weapon (sword, bow, pistol)
	
	public Weapon () {	// 1st constructor with no arguments
		id = 0;
		x = 0;
		y = 0;
		playerId = 0;
		type = null;
		
	}
	/*
	public Weapon (Weapon a) {		// 2nd constructor with an object of class Weapon as an argument
		a.id = id;
		a.x = x;
		a.y = y;
		a.playerId = playerId;
		a.type = type;
	}
	*/
	public Weapon(Weapon w) {
		this.id = w.id;
		this.x = w.x;
		this.y = w.y;
		this.playerId = w.playerId;
		this.type = w.type;
	}
	public void setId (int id) {		// getters setters
		this.id = id;
	}
	public void setX (int x) {
		this.x = x;
	}
	public void setY (int y) {
		this.y = y;
	}
	public void setPlayerId (int playerId) {
		this.playerId = playerId;
	}
	public void setType (String type) {
		this.type = type;
	}
	public int getId () {
		return id;
	}
	public int getX () {
		return x;
	}
	public int getY () {
		return y;
	}
	public int getPlayerId () {
		return playerId;
	}
	public String getType () {
		return type;
	}	
}
