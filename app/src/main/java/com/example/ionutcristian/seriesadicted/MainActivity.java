package com.example.ionutcristian.seriesadicted;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    private final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(LOG_TAG, "in onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new SeriesFragment())
                    .commit();
        }
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

    /**
     * A placeholder fragment containing a simple view.
     */
    /*public static class SeriesFragment extends Fragment {

        public SeriesFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            ArrayList<String> listaSeriale = new ArrayList<String>();
            listaSeriale.add("Breaking bad");
            listaSeriale.add("Californication");
            listaSeriale.add("Game of thrones");
            ArrayAdapter<String> seriesAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item_series, R.id.list_item_series_textview,
                    listaSeriale);

            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            ListView listView = (ListView) rootView.findViewById(R.id.ListViewSeries);
            listView.setAdapter(seriesAdapter);

            return rootView;
        }
    }*/
}
