package com.superkorsuk.happybaby;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.superkorsuk.happybaby.db.BabyDoRepository;
import com.superkorsuk.happybaby.util.Alarm;
import com.superkorsuk.happybaby.util.ShareUtil;
import com.superkorsuk.happybaby.views.BabyBasicInfoActivity;
import com.superkorsuk.happybaby.util.NotificationRegisterUtil;
import com.superkorsuk.happybaby.db.BabyRepository;
import com.superkorsuk.happybaby.models.*;
import com.superkorsuk.happybaby.views.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import me.drakeet.materialdialog.MaterialDialog;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ChartFragment.OnFragmentInteractionListener {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        // Chart Fragment
        createChartFragment();


    }

    public void createChartFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.frag_chart, ChartFragment.newInstance("param1", "param2"));
        fragmentTransaction.commit();
    }


    public void onClick(View v) {
        Alarm alarm = new Alarm(getApplicationContext());

        switch (v.getId()) {
            case R.id.buttonAlarm1:
                alarm.setTimerForOnetime(5);
                break;
            case R.id.buttonAlarm2:
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis() + 5000);
                alarm.setTimerForRepeat(calendar, 60);
                break;
            case R.id.buttonNotification:
                alarm.removeAlarm();

                Log.d("NOTIFICATION", "notification start.");

                NotificationRegisterUtil n = new NotificationRegisterUtil(getApplicationContext());

                Intent intent = new Intent(MainActivity.this, GetPhotoActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, (int) System.currentTimeMillis(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
                n.notify(n.makeSimpleNotification(pendingIntent, "TITLE", "Messages"));
                break;
            case R.id.buttonChartActivity:
                Intent intent1 = new Intent(this, ChartActivity.class);
                startActivity(intent1);
                break;
            case R.id.buttonAlert:
                final MaterialDialog mDialog = new MaterialDialog(this);

                mDialog.setTitle("Alert Test")
                        .setMessage("This is the alert test")
                        .setPositiveButton("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(MainActivity.this, "OK clicked", Toast.LENGTH_SHORT).show();
                                mDialog.dismiss();
                            }
                        })
                        .setNegativeButton("Cancel", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(MainActivity.this, "Cancel clicked", Toast.LENGTH_SHORT).show();
                                mDialog.dismiss();
                            }
                        })
                        .setCanceledOnTouchOutside(false)
                        .show();
                break;

            case R.id.button_add_baby:
                addBabyRandomNew();
                break;

            case R.id.button_goToBabyBasicInfo:
//                Intent intentBabyBasicInfo = new Intent(getApplicationContext(), BabyBasicInfoActivity.class);
                Intent intentBabyBasicInfo = new Intent(this, BabyBasicInfoActivity.class);

                startActivity(intentBabyBasicInfo);
                break;

            case R.id.button_get_all_babies:
                BabyRepository babyRepo = new BabyRepository(getApplicationContext());
                List<Baby> babies = babyRepo.all();
                for(Baby baby : babies) {
                    Log.d("DB", baby.toString());
                }
                break;

            case R.id.btn_get_photo:
                Intent intent2 = new Intent(this, GetPhotoActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_firebase_activity:
                Intent intent3 = new Intent(this, FirebaseActivity.class);
                startActivity(intent3);
                break;
            case R.id.btn_share:
                ShareUtil.sendShare(this);
                break;

            case R.id.button_go_to_today:
                startActivity(new Intent(MainActivity.this, TodayActivity.class));
                break;

            case R.id.btn_statistics_calendar:
                startActivity(new Intent(MainActivity.this, StatisticsCalendarActivity.class));
                break;

            default:
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK) {
            return;
        }

        switch (requestCode) {
            case 999:
                Uri imageCaptureUri = data.getData();
                Log.d("Image", imageCaptureUri.getPath().toString());
                break;
            default:
                break;
        }
    }

    private void addBabyRandomNew() {
        BabyRepository babyRepo = new BabyRepository(getApplicationContext());

        // add
        String babyName = "Baby" + (int)(Math.random() * 100);
        Baby baby = new Baby();
        baby.setName(babyName);
        baby.setGender(Gender.FEMALE);
        baby.setBirthday(new Date());
        baby.setGestationPeriod(42, 2);

        int result = babyRepo.create(baby);

        if (result > 0) {
            Toast.makeText(getApplicationContext(), babyName + " was added !", Toast.LENGTH_LONG).show();

            Log.d("DB", "baby added");
        } else {
            Log.d("DB", "baby addition was failed");
        }

    }

    private void addBabyDoRandom() {
        BabyDoRepository doRepo = new BabyDoRepository(getApplicationContext());

        int amount = (int)(Math.random() * 150);
        int babyId = 1;

        BabyDo babyDo = BabyDoFactory.getFormula(babyId, new Date(), amount, "Good child.");

        int id = doRepo.create(babyDo);
        if (id > 0) {
            Toast.makeText(getApplicationContext(), "baby drank formula : " + amount + "ml", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "baby drank formula : failed", Toast.LENGTH_LONG).show();

        }
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
//        if (id == R.id.action_settings) {
//            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
//            startActivity(intent);
////            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_share:
                break;
            default:
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.superkorsuk.happybaby/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.superkorsuk.happybaby/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
