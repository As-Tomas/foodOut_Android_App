package bance.eutvikling.foodout;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;




    public interface OnMygtukasListener {
        void registerNewUser(String name, String email, String phone, String password);


    }

    //dependenci ijection
    // galima butu pasiskelbti ir Contexta ir permesti i ji main activity tai butu dependenci ijection nereiketu interveiso ir galetume viska pasiekti is main activity bet nerekomenduojama
    OnMygtukasListener mCallback;//interfeiso kintamasis, i ji irasysime perduota MainActivity kintamaji (context) onAttach metode

    /*
    * Eiliskumas
    * pasiskelbem interfeisa ir interfeiso kintamaji
    * overaidinam onAtach funkcija kad galetume pasimti Context, galim ir per kostruktoriu bet darom taip
    * overaidinam onDetach ir nunulinam calbaka
    * overaidinam onVieCreated ir susikuriam ka darys paspaudus knopke pvz iskvies implementuota funkcija
    * MainAct implementuojam ir overaidinam metoda ir MainAct pakraunam pirma fragmenta ir jai reikia perduodam duomenis
    * */




    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        //ar tevas paveldi OnMygtukasListener. context tai yra main activity
        if(context instanceof OnMygtukasListener){//paziurime ar MainActivity implementuoja OnMygtukasListener interfeisa
            mCallback =(OnMygtukasListener) context;
        } else {
            //klaidos pranesimas
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Tik nuo sito metodo galime pasiimti interfeiso elementus su
        // finViewById()
        //Jau interfeisas visas pakrautas (sitam onViewCreated() metode)
        //findViewById() yra Activity (ne Fragment) metodas
        //getActivity() grazina Activity, pakrovusia fragmenta

        Button register = getActivity().findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText nameFeald = getActivity().findViewById(R.id.name);
                String name = nameFeald.getText().toString();

                EditText emailFeald = getActivity().findViewById(R.id.email);
                String email = emailFeald.getText().toString();

                EditText phoneFeald = getActivity().findViewById(R.id.phone);
                String phone = phoneFeald.getText().toString();

                EditText passwordFeald = getActivity().findViewById(R.id.password);
                String password = passwordFeald.getText().toString();

                mCallback.registerNewUser(name, email, phone, password);


            }
        });
    }
}