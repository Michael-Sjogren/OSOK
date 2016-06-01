package application;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/** Keyinput adds keyEventsHandlers to the stage **/
public class KeyInput {
	private String message;
	private boolean isShowing = false;
	
	/** Constructor demands a reference to the "controller" **/ //which allows the class to work with other classes
	public KeyInput(Bank bank) {

		bank.getLogin().getStage().addEventHandler(KeyEvent.KEY_PRESSED, ep -> {
			//sets the players velocity to "left" and changes the image to the left-looking one
			if (ep.getCode() == KeyCode.LEFT) {
				bank.getMove().moveLeft();
				bank.getGui().getPlayerCircle().setFill(bank.getGui().getPatternLookLeft());
				bank.getPlayer().setLeft(true);
			}
			//Shoots the bullet
			if (ep.getCode() == KeyCode.SPACE) {
				bank.getBullet().shootBullet();
			}
			//sets the players velocity to "right" and changes the image to the right-looking one
			if (ep.getCode() == KeyCode.RIGHT) {
				bank.getMove().moveRight();
				bank.getGui().getPlayerCircle().setFill(bank.getGui().getPatternLookRight());
				bank.getPlayer().setLeft(false);
			}
			//Player jumps
			if (ep.getCode() == KeyCode.UP) {
				bank.getMove().jump();
			}
			//Shows the scoreboard panel
			if (ep.getCode() == KeyCode.TAB) {
				bank.getGui().getScorePanel().setStyle("visibility: visible");
			}

		});

		bank.getLogin().getStage().addEventHandler(KeyEvent.KEY_RELEASED, er -> {
			//Removes the players right velocity
			if (er.getCode() == KeyCode.RIGHT) {
				bank.getMove().resetVelRight();
			}
			//Removes the players left velocity
			if (er.getCode() == KeyCode.LEFT) {
				bank.getMove().resetVelLeft();
			}
			//Hides the scoreboard panel
			if (er.getCode() == KeyCode.TAB) {
				bank.getGui().getScorePanel().setStyle("visibility: hidden");
			}
		});

		bank.getLogin().getStage().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
			//Enables/disables the chatt when pressing ENTER
			if (event.getCode() == KeyCode.ENTER) {
				if (isShowing) {
					message = bank.getGui().getMessageInput().getText();
					if (!message.equals("")) {
						bank.getPlayer().setMessage(message);
						bank.getGui().getMessageInput().clear();
						bank.getGui().getChatLog().setFocusTraversable(false);
						bank.getGui().getMessageInput().setFocusTraversable(false);
						isShowing = false;
					}
					bank.getGui().getChatUI().setStyle("visibility: hidden");
					bank.getGui().getMessageInput().setStyle("visibility: hidden");
					bank.getGui().getChatLog().setStyle("-fx-background-color: rgba(59, 64, 68, 0.2);");
					bank.getGui().getChatLog().lookup(".scroll-bar:vertical").setStyle("-fx-opacity: 0");
					isShowing = false;
				} else {
					bank.getGui().getMessageInput().setFocusTraversable(true);
					bank.getGui().getChatUI().setStyle("visibility: visible");
					bank.getGui().getMessageInput().setStyle("visibility: visible");

					bank.getGui().getChatLog().setStyle("-fx-background-color: rgba(59, 64, 68, 0.4);");
					bank.getGui().getChatLog().lookup(".scroll-bar:vertical").setStyle("-fx-opacity: 1");
					isShowing = true;
				}
			}

		});

	}

}
