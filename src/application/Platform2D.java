package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
/** Platform2D contains the platforms in the game which consists of 4 lines up, right, down, left **/
public class Platform2D {
    private List<Shape> lines = new ArrayList<Shape>();
    private double width = 300;
    private double height = 20;
    private double startX;
    private double startY;
    private Line north;
    private Line east;
    private Line south;
    private Line west;
    private ImageView iv1;

    /** Enter the startX and startY of the platform **/ //Connects all the Lines so it looks like one entity
    public Platform2D(double startX, double startY) {
        this.setStartX(startX);
        this.setStartY(startY);

        north = new Line(startX, startY, startX + width, startY);
        north.setStrokeWidth(2);

        east = new Line(north.getEndX(), north.getEndY(), north.getEndX(), north.getEndY() + height);
        east.setStrokeWidth(2);

        south = new Line(east.getEndX(), east.getEndY(), east.getEndX() - width, east.getEndY());
        south.setStrokeWidth(2);

        west = new Line(startX, startY, startX, startY + height);
        west.setStrokeWidth(2);
        //Texture for the platform
        Image image = new Image("Sprite-0007.png");
        iv1 = new ImageView();
        iv1.setImage(image);
        iv1.setX(startX);
        iv1.setY(startY);
        
        //Creates a line for the floor(Quick and dirty fix  *shhh*)
        Line floor = new Line(0, 675, 1024, 675);
        floor.setStroke(Color.TRANSPARENT);

        lines.addAll(Arrays.asList(north, west, south, east, floor));

    }

    /** Setter and Getters **/
    
    public List<Shape> getPlatform() {
        return lines;
    }

    public double getStartX() {
        return startX;
    }

    public void setStartX(double startX) {
        this.startX = startX;
    }

    public double getStartY() {
        return startY;
    }

    public void setStartY(double startY) {
        this.startY = startY;
    }

    public ImageView getImage() {
        return iv1;
    }
    
    /** ------------------------------------------------------------ **/

}

