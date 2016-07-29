package in.of10.hungry;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import in.of10.hungry.service.LoginService;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    EditText mUsername;
    EditText mPassword;
    Button mLogin;
    Button mLoginFacebook;
    ImageView mLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);
        mLogin = (Button) findViewById(R.id.login);
        mLoginFacebook = (Button) findViewById(R.id.loginWithFacebook);
        mLogo = (ImageView) findViewById(R.id.logo);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("app","Login Button Clicked");
                Log.d("app", "Username:" + mUsername.getText().toString());
                LoginService.getInstance().login(mUsername.getText().toString(), mPassword.getText().toString(), new OnLoggedInCallback() {
                    @Override
                    public void loginComplete(boolean success) {
                        Intent i = new Intent(LoginActivity.this, RestaurantsActivity.class);
                        startActivity(i);
                    }
                });
            }
        });

    }


}

