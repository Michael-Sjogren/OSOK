package application;
/** Scoreboard class updates the scoeboard 2.5 times a second  **/
public class Scoreboard implements Runnable {

	private Bank bank;
	private boolean running = true;
	/** Constructor demands a reference to the "controller" **/ //which allows the class to work with other classes
	public Scoreboard(Bank bank) {
		this.bank = bank;
	}

	@Override
	public void run() {
		try {
			while (running) {
				if (!(bank.getOpponents().getOpponentsList() == null)) {
				String temp = "";
				temp += bank.getPlayer().getUsername() + ": " + bank.getPlayer().getScore() + "\n";
					try {
						for (int i = 0; i < bank.getOpponents().getOpponentsList().size(); i++) {
							temp += bank.getOpponents().getOpponentsList().get(i).getUsername() + ": "
									+ bank.getOpponents().getOpponentsList().get(i).getScore() + "\n";
						}
					} catch (Exception e) {
						
					}
					bank.getGui().getScore().setText(temp);
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
