package application;

import javafx.scene.shape.Line;

public class Bullet {

	private Player player;
	private Line bullet = new Line();

	public Bullet(Player player) {
		this.player = player;
		bullet.setStartX(player.getxPos());
		bullet.setEndX(player.getxPos()+10);
		bullet.setStartY(player.getyPos());
		bullet.setEndY(player.getyPos());
	}

}
