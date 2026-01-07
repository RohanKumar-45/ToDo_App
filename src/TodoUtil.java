import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TodoUtil {
    private static String filename = "todo.json";

    public static JSONArray readTodoArray(){
        try{
            JSONParser parser = new JSONParser();
            return (JSONArray) parser.parse(new FileReader(filename));
        }catch (Exception e){
            return new JSONArray();
        }
    }

    public static void writeTodoArray(JSONArray array){
        try(FileWriter writer = new FileWriter(filename)){
            writer.write(array.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
