package com.example.lustig.pollr.view;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.example.lustig.pollr.R;


public class MainActivity extends ActionBarActivity {

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

    }
}
