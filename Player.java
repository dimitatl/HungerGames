/*
 * PAPAFOTIOU THEODOROS - AEM: 9708 - PHONE: 6977021300 - EMAIL: papafotit@ece.auth.gr
 * 
 * TATLI DIMITRA - AEM: 9802 - PHONE: 6971881071 - EMAIL: dimitatl@ece.auth.gr
 * 
 */

package hungerGames2020;

public class Player {
	int id;			// identity of the player (1,2)
	String name;	// name of player
	Board board;	
	int score;		// score of player
	int x;			// position of player - X axis
	int y;			// position of player - Y axis
	Weapon bow;
	Weapon pistol;
	Weapon sword;
	
	public Player() {		// 1st constructor with no arguments
		id = 0;
		name = null;
		score = 0;
		x = 0;
		y = 0;
		bow = null;
		pistol = null;
		sword = null;
	}
	
	public Player (int id, String name, int x, int y, Board board) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.name = name;
		this.board = board;
	}
	
	public Player (Player p) {		// 3rd constructor with an object of class Player as an argument
		id = p.id;
		board = p.board;
		name = p.name;
		score = p.score;
		x = p.x;
		y = p.y;
		bow = p.bow;
		pistol = p.pistol;
		sword = p.sword;
	}
	
	public void setId(int id) {		//setters getters
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setBow(Weapon bow) {
		this.bow = bow;
	}
	public void setPistol(Weapon pistol) {
		this.pistol = pistol;
	}
	public void setSword(Weapon sword) {
		this.sword = sword;
	}
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Board getBoard() {
		return board;
	}
	public int getScore() {
		return score;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Weapon getBow() {
		return bow;
	}
	public Weapon getPistol() {
		return pistol;
	}
	public Weapon getSword() {
		return sword;
	}
	
	/*
	 * This function works like the dice in the game. It returns a random integer (from 1 to 8) 
	 * depending on the movements of the player that are allowed (depending on its position on the Board)
	 */
	public int whatsTheMove() {
		
		int N = board.getN();
		int M = board.getM();
		int k = (((int)(Math.random()*100) % 8) + 1);
		
		
		if (x == -M/2 && y != N/2 && y != -N/2) {			//up side, NO corners
			while (k == 1 || k == 2 || k == 8) {
				k = (((int)(Math.random()*100) % 8) + 1);
			}
		}
		else if (x == M/2 && y != N/2 && y != -N/2) {		//down side, NO corners
			while (k == 4 || k == 5 || k == 6) {
				k = (((int)(Math.random()*100) % 8) + 1);
			}
		}
		else if (y == N/2 && x != M/2 && x != -M/2) {		//right side, NO corners
			while (k == 2 || k == 3 || k == 4) {
				k = (((int)(Math.random()*100) % 8) + 1);
			}
		}
		else if (y == -N/2 && x != M/2 && x != -M/2) {		//left side, NO corners
			while (k == 6 || k == 7 || k == 8) {
				k = (((int)(Math.random()*100) % 8) + 1);
			}
		}
		else if (y == N/2 && x == -M/2) {					//up right corner
			while (k != 5 && k != 6 && k != 7) {
				k = (((int)(Math.random()*100) % 8) + 1);
			}
		}
		else if (y == N/2 && x == M/2) {					//down right corner
			while (k != 1 && k != 7 && k != 8) {
				k = (((int)(Math.random()*100) % 8) + 1);
			}
		}
		else if (y == -N/2 && x == M/2) {					//down left corner
			while (k != 1 && k != 2 && k != 3) {
				k = (((int)(Math.random()*100) % 8) + 1);
			}
		}
		else if (y == -N/2 && x == -M/2) {					//up left corner
			while (k != 5 && k != 3 && k != 4) {
				k = (((int)(Math.random()*100) % 8) + 1);
			}
		}
		
		return k;
	}
	
	
	/*
	 * This function, depending on the k (the return value of the function above)
	 * sets virtually the new coordinates of the player (moves the player) and returns the virtual new position
	 * through a int[] Á board
	 */
	public int[] positions(int k) {
		
		int[] Position = new int[2];  						
		
		int a = y;
		int b = x;
		
		if (k == 1) {
			--b;
			if(b == 0) {
				--b;
			}
		}
		else if (k == 2) {
			--b;
			++a;
			if(b == 0) {
				--b;
			}
			
			if(a == 0) {
				++a;
			}
		}
		else if (k == 3) {
			++a;
			if(a == 0) {
				++a;
			}
		}
		else if (k == 4) {
			++b;
			++a;
			if(b == 0) {
				++b;
			}
			
			if(a == 0) {
				++a;
			}
		}
		else if (k == 5) {
			++b;
			if(b == 0) {
				++b;
			}
		}
		else if (k == 6) {
			++b;
			--a;
			if(b == 0) {
				++b;
			}
			
			if(a == 0) {
				--a;
			}
		}
		else if (k == 7) {
			--a;
			if(a == 0) {
				--a;
			}
		}
		else{				///k = 8
			--b;
			--a;
			if(b == 0) {
				--b;
			}
			
			if(a == 0) {
				--a;
			}
		}
		
		Position[0] = b;
		Position[1] = a;
		
		return Position;
	}
	
	public int[] getRandomMove(){
		
		int[] Movement = new int[2]; 
		
		int k = whatsTheMove();
		
		int[] position = positions(k);
		
		setX(position[0]);
		setY(position[1]);
		
		Movement[0] = getX();
		Movement[1] = getY();
		
		return Movement;
		
	}
	
	/*
	 * Ôhis function counts how many Weapons, Traps, Food a player has taken (or fallen into), 
	 * it makes the score of a player, it prints messages on the console, when the player
	 * takes Weapon, Trap, Food (also prints the points it takes form them).
	 * Finally, it returns an array with the new position of the player (X, Y)
	 * and the counters of Weapons, Food, Traps (countW, countF, countT)
	 */
	
	public int[][] moveResult(int x, int y) {
		
		int countW = 0;
		int countF = 0;
		int countT = 0;
		int points = 0;
		int Wposition = 0;
		int Fposition = 0;
		int Tposition = 0;
		
		//for the weapons 
		for(int i = 0; i < board.getW(); i++) {
			if(board.weapons[i].getX() == x && board.weapons[i].getY() == y && board.weapons[i].getPlayerId() == id) {
				countW++;
				Wposition = i;
				board.weapons[i].setX(0);
				board.weapons[i].setY(0);
				if(board.weapons[i].getType() == "pistol") {
					pistol = board.weapons[i];
				}
				if(board.weapons[i].getType() == "bow") {
					bow = board.weapons[i];
				}
				if(board.weapons[i].getType() == "sword") {
					sword = board.weapons[i];
				}
			}
		}
		
		//for food
		for(int i = 0; i < board.getF(); i++) {
			if(board.food[i].getX() == x && board.food[i].getY() == y) {
				countF++;
				Fposition = i;
				board.food[i].setX(0);
				board.food[i].setY(0);
				points = board.food[i].getPoints();	
				score = score + points;
			}
		}
		
		//for traps
		for(int i = 0; i < board.getT(); i++) {
			if(board.traps[i].getX() == x && board.traps[i].getY() == y) {
				countT++;
				Tposition = i;
				if(!((board.traps[i].getType() == "rope" && sword != null) || (board.traps[i].getType() == "animal" && bow != null))) {
					points = board.traps[i].getPoints();
					score = score + points;
				}
			}
		}
		
		int [][] B = new int[4][2];
		B[0][0] = points;
		B[0][1] = 0;
		B[1][0] = countW;
		B[1][1] = Wposition;
		B[2][0] = countF;
		B[2][1] = Fposition;
		B[3][0] = countT;
		B[3][1] = Tposition;
		
		return B;
	}
	
	public int[] move() {
		
		int [] Move = getRandomMove();
		
		setX(Move[0]);
		setY(Move[1]);
		
		int [][] results = moveResult(Move[0], Move[1]);
		
		int [] A = {Move[0], Move[1], results[0][0], results[1][0], results[2][0], results[3][0]};
		
		return A;	
	}
}
