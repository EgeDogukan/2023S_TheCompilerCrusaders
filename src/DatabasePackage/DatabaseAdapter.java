package DatabasePackage;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class DatabaseAdapter implements ImongodbInterface {
    private final String connectionString;
    private final String databaseName;
    private final String collectionName;

    public DatabaseAdapter(String connectionString, String databaseName, String collectionName) {
        this.connectionString = connectionString;
        this.databaseName = databaseName;
        this.collectionName = collectionName;
    }

    @Override
    public String getCreditentials() {
        MongoClient mongoClient = MongoClients.create(connectionString);
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        MongoCollection<Document> collection = database.getCollection(collectionName);

        // Perform your MongoDB operations here
        // For example, saving and retrieving credentials
        Document document = new Document("username", "John")
                .append("password", "pass123");
        collection.insertOne(document);

        Document retrievedDocument = collection.find().first();
        String username = retrievedDocument.getString("username");
        String password = retrievedDocument.getString("password");

        return "Username: " + username + ", Password: " + password;
    }
}
