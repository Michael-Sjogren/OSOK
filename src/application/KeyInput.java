package application;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/** Keyinput adds keyEventsHandlers to the stage **/
public class KeyInput {
	private String message;
	private boolean isShowing = false;
	
	/** Constructor demands a reference to the "controller" **/ //which allows the class to work with other classes
	public KeyInput(Controller con) {

		con.getLogin().getStage().addEventHandler(KeyEvent.KEY_PRESSED, ep -> {
			//sets the players velocity to "left" and changes the image to the left-looking one
			if (ep.getCode() == KeyCode.LEFT) {
				con.getMove().moveLeft();
				con.getGui().getPlayerCircle().setFill(con.getGui().getPatternLookLeft());
				con.getPlayer().setLeft(true);
			}
			//Shoots the bullet
			if (ep.getCode() == KeyCode.SPACE) {
				con.getBullet().shootBullet();
			}
			//sets the players velocity to "right" and changes the image to the right-looking one
			if (ep.getCode() == KeyCode.RIGHT) {
				con.getMove().moveRight();
				con.getGui().getPlayerCircle().setFill(con.getGui().getPatternLookRight());
				con.getPlayer().setLeft(false);
			}
			//Player jumps
			if (ep.getCode() == KeyCode.UP) {
				con.getMove().jump();
			}
			//Shows the scoreboard panel
			if (ep.getCode() == KeyCode.TAB) {
				con.getGui().getScorePanel().setStyle("visibility: visible");
			}

		});

		con.getLogin().getStage().addEventHandler(KeyEvent.KEY_RELEASED, er -> {
			//Removes the players right velocity
			if (er.getCode() == KeyCode.RIGHT) {
				con.getMove().resetVelRight();
			}
			//Removes the players left velocity
			if (er.getCode() == KeyCode.LEFT) {
				con.getMove().resetVelLeft();
			}
			//Hides the scoreboard panel
			if (er.getCode() == KeyCode.TAB) {
				con.getGui().getScorePanel().setStyle("visibility: hidden");
			}
		});

		con.getLogin().getStage().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
			//Enables/disables the chatt when pressing ENTER
			if (event.getCode() == KeyCode.ENTER) {
				if (isShowing) {
					message = con.getGui().getMessageInput().getText();
					if (!message.equals("")) {
						con.getPlayer().setMessage(message);
						con.getGui().getMessageInput().clear();
						con.getGui().getChatLog().setFocusTraversable(false);
						con.getGui().getMessageInput().setFocusTraversable(false);
						isShowing = false;
					}
					con.getGui().getChatUI().setStyle("visibility: hidden");
					con.getGui().getMessageInput().setStyle("visibility: hidden");
					con.getGui().getChatLog().setStyle("-fx-background-color: rgba(59, 64, 68, 0.2);");
					con.getGui().getChatLog().lookup(".scroll-bar:vertical").setStyle("-fx-opacity: 0");
					isShowing = false;
				} else {
					con.getGui().getMessageInput().setFocusTraversable(true);
					con.getGui().getChatUI().setStyle("visibility: visible");
					con.getGui().getMessageInput().setStyle("visibility: visible");

					con.getGui().getChatLog().setStyle("-fx-background-color: rgba(59, 64, 68, 0.4);");
					con.getGui().getChatLog().lookup(".scroll-bar:vertical").setStyle("-fx-opacity: 1");
					isShowing = true;
				}
			}

		});

	}

}
