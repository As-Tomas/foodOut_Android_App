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
        DataModel[] drawerItems = new DataModel[3];
        drawerItems[0] = new DataModel(R.drawable.delfi, mNavigationDrawerItemTitles[0]);
        drawerItems[1] = new DataModel(R.drawable.lrytas, mNavigationDrawerItemTitles[1]);
        drawerItems[2] = new DataModel(R.drawable.calculate, mNavigationDrawerItemTitles[2]);

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
                web.loadUrl("https://www.delfi.lt");
                break;
            case 1:
                //paspausta lrytas
                web.loadUrl("https://www.lrytas.lt");
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