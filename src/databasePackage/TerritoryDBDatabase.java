package databasePackage;

import static com.mongodb.client.model.Filters.eq;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import RiskPackage.Territory;

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
		collection = database.getCollection("territories"); // collection 
		
	}

	public void save(ArrayList<String> saveList, String username) throws IOException {
		
		Document doc = new Document();
		Bson filter = eq("username", username);
        collection.deleteMany(filter);
		doc.append("username", username);
		doc.append("xCoordinate", saveList.get(0));
		doc.append("yCoordinate", saveList.get(1));
		doc.append("width", saveList.get(2));
		doc.append("height", saveList.get(3));
		doc.append("name", saveList.get(4));
		doc.append("color", saveList.get(5));
		doc.append("continentName", saveList.get(6));
		doc.append("playerID", saveList.get(7));
		collection.insertOne(doc);
		
		System.out.println("Game saved to the database for " +username);
		
	}

	@Override
	public ArrayList<String> load(String username) throws IOException {
		
		Document my_doc = collection.find(eq("username", username)).first();
		if(my_doc==null) {return null;}
		
		ArrayList<String> loadList = new ArrayList<String>();
		
		loadList.add(my_doc.get("xCoordinate").toString());
		loadList.add(my_doc.get("yCoordinate").toString());
		loadList.add(my_doc.get("width").toString());
		loadList.add(my_doc.get("height").toString());
		loadList.add(my_doc.get("name").toString());
		loadList.add(my_doc.get("color").toString());
		loadList.add(my_doc.get("continentName").toString());
		loadList.add(my_doc.get("playerID").toString());
		
		System.out.println("Game successfully loaded from the database for "+username);
		return loadList;
	}

	

	public void save(String username, String password) throws IOException {
		return;
		
	}
	
	public static void main(String[] args) throws IOException {
		TerritoryDBDatabase database =  new TerritoryDBDatabase();
		database.prepare();
		database.save((new Territory(15, 25, 4, 8, "bursa", Color.blue, null, 1)).getList(), "Kerem");
		
	}


	

}
