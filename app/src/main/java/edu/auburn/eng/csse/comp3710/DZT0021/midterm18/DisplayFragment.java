package edu.auburn.eng.csse.comp3710.DZT0021.midterm18;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.util.Log;
import android.view.KeyEvent;



/**
 * Created by donaldtran on 3/16/18.
 */

public class DisplayFragment extends Fragment {



    protected TextView mHaikuLine1Text;
    protected TextView mHaikuLine2Text;
    protected TextView mHaikuLine3Text;


    private static final String LOG_TAG = DisplayFragment.class.getSimpleName() + "_TAG";

    private static final String P1 = "p1_string";
    private static final String P2 = "p2_string";
    private static final String P3 = "p3_string";


    // This is a public method that the Activity can use to communicate
    // directly with this Fragment
    public void messageFromMainMenuFragment(Bundle message) {

        String p1 = message.getString("p1_string");

        String p2 = message.getString("p2_string");

        String p3 = message.getString("p3_string");

        mHaikuLine1Text.setText(p1);

        mHaikuLine2Text.setText(p2);

        mHaikuLine3Text.setText(p3);
    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(LOG_TAG, "DisplayFragment.onSaveInstanceState");
        outState.putString("phrase1", mHaikuLine1Text.getText().toString());
        outState.putString("phrase2", mHaikuLine2Text.getText().toString());
        outState.putString("phrase3", mHaikuLine3Text.getText().toString());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(LOG_TAG, "DisplayFragment.onAttach");

    }

    // This event fires 2nd, before views are created for the fragment
    // The onCreate method is called when the Fragment instance is being created, or re-created.
    // Use onCreate for any standard setup that does not require the activity to be fully created
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRetainInstance(true);
        Log.d(LOG_TAG, "DisplayFragment.onCreate");

    }

    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(LOG_TAG, "DisplayFragment.onCreateView");

        View view = inflater.inflate(R.layout.fragment_display_haiku, container, false);

        mHaikuLine1Text = (TextView) view.findViewById(R.id.display_haiku_line1);
        mHaikuLine2Text = (TextView) view.findViewById(R.id.display_haiku_line2);
        mHaikuLine3Text = (TextView) view.findViewById(R.id.display_haiku_line3);

        return view;
    }

    // This event is triggered soon after onCreateView().
    // onViewCreated() is only called if the view returned from onCreateView() is non-null.
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(LOG_TAG, "DisplayFragment.onViewCreated");
    }

    // This method is called when the fragment is no longer connected to the Activity
    // Any references saved in onAttach should be nulled out here to prevent memory leaks.
    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(LOG_TAG, "DisplayFragment.onDetach");
        mHaikuLine1Text.setText(null);
        mHaikuLine2Text.setText(null);
        mHaikuLine3Text.setText(null);

    }

    // This method is called after the parent Activity's onCreate() method has completed.
    // Accessing the view hierarchy of the parent activity must be done in the onActivityCreated.
    // At this point, it is safe to search for activity View objects by their ID, for example.
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(LOG_TAG, "DisplayFragment.onActivityCreated");

        if (savedInstanceState != null) {
            String s1 = savedInstanceState.getString("phrase1");
            String s2 = savedInstanceState.getString("phrase2");
            String s3 = savedInstanceState.getString("phrase3");

            mHaikuLine1Text.setText(s1);
            mHaikuLine2Text.setText(s2);
            mHaikuLine3Text.setText(s3);
        }

    }


}


