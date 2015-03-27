package com.example.lustig.pollr.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lustig.pollr.R;
import com.example.lustig.pollr.model.Poll;
import com.example.lustig.pollr.model.PollItem;
import com.example.lustig.pollr.view.PollDetailView;

import java.util.Collections;
import java.util.List;

public class MySimpleListAdapter extends RecyclerView.Adapter<MySimpleListAdapter.MyViewHolder> {

    private LayoutInflater mInflator;

    List<Poll> mData = Collections.emptyList();
    Context mContext;
    CustomOnItemClickListener mListener;

    public MySimpleListAdapter(Context context, List<Poll> data) {
        mInflator = LayoutInflater.from(context);
        mData = data;
        mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflator.inflate(R.layout.text_poll_card, parent, false);

        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        final Poll currentPoll = mData.get(position);
        final List<PollItem> currentPollItems = currentPoll.getPollItems();
        holder.label.setText(currentPoll.getTitle());

        holder.tvOption1.setText(currentPollItems.get(0).getTitle());
        holder.tvOption2.setText(currentPollItems.get(1).getTitle());
        holder.tvOption3.setText(currentPollItems.get(2).getTitle());

        holder.root.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(
                        mContext,
                        "You clicked the root view",
                        Toast.LENGTH_SHORT
                ).show();

                Intent showPollDetailIntent = new Intent(mContext, PollDetailView.class);
                showPollDetailIntent.putExtra(Poll.CLASS_TAG, currentPoll);

                showPollDetailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                mContext.startActivity(showPollDetailIntent);

            }
        });

        holder.tvOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                currentPoll.incrementPollItemVoteCount(0);

                Toast.makeText(
                        mContext,
                        "You clicked option 1 in poll " + (position + 1) + ": " + currentPollItems.get(0).getVoteCount(),
                        Toast.LENGTH_SHORT
                ).show();



            }
        });

        holder.tvOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                currentPoll.incrementPollItemVoteCount(1);

                Toast.makeText(
                        mContext,
                        "You clicked option 2 in poll " + (position + 1)  + ": " + currentPollItems.get(1).getVoteCount(),
                        Toast.LENGTH_SHORT
                ).show();

            }
        });

        holder.tvOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                currentPoll.incrementPollItemVoteCount(2);

                Toast.makeText(
                        mContext,
                        "You clicked option 3 in poll " + (position + 1)  + ": " + currentPollItems.get(2).getVoteCount(),
                        Toast.LENGTH_SHORT
                ).show();

            }
        });

    }

    public void setCustomOnItemClickListener(CustomOnItemClickListener customClickListener) {
        mListener = customClickListener;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public interface CustomOnItemClickListener {
        public void onItemClick(View view, int position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CustomOnItemClickListener mItemClickListener;

        TextView label;

        LinearLayout root;

        // These image views are going to be used if the poll is image based, using textviews for now
        ImageView ivOption1;
        ImageView ivOption2;
        ImageView ivOption3;
        ImageView ivOption4;
        ImageView ivOption5;
        ImageView ivOption6;

        TextView tvOption1;
        TextView tvOption2;
        TextView tvOption3;

        public MyViewHolder(View itemView) {
            super(itemView);


            root = (LinearLayout) itemView.findViewById(R.id.root);

            label = (TextView) itemView.findViewById(R.id.tvLabel);
//            ivOption1 = (ImageView) itemView.findViewById(R.id.ivOption1);

            tvOption1 = (TextView) itemView.findViewById(R.id.tvOption1);
            tvOption2 = (TextView) itemView.findViewById(R.id.tvOption2);
            tvOption3 = (TextView) itemView.findViewById(R.id.tvOption3);


        }

        @Override
        public void onClick(View v) {
            mItemClickListener.onItemClick(v, getPosition());
        }
    }

}

