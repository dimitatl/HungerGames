package application;

public class Board {
	int M, N;	//size of GameBoard
	int initM;
	int W, F, T; // number of items of Weapons, Food, Traps
	int A, B, C; // half size of Weapon-, Food-, TrapAreaLimits 
	int [][] weaponAreaLimits = new int[4][2];
	int [][] foodAreaLimits = new int[4][2];
	int [][] trapAreaLimits = new int[4][2];
	Weapon[] weapons;
	Food[] food;
	Trap[] traps;
	
	public Board() {	//the first constructor with no arguments
		M = 0;
		N = 0;
		initM = 0;
		W = 0;
		F = 0;
		T = 0;
		A = 0;
		B = 0;
		C = 0;
		weapons = null;
		food = null;
		traps = null;
		weaponAreaLimits = null;
		foodAreaLimits = null;
		trapAreaLimits = null;
	}
	public Board(int M, int N, int W, int F, int T, int A, int B, int C) {	//the second constructor with specific arguments
		this.M = M;
		this.N = N;
		this.W = W;
		this.F = F;
		this.T = T;
		this.A = A;
		this.B = B;
		this.C = C;
		this.initM = M;
		
		weaponAreaLimits[0][0] = A;		// here we initialize the weapon-, food-, trap-, AreaLimits boards
		weaponAreaLimits[0][1] = A;
		weaponAreaLimits[1][0] = A;
		weaponAreaLimits[1][1] = -A;
		weaponAreaLimits[2][0] = -A;
		weaponAreaLimits[2][1] = -A;
		weaponAreaLimits[3][0] = -A;
		weaponAreaLimits[3][1] = A;
		
		foodAreaLimits[0][0] = B;
		foodAreaLimits[0][1] = B;
		foodAreaLimits[1][0] = B;
		foodAreaLimits[1][1] = -B;
		foodAreaLimits[2][0] = -B;
		foodAreaLimits[2][1] = -B;
		foodAreaLimits[3][0] = -B;
		foodAreaLimits[3][1] = B;
		
		trapAreaLimits[0][0] = C;
		trapAreaLimits[0][1] = C;
		trapAreaLimits[1][0] = C;
		trapAreaLimits[1][1] = -C;
		trapAreaLimits[2][0] = -C;
		trapAreaLimits[2][1] = -C;
		trapAreaLimits[3][0] = -C;
		trapAreaLimits[3][1] = C;
		
		weapons = new Weapon[W];
		food = new Food[F];
		traps = new Trap[T];
		
		for (int i = 0; i < W; i++) {	// here we initialize the weapons-, food-, traps- boards
			weapons[i] = new Weapon();
		}
		for (int i = 0; i < F; i++) {
			food[i] = new Food();
		}
		for (int i = 0; i < T; i++) {
			traps[i] = new Trap();
		}
	}
	
	public Board(Board b) {
		// Sizes
		N = b.N;
		M = b.M;
		W = b.W;
		F = b.F;
		T = b.T;
	    A = b.A;
		B = b.B;
		C = b.C;
		initM = b.initM;

		weaponAreaLimits = new int[4][2];
		foodAreaLimits = new int[4][2];
		trapAreaLimits = new int[4][2];
		weapons = new Weapon[W];
		food = new Food[F];
		traps = new Trap[T];

		weaponAreaLimits[0][0] = A;		// here we initialize the weapon-, food-, trap-, AreaLimits boards
		weaponAreaLimits[0][1] = A;
		weaponAreaLimits[1][0] = A;
		weaponAreaLimits[1][1] = -A;
		weaponAreaLimits[2][0] = -A;
		weaponAreaLimits[2][1] = -A;
		weaponAreaLimits[3][0] = -A;
		weaponAreaLimits[3][1] = A;
		
		foodAreaLimits[0][0] = B;
		foodAreaLimits[0][1] = B;
		foodAreaLimits[1][0] = B;
		foodAreaLimits[1][1] = -B;
		foodAreaLimits[2][0] = -B;
		foodAreaLimits[2][1] = -B;
		foodAreaLimits[3][0] = -B;
		foodAreaLimits[3][1] = B;
		
		trapAreaLimits[0][0] = C;
		trapAreaLimits[0][1] = C;
		trapAreaLimits[1][0] = C;
		trapAreaLimits[1][1] = -C;
		trapAreaLimits[2][0] = -C;
		trapAreaLimits[2][1] = -C;
		trapAreaLimits[3][0] = -C;
		trapAreaLimits[3][1] = C;
		
		for(int i=0; i<W; i++) {
			weapons[i] = new Weapon(b.weapons[i]);
		}
		for(int i=0; i<F; i++) {
			food[i] = new Food(b.food[i]);
		}
		for(int i=0; i<T; i++) {
			traps[i] = new Trap(b.traps[i]);
		}
	}
	public void setM (int M) {		//setters and getters for all our variables of the class Board
		this.M = M;
	}
	public void setN (int N) {
		this.N = N;
	}
	public void setInitM (int initM) {
		this.initM = initM;
	}
	public void setW (int W) {
		this.W = W;
	}
	public void setF (int F) {
		this.F = F;
	}
	public void setT (int T) {
		this.T = T;
	}
	public void setA(int A) {
		this.A = A;
	}
	public void setB(int B) {
		this.B = B;
	}
	public void setC(int C) {
		this.C = C;
	}
	public int getM () {
		return M;
	}
	public int getN () {
		return N;
	}
	public int getInitM() {
		return initM;
	}
	public int getW () {
		return W;
	}
	public int getF () {
		return F;
	}
	public int getT () {
		return T;
	}
	public int getA() {
		return A;
	}
	public int getB() {
		return B;
	}
	public int getC() {
		return C;
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
	
	public void createRandomWeapon() {				// it puts in a random way the weapons on the board
		for (int i = 0; i < getW(); i++) {

			weapons[i].setPlayerId((i % 2) + 1);   // this way, the weapons are shared equally to the 2 players (considering that the W is an even number)
			
			if(i < (getW()/3)) {				   // each 33.3% of the weapons becomes a different weapon (id_bow = 1, id_sword = 2, id_pistol = 3)
				weapons[i].setId(1);
				weapons[i].setType("bow");
			}
			if((i >= (getW()/3)) && (i < (2*getW()/3))) {
				weapons[i].setId(2);
				weapons[i].setType("sword");
			}
			if((i >= 2*(getW()/3)) && (i < getW())) {
				weapons[i].setId(3);
				weapons[i].setType("pistol");
			}

			weapons[i].setX(((((int)(Math.random()*100)) % (2*A + 1)) - A));	// randomly placed inside the weaponAreaLimits board
			weapons[i].setY(((((int)(Math.random()*100)) % (2*A + 1)) - A));
	
			while (weapons[i].x == 0 || weapons[i].y == 0) {					// x = 0, y = 0 don't even exist in the board (practically)
				weapons[i].setX(((((int)(Math.random()*100)) % (2*A + 1)) - A));
				weapons[i].setY(((((int)(Math.random()*100)) % (2*A + 1)) - A));
			}
			
			int j = 0;
			while (j < i) {		// we check if one weapon is placed at the same position with some other 
				
				if (weapons[i].x == weapons[j].x && weapons[i].y == weapons[j].y)  {
					
					weapons[i].x = 0;
					
					while(weapons[i].x == 0 || weapons[i].y == 0) {						//(we check again if x = 0, y = 0, since we call again the Math.random())
						weapons[i].setX(((((int)(Math.random()*100)) % (2*A + 1)) - A));
						weapons[i].setY(((((int)(Math.random()*100)) % (2*A + 1)) - A));
					}
					//wait(10);
					j = 0;		// we have to check again if one weapon is placed at the same position with some other, since we call again the Math.random()
				}
				else 
					j++;
			}
			System.out.println("Weapon created at position " + weapons[i].getX() 
								+ "\t" + weapons[i].getY());
		}
		System.out.println("\n");
	}

	
	public void createRandomTrap() {									// it puts in a random way the weapons on the board
		for (int i = 0; i < getT(); i++) {
			
			traps[i].setId(i);											//each trap has each own id
			
			traps[i].setPoints((((int)(Math.random()*100)) % 10) - 10);	// each trap has its points (from -10 to -1)
			
			switch (((int)(Math.random()*100)) % 2) {					// the traps randomly become of type "ropes" or "animals"
		 	
		 	case 0:
				traps[i].setType("ropes");
				break;
		 	case 1:
		 		traps[i].setType("animals");
		 		break;
			}
			

			traps[i].setX(((((int)(Math.random()*100)) % (2*C + 1)) - C));	// randomly placed inside the trapsAreaLimits board
			traps[i].setY(((((int)(Math.random()*100)) % (2*C + 1)) - C));

			while (traps[i].x == 0 || traps[i].y == 0) {					// x = 0, y = 0 don't even exist in the board (practically)
				traps[i].setX(((((int)(Math.random()*100)) % (2*C + 1)) - C));
				traps[i].setY(((((int)(Math.random()*100)) % (2*C + 1)) - C));
			}
			
			int j = 0;
			while (j <= i) {
				
				while(!traps[i].IsInTheLimits(B)) {							// we check if the traps are placed outside the foodAreaLimits board
					
					traps[i].setX(0);	// so as it can get in the 'if' under
					
					while(traps[i].x == 0 || traps[i].y == 0) {				//we check if x = 0, y = 0, since we call again the Math.random()
						traps[i].setX(((((int)(Math.random()*100)) % (2*C + 1)) - C));
						traps[i].setY(((((int)(Math.random()*100)) % (2*C + 1)) - C));
						//wait(10);
					}
				}
				
				if(i != j) {												// we check if one trap is placed at the same position with some other
					if (traps[i].x == traps[j].x && traps[i].y == traps[j].y) {
						
						traps[i].setX(0); 	 
						
						while(traps[i].x == 0 || traps[i].y == 0) {			//we check if x = 0, y = 0, since we call again the Math.random()
							traps[i].setX(((((int)(Math.random()*100)) % (2*C + 1)) - C));
							traps[i].setY(((((int)(Math.random()*100)) % (2*C + 1)) - C));
							//wait(10);
						}
						j = 0;	// we have to check again if one weapon is placed at the same position with some other, since we call again the Math.random()
					}
					else 
						j++;
				}
				else if (i == j) 	// in the case i = 0, there is no other trap, so the position of the trap is approved
					break;
				
			}
			System.out.println("Trap created at position: " + traps[i].getX() +
								"\t" + traps[i].getY());
		}
		System.out.println("\n");
	}
	
	/*
	 * The comments for the createRandomFood() are the same like the createRandomTrap() 
	 */
	public void createRandomFood() {		
		for (int i = 0; i < getF(); i++) {
			food[i].setId(i);
			food[i].setPoints((((int)(Math.random()*100)) % 10) + 1);	
			
			food[i].setX(((((int)(Math.random()*100)) % (2*B + 1)) - B));
			food[i].setY(((((int)(Math.random()*100)) % (2*B + 1)) - B));

			while(food[i].x == 0 || food[i].y == 0) {
				food[i].setX(((((int)(Math.random()*100)) % (2*B + 1)) - B));
				food[i].setY(((((int)(Math.random()*100)) % (2*B + 1)) - B));
			}
			
			int j = 0;
			while (j <= i) {
				
				while(!food[i].IsInTheLimits(A)) {
					food[i].setX(0);
					while(food[i].x == 0 || food[i].y == 0) {
						food[i].setX(((((int)(Math.random()*100)) % (2*B + 1)) - B));
						food[i].setY(((((int)(Math.random()*100)) % (2*B + 1)) - B));
						
					}
					//wait(10);
				}
				
				if(i != j) {				
					if (food[i].x == food[j].x && food[i].y == food[j].y) {
						food[i].setX(0);
						while(food[i].x == 0 || food[i].y == 0) {
							food[i].setX(((((int)(Math.random()*100)) % (2*B + 1)) - B));
							food[i].setY(((((int)(Math.random()*100)) % (2*B + 1)) - B));
							//wait(10);
						}
						j = 0;
					}
					else 
						j++;
				}
				else if (i == j)
					break;

			}
			System.out.println("Food created at position: " + food[i].getX() +
								"\t" + food[i].getY());
		}	
		System.out.println("\n");
	}
	
	public void createBoard() {		// it creates in a random way the Board (calling the 3 methods from above)
	
		createRandomWeapon();
		createRandomFood();
		createRandomTrap();
		System.out.println("I CREATED THE BOARD");

	}
	
	public void resizeBoard(Player p1, Player p2) {	// it changes the size of the Board 		
		
		if (Math.abs(p1.x) == M/2 || Math.abs(p1.y) == N/2 || Math.abs(p2.x) == M/2 || Math.abs(p2.y) == N/2) // in case that the player is on the borders of the BoardGame
			return;
		else {
			setM(M - 2);	// practically -1 each side
			setN(N - 2);
			if (getM()/2 < getC() && getM()/2 > getB()) {
				setC(getC() - 1);
			}
			if (getM()/2 < getB() && getM()/2 > getA()) {
				setB(getB() - 1);
				
			}
			if (getM()/2 == getB()) {
				setC(getB());
			}
			if (getM()/2 == getA()) {
				setB(getA());
				setC(getA());
			}
		}
	}
	
	/*
	 * In getStringRepresentation(), (since we don't want the x = 0 and y = 0 axis being represented in the board), 
	 * we broke the board in 4 quadrants, so we can manage the Board. In this method, 
	 * we practically transform the board with limits (0,0), (0, N), (M, 0), (M, N) WITHOUT THE AXIS x = M, y = N
	 * in a board with limits (-M/2, -N/2), (-M/2, N/2), (M/2, -N/2), (M/2, N/2) WITHOUT THE AXIS x = 0, y = 0
	 */
	
	public String[][] getStringRepresentation(int X1, int Y1, int X2, int Y2){
		String[][] Beautiful = new String[getN()][getM()];

		int M = getM();
		int N = getN();
		int x, y;
		for (int i = 0; i < M/2; i++) {						// 2nd quadrant
			for (int j = 0; j < N/2; j++) {
				
				x = i - M/2;			
				y = j - N/2;
				
				Beautiful[i][j] = "[_]";
				
				for (int k = 0; k < W; k++) {										
					if (weapons[k].x == x && weapons[k].y == y) {		// if that space of the board is 'weapon'
						Beautiful[i][j] =  "W" + weapons[k].playerId + weapons[k].id;
					}
				}
				for (int k = 0; k < F; k++) {							// if that space of the board is 'food'
					if (food[k].x == x && food[k].y == y) {
						Beautiful[i][j] = "F" + food[k].id;
					}
				}
				for (int k = 0; k < T; k++) {							// if that space of the board is 'trap'
					if (traps[k].x == x && traps[k].y == y) {
						Beautiful[i][j] = "T" + traps[k].id;
					}
				}
				if (X1 == x && Y1 == y) {								// if there stands the player 1 
					Beautiful[i][j] = "|$|";
				}
				if (X2 == x && Y2 == y) {								// if there stands the player 2
					Beautiful[i][j] = "|@|";
				}
				
			}
		}
		
		for (int i = 0; i < M/2; i++) {						// 1st quadrant
			for (int j = (N/2 + 1); j <= N; j++) {
				x = i - M/2;
				y = j - N/2;
				
				Beautiful[i][j-1] = "[_]";
				
				for (int k = 0; k < W; k++) {
					if (weapons[k].x == x && weapons[k].y == y) {
						Beautiful[i][j-1] = "W" + weapons[k].playerId + weapons[k].id;
					}
				}
				for (int k = 0; k < F; k++) {
					if (food[k].x == x && food[k].y == y) {
						Beautiful[i][j-1] = "F" + food[k].id;
					}
				}
				for (int k = 0; k < T; k++) {
					if (traps[k].x == x && traps[k].y == y) {
						Beautiful[i][j-1] = "T" + traps[k].id;
					}
				}
				if (X1 == x && Y1 == y) {
					Beautiful[i][j-1] = "|$|";
				}
				if (X2 == x && Y2 == y) {
					Beautiful[i][j-1] = "|@|";
				}
			}			
		}
		
		for (int i = (M/2 + 1); i <= M; i++) {				// 4th quadrant
			for (int j = (N/2 + 1); j <= N; j++) {
				x = i - M/2;
				y = j - N/2;
				
				Beautiful[i-1][j-1] = "[_]";
				
				for (int k = 0; k < W; k++) {
					if (weapons[k].x == x && weapons[k].y == y) {
						Beautiful[i-1][j-1] = "W" + weapons[k].playerId + weapons[k].id;
					}
				}
				for (int k = 0; k < F; k++) {
					if (food[k].x == x && food[k].y == y) {
						Beautiful[i-1][j-1] = "F" + food[k].id;
					}
				}
				for (int k = 0; k < T; k++) {
					if (traps[k].x == x && traps[k].y == y) {
						Beautiful[i-1][j-1] = "T" + traps[k].id;
					}
				}
				if (X1 == x && Y1 == y) {
					Beautiful[i-1][j-1] = "|$|";
				}
				if (X2 == x && Y2 == y) {
					Beautiful[i-1][j-1] = "|@|";
				}
			}
		}
		for (int i = (M/2 + 1); i <= M; i++) {					// 3rd quadrant
			for (int j = 0; j < N/2 ; j++) {
				x = i - M/2;
				y = j - N/2;
				
				Beautiful[i-1][j] = "[_]";
				
				for (int k = 0; k < W; k++) {
					if (weapons[k].x == x && weapons[k].y == y) {
						Beautiful[i-1][j] = "W" + weapons[k].playerId + weapons[k].id;
					}
				}
				for (int k = 0; k < F; k++) {
					if (food[k].x == x && food[k].y == y) {
						Beautiful[i-1][j] = "F" + food[k].id;
					}
				}
				for (int k = 0; k < T; k++) {
					if (traps[k].x == x && traps[k].y == y) {
						Beautiful[i-1][j] = "T" + traps[k].id;
					}
				}
				if (X1 == x && Y1 == y) {
					Beautiful[i-1][j] = "|$|";
				}
				if (X2 == x && Y2 == y) {
					Beautiful[i-1][j] = "|@|";
				}
			}
		}
		
		for (int i = 0; i < M; i++) {			// printing the board
			for (int j = 0; j < N; j++) {
				System.out.print(Beautiful[i][j] + "\t");
			}
			System.out.println("\n\n");
		}
		return Beautiful;
	}
}
