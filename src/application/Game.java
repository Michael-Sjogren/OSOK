package application;

import javafx.scene.shape.Line;

public class Game implements Runnable {
	
	Bank bank;
	long lastTime = System.nanoTime();
	double amountOfTicks = 60.0;
	double ns = 1000000000 / amountOfTicks;
	double delta = 0;
	long timer = System.currentTimeMillis();
	int updates = 0;
	int frames = 0;
	boolean running = true;

	public Game(Bank bank) {
		this.bank=bank;
	}

	public void run() {
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}

	}
	

	private void render() {

	}

	private void tick() {
		bank.getCollision().checkCollision();
		bank.getGravity().fall();
		bank.getMove().movePlayer();
		bank.getOpponents().updateOpponents();
		bank.getBullet().moveBullet();
		
	}
	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
	
}
