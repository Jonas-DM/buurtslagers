package be.sitewish.buurtslagers.domain;

public class Item {
    private Broodje broodje;
    private int aantal;
    private String opmerking;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOpmerking() {
        return opmerking;
    }

    public void setOpmerking(String opmerking) {
        this.opmerking = opmerking;
    }

    public Broodje getBroodje() {
        return broodje;
    }

    public void setBroodje(Broodje broodje) {
        this.broodje = broodje;
    }

    public int getAantal() {
        return aantal;
    }

    public void setAantal(int aantal) {
        this.aantal = aantal;
    }

    public Item(Broodje broodje, int aantal, String opmerking, String type) {
        this.broodje = broodje;
        this.aantal = aantal;
        this.opmerking = opmerking;
        this.type = type;
    }
}
