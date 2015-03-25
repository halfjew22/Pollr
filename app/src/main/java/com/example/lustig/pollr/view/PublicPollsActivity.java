package com.example.lustig.pollr.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.lustig.pollr.R;
import com.example.lustig.pollr.model.Poll;
import com.example.lustig.pollr.model.PollItem;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class PublicPollsActivity extends Activity {

    List<Poll> mPolls;
    ListView mPublicPollListView;

    /**
     * ToDo fix these inflators and use Google's RecyclerView + CardView
     */
    BaseInflaterAdapter<Poll> mAdapter;

    public static final int ADD_POLL_CODE = 7;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.public_polls_layout);

        mPolls = new ArrayList<Poll>();

        // create array list from database
        getUpdateFromDatabase();

        mPublicPollListView = (ListView) findViewById(R.id.list_view);

        initializeListView();

        mPublicPollListView.addHeaderView(new View(this));
        mPublicPollListView.addFooterView(new View(this));
    }

    private void initializeListView() {

        /**
         * ToDo fix these inflators and use Google's RecyclerView + CardView
         */
        // This BaseInflaterAdapter is going to have to go in favor of the RecyclerView stuff
        mAdapter = new BaseInflaterAdapter<Poll>(new CardInflater());

        mPublicPollListView.setAdapter(mAdapter);

        addPollsToAdapter();

        mPublicPollListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /**
                 * For a poll detail view, what information needs to be passed?
                 *
                 * Poll title along with all PollItems
                 *
                 * Poll items each need to have a vote count as well. I need to start pulling
                 * code over from the first app I worked on.
                 */
                Poll clickedPoll = mPolls.get(position - 1);

                Intent detailIntent = new Intent(PublicPollsActivity.this, PollDetailView.class);

                detailIntent.putExtra(Poll.CLASS_TAG, clickedPoll);

                startActivity(detailIntent);
            }
        });

    }



    /**
     * Right now, we are downloading all polls and updating the list with each one.
     * This is terribly inefficient. I think we might need to add more fields to the object
     * to determine when objects were added. Right now, I can't really think of the most efficient
     * way to load every poll with up to date changes... Hmmmm
     *
     * Query the database for all polls, and put that information in the mPolls ArrayList
     *
     * We can also have this activated by a button.
     *
     * Currently, just called in the beginning of onCreate.
     */
    public void getUpdateFromDatabase() {

        // Assume ParseObject myPost was previously created.
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Poll");

        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> pollListFromDB, ParseException e) {
                // pollListFromDB now has the Polls for everything

                for (ParseObject obj : pollListFromDB) {

                    Log.d("MichaelLustig", obj.getObjectId());

                    Poll poll = new Poll(obj);
                    mPolls.add(poll);

                }

                addPollsToAdapter();

            }
        });

    }

    public void addPoll(View v) {

        Intent i = new Intent(PublicPollsActivity.this, AddPollActivity.class);
        startActivityForResult(i, ADD_POLL_CODE);

    }



    private void addPollsToAdapter() {

        for (Poll poll : mPolls) {
            mAdapter.addItem(poll, true);
        }

    }

    /**
     * Not used, but I'm keeping the code to remember how a Poll is constructed statically
     */
    private void createStaticPublicArrayList() {

        mPolls = new ArrayList<>();

        // Add 3 fun polls

        // Dress color
        Poll dressColor = new Poll("What color is this dress?",
                new ArrayList<PollItem>() {{

                    add(new PollItem("blue and black"));
                    add(new PollItem("gold and white"));
                    add(new PollItem("SHUT THE FUCK UP ABOUT THE DRESS!"));

                }} );

        // Who let the dogs out
        Poll dogsOut = new Poll("Who let the dogs out?",

                new ArrayList<PollItem>() {{

                    add(new PollItem("Who?"));
                    add(new PollItem("Who??"));
                    add(new PollItem("WHO?!!"));

                }}

        );

        // What's cooler than being cool
        Poll coolerThanCool = new Poll("What's cooler than bein' cool?",

                new ArrayList<PollItem>() {{

                    add(new PollItem("A polar bear's toenail."));
                    add(new PollItem("This dick."));
                    add(new PollItem("Ice cold!"));

                }}

        );

        mPolls.add(dressColor);
        mPolls.add(dogsOut);
        mPolls.add(coolerThanCool);

    }

}

