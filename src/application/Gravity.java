package application;

public class Gravity {
	Bank bank;

	public Gravity(Bank bank) {
		this.bank=bank;
	}
	public void fall(){
		if(!bank.getPlayer().isColDown()){
		bank.getPlayer().setVelY(bank.getPlayer().getVelY()+0.1/.30);
		}
	}
}
