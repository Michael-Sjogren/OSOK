package application;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class CleintGui extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Player player = new Player();
		Scene scene = new Scene(createContent(player), 1024, 720);
		primaryStage.setScene(scene);
		primaryStage.show();
		Move move = new Move(player);
		KeyInput keyInput = new KeyInput(primaryStage, move);
		Game game = new Game(move);
		Thread thread = new Thread(game);
		thread.start();

	}

	public static void main(String[] args) {
		launch(args);

	}

	public Parent createContent(Player player) {
		BorderPane root = new BorderPane();
		root.getChildren().add(player.getBall());
		return root;
	}

}
