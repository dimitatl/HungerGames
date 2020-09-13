package application;

import java.util.ArrayList;
import java.util.Scanner;

public class ManualPlayer extends HeuristicPlayer{
	/*
	 * path[0] = dice
	 * path[1] = points
	 * path[2] = if weapon
	 * path[3] = if food
	 * path[4] = if trap
	 * path[5] = roundPlayer
	 */
	ArrayList<Integer[][]> path = new ArrayList<>();	//created to put in it the information above
	int r;				//shows "how far" the player can see
	int roundPlayer;	//a counter of the rounds (we use it in our functions)
	
	public ManualPlayer() {		// 1st constructor with no arguments (variables just like the class PLayer)
		id = 0;
		roundPlayer = 0;
		name = null;
		score = 0;
		x = 0;
		y = 0;
		bow = null;
		pistol = null;
		sword = null;
		r = 3;
		roundPlayer = 0;
	}
	
	public ManualPlayer (int id, String name, int x, int y, Board board, int r) {	// 2nd constructor with specific arguments
		this.id = id;
		this.x = x;
		this.y = y;
		this.name = name;
		this.board = board;
		this.r = r;
		roundPlayer = 0;
	}
	
	public ManualPlayer (ManualPlayer p) {		// 3rd constructor with an object of class HeuristicPlayer as an argument
		id = p.id;
		board = p.board;
		name = p.name;
		score = p.score;
		x = p.x;
		y = p.y;
		bow = p.bow;
		pistol = p.pistol;
		sword = p.sword;
		r = p.r;
		roundPlayer = p.roundPlayer;
	}
	
	
	/*
	 * It returns true if the player can kill the other player 
	 * (the player has the pistol and the distance of the other player is < d)
	 */
	static boolean kill(Player p1, Player p2, float d) {
		ManualPlayer pp1 = new ManualPlayer();
		pp1.setX(p1.getX());
		pp1.setY(p1.getY());
		
		double playersDistance = pp1.distanceFrom(p2.getX(), p2.getY());
		
		if (playersDistance < d && playersDistance > 0 && p1.pistol != null) 
			return true;
		else 
			return false;
	}
	
	public static void wait(int ms){				// we created a function that can be used for delays in the code: wait() 
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }
	
	/*
	 * It returns a board with the coordinates (x, y) of the new position of the player (it "moves" the player)
 	 * The dice is chosen by the human_player. We check firstly if the move that the dice indicates is IN the borders of the board.
	 * Before we return the the coordinates, we put the results of that move in the ArrayList<> (the dice chosen, the points it took,
	 * if it took a weapon, if it took food, if it went on a trap)
	 */
	public int[] moveItGood(Player p, Scanner input){
		
		//Scanner input = new Scanner(System.in);
		int x_x = 0;
		int y_y = 0;
		int bestDice = 0;
		float [][] weaponInfo = weaponDistance();
		float [][] foodInfo = foodDistance();
		float [][] trapInfo = trapDistance();
 		
		while(true) {
			
			System.out.println("\t 1 -> up\n"
						+ "\t 2 -> up right\n"
						+ "\t 3 -> right\n"
						+ "\t 4 -> down right\n"
						+ "\t 5 -> down\n"
						+ "\t 6 -> down left\n"
						+ "\t 7 -> left\n"
						+ "\t 8 -> up left\n");
			
			System.out.println("The weapons nearby (distance < r) are: ");
			
			for (int i = 0; i < board.getW(); i++) {
				if (weaponInfo[i][0] > 0) {
					System.out.print("\t At distance: " + weaponInfo[i][0] + " and at direction: " + weaponInfo[i][1]);
				}
				else {
					continue;
				}
				if (weaponInfo[i][2] == 1) {
					System.out.print(" and it is a pistol");
				}
				System.out.println();
			}
			
			System.out.println("The food nearby (distance < r) is: ");
			
			for (int i = 0; i < board.getF(); i++) {
				if (foodInfo[i][0] != -1) {
					System.out.println("\t At distance: " + foodInfo[i][0] + " at direction: " + foodInfo[i][1] + " with points: " + foodInfo[i][2]);
				}
			}
			
			System.out.println("The traps nearby (distance < r) are: ");
			
			for (int i = 0; i < board.getT(); i++) {
				if (trapInfo[i][0] != -1) {
					System.out.println("\t At distance: " + trapInfo[i][0] + " at direction: " + trapInfo[i][1] + " with penalty: " + trapInfo[i][2]);
				}
			}
			
			System.out.print("Give me the dice for your new position: \n\t DICE = "); 
			//wait(5000);
			bestDice = input.nextInt();
			
			/*
			if(input.hasNextInt())
			     bestDice = input.nextInt(); // if there is another number  
			else 
			     bestDice = input.nextInt(); // nothing added in the input 
			*/
			
			if (bestDice == 1) {
				x_x = this.x - 1;
				y_y = this.y;
			}
			else if (bestDice == 2) {
				x_x = this.x - 1;
				y_y = this.y + 1;
			}
			else if (bestDice == 3) {
				x_x = this.x;
				y_y = this.y + 1;
			}
			else if (bestDice == 4) {
				x_x = this.x + 1;
				y_y = this.y + 1;
			}
			else if (bestDice == 5) {
				x_x = this.x + 1;
				y_y = this.y;
			}
			else if (bestDice == 6) {
				x_x = this.x + 1;
				y_y = this.y - 1;
			}
			else if (bestDice == 7) {
				x_x = this.x;
				y_y = this.y - 1;
			}
			else if (bestDice == 8){
				x_x = this.x - 1;
				y_y = this.y - 1;
			}
			else {
				System.out.println("You are out of bounds, what an idiot, you lost.");
				continue;
			}
			
			if (isAllowed(bestDice)) {
				setX(x_x);
				setY(y_y);
				break;
			}
			else {
				System.out.println("You are out of bounds, what an idiot, you lost.");
				continue;
			}
		}
		
		int Movement [] = {getX(), getY()};
		
		int [][] results = moveResult(Movement[0], Movement[1]);
		
		Integer[][] data = new Integer[6][2];
		
		data[0][0] = bestDice;			//dice
		data[0][1] = 0;					
		data[1][0] = results[0][0];		//points	
		data[1][1] = 0;
		data[2][0] = results[1][0];		//weapon or not
		data[2][1] = results[1][1];		//where on weapons[]
		data[3][0] = results[2][0];		//food or not
		data[3][1] = results[2][1];		//where on food[]
		data[4][0] = results[3][0];		//trap or not
		data[4][1] = results[3][1];		//where on traps[]
		data[5][0] = roundPlayer;		//roundPlayer
		data[5][1] = 0;
		
		path.add(roundPlayer, data);
		
		setRoundPlayer(++roundPlayer);
		
		return Movement;
	}
	
	public void statisticsManual() {
		
		int j = path.get(roundPlayer - 1)[2][1];
		int t = path.get(roundPlayer - 1)[4][1];
		int points = path.get(roundPlayer - 1)[1][0];
		
		System.out.println("\nPlayer " + name + " chose the " + path.get(roundPlayer - 1)[0][0] + " dice!\n");
		if (path.get(roundPlayer - 1)[2][0] == 1)
			System.out.println("Congrats, you found a WEAPON! It is a " + board.weapons[j].getType() + "\n");
		if (path.get(roundPlayer - 1)[3][0] == 1)
			System.out.println("Congrats, you found food! + " + points + " points gained!\n");
		
		if (path.get(roundPlayer - 1)[4][0] == 1) {
			if((board.traps[t].getType() == "rope" && sword != null) || (board.traps[t].getType() == "animal" && bow != null)) {
				System.out.println("You fell into a TRAP, but you escaped it! NO points lost\n");
			}
			else {
				System.out.println("Ooops, you fell into a TRAP! " + points + " points lost!\n");
			}
		}
	}
}
