package bance.eutvikling.foodout;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements RegisterFragment.OnMygtukasListener, LoginFragment.onLoginListener, TabFragment.LoadUserData, TabDeliveryFragment.CheckIfLoggedIn{

    ArrayList arrUsers;
    Boolean userLoggedIn;




    private String mNavigationDrawerItemTitles[];//hamburgerio meniu komandu pavadinimu masyvas (listview)
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private RelativeLayout mDrawerPane;
    Toolbar toolbar;
    ActionBarDrawerToggle mDrawerToggle; //hamburgeris pavista i back rodykle

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userLoggedIn = false;
        setContentView(R.layout.activity_main);




        Toolbar toolbar = findViewById(R.id.toolbar);

        //pakeisti temose (themes.xml) i Theme.MaterialComponents.DayNight.NoActionBar
        setSupportActionBar(toolbar);

        //is resursu strings.xml paimam i paprasta masyva <string-array>
        mNavigationDrawerItemTitles= getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerPane = findViewById(R.id.drawerPane);
        mDrawerList = findViewById(R.id.left_drawer);

        nustatytiToolbar();

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);

        //Sukrausime listView meniu komandas hamburgeryje
        DataModel[] drawerItems = new DataModel[6];
        drawerItems[0] = new DataModel(R.drawable.flag96, mNavigationDrawerItemTitles[0]);
        drawerItems[1] = new DataModel(R.drawable.chefhat96, mNavigationDrawerItemTitles[1]);
        drawerItems[2] = new DataModel(R.drawable.discount96, mNavigationDrawerItemTitles[2]);
        drawerItems[3] = new DataModel(R.drawable.adduser96, mNavigationDrawerItemTitles[3]);
        drawerItems[4] = new DataModel(R.drawable.help96, mNavigationDrawerItemTitles[4]);
        drawerItems[5] = new DataModel(R.drawable.user96, mNavigationDrawerItemTitles[5]);

        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.list_view_item_row, drawerItems);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        setupDrawerToggle();


        //Pirmo fragmento pakrovimas--------------------------------------------------------------------------
        if(findViewById(R.id.frame) != null) {
            if (savedInstanceState != null) {
                return;
            }
            // Sukuriam pirmaji fragmenta
            RegisterFragment regFragment = new RegisterFragment();
            MainRestoransFragment mainRestFragment = new MainRestoransFragment();
            // dar galima pasinaudoti ir default per new instance pvz
            //AntrasFragment secondFragment = AntrasFragment.newInstance();

            //Galimybe nusiusti pirmam fragmentui parametrus
            //jei sita Activity butu ne pagrindine ir gautu parametrus
            //is ankstesnes Activity per Intent
            //firstFragment.setArguments(getIntent().getExtras());

            //Jei siaip perduot, galima ir taip:
            //Bundle b=new Bundle();
            //b.putInt("sk1",20);
            //firstFragment.setArguments(b);

            // Prideti fragmenta i MainActivity Layout (FrameLayout)
            getSupportFragmentManager().beginTransaction().
                    add(R.id.frame, mainRestFragment).commit();
        }
    }

    void nustatytiToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    void setupDrawerToggle(){
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name, R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle.syncState();
    }

    @Override
    public void registerNewUser(String name, String email, String phone, String password){

        Log.i("Vardas",name);

        try {
            saveDB("user", name, email, phone, password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        pakeistiFragmentus(true);
    }

    @Override
    public boolean checkIfLoggedIn() {
        return userLoggedIn;
    }


    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            rodytiSvetaine(position);
        }
    }

    public void rodytiSvetaine(int pos){


        switch (pos) {
            case 0: // chose your contry
                Toast.makeText(this, "No implementations jet", Toast.LENGTH_LONG).show();
                break;
            case 1: // List of restorans MAIN
                MainRestoransFragment mainRestoranFragment = new MainRestoransFragment();//kursime antra fragmenta
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //Sukeiciam fragmentus
                transaction.replace(R.id.frame, mainRestoranFragment,"MY_FRAGMENT");
                //Pirma "varom" i steka. Useris tada su "Back" mygtuku gali grizt i pirma fragmenta
                //transaction.addToBackStack(null);
                // Patvirtinam tranzakcija
                transaction.commit();
                break;
            case 2: // Discounts
                TabFragment tabFragment = new TabFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, tabFragment).commit();

                break;
            case 3: //Registration

                Log.i("kokia bukle", userLoggedIn.toString());
                if (!userLoggedIn) {
                    RegisterFragment regFragment = new RegisterFragment(); //kursime antra fragmenta
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, regFragment).commit();
                    getSupportActionBar().setTitle(mNavigationDrawerItemTitles[pos]);
                } else {
                    TabFragment tabFragment2 = new TabFragment(); //kursime antra fragmenta
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, tabFragment2).commit();
                    getSupportActionBar().setTitle(mNavigationDrawerItemTitles[2]);
                }

                break;
            case 4: // About
                AboutFragment aboutFragment = new AboutFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, aboutFragment).commit();
                break;
            case 5: // Login
                LoginFragment loginFragment = new LoginFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, loginFragment).commit();
                break;

            default:
                break;
        }
        mDrawerList.setItemChecked(pos, true);
        mDrawerList.setSelection(pos);//paliks pasirinkto spalva
        if (pos != 3 ) {
            getSupportActionBar().setTitle(mNavigationDrawerItemTitles[pos]);
        }
        mDrawerLayout.closeDrawer(mDrawerPane);
    }

    @Override
    public void pakeistiFragmentus(boolean match) {
        if(match){
            userLoggedIn= true;
            TabFragment tabFragment = new TabFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame, tabFragment).commit();
            getSupportActionBar().setTitle(mNavigationDrawerItemTitles[2]);
        }
    }

    public JSONArray readDB() throws JSONException {


        if(isExternalStorageWritable()==false){
            Toast.makeText(this,"Negalima pasiekti isorines atminties",Toast.LENGTH_SHORT).show();
            return null;
        }

        String path = getExternalFilesDir(null) + "/FoodOut";
        File file = new File(path ,"users.txt");

        String content=null;
        if(file.exists())
        {
            FileReader reader = null;
            try {
                reader = new FileReader(file);
                char[] chars = new char[(int) file.length()];
                reader.read(chars);
                content = new String(chars);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

            System.out.println("CANT READ FILE or no file ****************************************************************");
           Toast.makeText(this, "No ussers registered",Toast.LENGTH_SHORT).show();
        }
        System.out.println("----------------------------------------------------Failas nuskaitytas--------------------------------------------------------------------------");
        System.out.println(content);

        JSONArray arrfromFile = new JSONArray();

        if(content != null){
            JSONObject temp = new JSONObject(content);
            arrfromFile.put(temp);

        }
        return arrfromFile;
    }


    public void saveDB(String objName, String name, String email, String phone, String password) throws JSONException {

        JSONObject objToSave = convertToJasonObj(objName, name, email, phone, password);

        if(isExternalStorageWritable()){
            try {

                String state = Environment.getExternalStorageState();
                if (Environment.MEDIA_MOUNTED.equals(state)) {
                    System.err.println("Permision is give to read******************************** "+state);
                }

                String path = getExternalFilesDir(null) + "/FoodOut";
                File myDir = new File(path);
                System.out.println("Creating file here******************************** "+myDir);
                if (!myDir.exists()) {
                    myDir.mkdirs();
                    System.err.println("Sukure kataloga ******************************** "+myDir);
                }

                File files = new File(path,"users.txt");
                FileWriter fw;
                fw = new FileWriter(files);
                fw.write(String.valueOf(objToSave));
                fw.close();
                Log.i("SaveToFile","Success 2");

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println(" No Permision to write ************************************************* isExternalStorageWritable()");
        }
    }


    public JSONObject convertToJasonObj(String nameObject, String name, String email, String phone, String password) throws JSONException {

        JSONObject user = new JSONObject();
        JSONObject restoran = new JSONObject();

        if(nameObject.equals("user")){
            JSONObject jObj = new JSONObject();
            jObj.put("name", name);
            jObj.put("email", email);
            jObj.put("phone", phone);
            jObj.put("password", password);

            user.put("user", jObj);

        } else if (nameObject.equals("restoran")){
            // for restorans registration etc...
        }

        return user;
    }

    /* tikrinam, ar galime skaityti ir rasyti */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }


}