package osok.network.client;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;




public class testMain extends Application{

    private static Client client;
    private static String ip = "127.0.0.1";
    private static int port = 55556;


    @Override
    public void start(Stage primaryStage) throws Exception {


        Group root = new Group();
        Label lbl = new Label("hello world");

        lbl.setTextFill(Color.GOLDENROD);
        lbl.setFont(Font.font(43));


        Circle circle = new Circle(30);
        circle.setFill(Color.BLACK);

        root.getChildren().addAll(lbl , circle);
        primaryStage.setScene(new Scene(root , 400 , 400));
        primaryStage.show();




        primaryStage.addEventHandler(KeyEvent.ANY, event -> {
            switch (event.getCode()) {
                case UP:    circle.setCenterY(circle.getCenterY() - 15); break;
                case RIGHT: circle.setCenterX(circle.getCenterX() + 15); break;
                case DOWN:  circle.setCenterY(circle.getCenterY() + 15); break;
                case LEFT:  circle.setCenterX(circle.getCenterX() - 15); break;
            }
        });

    }

    @Override
    public void stop(){
        client.shutdown();
        System.out.println("stage close");
    }

    public static void main(String[] args )
    {
        System.out.println("client started");
        launch(args);
    }




}
