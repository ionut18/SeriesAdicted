package com.example.ionutcristian.seriesadicted;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class MySeriesActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_series);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_series, menu);
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

    protected static ArrayList<String> MySeriesList = new ArrayList<>();

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            Intent intentToMySeries = getActivity().getIntent();

            if(intentToMySeries != null && intentToMySeries.hasExtra(Intent.EXTRA_TEXT))
            {
                String seriesTitle = intentToMySeries.getStringExtra(Intent.EXTRA_TEXT);
                if(!MySeriesList.contains(seriesTitle))
                    MySeriesList.add(seriesTitle);
            }

            ArrayAdapter<String> myListAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item_series,
                    R.id.list_item_series_textview, MySeriesList);
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            ListView mySeriesListView = (ListView) rootView.findViewById(R.id.ListViewSeries);
            mySeriesListView.setAdapter(myListAdapter);

            return rootView;
        }
    }
}
