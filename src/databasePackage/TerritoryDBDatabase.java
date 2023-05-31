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

import RiskPackage.GameController;
import RiskPackage.Player;
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

	public void save(Player player) throws IOException {
		
		String username = String.valueOf(player.getId());
		
		for (int i=0;i<player.getTerritories().size();i++) {
			Document doc = new Document();
			//Bson filter = eq("username", username);
	        //collection.deleteMany(filter);
			
			ArrayList<String> saveList = player.getTerritories().get(i).getList();
			
			
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
		}
		
		System.out.println("Game saved to the database for " +username);
	}

	public ArrayList<ArrayList<String>> load(Player player) throws IOException {
		
		this.prepare();
		
		String username = String.valueOf(player.getId());
		//System.out.println(this.collection);
		ArrayList<Document> documents = this.collection.find(eq("username", username)).into(new ArrayList<>());


		if(documents==null) {return null;}
		
		ArrayList<ArrayList<String>> informations = new ArrayList<ArrayList<String>>();
		
		for (Document my_doc : documents) {
			ArrayList<String> loadList = new ArrayList<String>();
			
			loadList.add(my_doc.get("xCoordinate").toString());
			loadList.add(my_doc.get("yCoordinate").toString());
			loadList.add(my_doc.get("width").toString());
			loadList.add(my_doc.get("height").toString());
			loadList.add(my_doc.get("name").toString());
			loadList.add(my_doc.get("color").toString());
			loadList.add(my_doc.get("continentName").toString());
			loadList.add(my_doc.get("playerID").toString());
			informations.add(loadList);
			
		}
		

		System.out.println("Game successfully loaded from the database for "+username);
		return informations;
	}
	
	public ArrayList<ArrayList<ArrayList<String>>> loadAll() throws IOException{
		
		this.prepare();
		ArrayList<ArrayList<ArrayList<String>>> allArrayList = new ArrayList<>();
		
		String[] usernames = {"0","1","2","3","4","5"};
		
		for (String username : usernames) {
			ArrayList<Document> documents = this.collection.find(eq("username", username)).into(new ArrayList<>());


			if(documents==null) {return null;}
			
			ArrayList<ArrayList<String>> informations = new ArrayList<ArrayList<String>>();
			
			for (Document my_doc : documents) {
				ArrayList<String> loadList = new ArrayList<String>();
				
				loadList.add(my_doc.get("xCoordinate").toString());
				loadList.add(my_doc.get("yCoordinate").toString());
				loadList.add(my_doc.get("width").toString());
				loadList.add(my_doc.get("height").toString());
				loadList.add(my_doc.get("name").toString());
				loadList.add(my_doc.get("color").toString());
				loadList.add(my_doc.get("continentName").toString());
				loadList.add(my_doc.get("playerID").toString());
				informations.add(loadList);
				
			}
			if (informations.size()!=0){
				allArrayList.add(informations);
			}
			
		}
		return allArrayList;
		
		
	}
	

	public void saveAll() throws IOException {
		this.prepare();
		ArrayList<Player> players = GameController.getPlayers();
		for (Player player : players) {
			this.save(player);
		}
	}
	
	@Override
	public void empty() throws IOException{
		this.prepare();
        this.collection.deleteMany(new Document());
	}

	@Override
	public void save(Shape shape) throws IOException {
		// TODO Auto-generated method stub
		
	}

	


	




	

}
