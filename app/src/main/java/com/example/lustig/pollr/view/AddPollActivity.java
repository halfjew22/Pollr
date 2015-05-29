package com.example.lustig.pollr.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.lustig.pollr.R;
import com.example.lustig.pollr.model.Poll_Text;
import com.example.lustig.pollr.utilities.PollrDataBase;
import com.parse.ParseObject;

import org.json.JSONArray;


public class AddPollActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_poll_layout);

    }

    public void postPoll(View v) {

        Intent returnIntent = new Intent();

        String pollTitle = ((EditText) findViewById(R.id.etPollName)).getText().toString();

        String option1 = ((EditText) findViewById(R.id.etOption1)).getText().toString();
        String option2 = ((EditText) findViewById(R.id.etOption2)).getText().toString();
        String option3 = ((EditText) findViewById(R.id.etOption3)).getText().toString();

        returnIntent.putExtra("question", pollTitle);

        returnIntent.putExtra("option1", option1);
        returnIntent.putExtra("option2", option2);
        returnIntent.putExtra("option3", option3);
        JSONArray options = new JSONArray();


            options.put(option1);
            options.put(option2);
            options.put(option3);

        Poll_Text pollText = new Poll_Text(pollTitle,0,options);
        PollrDataBase.AddPoll(pollText);

       // setResult(RESULT_OK, returnIntent);
       // finish();

    }

}
