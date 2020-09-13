package application;

import java.util.ArrayList;

public class MinMaxPlayer extends Player{
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
	int chooseStrategy;	//it chooses one of the 3 strategies we have created, before the player goes near food or weapon
	public int almostBestDice; //used for a comparison when the bestDice of the pseudorandom move is chosen.
	
	public MinMaxPlayer() {		// 1st constructor with no arguments (variables just like the class PLayer)
		id = 0;
		roundPlayer = 0;
		name = null;
		score = 0;
		x = 0;
		y = 0;
		bow = null;
		pistol = null;
		sword = null;
		type = "MinMax";
		r = 3;
		roundPlayer = 0;
	}
	
	public MinMaxPlayer (int id, String name, int x, int y, Board board, int r) {	// 2nd constructor with specific arguments
		this.id = id;
		this.x = x;
		this.y = y;
		this.name = name;
		this.board = board;
		this.r = r;
		this.type = "MinMax";
		roundPlayer = 0;
		setChooseStrategy((int)((Math.random() * 100) % 3));	//choose the strategy at the beginning of each game
	}
	
	public MinMaxPlayer (MinMaxPlayer p) {		// 3rd constructor with an object of class HeuristicPlayer as an argument
		id = p.id;
		board = p.board;
		name = p.name;
		score = p.score;
		x = p.x;
		y = p.y;
		bow = p.bow;
		pistol = p.pistol;
		sword = p.sword;
		type = p.type;
		r = p.r;
		roundPlayer = p.roundPlayer;
		chooseStrategy = p.chooseStrategy;
	}
	
	public void setR(int r) {
		this.r = r;
	}
	public int getR() {
		return r;
	}
	public void setRoundPlayer(int roundPlayer) {
		this.roundPlayer = roundPlayer;
	}
	public int getRoundPlayer() {
		return roundPlayer;
	}
	public void setChooseStrategy(int chooseStrategy) {
		this.chooseStrategy = chooseStrategy;
	}
	public int getChooseStrategy() {
		return chooseStrategy;
	}
	
	/*
	 *	It returns the distance from the object we want (player, weapon, food, trap) 
	 *
	 *  Since we calculate the Cartesian distance, the distance = [1,2) is the field r = 1 around the player,
	 *  the distance = [2, 3) is the field r = 2 around the player etc
	 */
	public float distanceFrom(int xx, int yy) {
		
		int m, n, s, t;
		
		if(x*xx > 0 && y*yy > 0) {		// if player and object are on the same quadrant
			m = x;
			n = xx;
			s = y;
			t = yy;
		}
		else if(x*xx > 0 && y*yy < 0) {	// if player and object are on the quadrant 1 - 2, 3 - 4 relatively (and vice versa)	
			m = x;
			n = xx;
			if (y < 0) {
				s = y + 1;
				t = yy;
			}
			else {
				s = y;
				t = yy + 1;
			}
		}
		else if(x*xx < 0 && y*yy > 0) {	// if player and object are on the quadrant 1 - 4, 2 - 3 relatively (and vice versa)
		    s = y;
			t = yy;
			if (x < 0) {
				m = x + 1;
				n = xx;
			}
			else {
				m = x;
				n = xx + 1;
			}
		}	
		else {							// if player and object are on the quadrant 1 - 3, 2 - 4 relatively (and vice versa)
			if (x < 0) {
				m = x + 1;
				n = xx;
			}
			else {
				m = x;
				n = xx + 1;
			}
			
			if (y < 0) {
				s = y + 1;
				t = yy;
			}
			else {
				s = y;
				t = yy + 1;
			}
		}
			
		float distance = (float)Math.sqrt(Math.pow(m - n, 2) + Math.pow(s - t, 2));
		
		if (distance < (getR() + 1)) 
			return distance;
		
		else 
			return -1;
	}
	
	/*
	 * It indicates in which direction is the object we want to find (player, weapon, food, trap) 
	 */
	public int direction(int xx, int yy) {
		if (x == xx && y != yy) {
			if (y > yy)
				return 7;
			else 
				return 3;
		}
		else if (x != xx && y == yy) {
			if(x > xx)
				return 1;
			else
				return 5;
		}
		else {
			if (x > xx && y > yy)
				return 8;
			else if (x > xx && y < yy)
				return 2;
			else if (x < xx && y > yy)
				return 6;
			else
				return 4;
		}
	}
	
	/*
	 * It returns a board with information about the distance of a weapon from a player
	 * (-1 if distance > r), about the direction of that weapon (if distance > r, direction = 0)
	 * and informs us if the weapon is a pistol or not
	 * P.S. All that weapons are the ones that the player can take (getPlayerId() == id)
	 */
	public float [][] weaponDistance() 	{
				
		float[][] weaponInfo = new float[board.getW()][3];
		
		for (int i = 0; i < board.getW(); i++) {										
			int xx = board.weapons[i].getX();
			int yy = board.weapons[i].getY();
			
			int direction = 0;
			int pistol = 0;
			if (board.weapons[i].getType() == "pistol")
				pistol = 1;
			
			float weaponDistance = distanceFrom(xx, yy);
			if(weaponDistance != -1)
				direction = direction(xx, yy);
			
			if (board.weapons[i].getPlayerId() != id)
				weaponDistance = 0;
			
			weaponInfo[i][0] = weaponDistance;
			weaponInfo[i][1] = direction;
			weaponInfo[i][2] = pistol;
		}
		
		return weaponInfo;
	}
	
	/*
	 * It returns a board with information about the distance of a food from a player
	 * (-1 if distance > r), about the direction of that food (if distance > r, direction = 0)
	 * and about the points which that food gives.
	 */
	public float [][] foodDistance() {
		
		float[][] foodInfo = new float[board.getF()][3];
		
		for (int i = 0; i < board.getF(); i++) {										
			int xx = board.food[i].getX();
			int yy = board.food[i].getY();
			
			int direction = 0;

			float foodDistance = distanceFrom(xx, yy);
			if(foodDistance != -1)
				direction = direction(xx, yy);
			
			
			foodInfo[i][0] = foodDistance;
			foodInfo[i][1] = direction;
			foodInfo[i][2] = board.food[i].getPoints();
		}
		
		return foodInfo;
	}
	
	/*
	 * It returns a board with information about the distance of a trap from a player
	 * (-1 if distance > r), about the direction of that trap (if distance > r, direction = 0)
	 * and about the points which that trap "takes".
	 */
	public float [][] trapDistance() {
		
		float[][] trapInfo = new float[board.getT()][3];
		
		for (int i = 0; i < board.getT(); i++) {										
			int xx = board.traps[i].getX();
			int yy = board.traps[i].getY();
			
			int direction = direction(xx, yy);

			float trapDistance = distanceFrom (xx, yy);
			
			trapInfo[i][0] = trapDistance;
			trapInfo[i][1] = direction;
			trapInfo[i][2] = board.traps[i].getPoints();
		}
		
		return trapInfo;
	}
	
	/*
	 * It indicates if the dice (taken as an input) leads to an allowed move 
	 * It returns true if the move is allowed, false if not
	 */
	public boolean isAllowed(int k) {
				
		int M = board.getM();
		int N = board.getN();
			
			if (y == -N/2 && x != M/2 && x != -M/2) {			//left side, NO corners
				if (k < 6) {
					return true;
				}
				else {
					return false;
				}
			}
			else if (y == N/2 && x != M/2 && x != -M/2) {		//right side, NO corners
				if (k == 1 || k > 4) {
					return true;
				}
				else {
					return false;
				}
			}
			else if (x == M/2 && y != N/2 && y != -N/2) {		//down side, NO corners
				if (k < 4 || k > 6) {
					return true;
				}
				else {
					return false;
				}
			}
			else if (x == -M/2 && y != N/2 && y != -N/2) {		//up side, NO corners
				if (k > 2 && k < 8) {
					return true;
				}
				else {
					return false;
				}
			}
			else if (x == -M/2 && y == N/2) {					//up right corner
				if (k > 4 && k < 8) {
					return true;
				}
				else {
					return false;
				}
			}
			else if (x == M/2 && y == N/2) {					//down right corner
				if (k == 1 || k == 7 || k == 8) {
					return true;
				}
				else {
					return false;
				}
			}
			else if (x == M/2 && y == -N/2) {					//down left corner
				if (k < 4) {
					return true;
				}
				else {
					return false;
				}
			}
			else if (x == -M/2 && y == -N/2) {					//up left corner
				if (k == 3 || k == 4 || k == 5) {
					return true;
				}
				else {
					return false;
				}
			}
			else {
				return true;
			}
	}
	
	/*
	 * It returns the dice of the best pseudorandom move we can make each time
	 * (if we are going to make a pseudorandom move)
	 * Our move depends on one of the 3 strategies which is chosen randomly at the start of each game
	 * and also the roundPlayer (the times each Player makes move).
	 * Finally we check if using this dice the player goes ON a trap or out of borders and we turn the dice into 0.
	 */
	public int moveRandom(Player opponent) {
		
		int bestDice = 0;
		
			if (getX() < 0 && getY() < 0) {			//2nd quadrant
										
				if(getChooseStrategy() == 0) {
					bestDice = 4;
				}
				else if(getChooseStrategy() == 1) {
					
					if (roundPlayer == 0) {
						bestDice = 5;
					}
					else{
						bestDice = 4;
					}
				}
				else {
					if (roundPlayer == 0) {
						bestDice = 3;
					}
					else {
						bestDice = 4;
					}
				}
			}
			else if (getX() > 0 && getY() < 0) {		//3rd quadrant
				
				if(getChooseStrategy() == 0) {
					bestDice = 2;
				}
				else if(getChooseStrategy() == 1) {
					if (roundPlayer == 0)
						bestDice = 3;
					else
						bestDice = 2;	
				}
				else {
					if (roundPlayer == 0)
						bestDice = 1;
					else
						bestDice = 2;
				}
			}
			else if (getX() > 0 && getY() > 0) {		//4th quadrant
				if(getChooseStrategy() == 0) {
					bestDice = 8;
				}
				else if(getChooseStrategy() == 1) {
					if (roundPlayer == 0)
						bestDice = 7;
					else
						bestDice = 8;
				}
				else {
					if (roundPlayer == 0)
						bestDice = 1;
					else
						bestDice = 8;
				}
			}
			else {										//1st quadrant
				if(getChooseStrategy() == 0) {
					bestDice = 6;
				}
				else if(getChooseStrategy() == 1) {
					if (roundPlayer == 0)
						bestDice = 7;
					else
						bestDice = 6;
				}
				else {
					if (roundPlayer == 0)
						bestDice = 5;
					else
						bestDice = 6;
				}
			}	
			
			int myPistolX = 0;
			int myPistolY = 0;
			int oppPistolX = 0;
			int oppPistolY = 0;
			
			for (int j = 0; j < board.getW(); j++) {
				if (board.weapons[j].type == "pistol" && board.weapons[j].id == this.id) {
					myPistolX = board.weapons[j].getX();
					myPistolY = board.weapons[j].getY();
				}
				if (board.weapons[j].type == "pistol" && board.weapons[j].id != this.id) {
					oppPistolX = board.weapons[j].getX();
					oppPistolY = board.weapons[j].getY();
				}
			}
			if ((distanceFrom(myPistolX, myPistolY) > distanceFrom(oppPistolX, oppPistolY)) 
					&& distanceFrom(opponent.getX(), opponent.getY()) < 3 && getRoundPlayer() != 0) {
				bestDice -= 1;
			}	
			
			int [] Positions = positions(bestDice);
			for (int j = 0; j < board.getT(); j++) {
				if(Positions[0] == board.traps[j].x && Positions[1] == board.traps[j].y)
					bestDice = 0;
			}
			if((Math.abs(Positions[0]) > (board.getM()/2)) || (Math.abs(Positions[1]) > (board.getN()/2)))
				bestDice = 0;
		
		return bestDice;
	}
	
	/*
	 * It makes the evaluation of each dice (1 - 8), considering the points a food gives (gainPoints, gainPointsD),
	 * the points a trap "takes" (avoidTraps),
	 * the kind of the weapon that is near (pistol or the other 2)(gainWeapons, gainWeaponPistol, gainWeaponPistolD), 
	 * the fact that a player has the pistol and the other player is near (distance < r)(ForceKill), 
	 * the fact that the player has NO pistol and the other player is near (distance < r)(runAway) 
	 * and the fact that there is nothing around the player, so it has to make a pseudorandom move (JustMove).
	 * All those variables are multiplied with specific factors (A - G) relatively. 
	 * At the start of the function, the function moveRandom is called so we can choose the "best" pseudorandom move
	 * 
	 * P.S. The gainPointsD and gainWeaponPistolD contain the letter "D" because they are activated 
	 * 		if the food or weapon is at distance bigger than 2 (used in our strategy)
	 */
	public double evaluate(int dice, int x, int y, Player opponent) {
		
		double A = 5;		//food
		double A1 = 3;		//food from distance
		double B = 1;		//weapon
		double C = 70;		//pistol
		double C1 = 60;		//pistol from distance
		double D = 2;		//traps
		double E = 1000;	//kill	
		double F = 0.1;		//move
		double G = -1000;	//RUN away
		
		if (dice == 1)
			almostBestDice = moveRandom(opponent);
		
		int avoidTraps = 0, gainPoints = 0, gainPointsD = 0, gainWeapons = 0;
		int gainWeaponPistol = 0, gainWeaponPistolD = 0, ForceKill = 0, JustMove = 0, runAway = 0;
		int [] position = positions(dice);				// it moves virtually the player in the direction of the dice
		float[][] weaponDistance = weaponDistance();
		float[][] foodDistance = foodDistance();
		float[][] trapDistance = trapDistance();
		Board board = opponent.getBoard();				// we want the virtually changed board
		
		float playersDistance = distanceFrom(opponent.x, opponent.y);
		int direction = direction(opponent.x, opponent.y);
		
		if (isAllowed(dice) == false)
			return -(Double.MAX_VALUE);
		
		for (int i = 0; i < board.getW(); i++) {
			if (weaponDistance[i][1] == dice && weaponDistance[i][0] > 0) {
				if (weaponDistance[i][2] == 1) {
					if(weaponDistance[i][0] < 2)
						gainWeaponPistol = 1;
					else
						gainWeaponPistolD = 1;
				}
				else {
					gainWeapons = 1;
				}
			}
		}
		
		for (int i = 0; i < board.getF(); i++) {
			if (foodDistance[i][1] == dice && foodDistance[i][0] > 0) {
				gainPoints = (int)foodDistance[i][2];
				if (foodDistance[i][0] > 2)
					gainPointsD = 1;
			}
		}
		
		for (int i = 0; i < board.getT(); i++) {
			if (trapDistance[i][1] == dice && trapDistance[i][0] > 0 && trapDistance[i][0] < 2) {
				avoidTraps = (int)trapDistance[i][2];
			}
		}
		
		if (playersDistance > 0 &&  direction == dice && pistol != null) {
			ForceKill = 1;
		}
		else if (playersDistance > 0 && playersDistance < 3 && direction == dice && pistol == null && opponent.pistol != null) {
			runAway = 1;
		}
	
		if (x != position[0] && y != position[1]) {	// if the direction is diagonal (different x, different y in the new position)
			JustMove = 1;
		}
		
		if (almostBestDice == dice)
			JustMove = 2;
		
		return A*gainPoints + A1*gainPointsD + B*gainWeapons + C*gainWeaponPistol + C1*gainWeaponPistolD 
				+ D*avoidTraps + E*ForceKill + F*JustMove + G*runAway;
	}

	/*
	 * It returns a board with the coordinates (x, y) of the new position of the player (it "moves" the player)
	 * The function uses the MinMax Tree created by the function createMySubTree and calls the function chooseMinMaxMove.
	 * If the evaluation is <-900 and >-2000 (random value bigger than -double.MAX_VALUE), 
	 * the runAway variable is activated, so the player goes at the opposite direction 
	 * of that evaluation's dice checking that it doesn't go out of the borders.
	 * 
	 * Before we return the the coordinates, we put the results of that move in the ArrayList<> (the dice chosen, the points it took,
	 * if it took a weapon, if it took food, if it went on a trap)
	 */
	public int[] getNextMove (int xCurrentPos, int yCurrentPos, HeuristicPlayer opponent) {
		
		int [] Movement = new int[2];
		int dice = 0;
		int [] info = {xCurrentPos, yCurrentPos, dice};
		
		Node root = new Node(null, info, 0);

		createMySubtree(root, 1, xCurrentPos, yCurrentPos, opponent);
		
		int bestDice = chooseMinMaxMove(root);
					
		if (evaluate(bestDice, xCurrentPos, yCurrentPos, opponent) < -900 
				&& evaluate(bestDice, xCurrentPos, yCurrentPos, opponent) > - 2000) {		//when RunAway is activated
			int j = 4;
			while(j > 0) {
				if (bestDice < 5)
					bestDice += j;
				else
					bestDice -= j;
				
				int [] positions = positions(bestDice);
				if (Math.abs(positions[0]) > (board.getM()/2) || Math.abs(positions[1]) > (board.getN()/2)) {	//check if it is out of the borders
					j--;
				}
				else
					break;
			}
		}	
	
		setX(positions(bestDice)[0]);
		setY(positions(bestDice)[1]);
		
		Movement[0] = getX();
		Movement[1] = getY();
		
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
	
	/*
	 * Returns an ArrayList of all available moves a player can make
	 * considered its position
	 */
	ArrayList<Integer> availableMoves (int x, int y){
		
		ArrayList<Integer> availableMoves = new ArrayList<>();
		
		int M = board.getM();
		int N = board.getN();
		
		for (int k = 1; k <= 8; k++) {
			
			if (y == -N/2 && x != M/2 && x != -M/2) {			//left side, NO corners
				if (k < 6) {
					availableMoves.add(k);
				}
			}
			else if (y == N/2 && x != M/2 && x != -M/2) {		//right side, NO corners
				if (k == 1 || k > 4) {
					availableMoves.add(k);
				}
			}
			else if (x == M/2 && y != N/2 && y != -N/2) {		//down side, NO corners
				if (k < 4 || k > 6) {
					availableMoves.add(k);
				}
			}
			else if (x == -M/2 && y != N/2 && y != -N/2) {		//up side, NO corners
				if (k > 2 && k < 8) {
					availableMoves.add(k);
				}
			}
			else if (x == -M/2 && y == N/2) {					//up right corner
				if (k > 4 && k < 8) {
					availableMoves.add(k);
				}
			}
			else if (x == M/2 && y == N/2) {					//down right corner
				if (k == 1 || k == 7 || k == 8) {
					availableMoves.add(k);
				}
			}
			else if (x == M/2 && y == -N/2) {					//down left corner
				if (k < 4) {
					availableMoves.add(k);
				}
			}
			else if (x == -M/2 && y == -N/2) {					//up left corner
				if (k == 3 || k == 4 || k == 5) {
					availableMoves.add(k);
				}
			}
			else {
				availableMoves.add(k);
			}
		}
		return availableMoves;
	}
	
	/*
	 * It creates the subtree, considering the available moves. It creates a virtual board, a virtual player
	 * and moves virtually the player at the direction of the dice (of the available moves). Each move identifies with
	 * a node created with an evaluation. After that, we create the OpponentSubtree down of each node we created at first.
	 */
	void createMySubtree(Node root, int depth, int xCurrentPos, int yCurrentPos, HeuristicPlayer opponent) {
			
		ArrayList <Integer> availableMoves = availableMoves(xCurrentPos, yCurrentPos);
		for (int i = 0; i < availableMoves.size(); i++) {
			
			int dice = availableMoves.get(i);

			Board virtualBoard = new Board(board);
			
			int [] virtualPosition = positions(dice);
			MinMaxPlayer virtualPlayer = new MinMaxPlayer(this);
			virtualPlayer.setBoard(virtualBoard);
			virtualPlayer.setX(virtualPosition[0]);
			virtualPlayer.setY(virtualPosition[1]);
						
			int x = virtualPlayer.getX();
			int y = virtualPlayer.getY();
			
			int[] info = {x, y, dice}; 
			
			Node child = new Node(root, info, 1);
			child.setNodeEvaluation(evaluate(dice, xCurrentPos, yCurrentPos, opponent));
			
			root.children.add(child);
			
			createOpponentSubtree(child, depth + 1, opponent, virtualPlayer);
		}
	}

	/*
	 * It creates the OPPONENTS subtree, considering the available moves and the virtually moved player. Each move identifies with
	 * a node created with an evaluation which is the evaluation of the parent_node_evaluation - its_node_evaluation.
	 * The MinMax tree is ready.
	 */
	void createOpponentSubtree(Node parent, int depth, HeuristicPlayer opponent, MinMaxPlayer player) {
		ArrayList <Integer> availableMoves = availableMoves(opponent.x, opponent.y);
		
		for (int i = 0; i < availableMoves.size(); i++) {
			
			int dice = availableMoves.get(i);
			int [] virtualPosition = positions(dice);
			int[] info = {virtualPosition[0], virtualPosition[1], dice}; 
			
			Node grandChild = new Node(parent, info, depth);
			grandChild.setNodeEvaluation(parent.getNodeEvaluation() - opponent.evaluate(dice, player));
			
			parent.children.add(grandChild);
		}
	}

	/*
	 * It returns the dice of the best dice, using the MinMax tree (MinMax Algorithm) to find the MaxEvaluation 
	 * of the MinEvaluation of each available move of MinMaxPlayer.
	 */
	public int chooseMinMaxMove(Node root) {
		
		double MaxEvaluation = 0;
		double [] MinEvaluation = new double[8];
		int bestDice = 0;

		for (int i = 0; i < root.children.size(); i++) {
			
			Node child = root.children.get(i);
			MinEvaluation[i] = child.children.get(0).getNodeEvaluation();
			
			for (int j = 0; j < child.children.size(); j++) {

				if (child.children.get(j).nodeEvaluation < MinEvaluation[i]) {
					MinEvaluation[i] = child.children.get(j).getNodeEvaluation();
				}
			}
		}

		MaxEvaluation = MinEvaluation[0];
		bestDice = root.children.get(0).nodeMove[2];

		for (int i = 0; i < root.children.size(); i++) {
			double evaluation = MinEvaluation[i];
			if(evaluation > MaxEvaluation) {
				MaxEvaluation = evaluation;
				bestDice = root.children.get(i).nodeMove[2];
			}
		}
		return bestDice;
	}
	
	/*
	 * It prints on the terminal the "results" of each move using the ArrayList.
	 * (the dice chosen, if the player gained Points, or took a weapon, or lost Points)
	 */
	public void statistics() {
		
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