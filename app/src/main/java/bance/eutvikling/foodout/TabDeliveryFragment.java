package bance.eutvikling.foodout;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TabDeliveryFragment extends Fragment {

    public interface CheckIfLoggedIn{
        boolean checkIfLoggedIn();
    }

    CheckIfLoggedIn mCallback;

    private String json;

    public void setJson(String json) {
        this.json = json;
    }

    public TabDeliveryFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static TabDeliveryFragment newInstance() {
        TabDeliveryFragment fragment = new TabDeliveryFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_delivery, container, false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof CheckIfLoggedIn){
            mCallback = (CheckIfLoggedIn) context;
        } else {
            //error
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback=null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        boolean loggedIn = false;

        loggedIn = mCallback.checkIfLoggedIn();

        TableLayout lentele=getActivity().findViewById(R.id.lentele);

        if (loggedIn){
            try{
                JSONObject obj = new JSONObject(this.json);

                JSONObject user = obj.getJSONObject("user");

                String name = user.getString("name");
                String email = (String) user.get("email");
                String phone = (String) user.get("phone");

                TableRow eilute=new TableRow(getActivity());
                eilute.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT, 1f));
                TextView txt=new TextView(getActivity());
                txt.setText(name);
                txt.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT,1f));
                eilute.addView(txt);

                txt=new TextView(getActivity());
                txt.setText(email);
                txt.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT,1f));
                eilute.addView(txt);

                txt=new TextView(getActivity());
                txt.setText(phone);
                txt.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT,1f));
                eilute.addView(txt);

                lentele.addView(eilute);

            } catch(JSONException e){
                e.printStackTrace();
            }

        } else {

            String name = "Not registered";

            TableRow eilute=new TableRow(getActivity());
            eilute.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT, 1f));
            TextView txt=new TextView(getActivity());
            txt.setText(name);
            txt.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT,1f));
            eilute.addView(txt);

            lentele.addView(eilute);
        }

    }
}