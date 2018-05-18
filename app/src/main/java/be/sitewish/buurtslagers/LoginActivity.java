package be.sitewish.buurtslagers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import be.sitewish.buurtslagers.domain.*;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements AsyncResponse {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
//    private UserLoginTask mAuthTask = null;

    // UI references.
    private EditText mGebruikersNaamView;
    private EditText mPasswordView;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mGebruikersNaamView = (EditText) findViewById(R.id.gebruikersnaam);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        RequestHandler requestHandler = new RequestHandler();


//        mLoginFormView = findViewById(R.id.login_form);
//        mProgressView = findViewById(R.id.login_progress);
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
//        if (mAuthTask != null) {
//            return;
//        }

        // Reset errors.
        mGebruikersNaamView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mGebruikersNaamView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mGebruikersNaamView.setError(getString(R.string.error_field_required));
            focusView = mGebruikersNaamView;
            cancel = true;
        }

        if(!Controller.NetworkAvailable(this)){
            mGebruikersNaamView.setError(getString(R.string.no_internet));
            focusView = mGebruikersNaamView;
            cancel = true;
        }

        new InternetAvailable(this).execute();


        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
//            mAuthTask = new UserLoginTask(email, password);
//            mAuthTask.execute((Void) null);

            String url = "https://api.sitewish.be/klant";

            HashMap map = new HashMap();
            map.put("api_key", "8e6c7b47e63f664c7c66d84fcf0588202d37c3939e9fd65bf7ad6d9df52d4c188bd9c745");
            map.put("action", "GET");
            gson = new Gson();

            String[] zoeken = {"inlognaam", "=", email};

            map.put("zoekterm",gson.toJson(zoeken));

            NetworkRequest networkRequest = (NetworkRequest) new NetworkRequest(url, map, password ,new AsyncResponse() {
                @Override
                public void processFinish(int output) {
                    System.out.println("LOGIN " + output);
                    if(output == -1){
                        mGebruikersNaamView.setError(getString(R.string.invalid_gebruikersnaam));
                    }
                    else if(output == -2){
                        mPasswordView.setError(getString(R.string.error_invalid_password));
                    }
                    else{
                        System.out.println("LOGIN ingelogd");
                        StartActivity();
                    }
                }
            });
            networkRequest.execute();
        }
    }

    @Override
    public void processFinish(int output) {
        if(output == 0){
            mGebruikersNaamView.setError(getString(R.string.no_internet));
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return true;
    }

    private void StartActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
//    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
//
//        private final String mEmail;
//        private final String mPassword;
//
//        UserLoginTask(String email, String password) {
//            mEmail = email;
//            mPassword = password;
//        }
//
//        @Override
//        protected Boolean doInBackground(Void... params) {
//            // TODO: attempt authentication against a network service.
//
//            try {
//                // Simulate network access.
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                return false;
//            }
//
//            for (String credential : DUMMY_CREDENTIALS) {
//                String[] pieces = credential.split(":");
//                if (pieces[0].equals(mEmail)) {
//                    // Account exists, return true if the password matches.
//                    return pieces[1].equals(mPassword);
//                }
//            }
//
//            // TODO: register the new account here.
//            return true;
//        }
//
//        @Override
//        protected void onPostExecute(final Boolean success) {
//            mAuthTask = null;
//
//            if (success) {
//                finish();
//            } else {
//                mPasswordView.setError(getString(R.string.error_incorrect_password));
//                mPasswordView.requestFocus();
//            }
//        }
//
//        @Override
//        protected void onCancelled() {
//            mAuthTask = null;
//        }
//    }
}

