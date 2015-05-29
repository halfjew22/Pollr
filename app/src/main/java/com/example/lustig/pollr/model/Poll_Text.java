package com.example.lustig.pollr.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.example.lustig.pollr.utilities.PollrDataBase;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Poll objects will contain data relating to polls. Poll objects will
 * contain a ListArray of PollItem's
 */
public class Poll_Text {

    public String id;
    public String title;
    ;
    public String start_date;
    public String end_date;
    public int vote;
    public JSONArray options;

    public int itemNumber;

    public Poll_Text() {
        id = new String();
        title = new String();
        start_date = new String();
        end_date = new String();
        options = new JSONArray();
        itemNumber = 0;
    }

    public Poll_Text( String title, int vote, JSONArray options) {


        this.id = "";
        this.title = title;
        this.vote = vote;
        this.options = options;
    }


    public Poll_Text( ParseObject obj)
    {
        this.id = obj.getObjectId();
        this.title = obj.getString(PollrDataBase.TITLE);
        this.options = obj.getJSONArray(PollrDataBase.OPTIONS);
        this.itemNumber =obj.getInt(PollrDataBase.ITEMNUMBER);
    }
}