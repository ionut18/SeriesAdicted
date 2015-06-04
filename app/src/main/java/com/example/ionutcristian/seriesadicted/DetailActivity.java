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

    SeriesDataBase sdb;
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
        String seriesTitle = seriesStr;
        if(!MySeriesList.contains(seriesTitle))
        {
            MySeriesList.add(seriesTitle);
            Intent intentToMySeries = new Intent(this,MySeriesActivity.class).putExtra(Intent.EXTRA_TEXT, seriesTitle);
            startActivity(intentToMySeries);
        }
        else
        {
            MySeriesList.remove(seriesTitle);
            Intent intentToMySeries = new Intent(this,MySeriesActivity.class).putExtra(Intent.EXTRA_TEXT, seriesTitle);
            startActivity(intentToMySeries);
        }

    }

    public void sendToImdb(View v) {
        Intent intentToImdb = new Intent(Intent.ACTION_VIEW);
        String url = null;
        sdb = new SeriesDataBase(this);
        ArrayList listaTitluri = sdb.getAllTitles();
        ArrayList listImdb = sdb.getImdb();
        for(int i = 0; i < listaTitluri.size(); i++)
            if(listaTitluri.get(i).toString().equals(seriesStr)) {
                url = listImdb.get(i).toString();
                intentToImdb.setData(Uri.parse(url));
                startActivity(intentToImdb);
            }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        SeriesDataBase sdb;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            sdb = new SeriesDataBase(getActivity());
            ArrayList listaTitluri = sdb.getAllTitles();
            ArrayList desc = sdb.getLgDesc();
            ArrayList genre = sdb.getGenre();
            ArrayList lastep = sdb.getLastEp();
            ArrayList nextep = sdb.getNextEp();
            ArrayList company = sdb.getCompany();
            ArrayList grade = sdb.getGrade();

            View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
            Intent intent = getActivity().getIntent();
            if(intent != null && intent.hasExtra(Intent.EXTRA_TEXT))
            {
                seriesStr = intent.getStringExtra(Intent.EXTRA_TEXT);
                String seriesTitle = seriesStr;//.substring(0,seriesStr.indexOf("\n"));

                if(!MySeriesList.contains(seriesTitle)) {
                    ((TextView) rootView.findViewById(R.id.detail_text)).setText(seriesStr + "\n" + "\n");
                }
                else {
                    ImageButton bp = (ImageButton) rootView.findViewById(R.id.but);
                    bp.setVisibility(View.GONE);
                    ImageButton br = (ImageButton) rootView.findViewById(R.id.butrem);
                    br.setVisibility(View.VISIBLE);
                    ((TextView) rootView.findViewById(R.id.detail_text)).setText(seriesStr + "\n\n");
                }
            }

            for(int i = 0; i < listaTitluri.size(); i++)
                if(listaTitluri.get(i).toString().equals(seriesStr))
                    ((TextView) rootView.findViewById(R.id.detail_text)).append("   " + desc.get(i).toString() + "\n\n" + "   Company : " + company.get(i).toString()
                        + "\n\n" + "   Genre : " + genre.get(i).toString() + "\n\n" + "   Last Episode : " + lastep.get(i).toString()
                            + "\n\n" + "   Next Episode : " + nextep.get(i).toString() + "\n\n" + "   Grade : " + grade.get(i).toString());

            return rootView;

        }
    }
}
