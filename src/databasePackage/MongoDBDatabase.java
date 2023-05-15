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
public class MongoDBDatabase implements ISaveLoadAdapter {

	Logger mongoLogger;
	MongoClient mongoClient;
	MongoDatabase database;
	MongoCollection<Document> collection;
	@Override
	public void prepare() {
		mongoLogger = Logger.getLogger("org.mongodb.driver");
		System.out.println("selamm");
		mongoLogger.setLevel(Level.SEVERE); // e.g. or Log.WARNING, etc.	
		mongoClient = MongoClients.create("mongodb+srv://compilercrusader:comp123comp@compilercrusader.pw4xqlq.mongodb.net/"); // uri connection to the server
		database = mongoClient.getDatabase("users"); // selecting the database 
		collection = database.getCollection("usersCreditentials"); // collection 	
	}

	public void save(String username, String password) throws IOException{

		Document doc = new Document();
		Bson filter = eq("username", username);
		System.out.println("selam");
		collection.deleteMany(filter);
		doc.append("username", username);
		doc.append("password", password);
		
		collection.insertOne(doc);
		System.out.println("Game saved to the database for " +username);
		
	}

	public String load(String username) throws IOException {
		
		Document my_doc = collection.find(eq("username", username)).first();
		if(my_doc==null) {
			return null;
		}
		System.out.println("Game successfully loaded from the database for "+username);
		return my_doc.getString("password").toString();

		
	}
	
	public static void main(String[] args) throws IOException {
		MongoDBDatabase database =  new MongoDBDatabase();
		database.prepare();
		database.save("Kerem", "123");
	}

}