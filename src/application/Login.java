package application;

import javafx.animation.Animation;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import osok.network.client.Client;

public class Login extends Application{
	private Bank bank;
	private Stage stage;
    private Client client;

	Image IMAGE = new Image("Sprite-glowing.png");

	private int COLUMNS  =   29;
	private int COUNT    =  29;
	private int OFFSET_X =  0;
	private int OFFSET_Y =  0;
	private int WIDTH    = 1024;
	private int HEIGHT   = 720;



    @Override
	public void start(Stage primaryStage) throws Exception {
		this.stage=primaryStage;
		bank = new Bank(this);
		BorderPane pane = new BorderPane();
		ImageView imageView = new ImageView(IMAGE);
		imageView.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH, HEIGHT));

		Animation animation = new SpriteAnimation(
				imageView,
				Duration.millis(2000),
				COUNT, COLUMNS,
				OFFSET_X, OFFSET_Y,
				WIDTH, HEIGHT
		);
		animation.setCycleCount(Animation.INDEFINITE);
		animation.play();
		Group group = new Group(imageView);

		pane.getChildren().add(group);
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

		Label user = new Label("Username:");
		user.setFont(new Font("Arial", 30));
		user.setTextFill(Color.WHITE);
		grid.add(user, 0, 1);

		TextField userName = new TextField();
		userName.setStyle("-fx-background-color: black; -fx-text-inner-color: white;");
		userName.setText("localhost");
		grid.add(userName, 1, 1);

		Label ipAdress = new Label("IP-Adress:");
		ipAdress.setFont(new Font("Arial", 30));
		ipAdress.setTextFill(Color.WHITE);
		grid.add(ipAdress, 0, 2);

		TextField ip = new TextField();
		ip.setStyle("-fx-background-color: black; -fx-text-inner-color: white;");

		ip.setText("localhost");
		grid.add(ip, 1, 2);

		Label portNummer = new Label("Port-Nummer:");
		portNummer.setFont(new Font("Arial", 30));
		portNummer.setTextFill(Color.WHITE);
		grid.add(portNummer, 0, 3);

		TextField port = new TextField();
		port.setStyle("-fx-background-color: black; -fx-text-inner-color: white;");
		port.setText("55556");
		grid.add(port, 1, 3);

		Button btn = new Button("Logga in!");
		btn.setOnAction(e-> {
          if(!(ip.getText().equals("") && userName.getText().equals("") && port.getText().equals("") )){

			  Player player =	bank.getPlayer();
			  player.setUsername(userName.getText());
              player.setIp(ip.getText());
               player.setPort(Integer.parseInt(port.getText()));
			  for (int i = 0; i <  bank.opponents.getOpponentsList().size(); i++){
				  if(userName.getText().equals(bank.opponents.getOpponentsList().get(i).getUsername())){
					  System.out.println(bank.opponents.getOpponentsList().get(i).getUsername());
					  System.out.println("username not unique!");
					  userName.setText("");
				  }else{
					  player.setUsername(userName.getText());
				  }
			  }


               System.out.println("USERNAME :: " +player.getUsername());
              System.out.println("IP :: " +player.getIp());
			  System.out.println("PORT :: " +player.getPort());

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
