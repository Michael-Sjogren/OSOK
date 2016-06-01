package application;
/** Gravity affects the player's Y velocity**/
public class Gravity {
	Controller con;

	/** Constructor demands a reference to the "controller" **/ //which allows the class to work with other classes
	public Gravity(Controller con) {
		this.con=con;
	}
	/** Affects the player with a gravity that increases with 1/3 every tick(60hz) **/
	public void fall(){
		if(!con.getPlayer().isColDown()){
		con.getPlayer().setVelY(con.getPlayer().getVelY()+0.1/.30);
		}
	}
}
