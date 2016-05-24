package application;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Login extends Application{


	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane pane = new BorderPane();
		Scene scene = new Scene(pane, 1024, 720);
		pane.setCenter(addGridPane());

		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public GridPane addGridPane() {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("Välkommen till OSOK!");
		grid.add(scenetitle, 0, 0, 2, 1);

		Label user = new Label("Username");
		grid.add(user, 0, 1);

		TextField userName = new TextField();
		grid.add(userName, 1, 1);

		Label ipAdress = new Label("IP-Adress:");
		grid.add(ipAdress, 0, 2);

		TextField ip = new TextField();
		grid.add(ip, 1, 2);

		Label portNummer = new Label("Port-Nummer:");
		grid.add(portNummer, 0, 3);

		TextField port = new TextField();
		grid.add(port, 1, 3);

		Button btn = new Button("Logga in!");

		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 1, 4);

		final Text actiontarget = new Text();
		grid.add(actiontarget, 0, 6);
		grid.setColumnSpan(actiontarget, 2);
		grid.setHalignment(actiontarget, HPos.RIGHT);
		actiontarget.setId("actiontarget");

		return grid;
	}
}
