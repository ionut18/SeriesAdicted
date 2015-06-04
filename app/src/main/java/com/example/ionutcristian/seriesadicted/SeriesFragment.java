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

    SeriesDataBase sdb;

    public SeriesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        sdb = new SeriesDataBase(getActivity());
        ArrayList listaTitluri = sdb.getAllTitles();
        ArrayList listaDesc = sdb.getShDesc();
        ArrayList listaSeriale = new ArrayList();
        for(int i = 0; i < listaTitluri.size(); i++)
            listaSeriale.add(listaTitluri.get(i) + "\n" + "    " + listaDesc.get(i));

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