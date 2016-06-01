package application;
/** Game class runs at 60hz and it's the main thread on the client side, it is the game-engine **/
public class Game implements Runnable {
	
	Controller con;
	long lastTime = System.nanoTime();
	double amountOfTicks = 60.0;
	double ns = 1000000000 / amountOfTicks;
	double delta = 0;
	long timer = System.currentTimeMillis();
	int updates = 0;
	int frames = 0;
	boolean running = true;

	/** Constructor demands a reference to the "controller" **/ //which allows the class to work with other classes
	public Game(Controller con) {
		this.con=con;
	}
	//Calls the tick-method at 60 times/sec
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
	/** Contain the calls which are reqiured for game updates and logic **/
	private void tick() {
		con.getCollision().checkCollision();
		con.getGravity().fall();
		con.getMove().movePlayer();
		con.getOpponents().updateOpponents();
		con.getBullet().moveBullet();
		
	}
	
	//Setters and Getters
	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
	
}
