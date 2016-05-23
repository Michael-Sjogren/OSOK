package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.shape.*;
import javafx.scene.shape.Shape;

public class Platform2D{
	private List<Shape> lines = new ArrayList<Shape>();
	private double width = 300;
	private double height = 20;
	private double startX;
	private double startY;
	private Line north;
	private Line east;
	private Line south;
	private Line west;

	/** Enter the startX and startY of the platform **/
	public Platform2D(double startX, double startY) {
		this.startX = startX;
		this.startY = startY;

		north = new Line(startX, startY, startX + width, startY);

		east = new Line(north.getEndX(), north.getEndY(), north.getEndX(), north.getEndY() + height);

		south = new Line(east.getEndX(), east.getEndY(), east.getEndX() - width, east.getEndY());

		west = new Line(startX, startY, startX, startY + height);

		lines.addAll(Arrays.asList(north, west, south, east));

	}

	public List<Shape> getPlatform() {
		return lines;
	}

}
