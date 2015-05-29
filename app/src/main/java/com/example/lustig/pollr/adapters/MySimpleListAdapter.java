
package com.example.lustig.pollr.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lustig.pollr.R;
import com.example.lustig.pollr.model.Poll_Text;
import com.example.lustig.pollr.utilities.PollrDataBase;

//import com.example.lustig.pollr.view.PollDetailView;

import org.json.JSONException;

import java.util.Collections;
import java.util.List;

public class MySimpleListAdapter extends RecyclerView.Adapter<MySimpleListAdapter.MyViewHolder> {

    private LayoutInflater mInflator;

    List<Poll_Text> mData = Collections.emptyList();
    Context mContext;
    CustomOnItemClickListener mListener;

    public MySimpleListAdapter(Context context, List<Poll_Text> data) {
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

        final Poll_Text currentPollText = mData.get(position);

        holder.label.setText(currentPollText.title);

        for(int i = 0 ; i < currentPollText.itemNumber;++i)
            holder.relative.getChildAt(i).setVisibility(View.VISIBLE);

        try {
            switch (currentPollText.itemNumber)
            {
                case 2:
                    holder.tvOption1.setText(currentPollText.options.getString(0));
                    holder.tvOption2.setText(currentPollText.options.getString(1));
                    break;

                case 3:
                    holder.tvOption1.setText(currentPollText.options.getString(0));
                    holder.tvOption2.setText(currentPollText.options.getString(1));
                    holder.tvOption3.setText(currentPollText.options.getString(2));
                    break;

                case 4:
                    holder.tvOption1.setText(currentPollText.options.getString(0));
                    holder.tvOption2.setText(currentPollText.options.getString(1));
                    holder.tvOption3.setText(currentPollText.options.getString(2));
                    holder.tvOption4.setText(currentPollText.options.getString(3));
                    break;

                case 5:
                    holder.tvOption1.setText(currentPollText.options.getString(0));
                    holder.tvOption2.setText(currentPollText.options.getString(1));
                    holder.tvOption3.setText(currentPollText.options.getString(2));
                    holder.tvOption4.setText(currentPollText.options.getString(3));
                    holder.tvOption5.setText(currentPollText.options.getString(4));
                    break;

                case 6:
                    holder.tvOption1.setText(currentPollText.options.getString(0));
                    holder.tvOption2.setText(currentPollText.options.getString(1));
                    holder.tvOption3.setText(currentPollText.options.getString(2));
                    holder.tvOption4.setText(currentPollText.options.getString(3));
                    holder.tvOption5.setText(currentPollText.options.getString(4));
                    holder.tvOption6.setText(currentPollText.options.getString(5));
                    break;

                default: //
                    break;

            }
            holder.tvOption1.setText(currentPollText.options.getString(0));
            holder.tvOption2.setText(currentPollText.options.getString(1));
            holder.tvOption3.setText(currentPollText.options.getString(2));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        holder.root.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(
                        mContext,
                        "You clicked the root view",
                        Toast.LENGTH_SHORT
                ).show();

               // Intent showPollDetailIntent = new Intent(mContext, PollDetailView.class);
               // showPollDetailIntent.putExtra(Poll_Text.CLASS_TAG, currentPollText);

                //showPollDetailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

               // mContext.startActivity(showPollDetailIntent);

            }
        });

        holder.tvOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PollrDataBase.IncrementPoll(0, currentPollText.id);

                Toast.makeText(
                        mContext,
                        "You clicked option 1 in poll " + (position + 1) + ":",
                        Toast.LENGTH_SHORT
                ).show();



            }
        });

        holder.tvOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PollrDataBase.IncrementPoll(1,currentPollText.id);;

                Toast.makeText(
                        mContext,
                        "You clicked option 2 in poll " + (position + 1)  + ": " ,
                        Toast.LENGTH_SHORT
                ).show();

            }
        });

        holder.tvOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PollrDataBase.IncrementPoll(2,currentPollText.id);

                Toast.makeText(
                        mContext,
                        "You clicked option 3 in poll " + (position + 1)  + ": " ,
                        Toast.LENGTH_SHORT
                ).show();

            }
        });

        holder.tvOption4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PollrDataBase.IncrementPoll(3,currentPollText.id);

                Toast.makeText(
                        mContext,
                        "You clicked option 4 in poll " + (position + 1)  + ": " ,
                        Toast.LENGTH_SHORT
                ).show();

            }
        });

        holder.tvOption5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PollrDataBase.IncrementPoll(4,currentPollText.id);

                Toast.makeText(
                        mContext,
                        "You clicked option 5 in poll " + (position + 1)  + ": " ,
                        Toast.LENGTH_SHORT
                ).show();

            }
        });

        holder.tvOption6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PollrDataBase.IncrementPoll(5,currentPollText.id);

                Toast.makeText(
                        mContext,
                        "You clicked option 6 in poll " + (position + 1)  + ": " ,
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
        RelativeLayout relative;
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
        TextView tvOption4;
        TextView tvOption5;
        TextView tvOption6
                ;
        public MyViewHolder(View itemView) {
            super(itemView);


            root = (LinearLayout) itemView.findViewById(R.id.root);

            label = (TextView) itemView.findViewById(R.id.tvLabel);

            relative = (RelativeLayout)itemView.findViewById(R.id.relative);



            tvOption1 = (TextView) itemView.findViewById(R.id.tvOption1);
            tvOption2 = (TextView) itemView.findViewById(R.id.tvOption2);
            tvOption3 = (TextView) itemView.findViewById(R.id.tvOption3);
            tvOption4 = (TextView) itemView.findViewById(R.id.tvOption4);
            tvOption5 = (TextView) itemView.findViewById(R.id.tvOption5);
            tvOption6 = (TextView) itemView.findViewById(R.id.tvOption6);




        }

        @Override
        public void onClick(View v) {
            mItemClickListener.onItemClick(v, getPosition());
        }
    }

}

