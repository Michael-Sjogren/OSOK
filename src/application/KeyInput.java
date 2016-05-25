package application;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyInput {

	public KeyInput(Bank bank) {

		bank.getLogin().getStage().addEventHandler(KeyEvent.KEY_PRESSED, ep -> {
			if (ep.getCode() == KeyCode.LEFT) {
				bank.getMove().moveLeft();
			}
		});
		
		bank.getLogin().getStage().addEventHandler(KeyEvent.KEY_PRESSED, ep -> {
			if (ep.getCode() == KeyCode.RIGHT) {
				bank.getMove().moveRight();
			}

		});

		bank.getLogin().getStage().addEventHandler(KeyEvent.KEY_RELEASED, er -> {
			if (er.getCode() == KeyCode.RIGHT) {
				bank.getMove().resetVelRight();
			}
		});

		bank.getLogin().getStage().addEventHandler(KeyEvent.KEY_RELEASED, er -> {
			if (er.getCode() == KeyCode.LEFT) {
				bank.getMove().resetVelLeft();
			}
		});

		bank.getLogin().getStage().addEventHandler(KeyEvent.KEY_PRESSED, ej -> {
			if (ej.getCode() == KeyCode.UP) {
				bank.getMove().jump();
			}
		});

		
	}

}
