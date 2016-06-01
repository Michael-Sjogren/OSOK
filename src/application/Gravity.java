package application;
/** Gravity affects the player's Y velocity**/
public class Gravity {
	Bank bank;

	/** Constructor demands a reference to the "controller" **/ //which allows the class to work with other classes
	public Gravity(Bank bank) {
		this.bank=bank;
	}
	/** Affects the player with a gravity that increases with 1/3 every tick(60hz) **/
	public void fall(){
		if(!bank.getPlayer().isColDown()){
		bank.getPlayer().setVelY(bank.getPlayer().getVelY()+0.1/.30);
		}
	}
}
