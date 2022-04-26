package fr.eni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import fr.eni.adapter.ArticleAdapter;
import fr.eni.bo.Article;
import fr.eni.dal.ArticleDAO;

public class ListeArticlesActivity extends AppCompatActivity {
    private RecyclerView rv_articles;
    private ArticleDAO articleDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_articles);

        // Création du RecyclerView
        rv_articles = findViewById(R.id.rv_articles);
        // Empeche la redimension de la liste par l'utilisateur. Pour performance.
        rv_articles.setHasFixedSize(true);
        // Ajout d'un linear layout pour expliciter que les éléments s'affichent l'un au dessus de l'autre
        RecyclerView.LayoutManager myLayout = new LinearLayoutManager(this);
        //Affecte le layout au recycler View
        rv_articles.setLayoutManager(myLayout);

        // Création toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_liste_article);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Création de la liste d'articles
        articleDAO = new ArticleDAO(this);
        List<Article> listArticles = new ArrayList<>();

        // Etape de tri :  Est-ce que je dois trier ?
        SharedPreferences sp = getSharedPreferences("pref", MODE_PRIVATE);
        boolean triActif = sp.getBoolean("config_tri", false);
        Log.i("toto","ListeArticlesActivity - onResume - triActif : " + triActif);

        // Récup liste d'article
        listArticles=articleDAO.getAll(triActif);

        // Création de l'adapter qui fait lien entre vue et données
        ArticleAdapter adapter = new ArticleAdapter(listArticles, this);
        // Affecte l'adapter au RecyclerView
        rv_articles.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Injecte le menu 'menu_list_article' dans le menu vide créé pour la toolbar
        getMenuInflater().inflate(R.menu.menu_list_article,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:{
                // OU démarre 1 activité formulaire activity (qui fait formulaire pour edition et création)
                Toast.makeText(this, "Clic add", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,AddArticle.class);
                startActivity(intent);

                break;
            }
            case R.id.action_settings:{
                Toast.makeText(this, "Clic settings", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,ConfigurationActivity.class);
                startActivity(intent);
                break;
            }
            default:{
                Log.i("toto","I'm superman");
            }
        }
        return super.onOptionsItemSelected(item);
    }
}