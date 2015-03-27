package com.example.lustig.pollr.helpers;

import android.content.Context;

import com.parse.Parse;
import com.parse.ParseUser;

/**
 * Created by lustig on 3/27/15.
 */
public class ParseHelper {

    public static String PARSE_APPLICATION_ID       = "0a0zQDm9BiHwRw6FNQqUM4vj8fHeEAAA4EAVGUr5";
    public static String PARSE_CLIENT_KEY           = "XJhfRJboOpgtxabo4CHLieVCPBA0yDJnI1MDkQnC";

    public static boolean LOCAL_DATASTORE_ENABLED   = true;
    public static boolean AUTOMATIC_USER_ENABLED    = true;

    public static void InitializeParse(Context context) {

        if (LOCAL_DATASTORE_ENABLED) {

            // Enable Local Datastore
            Parse.enableLocalDatastore(context);

        }

        if (AUTOMATIC_USER_ENABLED) {

            ParseUser.enableAutomaticUser();

        }

        Parse.initialize(context, PARSE_APPLICATION_ID, PARSE_CLIENT_KEY);

    }

}
