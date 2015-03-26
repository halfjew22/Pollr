package com.example.lustig.pollr.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.lustig.pollr.R;
import com.example.lustig.pollr.adapters.MySimpleListAdapter;
import com.example.lustig.pollr.model.Poll;
import com.example.lustig.pollr.model.PollItem;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/** Checklist **
 * RecyclerView in its most limited state is working
 * ToDo add image button click to vote card
 * ToDo add helper methods for Parse stuff
 *  -make things like returning Poll objects and incrememnting vote easier
 * ToDo add test button click to vote from card
 * ToDo add card click --> Detail view from list
 */

public class PublicPollsActivity extends Activity {

    ArrayList<Poll> mPolls;
    RecyclerView mPublicPollRecyclerView;
    private MySimpleListAdapter mAdapter;

    public static final int ADD_POLL_CODE = 7;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.public_polls_layout);

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        ParseUser.enableAutomaticUser();

        Parse.initialize(this,
                "0a0zQDm9BiHwRw6FNQqUM4vj8fHeEAAA4EAVGUr5",
                "XJhfRJboOpgtxabo4CHLieVCPBA0yDJnI1MDkQnC");

        // Wasn't instantiating my ArrayList so I was getting FCs... D'oh!
        mPolls = new ArrayList<Poll>();

        getUpdateFromDatabase();

        mAdapter = new MySimpleListAdapter(getApplicationContext(), mPolls);

        mPublicPollRecyclerView = (RecyclerView) findViewById(R.id.publicpollRecyclerView);

        mPublicPollRecyclerView.setAdapter(mAdapter);

        mPublicPollRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    }

    public void button3(View v) {
        Log.d("BUTTON 3## # # ##!", "Button 3 clicked");
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

        try {
            List<ParseObject> objects = query.find();

            for (ParseObject obj : objects) {

                Log.d("MichaelLustig", obj.getObjectId());

                Poll poll = new Poll(obj);
                mPolls.add(poll);

                Log.d("MichaelLustig", mPolls.size() + "");
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

/**
 * ToDo figure out why I can't get findInBackground to work, although the same behavior is achieved
 * from find()...
 */

//        query.findInBackground(new FindCallback<ParseObject>() {
//            public void done(List<ParseObject> pollListFromDB, ParseException e) {
//                // pollListFromDB now has the Polls for everything
//
//                for (ParseObject obj : pollListFromDB) {
//
//                    Log.d("MichaelLustig", obj.getObjectId());
//
//                    Poll poll = new Poll(obj);
//                    mPolls.add(poll);
//                }
//
//                // Creating method to set adapter only after the data is finished loading
//                //setAdapter();
//            }
//        });
    }

    public void addPoll(View v) {

        Intent i = new Intent(PublicPollsActivity.this, AddPollActivity.class);
        startActivityForResult(i, ADD_POLL_CODE);

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

