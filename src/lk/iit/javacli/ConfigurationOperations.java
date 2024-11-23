package lk.iit.javacli;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigurationOperations {

    public static void saveConfigurationToFile(Configuration config, String filePath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(config, writer); // Serialize the object to JSON
            System.out.println("Configuration saved to " + filePath);
        } catch (IOException e) {
            System.err.println("Failed to save configuration: " + e.getMessage());
        }
    }

    public static Configuration loadConfigurationFromFile(String filePath) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, Configuration.class); // Deserialize JSON to Configuration object
        } catch (IOException e) {
            System.err.println("Failed to load configuration: " + e.getMessage());
            return null;
        }
    }
}
