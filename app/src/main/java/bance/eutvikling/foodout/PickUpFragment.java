package bance.eutvikling.foodout;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class PickUpFragment extends Fragment {

    public PickUpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pick_up, container, false);

        ArrayList<RestoransDataModel> listDuomenys;
        ListView listas;
        RestoransArrayListAdapter adapteris;

        listDuomenys=new ArrayList<>();
        listDuomenys.add(new RestoransDataModel("Spurgine","Adresas Veiveriu 65-2 Kaunas ---Spurgos - bandeles skaniausios nuo rusu laiku",R.drawable.donut));
        listDuomenys.add(new RestoransDataModel("Piragu pasaulis ","Adresas Griezles 113 Kaunas ---Saldesiai su cukrum ir be jo",R.drawable.froyo));
        adapteris=new RestoransArrayListAdapter(listDuomenys,view.getContext());

        listas=view.findViewById(R.id.list);
        listas.setAdapter(adapteris);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }
}