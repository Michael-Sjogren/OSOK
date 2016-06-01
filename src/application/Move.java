package application;

/** Move class updates the player location depending on the players gravity and input **/
public class Move {
	Bank bank;
	/** Constructor demands a reference to the "controller" **/ //which allows the class to work with other classes
	public Move(Bank bank) {
		this.bank=bank;
	}
	/** Gets the player state and moves it acordingly **/
	public void movePlayer() {
		bank.getPlayer().setxPos(bank.getPlayer().getxPos() + bank.getPlayer().getVelRight() + bank.getPlayer().getVelLeft());
	    bank.getPlayer().setyPos(bank.getPlayer().getyPos() + bank.getPlayer().getVelY());
	    updatePlayerBall();
			
	}
	/** Updates the player-circle position with the players cordinates **/
	public void updatePlayerBall(){
		bank.getGui().getPlayerCircle().setCenterX(bank.getPlayer().getxPos());
		bank.getGui().getPlayerCircle().setCenterY(bank.getPlayer().getyPos());
		bank.getGui().getPlayerCircle().setCenterY(bank.getPlayer().getyPos());
	}

	//Changes the player data---------------------
	public void changeVelY(double velY) {
		bank.getPlayer().setVelY(velY);
	}

	public void moveLeft() {
		bank.getPlayer().setVelLeft(-3);
	}

	public void moveRight() {
		bank.getPlayer().setVelRight(+3);
	}

	public void resetVelLeft() {
		bank.getPlayer().setVelLeft(0);
	}

	public void resetVelRight() {
		bank.getPlayer().setVelRight(0);
	}
	//Changes the player data---------------------

	/** Makes the player jump by changing it's gravity to a negative state**/
	public void jump() {
		if (bank.getPlayer().isColDown()) {
			bank.getPlayer().setColDown(false);
			bank.getPlayer().setVelY(-12);
		}
	}

}
