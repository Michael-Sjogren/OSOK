package application;

public class Move {
	Player player;

	public Move(Player player) {
		this.player = player;
	}

	public void movePlayer() {
		player.setxPos(player.getxPos() + player.getVelRight()+player.getVelLeft());
		player.setyPos(player.getyPos() + player.getVelY());
	}

	public void changeVelY(double velY) {
		player.setVelY(velY);
	}

	public void moveLeft() {
		player.setVelLeft(-3);
	}

	public void moveRight() {
		player.setVelRight(+3);
	}
	public void resetVelLeft(){
		player.setVelLeft(0);
	}
	public void resetVelRight(){
		player.setVelRight(0);
	}

}
