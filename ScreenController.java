package application;

import java.util.HashMap;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class ScreenController {
	
	public HashMap<String, Pane> screenMap = new HashMap<>();
    public Scene main;

    public ScreenController(Scene main) {
        this.main = main;
    }

    public void addScreen(String name, GridPane pane){
         screenMap.put(name, pane);
    }

    public void clearRoot(String name){
    	screenMap.get(name).getChildren().clear();
    }

    public Scene activate(String name){
        //main.setRoot(screenMap.get(name));
        System.out.println(screenMap.get(name).getId());
        Scene scene = new Scene(screenMap.get(name), 1200, 600);
        return scene;
    }
    
    public Scene getActivatedScene() {
        System.out.println(main.getRoot().getId());
    	return main;
    }
}
