package fr.eni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import fr.eni.bo.Article;

public class InfoUrlActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_url);

        // Récupère l'intention envoyée par InfoArticle/
        Intent intent = getIntent();

        // Recup de l'extra article
        Article article = intent.getParcelableExtra("article");

        TextView url = findViewById(R.id.infoUrl_tv_url);
        url.setText(article.getUrl());

         Log.i("toto", "InfoUrlActivity / onCreate / article : " + article.getNom() + " url : " + article.getUrl());


        webView = findViewById(R.id.infoUrl_wv);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl(article.getUrl());
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Intercepté le click sur retour pour le répércuter sur la webview
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}