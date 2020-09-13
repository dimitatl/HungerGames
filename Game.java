package application;

public class Game {
	
	int round;		// counter that counts the rounds of the game

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
}
