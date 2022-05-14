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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PickUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PickUpFragment extends Fragment {




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PickUpFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PickUpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PickUpFragment newInstance(String param1, String param2) {
        PickUpFragment fragment = new PickUpFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pick_up, container, false);

        ArrayList<RestoransDataModel> listDuomenys;//ArrayList masyvas, bus saugojami visi duomenys
        ListView listas;//pats ListView kaip interfeiso elementas
        RestoransArrayListAdapter adapteris;

        listDuomenys=new ArrayList<>();//susikuriam nauja ArrayList
        //galima ir is karto sukraut su anoniminem klasem
        listDuomenys.add(new RestoransDataModel("Spurgine","Adresas Veiveriu 65-2 Kaunas ---Spurgos - bandeles skaniausios nuo rusu laiku",R.drawable.donut));
        listDuomenys.add(new RestoransDataModel("Piragu pasaulis ","Adresas Griezles 113 Kaunas ---Saldesiai su cukrum ir be jo",R.drawable.froyo));
        adapteris=new RestoransArrayListAdapter(listDuomenys,view.getContext());//sukuriam musu adapteri

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