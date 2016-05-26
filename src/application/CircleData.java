package application;

import javafx.scene.shape.Circle;

import java.io.Serializable;

/**
 * Created by Michael Sjögren on 2016-05-26.
 */
public class CircleData implements Serializable{

    private Circle circle;

    CircleData(Circle circle){

        this.circle = circle;
    }


}
