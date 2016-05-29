package application;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Player implements Serializable{

	private transient LinkedList<Bullet> bulletList = new LinkedList<Bullet>();
	private boolean connected;
	private String username;
	private transient String message;
	private int port;
	private String ip;
	private final double size = 20.0;
	private double yPos = 10.0, xPos = 512.0, velY = 0;
	private double velLeft = 0, velRight = 0;
	private volatile boolean colUp = false, colDown = false, colLeft = false, colRight = false, firstTouch = false;
	private boolean isLeft = true;
    private transient String newMessage;

    public Player() {
	}

	public double getSize() {
		return size;
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
	}

	public Double getyPos() {
		return yPos;
	}

	public void setyPos(Double yPos) {
		this.yPos = yPos;
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

	public List<Bullet> getBulletList() {
		return bulletList;
	}

	public void setBulletList(LinkedList<Bullet> bulletList) {
		this.bulletList = bulletList;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
