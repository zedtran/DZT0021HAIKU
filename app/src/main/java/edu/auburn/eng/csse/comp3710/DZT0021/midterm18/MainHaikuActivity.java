package edu.auburn.eng.csse.comp3710.DZT0021.midterm18;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

public class MainHaikuActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainHaikuActivity.class.getSimpleName() + "_TAG";

    private static HaikuModel mHaiku;

    public MainHaikuActivity() {
    }

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.activity_main_haiku);

        Log.i(LOG_TAG, "Activity.onCreate");

        mHaiku = new HaikuModel();



        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
           MainMenuFragment mainMenuFragment = new MainMenuFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            mainMenuFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, mainMenuFragment).commit();
        }




        FragmentManager fm = this.getSupportFragmentManager();

        Fragment fragment = fm.findFragmentById(R.id.fragment_main_menu);

        if(fragment == null) {

            fragment = new MainMenuFragment();

            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }

    }

    protected void onPause() {
        Log.i(LOG_TAG, "Activity.onPause");
        super.onPause();
    }

    protected void onStop() {
        Log.i(LOG_TAG, "Activity.onStop");
        super.onStop();
    }

    protected void onDestroy() {
        Log.i(LOG_TAG, "Activity.onDestroy");
        super.onDestroy();
    }

    protected void onResume() {
        Log.i(LOG_TAG, "Activity.onResume");
        super.onResume();
    }

    protected void onStart() {
        Log.i(LOG_TAG, "Activity.onStart");
        super.onStart();
    }

    public void onConfigurationChanged(Configuration newConfig) {
        Log.i(LOG_TAG, "Activity.onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
    }

    protected void onSaveInstanceState(Bundle outState) {
        Log.i(LOG_TAG, "Activity.onSaveInstanceState");
        super.onSaveInstanceState(outState);
        outState.putBoolean("MAKE_STATE_NOT_NULL", true);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.i(LOG_TAG, "Activity.onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
    }
}
