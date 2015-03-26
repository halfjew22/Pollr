package com.example.lustig.pollr.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lustig.pollr.R;
import com.example.lustig.pollr.model.Poll;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class MyPollsActivity extends ActionBarActivity {

    /**
     * ToDo fix these inflators and use Google's RecyclerView + CardView
     */
//    BaseInflaterAdapter<Poll> mAdapter;
    String mObjectIDFromPreviousActivity;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_polls_layout);

//        getTestDataFromInterwebz();

        ListView list = (ListView) findViewById(R.id.list_view);

        list.addHeaderView(new View(this));
        list.addFooterView(new View(this));

        /**
         * ToDo fix these inflators and use Google's RecyclerView + CardView
         */
//        mAdapter = new BaseInflaterAdapter<Poll>(new CardInflater());

//        list.setAdapter(mAdapter);
    }

    private void getTestDataFromInterwebz() {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("UncleSteve");
        query.getInBackground(mObjectIDFromPreviousActivity, new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {

                    object.getString("age");

                    Toast.makeText(
                            getApplicationContext(),
                            object.getString("age"),
                            Toast.LENGTH_SHORT
                    ).show();



                } else {
                    Toast.makeText(
                            getApplicationContext(),
                            "NULL!",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        String title;
        String option1, option2, option3;

        // Check which request we're responding to
        if (requestCode == 04) {

            // Make sure the request was successful
            if (resultCode == RESULT_OK) {

                title = data.getStringExtra("question");

                option1 = data.getStringExtra("option1");
                option2 = data.getStringExtra("option2");
                option3 = data.getStringExtra("option3");

                Poll newItem = new Poll(title);
//                mAdapter.addItem(newItem, true);

            } else {
                Toast.makeText(
                        getApplicationContext(),
                        "You afdl;kajdskflajsd;ll!",
                        Toast.LENGTH_SHORT).show();
            }

        }

    }

    public void addPoll(View v) {
//        CardItemData newCardData = new CardItemData(
//                "this is new data!!!",
//                "this is data 2",
//                "and 3");
//
//        mAdapter.addItem(newCardData, true);

        // Go to your my polls list
        Intent intent = new Intent(MyPollsActivity.this, AddPollActivity.class);
        startActivityForResult(intent, 04);


    }

    public void addPoll(String[] data) {

    }
}