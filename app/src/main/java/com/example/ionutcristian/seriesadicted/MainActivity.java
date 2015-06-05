package com.example.ionutcristian.seriesadicted;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    private final String LOG_TAG = MainActivity.class.getSimpleName();

    private Menu mymenu;

    SeriesDataBase sdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sdb = new SeriesDataBase(this);

        sdb.insertSeries(1,"Game of Thrones", "Several noble families fight for control of the mythical... ",
                "Several noble families fight for control of the mythical land of Westeros.",
                "Action", "HBO", "RUNNING", 0, "31.05.2015 - Hardhome", "07.06.2015 - The Dance of Dragons", "http://www.imdb.com/title/tt0944947/", 9.5);

        sdb.insertSeries(2,"Californication", "A self-loathing, alcoholic writer attempts to repair his... ",
                "A self-loathing, alcoholic writer attempts to repair his damaged relationships with his daughter and her mother while combating sex addiction, a budding drug problem, and the seeming inability to avoid making bad decisions.",
                "Comedy", "SHOWTIME", "ENDED", 0, "29.06.2014 - Grace", "Ended", "http://www.imdb.com/title/tt0904208/", 8.4);

        sdb.insertSeries(3,"Breaking Bad", "A chemistry teacher diagnosed with a terminal lung cancer... ",
                "A chemistry teacher diagnosed with a terminal lung cancer, teams up with his former student, Jesse Pinkman, to cook and sell crystal meth.",
                "Action", "AMC", "ENDED", 0, "29.09.2013 - Felina", "Ended", "http://www.imdb.com/title/tt0903747/", 9.5);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new SeriesFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mymenu = menu;
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

        if (id == R.id.action_toMySeries) {
            Intent intentToMySeries = new Intent(this,MySeriesActivity.class);
            startActivity(intentToMySeries);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // The activity is about to become visible.
    }
    @Override
    protected void onResume() {
        super.onResume();
        // The activity has become visible (it is now "resumed").
    }
    @Override
    protected void onPause() {
        super.onPause();
        // Another activity is taking focus (this activity is about to be "paused").
    }
    @Override
    protected void onStop() {
        super.onStop();
        // The activity is no longer visible (it is now "stopped")
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // The activity is about to be destroyed.
    }

}
