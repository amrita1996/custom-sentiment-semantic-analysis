import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoDBConnector {

    public static final String DATABASE = "news";
    private MongoDatabase mongoDatabase;

    public MongoDBConnector() {
        MongoClient mongoClient = getMongoClient();
        mongoDatabase = mongoClient.getDatabase(DATABASE);

    }

    private MongoClient getMongoClient() {
        ConnectionString connectionString = new ConnectionString("mongodb+srv://dbUser:PASSWORD123@cluster0.4yq2y.mongodb.net/?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        return MongoClients.create(settings);
    }

    public List<Article> getFromMongo() {
        List<Article> documents = new ArrayList<>();
        MongoCollection<Document> collection = mongoDatabase.getCollection("newsCollection");
        final MongoCursor<Document> findIterable = collection.find().iterator();
        while (findIterable.hasNext()) {
            final Document document = findIterable.next();
            final String content = document.get("content", String.class);
            final String title = document.get("title", String.class);
            final String description = document.get("description", String.class);
            documents.add(new Article(title, description, content));
        }
        return documents;
    }
}
