package databasePackage;


import static com.mongodb.client.model.Filters.eq;

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


public class TerritoryJSONDBDatabase implements ISaveLoadAdapter {
	

	public String filePath;

	public TerritoryJSONDBDatabase() {
	}
	
	
	public void save(Player player) throws IOException {
		this.prepare();
		String filePath="data.json";
		Gson gson = new Gson();
		
		
		for (int i=0;i<player.getTerritories().size();i++) {
			FileWriter writer = new FileWriter(filePath, true);
			Territory ter = player.getTerritories().get(i);
			ArrayList<String> saveList = ter.getList();
			
			System.out.println("player.getTerritories().size()"+player.getTerritories().size());
			
			String json = gson.toJson(saveList);
			writer.write(json);

			writer.write("\n");
			writer.close();		
		}
		
	}
	
	public void saveAll() throws IOException {
		this.prepare();
		ArrayList<Player> players = GameController.getPlayers();
		for (Player player : players) {
			save(player);
		}
	}

	public void empty() throws IOException {
		String filePath="data.json";
		Gson gson = new Gson();
		
		FileWriter writer = new FileWriter(filePath);
			
		String json = gson.toJson("");
		writer.write(json);
	}

	@Override
	public void prepare() {
		this.filePath="data.json";
	}



	public ArrayList<ArrayList<ArrayList<String>>> loadAll() throws IOException {
		this.prepare();
		
		ArrayList<ArrayList<ArrayList<String>>> allArrayList = new ArrayList<>();
		Gson gson = new Gson();
		
		String[] usernames = {"0","1","2","3","4","5"};
		
		for (String username : usernames) {
			try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
		        String line;
		        ArrayList<ArrayList<String>> informations = new ArrayList<ArrayList<String>>();
		        while ((line = reader.readLine()) != null) {
		            Type listType = new TypeToken<ArrayList<String>>() {}.getType();
		            ArrayList<String> data = gson.fromJson(line, listType);
		            
		            if (data.get(data.size()-1).equals(username)) {
		            	informations.add(data);
		            }
		           
		        }
		        if (informations.size()!=0) {
			        allArrayList.add(informations);
		        }
		    }
			
		}
		return allArrayList;
	}
	
	





	






	
		
	

}
