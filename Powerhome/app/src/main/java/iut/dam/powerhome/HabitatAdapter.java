package iut.dam.powerhome;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.List;

public class HabitatAdapter extends ArrayAdapter<Habitat> {

    public HabitatAdapter(Context context, List<Habitat> habitatList) {
        super(context, 0, habitatList);
    }

    @Override
    public View getView(int pos, View convert, ViewGroup parent){
        Habitat h = getItem(pos);

    }
}
