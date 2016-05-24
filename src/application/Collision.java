package application;

import java.util.List;

import javafx.scene.shape.Line;

public class Collision {

	List<Platform2D> platforms;
	Player player;

	public Collision(List<Platform2D> platforms, Player player) {
		this.platforms = platforms;
		this.player = player;

	}

	public void checkCollision() {
		checkNorth();
//		checkEast();
		checkSouth();
//		checkWest();
	}

	public void checkNorth() {
		boolean flag = false;
		for (int i = 0; i < platforms.size(); i++) {
			if (player.getBall().getBoundsInParent().intersects(platforms.get(i).getPlatform().get(0).getBoundsInParent())) {
				if (!player.isFirstTouch()) {
					player.setVelY(0);
					player.setFirstTouch(true);
					player.setyPos(((Line) platforms.get(i).getPlatform().get(0)).getStartY() - player.getBall().getRadius());
				}
				flag = true;
				player.setColDown(true);
			}
			if (player.getBall().getBoundsInParent().intersects(platforms.get(i).getPlatform().get(4).getBoundsInParent())) {
				if (!player.isFirstTouch()) {
					player.setVelY(0);
					player.setFirstTouch(true);
					player.setyPos(((Line) platforms.get(i).getPlatform().get(4)).getStartY() - player.getBall().getRadius());
				}
				flag = true;
				player.setColDown(true);
			}
		}
		if (!flag) {
			player.setFirstTouch(false);
			player.setColDown(false);
		}
	}

	public void checkEast() {
		boolean flag = false;
		for (int i = 0; i < platforms.size(); i++) {
			if (player.getBall().getBoundsInParent().intersects(platforms.get(i).getPlatform().get(1).getBoundsInParent())) {
					player.setxPos(((Line) platforms.get(i).getPlatform().get(1)).getStartX() + player.getBall().getRadius()+1);
				}
				flag = true;
				player.setColLeft(true);
			}
		if (!flag) {
			player.setColLeft(false);
		}
	}
	

	public void checkSouth() {
		boolean flag = false;
		for (int i = 0; i < platforms.size(); i++) {
			if (player.getBall().getBoundsInParent().intersects(platforms.get(i).getPlatform().get(2).getBoundsInParent())) {
					player.setVelY(player.getVelY()*-0.4);
					player.setyPos(((Line) platforms.get(i).getPlatform().get(2)).getEndY() + player.getBall().getRadius());
				}
				flag = true;
				player.setColUp(true);
			}
		if (!flag) {
			player.setColUp(false);
		}
	}

	public void checkWest() {
		boolean flag = false;
		for (int i = 0; i < platforms.size(); i++) {
			if (player.getBall().getBoundsInParent().intersects(platforms.get(i).getPlatform().get(3).getBoundsInParent())) {
					player.setxPos(((Line) platforms.get(i).getPlatform().get(3)).getStartX() + player.getBall().getRadius()-1);
				}
				flag = true;
				player.setColRight(true);
			}
		if (!flag) {
			player.setColRight(false);
		}
	}
}