package Utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.Reader;

public class JsonIO {

    public static Object readSimple(String fileName, String key){
        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader(fileName)){
            JSONObject obj = (JSONObject) parser.parse(reader);
            return obj.get(key);
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
