package application;

import javafx.scene.shape.Circle;

public class Player {
	private String username;
	private final double size = 20.0;
	private double yPos = 10.0, xPos = 512.0, velY = 0.1;
	private double velLeft = 0, velRight = 0;
	private Circle ball = new Circle();
	private volatile boolean colUp= false, colDown= false, colLeft= false, colRight = false;

	

	public Player() {
		ball.setRadius(size);
		ball.setCenterX(xPos);
		ball.setCenterY(yPos);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Double getxPos() {
		return xPos;
	}

	public void setxPos(Double xPos) {
		this.xPos = xPos;
		ball.setCenterX(xPos);
	}

	public Double getyPos() {
		return yPos;
	}

	public void setyPos(Double yPos) {
		this.yPos = yPos;
		ball.setCenterY(yPos);
	}

	public Circle getBall() {
		return ball;
	}

	public double getVelY() {
		return velY;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}

	public double getVelRight() {
		return velRight;
	}

	public void setVelRight(double velRight) {
		this.velRight = velRight;
	}

	public double getVelLeft() {
		return velLeft;
	}

	public void setVelLeft(double velLeft) {
		this.velLeft = velLeft;
	}
	public boolean isColUp() {
		return colUp;
	}

	public void setColUp(boolean colUp) {
		this.colUp = colUp;
	}

	public boolean isColDown() {
		return colDown;
	}

	public void setColDown(boolean colDown) {
		this.colDown = colDown;
	}

	public boolean isColLeft() {
		return colLeft;
	}

	public void setColLeft(boolean colLeft) {
		this.colLeft = colLeft;
	}

	public boolean isColRight() {
		return colRight;
	}

	public void setColRight(boolean colRight) {
		this.colRight = colRight;
	}

}
