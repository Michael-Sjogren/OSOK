package application;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.shape.Line;

public class Bullet {

	private Player player;
	private Bank bank;
	private Line bulletLine;
	private final int width = 5;
	private int velX = 8;
	private int distanceTravelled = 0;

	public Bullet(Player player, Bank bank) {
		System.out.println("Spawn Bullet");
		this.bank = bank;
		this.player = player;
		bulletLine = new Line(player.getxPos(), player.getyPos(), player.getxPos() + width, player.getyPos());
		bulletLine.setStrokeWidth(3);
		player.getBulletList().add(this);
		bank.getGui().getRoot().getChildren().add(bulletLine);
		if(bank.getPlayer().isLeft()){
			velX *=-1;
		}else{
			velX*=1;
		}

	}

	public void moveBullet() {

		bulletLine.setStartX(bulletLine.getStartX() + velX);
		bulletLine.setStartY(bulletLine.getStartY());
		bulletLine.setEndX(bulletLine.getEndX() + velX);
		bulletLine.setEndY(bulletLine.getEndY());
		
		distanceTravelled += velX;
		
		if (distanceTravelled > 1200) {
			deleteBullet();
		}
	}

	public void deleteBullet() {
		System.out.println("Delete Button");
		List<Bullet> temp = new ArrayList<Bullet>();
		for (int i = 0; i < player.getBulletList().size(); i++) {
			if (player.getBulletList().get(i).equals(this)) {
				
			}else{
				temp.add(player.getBulletList().get(i));
			}
		}
		player.setBulletList(temp);
	}

}
