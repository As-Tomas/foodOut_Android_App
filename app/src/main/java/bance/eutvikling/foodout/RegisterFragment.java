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

    public interface OnMygtukasListener {
        void registerNewUser(String name, String email, String phone, String password);
    }

    OnMygtukasListener mCallback;

    public RegisterFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof OnMygtukasListener){
            mCallback =(OnMygtukasListener) context;
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