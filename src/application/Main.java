package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application implements Runnable {

	boolean running = true;
	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println("Hello World leo suger KNOT");
		Main main = new Main();
		Thread thread = new Thread(main);
		thread.start();
	

	}

	public void run() {
		while (running) {
			try {
				Thread.sleep(17);
				System.out.println("Running");
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}

	}

}
