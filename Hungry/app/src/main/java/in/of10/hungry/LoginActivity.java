package in.of10.hungry;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

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

        mLogin.setOnHoverListener(new View.OnHoverListener() {
            @Override
            public boolean onHover(View view, MotionEvent motionEvent) {
                String url = "http://www.underconsideration.com/brandnew/archives/facebook_2015_logo_detail.png";
                Picasso.with(LoginActivity.this).load(url)
                        .into(mLogo);
                return true;
            }
        });

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("app","Login Button Clicked");
                Log.d("app", "Username:" + mUsername.getText().toString());

                Intent i = new Intent(LoginActivity.this, RestaurantsActivity.class);
                startActivity(i);
            }
        });

    }


}

