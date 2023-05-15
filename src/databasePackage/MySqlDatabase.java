package databasePackage;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MySqlDatabase {
    private MongoClient client;
    private MongoDatabase database;
    private MongoCollection<Document> collection;
    

    public MySqlDatabase(String connectionString, String databaseName, String collectionName) {
        // Create a new MongoDB client
        client = new MongoClient(new MongoClientURI(connectionString));

        // Connect to the database
        database = client.getDatabase(databaseName);

        // Get the users collection
        collection = database.getCollection(collectionName);
    }

    public void registerUser(String username, String password) {
        // Check if the user already exists
        Document existingUser = collection.find(new Document("username", username)).first();

        if (existingUser != null) {
            System.out.println("Username already exists!");
            return;
        }

        // Insert the new user into the collection
        Document newUser = new Document("username", username)
                .append("password", password);
        collection.insertOne(newUser);

        System.out.println("User registered successfully!");
    }

    public void authenticateUser(String username, String password) {
        // Find the user with the provided username and password
        Document user = collection.find(new Document("username", username)
                .append("password", password)).first();

        if (user != null) {
            System.out.println("User authenticated successfully!");
        } else {
            System.out.println("Invalid username or password!");
        }
    }

    public void disconnect() {
        // Close the MongoDB client
        client.close();
    }

    public static void main(String[] args) {
        String connectionString = "mongodb://localhost:27017";
        String databaseName = "userdb";
        String collectionName = "users";

        MySqlDatabase userDB = new MySqlDatabase(connectionString, databaseName, collectionName);
        userDB.registerUser("john.doe", "password123");
        userDB.authenticateUser("john.doe", "password123");
        userDB.disconnect();
    }
}

	

