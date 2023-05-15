package databasePackage;


import java.io.IOException;
import java.util.ArrayList;

public interface ISaveLoadAdapter {

	public void prepare();
	
	public void save(String username, String password) throws IOException;
	public void save(ArrayList<String> saveList, String username) throws IOException;
	
	public ArrayList<String> load(String username) throws IOException;
}