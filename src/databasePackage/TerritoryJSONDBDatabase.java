package databasePackage;


import static com.mongodb.client.model.Filters.eq;

import java.awt.Shape;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.json.simple.JsonArray;
import org.json.simple.JsonObject;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import RiskPackage.GameController;
import RiskPackage.Player;
import RiskPackage.Territory;
import uipackage.BuildingModeNew;
import uipackage.RunningModeNew;
import uipackage.WorldMap;


public class TerritoryJSONDBDatabase implements ISaveLoadAdapter {
	

	public String filePath;

	public TerritoryJSONDBDatabase() {
	}
	
	@Override
	public void prepare() {
		this.filePath="data.json";
		
	}

	
	public void saveAll() throws IOException {
		this.prepare();
		ArrayList<Shape> shapes = BuildingModeNew.getWorldMap().getShapeList();
		for (Shape shape : shapes) {
			System.out.println(shape);
			save(shape);
		}
	}
	
	public void save(Shape shape) throws IOException {
		Gson gson = new Gson();
		FileWriter writer = new FileWriter(filePath, true);
		
		WorldMap worldMap = BuildingModeNew.getWorldMap();
		
		int index = worldMap.getShapeIndex(shape);
		
		
		int r = worldMap.getColorList().get(index).getRed();
		int g = worldMap.getColorList().get(index).getGreen();
		int b = worldMap.getColorList().get(index).getBlue();
		
		int[] infos = {index, r, g, b};
		
		String json = gson.toJson(infos);
		writer.write(json);
		writer.write("\n");
		writer.close();	
	}

	
	

	public void empty() throws IOException {
		String filePath="data.json";
		Gson gson = new Gson();
		
		FileWriter writer = new FileWriter(filePath);
			
		String json = gson.toJson("");
		writer.write(json);
	}

	
	
	public ArrayList<ArrayList<String>> load() throws FileNotFoundException, IOException {
		this.prepare();
		Gson gson = new Gson();
		
		ArrayList<ArrayList<String>> informations = new ArrayList<>();

	    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            Type listType = new TypeToken<ArrayList<String>>() {}.getType();
	            ArrayList<String> data = gson.fromJson(line, listType);
	            informations.add(data);
	           
	        }
	    }
	    return informations;
	}

	@Override
	public ArrayList<ArrayList<ArrayList<String>>> loadAll() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}


	
	
	





	






	
		
	

}
