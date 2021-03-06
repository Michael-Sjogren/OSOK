package application;
/** Scoreboard class updates the scoeboard 2.5 times a second  **/
public class Scoreboard implements Runnable {

	private Controller con;
	private boolean running = true;
	/** Constructor demands a reference to the "controller" **/ //which allows the class to work with other classes
	public Scoreboard(Controller con) {
		this.con = con;
	}

	@Override
	public void run() {
		try {
			while (running) {
				if (!(con.getOpponents().getOpponentsList() == null)) {
				String temp = "";
				temp += con.getPlayer().getUsername() + ": " + con.getPlayer().getScore() + "\n";
					try {
						for (int i = 0; i < con.getOpponents().getOpponentsList().size(); i++) {
							temp += con.getOpponents().getOpponentsList().get(i).getUsername() + ": "
									+ con.getOpponents().getOpponentsList().get(i).getScore() + "\n";
						}
					} catch (Exception e) {
						
					}
					con.getGui().getScore().setText(temp);
				}

				Thread.sleep(400);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//Setters and Getters
	
	public void stopThread(){
		running = false;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

}
