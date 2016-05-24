package application;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class KeyInput{
	
	private Stage stage;
	
	public KeyInput(Stage stage,Move move){
		
		stage.addEventHandler(KeyEvent.KEY_PRESSED, ep->{
			if(ep.getCode()==KeyCode.LEFT){
				move.moveLeft();
				}
		});
		stage.addEventHandler(KeyEvent.KEY_PRESSED, ep->{
			if(ep.getCode()==KeyCode.RIGHT){
				move.moveRight();
			}
			
		});
		
		stage.addEventHandler(KeyEvent.KEY_RELEASED, er->{
			if(er.getCode()==KeyCode.RIGHT){
				move.resetVelRight();
			}
		});
		
		stage.addEventHandler(KeyEvent.KEY_RELEASED, er->{
			if(er.getCode()==KeyCode.LEFT){
				move.resetVelLeft();
				}
		});
		
		stage.addEventHandler(KeyEvent.KEY_PRESSED, ej->{
			if(ej.getCode()==KeyCode.UP){
				move.jump();
			}
		});
		
		
		
		this.stage = stage;	
	}
	
}
