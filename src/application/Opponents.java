package application;

import java.util.ArrayList;

import javafx.scene.shape.Circle;

public class Opponents {

	Bank bank;
	private ArrayList<Player> opponentsList = new ArrayList<Player>();
	private ArrayList<Circle> opponentsCircleList = new ArrayList<Circle>();

	public Opponents(Bank bank) {
		for (int i = 0; i < 4; i++) {
			opponentsCircleList.add(new Circle(20));
		}
		this.bank = bank;
	}

	public void updateOpponents() {
		try{
			if(!(bank.getOpponents().getOpponentsList().size()==0)){
		for (int i = 0; i < bank.getOpponents().getOpponentsList().size(); i++) {
			bank.getOpponents().getOpponentsCircleList().get(i).setCenterX(bank.getOpponents().getOpponentsList().get(i).getxPos());
			bank.getOpponents().getOpponentsCircleList().get(i).setCenterY(bank.getOpponents().getOpponentsList().get(i).getyPos());
				}
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		

		// for (int i = 0; i < bank.getOpponents().getOpponentsList().size();
		// i++) {
		// bank.getGui().getRoot().getChildren().remove(bank.getOpponents().getOpponentsCircleList().get(i));
		// }
		// for (int i = 0; i < bank.getOpponents().getOpponentsList().size();
		// i++) {
		// bank.getOpponents().getOpponentsCircleList().get(i)
		// .setCenterX(bank.getOpponents().getOpponentsList().get(i).getxPos());
		// bank.getOpponents().getOpponentsCircleList().get(i)
		// .setCenterY(bank.getOpponents().getOpponentsList().get(i).getyPos());
		//
		// bank.getGui().getRoot().getChildren().add(bank.getOpponents().getOpponentsCircleList().get(i));
		//
		// }
		// }
	}

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

}
