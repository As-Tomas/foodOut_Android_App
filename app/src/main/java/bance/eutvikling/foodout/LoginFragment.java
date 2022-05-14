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


public class LoginFragment extends Fragment {

    public interface onLoginListener {
        JSONArray readDB() throws JSONException;
        void pakeistiFragmentus(boolean match);
    }

    onLoginListener mCallback;


    public LoginFragment() {
        // Required empty public constructor
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