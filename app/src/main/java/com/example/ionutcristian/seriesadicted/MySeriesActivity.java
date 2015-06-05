package com.example.ionutcristian.seriesadicted;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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

    protected static ArrayList<String> MySeriesList = new ArrayList<>();

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        //private static final String LOG_TAG = DetailActivity.class.getSimpleName();
        public static final String mySeries_hashtag = "#SeriesAddicted";

        public PlaceholderFragment() {
            setHasOptionsMenu(true);
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
                else
                    MySeriesList.remove(seriesTitle);
            }

            final ArrayAdapter<String> myListAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item_series,
                    R.id.list_item_series_textview, MySeriesList);
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            final TextView myTextView = (TextView) rootView.findViewById(R.id.textView);
            myTextView.setText("My Series");
            final ListView mySeriesListView = (ListView) rootView.findViewById(R.id.ListViewSeries);
            mySeriesListView.setAdapter(myListAdapter);
            mySeriesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    String series = myListAdapter.getItem(position);
                    Intent sendToDetail = new Intent(getActivity(), DetailActivity.class).putExtra(Intent.EXTRA_TEXT, series);
                    startActivity(sendToDetail);
                }

            });

            return rootView;
        }

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            // Inflate the menu; this adds items to the action bar if it is present.
            inflater.inflate(R.menu.myseries, menu);

            // Retrieve the share menu item
            MenuItem menuItem = menu.findItem(R.id.action_share);

            // Get the provider and hold onto it to set/change the share intent.
            ShareActionProvider mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);

            // Attach an intent to this ShareActionProvider.  You can update this at any time,
            // like when the user selects a new piece of data they might like to share.
            if (mShareActionProvider != null) {
                mShareActionProvider.setShareIntent(createShareForecastIntent());
            }
        }

        private Intent createShareForecastIntent() {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            shareIntent.setType("text/plain");
            String hello = "Hello. These are my Series that I am watching: \n";
            String str = "";
            for(int i = 0; i < MySeriesList.size(); i++)
                str += MySeriesList.get(i) + "\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, hello + str + mySeries_hashtag);
            return shareIntent;
        }
    }
}
