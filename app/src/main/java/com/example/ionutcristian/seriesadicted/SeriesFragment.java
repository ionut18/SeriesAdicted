package com.example.ionutcristian.seriesadicted;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
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

    public SeriesDataBase db;

    public SeriesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        db = new SeriesDataBase(this);
        ArrayList<String> listaSeriale = new ArrayList<String>();
        listaSeriale.add("Californication \n   A self-loathing, alcoholic writer attempts to repair his...");
        listaSeriale.add("Game of thrones \n   Several noble families fight for control of the mythical...");
        listaSeriale.add("Breaking bad \n   A chemistry teacher diagnosed with a terminal lung cancer...");
        listaSeriale.add("Better call Saul \n   The trials and tribulations of criminal lawyer, Saul Goodman...");
        listaSeriale.add("Dexter \n   A Miami police forensics expert moonlights as a serial killer of criminals...");
        final ArrayAdapter<String> seriesAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item_series, R.id.list_item_series_textview,
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
}