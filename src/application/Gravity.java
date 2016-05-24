package application;

public class Gravity {
	Player player;

	public Gravity(Player player) {
		this.player = player;
	}
	public void fall(){
		if(!player.isColDown()){
		player.setVelY(player.getVelY()+0.1/.40);
		}
	}
}
