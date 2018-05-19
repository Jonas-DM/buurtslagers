package be.sitewish.buurtslagers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import be.sitewish.buurtslagers.domain.Item;

import java.util.ArrayList;

public class WinkelmandjeAdapter extends ArrayAdapter {
    public WinkelmandjeAdapter(@NonNull Context context, ArrayList<Item> winkelmand) {
        super(context, 0, winkelmand);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Item item = (Item) getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_winkelmand, parent, false);
        }

        TextView tvNaam = (TextView) convertView.findViewById(R.id.txtNaam);
        TextView tvAantal = (TextView) convertView.findViewById(R.id.txtAantal);
        TextView tvType = (TextView) convertView.findViewById(R.id.txtType);
        TextView tvOpmerking = (TextView) convertView.findViewById(R.id.txtOpmerking);

        tvNaam.setText("Broodje " + item.getBroodje().getNaam());
        tvAantal.setText("Aantal: " + item.getAantal());
        tvType.setText("Type: " + item.getType());
        tvOpmerking.setText("Opmerking: " + item.getOpmerking());

        return convertView;
    }
}
