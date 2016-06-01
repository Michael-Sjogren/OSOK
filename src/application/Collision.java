package application;

import javafx.scene.shape.Line;

public class Collision {

	Controller con;

	public Collision(Controller con) {
		this.con = con;

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
		for (int i = 0; i < con.getPlatforms().size(); i++) {
			// check if the player is touching one of the north lines
			if (con.getGui().getPlayerCircle().getBoundsInParent()
					.intersects(con.getPlatforms().get(i).getPlatform().get(0).getBoundsInParent())) {
				if (!con.getPlayer().isFirstTouch()) {
					con.getPlayer().setVelY(0);
					con.getPlayer().setFirstTouch(true);
					con.getPlayer().setyPos(((Line) con.getPlatforms().get(i).getPlatform().get(0)).getStartY()
							- con.getGui().getPlayerCircle().getRadius());
					con.getMove().updatePlayerBall();
				}
				flag = true;
				con.getPlayer().setColDown(true);
			}
			// check if the player is touching a floor line
			if (con.getGui().getPlayerCircle().getBoundsInParent()
					.intersects(con.getPlatforms().get(i).getPlatform().get(4).getBoundsInParent())) {
				if (!con.getPlayer().isFirstTouch()) {
					con.getPlayer().setVelY(0);
					con.getPlayer().setFirstTouch(true);
					con.getPlayer().setyPos(((Line) con.getPlatforms().get(i).getPlatform().get(4)).getStartY()
							- con.getGui().getPlayerCircle().getRadius());
					con.getMove().updatePlayerBall();
				}
				flag = true;
				con.getPlayer().setColDown(true);
			}
		}
		// the player isnt touching anyting set collision to false
		if (!flag) {
			con.getPlayer().setFirstTouch(false);
			con.getPlayer().setColDown(false);
		}
	}

	// Not used AMT
	public void checkEast() {
		boolean flag = false;
		for (int i = 0; i < con.getPlatforms().size(); i++) {
			if (con.getGui().getPlayerCircle().getBoundsInParent()
					.intersects(con.getPlatforms().get(i).getPlatform().get(1).getBoundsInParent())) {
				con.getPlayer().setxPos(((Line) con.getPlatforms().get(i).getPlatform().get(1)).getStartX()
						+ con.getGui().getPlayerCircle().getRadius() + 1);
				con.getMove().updatePlayerBall();
			}
			flag = true;
			con.getPlayer().setColLeft(true);
		}
		if (!flag) {
			con.getPlayer().setColLeft(false);
		}
	}
	/** Checks if the player is intesecting with a south line **/
	public void checkSouth() {
		// a flag which holds a boolean. The boolean changes value if the if statement is fulfilled
		boolean flag = false;
		for (int i = 0; i < con.getPlatforms().size(); i++) {
			// Check if the player is touching one of the south lines 
			// And if so change the players gravity to positive so he starts falling
			if (con.getGui().getPlayerCircle().getBoundsInParent()
					.intersects(con.getPlatforms().get(i).getPlatform().get(2).getBoundsInParent())) {
				con.getPlayer().setVelY(con.getPlayer().getVelY() * -0.4);
				con.getPlayer().setyPos(((Line) con.getPlatforms().get(i).getPlatform().get(2)).getEndY()
						+ con.getGui().getPlayerCircle().getRadius());
				con.getMove().updatePlayerBall();
			}
			flag = true;
			con.getPlayer().setColUp(true);
		}
		if (!flag) {
			con.getPlayer().setColUp(false);
		}
	}
	
	// Not used AMT
	public void checkWest() {
		boolean flag = false;
		for (int i = 0; i < con.getPlatforms().size(); i++) {
			if (con.getGui().getPlayerCircle().getBoundsInParent()
					.intersects(con.getPlatforms().get(i).getPlatform().get(3).getBoundsInParent())) {
				con.getPlayer().setxPos(((Line) con.getPlatforms().get(i).getPlatform().get(3)).getStartX()
						+ con.getGui().getPlayerCircle().getRadius() - 1);
				con.getMove().updatePlayerBall();
			}
			flag = true;
			con.getPlayer().setColRight(true);
		}
		if (!flag) {
			con.getPlayer().setColRight(false);
		}
	}
}