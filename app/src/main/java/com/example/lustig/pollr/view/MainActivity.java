package com.example.lustig.pollr.view;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.lustig.pollr.R;
import com.example.lustig.pollr.adapters.MySimpleListAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private RecyclerView mRecyclerView;
    private MySimpleListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * ToDo reinitizlize can't spell Parse API by uncommenting the below
         */


//         // Enable Local Datastore.
//         Parse.enableLocalDatastore(this);
//
//         ParseUser.enableAutomaticUser();
//
//         Parse.initialize(this,
//                 "0a0zQDm9BiHwRw6FNQqUM4vj8fHeEAAA4EAVGUr5",
//                 "XJhfRJboOpgtxabo4CHLieVCPBA0yDJnI1MDkQnC");

        mAdapter = new MySimpleListAdapter(getApplicationContext(), getData());

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    }

    public static List<Information> getData() {

        List<Information> data = new ArrayList<>();

        String[] titles = {
                "Poll 1",
                "Poll 2",
                "Poll 3",
                "Poll 4"
        };

        for (int i = 0; i < titles.length; i++) {

            Information current = new Information();
            current.title = titles[i];

            data.add(current);

        }

        return data;

    }

}
