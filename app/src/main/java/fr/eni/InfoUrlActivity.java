package fr.eni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import fr.eni.bo.Article;

public class InfoUrlActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_url);

        // Récupère l'intention envoyée par InfoArticle/
        Intent intent = getIntent();

        // Recup de l'extra article
        Article article =intent.getParcelableExtra("article");

        TextView url=findViewById(R.id.tv_url);
        url.setText(article.getUrl());

        Log.i("toto", "Article : " + article.getNom() + " url : " + article.getUrl());

      // Toast.makeText(this, article.getUrl(), Toast.LENGTH_SHORT).show();

    }


}