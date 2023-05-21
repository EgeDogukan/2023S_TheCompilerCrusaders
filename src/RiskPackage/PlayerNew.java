package RiskPackage;

import java.awt.Color;
import java.util.ArrayList;

public class PlayerNew {

	int id;
	Color color;
	ArrayList<Integer>shapeIndices;
	
	public PlayerNew(int id, Color color, ArrayList<Integer> shapeIndices) {
		this.id=id;
		this.color=color;
		this.shapeIndices=shapeIndices;
	}
	
	
	
	
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public ArrayList<Integer> getShapeIndices() {
		return shapeIndices;
	}

	public void setShapeIndices(ArrayList<Integer> shapeIndices) {
		this.shapeIndices = shapeIndices;
	}
	

}
