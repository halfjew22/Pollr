package com.example.lustig.pollr.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.lustig.pollr.R;

import com.example.lustig.pollr.adapters.MySimpleListAdapter;
import com.example.lustig.pollr.model.Poll_Text;

import com.example.lustig.pollr.utilities.PollrDataBase;

import java.util.ArrayList;

/** Checklist **
 * RecyclerView in its most limited state is working
 * ToDo add image button click to vote card
 * ToDo add helper methods for Parse stuff
 *  -make things like returning Poll objects and incrememnting vote easier
 * ToDo add test button click to vote from card
 * ToDo add card click --> Detail view from list
 */

public class PublicPollsActivity extends Activity {

    ArrayList<Poll_Text> mPollTexts;
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



        PollrDataBase.init_db(this);

//        // Enable Local Datastore
//        Parse.enableLocalDatastore(this);
//
//        ParseUser.enableAutomaticUser();
//
//        Parse.initialize(this,
//                "0a0zQDm9BiHwRw6FNQqUM4vj8fHeEAAA4EAVGUr5",
//                "XJhfRJboOpgtxabo4CHLieVCPBA0yDJnI1MDkQnC");


        mPollTexts = PollrDataBase.getTextPollsFromDatabase();



       mAdapter = new MySimpleListAdapter(getApplicationContext(), mPollTexts);

        mPublicPollRecyclerView = (RecyclerView) findViewById(R.id.publicpollRecyclerView);

        mPublicPollRecyclerView.setAdapter(mAdapter);


        mPublicPollRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

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
   // }

    public void addPoll(View v) {

        Intent i = new Intent(PublicPollsActivity.this, AddPollActivity.class);
        startActivityForResult(i, ADD_POLL_CODE);

    }



}

