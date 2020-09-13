package application;

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
	public Weapon(Weapon w) {	// 2nd constructor with an object of class Weapon as an argument
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
	 /*
    else if (player1choiceBox.getValue().toString() == "MinMax") {
    	player1Label = new Text("MinMax");
    	MinMaxPlayer p1 = new MinMaxPlayer(1, null, 10, 10, boardGame, 20);
    	
    	//the gender of our players
    	String name1 = setPlayerName(groupGender1.getUserData().toString(), 1);
	    Text player1Name = new Text(name1);
	    p1.setName(name1);
    	
    	if (player2choiceBox.getValue().toString() == "Heuristic") {
	    	player2Label = new Text("Heuristic");
	    	HeuristicPlayer p2 = new HeuristicPlayer(2, null, 10, 10, boardGame, 3);
	    	
	    	String name2 = setPlayerName(groupGender2.getUserData().toString(), 2);
		    Text player2Name = new Text(name2);
		    p2.setName(name2);
	    }
	    else if (player2choiceBox.getValue().toString() == "MinMax") {
	    	player2Label = new Text("MinMax");
	    	MinMaxPlayer p2 = new MinMaxPlayer(2, null, 10, 10, boardGame, 20);
	    	
	    	String name2 = setPlayerName(groupGender2.getUserData().toString(), 2);
		    Text player2Name = new Text(name2);
		    p2.setName(name2);
	    }
	    else if (player2choiceBox.getValue().toString() == "Random") {
	    	player2Label = new Text("Random");
	    	Player p2 = new Player(2, null, 10, 10, boardGame);
	    	
		    String name2 = setPlayerName(groupGender2.getUserData().toString(), 2);
		    Text player2Name = new Text(name2);
		    p2.setName(name2);
	    }
    }
    else if (player1choiceBox.getValue().toString() == "Random") {
    	player1Label = new Text("Random");
    	Player p1 = new Player(1, null, 10, 10, boardGame);
    	
    	//the gender of our players
    	String name1 = setPlayerName(groupGender1.getUserData().toString(), 1);
	    Text player1Name = new Text(name1);
	    p1.setName(name1);
    	
    	if (player2choiceBox.getValue().toString() == "Heuristic") {
	    	player2Label = new Text("Heuristic");
	    	HeuristicPlayer p2 = new HeuristicPlayer(2, null, 10, 10, boardGame, 3);
	    	
	    	String name2 = setPlayerName(groupGender2.getUserData().toString(), 2);
		    Text player2Name = new Text(name2);
		    p2.setName(name2);
	    }
	    else if (player2choiceBox.getValue().toString() == "MinMax") {
	    	player2Label = new Text("MinMax");
	    	MinMaxPlayer p2 = new MinMaxPlayer(2, null, 10, 10, boardGame, 20);
	    	
	    	String name2 = setPlayerName(groupGender2.getUserData().toString(), 2);
		    Text player2Name = new Text(name2);
		    p2.setName(name2);
	    }
	    else if (player2choiceBox.getValue().toString() == "Random") {
	    	player2Label = new Text("Random");
	    	Player p2 = new Player(2, null, 10, 10, boardGame);
	    	
	    	String name2 = setPlayerName(groupGender2.getUserData().toString(), 2);
		    Text player2Name = new Text(name2);
		    p2.setName(name2);
	    }
    }
		
	*/
	
	/*
    else if (player2choiceBox.getValue().toString() == "MinMax") {
    	player2Label = new Text("MinMax");
    	MinMaxPlayer p2 = new MinMaxPlayer(2, null, 10, 10, boardGame, 20);
    	
    	//the gender of our players
    	String name2 = setPlayerName(groupGender2.getUserData().toString(), 2);
	    Text player2Name = new Text(name2);
	    p2.setName(name2);
    }
    else if (player2choiceBox.getValue().toString() == "Random") {
    	player2Label = new Text("Random");
    	Player p2 = new Player(2, null, 10, 10, boardGame);
    	
    	//the gender of our players
    	String name2 = setPlayerName(groupGender2.getUserData().toString(), 2);
	    Text player2Name = new Text(name2);
	    p2.setName(name2);
    } */
	
	
}
