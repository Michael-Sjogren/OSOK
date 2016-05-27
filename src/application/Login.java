package application;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import osok.network.client.Client;

public class Login extends Application{
	private Bank bank;
	private Stage stage;
    private Client client;

    @Override
	public void start(Stage primaryStage) throws Exception {
		this.stage=primaryStage;
		bank = new Bank(this);
		BorderPane pane = new BorderPane();
		Image image = new Image("application/Sprite-0003.png");
		ImageView iv1 = new ImageView();
		iv1.setImage(image);
		pane.getChildren().add(iv1);
		pane.setCenter(addGridPane());
		Scene scene = new Scene(pane, 1014, 710);

		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}


	public GridPane addGridPane() {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("Välkommen till OSOK!");
		grid.add(scenetitle, 0, 0, 2, 1);

		Label user = new Label("Username");
		grid.add(user, 0, 1);

		TextField userName = new TextField();
		userName.setText("localhost");
		grid.add(userName, 1, 1);

		Label ipAdress = new Label("IP-Adress:");
		grid.add(ipAdress, 0, 2);

		TextField ip = new TextField();
		ip.setText("localhost");
		grid.add(ip, 1, 2);

		Label portNummer = new Label("Port-Nummer:");
		grid.add(portNummer, 0, 3);

		TextField port = new TextField();
		port.setText("55556");
		grid.add(port, 1, 3);

		Button btn = new Button("Logga in!");
		btn.setOnAction(e-> {
          if(!(ip.getText().equals("") && userName.getText().equals("") && port.getText().equals(""))){

                Player player =	bank.getPlayer();
              player.setIp(ip.getText());
               player.setPort(Integer.parseInt(port.getText()));
               player.setUsername(userName.getText());

               System.out.println(player.getUsername());
              System.out.println(player.getIp());
			  System.out.println(player.getPort());

                client = new Client(bank);
                if(player.isConnected()){

                    bank.getLogin().getStage().setScene(bank.getGui().getScene());
                }
           }
		});

		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 1, 4);

		final Text actiontarget = new Text();
		grid.add(actiontarget, 0, 6);
		grid.setColumnSpan(actiontarget, 2);
		grid.setHalignment(actiontarget, HPos.RIGHT);
		actiontarget.setId("actiontarget");

		return grid;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
    public void stop(){
		bank.getGame().setRunning(false);
        client.shutdown();
		try {
			bank.getThread().join();
			System.out.println("Game Thread and client socket Terminated");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
}
