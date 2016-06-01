package application;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
/** Opponents class holds all the nodes/shapes which gets drawn and moved depending on the players you are facing **/
public class Opponents {

	Controller con;
	private ArrayList<Player> opponentsList = new ArrayList<Player>();
	private ArrayList<Bullet> bulletsList = new ArrayList<Bullet>();
	private ArrayList<Circle> opponentsCircleList = new ArrayList<Circle>();
	private ArrayList<Circle> opponentsBulletList = new ArrayList<Circle>();
	ImagePattern imageLookLeft;
	ImagePattern imageLookRight;
	/** Constructor demands a reference to the "controller" **/ //which allows the class to work with other classes
	public Opponents(Controller con) {
		//Gets the images for the opponent enteties
		imageLookLeft = new ImagePattern(new Image("Sprite-00012.png"));
		imageLookRight = new ImagePattern(new Image("Sprite-00013.png"));
		
		//Adds 4 "dead" circles which can be influenced by other clients, theese represent the players themselves
		for (int i = 0; i < 4; i++) {
			opponentsCircleList.add(new Circle(-30,-30,20));
		}
		//Adds 4 circles which can be influenced by other clients, theese represent the player's bullets
		for (int i = 0; i < 4; i++) {
			Circle temp = new Circle(-60,-60,5,Color.GOLDENROD);
			temp.setStroke(Color.BLACK);
			temp.setStrokeWidth(2);
			opponentsBulletList.add(temp);
			
		}
		this.con = con;
	}
/** Updates the opponents bullet and circle location **/
	public void updateOpponents() {
		try{
			if(!(con.getOpponents().getOpponentsList().size()==0)){
				//Loops through all the opponents sent to the client from the server
		for (int i = 0; i < con.getOpponents().getOpponentsList().size(); i++) {
			//Changes the images depending on the way the opponent is facing
			if(con.getOpponents().getOpponentsList().get(i).isLeft()){
				con.getOpponents().getOpponentsCircleList().get(i).setFill(imageLookLeft);
			}else{
				con.getOpponents().getOpponentsCircleList().get(i).setFill(imageLookRight);
			}
			//Update the dead circles to match the cordinates of the other clients
			con.getOpponents().getOpponentsCircleList().get(i).setCenterX(con.getOpponents().getOpponentsList().get(i).getxPos());
			con.getOpponents().getOpponentsCircleList().get(i).setCenterY(con.getOpponents().getOpponentsList().get(i).getyPos());
			con.getOpponents().getOpponentsBulletList().get(i).setCenterX(con.getOpponents().getBulletsList().get(i).getCurrentCordsX());
			con.getOpponents().getOpponentsBulletList().get(i).setCenterY(con.getOpponents().getBulletsList().get(i).getCurrentCordsY());
			
				}
			}
			//Moves all the circles which no one is controlling of the screen
			for(int i = con.getOpponents().getOpponentsList().size();i<4;i++){
				con.getOpponents().getOpponentsCircleList().get(i).setCenterX(-30);
				con.getOpponents().getOpponentsCircleList().get(i).setCenterY(-30);
						}
		}catch(Exception e){
			
		}
	}
	
	
	/** Setter and getters **/

	public ArrayList<Player> getOpponentsList() {
		return opponentsList;
	}

	public void setOpponentsList(ArrayList<Player> opponentsList) {
		this.opponentsList = opponentsList;
	}

	public ArrayList<Circle> getOpponentsCircleList() {
		return opponentsCircleList;
	}

	public void setOpponentsCircleList(ArrayList<Circle> opponentsCircleList) {
		this.opponentsCircleList = opponentsCircleList;
	}

	public ArrayList<Circle> getOpponentsBulletList() {
		return opponentsBulletList;
	}

	public void setOpponentsBulletList(ArrayList<Circle> opponentsBulletList) {
		this.opponentsBulletList = opponentsBulletList;
	}

	public ArrayList<Bullet> getBulletsList() {
		return bulletsList;
	}

	public void setBulletList(ArrayList<Bullet> bulletList) {
		this.bulletsList = bulletList;
	}
	
	/** ----------------------------------------------------------- **/

}
