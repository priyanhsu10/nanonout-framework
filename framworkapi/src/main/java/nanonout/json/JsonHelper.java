package nanonout.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class JsonHelper {
    public  static Gson gson ;
    static {
        gson = new GsonBuilder().create();

    }
    public  static String serialize(Object object){
      return   gson.toJson(object);
    }
    public  static <T> T deserialize(String data ,Class<T> aClass){

        // see you latter this condition
        if( aClass.isAssignableFrom(List.class)){

       }
        return   gson.fromJson(data,aClass);
    }
}
