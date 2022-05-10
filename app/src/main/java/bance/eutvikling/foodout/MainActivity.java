package bance.eutvikling.foodout;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private String mNavigationDrawerItemTitles[];//hamburgerio meniu komandu pavadinimu masyvas (listview)
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private RelativeLayout mDrawerPane;
    Toolbar toolbar;
    ActionBarDrawerToggle mDrawerToggle; //hamburgeris pavista i back rodykle

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            rodytiSvetaine(position);
        }
    }





    public void rodytiSvetaine(int pos){
        WebView web=findViewById(R.id.webas);
        web.getSettings().setJavaScriptEnabled(true); // enable javascript

        //kad nekviestu kitos narsykles o pats rodytu ir veiktu puslapyje linkai
        web.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }
        });

        switch (pos) {
            case 0:
                web.loadUrl("https://www.google.com");
                break;
            case 2:
                //paspausta Skaiciuoti
                //
                break;

            default:
                break;
        }
        mDrawerList.setItemChecked(pos, true);
        mDrawerList.setSelection(pos);//paliks pasirinkto spalva
        getSupportActionBar().setTitle(mNavigationDrawerItemTitles[pos]);
        mDrawerLayout.closeDrawer(mDrawerPane);
    }
}