package com.umk.register.menu.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;

import com.onesignal.OneSignal;
import com.umk.register.R;
import com.umk.register.app.ApplicationClass;

public class SettingsActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "myprefs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Ustawienia");
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    public static class SettingsFragment extends PreferenceFragmentCompat {


        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);

            SwitchPreferenceCompat notifications = findPreference("notifications");
            if (notifications !=null){
                notifications.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference preference, Object newValue) {
                        boolean areNotificationsOn = (Boolean)newValue;
                        if (areNotificationsOn){
                            OneSignal.setSubscription(true);
                            OneSignal.startInit(getContext()).init();
                        }
                        if(!areNotificationsOn){
                            OneSignal.setSubscription(false);
                            OneSignal.startInit(getContext()).init();
                        }
                        return true;
                    }
                });
            }


            SharedPreferences sharedPreferences;
            sharedPreferences = ApplicationClass.getAppContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            Boolean parent = sharedPreferences.getBoolean("parent",false);
            String moreThanOneStudent = sharedPreferences.getString("moreThanOneStudent","");

            Preference preference = findPreference("changeStudent");
            PreferenceCategory preferenceCategory = findPreference("categoryPrefs");

            if (preference != null){
                if(!parent || moreThanOneStudent.contains("ok")){
                    preferenceCategory.removePreference(preference);
                }
            }

        }

    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}