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
import osok.network.client.ChatClient;
import osok.network.client.Client;

/** Logins addGridPane method consists of labels, textfields, a button. The labels, and a textfield is created on a GridPane and then added to a BorderPane in the start method.
 *
 * The start method consists of a group that is added to a BorderPane that is added to the scene. In the start method we also have our animation of the "Sprite-glowing2.png image.
 * The animated picture is added to a Group that's added to the BorderPane.
 *
 **/
public class Login extends Application{
	private Bank bank;
	private Stage stage;
    private Client client;

	Image IMAGE = new Image("Sprite-glowing2.png");

	/*Values to be sent to the SpriteAnimation class. Columns and count says in how many pieces an image is supposed to the split up into and to be used.
	 *Offset value is to change the start point of the animation. The Width and Height is the size of the area you want the sprite to cover, in this case the animation is used
	 * on the entire scene.
	 */
	private int COLUMNS  =   29;
	private int COUNT    =  29;
	private int OFFSET_X =  0;
	private int OFFSET_Y =  0;
	private int WIDTH    = 1024;
	private int HEIGHT   = 720;


	private ChatClient chatClient;



    @Override
	public void start(Stage primaryStage) throws Exception {
		this.stage=primaryStage;
		bank = new Bank(this);
		BorderPane pane = new BorderPane();
		ImageView imageView = new ImageView(IMAGE);
		imageView.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH, HEIGHT));

		//The code that's used to send the values that was mentioned earlier as well as the speed you want the animation to go in. The lower the number is the faster the animation is.
		Animation animation = new SpriteAnimation(
				imageView,
				Duration.millis(2000),
				COUNT, COLUMNS,
				OFFSET_X, OFFSET_Y,
				WIDTH, HEIGHT
		);
		//Sets how many times the animation is supposed to circle around and starts the animation.
		animation.setCycleCount(Animation.INDEFINITE);
		animation.play();

		Group group = new Group(imageView);
		pane.getChildren().add(group);
		pane.setCenter(addGridPane());
		Scene scene = new Scene(pane, 1014, 710);
		scene.getStylesheets().add("/application/style.css");
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

			  Player player = bank.getPlayer();
			  player.setUsername(userName.getText());
              player.setIp(ip.getText());
			  player.setPort(Integer.parseInt(port.getText()));
			  player.setUsername(userName.getText());

			  // tries to connect to server when pressing login
			  chatClient = new ChatClient(bank.getPlayer().getIp() , bank);
			  client = new Client(bank);

				// if connection is success setter isConnected in Client will be set to true and game scene will launch
                if(player.isConnected()){
					// sets scene of game
                    bank.getLogin().getStage().setScene(bank.getGui().getScene());
					// starts chatClient
					chatClient.startChatClient();
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

	/**  terminates all threads on client side when window is closed **/
    public void stop(){
		bank.getGame().setRunning(false);
        client.shutdown();
        chatClient.stopChat();
        bank.getScoreboard().stopThread();
		try {
			bank.getThreadG().join();
			bank.getThreadSb().join();
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
