package womensafety.com.pink;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.identity.intents.Address;
import com.google.android.gms.location.LocationServices;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener {

    MediaPlayer mp = null;
    String hello = "Hello!";
    Button btn_sos, btn_scream;
    SharedPreferences sp, sp1;
    GoogleApiClient gac;
    Location loc;
    String msg1="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //startService(new Intent(getApplicationContext(), LockService.class));

        btn_sos = (Button) findViewById(R.id.btn_sos);
        btn_scream = (Button) findViewById(R.id.btn_scream);

        sp = getSharedPreferences("event", MODE_PRIVATE);
        String no1 = sp.getString("no1", "");
        String no2 = sp.getString("no2", "");
        String name = sp.getString("name", "");
        String contact = sp.getString("contact", "");


        Toast.makeText(getApplicationContext(), name + " " + contact + " " + no1 + " " + no2, Toast.LENGTH_LONG).show();
        final String msg = "Help, I am in Danger!";
        final String[] locmsg = {""};
        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {Manifest.permission.SEND_SMS, Manifest.permission.READ_PHONE_STATE};
        final String[] contacts = {no1, no2};
        Intent user_i = getIntent();
//        String name=user_i.getStringExtra("name");
//        String contact_no=user_i.getStringExtra("contact");
//        SharedPreferences.Editor editor = sp1.edit();
//        editor.putString("contact",contact_no);
//        editor.commit();
        sp1 = getSharedPreferences("event", MODE_PRIVATE);
        String get1 = sp1.getString("contact", "");

        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }

        btn_sos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (int i = 0; i < contacts.length; i++) {
                    try {
                        SmsManager smsManager = SmsManager.getDefault();

                        smsManager.sendTextMessage(contacts[i], null, msg, null, null);
                        Toast.makeText(getApplicationContext(), "SOS Sent",
                                Toast.LENGTH_SHORT).show();
                    } catch (Exception ex) {
                        Toast.makeText(getApplicationContext(), ex.getMessage().toString(),
                                Toast.LENGTH_LONG).show();
                        ex.printStackTrace();
                    }
                }
            }
        });
        btn_scream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i=new Intent(getApplicationContext(),GooglePlacesActivity.class);
//                startActivity(i);
                if (mp != null) {
                    mp.reset();
                    mp.release();
                }
                if (hello == hello)
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.siren2);

                mp.setLooping(true);
                mp.start();
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
            sp = getSharedPreferences("event", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("nam", "");
            editor.commit();
            Intent i = new Intent(getApplicationContext(), Login.class);
            startActivity(i);
        } else if (id == R.id.nav_manage) {
            Intent i = new Intent(getApplicationContext(), webview.class);
            startActivity(i);

        } else if (id == R.id.nav_share) {
            ApplicationInfo app = getApplicationContext().getApplicationInfo();
            String filepath = app.sourceDir;
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("*/*");
            i.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(filepath)));
            startActivity(i.createChooser(i, "Share App via"));

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.nav_about) {
            Intent i = new Intent(getApplicationContext(), about.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (gac != null)    gac.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (gac != null)    gac.disconnect();
    }
}