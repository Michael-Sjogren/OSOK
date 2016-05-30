package application;

public class Bullet {

	private transient Bank bank;
	private double idleCordsX = -60, idleCordsY = -60;
	private double currentCordsX = 0, currentCordsY = 0;
	private final double size = 3;
	private final double velX = 15.0;
	private boolean isLeft = true;
	private boolean flying = false;
	private boolean hit = false;
	private int distanceTravelled = 0;

	public Bullet(Bank bank) {
		this.bank = bank;

	}

	public void shootBullet() {
		if (!flying) {
			System.out.println("Shoot Bullet");
			flying = true;
			distanceTravelled = 0;
			if (bank.getPlayer().isLeft()) {
				isLeft = true;
			} else {
				isLeft = false;
			}
			currentCordsX = bank.getPlayer().getxPos();
			currentCordsY = bank.getPlayer().getyPos();
		}
	}

	public void moveBullet() {
		if (flying) {
			distanceTravelled += velX;
			bank.getGui().getPlayerBullet().setCenterX(currentCordsX);
			bank.getGui().getPlayerBullet().setCenterY(currentCordsY);
			if (this.isLeft()) {
				currentCordsX += velX * -1;
			} else {
				currentCordsX += velX * 1;
			}

			if (distanceTravelled > 1050 || distanceTravelled < -1050) {
				flying = false;
				hideBullet();
			}
			checkCollision();
		}

	}

	public void hideBullet() {
		currentCordsX = idleCordsX;
		currentCordsY = idleCordsY;
		bank.getGui().getPlayerBullet().setCenterX(idleCordsX);
		bank.getGui().getPlayerBullet().setCenterY(idleCordsY);
	}

	public void checkCollision() {
		for (int i = 0; i < bank.getOpponents().getOpponentsCircleList().size(); i++) {
			if(bank.getGui().getPlayerBullet().getBoundsInParent().intersects(bank.getOpponents().getOpponentsCircleList().get(i).getBoundsInParent())){
				System.out.println("Hit");
				flying = false;
				hideBullet();
				break;
			}
		}
	}

	public double getSize() {
		return size;
	}

	public double getIdleCordsX() {
		return idleCordsX;
	}

	public void setIdleCordsX(double idleCordsX) {
		this.idleCordsX = idleCordsX;
	}

	public double getIdleCordsY() {
		return idleCordsY;
	}

	public void setIdleCordsY(double idleCordsY) {
		this.idleCordsY = idleCordsY;
	}

	public double getCurrentCordsX() {
		return currentCordsX;
	}

	public void setCurrentCordsX(double currentCordsX) {
		this.currentCordsX = currentCordsX;
	}

	public double getCurrentCordsY() {
		return currentCordsY;
	}

	public void setCurrentCordsY(double currentCordsY) {
		this.currentCordsY = currentCordsY;
	}

	public double getVelX() {
		return velX;
	}

	public boolean isLeft() {
		return isLeft;
	}

	public void setLeft(boolean isLeft) {
		this.isLeft = isLeft;
	}

	public boolean isFlying() {
		return flying;
	}

	public void setFlying(boolean flying) {
		this.flying = flying;
	}

	public boolean isHit() {
		return hit;
	}

	public void setHit(boolean hit) {
		this.hit = hit;
	}

	public int getDistanceTravelled() {
		return distanceTravelled;
	}

	public void setDistanceTravelled(int distanceTravelled) {
		this.distanceTravelled = distanceTravelled;
	}

}
