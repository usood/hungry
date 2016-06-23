package com.kiran.jhakasfood;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class LoginActivity extends AppCompatActivity {

    EditText mUsername;
    EditText mPassword;
    Button mLogin;

    ImageView mLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);
        mLogin = (Button) findViewById(R.id.login);
        mLogo = (ImageView) findViewById(R.id.logo);

        mUsername.setText("Harsh JAin");
        mLogin.setText("BOom Boom");

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = "http://www.underconsideration.com/brandnew/archives/facebook_2015_logo_detail.png";
                Picasso.with(LoginActivity.this).load(url)
                        .into(mLogo);


            }
        });
    }
}
