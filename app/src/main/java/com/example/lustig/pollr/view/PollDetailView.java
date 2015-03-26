package com.example.lustig.pollr.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lustig.pollr.R;
import com.example.lustig.pollr.model.Poll;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * ToDo fix buttonXClick methods by using implementing an OnClickListener within the class
 *
 */

public class PollDetailView extends ActionBarActivity {

    Poll mPoll;

    TextView mtvTitle;

    Button mbOption1;
    Button mbOption2;
    Button mbOption3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll_detail_view);

        initializeViews();

        Bundle extras = getIntent().getExtras();

        // Stupid workaround, but I'm lazy. Sue me.
        // Wasn't parceable, whodda thought.
        mPoll = null;

        if(extras != null) {
            mPoll = extras.getParcelable(Poll.CLASS_TAG);
        }

        mtvTitle.setText(mPoll.getTitle());

        mbOption1.setText(mPoll.getPollItems().get(0).getTitle());
        mbOption2.setText(mPoll.getPollItems().get(1).getTitle());
        mbOption3.setText(mPoll.getPollItems().get(2).getTitle());
    }

    /**
     * ToDo fix this ugly fucking code having different functions for different
     * buttons. GET YOUR SHIT TOGETHER SMALLS!
     */
    public void button1Click(View v) {

        disableButtons();

        // Color voted button blue, what hex value?
        mbOption1.setBackgroundColor(Color.parseColor("#00AEEF"));

        // increment the vote count locally and on the database
        // local increment
        mPoll.getPollItems().get(0).incrementVote();

        // increment database
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Poll");

        Log.d("MichaelLustig", mPoll.getObjectID());

        query.whereEqualTo("objectId", mPoll.getObjectID());
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> poll, ParseException e) {
                if (e == null) {

                    Log.d("Poll",
                            poll.get(0).getInt("vote1") + "");

                    Log.d("Poll", "Retrieved " + poll.size() + " scores");

                    poll.get(0).increment("vote1");
                    poll.get(0).saveInBackground();


                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
        // First, check for five total votes. If
        if(mPoll.getTotalVotes()>=5)

        {

            // Show dialog one final time
            AlertDialog alertDialog = new AlertDialog.Builder(PollDetailView.this).create();
            alertDialog.setTitle("Votes");

            // Show each vote per item
            String message = mPoll.getVoteString();

            alertDialog.setMessage(message);
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();

            // Disable buttons
            disableButtons();



            // return
            return;
        }


        AlertDialog alertDialog = new AlertDialog.Builder(PollDetailView.this).create();
        alertDialog.setTitle("Votes");

        // Show each vote per item
        String message = mPoll.getVoteString();

        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL,"OK",
                new DialogInterface.OnClickListener()

                {
                    public void onClick (DialogInterface dialog,int which){
                        dialog.dismiss();
                    }
                }

        );
        alertDialog.show();
    }

    /**
     * ToDo fix this ugly fucking code having different functions for different
     * buttons. GET YOUR SHIT TOGETHER SMALLS!
     */
    public void button2Click(View v) {

        disableButtons();

        // Color voted button blue, what hex value?
        mbOption2.setBackgroundColor(Color.parseColor("#00AEEF"));

        mPoll.getPollItems().get(1).incrementVote();

        // increment database
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Poll");

        Log.d("MichaelLustig", mPoll.getObjectID());

        query.whereEqualTo("objectId", mPoll.getObjectID());
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> poll, ParseException e) {
                if (e == null) {

                    Log.d("Poll",
                            poll.get(0).getInt("vote2") + "");

                    Log.d("Poll", "Retrieved " + poll.size() + " scores");

                    poll.get(0).increment("vote2");
                    poll.get(0).saveInBackground();


                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });

        // First, check for five total votes. If
        if (mPoll.getTotalVotes() >= 5) {

            // Show dialog one final time
            AlertDialog alertDialog = new AlertDialog.Builder(PollDetailView.this).create();
            alertDialog.setTitle("Votes");

            // Show each vote per item
            String message = mPoll.getVoteString();

            alertDialog.setMessage(message);
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();

            // Disable buttons
            disableButtons();



            // return
            return;
        }



        AlertDialog alertDialog = new AlertDialog.Builder(PollDetailView.this).create();
        alertDialog.setTitle("Votes");

        // Show each vote per item
        String message = mPoll.getVoteString();

        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    /**
     * ToDo fix this ugly fucking code having different functions for different
     * buttons. GET YOUR SHIT TOGETHER SMALLS!
     */
    public void button3Click(View v) {

        disableButtons();

        // Color voted button blue, what hex value?
        mbOption3.setBackgroundColor(Color.parseColor("#00AEEF"));

        mPoll.getPollItems().get(2).incrementVote();

        // increment database
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Poll");

        Log.d("MichaelLustig", mPoll.getObjectID());

        query.whereEqualTo("objectId", mPoll.getObjectID());
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> poll, ParseException e) {
                if (e == null) {

                    Log.d("Poll",
                            poll.get(0).getInt("vote3") + "");

                    Log.d("Poll", "Retrieved " + poll.size() + " scores");

                    poll.get(0).increment("vote3");
                    poll.get(0).saveInBackground();


                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });

        // First, check for five total votes. If
        if (mPoll.getTotalVotes() >= 5) {

            // Show dialog one final time
            AlertDialog alertDialog = new AlertDialog.Builder(PollDetailView.this).create();
            alertDialog.setTitle("Votes");

            // Show each vote per item
            String message = mPoll.getVoteString();

            alertDialog.setMessage(message);
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();

            // Disable buttons
            disableButtons();



            // return
            return;
        }

        AlertDialog alertDialog = new AlertDialog.Builder(PollDetailView.this).create();
        alertDialog.setTitle("Votes");

        // Show each vote per item
        String message = mPoll.getVoteString();

        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public void disableButtons() {
        mbOption1.setEnabled(false);
        mbOption2.setEnabled(false);
        mbOption3.setEnabled(false);
    }

    private void initializeViews() {

        mtvTitle = (TextView) findViewById(R.id.tvPollDetailTitle);

        mbOption1 = (Button) findViewById(R.id.bOption1);
        mbOption2 = (Button) findViewById(R.id.bOption2);
        mbOption3 = (Button) findViewById(R.id.bOption3);

    }


}
