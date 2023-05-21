package databasePackage;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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
import com.google.gson.stream.JsonReader;

import RiskPackage.GameController;
import RiskPackage.Player;
import RiskPackage.Territory;


public class TerritoryJSONDBDatabase implements ISaveLoadAdapter {
	

	public String filePath;

	public TerritoryJSONDBDatabase() {
	}
	
	
	
	public static void savePlayer(Player player) throws IOException {
		
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
			savePlayer(player);
		}
	}
	
	public void load(String filePath) {
		this.prepare();
		Gson gson = new Gson();
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                JsonObject jsonObject = gson.fromJson(line, JsonObject.class);
                System.out.println(jsonObject.get("nationality"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	
	
	public void loadAll() {
		this.prepare();
		Gson gson = new Gson();
		String filePath="data.json";
		
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                JsonObject jsonObject = gson.fromJson(line, JsonObject.class);
                System.out.println(jsonObject);
            }
        } catch (IOException e) {
            e.printStackTrace();
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

	@Override
	public void save(String username, String password) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(ArrayList<String> saveList, String username) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Player player) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<ArrayList<String>> load(Player player) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
		
	

}
