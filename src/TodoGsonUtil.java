import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class TodoGsonUtil {
    private static String filename = "todo.json";

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static Map<Integer ,TodoItem> readTodos(){
        try(FileReader reader = new FileReader(filename)){
            Type listType = new TypeToken<HashMap<Integer , TodoItem>>(){}.getType();
            Map<Integer , TodoItem> tasks = gson.fromJson(reader , listType);
            return (tasks != null) ? tasks : new HashMap<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new HashMap<>();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
            return new HashMap<>();
        }
    }

    public static void writeTodos(Map<Integer , TodoItem> tasks){
        try(FileWriter writer = new FileWriter(filename)){
            gson.toJson(tasks , writer);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
