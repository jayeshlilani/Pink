package womensafety.com.pink;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class webview extends AppCompatActivity {

    WebView wv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        wv= (WebView) findViewById(R.id.wv);
        WebSettings webSettings=wv.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wv.loadUrl("https://jayeshlilani36.wixsite.com/index");
    }
}
