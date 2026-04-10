package iut.dam.powerhome;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class HabitatAdapter extends ArrayAdapter<Habitat> {

    public HabitatAdapter(Context context, List<Habitat> habitatList) {
        super(context, 0, habitatList);
    }

    @Override
    public View getView(int pos, View convert, ViewGroup parent){
        Habitat h = getItem(pos);

        TextView nomResident = convert.findViewById(R.id.tv_resident_name);
        TextView etage = convert.findViewById(R.id.tv_floor_number);
        TextView equipements = convert.findViewById(R.id.tv_equipments);
        LinearLayout icones = convert.findViewById(R.id.ll_icones_container);

        nomResident.setText(h.getName());
        etage.setText(String.valueOf(h.getFloor()));
        equipements.setText(h.getAppliances().size() + "équipement(s)");
        icones.removeAllViews();

        for(Appliance a : h.getAppliances()){
            ImageView icone = new ImageView(getContext());
            setIcone(icone, a);
            icones.addView(icone);
        }

        return convert;
    }

    private void setIcone(ImageView icone, Appliance a){
        icone.setImageResource(a.icone);
        int size = (int) (18 * getContext().getResources().getDisplayMetrics().density);
        LinearLayout.LayoutParams settings = new LinearLayout.LayoutParams(size, size);
        settings.setMargins(6,0,0,0);
        icone.setLayoutParams(settings);
        icone.setScaleType(ImageView.ScaleType.CENTER);
    }
}
