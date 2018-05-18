package be.sitewish.buurtslagers.domain;

import android.content.Context;
import android.net.ConnectivityManager;
import org.json.JSONArray;

import java.net.InetAddress;

public class Controller {
    public static boolean NetworkAvailable(Context context){
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public static boolean InternetAvailable(){
        try{
            InetAddress ipAddr = InetAddress.getByName("www.google.com");
            return !ipAddr.equals("");
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public static boolean IsJSONArray(String s){
        try{
            JSONArray arr = new JSONArray(s);
            return  true;
        }
        catch (Exception e){
            return false;
        }
    }
}
