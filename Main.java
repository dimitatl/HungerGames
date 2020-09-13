package application;
	
import java.util.HashMap;

import javafx.application.Application;
//import javafx.beans.value.ChangeListener;
//import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
//import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;


public class Main extends Application {
	
	public ImageView setImage(ImageView img) {
		img.setFitHeight(50); 
	    img.setFitWidth(50);
	    img.setPreserveRatio(true);
	    return img;
	}
	
	public ImageView putImage(Image image, int x, int y, int initM) {
		ImageView img = new ImageView(image);
		int RecWid = 20;
		int center = 200;
		img.setFitHeight(RecWid); 
	    img.setFitWidth(RecWid);
	    int u, v;

	    if(x > 0) {
	    	u = 2*center - RecWid * (initM/2 + 1 - x);
	    	if(y > 0) {
	    		v = 2*center - RecWid * (initM/2 + 1 - y);
	    	}
	    	else
	    		v = RecWid * (initM/2 + y);
	    }
	    else {
	    	u = RecWid * (initM/2 + x);
	    	if(y > 0) {
	    		v = 2*center - RecWid * (initM/2 + 1 - y);
	    	}
	    	else
	    		v = RecWid * (initM/2 + y);
	    }
	    img.setX(u);
	    img.setY(v);
	    img.setPreserveRatio(true);
	    return img;
	}
	
	public String setPlayerName(RadioButton male, RadioButton female, RadioButton other, int id) {
		
		if (male.getText() == "male") {
	    	if (id == 1)
	    		return "THODORIS";
	    	else
	    		return "GEORGE";
	    }
	    else if (male.getText() == "male") {
	    	if (id == 1)
	    		return "DIMITRA";
	    	else
	    		return "DESPOINA";
	    }
	    else {
	    	return "ROBIN";
	    }
	}
	
	public Group createBoard(Board boardGame) {
		
		int RecWid = 20;
	  	int A = boardGame.getA();
	  	int B = boardGame.getB();
	  	int C = boardGame.getC();
	  	int M = boardGame.getM();
	  	int Ht = M * RecWid;
	  	int center = 200;
	  	System.out.println("The M is: " + M + " and the Ht is: " + Ht);
	  	
	  	Group root = new Group();
	    
	    //creating and initializing the board
	    Rectangle board = new Rectangle();
	    	
	    board.setX(center - (Ht/2));
	    board.setY(center - (Ht/2));
	    board.setHeight(Ht);
	    board.setWidth(Ht);
	    
	    Rectangle trapsBoard = new Rectangle();

	    trapsBoard.setX(center - (C * RecWid));
	    trapsBoard.setY(center - (C * RecWid));
	    trapsBoard.setHeight(2 * C * RecWid);
	    trapsBoard.setWidth(2 * C * RecWid);
	    
	    Rectangle foodBoard = new Rectangle();

	    foodBoard.setX(center - (B * RecWid));
	    foodBoard.setY(center - (B * RecWid));
	    foodBoard.setHeight(2 * B * RecWid);
	    foodBoard.setWidth(2 * B * RecWid);
	    
	    Rectangle weaponsBoard = new Rectangle();

	    weaponsBoard.setX(center - (A * RecWid));
	    weaponsBoard.setY(center - (A * RecWid));
	    weaponsBoard.setHeight(2 * A * RecWid);
	    weaponsBoard.setWidth(2 * A * RecWid);
	    weaponsBoard.setFill(Color.ORANGE);
	    
	    Line[] lineT = new Line[M + 1];
	    Line[] lineN = new Line[M + 1];

	    for (int i = 0; i <= M ; i++) {
	    	lineT[i] = new Line();
	    	lineN[i] = new Line();
	    }
	    
	    for (int i = 0; i <= M ; i++) {

	    	lineT[i].setStartY(center - Ht/2 + (i * RecWid));
	    	lineT[i].setStartX(center - (Ht/2));
	    	lineT[i].setEndY(center - Ht/2 + (i * RecWid));
	    	lineT[i].setEndX(center + (Ht/2)); 
	    	
	    	lineN[i].setStartX(center - Ht/2 + (i * RecWid));
	    	lineN[i].setStartY(center - (Ht/2));
	    	lineN[i].setEndX(center - Ht/2 + (i * RecWid));
	    	lineN[i].setEndY(center + (Ht/2));
	    	
	    }
	    
	    if (M/2 > A && M/2 <= B) {

		    Shape board3 = Shape.subtract(foodBoard,  weaponsBoard);
		    board3.setFill(Color.DARKBLUE);
		    
		    root.getChildren().add(board3);
		    root.getChildren().add(weaponsBoard);
		    for (int i = 0; i <= M; i++) {
		    	root.getChildren().add(lineN[i]);
		    	root.getChildren().add(lineT[i]);
		    }
	    }
	    
	    if (M/2 > B && M/2 <= C) {

		    Shape board2 = Shape.subtract(trapsBoard, foodBoard);
		    Shape board3 = Shape.subtract(foodBoard,  weaponsBoard);
		    board2.setFill(Color.DARKGREEN);
		    board3.setFill(Color.DARKBLUE);
		    
		    root.getChildren().add(board2);
		    root.getChildren().add(board3);
		    root.getChildren().add(weaponsBoard);
		    for (int i = 0; i <= M; i++) {
		    	root.getChildren().add(lineN[i]);
		    	root.getChildren().add(lineT[i]);
		    }
	    }
	    
	    if (M/2 > C) {

		    Shape board1 = Shape.subtract(board, trapsBoard);
		    Shape board2 = Shape.subtract(trapsBoard, foodBoard);
		    Shape board3 = Shape.subtract(foodBoard,  weaponsBoard);
		    board1.setFill(Color.DARKRED);
		    board2.setFill(Color.DARKGREEN);
		    board3.setFill(Color.DARKBLUE);
		    
		    root.getChildren().add(board1);
		    root.getChildren().add(board2);
		    root.getChildren().add(board3);
		    root.getChildren().add(weaponsBoard);
		    for (int i = 0; i <= M; i++) {
		    	root.getChildren().add(lineN[i]);
		    	root.getChildren().add(lineT[i]);
		    }
	    }
	    
	    //put the traps in the right place
 	  	Image rope = new Image("rope2.png");
 	  	Image animal = new Image("animal.gif");
 	  	for (int i = 0; i < boardGame.getT(); i++) {
 	  		
 	  		if (Math.abs(boardGame.traps[i].getX()) <= C && Math.abs(boardGame.traps[i].getY()) <= C) {
	 	  		if(boardGame.traps[i].getType() == "ropes") {
	 	  			ImageView r = putImage(rope, boardGame.traps[i].getX(), 
	 	  					boardGame.traps[i].getY(), boardGame.getInitM());
	 	  			root.getChildren().add(r);
	 	  		}
	 	  		else {
	 	  			ImageView a = putImage(animal, boardGame.traps[i].getX(), 
	 	  					boardGame.traps[i].getY(), boardGame.getInitM());
	 	  			root.getChildren().add(a);
	 	  		}
 	  		}
 	  	}
 	  	
 	  	//put the weapons in the right place
 	  	Image pistol1 = new Image("pistol1.png");
 	  	Image pistol2 = new Image("pistol2.png");
 	  	Image bow1 = new Image("bow1.png");
 	  	Image bow2 = new Image("bow2.png");
 	  	Image sword1 = new Image("sword1.png");
 	  	Image sword2 = new Image("sword2.png");

 	  	for(int i = 0; i < boardGame.getW(); i++) {
 	  		if(boardGame.weapons[i].getType() == "pistol") {
 	  			if(boardGame.weapons[i].getPlayerId() == 1) {
 	  				ImageView pis1 = putImage(pistol1, boardGame.weapons[i].getX(), 
 	  						boardGame.weapons[i].getY(), boardGame.getInitM());
 	  				root.getChildren().add(pis1);		
 	  			}
 	  			else {
 	  				ImageView pis2 = putImage(pistol2, boardGame.weapons[i].getX(), 
 	  						boardGame.weapons[i].getY(), boardGame.getInitM());
 	  				root.getChildren().add(pis2);	
 	  			}
 	  		}
 	  		if(boardGame.weapons[i].getType() == "bow") {
 	  			if(boardGame.weapons[i].getPlayerId() == 1) {
 	  				ImageView b1 = putImage(bow1, boardGame.weapons[i].getX(), 
 	  						boardGame.weapons[i].getY(), boardGame.getInitM());
 	  				root.getChildren().add(b1);		
 	  			}
 	  			else {
 	  				ImageView b2 = putImage(bow2, boardGame.weapons[i].getX(), 
 	  						boardGame.weapons[i].getY(), boardGame.getInitM());
 	  				root.getChildren().add(b2);	
 	  			}
 	  		}
 	  		if(boardGame.weapons[i].getType() == "sword") {
 	  			if(boardGame.weapons[i].getPlayerId() == 1) {
 	  				ImageView s1 = putImage(sword1, boardGame.weapons[i].getX(), 
 	  						boardGame.weapons[i].getY(), boardGame.getInitM());
 	  				root.getChildren().add(s1);		
 	  			}
 	  			else {
 	  				ImageView s2 = putImage(sword2, boardGame.weapons[i].getX(), 
 	  						boardGame.weapons[i].getY(), boardGame.getInitM());
 	  				root.getChildren().add(s2);	
 	  			}
 	  		}
 	  	}
 	  	
 	  	Image anana = new Image("anana.gif");
	  	Image banana = new Image("banana.gif");
	  	Image cherry = new Image("cherry.gif");
	  	Image cherrypie = new Image("cherrypie.png");
	  	Image donut = new Image("donut.png");
	  	Image grapes = new Image("grapes.png");
	  	Image hamburger = new Image("hamburger.png");
	  	Image hotdog = new Image("hotdog.gif");
	  	Image pancakes = new Image("pancakes.png");
	  	Image pear = new Image("pear.gif");
	  	Image pizzaslice = new Image("pizza-slice.png");
	  	Image pizza = new Image("pizza.png");
	  	Image strawberry = new Image("strawberry.png");
	  	Image vegie = new Image("vegie.png");
	  	Image peper = new Image("peper.png");
	  	Image cupcake = new Image("cupcake.png");
	  	Image tomato = new Image("tomato.gif");
	  	Image pasta = new Image("pasta.png");
	  	Image corn = new Image("corn.png");
	  	Image mushrooms = new Image("mushrooms.png");
	  	
	  	HashMap<Integer, Image> foodmap = new HashMap<Integer, Image>();
	  	foodmap.put(0, anana);
	  	foodmap.put(1, banana);
	  	foodmap.put(2, cherry);
	  	foodmap.put(3, cherrypie);
	  	foodmap.put(4, donut);
	  	foodmap.put(5, grapes);
	  	foodmap.put(6, hamburger);
	  	foodmap.put(7, hotdog);
	  	foodmap.put(8, pancakes);
	  	foodmap.put(9, pear);
	  	foodmap.put(10, pizzaslice);
	  	foodmap.put(11, pizza);
	  	foodmap.put(12, strawberry);
	  	foodmap.put(13, vegie);
	  	foodmap.put(14, peper);
	  	foodmap.put(15, cupcake);
	  	foodmap.put(16, tomato);
	  	foodmap.put(17, corn);
	  	foodmap.put(18, pasta);
	  	foodmap.put(19, mushrooms);
	  	
	  	for(int i = 0; i < boardGame.getF(); i++) {
	  		if(i < 20) {
    	  		Image u = foodmap.get(i);
    	  		if (Math.abs(boardGame.food[i].getX()) <= B && Math.abs(boardGame.food[i].getY()) <= B) {
	    	  		ImageView f = putImage(u, boardGame.food[i].getX(),
	    	  				boardGame.food[i].getY(), boardGame.getInitM());
	    	  		root.getChildren().add(f);
    	  		}
	  		}
	  		else {
	  			Image u = foodmap.get(i - 20);
	  			if (Math.abs(boardGame.food[i].getX()) <= B && Math.abs(boardGame.food[i].getY()) <= B) {
	    	  		ImageView f = putImage(u, boardGame.food[i].getX(),
	    	  				boardGame.food[i].getY(), boardGame.getInitM());
	    	  		root.getChildren().add(f);
    	  		}
	  		}
	  	}
	    return root;
	}
	
	public Scene windowLastMessage(Text[] text, Button close) {
	
		GridPane pane = createGridPane();
	    
	    if (text[1] == null) {
	    	pane.add(text[0], 1, 0);
	    	pane.add(close, 1, 1);
	    }
	    
	    else {
	    	pane.add(text[0], 1, 0);
	    	pane.add(text[1], 1, 1);
	    	pane.add(text[2], 1, 2);
	    	pane.add(close, 1, 3);
	    }
	    Scene windowMessage = new Scene(pane, 600, 600);
	    
	    return windowMessage;
	}
	
	public GridPane createGridPane() {

	    GridPane pane = new GridPane();
	    
	    //Setting size for the pane 
	    pane.setMinSize(1200, 700); 
	       
	    //Setting the padding    
	    pane.setPadding(new Insets(10, 10, 10, 10));  
	      
	    //Setting the vertical and horizontal gaps between the columns 
	    pane.setVgap(15); 
	    pane.setHgap(15);       
	      
	    //Setting the Grid alignment 
	    pane.setAlignment(Pos.CENTER);
	    
	    //Setting the back ground color 
        pane.setStyle("-fx-background-color: BEIGE;");

	    return pane;
	}
	
	public int chooseFirst(int a) {
		if (a == 1) {
			return 0;
		}
		else 
			return 1;
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			Image[] charImg = new Image[2];
			
			//Label for name 
		    Text nameLabel = new Text("Observer Name"); 
		      
		    //Text field for name 
		    TextField nameText = new TextField(); 
		       
		    //Label for gender
		    Text genderLabel1 = new Text("Player 1 Gender"); 
		      
		    //Toggle group of radio buttons       
		    ToggleGroup groupGender1 = new ToggleGroup(); 
		    RadioButton maleRadio1 = new RadioButton("Male"); 
		    maleRadio1.setToggleGroup(groupGender1); 
		    maleRadio1.setOnMouseClicked((new EventHandler<MouseEvent>() { 
		          public void handle(MouseEvent event) { 
		             Image male1 = new Image("male1.png");
		             charImg[0] = male1;
		          } 
		    }));

		    RadioButton femaleRadio1 = new RadioButton("Female"); 
		    femaleRadio1.setToggleGroup(groupGender1); 
		    femaleRadio1.setOnMouseClicked((new EventHandler<MouseEvent>() { 
		          public void handle(MouseEvent event) { 
		        	  Image female1 = new Image("female1.png");
			          charImg[0] = female1; 
		          } 
		    }));

		    RadioButton neutralRadio1 = new RadioButton("Other"); 
		    neutralRadio1.setToggleGroup(groupGender1); 
		    neutralRadio1.setOnMouseClicked((new EventHandler<MouseEvent>() { 
		          public void handle(MouseEvent event) { 
		        	  Image other1 = new Image("other1.png");
			          charImg[0] = other1; 
		          } 
		    }));
		    
		    //Label for gender
		    Text genderLabel2 = new Text("Player 2 Gender"); 
		    
		    //Toggle group of radio buttons       
		    ToggleGroup groupGender2 = new ToggleGroup(); 
		    RadioButton maleRadio2 = new RadioButton("Male"); 
		    maleRadio2.setToggleGroup(groupGender2); 
		    maleRadio2.setOnMouseClicked((new EventHandler<MouseEvent>() { 
		          public void handle(MouseEvent event) { 
		        	  Image male2 = new Image("male2.png");
			          charImg[1] = male2; 
		          } 
		    }));
		    RadioButton femaleRadio2 = new RadioButton("Female"); 
		    femaleRadio2.setToggleGroup(groupGender2);
		    femaleRadio2.setOnMouseClicked((new EventHandler<MouseEvent>() { 
		          public void handle(MouseEvent event) { 
		        	  Image female2 = new Image("female2.png");
			          charImg[1] = female2; 
		          } 
		    }));
		    RadioButton neutralRadio2 = new RadioButton("Other"); 
		    neutralRadio2.setToggleGroup(groupGender2);
		    neutralRadio2.setOnMouseClicked((new EventHandler<MouseEvent>() { 
		          public void handle(MouseEvent event) { 
		        	  Image other2 = new Image("other2.png");
			          charImg[1] = other2; 
		          } 
		    }));
		  
		    Text player1Label = new Text("Player 1"); 
		      
		    //Choice box 
		    ChoiceBox<String> player1choiceBox = new ChoiceBox<>();
		    player1choiceBox.getItems().addAll ("Heuristic", "Random", "MinMax"); 
		    player1choiceBox.getSelectionModel().select(0);
		    
		    Text player2Label = new Text("Player 2"); 
		      
		    //Choice box
		    ChoiceBox<String> player2choiceBox = new ChoiceBox<>(); 
		    player2choiceBox.getItems().addAll ("Heuristic", "Random", "MinMax"); 
		    player2choiceBox.getSelectionModel().select(0);

		    
		    Text sizeBoard = new Text("Choose the size of the board (number x number)");
		    
		    ChoiceBox<Integer> chooseSize1 = new ChoiceBox<>();
		    chooseSize1.getItems().addAll(20, 24, 28, 32, 36, 40, 44, 48, 52, 56, 60);
		    chooseSize1.getSelectionModel().select(0);

		    
		    //Label for WSize 
		    Text WSize = new Text("Choose the size of the Weapons-Board (number x number)");
		    ChoiceBox<Integer> chooseSize2 = new ChoiceBox<>();
		    chooseSize2.getItems().addAll(4, 6, 8);
		    chooseSize2.getSelectionModel().select(0);

		    
		    //Label for FSize 
		    Text FSize = new Text("Choose the size of the Food-Board (number x number)");
		    ChoiceBox<Integer> chooseSize3 = new ChoiceBox<>();
		    chooseSize3.getItems().addAll(10, 12, 14);
		    chooseSize3.getSelectionModel().select(0);

		    
		    //Label for TSize 
		    Text TSize = new Text("Choose the size of the Traps-Board (number x number)");
		    ChoiceBox<Integer> chooseSize4 = new ChoiceBox<>();
		    chooseSize4.getItems().addAll(16, 18, 20);
		    chooseSize4.getSelectionModel().select(0);

		    
		    Text numOfTraps = new Text("How many traps do you want to put on the board?");
		    //Creating a scrollBar 
		    ScrollBar scrollTraps = new ScrollBar();
		    scrollTraps.setMin(1);
		    scrollTraps.setMax(50);
		    scrollTraps.setValue(20);

		    
		    Text numOfFood = new Text("How many foodElements do you want to put on the board?");
		    //Creating a scrollBar 
		    ScrollBar scrollFood = new ScrollBar();
		    scrollFood.setMin(1);
		    scrollFood.setMax(40);
		    scrollFood.setValue(15);
		    
		    //Label for Play 
		    Button buttonPlay = new Button("PLAY");
		    Button submit = new Button("SUBMIT CHANGES");
		    
		    //Character Images of Players
		    ImageView char1 = new ImageView(new Image("male1.png"));
		    char1 = setImage(char1);
		    ImageView char2 = new ImageView(new Image("female1.png"));
		    char2 = setImage(char2);
		    ImageView char3 = new ImageView(new Image("other1.png"));
		    char3 = setImage(char3);
		    ImageView char4 = new ImageView(new Image("male2.png"));
		    char4 = setImage(char4);
		    ImageView char5 = new ImageView(new Image("female2.png"));
		    char5 = setImage(char5);
		    ImageView char6 = new ImageView(new Image("other2.png"));
		    char6 = setImage(char6);

		    //Creating a Grid Pane 
	        GridPane gridPane = createGridPane();    

	        //Arranging all the nodes in the grid 
	        gridPane.add(nameLabel, 1, 0); 
	        gridPane.add(nameText, 1, 1); 
	        gridPane.add(player1Label, 0, 2);
	        gridPane.add(player2Label, 2, 2);
	        gridPane.add(player1choiceBox, 0, 3);
		    gridPane.add(player2choiceBox, 2, 3);
		    gridPane.add(genderLabel1, 0, 4); 
		    gridPane.add(maleRadio1, 0, 5);       
		    gridPane.add(femaleRadio1, 0, 6);
		    gridPane.add(neutralRadio1, 0, 7);
		    gridPane.add(genderLabel2, 2, 4); 
		    gridPane.add(maleRadio2, 2, 5);       
		    gridPane.add(femaleRadio2, 2, 6);
		    gridPane.add(neutralRadio2, 2, 7);
		    gridPane.add(sizeBoard, 0, 8);
		    gridPane.add(chooseSize1, 2, 8);
		    gridPane.add(WSize, 0, 9);
		    gridPane.add(chooseSize2, 2, 9);
		    gridPane.add(FSize, 0, 10);
		    gridPane.add(chooseSize3, 2, 10);
		    gridPane.add(TSize, 0, 11);
		    gridPane.add(chooseSize4, 2, 11);
		    gridPane.add(numOfTraps, 0, 12);
		    gridPane.add(scrollTraps, 2, 12);
		    gridPane.add(numOfFood, 0, 13);
		    gridPane.add(scrollFood, 2, 13);
		    gridPane.add(submit, 1, 14);
		    gridPane.add(char1, 1, 5);
		    gridPane.add(char2, 1, 6);
		    gridPane.add(char3, 1, 7);
		    gridPane.add(char4, 3, 5);
		    gridPane.add(char5, 3, 6);
		    gridPane.add(char6, 3, 7);


	        //Styling nodes   
	      
	        nameLabel.setStyle("-fx-font: normal bold 15px 'serif' "); 
	        genderLabel1.setStyle("-fx-font: normal bold 15px 'serif' ");
	        genderLabel2.setStyle("-fx-font: normal bold 15px 'serif' ");
	        player1Label.setStyle("-fx-font: normal bold 15px 'serif' ");
	        player2Label.setStyle("-fx-font: normal bold 15px 'serif' ");
	        sizeBoard.setStyle("-fx-font: normal bold 15px 'serif' ");
	        WSize.setStyle("-fx-font: normal bold 15px 'serif' ");
	        FSize.setStyle("-fx-font: normal bold 15px 'serif' ");
	        TSize.setStyle("-fx-font: normal bold 15px 'serif' ");
	        numOfTraps.setStyle("-fx-font: normal bold 15px 'serif' ");
	        numOfFood.setStyle("-fx-font: normal bold 15px 'serif' ");
	        buttonPlay.setStyle("-fx-font: normal bold 15px 'serif' ");
	        submit.setStyle("-fx-font: normal bold 15px 'serif' ");

	      	      
	        Scene beginScene = new Scene(gridPane, 1200, 600);
			
			//Setting title to the Stage 
		    primaryStage.setTitle("HUNGER GAMES REMASTERED"); 
		    
		    primaryStage.setScene(beginScene);
			primaryStage.show();
	        
			System.out.println("Hello World");  
			Game game = new Game();
			final Board boardGame = new Board();
			final HeuristicPlayer p1 = new HeuristicPlayer();
			final HeuristicPlayer p2 = new HeuristicPlayer();
			
			Button buttonTerminate = new Button("TERMINATE");
	        buttonTerminate.setStyle("-fx-font: normal bold 15px 'serif' ");
		      
		    buttonTerminate.setOnMouseClicked((new EventHandler<MouseEvent>() { 
		          public void handle(MouseEvent event) { 
		             System.out.println("Ok, I will terminate the game."); 
		             primaryStage.close();
		          } 
		     }));
		    
			submit.setOnMousePressed(event -> {
				 System.out.println(player1choiceBox.getValue().toString());
				 int M = chooseSize1.getValue();
	        	 int W_L = chooseSize2.getValue();
	        	 int F_L = chooseSize3.getValue();
	        	 int T_L = chooseSize4.getValue();
	        	 int F = (int)scrollFood.getValue();
	        	 int T = (int)scrollTraps.getValue();
	        	 boardGame.setM(M);
	        	 boardGame.setInitM(M);
	        	 boardGame.setN(M);
	        	 boardGame.setA(W_L/2);
	        	 boardGame.setB(F_L/2);
	        	 boardGame.setC(T_L/2);
	        	 boardGame.setW(6);
	        	 boardGame.setF(F);
	        	 boardGame.setT(T);
	        	 boardGame.weapons = new Weapon[6];
	        	 boardGame.food = new Food[F];
	        	 boardGame.traps = new Trap[T];
	     		
	        	 for (int i = 0; i < 6; i++) {	// here we initialize the weapons-, food-, traps- boards
	     			boardGame.weapons[i] = new Weapon();
	     		 }
	     		 for (int i = 0; i < F; i++) {
	     			boardGame.food[i] = new Food();
	     		 }
	     		 for (int i = 0; i < T; i++) {
	     			boardGame.traps[i] = new Trap();
	     		 }
		     	
	        	 boardGame.createBoard();
	        	 
	        	 
	        	 p1.setBoard(boardGame);
	        	 p1.setId(1);
	        	 p1.setX(boardGame.getM()/2);
	        	 p1.setY(boardGame.getM()/2);
	        	 p1.setScore(15);
	        	 
	        	 p2.setBoard(boardGame);
	        	 p2.setId(2);
	        	 p2.setX(boardGame.getM()/2);
	        	 p2.setY(boardGame.getM()/2);
	        	 p2.setScore(15);
	        	 
	        	 GridPane secondPane = createGridPane();
	        	 secondPane.add(buttonPlay, 1, 0);
	        	 secondPane.add(buttonTerminate, 1, 1);
	        	 Scene secondScene = new Scene(secondPane, 300, 300);
	        	 primaryStage.setScene(secondScene);
				 primaryStage.show();
	        	 
			});

			Text[] windowMessages = new Text[3];
			for (int i = 0; i < 3; i++) {
				windowMessages[i] = null;
			}
			
			//the type of our players
			Text player1 = new Text("Player 1: ");
		    Text player2 = new Text("Player 2: ");
		    
		    Button buttonClose = new Button("CLOSE");
	        buttonClose.setStyle("-fx-font: normal bold 15px 'serif' ");
		      
		    buttonClose.setOnMouseClicked((new EventHandler<MouseEvent>() { 
		          public void handle(MouseEvent event) { 
		             System.out.println("Ok, I will close the window."); 
		             primaryStage.close();
		          } 
		    }));
		    
			final int chooseCase = (int)((Math.random()*100) % 2);
			final Text[] statistics1 = new Text[2];
			final Text[] statistics2 = new Text[2];

			buttonPlay.setOnMouseClicked((new EventHandler<MouseEvent>() { 
		    	public void handle(MouseEvent event) {
		    		Text movement1 = new Text();
					Text movement2 = new Text();
					Text player1kills = new Text();
					Text player2kills = new Text();
					Text gamePaused = new Text();
					Text scoreWin1 = new Text();
					Text scoreWin2 = new Text();
					Text draw = new Text();
					Text Endgame = new Text();
					Text scores = new Text();
					Text InfinityWar1 = new Text();
					Text InfinityWar2 = new Text();
					
					movement1.setStyle("-fx-font: normal bold 15px 'serif' ");
					movement2.setStyle("-fx-font: normal bold 15px 'serif' ");
					player1kills.setStyle("-fx-font: normal bold 15px 'serif' ");
					player2kills.setStyle("-fx-font: normal bold 15px 'serif' ");
					gamePaused.setStyle("-fx-font: normal bold 15px 'serif' ");
					scoreWin1.setStyle("-fx-font: normal bold 15px 'serif' ");
					scoreWin2.setStyle("-fx-font: normal bold 15px 'serif' ");
					draw.setStyle("-fx-font: normal bold 15px 'serif' ");
					Endgame.setStyle("-fx-font: normal bold 15px 'serif' ");
					scores.setStyle("-fx-font: normal bold 15px 'serif' ");
					InfinityWar1.setStyle("-fx-font: normal bold 15px 'serif' ");					
					InfinityWar2.setStyle("-fx-font: normal bold 15px 'serif' ");
					player1.setStyle("-fx-font: normal bold 15px 'serif' ");
					player2.setStyle("-fx-font: normal bold 15px 'serif' ");

				    int p1First = chooseFirst(chooseCase);
				    int p2First = 1 - p1First;
				    				    
				    int moveP1X = 0, moveP1Y = 0, 
				    		moveP2X = 0, moveP2Y = 0; // used in the GetStringRepresentation() for the position of the players. 
				    									//We need them global, because they change in each round.

				    int [] movePlayer1 = new int[2];
				    int [] movePlayer2 = new int[2];
				    
				    ImageView[] characters = new ImageView[2];
		        	characters[0] = putImage(charImg[0], p1.getX(), p1.getY(), boardGame.getInitM());
		        	characters[1] = putImage(charImg[1], p2.getX(), p2.getY(), boardGame.getInitM());
				    
		    		if (game.getRound() % 3 == 0) {
						boardGame.resizeBoard(p1, p2);
					}///
					
					if (game.getRound() % 2 == p1First) {
						
						if (HeuristicPlayer.kill(p1, p2, 2)) {
							player1kills = new Text("Player P1 KILLS THE OPPONENT!! PLAYER " + p1.getName() + " WINS!!");
							player2kills.setStyle("-fx-font: normal bold 15px 'serif' ");
							windowMessages[0] = player1kills;
							System.out.println("I finished!");
						}///
						
						movePlayer1 = p1.moveIt(p2);
						
						
						moveP1X = movePlayer1[0];
						moveP1Y = movePlayer1[1];	
						characters[0] = putImage(charImg[0], moveP1X, moveP1Y, boardGame.getInitM());
						
						movement1 = new Text("The player P1 goes to position: \nx = " + movePlayer1[0] 
													+ "\ty = " + movePlayer1[1]);
						movement1.setStyle("-fx-font: normal bold 15px 'serif' ");

						
						if (HeuristicPlayer.kill(p1, p2, 2)) {
							player1kills = new Text("Player P1 KILLS THE OPPONENT!! PLAYER " + p1.getName() + " WINS!!");
							player1kills.setStyle("-fx-font: normal bold 15px 'serif' ");
							windowMessages[0] = player1kills;
							System.out.println("I finished!");
						}///
					}
					else if (game.getRound() % 2 == p2First) {
						
						if (HeuristicPlayer.kill(p2, p1, 2)) {
							player2kills = new Text("Player P2 KILLS THE OPPONENT!! PLAYER " + p2.getName() + " WINS!!");
							player2kills.setStyle("-fx-font: normal bold 15px 'serif' ");
							windowMessages[0] = player2kills;
							System.out.println("I finished!");
						}///
						
						movePlayer2 = p2.moveIt(p1);
						
						
						moveP2X = movePlayer2[0];
						moveP2Y = movePlayer2[1];
						characters[1] = putImage(charImg[1], moveP2X, moveP2Y, boardGame.getInitM());
						
						movement2 = new Text("The player P2 goes to position: \nx = " + movePlayer2[0] 
													+ "\ty = " + movePlayer2[1]);
						movement2.setStyle("-fx-font: normal bold 15px 'serif' ");

						
						if (HeuristicPlayer.kill(p2, p1, 2)) {
							player2kills = new Text("Player P2 KILLS THE OPPONENT!! PLAYER " + p2.getName() + " WINS!!");
							player2kills.setStyle("-fx-font: normal bold 15px 'serif' ");
							windowMessages[0] = player2kills;
							System.out.println("I finished!");
						}///
					}
					
					if (game.getRound() % 2 == p1First) {
						statistics1[0] = p1.statistics()[0];
						statistics1[1] = p1.statistics()[1];
					}
					else if (game.getRound() % 2 == p2First) {
						statistics2[0] = p2.statistics()[0];
						statistics2[1] = p2.statistics()[1];
					}
					else {
						System.out.println("ERROR 404");
					}
					
				    //boardGame.getStringRepresentation(moveP1X, moveP1Y, moveP2X, moveP2Y);

					if (game.round > 500) {						// when the game lasts too long, it stops.
						gamePaused = new Text("GAME PAUSED");
						windowMessages[0] = gamePaused;
						System.out.println("I finished!");
					}///
					
					if (p1.getScore() < 0) {
						scoreWin2 = new Text("Player P2 " + p2.getName() + " wins the game!! The other player has a negative score!");
						windowMessages[0] = scoreWin2;
						System.out.println("I finished!");
					}///
					if (p2.getScore() < 0) {
						scoreWin1 = new Text("Player P1 " + p1.getName() + " wins the game!! The other player has a negative score!");
						windowMessages[0] = scoreWin1;
						System.out.println("I finished!");
					}///
					
					
				    if(boardGame.getN()/2 <= boardGame.getA() && boardGame.getM()/2 <= boardGame.getA()) {
						Endgame = new Text("!GAME IS OVER!");
						scores = new Text("The score of P1 " + p1.getName() + " is: " + p1.getScore()
										+ " and the score of P2 " + p2.getName() + " is: " + p2.getScore());
						if (p1.getScore() > p2.getScore()) {
							InfinityWar1 = new Text("Player P1 " + p1.getName() + " wins the game!!");
							windowMessages[0] = Endgame;
							windowMessages[1] = scores;
							windowMessages[2] = InfinityWar1;
						}
						
						else if (p1.getScore() < p2.getScore()) {
							InfinityWar2 = new Text("Player P2 " + p2.getName() + " wins the game!!");
							windowMessages[0] = Endgame;
							windowMessages[1] = scores;
							windowMessages[2] = InfinityWar2;
						}
						else {
							draw = new Text("We have a draw!!");
							windowMessages[0] = Endgame;
							windowMessages[1] = scores;
							windowMessages[2] = draw;
						}
					}///
				    
				    if (windowMessages[0] != null) {
				    	primaryStage.setScene(windowLastMessage(windowMessages, buttonClose));
						primaryStage.show();
				    }
				    else {
				    	Text score1 = new Text("The score of P1 " + p1.getName() + " is: " + p1.getScore());
						Text score2 = new Text("The score of P2 " + p2.getName() + " is: " + p2.getScore());
						score1.setStyle("-fx-font: normal bold 15px 'serif' ");
						score2.setStyle("-fx-font: normal bold 15px 'serif' ");
						Group newBoard = createBoard(boardGame);
						newBoard.getChildren().add(characters[0]);
						newBoard.getChildren().add(characters[1]);

						GridPane newPane = createGridPane();
						newPane.add(newBoard, 1, 2);
						newPane.add(buttonPlay, 0, 1);
						newPane.add(buttonTerminate, 2, 1);
						newPane.add(movement1, 0, 4);
						newPane.add(movement2, 2, 4);
						newPane.add(score1, 0, 3);
						newPane.add(score2, 2, 3);
						newPane.add(player1, 0, 2);
						newPane.add(player2, 2, 2);
						if (game.getRound() % 2 == p1First) {
							if (statistics1[1] == null) {
								newPane.add(statistics1[0], 1, 5);
								statistics1[0].setStyle("-fx-font: normal bold 15px 'serif' ");
							}
							else if (statistics1[1] != null) {
								newPane.add(statistics1[0], 0, 5);
								newPane.add(statistics1[1], 2, 5);
								statistics1[1].setStyle("-fx-font: normal bold 15px 'serif' ");
								statistics1[0].setStyle("-fx-font: normal bold 15px 'serif' ");
							}
						}
						else if(game.getRound() % 2 == p2First) {
							if (statistics2[1] == null) {
								newPane.add(statistics2[0], 1, 5);
								statistics2[0].setStyle("-fx-font: normal bold 15px 'serif' ");
							}
							else if (statistics2[1] != null) {
								newPane.add(statistics2[0], 0, 5);
								newPane.add(statistics2[1], 2, 5);
								statistics2[0].setStyle("-fx-font: normal bold 15px 'serif' ");
								statistics2[1].setStyle("-fx-font: normal bold 15px 'serif' ");
							}
						}

						Scene newScene = new Scene(newPane, 1200, 700);
						game.setRound(++game.round);
						primaryStage.setScene(newScene);
						primaryStage.show();
				    }
		    	}
			}));
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	  
	public static void main(String[] args) {
				
		launch(args);

	}
}
