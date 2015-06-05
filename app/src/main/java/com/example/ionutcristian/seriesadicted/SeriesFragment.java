package com.example.ionutcristian.seriesadicted;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by IonutCristian on 5/15/2015.
 */
public class SeriesFragment extends Fragment {

    SeriesDataBase sdb;
    static ArrayAdapter<String> seriesAdapter = null;

    public SeriesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add this line in order for this fragment to handle menu events.
        setHasOptionsMenu(true);
    }

//    public void update() {
//        sdb = new SeriesDataBase(getActivity());
//        ArrayList title = sdb.getAllTitles();
//        ArrayList genre = sdb.getGenre();
//        int i;
//        if (SettingsActivity.option.equals("Action"))
//            for (i = 0; i < genre.size(); i++)
//                if (!genre.get(i).toString().equals("Action"))
//                    sdb.updateCheck(title.get(i).toString(), 1);
//        if (SettingsActivity.option.equals("Comedy"))
//            for (i = 0; i < genre.size(); i++)
//                if (!genre.get(i).toString().equals("Comedy"))
//                    sdb.updateCheck(title.get(i).toString(), 1);
//        if (SettingsActivity.option.equals("Drama"))
//            for (i = 0; i < genre.size(); i++)
//                if (!genre.get(i).toString().equals("Drama"))
//                    sdb.updateCheck(title.get(i).toString(), 1);
//        if (SettingsActivity.option.equals("All"))
//            for (i = 0; i < genre.size(); i++)
//                sdb.updateCheck(title.get(i).toString(), 0);
//    }


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

        if(id == R.id.action_refresh)
        {
            startActivity(new Intent(getActivity(), MainActivity.class));
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

    public class UpdateTask extends AsyncTask<Void, Void, Void> {

        private Context mCon;

        public UpdateTask(Context con)
        {
            mCon = con;
        }

        public void update() {
            SharedPreferences sharedPrefs =
                    PreferenceManager.getDefaultSharedPreferences(getActivity());
            String type = sharedPrefs.getString(
                    getString(R.string.pref_genre_key),
                    "All");

            if(type.equals("Action")) {
                Toast.makeText(mCon, "Action", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected Void doInBackground(Void... nope) {
            try {

                startActivity(new Intent(getActivity(),SeriesFragment.class));

                return null;

            } catch (Exception e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(Void nope) {
            // Give some feedback on the UI.
            Toast.makeText(mCon, "Finished complex background function!",
                    Toast.LENGTH_LONG).show();

        }
    }
}