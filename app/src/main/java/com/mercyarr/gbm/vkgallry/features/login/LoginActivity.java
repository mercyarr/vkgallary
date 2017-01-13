package com.mercyarr.gbm.vkgallry.features.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mercyarr.gbm.vkgallry.R;

public class LoginActivity extends AppCompatActivity {

    private WebView wvBrowser;

    private String AUTHORIZATION_URL = "";
    private int client_id = 11122843;


    public static String VK_FILED_ACCESS_TOKEN = "access_token";
    public static String VK_FILED_USER_ID = "user_id";

    private static String TAG = "DEBUG:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        System.out.println("DEBUG: Start Login Activity");

        initUrl();
        initView();
    }

    private void initUrl(){
        AUTHORIZATION_URL = "https://oauth.vk.com/authorize?client_id="+client_id
                +"&display=page&redirect_uri=https://oauth.vk.com/blank.html"
                +"&scope="+"friends,photos,audio,video,pages,status,notes,messages,wall,groups,notifications"
                +"&response_type=token&v=5.60&state=123456";
    }

    private void initView(){
        wvBrowser = (WebView) findViewById(R.id.wvBrowser);

        wvBrowser.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                if (checkIsAuthDone(url)) {
                    wvBrowser.setVisibility(View.GONE);
                    Intent intent = getAuthIntent(url);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

        wvBrowser.loadUrl(AUTHORIZATION_URL);
    }

    public static boolean checkIsAuthDone(String url) {
        String[] response_array = url.split("[=#&]");
        if (response_array.length > 6)
        {
            if (response_array[1].equals(VK_FILED_ACCESS_TOKEN)
                    && response_array[5].equals(VK_FILED_USER_ID)) {
                return true;
            }
        }
        return false;
    }

    public static Intent getAuthIntent(String url) {
        String[] response_array = vkAuthResponseParse(url);
        Intent data = new Intent();
        data.putExtra(VK_FILED_ACCESS_TOKEN, response_array[0]);
        data.putExtra(VK_FILED_USER_ID, Integer.parseInt(response_array[1]));
        return data;
    }

    private static String[] vkAuthResponseParse(String url){
        String[] responseArr = new String[2];
        String[] array = url.split("[=#&]");

        responseArr[0] = array[2];
        responseArr[1] = array[6];

        return responseArr;
    }
}