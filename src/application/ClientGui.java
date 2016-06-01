package application;

import java.util.Arrays;

import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**CientGui class contains the vast majority of the Client Gui **/
public class ClientGui {
	//I wouldn't even touch this class with a long pointy stick
	private Bank bank;
	private Scene scene;
	private BorderPane root;
	private Circle playerCircle;
	private Circle playerBullet;
	private Image imageLookRight;
	private Image imageLookLeft;
	private ImagePattern patternLookRight;
	private ImagePattern patternLookLeft;
	private Pane chatUI;
	private TextArea chatLog;
	private TextField messageInput;
	private BorderPane scorePanel;
	private TextArea score;

	
	/*Values to be sent to the SpriteAnimation class.
	 *Columns and count says in how many pieces an image is supposed to the split up into and to be used.
	 *Offset value is to change the start point of the animation.
	 *The Width and Height is the size of the area you want the sprite to cover, in this case the animation is used on the entire scene.
	 */
	private int COLUMNS = 8;
	private int COUNT = 8;
	private int OFFSET_X = 0;
	private int OFFSET_Y = 0;
	private int WIDTH = 1024;
	private int HEIGHT = 720;

	/** Constructor demands a reference to the "controller" **/ //which allows the class to work with other classes
	public ClientGui(Bank bank) {
		this.bank = bank;
		System.out.println("Starting Game");
		//creates platform which later gets drawn 
		Platform2D p2D0 = new Platform2D(106, 150);
		Platform2D p2D1 = new Platform2D(362, 325);
		Platform2D p2D2 = new Platform2D(106, 500);
		Platform2D p2D3 = new Platform2D(618, 150);
		Platform2D p2D4 = new Platform2D(618, 500);
		//Adds all the platforms to a array which is stored in the "controller"
		bank.getPlatforms().addAll(Arrays.asList(p2D0, p2D1, p2D2, p2D3, p2D4));
		//call the method a scene for the game with the current player and sets the res to 1024x720
		scene = new Scene(createContent(bank.getPlayer()), 1024, 720);
		scene.getStylesheets().add("/application/style.css");

	}

	/** Creates the scene for the GUI  **/
	public Parent createContent(Player player) {
		//Save yourself, abbandon ship
		root = new BorderPane();
		imageLookRight = new Image("Sprite-0009.png");
		imageLookLeft = new Image("Sprite-00010.png");
		patternLookRight = new ImagePattern(imageLookRight);
		patternLookLeft = new ImagePattern(imageLookLeft);
		//Creates a circle the players properties
		playerCircle = new Circle(bank.getPlayer().getxPos(), bank.getPlayer().getyPos(), bank.getPlayer().getSize());
		playerCircle.setFill(patternLookLeft);
		//Creates the bullet which the player shoots
		playerBullet = new Circle(bank.getBullet().getIdleCordsX(), bank.getBullet().getIdleCordsY(),
				bank.getBullet().getSize(), Color.GOLDENROD);
		playerBullet.setStroke(Color.BLACK);
		playerBullet.setStrokeWidth(2);

		//Creates the play background and animation for it
		ImageView imageView = new ImageView("Sprite-00011-sheet-background.png");
		imageView.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH, HEIGHT));
		Animation animation = new SpriteAnimation(imageView, Duration.millis(1000), COUNT, COLUMNS, OFFSET_X, OFFSET_Y,
				WIDTH, HEIGHT);

		animation.setCycleCount(Animation.INDEFINITE);
		animation.play();
		//Add the animated background to a group object
		Group group = new Group(imageView);
		//Add the background, playerCircle and playerBullet
		root.getChildren().addAll(group, playerCircle, playerBullet);

		//adds all the platforms
		for (int i = 0; i < bank.getPlatforms().size(); i++) {
			for (int j = 0; j < bank.getPlatforms().get(i).getPlatform().size(); j++) {
				root.getChildren().add(bank.getPlatforms().get(i).getPlatform().get(j));
			}
			root.getChildren().add(bank.getPlatforms().get(i).getImage());

		}
		//Add all the opponents circles to the root/BorderPane
		for (int i = 0; i < 4; i++) {
			root.getChildren().addAll(bank.getOpponents().getOpponentsCircleList().get(i));
			root.getChildren().addAll(bank.getOpponents().getOpponentsBulletList().get(i));

		}
		root.setBottom(createChatGUI());
		root.setCenter(createScoreboard());
		return root;
	}
	
	/** Creates the chat box **/
	public Pane createChatGUI() {
		chatLog = new TextArea();
		messageInput = new TextField();

		chatLog.setEditable(false);
		chatLog.setFocusTraversable(false);
		messageInput.setFocusTraversable(false);

		chatLog.getStyleClass().add("chat-log");
		chatLog.setWrapText(true);
		messageInput.getStyleClass().add("message-input");

		chatUI = new BorderPane(null, null, null, messageInput, chatLog);
		chatUI.getStyleClass().add("chatUI");

		return chatUI;

	}

	/** Creates the scoreboard box**/
	public Pane createScoreboard() {
		score = new TextArea();
		score.setEditable(false);
		score.setMouseTransparent(true);
		score.setFocusTraversable(false);

		score.getStyleClass().add("score-textarea");
		scorePanel = new BorderPane(score);
		scorePanel.getStyleClass().add("score-panel");
		return scorePanel;
	}

	public void removePlayerCircle() {
		bank.getGui().getPlayerCircle().setCenterX(-30);
		bank.getGui().getPlayerCircle().setCenterY(-30);
	}
	
	/** Setter and Getters **/

	public TextArea getChatLog() {
		return chatLog;
	}

	public BorderPane getScorePanel() {
		return scorePanel;
	}

	public TextField getMessageInput() {
		return messageInput;
	}

	public Circle getPlayerBullet() {
		return playerBullet;
	}

	public void setPlayerBullet(Circle playerBullet) {
		this.playerBullet = playerBullet;
	}

	public TextArea getScore() {
		return score;
	}

	public void setScore(TextArea score) {
		this.score = score;
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

	public ImagePattern getPatternLookLeft() {
		return patternLookLeft;
	}

	public ImagePattern getPatternLookRight() {
		return patternLookRight;
	}

	public Pane getChatUI() {
		return chatUI;
	}
	
	/** ------------------------------------------------------------------------------------------------ **/

}
