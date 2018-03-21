package edu.auburn.eng.csse.comp3710.DZT0021.midterm18;

/**
 * Created by donaldtran on 3/15/18.
 */

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.NoSuchElementException;





public class MainMenuFragment extends Fragment {
    protected RadioButton mAdjectivesButton;
    protected RadioButton mNounsButton;
    protected RadioButton mVerbsButton;
    protected RadioButton mOthersButton;
    protected Spinner mSpinner;
    protected Button mAddToHaikuButton;
    protected TextView mHaikuLine1;
    protected TextView mHaikuLine2;
    protected TextView mHaikuLine3;
    protected Button mDeleteLastWordButton;
    protected Button mStartOverButton;
    protected Button mDisplayHaikuButton;
    protected Toast mToast;
    protected HaikuModel mHaiku;
    protected static final String LOG_TAG = MainMenuFragment.class.getSimpleName() + "_TAG";
    private int lastKnownSyllableCount = -1;

    // Interface Callback listener
    private OnDisplayFragmentSelectedListener mCallback;

    // Container Activity must implement this interface
    public interface OnDisplayFragmentSelectedListener {
        void onDisplayFragmentSelected(Bundle messageToDisplay);
    }


    @Override
    public void onResume() {
        super.onResume();


    }


    // This event fires 2nd, before views are created for the fragment
    // The onCreate method is called when the Fragment instance is being created, or re-created.
    // Use onCreate for any standard setup that does not require the activity to be fully created
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        Log.d(LOG_TAG, "MainMenuFragment.onCreate");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(LOG_TAG, "MainMenuFragment.onCreateView");

        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);

        // Create Model
        mHaiku = new HaikuModel();

        // Assign controls for xml views
        mAdjectivesButton = (RadioButton) view.findViewById(R.id.adjectives_radio_button);
        mNounsButton = (RadioButton) view.findViewById(R.id.nouns_radio_button);
        mVerbsButton = (RadioButton) view.findViewById(R.id.verbs_radio_button);
        mOthersButton = (RadioButton) view.findViewById(R.id.others_radio_button);
        mSpinner = (Spinner) view.findViewById(R.id.spinner);
        mAddToHaikuButton = (Button) view.findViewById(R.id.add_to_haiku_button);
        mHaikuLine1 = (TextView) view.findViewById(R.id.haiku_line1);
        mHaikuLine2 = (TextView) view.findViewById(R.id.haiku_line2);
        mHaikuLine3 = (TextView) view.findViewById(R.id.haiku_line3);
        mDeleteLastWordButton = (Button) view.findViewById(R.id.delete_last_button);
        mStartOverButton = (Button) view.findViewById(R.id.start_over_button);
        mDisplayHaikuButton = (Button) view.findViewById(R.id.display_button);


        if(savedInstanceState != null) {

            int resIDtoResize = -1; // The appropriate resource ID

            boolean spinnerStatus = savedInstanceState.getBoolean("SpinnerStatus");
            boolean wordsExist = savedInstanceState.getBoolean("WordsExist");
            boolean adderStatus = savedInstanceState.getBoolean("AdderStatus");
            boolean verbsButtonStatus = savedInstanceState.getBoolean("verbsIsChecked");
            boolean nounsButtonStatus = savedInstanceState.getBoolean("nounsIsChecked");
            boolean adjectivesButtonStatus = savedInstanceState.getBoolean("adjectivesIsChecked");
            boolean othersButtonStatus = savedInstanceState.getBoolean("othersIsChecked");
            int lastSyllableCount = savedInstanceState.getInt("lastKnownSyllableCount");

            if(verbsButtonStatus) {
                radioButtonEvents(R.array.verbs);
                mVerbsButton.setChecked(true);
                resIDtoResize = R.array.verbs;
            }
            else if(nounsButtonStatus) {
                radioButtonEvents(R.array.nouns);
                mNounsButton.setChecked(true);
                resIDtoResize = R.array.nouns;
            }
            else if(adjectivesButtonStatus) {
                radioButtonEvents(R.array.adjectives);
                mAdjectivesButton.setChecked(true);
                resIDtoResize = R.array.adjectives;
            }
            else if(othersButtonStatus) {
                radioButtonEvents(R.array.other);
                mOthersButton.setChecked(true);
                resIDtoResize = R.array.other;
            }


            if(spinnerStatus) {
                mSpinner.setEnabled(true);
                mSpinner.setVisibility(View.VISIBLE);
            }

            if(adderStatus) {
                mAddToHaikuButton.setEnabled(true);
                mAddToHaikuButton.setVisibility(View.VISIBLE);
            } else {
                mAddToHaikuButton.setVisibility(View.INVISIBLE);
            }

            if(wordsExist) {
                mDeleteLastWordButton.setEnabled(true);
                mStartOverButton.setEnabled(true);
                mDisplayHaikuButton.setEnabled(true);
                mDeleteLastWordButton.setVisibility(View.VISIBLE);
                mStartOverButton.setVisibility(View.VISIBLE);
                mDisplayHaikuButton.setVisibility(View.VISIBLE);
                mAddToHaikuButton.setText(R.string.add_to_haiku_button_text);
            }

            ArrayList<String> p1wal = savedInstanceState.getStringArrayList("p1wal");
            ArrayList<Integer> p1cal = savedInstanceState.getIntegerArrayList("p1cal");
            ArrayList<String> p2wal = savedInstanceState.getStringArrayList("p2wal");
            ArrayList<Integer> p2cal = savedInstanceState.getIntegerArrayList("p2cal");
            ArrayList<String> p3wal = savedInstanceState.getStringArrayList("p3wal");
            ArrayList<Integer> p3cal = savedInstanceState.getIntegerArrayList("p3cal");

            List<Pair<String, Integer>> p1 = new ArrayList<>();
            List<Pair<String, Integer>> p2 = new ArrayList<>();
            List<Pair<String, Integer>> p3 = new ArrayList<>();

            Pair<String, Integer> pair;

            if(p1wal != null && p1cal != null) {
                for(int i = 0; i < p1wal.size(); i++) {
                    pair = new Pair<>(p1wal.get(i), p1cal.get(i));
                    p1.add(i, pair);
                }

                mHaiku.setPhrase1pair(p1);
                mHaikuLine1.setText(mHaiku.getPhraseOneAsString());
            }

            if(p2wal != null && p2cal != null) {
                for(int i = 0; i < p2wal.size(); i++) {
                    pair = new Pair<>(p2wal.get(i), p2cal.get(i));
                    p2.add(i, pair);
                }

                mHaiku.setPhrase2pair(p2);
                mHaikuLine2.setText(mHaiku.getPhraseTwoAsString());
            }

            if(p3wal != null && p3cal != null) {
                for(int i = 0; i < p3wal.size(); i++) {
                    pair = new Pair<>(p3wal.get(i), p3cal.get(i));
                    p3.add(i, pair);
                }

                mHaiku.setPhrase3pair(p3);
                mHaikuLine3.setText(mHaiku.getPhraseThreeAsString());
            }

            if(mHaiku.getphraseThreeSyllableCount() + lastSyllableCount > 5 && mHaiku.getphraseThreeSyllableCount() > 0 && resIDtoResize != -1) {
                radioButtonEvents(resIDtoResize);
            } else if(mHaiku.getphraseTwoSyllableCount() + lastSyllableCount > 7 && mHaiku.getphraseTwoSyllableCount() > 0 && resIDtoResize != -1) {
                radioButtonEvents(resIDtoResize);
            } else if(mHaiku.getphraseOneSyllableCount() + lastSyllableCount > 5 && mHaiku.getphraseOneSyllableCount() > 0 && resIDtoResize != -1) {
                radioButtonEvents(resIDtoResize);
            }

        }




        //////////////////////////
        // Begin Event Handlers //
        //////////////////////////
        mStartOverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainMenuFragment fragment = (MainMenuFragment) getFragmentManager().findFragmentById(R.id.fragment_container);
                getFragmentManager().beginTransaction().detach(fragment).attach(fragment).commit();

                if(mOthersButton.isChecked()) {
                    mOthersButton.setChecked(false);
                }
                else if(mAdjectivesButton.isChecked()) {
                    mAdjectivesButton.setChecked(false);
                }
                else if(mVerbsButton.isChecked()) {
                    mVerbsButton.setChecked(false);
                }
                else if(mNounsButton.isChecked()) {
                    mNounsButton.setChecked(false);
                }

                mHaiku.reset();
            }
        });

        mDeleteLastWordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String item;
                String text;

                // Starting delete from phrase 3
                if(mHaiku.getphraseThreeSyllableCount() <= 5 && mHaiku.getphraseThreeSyllableCount() > 0) {

                    // last word in phrase 3
                    item = mHaiku.getPhraseThreeAsList().get(mHaiku.getPhraseThreeSize() - 1);

                    // if remove successful
                    if(item != null && mHaiku.removeFromPhraseThree()) {

                        // Toast success
                        mHaikuLine3.setText(mHaiku.getPhraseThreeAsString());
                        text = "'" + item.toUpperCase() + "' removed!";
                        makeToast(text);

                        // If we made room in phrase 3 to add back to our Haiku,
                        // (1) Make the spinner and add button available
                        // (2) Refresh the dropdown list and resize to only words with appropriate syllable count
                        if(mHaiku.getphraseThreeSyllableCount() < 5) {

                            if(!mSpinner.isClickable() || !mSpinner.isEnabled()) {
                                mSpinner.setEnabled(true);
                                mSpinner.setClickable(true);
                            }
                            if (!mAddToHaikuButton.isClickable() || !mAddToHaikuButton.isEnabled()) {
                                mAddToHaikuButton.setClickable(true);
                                mAddToHaikuButton.setEnabled(true);
                            }

                            if (mVerbsButton.isChecked()) {
                                radioButtonEvents(R.array.verbs);
                            }
                            else if(mNounsButton.isChecked()) {
                                radioButtonEvents(R.array.nouns);
                            }
                            else if(mOthersButton.isChecked()) {
                                radioButtonEvents(R.array.other);
                            }
                            else if(mAdjectivesButton.isChecked()) {
                                radioButtonEvents(R.array.adjectives);
                            }

                        }

                    }
                }
                // Evaluating phrase 2 to see if it has words to delete
                else if(mHaiku.getphraseTwoSyllableCount() <= 7 && mHaiku.getphraseTwoSyllableCount() > 0) {

                    // last word in phrase 2
                    item = mHaiku.getPhraseTwoAsList().get(mHaiku.getPhraseTwoSize() - 1);

                    // If remove was success, show toast msg.
                    if(item != null && mHaiku.removeFromPhraseTwo()) {

                        mHaikuLine2.setText(mHaiku.getPhraseTwoAsString());
                        text = "'" + item.toUpperCase() + "' removed!";
                        makeToast(text);
                    }
                }
                // Evaluating phrase 1 to see if it has words to delete
                else if(mHaiku.getphraseOneSyllableCount() <= 5 && mHaiku.getphraseOneSyllableCount() > 0) {

                    // last word in phrase 1
                    item = mHaiku.getPhraseOneAsList().get(mHaiku.getPhraseOneSize() - 1);

                    // If remove was successful, show toast message.
                    if(item != null && mHaiku.removeFromPhraseOne()) {

                        mHaikuLine1.setText(mHaiku.getPhraseOneAsString());
                        text = "'" + item.toUpperCase() + "' removed!";
                        makeToast(text);

                        // If we removed the last word, we don't need these buttons anymore (modify as appropriate)
                        if (mHaiku.getPhraseOneSize() == 0) {
                            mDeleteLastWordButton.setEnabled(false);
                            mStartOverButton.setEnabled(false);
                            mDisplayHaikuButton.setEnabled(false);
                            mSpinner.setEnabled(true);
                            mAddToHaikuButton.setEnabled(true);
                        }
                    }
                }
                else {
                    item = "ERROR: NOTHING TO REMOVE!";
                    makeToast(item);
                }

            }
        });

        mDisplayHaikuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // Pass the message to our host activity for the Haiku we want to display
                Bundle messageToDisplay = new Bundle();
                messageToDisplay.putString("p1_string", mHaiku.getPhraseOneAsString());
                messageToDisplay.putString("p2_string", mHaiku.getPhraseTwoAsString());
                messageToDisplay.putString("p3_string", mHaiku.getPhraseThreeAsString());


                // Callback to our MainHaikuActivity
                mCallback.onDisplayFragmentSelected(messageToDisplay);

            }
        });

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                CharSequence newAddButtonText = "ADD '" + mSpinner.getSelectedItem().toString().toUpperCase() + "' TO THE HAIKU";
                mAddToHaikuButton.setText(newAddButtonText);

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                mAddToHaikuButton.setVisibility(View.INVISIBLE);
            }
        });
        // If this button was clicked, deselect the last radio button and perform
        // radio button events
        mAdjectivesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mVerbsButton.isChecked()) {
                    mVerbsButton.setChecked(false);
                }
                if (mNounsButton.isChecked()) {
                    mNounsButton.setChecked(false);
                }
                if (mOthersButton.isChecked()) {
                    mOthersButton.setChecked(false);
                }

                radioButtonEvents(R.array.adjectives);

            }
        });

        // If this button was clicked, deselect the last radio button and perform
        // radio button events
        mNounsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mVerbsButton.isChecked()) {
                    mVerbsButton.setChecked(false);
                }
                if (mAdjectivesButton.isChecked()) {
                    mAdjectivesButton.setChecked(false);
                }
                if (mOthersButton.isChecked()) {
                    mOthersButton.setChecked(false);
                }

                radioButtonEvents(R.array.nouns);

            }
        });

        // If this button was clicked, deselect the last radio button and perform
        // radio button events
        mVerbsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mNounsButton.isChecked()) {
                    mNounsButton.setChecked(false);
                }
                if (mAdjectivesButton.isChecked()) {
                    mAdjectivesButton.setChecked(false);
                }
                if (mOthersButton.isChecked()) {
                    mOthersButton.setChecked(false);
                }

                radioButtonEvents(R.array.verbs);

            }
        });

        // If this button was clicked, deselect the last radio button and perform
        // radio button events
        mOthersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mNounsButton.isChecked()) {
                    mNounsButton.setChecked(false);
                }
                if (mAdjectivesButton.isChecked()) {
                    mAdjectivesButton.setChecked(false);
                }
                if (mVerbsButton.isChecked()) {
                    mVerbsButton.setChecked(false);
                }

                radioButtonEvents(R.array.other);

            }
        });


        mAddToHaikuButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // The resource ID we will use -- default to -1
                int resID = -1;

                // The resource array as reference
                String[] arrayReference;

                String selectedWord = null;

                // The syllable count for the selected word
                int selectionSyllableCount = 0;

                // The word we chose to add for comparing against resource
                String crossReference = mSpinner.getSelectedItem().toString();

                // The resource being utilized
                if (mNounsButton.isChecked()) {

                    resID = R.array.nouns;
                }
                else if (mVerbsButton.isChecked()) {

                    resID = R.array.verbs;
                }
                else if (mAdjectivesButton.isChecked()) {

                    resID = R.array.adjectives;
                }
                else if (mOthersButton.isChecked()) {

                    resID = R.array.other;
                }

                arrayReference = getResources().getStringArray(resID);

                // Parsing out to the get the int value from our reference
                for (String s: arrayReference) {
                    if (s.substring(1, s.length()).equals(crossReference)) {
                        selectedWord = s;
                        selectionSyllableCount = Integer.parseInt(s.substring(0, 1));
                        break;
                    }
                }


                // Starting with phrase 1 to 3
                if(mHaiku.getphraseOneSyllableCount() < 5 ) {

                    // proceed to add the selected word
                    addToHaikuButtonEvents(selectedWord, selectionSyllableCount, arrayReference, resID, crossReference);

                }
                else if (mHaiku.getphraseTwoSyllableCount() < 7) {

                    // proceed to add the selected word
                    addToHaikuButtonEvents(selectedWord, selectionSyllableCount, arrayReference, resID, crossReference);

                }
                else if (mHaiku.getphraseThreeSyllableCount() < 5) {

                    // proceed to add the selected word
                    addToHaikuButtonEvents(selectedWord, selectionSyllableCount, arrayReference, resID, crossReference);

                }
            }
        });

        // The fragment to return
        return view;
    }

    // This event is triggered soon after onCreateView().
    // onViewCreated() is only called if the view returned from onCreateView() is non-null.
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d(LOG_TAG, "MainMenuFragment.onViewCreated");

    }

    // This event fires 1st, before creation of fragment or any views
    // The onAttach method is called when the Fragment instance is associated with an Activity.
    // This does not mean the Activity is fully initialized.
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Log.d(LOG_TAG, "MainMenuFragment.onAttach");

        if (context instanceof OnDisplayFragmentSelectedListener) {
            mCallback = (OnDisplayFragmentSelectedListener) context;
        }
        else {
            throw new RuntimeException(context.toString() + " must implement OnDisplayFragmentSelectedListener");
        }

    }





    // This method is called when the fragment is no longer connected to the Activity
    // Any references saved in onAttach should be nulled out here to prevent memory leaks.
    @Override
    public void onDetach() {
        super.onDetach();

        Log.d(LOG_TAG, "MainMenuFragment.onDetach");

        /*

        mNounsButton.setOnClickListener(null);
        mVerbsButton.setOnClickListener(null);
        mAdjectivesButton.setOnClickListener(null);
        mOthersButton.setOnClickListener(null);
        mSpinner.setOnItemSelectedListener(null);
        mAddToHaikuButton.setOnClickListener(null);
        mDisplayHaikuButton.setOnClickListener(null);
        mDeleteLastWordButton.setOnClickListener(null);
        mStartOverButton.setOnClickListener(null);
        mDisplayHaikuButton.setOnClickListener(null);
        mCallback = null;

        */

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(LOG_TAG, "MainMenuFragment.onActivityCreated");


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.d(LOG_TAG, "MainMenuFragment.onSaveInstanceState");

        getActivity().getSupportFragmentManager().putFragment(outState, LOG_TAG, this);

        List<Pair<String, Integer>> first = mHaiku.getP1Pair();
        List<Pair<String, Integer>> second = mHaiku.getP2Pair();
        List<Pair<String, Integer>> third = mHaiku.getP3Pair();

        ArrayList<String> p1wordArrList;
        ArrayList<Integer> p1countArrList;
        ArrayList<String> p2wordArrList;
        ArrayList<Integer> p2countArrList;
        ArrayList<String> p3wordArrList;
        ArrayList<Integer> p3countArrList;

        if(first.size() > 0) {
            p1wordArrList = new ArrayList<>();
            p1countArrList = new ArrayList<>();

            for (Pair<String, Integer> p: first) {
                p1wordArrList.add(p.first);
                p1countArrList.add(p.second);
            }

            outState.putStringArrayList("p1wal", p1wordArrList);
            outState.putIntegerArrayList("p1cal", p1countArrList);
        }

        if(second.size() > 0) {
            p2wordArrList = new ArrayList<>();
            p2countArrList = new ArrayList<>();

            for (Pair<String, Integer> p: second) {
                p2wordArrList.add(p.first);
                p2countArrList.add(p.second);
            }
            outState.putStringArrayList("p2wal", p2wordArrList);
            outState.putIntegerArrayList("p2cal", p2countArrList);
        }

        if(third.size() > 0) {
            p3wordArrList = new ArrayList<>();
            p3countArrList = new ArrayList<>();

            for (Pair<String, Integer> p: third) {
                p3wordArrList.add(p.first);
                p3countArrList.add(p.second);
            }

            outState.putStringArrayList("p3wal", p3wordArrList);
            outState.putIntegerArrayList("p3cal", p3countArrList);
        }

        outState.putBoolean("SpinnerStatus", mSpinner.isEnabled());
        outState.putBoolean("AdderStatus", mAddToHaikuButton.isEnabled());
        outState.putBoolean("WordsExist", first.size() > 0);
        outState.putBoolean("verbsIsChecked", mVerbsButton.isChecked());
        outState.putBoolean("nounsIsChecked", mNounsButton.isChecked());
        outState.putBoolean("adjectivesIsChecked", mAdjectivesButton.isChecked());
        outState.putBoolean("othersIsChecked", mOthersButton.isChecked());
        outState.putInt("lastKnownSyllableCount", lastKnownSyllableCount);

    }



    /*
     * Events that are triggered after each add to Haiku button press
     */
    private void addToHaikuButtonEvents(String selectedWord, int sWordSyllableCount, String[] resArr, int resID, String crossReference) {

        Log.d(LOG_TAG, "MainMenuFragment.addToHaikuButtonEvents (PRIVATE METHOD)");

        // Conditional check for pass/fail
        boolean fail = false;

        int itemSyllableCount;

        // Our new spinner adapter
        ArrayAdapter<String> mSpinnerAdapterRes;

        // The list we will use to populate the spinner adapter
        List<String> resArr_list = new ArrayList<>();


        /*
            (1) Go through all the words in the specified resource
            (2) Identify a match for our cross-reference
            (3) Eliminate words that don't fit the appropriate phrase
            (4) Add words that DO fit to a new list
         */
        for(int i = 0; i < resArr.length; i++) {

            itemSyllableCount = Integer.parseInt(resArr[i].substring(0, 1));
            lastKnownSyllableCount = itemSyllableCount;

            // If phrase 1 hasn't been completed
            if (mHaiku.getphraseOneSyllableCount() < 5) {
                // If this word doesn't fit in the phrase, set it to null
                if(mHaiku.getphraseOneSyllableCount() + itemSyllableCount > 5) {
                    resArr[i] = null;
                }
            }
            // else if phrase 2 hasn't been completed
            else if (mHaiku.getphraseTwoSyllableCount() < 7) {
                // If this word doesn't fit in the phrase, set it to null
                if(mHaiku.getphraseTwoSyllableCount() + itemSyllableCount > 7) {
                    resArr[i] = null;
                }
            }
            // else if phrase 3 hasn't been completed
            else if (mHaiku.getphraseThreeSyllableCount() < 5) {
                // If this word doesn't fit in the phrase, set it to null
                if(mHaiku.getphraseThreeSyllableCount() + itemSyllableCount > 5) {
                    resArr[i] = null;
                }
            }

            // If the word fits the appropriate phrase, remove the syllable count at the first index.
            // Finally, add the word to our new list
            if(resArr[i] != null) {
                resArr_list.add(resArr[i].substring(1, resArr[i].length()));
            }
        }

        // If the word that was chosen to be added was not found in the list,
        // we have a resource problem our have chosen to use the wrong list
        if (selectedWord == null || sWordSyllableCount == -1) {
            Log.e("\n\nAdd to Haiku Event Error", "Selected word from chosen resource was not found\n\n");
            throw new NoSuchElementException();
        }


        // Proceed to populate the adapter with our newly created list and assign the adapter to our spinner
        mSpinnerAdapterRes = new ArrayAdapter<>(MainMenuFragment.this.getContext(), android.R.layout.simple_list_item_1, resArr_list);
        mSpinnerAdapterRes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(mSpinnerAdapterRes);
        mSpinnerAdapterRes.notifyDataSetChanged();


        // NOW, Starting from phrase 1 to phrase 3
        // If the word syllable count fits in to phrase 1, proceed
        if(mHaiku.getphraseOneSyllableCount() < 5) {

            // calls on our model's method to add to and return a boolean value
            // performs a secondary pass/fail check to see if the word fits in the specified phrase
            if (mHaiku.addToPhraseOne(selectedWord)) {

                mHaikuLine1.setText(mHaiku.getPhraseOneAsString());

                // reset the list for phrase 2
                if (mHaiku.getphraseOneSyllableCount() == 5 || mHaiku.getphraseOneSyllableCount() + sWordSyllableCount > 5) {
                    radioButtonEvents(resID);
                }

            }
        }
        // OR If the word syllable count fits in to phrase 2, proceed
        else if(mHaiku.getphraseTwoSyllableCount() < 7) {
            // calls on our model's method to add to and return a boolean value
            // performs a secondary pass/fail check to see if the word fits in the specified phrase
            if (mHaiku.addToPhraseTwo(selectedWord)) {

                mHaikuLine2.setText(mHaiku.getPhraseTwoAsString());

                // reset the list for phrase 3
                if(mHaiku.getphraseTwoSyllableCount() == 7 || (mHaiku.getphraseTwoSyllableCount() + sWordSyllableCount > 7)) {
                    radioButtonEvents(resID);
                }

            }

        }
        // OR If the word syllable count fits in to phrase 3, proceed
        else if(mHaiku.getphraseThreeSyllableCount() < 5) {
            // calls on our model's method to add to and return a boolean value
            // performs a secondary pass/fail check to see if the word fits in the specified phrase
            if (mHaiku.addToPhraseThree(selectedWord)) {

                mHaikuLine3.setText(mHaiku.getPhraseThreeAsString());



                // reset the list if
                if(mHaiku.getphraseThreeSyllableCount() + sWordSyllableCount > 5) {
                    radioButtonEvents(resID);
                }


                // Disable if we finished the Haiku
                if(mHaiku.getphraseThreeSyllableCount() == 5) {
                    // ALSO disable these buttons cause we can't if our Haiku is complete
                    mAddToHaikuButton.setEnabled(false);
                    mSpinner.setEnabled(false);
                }

            }

        }
        else {
            // SOMETHING IS WRONG -- SET FAIL TO TRUE
            fail = true;
        }


        /* If we added successfully to the Haiku and these buttons are not usable, make them usable */
        if(!fail) {

            if (mHaiku.getphraseOneSyllableCount() >= 0 && mHaiku.getphraseThreeSyllableCount() < 5) {

                if(mDeleteLastWordButton.getVisibility() == View.INVISIBLE || !mDeleteLastWordButton.isEnabled() || !mDeleteLastWordButton.isClickable()) {
                    mDeleteLastWordButton.setVisibility(View.VISIBLE);
                    mDeleteLastWordButton.setEnabled(true);
                    mDeleteLastWordButton.setClickable(true);
                }

                if(mStartOverButton.getVisibility() == View.INVISIBLE || !mStartOverButton.isEnabled() || !mStartOverButton.isClickable()) {
                    mStartOverButton.setVisibility(View.VISIBLE);
                    mStartOverButton.setEnabled(true);
                    mStartOverButton.setClickable(true);
                }

                if(mDisplayHaikuButton.getVisibility() == View.INVISIBLE || !mDisplayHaikuButton.isEnabled() || !mDisplayHaikuButton.isClickable()) {
                    mDisplayHaikuButton.setVisibility(View.VISIBLE);
                    mDisplayHaikuButton.setEnabled(true);
                    mDisplayHaikuButton.setClickable(true);
                }
            }

            // DISPLAY SUCCESS TOAST
            CharSequence text = "'" + crossReference.toUpperCase() + "' added!";
            makeToast(text);
        }
        else {
            // FAILURE TOAST
            CharSequence text = "INTERNAL ERROR: '" + crossReference.toUpperCase() + "' was not added!";
            makeToast(text);
        }

    }

    public void radioButtonEvents(int resID) {

        Log.d(LOG_TAG, "MainMenuFragment.radioButtonEvents (PRIVATE METHOD)");


        String[] resArr = getResources().getStringArray(resID);
        int itemSyllableCount;
        ArrayAdapter<String> mSpinnerAdapterRes;
        List<String> resArr_list = new ArrayList<>();

        try {

            // If we can put words in the Haiku, if spinner and add are disabled, re-enable
            if (mHaiku.getphraseOneSyllableCount() >= 0 && mHaiku.getphraseThreeSyllableCount() < 5) {
                // Show and enable the spinner
                if (mSpinner.getVisibility() == View.INVISIBLE || !mSpinner.isEnabled()) {
                        mSpinner.setVisibility(View.VISIBLE);
                        mSpinner.setEnabled(true);
                }

                // show and enable the add button
                if (mAddToHaikuButton.getVisibility() == View.INVISIBLE || !mAddToHaikuButton.isEnabled()) {
                        mAddToHaikuButton.setVisibility(View.VISIBLE);
                        mAddToHaikuButton.setEnabled(true);
                }
            }

            // Phrase 1 constraints
            if(mHaiku.getphraseOneSyllableCount() < 5) {

                for(int i = 0; i < resArr.length; i++) {
                    itemSyllableCount = Integer.parseInt(resArr[i].substring(0, 1));
                    if(mHaiku.getphraseOneSyllableCount() + itemSyllableCount > 5) {
                        resArr[i] = null;
                    }

                    if(resArr[i] != null) {
                        resArr[i] = resArr[i].substring(1, resArr[i].length());
                        resArr_list.add(resArr[i]);
                    }
                }

            }
            // phrase 2 constraints
            else if(mHaiku.getphraseTwoSyllableCount() < 7) {

                for(int i = 0; i < resArr.length; i++) {
                    itemSyllableCount = Integer.parseInt(resArr[i].substring(0, 1));
                    if(mHaiku.getphraseTwoSyllableCount() + itemSyllableCount > 7) {
                        resArr[i] = null;
                    }

                    if(resArr[i] != null) {
                        resArr[i] = resArr[i].substring(1, resArr[i].length());
                        resArr_list.add(resArr[i]);
                    }
                }

            }
            // Phrase 3 constraints
            else if(mHaiku.getphraseThreeSyllableCount() < 5) {

                for(int i = 0; i < resArr.length; i++) {
                    itemSyllableCount = Integer.parseInt(resArr[i].substring(0, 1));
                    if(mHaiku.getphraseThreeSyllableCount() + itemSyllableCount > 5) {
                        resArr[i] = null;
                    }

                    if(resArr[i] != null) {
                        resArr[i] = resArr[i].substring(1, resArr[i].length());
                        resArr_list.add(resArr[i]);
                    }
                }

            }

            mSpinnerAdapterRes = new ArrayAdapter<>(MainMenuFragment.this.getContext(), android.R.layout.simple_list_item_1, resArr_list);
            mSpinnerAdapterRes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mSpinner.setAdapter(mSpinnerAdapterRes);
            mSpinnerAdapterRes.notifyDataSetChanged();

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    /* Simple toast message function */
    public void makeToast(CharSequence message) {
        if(mToast != null) {
            mToast.cancel();
        }

        Context context = getContext();
        int duration = Toast.LENGTH_SHORT;
        mToast = Toast.makeText(context, message, duration);
        mToast.show();
    }
}
