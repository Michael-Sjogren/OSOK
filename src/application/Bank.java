package application;

import java.util.ArrayList;
import java.util.List;

public class Bank {

	private Player player;
	private Opponents opponents;
	private Move move;
	private KeyInput keyInput;
	private Gravity gravity;
	private Collision collision;
	private Game game;
	private Thread thread;
	private ClientGui gui;
	private Login login;
	private Bullet bullet;

	private List<Platform2D> platforms = new ArrayList<Platform2D>();

	public Bank(Login login) {
		this.login = login;
		player = new Player();
		bullet = new Bullet(this);
		opponents = new Opponents(this);
		move = new Move(this);
		gravity = new Gravity(this);
		collision = new Collision(this);
		game = new Game(this);
		thread = new Thread(game);
		gui = new ClientGui(this);
		keyInput = new KeyInput(this);
		thread.start();
		
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Move getMove() {
		return move;
	}

	public void setMove(Move move) {
		this.move = move;
	}

	public KeyInput getKeyInput() {
		return keyInput;
	}

	public void setKeyInput(KeyInput keyInput) {
		this.keyInput = keyInput;
	}

	public Gravity getGravity() {
		return gravity;
	}

	public void setGravity(Gravity gravity) {
		this.gravity = gravity;
	}

	public Collision getCollision() {
		return collision;
	}

	public void setCollision(Collision collision) {
		this.collision = collision;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public List<Platform2D> getPlatforms() {
		return platforms;
	}

	public void setPlatforms(List<Platform2D> platforms) {
		this.platforms = platforms;
	}
	public ClientGui getGui() {
		return gui;
	}

	public void setGui(ClientGui gui) {
		this.gui = gui;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public Opponents getOpponents() {
		return opponents;
	}

	public Bullet getBullet() {
		return bullet;
	}

	public void setBullet(Bullet bullet) {
		this.bullet = bullet;
	}

	public void setOpponents(Opponents opponents) {
		this.opponents = opponents;
	}
}
