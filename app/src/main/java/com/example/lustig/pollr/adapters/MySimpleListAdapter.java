package com.example.lustig.pollr.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lustig.pollr.R;

import java.util.Collections;
import java.util.List;

public class MySimpleListAdapter extends RecyclerView.Adapter<MySimpleListAdapter.MyViewHolder> {

    private LayoutInflater mInflator;

    /**
     * ToDo Change Information out for Poll data and adjust inflation accordingly
     */
    List<Information> mData = Collections.emptyList();
    Context mContext;


    /**
     * ToDo Change Information out for Poll data and adjust inflation accordingly
     */
    public MySimpleListAdapter(Context context, List<Information> data) {
        mInflator = LayoutInflater.from(context);
        mData = data;
        mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflator.inflate(R.layout.custom_row, parent, false);

        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        /**
         * ToDo Change Information out for Poll data and adjust inflation accordingly
         */
        Information current = mData.get(position);
        holder.label.setText(current.title);

        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(
                        mContext,
                        "You clicked the root view",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        holder.ivOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(
                        mContext,
                        "You clicked option 1 in poll " + (position + 1),
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }



    class MyViewHolder extends RecyclerView.ViewHolder {

        /**
         * ToDo Change Information out for Poll data and adjust inflation accordingly
         */

        TextView label;

        LinearLayout root;

        ImageView ivOption1;
        ImageView ivOption2;
        ImageView ivOption3;
        ImageView ivOption4;
        ImageView ivOption5;
        ImageView ivOption6;

        public MyViewHolder(View itemView) {
            super(itemView);

            root = (LinearLayout) itemView.findViewById(R.id.root);

            label = (TextView) itemView.findViewById(R.id.tvLabel);
            ivOption1 = (ImageView) itemView.findViewById(R.id.ivOption1);

        }
    }

}

