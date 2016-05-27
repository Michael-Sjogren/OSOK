package application;

import java.util.ArrayList;
import java.util.List;

public class Bank {

	Player player;
	Opponents opponents;
	Move move;
	KeyInput keyInput;
	Gravity gravity;
	Collision collision;
	Game game;
	Thread thread;
	ClientGui gui;
	Login login;

	private List<Platform2D> platforms = new ArrayList<Platform2D>();

	public Bank(Login login) {
		this.login = login;
		player = new Player();
		opponents = new Opponents(this);
		move = new Move(this);
		keyInput = new KeyInput(this);
		gravity = new Gravity(this);
		collision = new Collision(this);
		game = new Game(this);
		thread = new Thread(game);
		gui = new ClientGui(this);
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

	public void setOpponents(Opponents opponents) {
		this.opponents = opponents;
	}
}
