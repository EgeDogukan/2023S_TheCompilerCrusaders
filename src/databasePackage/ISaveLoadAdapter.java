package databasePackage;


import java.awt.Shape;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public interface ISaveLoadAdapter {

	public void prepare();
	void save(Shape shape) throws IOException;
	public ArrayList<ArrayList<Integer>> loadAll() throws IOException;
	public void empty() throws IOException;
	public void saveAll() throws IOException;
}