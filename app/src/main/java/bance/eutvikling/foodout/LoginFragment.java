package bance.eutvikling.foodout;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    public interface onLoginListener {
        JSONArray readDB() throws JSONException;
        void pakeistiFragmentus(boolean match);
    }

    onLoginListener mCallback;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final JSONObject ARG_USER = null;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private  JSONObject mUSER;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mCallback = (onLoginListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnLogin = (Button) getActivity().findViewById(R.id.login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText emailFeald = getActivity().findViewById(R.id.email);
                String emailInput = emailFeald.getText().toString();

                EditText passwordFeald = getActivity().findViewById(R.id.password);
                String passwordInput = passwordFeald.getText().toString();

                TextView infoFeald = getActivity().findViewById(R.id.text);

                JSONArray users = new JSONArray();
                try {
                    users = mCallback.readDB();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                boolean match = false;

                try {
                    JSONObject user = users.getJSONObject(0);
                    JSONObject temp = user.getJSONObject("user");
                    if (temp.getString("email").equals(emailInput) && temp.getString("password").equals(passwordInput)) { // add and psw
                        match = true;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if(match){
                    //logg in user
                    mCallback.pakeistiFragmentus(true);
                    Toast.makeText(getActivity(), "Login successful", Toast.LENGTH_LONG ).show();
                } else {
                    infoFeald.setText("Wrong email or password");
                }
            }
        });
    }
}