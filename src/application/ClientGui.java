package application;

import java.util.Arrays;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import osok.network.client.Client;

public class ClientGui extends Application {
	Client client;
	private int port;
	private String ip;
	private String username;
	Bank bank;


	@Override
	public void start(Stage primaryStage) throws Exception {
		client = new Client(ip , port , username);
		System.out.println("client started");
		System.out.println("Starting Game");
		Platform2D p2D0 = new Platform2D(106,150);
		Platform2D p2D1 = new Platform2D(362,325);
		Platform2D p2D2 = new Platform2D(106,500);
		Platform2D p2D3 = new Platform2D(618,150);
		Platform2D p2D4 = new Platform2D(618,500);
		Bank bank = new Bank(primaryStage);
		bank.getPlatforms().addAll(Arrays.asList(p2D0, p2D1, p2D2, p2D3, p2D4));
		this.bank=bank;
		Scene scene = new Scene(createContent(bank.getPlayer()), 1024, 720);
		bank.getStage().setScene(scene);
		bank.getStage().setResizable(false);
		bank.getStage().show();

	}


	public static void main(String[] args) {
		launch(args);

	}

	public Parent createContent(Player player) {
		BorderPane root = new BorderPane();
		root.getChildren().add(player.getBall());
		for(int i = 0; i<bank.getPlatforms().size();i++){
			for(int j = 0;j<bank.getPlatforms().get(i).getPlatform().size();j++){
				root.getChildren().add(bank.getPlatforms().get(i).getPlatform().get(j));
			}
			
		}
		return root;
	}
	
	public void stop(){
		bank.getGame().setRunning(false);
		try {
			bank.getThread().join();
			client.shutdown();
			System.out.println("Game Thread and client socket Terminated");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

}
