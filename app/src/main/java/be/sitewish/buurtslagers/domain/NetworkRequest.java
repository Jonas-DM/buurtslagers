package be.sitewish.buurtslagers.domain;

import android.os.AsyncTask;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class NetworkRequest extends AsyncTask<Void, Void, String> {

    //url voor request
    String url = "";
    String wachtwoord;

    //parameters voor request
    HashMap<String, String> params;

    public AsyncResponse delgate = null;

    public NetworkRequest(String url, HashMap<String, String> params, String wachtwoord,AsyncResponse delgate) {
        this.url = url;
        this.params = params;
        this.delgate = delgate;
        this.wachtwoord = wachtwoord;
    }

    @Override
    protected String doInBackground(Void... voids) {
        RequestHandler request = new RequestHandler();

        return request.sendPostRequest(url, params);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        JSONObject object = null;
        JSONArray arr = null;

        boolean notArray = true;
        try{
//            System.out.println("RESPONSE " + s);
//            JSONArray jArray = new JSONArray(s);
//            JSONObject object = jArray.getJSONObject(0);
//
//            System.out.println("RESPONSE " + object.toString());
//            System.out.println("RESPONSE " + object.getString("inlognaam"));
//
//            delgate.processFinish(object);

            object = new JSONObject(s);

            if(object.getInt("status") == 400){
                delgate.processFinish(-1);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            notArray = false;
        }

        if(!notArray){
            try{
                arr = new JSONArray(s);
                object = arr.getJSONObject(0);

                if(object.getString("Wachtwoord").equals(wachtwoord)){
                    delgate.processFinish(0);
                }
                else{
                    delgate.processFinish(-2);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
