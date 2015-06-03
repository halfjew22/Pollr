package com.example.lustig.pollr.view;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lustig.pollr.R;
import com.example.lustig.pollr.model.Poll_Text;
import com.example.lustig.pollr.utilities.PollrDataBase;
import com.parse.ParseObject;

import org.json.JSONArray;


public class AddPollActivity extends Activity {


    private LinearLayout mLayout;
    private Button add_new_option;
    private int options_number ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_poll_layout);

        options_number = 2;
        mLayout = (LinearLayout) findViewById(R.id.new_options);
        add_new_option = (Button)findViewById(R.id.add_options_button);


        add_new_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                options_number++;
                if(options_number == 6)
                    add_new_option.setEnabled(false);

                mLayout.addView(createNewEditTextView());
            }
        });
    }

    private EditText createNewEditTextView() {


        final EditText editText = new EditText(this);
        editText.setHint("option " + options_number);

        switch (options_number)
        {
            case 3:
                editText.setId(R.id.option3_id);
                break;

            case 4:
                editText.setId(R.id.option4_id);
                break;

            case 5:
                editText.setId(R.id.option5_id);
                break;

            case 6:
                editText.setId(R.id.option6_id);
                break;
        }

        editText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        return editText;
    }
    public void postPoll(View v) {

        Intent returnIntent = new Intent();

        String pollTitle = ((EditText) findViewById(R.id.poll_title)).getText().toString();

        String option1 = ((EditText) findViewById(R.id.option1)).getText().toString();
        String option2 = ((EditText) findViewById(R.id.option2)).getText().toString();





        returnIntent.putExtra("question", pollTitle);

        returnIntent.putExtra("option1", option1);
        returnIntent.putExtra("option2", option2);

        JSONArray options = new JSONArray();


            options.put(option1);
            options.put(option2);

        if(options_number == 6) {
            options.put(((EditText) findViewById(R.id.option3_id)).getText().toString());
            options.put(((EditText) findViewById(R.id.option4_id)).getText().toString());
            options.put(((EditText) findViewById(R.id.option5_id)).getText().toString());
            options.put(((EditText) findViewById(R.id.option6_id)).getText().toString());
        }
        else if(options_number == 5 )
        {

                options.put(((EditText) findViewById(R.id.option3_id)).getText().toString());
                options.put(((EditText) findViewById(R.id.option4_id)).getText().toString());
                options.put(((EditText) findViewById(R.id.option5_id)).getText().toString());



        }

        else if(options_number == 4 )
        {

                options.put(((EditText) findViewById(R.id.option3_id)).getText().toString());
                options.put(((EditText) findViewById(R.id.option4_id)).getText().toString());

            }
       else  if(options_number == 3) {
            options.put(((EditText) findViewById(R.id.option3_id)).getText().toString());

        }


        Poll_Text pollText = new Poll_Text(pollTitle,0,options,options_number);
        PollrDataBase.AddPoll(pollText);

       setResult(RESULT_OK, returnIntent);
        finish();

    }

}
