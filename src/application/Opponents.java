package application;

import java.util.ArrayList;

public class Opponents {
	
	Bank bank;
	private ArrayList<Player> opponentsList = new ArrayList<Player>();

	public Opponents(Bank bank){
		this.bank=bank;
	}
	
	public ArrayList<Player> getOpponentsList() {
		return opponentsList;
	}

	public void setOpponentsList(ArrayList<Player> opponentsList) {
		this.opponentsList = opponentsList;
	}

}
