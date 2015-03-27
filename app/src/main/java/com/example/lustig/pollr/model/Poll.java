package com.example.lustig.pollr.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Poll objects will contain data relating to polls. Poll objects will
 * contain a ListArray of PollItem's
 */
public class Poll implements Parcelable {

    public static final String QUESTION_TAG = "question";
    public static final String CLASS_TAG = "poll";

    public static final int TYPE_IMAGE = 0;
    public static final int TYPE_TEXT = 1;

    public static final int NUM_OPTIONS = 3;

    // Max value for question length in characters
    public static final int MAX_QUESTION_LENGTH = 80;

    // Limited to MAX_QUESTION_LENGTH
    String mTitle;

    // 0 for image | 1 for text
    int mType;

    // Unique identifier nubmer for poll (fudged for now)
    int mUUID;

    /**
     * ArrayList of PollItems
     * <p/>
     * PollItems will be the individual gidgets people will be voting on.
     */
    List<PollItem> mPollItems;

    /**
     * Tags are the categories which a poll is targeting
     */
    String mTag;

    // Name of Poll - Not sure if we're going to use this
    String mName;

    // Username of the poster
    String mUserWhoPosted;

    // Use Unix time (seconds since Jan 1, 1970)
    int mTimePost;
    int mTimeEnd;

    // Total votes on poll
    int mTotalVotes;

    // Temporary simple solution for poll options
    String mOption1;
    String mOption2;
    String mOption3;

    // Temporary simple solution for vote counts
    int mVote1;
    int mVote2;
    int mVote3;

    // object ID
    String mObjectID;

    /* Default Poll */

    /**
     * Just specify the title. In the default poll, the type is image
     * and you just initialize with what you want the title to be.
     *
     * @param title Question being asked in the poll
     */
    public Poll(String title) {

        mType = TYPE_IMAGE;

        mTitle = title;
    }

    /**
     * Constructor for Poll that takes in a ParseObject and adds values
     * to the PollItems
     *
     * @param object
     */
    public Poll(ParseObject object) {

        mObjectID = object.getObjectId();

        Log.d("MichaelLustig: ", mObjectID);

        mPollItems = new ArrayList<>();

        mTitle = object.getString("title");

        mOption1 = object.getString("option1");
        mOption2 = object.getString("option2");
        mOption3 = object.getString("option3");

        mVote1 = object.getInt("vote1");
        mVote2 = object.getInt("vote2");
        mVote3 = object.getInt("vote3");

        mPollItems.add(new PollItem(mOption1, mVote1));
        mPollItems.add(new PollItem(mOption2, mVote2));
        mPollItems.add(new PollItem(mOption3, mVote3));
    }

    public Poll (Parcel p) {

        mTitle = p.readString();
        mPollItems = p.readArrayList(getClass().getClassLoader());

        mObjectID = p.readString();

        mVote1 = p.readInt();
        mVote2 = p.readInt();
        mVote3 = p.readInt();

        mOption1 = p.readString();
        mOption2 = p.readString();
        mOption3 = p.readString();
    }

    /* Poll constructor to specify question and PollItems */

    public Poll(String title, ArrayList<PollItem> pollItems) {

        // Set the type to image and the title to the title specified in the argument
        this(title);
        mPollItems = pollItems;

    }

    public int getTotalVotes() {
        int voteCount = 0;

        for (PollItem item: mPollItems) {
            voteCount += item.getVoteCount();
        }

        return voteCount;
    }

    public String getObjectID() {
        return mObjectID;
    }

    public void incrementPollItemVoteCount(final int pollItemPosition) {

        final PollItem incrementItem = mPollItems.get(pollItemPosition);

        // Increments the vote count locally
        // Look for way to do both locally and remotely at the same time
        //incrementItem.incrementVote();

        // increment database
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Poll");

        query.whereEqualTo("objectId", mObjectID);


        List<ParseObject> parseObjects;

        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> returnedPolls, ParseException e) {
                if (e == null) {

                    /**
                     * We have the specific object ID we're looking for, so this query
                     * will only return a list of one returnedPolls. Therefore, increment the
                     * 0th element of the returnedPolls.
                     */
                    incrementItem.setVoteCount(returnedPolls.get(0).getInt( "vote" + (pollItemPosition + 1)) + 1);
                    returnedPolls.get(0).increment("vote" + (pollItemPosition + 1));
                    returnedPolls.get(0).saveInBackground();


                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });

    }

    public String getVoteString() {

        String voteString = "";

        for (PollItem item : mPollItems) {
            voteString += (
                    item.getTitle() + ": " +
                            item.getVoteCount() + ": " +
                            ( 100 * (float) item.getVoteCount() / (float) getTotalVotes() ) + "%" + "\n"
            );
        }

        return voteString;
    }

    public List<PollItem> getPollItems() {
        return mPollItems;
    }

    public String getTitle() {
        return mTitle;
    }

    @Override
    public String toString() {
        return mTitle;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(mTitle);
        dest.writeList(mPollItems);
        dest.writeString(mObjectID);

        dest.writeInt(mVote1);
        dest.writeInt(mVote2);
        dest.writeInt(mVote3);

        dest.writeString(mOption1);
        dest.writeString(mOption2);
        dest.writeString(mOption3);
    }

    public static Creator<Poll> CREATOR = new Creator<Poll>() {
        @Override
        public Poll createFromParcel(Parcel source) {
            return new Poll(source);
        }

        @Override
        public Poll[] newArray(int size) {
            return new Poll[size];
        }
    };

}
