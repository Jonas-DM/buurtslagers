package be.sitewish.buurtslagers.domain;

import android.widget.Toast;
import org.json.JSONObject;

public class Klant {
    private int id;
    private String inlogNaam;
    private String naam;
    private String voornaam;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInlogNaam() {
        return inlogNaam;
    }

    public void setInlogNaam(String inlogNaam) {
        this.inlogNaam = inlogNaam;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public Klant() {

    }

    public Klant(int id, String inlogNaam, String naam, String voornaam) {
        this.id = id;
        this.inlogNaam = inlogNaam;
        this.naam = naam;
        this.voornaam = voornaam;
    }

    public static Klant fromJSON(JSONObject object){
        Klant k = new Klant();

        try{
            k.id = object.getInt("id");
            k.inlogNaam = object.getString("inlognaam");
            k.naam = object.getString("Naam");
            k.voornaam = object.getString("Voornaam");

            return k;
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
