package edu.auburn.eng.csse.comp3710.DZT0021.midterm18;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.res.Configuration;
import android.util.Log;

public class MainHaikuActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainHaikuActivity.class.getSimpleName() + "_TAG";

    public MainHaikuActivity() {
    }

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.activity_main_haiku);

        Log.d(LOG_TAG, "Activity.onCreate");

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

    }

    protected void onPause() {
        Log.d(LOG_TAG, "Activity.onPause");
        super.onPause();
    }

    protected void onStop() {
        Log.d(LOG_TAG, "Activity.onStop");
        super.onStop();
    }

    protected void onDestroy() {
        Log.d(LOG_TAG, "Activity.onDestroy");
        super.onDestroy();
    }

    protected void onResume() {
        Log.d(LOG_TAG, "Activity.onResume");
        super.onResume();
    }

    protected void onStart() {
        Log.d(LOG_TAG, "Activity.onStart");
        super.onStart();
    }

    public void onConfigurationChanged(Configuration newConfig) {
        Log.d(LOG_TAG, "Activity.onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
    }

    protected void onSaveInstanceState(Bundle outState) {
        Log.d(LOG_TAG, "Activity.onSaveInstanceState");
        super.onSaveInstanceState(outState);
        outState.putBoolean("MAKE_STATE_NOT_NULL", true);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "Activity.onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
    }
}
