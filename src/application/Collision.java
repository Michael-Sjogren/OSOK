package application;

import javafx.scene.shape.Line;

public class Collision {

	Bank bank;
	
	public Collision(Bank bank) {
		this.bank=bank;

	}

	public void checkCollision() {
		checkNorth();
//		checkEast();
		checkSouth();
//		checkWest();
	}

	public void checkNorth() {
		boolean flag = false;
		for (int i = 0; i < bank.getPlatforms().size(); i++) {
			if (bank.getPlayer().getBall().getBoundsInParent().intersects(bank.getPlatforms().get(i).getPlatform().get(0).getBoundsInParent())) {
				if (!bank.getPlayer().isFirstTouch()) {
					bank.getPlayer().setVelY(0);
					bank.getPlayer().setFirstTouch(true);
					bank.getPlayer().setyPos(((Line) bank.getPlatforms().get(i).getPlatform().get(0)).getStartY() - bank.getPlayer().getBall().getRadius());
				}
				flag = true;
				bank.getPlayer().setColDown(true);
			}
			if (bank.getPlayer().getBall().getBoundsInParent().intersects(bank.getPlatforms().get(i).getPlatform().get(4).getBoundsInParent())) {
				if (!bank.getPlayer().isFirstTouch()) {
					bank.getPlayer().setVelY(0);
					bank.getPlayer().setFirstTouch(true);
					bank.getPlayer().setyPos(((Line) bank.getPlatforms().get(i).getPlatform().get(4)).getStartY() - bank.getPlayer().getBall().getRadius());
				}
				flag = true;
				bank.getPlayer().setColDown(true);
			}
		}
		if (!flag) {
			bank.getPlayer().setFirstTouch(false);
			bank.getPlayer().setColDown(false);
		}
	}

	public void checkEast() {
		boolean flag = false;
		for (int i = 0; i < bank.getPlatforms().size(); i++) {
			if (bank.getPlayer().getBall().getBoundsInParent().intersects(bank.getPlatforms().get(i).getPlatform().get(1).getBoundsInParent())) {
					bank.getPlayer().setxPos(((Line) bank.getPlatforms().get(i).getPlatform().get(1)).getStartX() + bank.getPlayer().getBall().getRadius()+1);
				}
				flag = true;
				bank.getPlayer().setColLeft(true);
			}
		if (!flag) {
			bank.getPlayer().setColLeft(false);
		}
	}
	

	public void checkSouth() {
		boolean flag = false;
		for (int i = 0; i < bank.getPlatforms().size(); i++) {
			if (bank.getPlayer().getBall().getBoundsInParent().intersects(bank.getPlatforms().get(i).getPlatform().get(2).getBoundsInParent())) {
					bank.getPlayer().setVelY(bank.getPlayer().getVelY()*-0.4);
					bank.getPlayer().setyPos(((Line) bank.getPlatforms().get(i).getPlatform().get(2)).getEndY() + bank.getPlayer().getBall().getRadius());
				}
				flag = true;
				bank.getPlayer().setColUp(true);
			}
		if (!flag) {
			bank.getPlayer().setColUp(false);
		}
	}

	public void checkWest() {
		boolean flag = false;
		for (int i = 0; i < bank.getPlatforms().size(); i++) {
			if (bank.getPlayer().getBall().getBoundsInParent().intersects(bank.getPlatforms().get(i).getPlatform().get(3).getBoundsInParent())) {
					bank.getPlayer().setxPos(((Line) bank.getPlatforms().get(i).getPlatform().get(3)).getStartX() + bank.getPlayer().getBall().getRadius()-1);
				}
				flag = true;
				bank.getPlayer().setColRight(true);
			}
		if (!flag) {
			bank.getPlayer().setColRight(false);
		}
	}
}