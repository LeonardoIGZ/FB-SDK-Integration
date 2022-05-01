package com.ligz.fb_sdk_integration;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.ligz.fb_sdk_integration.Key.KeyHash;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private static final String EMAIL = "email";
    private LoginButton loginButton;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        KeyHash.keyHashes(this);


        callbackManager = CallbackManager.Factory.create();

        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList(EMAIL));

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                //startActivity(new Intent(""));
                Toast.makeText(MainActivity.this, "Acceso exitoso",
                        Toast.LENGTH_SHORT).show();

                Toast.makeText(MainActivity.this, loginResult.toString(),
                        Toast.LENGTH_SHORT).show();

                Log.d("LOGINRESULT", loginResult.toString());

            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                // App code
                Toast.makeText(MainActivity.this, "error en acceso",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void btnShare(View view){
        shareContent();
    }

    private void shareContent() {
        ShareDialog dialog = new ShareDialog(this);
        ShareLinkContent shareLinkContent = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse(getString(R.string.link)))
                .setQuote("Hola")
                .build();
        dialog.show(shareLinkContent);

    }
}
