package com.example.touki.webview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;
import android.content.res.AssetManager;
import android.webkit.WebSettings;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private final Activity activity=this;
    WebView myWebView =null;
    Button button =null;
    boolean ischange=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebAppInterface wai=new WebAppInterface(this);
         myWebView = (WebView) findViewById(R.id.webView1);
        if(!isConnected(MainActivity.this)) {
            Toast.makeText(MainActivity.this,"Connect to Internet",Toast.LENGTH_LONG).show();
            Intent i=new Intent();
            i.setAction("com.example.touki.webview");
            i.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            sendBroadcast(i);
           /* String summary = "<html><body><p>No <b>Internet</b>? Don't worry.</p></body></html>";
            myWebView.loadData(summary, "text/html", null);*/
        }
        else
        {
            Toast.makeText(MainActivity.this,"Internet is OK",Toast.LENGTH_LONG).show();
            //webview.loadUrl("https://www.aiub.edu");
            myWebView.loadUrl("file:///android_asset/web/myHtml.html");
            WebSettings webSettings = myWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            myWebView.addJavascriptInterface(wai, "Android");
            //myWebView.setWebViewClient(new WebViewClient());
        }
        Log.d("Web Activity", "on create");
    }

    public boolean isConnected(Context context)
    {
        ConnectivityManager cm =(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=cm.getActiveNetworkInfo();

        if(networkInfo !=null && networkInfo.isConnectedOrConnecting())
        {
            android.net.NetworkInfo wifi=cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile=cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile !=null && mobile.isConnectedOrConnecting())||(wifi!=null&&wifi.isConnectedOrConnecting())) return true;
            else return false;
        }
        else return false;
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        Log.d("Web Activity", "destroid");
    }

    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        super.onRestart();
        Log.d("Web Activity", "restart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Web Activity", "paused");
    }
    @Override
    protected void onResume() {
        super.onResume();
        //AssetManager assetManager = getAssets();
        //InputStream input = assetManager.open(web_calc);
        Log.d("Web Activity", "resume");
        //myWebView.loadUrl("file:///android_asset/myHtml.html");
        //myWebView.loadUrl("http://www.google.com/index.html");

    }
}
