package be.sitewish.buurtslagers;

import android.app.ActionBar;
import android.os.Bundle;
import android.service.media.MediaBrowserService;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import be.sitewish.buurtslagers.domain.Controller;
import be.sitewish.buurtslagers.domain.Item;

public class BroodjeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    be.sitewish.buurtslagers.domain.Broodje broodje = null;
    Controller controller = ((Controller) this.getApplication());

    TextView tvNaam;
    TextView tvPrijs;
    EditText etAantal;
    EditText etOpmerking;
    Button btnWinkelmandje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broodje);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final android.support.v7.app.ActionBar ab = getSupportActionBar();

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

        Bundle data = getIntent().getExtras();
        broodje = (be.sitewish.buurtslagers.domain.Broodje) data.getParcelable("Broodje");

        tvNaam = (TextView) findViewById(R.id.txtNaam);
        tvPrijs = (TextView) findViewById(R.id.txtPrijs);
        etAantal = (EditText) findViewById(R.id.etAantal);
        etOpmerking = (EditText) findViewById(R.id.etOpmerking);
        btnWinkelmandje = (Button) findViewById(R.id.btnToevoegen);

        tvNaam.setText("Broodje " + broodje.getNaam());
        tvPrijs.setText("€ " + broodje.getPrijs().toString());

        btnWinkelmandje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Item item = new Item(broodje, Integer.parseInt(etAantal.getText().toString()), etOpmerking.getText().toString(), "");
                ((Controller) BroodjeActivity.this.getApplication()).getWinkelmandje().AddItem(item);
                Toast.makeText(BroodjeActivity.this, "Broodje toegevoegd aan winkelmandje.", Toast.LENGTH_SHORT).show();
                BroodjeActivity.this.finish();
            }
        });
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
        getMenuInflater().inflate(R.menu.broodje, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
