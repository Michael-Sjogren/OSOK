package application;

import javafx.scene.shape.Line;

public class Collision {

	Bank bank;

	public Collision(Bank bank) {
		this.bank = bank;

	}

	// Checks collision for all the diffrent kind of lines
	public void checkCollision() {
		checkNorth();
		// checkEast();
		checkSouth();
		// checkWest();
	}

	/** Checks if the player is intesecting with a north line and/or the floor **/
	public void checkNorth() {
		// a flag which holds a boolean. The boolean changes value if the if statement is fulfilled
		boolean flag = false;
		for (int i = 0; i < bank.getPlatforms().size(); i++) {
			// check if the player is touching one of the north lines
			if (bank.getGui().getPlayerCircle().getBoundsInParent()
					.intersects(bank.getPlatforms().get(i).getPlatform().get(0).getBoundsInParent())) {
				if (!bank.getPlayer().isFirstTouch()) {
					bank.getPlayer().setVelY(0);
					bank.getPlayer().setFirstTouch(true);
					bank.getPlayer().setyPos(((Line) bank.getPlatforms().get(i).getPlatform().get(0)).getStartY()
							- bank.getGui().getPlayerCircle().getRadius());
					bank.getMove().updatePlayerBall();
				}
				flag = true;
				bank.getPlayer().setColDown(true);
			}
			// check if the player is touching a floor line
			if (bank.getGui().getPlayerCircle().getBoundsInParent()
					.intersects(bank.getPlatforms().get(i).getPlatform().get(4).getBoundsInParent())) {
				if (!bank.getPlayer().isFirstTouch()) {
					bank.getPlayer().setVelY(0);
					bank.getPlayer().setFirstTouch(true);
					bank.getPlayer().setyPos(((Line) bank.getPlatforms().get(i).getPlatform().get(4)).getStartY()
							- bank.getGui().getPlayerCircle().getRadius());
					bank.getMove().updatePlayerBall();
				}
				flag = true;
				bank.getPlayer().setColDown(true);
			}
		}
		// the player isnt touching anyting set collision to false
		if (!flag) {
			bank.getPlayer().setFirstTouch(false);
			bank.getPlayer().setColDown(false);
		}
	}

	// Not used AMT
	public void checkEast() {
		boolean flag = false;
		for (int i = 0; i < bank.getPlatforms().size(); i++) {
			if (bank.getGui().getPlayerCircle().getBoundsInParent()
					.intersects(bank.getPlatforms().get(i).getPlatform().get(1).getBoundsInParent())) {
				bank.getPlayer().setxPos(((Line) bank.getPlatforms().get(i).getPlatform().get(1)).getStartX()
						+ bank.getGui().getPlayerCircle().getRadius() + 1);
				bank.getMove().updatePlayerBall();
			}
			flag = true;
			bank.getPlayer().setColLeft(true);
		}
		if (!flag) {
			bank.getPlayer().setColLeft(false);
		}
	}
	/** Checks if the player is intesecting with a south line **/
	public void checkSouth() {
		// a flag which holds a boolean. The boolean changes value if the if statement is fulfilled
		boolean flag = false;
		for (int i = 0; i < bank.getPlatforms().size(); i++) {
			// Check if the player is touching one of the south lines 
			// And if so change the players gravity to positive so he starts falling
			if (bank.getGui().getPlayerCircle().getBoundsInParent()
					.intersects(bank.getPlatforms().get(i).getPlatform().get(2).getBoundsInParent())) {
				bank.getPlayer().setVelY(bank.getPlayer().getVelY() * -0.4);
				bank.getPlayer().setyPos(((Line) bank.getPlatforms().get(i).getPlatform().get(2)).getEndY()
						+ bank.getGui().getPlayerCircle().getRadius());
				bank.getMove().updatePlayerBall();
			}
			flag = true;
			bank.getPlayer().setColUp(true);
		}
		if (!flag) {
			bank.getPlayer().setColUp(false);
		}
	}
	
	// Not used AMT
	public void checkWest() {
		boolean flag = false;
		for (int i = 0; i < bank.getPlatforms().size(); i++) {
			if (bank.getGui().getPlayerCircle().getBoundsInParent()
					.intersects(bank.getPlatforms().get(i).getPlatform().get(3).getBoundsInParent())) {
				bank.getPlayer().setxPos(((Line) bank.getPlatforms().get(i).getPlatform().get(3)).getStartX()
						+ bank.getGui().getPlayerCircle().getRadius() - 1);
				bank.getMove().updatePlayerBall();
			}
			flag = true;
			bank.getPlayer().setColRight(true);
		}
		if (!flag) {
			bank.getPlayer().setColRight(false);
		}
	}
}