package be.sitewish.buurtslagers.domain;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Broodje implements Parcelable {
    private int id;
    private String naam;
    private String afbeelding;
    private BigDecimal prijs;

    public Broodje() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getAfbeelding() {
        return afbeelding;
    }

    public void setAfbeelding(String afbeelding) {
        this.afbeelding = afbeelding;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    public void setPrijs(BigDecimal prijs) {
        this.prijs = prijs;
    }

    public static Broodje fromJson(JSONObject obj){
        try{
            Broodje b = new Broodje();

            b.id = obj.getInt("id");
            b.naam = obj.getString("Naam");
            b.prijs = BigDecimal.valueOf(obj.getDouble("Prijs"));

            return b;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Broodje> fromJSON(JSONArray arr){
        ArrayList<Broodje> b = new ArrayList<>(arr.length());

        try{
            for(int i = 0; i < arr.length(); i++){
                JSONObject obj = arr.getJSONObject(i);

                Broodje broodje = Broodje.fromJson(obj);
                if(broodje != null){
                    b.add(broodje);
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }

        return b;
    }

    protected Broodje(Parcel in) {
        id = in.readInt();
        naam = in.readString();
        afbeelding = in.readString();
        prijs = (BigDecimal) in.readValue(BigDecimal.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(naam);
        dest.writeString(afbeelding);
        dest.writeValue(prijs);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Broodje> CREATOR = new Parcelable.Creator<Broodje>() {
        @Override
        public Broodje createFromParcel(Parcel in) {
            return new Broodje(in);
        }

        @Override
        public Broodje[] newArray(int size) {
            return new Broodje[size];
        }
    };
}
