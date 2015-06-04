package com.example.ionutcristian.seriesadicted;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by IonutCristian on 5/15/2015.
 */
public class SeriesFragment extends Fragment {

    SeriesDataBase sdb;
    ArrayAdapter<String> seriesAdapter = null;

    public SeriesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add this line in order for this fragment to handle menu events.
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivity(new Intent(getActivity(), SettingsActivity.class));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        sdb = new SeriesDataBase(getActivity());
        ArrayList listaTitluri = sdb.getAllTitles();
        ArrayList listaDesc = sdb.getShDesc();
        ArrayList listaSeriale = new ArrayList();
        ArrayList listaCheck = sdb.getCheck();

        for(int i = 0; i < listaTitluri.size(); i++)
            if(listaCheck.get(i).equals(0))
                listaSeriale.add(listaTitluri.get(i) + "\n" + "    " + listaDesc.get(i));

        seriesAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item_series, R.id.list_item_series_textview,
                listaSeriale);

        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.ListViewSeries);
        listView.setAdapter(seriesAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String series = seriesAdapter.getItem(position);
                series = series.substring(0,series.indexOf("\n"));
                Intent sendToDetail = new Intent(getActivity(), DetailActivity.class).putExtra(Intent.EXTRA_TEXT,series);
                startActivity(sendToDetail);
            }

        });
        return rootView;
    }


    protected void onPostExecute(String[] result) {
        if (result != null) {
            seriesAdapter.clear();
            for (String dayForecastStr : result) {
                seriesAdapter.add(dayForecastStr);
            }
        }
    }

}