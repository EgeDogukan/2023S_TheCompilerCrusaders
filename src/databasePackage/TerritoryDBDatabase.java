package databasePackage;

import static com.mongodb.client.model.Filters.eq;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class TerritoryDBDatabase implements ISaveLoadAdapter {

	Logger mongoLogger;
	MongoClient mongoClient;
	MongoDatabase database;
	MongoCollection<Document> collection;
	
	public void prepare() {
		mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE); // e.g. or Log.WARNING, etc.	
		mongoClient = MongoClients.create("mongodb+srv://compilercrusader:comp123comp@compilercrusader.pw4xqlq.mongodb.net/"); // uri connection to the server
		database = mongoClient.getDatabase("users"); // selecting the database 
		collection = database.getCollection("usersCreditentials"); // collection 
		
	}

	public void save(ArrayList<String> saveList, String username) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<String> load(String username) throws IOException {
		Document my_doc = collection.find(eq("username", username)).first();
		if(my_doc==null) {return null;}
		
		ArrayList<String> loadList = new ArrayList<String>();
		
		
		return null;
	}

	
	
	
	
	
	
	
	public void save(String username, String password) throws IOException {
		return;
		
	}


	

}
