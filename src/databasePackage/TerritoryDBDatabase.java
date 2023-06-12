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
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import uipackage.BuildingModeNew;
import uipackage.WorldMap;


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
		return;
	}

	@Override
	public ArrayList<ArrayList<Integer>> loadAll() throws IOException {
		this.prepare();
		ArrayList<ArrayList<Integer>> informations = new ArrayList<>();
		
		MongoCursor<Document> cursor = collection.find().iterator();
        while (cursor.hasNext()) {
            Document document = cursor.next();
            ArrayList<Integer> currentInformations = new ArrayList<>();
            currentInformations.add(Integer.parseInt(document.get("index").toString()));
            currentInformations.add(Integer.parseInt(document.get("r").toString()));
            currentInformations.add(Integer.parseInt(document.get("g").toString()));
            currentInformations.add(Integer.parseInt(document.get("b").toString()));
            currentInformations.add(Integer.parseInt(document.get("a").toString()));
            currentInformations.add(Integer.parseInt(document.get("c").toString()));
            currentInformations.add(Integer.parseInt(document.get("i").toString()));
            informations.add(currentInformations);
            
        }
		
		return informations;
		
	}
	
	@Override
	public void empty() throws IOException{
		this.prepare();
        this.collection.deleteMany(new Document());
	}
	
	@Override
	public void saveAll() throws IOException {
		this.prepare();
		
		WorldMap worldMap = BuildingModeNew.getWorldMap();
		ArrayList<Shape> shapes = worldMap.getShapeList();
		
		for (Shape shape : shapes) {
			Document doc = new Document();
			doc.append("index", worldMap.getShapeIndex(shape));
			int index = worldMap.getShapeIndex(shape);
			doc.append("r", worldMap.getColorList().get(index).getRed());
			doc.append("g", worldMap.getColorList().get(index).getGreen());
			doc.append("b", worldMap.getColorList().get(index).getBlue());
			doc.append("a", worldMap.shapeDomain.getShapeArmyArtillery(worldMap.getShapeIndex(shape)));
			doc.append("c", worldMap.shapeDomain.getShapeArmyCavalry(worldMap.getShapeIndex(shape)));
			doc.append("i", worldMap.shapeDomain.getShapeArmyInfantry(worldMap.getShapeIndex(shape)));
			collection.insertOne(doc);
		}
	}

	

	


	




	

}
