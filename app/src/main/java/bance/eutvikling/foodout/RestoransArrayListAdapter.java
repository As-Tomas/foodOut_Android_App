package bance.eutvikling.foodout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class RestoransArrayListAdapter extends ArrayAdapter<RestoransDataModel> {

    private ArrayList<RestoransDataModel> data;
    Context ctx; //saving MainActivity variable (this)

    public RestoransArrayListAdapter(ArrayList<RestoransDataModel> data, Context ctx) {
        super(ctx,R.layout.restorans_list_item,data);
        this.data = data;
        this.ctx = ctx;
    }

// getView will be called automatically on Android and needs to be written on how to load a separate ListView entry
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(ctx);
        //restrarts my_list_item.xml
        View  view= inflater.inflate(R.layout.restorans_list_item, parent, false);

        ImageView image=view.findViewById(R.id.icon);
        TextView title=view.findViewById(R.id.title);
        TextView description=view.findViewById(R.id.description);

        RestoransDataModel record_data=data.get(position);
        image.setImageResource(record_data.getIcon());
        title.setText(record_data.getName());
        description.setText(record_data.getDescription());

        //If want to use context meniu for items
        view.setId(position);//for every item set id equal to it position
        ((MainActivity)ctx).registerForContextMenu(view);
        //end context meniu

        return view;
    }
}
