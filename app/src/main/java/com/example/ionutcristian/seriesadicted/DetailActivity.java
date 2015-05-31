package com.example.ionutcristian.seriesadicted;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

interface list{
    ArrayList<String> MySeriesList = new ArrayList<>();
}

public class DetailActivity extends ActionBarActivity implements list{

    static String seriesStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    public void sendToMySeries(View v) {
        String seriesTitle = seriesStr.substring(0,seriesStr.indexOf("\n"));
        if(!MySeriesList.contains(seriesTitle))
        {
            MySeriesList.add(seriesTitle);
            Intent intentToMySeries = new Intent(this,MySeriesActivity.class).putExtra(Intent.EXTRA_TEXT, seriesTitle);
            startActivity(intentToMySeries);
        }
        else
        {
            MySeriesList.remove(seriesTitle);
            Intent intentToMySeries = new Intent(this,MySeriesActivity.class);
            startActivity(intentToMySeries);
        }

    }

    public void sendToImdb(View v) {
        String url = "http://www.imdb.com/title/tt0904208/?ref_=nv_sr_1";
        Intent intentToImdb = new Intent(Intent.ACTION_VIEW);
        intentToImdb.setData(Uri.parse(url));
        startActivity(intentToImdb);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
            Intent intent = getActivity().getIntent();
            if(intent != null && intent.hasExtra(Intent.EXTRA_TEXT))
            {
                seriesStr = intent.getStringExtra(Intent.EXTRA_TEXT);
                if(!MySeriesList.contains(seriesStr)) {
                    ((TextView) rootView.findViewById(R.id.detail_text)).setText(seriesStr);
                }
                else {
                    //Toast.makeText(getActivity(), "sad", Toast.LENGTH_SHORT).show();
                    ImageButton bp = (ImageButton) rootView.findViewById(R.id.but);
                    bp.setVisibility(View.GONE);
                    ImageButton br = (ImageButton) rootView.findViewById(R.id.butrem);
                    br.setVisibility(View.VISIBLE);
                    ((TextView) rootView.findViewById(R.id.detail_text)).setText(seriesStr);
                }
            }
            return rootView;

//Intent intent = getActivity().getIntent();
//        if(intent != null && intent.hasExtra(Intent.EXTRA_TEXT))
//        {
//            seriesStr = intent.getStringExtra(Intent.EXTRA_TEXT);
//            ((TextView) findViewById(R.id.detail_text)).setText(seriesStr);
//        }
//
//        if(MySeriesList.contains(seriesStr)) {
//            ImageButton b = (ImageButton) R.layout.fragment_detail.;
//            b.setBackgroundResource(R.drawable.rem);
//        }
//        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        }
    }
}
