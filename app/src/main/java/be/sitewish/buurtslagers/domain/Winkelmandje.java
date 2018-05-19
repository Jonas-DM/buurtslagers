package be.sitewish.buurtslagers.domain;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Winkelmandje {

    //region Properties en constructor
    private ArrayList<Item> winkelmand = new ArrayList<>();

    public ArrayList<Item> getWinkelmand() {
        return winkelmand;
    }

    public void setWinkelmand(ArrayList<Item> winkelmand) {
        this.winkelmand = winkelmand;
    }

    public Winkelmandje() {
    }

    public Winkelmandje(ArrayList<Item> winkelmand) {
        this.winkelmand = winkelmand;
    }
    //endregion

    public void AddItem(Item item){
        winkelmand.add(item);
    }

    public BigDecimal getTotaal(){
        BigDecimal totaal = BigDecimal.ZERO;

        for(int i = 0; i < winkelmand.size(); i++){
            totaal = totaal.add(winkelmand.get(i).getBroodje().getPrijs().multiply(new BigDecimal(winkelmand.get(i).getAantal())));
        }

        return totaal;
    }
}
