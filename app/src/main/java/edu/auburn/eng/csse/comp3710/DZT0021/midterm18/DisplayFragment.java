package edu.auburn.eng.csse.comp3710.DZT0021.midterm18;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by donaldtran on 3/16/18.
 */

public class DisplayFragment extends Fragment {

    protected TextView mHaikuLine1Text;
    protected TextView mHaikuLine2Text;
    protected TextView mHaikuLine3Text;


    private static final String LOG_TAG = DisplayFragment.class.getSimpleName() + "_TAG";

    private static final String P1_WAL = "p1wal";
    private static final String P2_WAL = "p2wal";
    private static final String P3_WAL = "p3wal";

    protected ArrayList<String> p1wal = new ArrayList<>();

    protected ArrayList<String> p2wal = new ArrayList<>();

    protected ArrayList<String> p3wal = new ArrayList<>();


    /*
    public static DisplayFragment newInstance(Bundle haikuBundle) {

        Bundle args = new Bundle();

        ArrayList<String> p1wal = new ArrayList<>();

        ArrayList<String> p2wal = new ArrayList<>();

        ArrayList<String> p3wal = new ArrayList<>();

        args.putStringArrayList(P1_WAL, p1wal);

        args.putStringArrayList(P2_WAL, p2wal);

        args.putStringArrayList(P3_WAL, p3wal);

        DisplayFragment fragment = new DisplayFragment();

        fragment.setArguments(args);

        return fragment;
    }
    */




    // This event fires 1st, before creation of fragment or any views
    // The onAttach method is called when the Fragment instance is associated with an Activity.
    // This does not mean the Activity is fully initialized.
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
        setRetainInstance(true);
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

        //p1wal = getArguments().getStringArrayList("p1wal");

        //p2wal = getArguments().getStringArrayList("p2wal");

        //p3wal = getArguments().getStringArrayList("p3wal");

        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();

        for (String s: p1wal) {
            sb1.append(s);
            sb1.append(" ");
        }

        mHaikuLine1Text.setText(sb1.toString());


        for (String s: p2wal) {
            sb1.append(s);
            sb2.append(" ");
        }

        mHaikuLine2Text.setText(sb2.toString());

        for (String s: p3wal) {
            sb3.append(s);
            sb3.append(" ");
        }

        mHaikuLine3Text.setText(sb3.toString());

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
    }

}


