package databasePackage;

import static com.mongodb.client.model.Filters.eq;

import java.awt.Color;
import java.awt.Shape;
import java.io.FileNotFoundException;
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


public class TerritoryDBDatabase implements ISaveLoadAdapter {

	Logger mongoLogger;
	MongoClient mongoClient;
	MongoDatabase database;
	MongoCollection<Document> collection;
	
	@Override
	public void prepare() {
		mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE); // e.g. or Log.WARNING, etc.	
		mongoClient = MongoClients.create("mongodb+srv://compilercrusader:comp123comp@compilercrusader.pw4xqlq.mongodb.net/"); // uri connection to the server
		database = mongoClient.getDatabase("users"); // selecting the database 
		collection = database.getCollection("territories"); // collection 
		
	}

	@Override
	public void save(Shape shape) throws IOException {
		
	}

	@Override
	public int[] loadAll() throws IOException {
		return null;
	}

	@Override
	public void empty() throws IOException {
		
	}

	@Override
	public void saveAll() throws IOException {
		
	}

	

	


	




	

}
