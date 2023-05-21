package databasePackage;


import java.io.IOException;
import java.util.ArrayList;

import RiskPackage.Player;

public interface ISaveLoadAdapter {

	public void prepare();
	
	public void save(String username, String password) throws IOException;
	public void save(ArrayList<String> saveList, String username) throws IOException;
	void save(Player player) throws IOException;
	
	public ArrayList<ArrayList<String>> load(Player player) throws IOException;

	public void empty() throws IOException;

	public void saveAll() throws IOException;
}