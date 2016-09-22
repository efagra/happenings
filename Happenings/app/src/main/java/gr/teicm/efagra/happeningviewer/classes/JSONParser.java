package gr.teicm.efagra.happeningviewer.classes;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by efagra on 7/2/16.
 */
public class JSONParser {

    public static JSONObject getJsonObject(String jsonString){
        JSONObject jObj = null;

        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(jsonString);
        } catch (JSONException e) {
            jObj = null;
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        return jObj;
    }

}
