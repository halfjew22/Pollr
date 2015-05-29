package com.example.lustig.pollr.utilities;

import android.content.Context;
import android.util.Log;

import com.example.lustig.pollr.model.Poll_Text;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allouch on 5/29/2015.
 */
public class PollrDataBase {

    private static final String TAG = "PollrDataBase";

    private static final String parse_app_id = "rrp81aNbMxTVZ8j0MpMtPsnAu7XjcYbHCrkbtolR";
    private static final String parse_client_key = "eUzkKH6p2cpa6kWoIpCWGLnXDaPWzDOHSR7cjN3y";

    //Poll_Text attributes
    public static final String TITLE = "title";
    public static final String OPTIONS = "options";
    public static final String ITEMNUMBER = "itemNumber";
    public static final String VOTE0 = "vote";
    public static final String VOTE1 = "vote1";
    public static final String VOTE2 = "vote2";
    public static final String VOTE3 = "vote3";
    public static final String VOTE4 = "vote4";
    public static final String VOTE5 = "vote5";
    public static final String VOTE = "vote";

    public static boolean init_db(Context c)
    {

        try{
            Parse.initialize(c, parse_app_id, parse_client_key);
        } catch(Exception e){
            Log.e(TAG, e.toString());
            return false;
        }
        return true;

    }



    /**
    This function adds a poll to the Database
     */
    public static Poll_Text AddPoll(Poll_Text pollText)
    {

        ParseObject parseObject = new ParseObject("Poll_Text");
        parseObject.put(TITLE,pollText.title);
        parseObject.put(OPTIONS, pollText.options);
        parseObject.put(VOTE0,0);
        parseObject.put(VOTE1,0);
        parseObject.put(VOTE2,0);
        parseObject.put(VOTE3,0);
        parseObject.put(VOTE4,0);
        parseObject.put(VOTE5,0);

        try {
            parseObject.save();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return pollText;
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
    public static ArrayList<Poll_Text> getTextPollsFromDatabase() {

        ArrayList<Poll_Text> poll_texts = new ArrayList<Poll_Text>();
        // Assume ParseObject myPost was previously created.
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Poll_Text");

        try {
            List<ParseObject> objects = query.find();

            for (ParseObject obj : objects) {

                Log.d(TAG, obj.getObjectId());

                Poll_Text pollText = new Poll_Text(obj);
                poll_texts.add(pollText);

                Log.d(TAG, poll_texts.size() + "");
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return  poll_texts;
    }



    public static void  IncrementPoll(int numb,String id)
    {
        String vote = VOTE + "" +numb;

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Poll_Text");
        try {
            ParseObject parseObject = query.get(id);
            parseObject.increment(vote,+1);
            parseObject.saveInBackground();
        } catch (ParseException e) {
            e.printStackTrace();
        }




    }

}
