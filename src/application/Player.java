package application;

import javafx.scene.shape.Circle;

import java.io.Serializable;

public class Player implements Serializable{
	private  boolean connected;
	private String username;
	private int port;
	private String ip;
	private final double size = 20.0;
	private double yPos = 10.0, xPos = 512.0, velY = 0;
	private double velLeft = 0, velRight = 0;
	private transient Circle ball = new Circle();
	private volatile boolean colUp = false, colDown = false, colLeft = false, colRight = false, firstTouch = false;
	private boolean isLeft = true;
	Bank bank;

	public Player(Bank bank) {
		this.bank=bank;
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

	public boolean isFirstTouch() {
		return firstTouch;
	}

	public void setFirstTouch(boolean firstTouch) {
		this.firstTouch = firstTouch;
	}

	public boolean isLeft() {
		return isLeft;
	}

	public void setLeft(boolean isLeft) {
		this.isLeft = isLeft;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setIsConnected(boolean Connected){
		this.connected = Connected;
	}

	public boolean isConnected() {
		return connected;
	}
}
