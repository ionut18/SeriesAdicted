package com.example.ionutcristian.seriesadicted;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

import java.util.ArrayList;

/**
 * Created by IonutCristian on 6/4/2015.
 */
public class SettingsActivity extends PreferenceActivity
        implements Preference.OnPreferenceChangeListener {

    SeriesDataBase sdb;

    static public String option = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_general);

        bindPreferenceSummaryToValue(findPreference(getString(R.string.pref_genre_key)));

        update(findPreference(getString(R.string.pref_genre_key)));

    }

    private void bindPreferenceSummaryToValue(Preference preference) {
        // Set the listener to watch for value changes.
        preference.setOnPreferenceChangeListener(this);

        // Trigger the listener immediately with the preference's
        // current value.

        onPreferenceChange(preference, PreferenceManager
                .getDefaultSharedPreferences(preference.getContext())
                .getString(preference.getKey(), ""));

//
        option = PreferenceManager
                .getDefaultSharedPreferences(preference.getContext())
                .getString(preference.getKey(), "");
//        Toast.makeText(this, PreferenceManager
//                .getDefaultSharedPreferences(preference.getContext())
//                .getString(preference.getKey(), ""), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object value) {
        String stringValue = value.toString();

        if (preference instanceof ListPreference) {
            // For list preferences, look up the correct display value in
            // the preference's 'entries' list (since they have separate labels/values).
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(stringValue);

            if (prefIndex >= 0) {
                preference.setSummary(listPreference.getEntries()[prefIndex]);
            }

        }
        else {
            // For other preferences, set the summary to the value's simple string representation.
            preference.setSummary(stringValue);
        }
        return true;
    }

    public void update(Preference preference) {

        sdb = new SeriesDataBase(this);
        ArrayList title = sdb.getAllTitles();
        ArrayList genre = sdb.getGenre();
        ArrayList check = sdb.getCheck();
        int i;
        if (preference instanceof ListPreference) {
            ListPreference listPreference = (ListPreference) preference;
            if (listPreference.getValue().equals("Action"))
                for (i = 0; i < genre.size(); i++)
                    if (!genre.get(i).toString().equals("Action"))
                        sdb.updateCheck(title.get(i).toString(), 1);
            if (listPreference.getValue().equals("Comedy"))
                for (i = 0; i < genre.size(); i++)
                    if (!genre.get(i).toString().equals("Comedy"))
                        sdb.updateCheck(title.get(i).toString(), 1);
            if (listPreference.getValue().equals("Drama"))
                for (i = 0; i < genre.size(); i++)
                    if (!genre.get(i).toString().equals("Drama"))
                        sdb.updateCheck(title.get(i).toString(), 1);
            if (listPreference.getValue().equals("All"))
                for (i = 0; i < genre.size(); i++)
                    sdb.updateCheck(title.get(i).toString(), 0);

        }
    }


}
