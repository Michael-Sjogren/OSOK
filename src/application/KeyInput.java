package application;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.ScrollBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public class KeyInput {
	private String message;
    private boolean isShowing = false;

	public KeyInput(Bank bank) {

		bank.getLogin().getStage().addEventHandler(KeyEvent.KEY_PRESSED, ep -> {
			if (ep.getCode() == KeyCode.LEFT) {
				bank.getMove().moveLeft();
				bank.getGui().getPlayerCircle().setFill(bank.getGui().getPatternLookLeft());
				bank.getPlayer().setLeft(true);
			}
			if (ep.getCode() == KeyCode.SPACE) {
				Bullet bullet = new Bullet(bank.getPlayer(),bank);
			}
			if (ep.getCode() == KeyCode.RIGHT) {
				bank.getMove().moveRight();
				bank.getGui().getPlayerCircle().setFill(bank.getGui().getPatternLookRight());
				bank.getPlayer().setLeft(false);
			}
			if (ep.getCode() == KeyCode.UP) {
				bank.getMove().jump();
			}
		});

		bank.getLogin().getStage().addEventHandler(KeyEvent.KEY_PRESSED, ep -> {
			

		});

		bank.getLogin().getStage().addEventHandler(KeyEvent.KEY_RELEASED, er -> {
			if (er.getCode() == KeyCode.RIGHT) {
				bank.getMove().resetVelRight();
			}
			if (er.getCode() == KeyCode.LEFT) {
				bank.getMove().resetVelLeft();
			}
		});

		bank.getLogin().getStage().addEventHandler(KeyEvent.KEY_RELEASED, er -> {
			
		});

		bank.getLogin().getStage().addEventHandler(KeyEvent.KEY_PRESSED, ej -> {
			
			bank.getLogin().getStage().addEventHandler(KeyEvent.KEY_PRESSED, es -> {
				
			});
		});


		bank.getGui().getMessageInput().addEventHandler(ActionEvent.ANY , e -> {

			 message = bank.getGui().getMessageInput().getText();
			if(!message.equals("")){
				bank.getPlayer().setMessage(message);
                bank.getGui().getMessageInput().clear();
                bank.getGui().getChatLog().setFocusTraversable(false);
                bank.getGui().getMessageInput().setFocusTraversable(false);
                isShowing = false;
			}
		});

        bank.getLogin().getStage().addEventHandler(KeyEvent.KEY_RELEASED , event -> {
            if(event.getCode() == KeyCode.ENTER){
                if(isShowing){
                    bank.getGui().getMessageInput().setStyle("visibility: hidden");
                    bank.getGui().getChatLog().setStyle("-fx-background-color: rgba(59, 64, 68, 0.2);");
                    bank.getGui().getChatLog().lookup(".scroll-bar:vertical").setStyle("-fx-opacity: 0");
                    isShowing = false;
                }else{
                    bank.getGui().getMessageInput().setFocusTraversable(true);
                    bank.getGui().getMessageInput().setStyle("visibility: visible");
                    bank.getGui().getChatLog().setStyle("-fx-background-color: rgba(59, 64, 68, 0.4);");
                    bank.getGui().getChatLog().lookup(".scroll-bar:vertical").setStyle("-fx-opacity: 1");
                    isShowing = true;
                }

            }
        });







	}

}
