package application;

/** Move class updates the player location depending on the players gravity and input **/
public class Move {
	Controller con;
	/** Constructor demands a reference to the "controller" **/ //which allows the class to work with other classes
	public Move(Controller con) {
		this.con=con;
	}
	/** Gets the player state and moves it acordingly **/
	public void movePlayer() {
		con.getPlayer().setxPos(con.getPlayer().getxPos() + con.getPlayer().getVelRight() + con.getPlayer().getVelLeft());
	    con.getPlayer().setyPos(con.getPlayer().getyPos() + con.getPlayer().getVelY());
	    updatePlayerBall();
			
	}
	/** Updates the player-circle position with the players cordinates **/
	public void updatePlayerBall(){
		con.getGui().getPlayerCircle().setCenterX(con.getPlayer().getxPos());
		con.getGui().getPlayerCircle().setCenterY(con.getPlayer().getyPos());
		con.getGui().getPlayerCircle().setCenterY(con.getPlayer().getyPos());
	}

	//Changes the player data---------------------
	public void changeVelY(double velY) {
		con.getPlayer().setVelY(velY);
	}

	public void moveLeft() {
		con.getPlayer().setVelLeft(-3);
	}

	public void moveRight() {
		con.getPlayer().setVelRight(+3);
	}

	public void resetVelLeft() {
		con.getPlayer().setVelLeft(0);
	}

	public void resetVelRight() {
		con.getPlayer().setVelRight(0);
	}
	//Changes the player data---------------------

	/** Makes the player jump by changing it's gravity to a negative state**/
	public void jump() {
		if (con.getPlayer().isColDown()) {
			con.getPlayer().setColDown(false);
			con.getPlayer().setVelY(-12);
		}
	}

}
