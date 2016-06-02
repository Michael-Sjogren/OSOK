package application;
/** Bullet class is a POJO of a bullet, it contains all the necessary infromation of the in-game bullet **/
public class Bullet {

	private transient Controller con;
	private double idleCordsX = -60, idleCordsY = -60;
	private double currentCordsX = 0, currentCordsY = 0;
	private final double size = 5;
	private final double velX = 15.0;
	private boolean isLeft = true;
	private boolean flying = false;
	private boolean hit = false;
	private int distanceTravelled = 0;
	private int timeSinceShot = 0;

	/** Constructor demands a reference to the "controller" **/ //which allows the class to work with other classes
	public Bullet(Controller con) {
		this.con = con;

	}
	

	/** Shoots the bullet from the players position if the bullet isnt already in-air **/
	public void shootBullet() {
		if (!flying&&timeSinceShot>79) {
			timeSinceShot = 0;
			System.out.println("Shoot Bullet");
			flying = true;
			distanceTravelled = 0;
			//Checks the players direction and sets the bullets accordingly
			if (con.getPlayer().isLeft()) {
				isLeft = true;
			} else {
				isLeft = false;
			}
			currentCordsX = con.getPlayer().getxPos();
			//corrects the bullet spawn point to match the barrel of the player
			currentCordsY = con.getPlayer().getyPos()-5;
		}
	}

	/** Moves the bullet a set amount of pixels **/
	public void moveBullet() {
		timeSinceShot +=1;
		if (flying) {
			//adds the pixels flewn to the distanceTravelled variable
			distanceTravelled += velX;
			con.getGui().getPlayerBullet().setCenterX(currentCordsX);
			con.getGui().getPlayerBullet().setCenterY(currentCordsY);
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
		con.getGui().getPlayerBullet().setCenterX(idleCordsX);
		con.getGui().getPlayerBullet().setCenterY(idleCordsY);
	}

	/** Checks if the players bullet is intercecting with a opponent **/
	public void checkCollision() {
		for (int i = 0; i < con.getOpponents().getOpponentsCircleList().size(); i++) {
			//Checks if the playerbullet is intersecting with any of the oponent circles
			if(con.getGui().getPlayerBullet().getBoundsInParent().intersects(con.getOpponents().getOpponentsCircleList().get(i).getBoundsInParent())){
				//When a opponent is hit the bullet gets removed from screen and the player gets 1 point
				System.out.println("Hit");
				con.getPlayer().setScore(con.getPlayer().getScore()+1);
				System.out.println(con.getPlayer().getScore());
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

	public int getTimeSinceShot() {
		return timeSinceShot;
	}

	public void setTimeSinceShot(int timeSinceShot) {
		this.timeSinceShot = timeSinceShot;
	}

	/** ----------------------------------------------------------- **/
	
}
