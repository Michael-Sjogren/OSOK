package application;

import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyInput {
	private String message;
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
				System.out.println(message);
			}
		});





	}

}
