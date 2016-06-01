package application;
/** Bullet class is a POJO of a bullet, it contains all the necessary infromation of the in-game bullet **/
public class Bullet {

	private transient Bank bank;
	private double idleCordsX = -60, idleCordsY = -60;
	private double currentCordsX = 0, currentCordsY = 0;
	private final double size = 5;
	private final double velX = 15.0;
	private boolean isLeft = true;
	private boolean flying = false;
	private boolean hit = false;
	private int distanceTravelled = 0;
	private int timeFlewn = 0;

	/** Constructor demands a reference to the "controller" **/ //which allows the class to work with other classes
	public Bullet(Bank bank) {
		this.bank = bank;

	}
	

	/** Shoots the bullet from the players position if the bullet isnt already in-air **/
	public void shootBullet() {
		if (!flying) {
			System.out.println("Shoot Bullet");
			flying = true;
			distanceTravelled = 0;
			//Checks the players direction and sets the bullets accordingly
			if (bank.getPlayer().isLeft()) {
				isLeft = true;
			} else {
				isLeft = false;
			}
			currentCordsX = bank.getPlayer().getxPos();
			//corrects the bullet spawn point to match the barrel of the player
			currentCordsY = bank.getPlayer().getyPos()-5;
		}
	}

	/** Moves the bullet a set amount of pixels **/
	public void moveBullet() {
		if (flying) {
			//adds the pixels flewn to the distanceTravelled variable
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

	/** Removes the bullet from the screen to the idle-cords **/
	public void hideBullet() {
		currentCordsX = idleCordsX;
		currentCordsY = idleCordsY;
		bank.getGui().getPlayerBullet().setCenterX(idleCordsX);
		bank.getGui().getPlayerBullet().setCenterY(idleCordsY);
	}

	/** Checks if the players bullet is intercecting with a opponent **/
	public void checkCollision() {
		for (int i = 0; i < bank.getOpponents().getOpponentsCircleList().size(); i++) {
			//Checks if the playerbullet is intersecting with any of the oponent circles
			if(bank.getGui().getPlayerBullet().getBoundsInParent().intersects(bank.getOpponents().getOpponentsCircleList().get(i).getBoundsInParent())){
				//When a opponent is hit the bullet gets removed from screen and the player gets 1 point
				System.out.println("Hit");
				bank.getPlayer().setScore(bank.getPlayer().getScore()+1);
				System.out.println(bank.getPlayer().getScore());
				flying = false;
				hideBullet();
				break;
			}
		}
	}
	
	/** Setters and Getters **/

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

	public int getTimeFlewn() {
		return timeFlewn;
	}

	public void setTimeFlewn(int timeFlewn) {
		this.timeFlewn = timeFlewn;
	}

	/** ----------------------------------------------------------- **/
	
}
