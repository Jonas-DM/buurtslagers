package be.sitewish.buurtslagers.domain;

import android.os.AsyncTask;
import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.ResourceBundle;

public class BroodjesRequest extends AsyncTask<Void, Void, String> {

    String url;

    HashMap map;

    public AsyncBroodjes delgate = null;

    public BroodjesRequest(String url, HashMap map, AsyncBroodjes delgate) {
        this.url = url;
        this.map = map;
        this.delgate = delgate;
    }

    @Override
    protected String doInBackground(Void... voids) {
        RequestHandler requestHandler = new RequestHandler();

        return requestHandler.sendPostRequest(url, map);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        JSONArray arr = null;
        JSONObject object = null;

        try {
            if(Controller.IsJSONArray(s)){
                arr = new JSONArray(s);
            }
            else{
                object = new JSONObject(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(arr != null){
            System.out.println("DEBUG arr is niet null");
            try {
                delgate.returnBroodjes(arr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
