package application;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyInput {

	public KeyInput(Bank bank) {

		bank.getLogin().getStage().addEventHandler(KeyEvent.KEY_PRESSED, ep -> {
			if (ep.getCode() == KeyCode.LEFT) {
				bank.getMove().moveLeft();
				bank.getGui().getPlayerCircle().setFill(bank.getGui().getPatternLookLeft());
			}
			if (ep.getCode() == KeyCode.SPACE) {
				Bullet bullet = new Bullet(bank.getPlayer(),bank);
			}
			if (ep.getCode() == KeyCode.RIGHT) {
				bank.getMove().moveRight();
				bank.getGui().getPlayerCircle().setFill(bank.getGui().getPatternLookRight());
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

	}

}
