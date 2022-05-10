package bance.eutvikling.foodout;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DrawerItemCustomAdapter extends ArrayAdapter<DataModel> {
    Context mContext;
    int layoutResourceId;
    DataModel data[] = null;//duomenys bus paprastam masyve, jei sarasas bus pastovus, nebutina ArrayList

    public DrawerItemCustomAdapter(Context mContext, int layoutResourceId, DataModel[] data) {
        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        View listItem = inflater.inflate(layoutResourceId, parent, false);
        //layoutResourceId bus list_view_item_row.xml

        ImageView imageViewIcon = listItem.findViewById(R.id.imageViewIcon);
        TextView textViewName = listItem.findViewById(R.id.textViewName);

        imageViewIcon.setImageResource(data[position].icon);
        textViewName.setText(data[position].name);

        return listItem;
    }
}
