package be.sitewish.buurtslagers.domain;

import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Broodje {
    private int id;
    private String naam;
    private String afbeelding;
    private BigDecimal prijs;

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
}
