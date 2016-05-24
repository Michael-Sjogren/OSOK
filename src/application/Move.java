package application;

public class Move {
	Player player;

	public Move(Player player) {
		this.player = player;
	}

	public void movePlayer() {
		player.setxPos(player.getxPos() + player.getVelRight() + player.getVelLeft());
			player.setyPos(player.getyPos() + player.getVelY());
	}

	public void changeVelY(double velY) {
		player.setVelY(velY);
	}

	public void moveLeft() {
		player.setVelLeft(-3);
		player.setLeft(true);
	}

	public void moveRight() {
		player.setVelRight(+3);
		player.setLeft(false);
	}

	public void resetVelLeft() {
		player.setVelLeft(0);
		player.setLeft(true);
	}

	public void resetVelRight() {
		player.setVelRight(0);
		player.setLeft(false);
	}

	public void jump() {
		if (player.isColDown()) {
			player.setColDown(false);
			player.setVelY(-12);
		}
	}

}
