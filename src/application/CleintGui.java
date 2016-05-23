package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class CleintGui extends Application {
	private Thread thread;
	private Game game;
	private Move move;
	private KeyInput keyInput;
	private Player player;
	private Scene scene;
	private Gravity gravity;
	private List<Platform2D> platforms = new ArrayList<Platform2D>();

	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println("Starting");
		Platform2D p2D0 = new Platform2D(106,150);
		Platform2D p2D1 = new Platform2D(362,325);
		Platform2D p2D2 = new Platform2D(106,500);
		Platform2D p2D3 = new Platform2D(618,150);
		Platform2D p2D4 = new Platform2D(618,500);
		platforms.addAll(Arrays.asList(p2D0, p2D1, p2D2, p2D3, p2D4));
		player = new Player();
		move = new Move(player);
		keyInput = new KeyInput(primaryStage, move);
		gravity = new Gravity(player);
		game = new Game(move,gravity);
		thread = new Thread(game);
		thread.start();
		scene = new Scene(createContent(player), 1024, 720);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);

	}

	public Parent createContent(Player player) {
		BorderPane root = new BorderPane();
		root.getChildren().add(player.getBall());
		for(int i = 0; i<platforms.size();i++){
			for(int j = 0;j<platforms.get(i).getPlatform().size();j++){
				root.getChildren().add(platforms.get(i).getPlatform().get(j));
			}
			
		}
		return root;
	}
	
	public void stop(){
		game.setRunning(false);
		try {
			thread.join();
			System.out.println("Game Thread Terminated");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

}
