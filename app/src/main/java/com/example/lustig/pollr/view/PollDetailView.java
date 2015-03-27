package com.example.lustig.pollr.view;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.lustig.pollr.R;
import com.example.lustig.pollr.model.Poll;
import com.example.lustig.pollr.model.PollItem;

import java.util.List;

/**
 * ToDo fix buttonXClick methods by using implementing an OnClickListener within the class
 *
 */

public class PollDetailView extends ActionBarActivity {

    Poll mCurrentPoll;
    List<PollItem> mCurrentPollItems;
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
        mCurrentPoll = null;

        if(extras != null) {
            mCurrentPoll = extras.getParcelable(Poll.CLASS_TAG);
        }

        mCurrentPollItems = mCurrentPoll.getPollItems();

        mtvTitle.setText(mCurrentPoll.getTitle());

        mbOption1.setText(mCurrentPoll.getPollItems().get(0).getTitle());
        mbOption2.setText(mCurrentPoll.getPollItems().get(1).getTitle());
        mbOption3.setText(mCurrentPoll.getPollItems().get(2).getTitle());
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
