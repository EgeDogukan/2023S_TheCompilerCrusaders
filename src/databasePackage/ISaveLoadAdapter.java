package databasePackage;


import java.io.IOException;
import java.util.ArrayList;

public interface ISaveLoadAdapter {

	public void prepare();
	public void save(String username, String password) throws IOException;
	public String load(String username) throws IOException;
}