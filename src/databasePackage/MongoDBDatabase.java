package databasePackage;


import static com.mongodb.client.model.Filters.eq;

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

import RiskPackage.Player;
public class MongoDBDatabase implements ISaveLoadAdapter {

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

	public void save(String username, String password) throws IOException{

		Document doc = new Document();
		Bson filter = eq("username", username);
		collection.deleteMany(filter);
		doc.append("username", username);
		doc.append("password", password);
		
		collection.insertOne(doc);
		System.out.println("Game saved to the database for " +username);
		
	}

	public ArrayList<String> load(String username) throws IOException {
		Document my_doc = collection.find(eq("username", username)).first();
		if(my_doc==null) {
			return null;
		}
		
		System.out.println("Game successfully loaded from the database for "+username);
		ArrayList<String> informations = new ArrayList<String>();
		informations.add(my_doc.getString("password").toString());

		
		return informations;
	}

	public void save(ArrayList<String> saveList, String username) throws IOException {
		return;
		
	}
	
	
	public static void main(String[] args) throws IOException {
		MongoDBDatabase database =  new MongoDBDatabase();
		database.prepare();
		database.save("Halil", "1234");
	}

	@Override
	public ArrayList<ArrayList<String>> load(Player player) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Player player) throws IOException {
		// TODO Auto-generated method stub
		
	}

}