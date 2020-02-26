package fr.epsi.gostyle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import fr.epsi.gostyle.model.Promo;

/**
 * Class PromosAdapter
 * Adapter pour la liste des promos dans l'application
 */
public class PromosAdapter extends ArrayAdapter<Promo> {
    public PromosAdapter(@NonNull Context context, int resource, @NonNull List<Promo> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater li = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = li.inflate(R.layout.c_promo, null);
        TextView textViewName=convertView.findViewById(R.id.textViewReduction);

        Promo promo=getItem(position);

        textViewName.setText(promo.getCode());

        return convertView;
    }
}
