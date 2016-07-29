package in.of10.hungry.service;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;
import in.of10.hungry.OnLoggedInCallback;

/**
 * Created by upaharsood on 17/07/16.
 */
public class LoginService {

    private static LoginService INSTANCE = new LoginService();

    private AsyncHttpClient client;

    public static LoginService getInstance() {
        return INSTANCE;
    }

    private LoginService() {
        client = new AsyncHttpClient();
    }

    public void setContext(Context ctxt) {
        client.setCookieStore(new PersistentCookieStore(ctxt));
    }

    public void login(String email, String password, final OnLoggedInCallback callback){
        RequestParams params = new RequestParams();
        params.add("email", email);
        params.add("password", password);

        client.post("http://www.jhakasfood.com/api/login", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.i("app", "Login " + statusCode);
                callback.loginComplete(true);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.i("app", "Login " + statusCode);
                callback.loginComplete(false);
            }
        });

    }


}
