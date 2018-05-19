package be.sitewish.buurtslagers;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONObject;

import be.sitewish.buurtslagers.domain.Broodje;
import be.sitewish.buurtslagers.domain.Controller;
import be.sitewish.buurtslagers.domain.RequestHandler;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView listView;
    private Controller controller = ((Controller) this.getApplication());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.broodjes_list);

        final ActionBar ab = getSupportActionBar();

        ab.setDisplayShowHomeEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowCustomEnabled(true);
        ab.setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Broodje broodje = (Broodje) parent.getAdapter().getItem(position);
                Intent intent = new Intent(MainActivity.this, BroodjeActivity.class);
                intent.putExtra("Broodje", broodje);
                MainActivity.this.startActivity(intent);
                //Toast.makeText(MainActivity.this, "Je koos " + broodje.getNaam(), Toast.LENGTH_SHORT).show();
            }
        });

        //api call voor alle broodjes
        HashMap map = new HashMap();
        map.put("api_key", Controller.KEY);
        map.put("action", "GET");

        BroodjeRequest broodjeRequest = new BroodjeRequest(Controller.URL + "/broodje", map);
        broodjeRequest.execute();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

        } else if (id == R.id.nav_winkelmandje) {
            Intent intent = new Intent(this, WinkelmandjeActivity.class);
            this.startActivity(intent);
            this.finish();
        } else if (id == R.id.nav_account) {

        } else if (id == R.id.nav_uitloggen) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setListView(){
        BroodjeAdapter broodjeAdapter = new BroodjeAdapter(this, ((Controller) this.getApplication()).getBroodjes());
        listView.setAdapter(broodjeAdapter);
    }

    private class BroodjeRequest extends AsyncTask<Void, Void, String>{

        String url;
        HashMap map;

        public BroodjeRequest(String url, HashMap map) {
            this.url = url;
            this.map = map;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            JSONArray arr = null;
            JSONObject obj = null;

            try{
                if(Controller.IsJSONArray(s)){
                    arr = new JSONArray(s);
                }
                else{
                    obj = new JSONObject(s);
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }

            if(arr != null){
                try{
                    ((Controller) MainActivity.this.getApplication()).setBroodjes(Broodje.fromJSON(arr));
                    setListView();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        @Override
        protected String doInBackground(Void... voids) {
            RequestHandler requestHandler = new RequestHandler();

            return requestHandler.sendPostRequest(url, map);
        }
    }
}
