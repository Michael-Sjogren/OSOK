package application;

public class Move {
	Bank bank;

	public Move(Bank bank) {
		this.bank=bank;
	}

	public void movePlayer() {
		bank.getPlayer().setxPos(bank.getPlayer().getxPos() + bank.getPlayer().getVelRight() + bank.getPlayer().getVelLeft());
	    bank.getPlayer().setyPos(bank.getPlayer().getyPos() + bank.getPlayer().getVelY());
	    updatePlayerBall();
			
	}
	
	public void updatePlayerBall(){
		bank.getGui().getPlayerCircle().setCenterX(bank.getPlayer().getxPos());
		bank.getGui().getPlayerCircle().setCenterY(bank.getPlayer().getyPos());
		bank.getGui().getPlayerCircle().setCenterY(bank.getPlayer().getyPos());
	}

	public void changeVelY(double velY) {
		bank.getPlayer().setVelY(velY);
	}

	public void moveLeft() {
		bank.getPlayer().setVelLeft(-3);
		bank.getPlayer().setLeft(true);
	}

	public void moveRight() {
		bank.getPlayer().setVelRight(+3);
		bank.getPlayer().setLeft(false);
	}

	public void resetVelLeft() {
		bank.getPlayer().setVelLeft(0);
		bank.getPlayer().setLeft(true);
	}

	public void resetVelRight() {
		bank.getPlayer().setVelRight(0);
		bank.getPlayer().setLeft(false);
	}

	public void jump() {
		if (bank.getPlayer().isColDown()) {
			bank.getPlayer().setColDown(false);
			bank.getPlayer().setVelY(-12);
		}
	}

}
