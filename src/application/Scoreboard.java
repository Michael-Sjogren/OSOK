package application;

public class Scoreboard implements Runnable {

	private Bank bank;
	private boolean running = true;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void stopThread(){
		running = false;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

}
