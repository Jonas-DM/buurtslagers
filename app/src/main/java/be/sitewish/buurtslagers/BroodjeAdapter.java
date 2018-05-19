package be.sitewish.buurtslagers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import be.sitewish.buurtslagers.domain.Broodje;

public class BroodjeAdapter extends ArrayAdapter {

    public BroodjeAdapter(@NonNull Context context, ArrayList<Broodje> broodjes) {
        super(context, 0, broodjes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Broodje broodje = (Broodje) getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_broodje, parent, false);
        }

        TextView tvNaam = (TextView) convertView.findViewById(R.id.txtNaam);
        TextView tvPrijs = (TextView) convertView.findViewById(R.id.txtPrijs);

        tvNaam.setText("Broodje " + broodje.getNaam());
        tvPrijs.setText("€ " +broodje.getPrijs().toString());

        return convertView;
    }


}
