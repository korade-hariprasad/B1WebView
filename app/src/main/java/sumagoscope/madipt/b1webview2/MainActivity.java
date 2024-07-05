package sumagoscope.madipt.b1webview2;

import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton btnNext, btnBack, btnSearch;
    WebView webView;
    EditText etUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBack = findViewById(R.id.btnBack);
        btnNext = findViewById(R.id.btnNext);
        btnSearch = findViewById(R.id.btnSearch);
        webView = findViewById(R.id.webView);
        etUrl = findViewById(R.id.etUrl);

        webView.loadUrl("https://www.google.com");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new CustomWebViewClient());

        btnSearch.setOnClickListener(v->{
            webView.loadUrl(etUrl.getText().toString());
        });

        btnNext.setOnClickListener(v->{
            if(webView.canGoForward()) webView.goForward();
        });

        btnBack.setOnClickListener(v->{
            if(webView.canGoBack()) webView.goBack();
        });
    }

    private class CustomWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            etUrl.setEnabled(false);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            etUrl.setEnabled(true);
            etUrl.setText(url);
        }
    }
}