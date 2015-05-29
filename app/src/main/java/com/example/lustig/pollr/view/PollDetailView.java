
package com.example.lustig.pollr.view;



/**
 * ToDo fix buttonXClick methods by using implementing an OnClickListener within the class
 *
 */
/*

public class PollDetailView extends ActionBarActivity {

    Poll_Text mCurrentPollText;
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
        mCurrentPollText = null;

        if(extras != null) {
            mCurrentPollText = extras.getParcelable(Poll_Text.CLASS_TAG);
        }

        mCurrentPollItems = mCurrentPollText.getPollItems();

        mtvTitle.setText(mCurrentPollText.getTitle());

        mbOption1.setText(mCurrentPollText.getPollItems().get(0).getTitle());
        mbOption2.setText(mCurrentPollText.getPollItems().get(1).getTitle());
        mbOption3.setText(mCurrentPollText.getPollItems().get(2).getTitle());
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
*/