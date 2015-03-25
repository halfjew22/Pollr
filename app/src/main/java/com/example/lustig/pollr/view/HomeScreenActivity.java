package com.example.lustig.pollr.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.lustig.pollr.R;
public class HomeScreenActivity extends Activity {

    String mObjectID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
    }

    public void goToPublicPollsList(View v) {

        // Go to your public polls list
        Intent intent = new Intent(HomeScreenActivity.this, PublicPollsActivity.class);
        startActivity(intent);

    }

    public void goToMyPollsList(View v) {

        // Go to your my polls list
        Intent intent = new Intent(HomeScreenActivity.this, MyPollsActivity.class);
        startActivity(intent);
    }
}