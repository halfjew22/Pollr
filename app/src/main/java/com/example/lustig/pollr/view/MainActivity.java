package com.example.lustig.pollr.view;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.lustig.pollr.R;

import com.example.lustig.pollr.model.Poll_Text;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

   // private RecyclerView mRecyclerView;
   // private MySimpleListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      //  mAdapter = new MySimpleListAdapter(getApplicationContext(), getData());

      //  mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

       // mRecyclerView.setAdapter(mAdapter);

     //   mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    }

    public static List<Poll_Text> getData() {

        List<Poll_Text> data = new ArrayList<>();



        return data;

    }

}
