package com.example.lustig.pollr.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PollItem implements Parcelable {

    String mTitle;
    int mVoteCount = 0;

    // PollItem has picture and is enumerated
    public PollItem(String title) {
        mTitle = title;
    }

    public PollItem(String title, int voteCount) {
        this(title);
        mVoteCount = voteCount;
    }

    public PollItem(Parcel p) {
        mTitle = p.readString();
        mVoteCount = p.readInt();
    }

    public String getTitle() {
        return mTitle;
    }

    public int getVoteCount() {
        return mVoteCount;
    }

    public void setVoteCount(int voteCount) {
        mVoteCount = voteCount;
    }

    public void incrementVote() {
        mVoteCount++;


    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeInt(mVoteCount);
    }

    public static Creator<PollItem> CREATOR = new Creator<PollItem>() {
        @Override
        public PollItem createFromParcel(Parcel source) {
            return new PollItem(source);
        }

        @Override
        public PollItem[] newArray(int size) {
            return new PollItem[size];
        }
    };
}
