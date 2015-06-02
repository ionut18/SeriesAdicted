package com.example.ionutcristian.seriesadicted;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.ionutcristian.seriesadicted.data.SeriesDataBase;


public class MainActivity extends ActionBarActivity {

    private final String LOG_TAG = MainActivity.class.getSimpleName();

    SeriesDataBase sdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        sdb.addPopularSeries("Got", 101, "Bataie", "ruleaza", false);
//        sdb.addPopularSeries("Go1t", 102, "Batvaie", "rusleaza", false);
//        sdb.addPopularSeries("Got2", 103, "Batsaie", "rulaseaza", true);
//        sdb.addPopularSeries("G4ot", 104, "Batasie", "rulfseaza", false);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        sdb = new SeriesDataBase(this);
//        try {
//            sdb.createDatabase();
//        } catch (IOException ioe) {
//            throw new Error("Unable to create database");
//        }
//        try {
//            sdb.openDatabase();
//        }catch(SQLException sqle){
//            try {
//                throw sqle;
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new SeriesFragment())
                    .commit();
        }
        /*Cursor c = sdb.getPopularSeriers();
        Context context = this;
        CharSequence text = c.getString(1);
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    protected void onStart() {
        Log.v(LOG_TAG, "in on Start");
        super.onStart();
        // The activity is about to become visible.
    }
    @Override
    protected void onResume() {
        Log.v(LOG_TAG, "in on Resume");
        super.onResume();
        // The activity has become visible (it is now "resumed").
    }
    @Override
    protected void onPause() {
        Log.v(LOG_TAG, "in on Pause");
        super.onPause();
        // Another activity is taking focus (this activity is about to be "paused").
    }
    @Override
    protected void onStop() {
        Log.v(LOG_TAG, "in on Stop");
        super.onStop();
        // The activity is no longer visible (it is now "stopped")
    }
    @Override
    protected void onDestroy() {
        Log.v(LOG_TAG, "in on Destroy");
        super.onDestroy();
        // The activity is about to be destroyed.
    }

}
