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

public class TabFragment extends Fragment {

    public interface LoadUserData{
        JSONArray readDB() throws JSONException;
    }

    LoadUserData loadUserDataCallback;

    public TabFragment() {
        // Required empty public constructor
    }

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