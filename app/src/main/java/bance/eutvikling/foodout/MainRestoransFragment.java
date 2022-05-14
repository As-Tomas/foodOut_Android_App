package bance.eutvikling.foodout;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


public class MainRestoransFragment extends Fragment {

    public MainRestoransFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pick_up, container, false);

        ArrayList<RestoransDataModel> listDuomenys;//ArrayList masyvas, bus saugojami visi duomenys
        ListView listas;//pats ListView kaip interfeiso elementas
        RestoransArrayListAdapter adapteris;

        listDuomenys=new ArrayList<>();//susikuriam nauja ArrayList
        //galima ir is karto sukraut su anoniminem klasem
        listDuomenys.add(new RestoransDataModel("Spurgine","Adresas Veiveriu 65-2 Kaunas ---Spurgos - bandeles skaniausios nuo rusu laiku",R.drawable.donut));
        listDuomenys.add(new RestoransDataModel("Piragu pasaulis ","Adresas Griezles 113 Kaunas ---Saldesiai su cukrum ir be jo",R.drawable.froyo));
        listDuomenys.add(new RestoransDataModel("Spurgine","Adresas Veiveriu 65-2 Kaunas ---Spurgos - bandeles skaniausios nuo rusu laiku",R.drawable.donut));
        listDuomenys.add(new RestoransDataModel("Piragu pasaulis ","Adresas Griezles 113 Kaunas ---Saldesiai su cukrum ir be jo",R.drawable.froyo));
        listDuomenys.add(new RestoransDataModel("Spurgine","Adresas Veiveriu 65-2 Kaunas ---Spurgos - bandeles skaniausios nuo rusu laiku",R.drawable.donut));
        listDuomenys.add(new RestoransDataModel("Piragu pasaulis ","Adresas Griezles 113 Kaunas ---Saldesiai su cukrum ir be jo",R.drawable.froyo));
        listDuomenys.add(new RestoransDataModel("Spurgine","Adresas Veiveriu 65-2 Kaunas ---Spurgos - bandeles skaniausios nuo rusu laiku",R.drawable.donut));
        listDuomenys.add(new RestoransDataModel("Piragu pasaulis ","Adresas Griezles 113 Kaunas ---Saldesiai su cukrum ir be jo",R.drawable.froyo));
        listDuomenys.add(new RestoransDataModel("Spurgine","Adresas Veiveriu 65-2 Kaunas ---Spurgos - bandeles skaniausios nuo rusu laiku",R.drawable.donut));
        listDuomenys.add(new RestoransDataModel("Piragu pasaulis ","Adresas Griezles 113 Kaunas ---Saldesiai su cukrum ir be jo",R.drawable.froyo));
        listDuomenys.add(new RestoransDataModel("Spurgine","Adresas Veiveriu 65-2 Kaunas ---Spurgos - bandeles skaniausios nuo rusu laiku",R.drawable.donut));
        listDuomenys.add(new RestoransDataModel("Piragu pasaulis ","Adresas Griezles 113 Kaunas ---Saldesiai su cukrum ir be jo",R.drawable.froyo));
        adapteris=new RestoransArrayListAdapter(listDuomenys,view.getContext());//sukuriam musu adapteri

        listas=view.findViewById(R.id.list);
        listas.setAdapter(adapteris);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}