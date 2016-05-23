package application;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class CleintGui extends Application {
	Thread thread;
	Game game;
	Move move;
	KeyInput keyInput;
	Player player;
	Scene scene;
	Gravity gravity;

	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println("Starting");
		player = new Player();
		move = new Move(player);
		keyInput = new KeyInput(primaryStage, move);
		gravity = new Gravity(player);
		game = new Game(move,gravity);
		thread = new Thread(game);
		thread.start();
		scene = new Scene(createContent(player), 1024, 720);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);

	}

	public Parent createContent(Player player) {
		BorderPane root = new BorderPane();
		root.getChildren().add(player.getBall());
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
