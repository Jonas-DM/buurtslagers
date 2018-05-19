package be.sitewish.buurtslagers.domain;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONArray;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Controller extends Application implements Parcelable {

    //region Properties
    public static String URL = "https://api.sitewish.be/";
    public static String KEY = "8e6c7b47e63f664c7c66d84fcf0588202d37c3939e9fd65bf7ad6d9df52d4c188bd9c745";

    private Klant klant;
    private ArrayList<Broodje> broodjes = new ArrayList<>();
    private Winkelmandje winkelmandje = new Winkelmandje();

    public Winkelmandje getWinkelmandje() {
        return winkelmandje;
    }

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


    public Controller() {
    }

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

//    public void GetBroodjes(){
//        HashMap map = new HashMap();
//        map.put("api_key", KEY);
//        map.put("action", "GET");
//
//        BroodjesRequest broodjesRequest = new BroodjesRequest(URL + "/broodje", map, this);
//        broodjesRequest.execute();
//    }
//
//    @Override
//    public void returnBroodjes(JSONArray output) {
//        this.setBroodjes(BroodjeActivity.fromJSON(output));
//        this.i = 5;
//
//        //setBroodjes(BroodjeActivity.fromJSON(output));
//        //ArrayList<BroodjeActivity> broodje = BroodjeActivity.fromJSON(output);
//        //System.out.println("DEBUG " + broodje.get(0).getNaam());
//        System.out.println("DEBUG " + this.i);
//        System.out.println("DEBUG " + broodjes.get(0).toString());
//    }

    protected Controller(Parcel in) {
        klant = (Klant) in.readValue(Klant.class.getClassLoader());
        if (in.readByte() == 0x01) {
            broodjes = new ArrayList<Broodje>();
            in.readList(broodjes, Broodje.class.getClassLoader());
        } else {
            broodjes = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(klant);
        if (broodjes == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(broodjes);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Controller> CREATOR = new Parcelable.Creator<Controller>() {
        @Override
        public Controller createFromParcel(Parcel in) {
            return new Controller(in);
        }

        @Override
        public Controller[] newArray(int size) {
            return new Controller[size];
        }
    };
}
