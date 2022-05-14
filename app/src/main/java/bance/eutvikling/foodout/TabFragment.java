package bance.eutvikling.foodout;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 //* Use the {@link TabFragment# newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabFragment extends Fragment {

    public interface LoadUserData{
        JSONArray readDB() throws JSONException;
    }

    LoadUserData loadUserDataCallback;

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    public TabFragment() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment TabFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static TabFragment newInstance(String param1, String param2) {
//        TabFragment fragment = new TabFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//
//
//
//    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof LoadUserData){
            loadUserDataCallback = (LoadUserData) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        loadUserDataCallback = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment fragment_tab2
        return inflater.inflate(R.layout.fragment_tab, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        JSONArray userAr ;
        JSONObject user = new JSONObject();
            try {
                    userAr = loadUserDataCallback.readDB();
                    JSONObject users = userAr.getJSONObject(0);
                    user.put("user", users.getJSONObject("user"));
                    Log.i("how it looks", user.toString());
                    } catch (JSONException e) {
                    e.printStackTrace();
                    }

        TabLayout tabLayout = (TabLayout) getActivity().findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Delivery"));
        tabLayout.addTab(tabLayout.newTab().setText("Pick up"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.pager);

        final PagerAdapter adapter = new PagerAdapter
                (getActivity().getSupportFragmentManager(), tabLayout.getTabCount(),user.toString());

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        FloatingActionButton mail =  getActivity().findViewById(R.id.fab);
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), " No actions implemented for button", Toast.LENGTH_SHORT).show();
            }
        });

    }
}