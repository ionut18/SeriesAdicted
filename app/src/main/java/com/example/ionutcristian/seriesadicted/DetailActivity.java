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
        String url;
        String first_Word = seriesStr.substring(0,seriesStr.indexOf(' '));
        Intent intentToImdb = new Intent(Intent.ACTION_VIEW);
        switch (first_Word){
            case "Californication":
                url = "http://www.imdb.com/title/tt0904208/?ref_=nv_sr_1";
                intentToImdb.setData(Uri.parse(url));
                startActivity(intentToImdb);
                break;
            case "Breaking":
                url = "http://www.imdb.com/title/tt0903747/?ref_=nv_sr_1";
                intentToImdb.setData(Uri.parse(url));
                startActivity(intentToImdb);
                break;
            case "Game":
                url = "http://www.imdb.com/title/tt0944947/?ref_=nv_sr_1";
                intentToImdb.setData(Uri.parse(url));
                startActivity(intentToImdb);
                break;
            case "Better":
                url = "http://www.imdb.com/title/tt3032476/?ref_=nv_sr_1";
                intentToImdb.setData(Uri.parse(url));
                startActivity(intentToImdb);
                break;
            case "Dexter":
                url = "http://www.imdb.com/title/tt0773262/?ref_=nv_sr_1";
                intentToImdb.setData(Uri.parse(url));
                startActivity(intentToImdb);
                break;
            default:
                break;
        }

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
                String seriesTitle = seriesStr;//.substring(0,seriesStr.indexOf("\n"));

                if(!MySeriesList.contains(seriesTitle)) {
                    ((TextView) rootView.findViewById(R.id.detail_text)).setText(seriesStr + "\n" + "\n");
                }
                else {
                    ImageButton bp = (ImageButton) rootView.findViewById(R.id.but);
                    bp.setVisibility(View.GONE);
                    ImageButton br = (ImageButton) rootView.findViewById(R.id.butrem);
                    br.setVisibility(View.VISIBLE);
                    ((TextView) rootView.findViewById(R.id.detail_text)).setText(seriesStr + "\n" + "\n");
                }
            }

            String first_Word = seriesStr.substring(0,seriesStr.indexOf(' '));
            switch (first_Word){
                case "Californication":
                    ((TextView) rootView.findViewById(R.id.detail_text)).append("  A self-loathing, alcoholic writer attempts to repair his damaged relationships with his daughter and her mother while combating sex addiction, a budding drug problem, and the seeming inability to avoid making bad decisions.");
                    break;
                case "Breaking":
                    ((TextView) rootView.findViewById(R.id.detail_text)).append("  A chemistry teacher diagnosed with a terminal lung cancer, teams up with his former student, Jesse Pinkman, to cook and sell crystal meth.");
                    break;
                case "Game":
                    ((TextView) rootView.findViewById(R.id.detail_text)).append("  Several noble families fight for control of the mythical land of Westeros.");
                    break;
                case "Better":
                    ((TextView) rootView.findViewById(R.id.detail_text)).append("  The trials and tribulations of criminal lawyer, Saul Goodman, in the time leading up to establishing his strip-mall law office in Albuquerque, New Mexico.");
                    break;
                case "Dexter":
                    ((TextView) rootView.findViewById(R.id.detail_text)).append("  A Miami police forensics expert moonlights as a serial killer of criminals whom he believes have escaped justice.");
                    break;
                default:
                    break;
            }

            return rootView;

        }
    }
}
