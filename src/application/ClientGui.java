package application;

import java.util.Arrays;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import osok.network.client.Client;

public class ClientGui{
	private Client client;
	private Bank bank;
	private Scene scene;
	private BorderPane root;
	private Circle playerCircle;


	public ClientGui(Bank bank) {
		this.bank=bank;
		System.out.println("Starting Game");
		Platform2D p2D0 = new Platform2D(106,150);
		Platform2D p2D1 = new Platform2D(362,325);
		Platform2D p2D2 = new Platform2D(106,500);
		Platform2D p2D3 = new Platform2D(618,150);
		Platform2D p2D4 = new Platform2D(618,500);
		bank.getPlatforms().addAll(Arrays.asList(p2D0, p2D1, p2D2, p2D3, p2D4));
		scene = new Scene(createContent(bank.getPlayer()), 1024, 720);
		

	}

	public Parent createContent(Player player) {
		root = new BorderPane();
		Image image = new Image("application/Sprite-0002.png");
		ImageView iv1 = new ImageView();
		playerCircle = new Circle(bank.getPlayer().getxPos(),bank.getPlayer().getyPos(),bank.getPlayer().getSize());
		iv1.setImage(image);
		root.getChildren().addAll(iv1,playerCircle);
		for(int i = 0; i<bank.getPlatforms().size();i++){
			for(int j = 0;j<bank.getPlatforms().get(i).getPlatform().size();j++){
				root.getChildren().add(bank.getPlatforms().get(i).getPlatform().get(j));
			}
			root.getChildren().add(bank.getPlatforms().get(i).getImage());

		}
		return root;
	}

	public BorderPane getRoot() {
		return root;
	}

	public void setRoot(BorderPane root) {
		this.root = root;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public Circle getPlayerCircle() {
		return playerCircle;
	}

	public void setPlayerCircle(Circle playerCircle) {
		this.playerCircle = playerCircle;
	}
	
	

}
