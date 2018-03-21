package edu.auburn.eng.csse.comp3710.DZT0021.midterm18;


import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.content.res.Configuration;
import android.util.Log;


public class MainHaikuActivity extends AppCompatActivity implements MainMenuFragment.OnDisplayFragmentSelectedListener, FragmentManager.OnBackStackChangedListener {

    private static final String LOG_TAG = MainHaikuActivity.class.getSimpleName() + "_TAG";
    private static final String MAIN_MENU_TAG = MainMenuFragment.class.getSimpleName() + "_TAG";
    private static final String DISPLAY_TAG = DisplayFragment.class.getSimpleName() + "_TAG";


    private Fragment defaultFragment;
    private Fragment previousFragment;
    private Fragment currentFragment;
    private String currentFragmentTag;



    public MainHaikuActivity() {
    }

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.activity_main_haiku);

        Log.d(LOG_TAG, "Activity.onCreate");

        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            else {

                getSupportFragmentManager().addOnBackStackChangedListener(this);

                MainMenuFragment firstFragment = new MainMenuFragment();
                defaultFragment = firstFragment;  // The default fragment

                currentFragmentTag = MAIN_MENU_TAG;
                currentFragment = firstFragment;

                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, currentFragment, currentFragmentTag)
                        .addToBackStack(currentFragmentTag)
                        .commit();

            }

        }

    }


    @Override
    protected void onPause() {
        Log.d(LOG_TAG, "Activity.onPause");
        super.onPause();
    }


    @Override
    protected void onStop() {
        Log.d(LOG_TAG, "Activity.onStop");
        super.onStop();
    }


    @Override
    protected void onDestroy() {
        Log.d(LOG_TAG, "Activity.onDestroy");
        super.onDestroy();

    }


    @Override
    protected void onResume() {
        Log.d(LOG_TAG, "Activity.onResume");
        super.onResume();
    }


    @Override
    protected void onStart() {
        Log.d(LOG_TAG, "Activity.onStart");
        super.onStart();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.d(LOG_TAG, "Activity.onConfigurationChanged");
        super.onConfigurationChanged(newConfig);

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(LOG_TAG, "Activity.onSaveInstanceState");
        super.onSaveInstanceState(outState);
        outState.putBoolean("MAKE_STATE_NOT_NULL", true);

        if (currentFragment instanceof MainMenuFragment) {
            getSupportFragmentManager().putFragment(outState, MAIN_MENU_TAG, currentFragment);
        }
        else if (currentFragment.isAdded()) {
            getSupportFragmentManager().putFragment(outState, DISPLAY_TAG, currentFragment);
        }


    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "Activity.onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState);

        // Pull from saved instance
        Fragment testReturnFragment = getSupportFragmentManager().getFragment(savedInstanceState, MAIN_MENU_TAG);

        // An existing instance?
        Fragment testReturnFragment2 = getSupportFragmentManager().findFragmentByTag(MAIN_MENU_TAG);

        if (testReturnFragment != null) {
            currentFragment = testReturnFragment;
            currentFragmentTag = MAIN_MENU_TAG;
        }
        else if (testReturnFragment2 != null && testReturnFragment2.isAdded()) {
            currentFragment = testReturnFragment2;
            currentFragmentTag = MAIN_MENU_TAG;
        }

    }

    @Override
    public void onDisplayFragmentSelected(Bundle messageToDisplay) {
        DisplayFragment df = new DisplayFragment();

        if (currentFragment instanceof MainMenuFragment) {
            previousFragment = currentFragment;
            currentFragment = df;

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, df, DISPLAY_TAG) // changed from .replace
                    .addToBackStack(DISPLAY_TAG).hide(previousFragment)
                    .commit();

            // This is the message that was sent from MainMenuFragment
            // This needs to go to the display Fragment now
            df.messageFromMainMenuFragment(messageToDisplay);

        }
        else if (currentFragment instanceof DisplayFragment) {
            currentFragment = df;

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, df, DISPLAY_TAG)
                    .addToBackStack(DISPLAY_TAG).hide(previousFragment)
                    .commit();

            currentFragment = previousFragment;
            previousFragment = df;

            df.messageFromMainMenuFragment(messageToDisplay);

        }

    }


    @Override
    public void onBackStackChanged() {
        Log.d(LOG_TAG , "onBackStackChangedListener -> " + getSupportFragmentManager().getBackStackEntryCount());

        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            String fragmentTag = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 1).getName();
            currentFragment = getSupportFragmentManager().findFragmentByTag(fragmentTag);
            currentFragmentTag = fragmentTag;
        } else {
            currentFragment = defaultFragment;
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        }
        else {
            super.onBackPressed();
        }
    }





}
