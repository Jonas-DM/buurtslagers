package be.sitewish.buurtslagers.domain;

import android.content.Context;
import android.net.ConnectivityManager;
import org.json.JSONArray;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Controller implements AsyncBroodjes{

    //region Properties
    public static String URL = "https://api.sitewish.be/";
    public static String KEY = "8e6c7b47e63f664c7c66d84fcf0588202d37c3939e9fd65bf7ad6d9df52d4c188bd9c745";

    private Klant klant;
    private ArrayList<Broodje> broodjes = new ArrayList<>();

    public ArrayList<Broodje> getBroodjes() {
        return broodjes;
    }

    public void setBroodjes(ArrayList<Broodje> broodjes) {
        this.broodjes = broodjes;
    }

    public Klant getKlant() {
        return klant;
    }

    public void setKlant(Klant klant) {
        this.klant = klant;
    }
    //endregion

    //controleren als men is verbonden met een netwerk
    public static boolean NetworkAvailable(Context context){
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    //effectieve internetconnectie controleren
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

    //controlern als api response een JSONArray is of niet.
    public static boolean IsJSONArray(String s){
        try{
            JSONArray arr = new JSONArray(s);
            return  true;
        }
        catch (Exception e){
            return false;
        }
    }

    public void GetBroodjes(){
        HashMap map = new HashMap();
        map.put("api_key", KEY);
        map.put("action", "GET");

        BroodjesRequest broodjesRequest = new BroodjesRequest(URL + "/broodje", map, this);
        broodjesRequest.execute();
    }

    @Override
    public void returnBroodjes(JSONArray output) {
        setBroodjes(Broodje.fromJSON(output));
        //ArrayList<Broodje> broodje = Broodje.fromJSON(output);
        //System.out.println("DEBUG " + broodje.get(0).getNaam());
    }
}
