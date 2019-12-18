/*
 * PAPAFOTIOU THEODOROS - AEM: 9708 - PHONE: 6977021300 - EMAIL: papafotit@ece.auth.gr
 * 
 * TATLI DIMITRA - AEM: 9802 - PHONE: 6971881071 - EMAIL: dimitatl@ece.auth.gr
 * 
 */

package hungerGames2020;

import java.util.Scanner;

public class Game {
	int round;		// counter that counts the rounds of the game
	
	static int moveP1X = 0, moveP1Y = 0, moveP2X = 0, moveP2Y = 0; // used in the GetStringRepresentation() for the position of the players. We need them global, because they change in each round.

	public Game() {					// 1st constructor with no arguments
		round = 1;
	}
	public Game(Game game) {		// 2nd constructor with an object of class Game as an argument
		game.round = round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public int getRound() {
		return round;
	}
	
	/*
	 * The main() of our game. It creates objects of classes Game, Board and Player 
	 * (and Scanner so we can take in from the console the values of our variables that each
	 * human who plays the game give, to create its own Board). 
	 * Initially we ask for the Board_dimension, Weapon_Board_dimension, Food_Board_dimension, 
	 * Trap_Board_dimension, number_of_Weapons, _Food, _Traps.
	 * After that, we set those variables through the Board_constructor to create our Board.
	 * Then the Game begins. Each 3 rounds (if no player is on the borders of the Board) we resize the Board.
	 * In each round the players play alternately and the Board (with the Weapons, Food, Traps, Players on it)
	 */
	public static void main (String[] args) {
		
		Scanner s = new Scanner(System.in);
		
		Game game = new Game();
		
		System.out.print("Give me the size of the GameBoard! (Give an even number)\t");
		int M = s.nextInt();
		while (M <= 4) {
			System.out.print("You gave a very small number. Give a bigger even number:\t");	//****
			M = s.nextInt();
			while(M % 2 != 0) {
				System.out.print("You gave an odd number. Give an even number:\t");
				M = s.nextInt();
			}
		}
		
		System.out.print("Give me the size of the weapon_limits_board! (Give an even number)\t");
		int W_L = s.nextInt();
		while ((W_L % 2 != 0) || (W_L > M - 4)) {	
			while (W_L % 2 != 0) {
				System.out.print("You gave an odd number. Give an even number:\t");
				W_L = s.nextInt();
			}
			while (W_L > M - 4) {
				System.out.print("You gave a number bigger than the GameBoard can handle. Give a smaller even number\t");
				W_L = s.nextInt();
			}
		}
		
		System.out.print("How many weapons do you want? (Give an even number)\t");
		int W = s.nextInt();
		while ((W % 2 != 0) || (W > Math.pow(W_L, 2))){	
			while (W % 2 != 0) {
				System.out.print("You gave an odd number. Give an even number:\t");
				W = s.nextInt();
			}
			while (W > Math.pow(W_L, 2)) {
				System.out.print("There are too many weapons for the weapon_board you chose. Give a smaller number\t");
				W = s.nextInt();
			}
		}
		
		System.out.print("Give me the size of the food_limits_board! (Give an even number bigger than the weapon_limits_board)\t");
		int F_L = s.nextInt();
		while ((F_L % 2 != 0) || (F_L <= W_L) || (F_L > M - 2)) {	
			while (F_L % 2 != 0) {
				System.out.print("You gave an odd number. "
									+ "Give an even number bigger than the weapon_limits_board:\t");
				F_L = s.nextInt();
			}
			while (F_L <= W_L) {
				System.out.print("You gave a number smaller than the weapon_limits_board. "
									+ "Give an even number bigger than the weapon_limits_board:\t");
				F_L = s.nextInt();
			}
			while (F_L > M - 2) {
				System.out.print("You gave a number bigger than the GameBoard can handle. Give a smaller even number\t");
				F_L = s.nextInt();
			}
		}
		
		System.out.print("How many food_elements do you want?\t");
		int F = s.nextInt();
		while (F > (Math.pow(F_L, 2)) - (Math.pow(W_L, 2))) {
			System.out.print("There are too many food_elements for the food_board you chose. Give a smaller number\t");
			F = s.nextInt();
		}
		
		System.out.print("Give me the size of the trap_limits_board! (Give an even number bigger than the food_limits_board)\t");
		int T_L = s.nextInt();
		while ((T_L % 2 != 0) || (T_L <= F_L) || (T_L > M)) {	
			while (T_L % 2 != 0) {
				System.out.print("You gave an odd number. "
									+ "Give an even number bigger than the food_limits_board:\t");
				T_L = s.nextInt();
			}
			while (T_L <= F_L) {
				System.out.print("You gave a number smaller than the food_limits_board. "
									+ "Give an even number bigger than the food_limits_board:\t");
				T_L = s.nextInt();
			}
			while (T_L > M) {
				System.out.print("You gave a number bigger than the GameBoard can handle. Give a smaller even number\t");
				T_L = s.nextInt();
			}
		}
		
		System.out.print("How many traps do you want?\t");
		int T = s.nextInt();
		while (T > (Math.pow(T_L, 2)) - (Math.pow(F_L, 2))) {
			System.out.print("There are too many traps for the trap_board you chose. Give a smaller number\t");
			T = s.nextInt();
		}
		
		if (s != null) {
			s.close();
		}
		
		/*
		int repeats = 0;
		int winsP1 = 0;
		int winsP2 = 0;
		int killing1 = 0;
		int killing2 = 0;
		int paused = 0;
		*/
		/*
		 * we use this while and all commands after (//) to check the possibilities of winning the 1st player
		 * and to create the factors that are multiplied with the variables in the evaluate()
		 */
		//while (repeats < 10000) {
	
		//game.setRound(0);	
			
		Board boardGame = new Board(M, M, W, F, T, W_L/2, F_L/2, T_L/2);
		
		HeuristicPlayer p1 = new HeuristicPlayer(1, "Thodoris", M/2, M/2, boardGame, 3);
		MinMaxPlayer p2 = new MinMaxPlayer(2, "Dimitra", M/2, M/2, boardGame, 3);
		
		p1.setScore(15);
		p2.setScore(15);
		
		System.out.println("The players are: " + p1.name + " and " + p2.name + "\n");
			
		boardGame.setM(M);
		boardGame.setN(M);
		boardGame.setW(W);
		boardGame.setF(F);
		boardGame.setT(T);
		boardGame.setA(W_L/2);
		boardGame.setB(F_L/2);
		boardGame.setC(T_L/2);
		boardGame.createBoard();
		
		for (int i = 0; i < T; i++)
			System.out.println("The trap points of T" + boardGame.traps[i].getId() + " are: " + boardGame.traps[i].getPoints());
		
		for (int i = 0; i < F; i++)
			System.out.println("The food points of F" + boardGame.food[i].getId() + " are: " + boardGame.food[i].getPoints());
		
		System.out.println("I have set all the board variables\n");
		
		while (boardGame.getN() > W_L && boardGame.getM() > W_L) {
			
			System.out.println("We are at ROUND " + game.getRound() + " !");
			
			if (game.getRound() % 3 == 0) {
				boardGame.resizeBoard(p1, p2);
			}
			
			if (game.getRound() % 2 == 1) {
				
				if (HeuristicPlayer.kill(p1, p2, 2)) {
					System.out.println("\nPlayer P1 KILLS THE OPPONENT!! PLAYER 1 WINS!!\n");
					//winsP1++;
					//killing1++;
					break;
				}
				
				int [] movePlayer1 = p1.moveIt(p2);
				p1.statistics();
				moveP1X = movePlayer1[0];
				moveP1Y = movePlayer1[1];
				System.out.println("The player P1 goes to position: x = " + movePlayer1[0] + "\ty = " + movePlayer1[1] + "\n");
				
				if (HeuristicPlayer.kill(p1, p2, 2)) {
					System.out.println("\nPlayer P1 KILLS THE OPPONENT!! PLAYER 1 WINS!!\n");
					//winsP1++;
					//killing2++;
					break;
				}
			}
			else if (game.getRound() % 2 == 0) {
				
				if (HeuristicPlayer.kill(p2, p1, 2)) {
					//System.out.println("\nPlayer P2 KILLS THE OPPONENT!! PLAYER 2 WINS!!\n");
					//winsP2++;
					break;
				}
				
				int [] movePlayer2 = p2.getNextMove(p2.getX(), p2.getY(), p1);
				p2.statistics();
				moveP2X = movePlayer2[0];
				moveP2Y = movePlayer2[1];
				System.out.println("The player P2 goes to position: x = " + movePlayer2[0] + "\ty = " + movePlayer2[1] + "\n");
				
				if (HeuristicPlayer.kill(p2, p1, 2)) {
					System.out.println("\nPlayer P2 KILLS THE OPPONENT!! PLAYER 2 WINS!!\n");
					//winsP2++;
					break;
				}
			}

			if (game.round > 500) {
				System.out.println("GAME PAUSED");
				//paused++;
				break;
			}
			/*
			if (p1.getScore() < 0) {
				System.out.println("Player P2 wins the game!! The other player doesn't know how to play!");
				break;
			}
			if (p2.getScore() < 0) {
				System.out.println("Player P1 wins the game!! The other player doesn't know how to play!");
				break;
			}*/
			game.setRound(++game.round);
			boardGame.getStringRepresentation(moveP1X, moveP1Y, moveP2X, moveP2Y);
			
		}
		//repeats++;
		if(boardGame.getN() <= W_L && boardGame.getM() <= W_L) {
			System.out.println("!GAME IS OVER!");
			System.out.println("The score of P1 " + p1.getName() + " is: " + p1.getScore() + " and the score of P2 " + p2.getName() + " is: " + p2.getScore());
			if (p1.getScore() > p2.getScore()) {
				System.out.println("\nPlayer P1 wins the game!!\n");
				//winsP1++;
			}
			
			else if (p1.getScore() < p2.getScore()) {
				System.out.println("\nPlayer P2 wins the game!!\n");
				//winsP2++;
			}
			else
				System.out.println("\nWe have a draw!!");
		}
		
	}
//System.out.println("The wins of P1 are: " + winsP1 + " of which kills: " + killing1 + "  " + killing2 + " and paused: " + paused);
//System.out.println("The wins of P2 are: " + winsP2);
//}
}	
