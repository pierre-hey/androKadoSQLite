package fr.eni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import fr.eni.bo.Article;
import fr.eni.dal.ArticleDAO;

public class AddArticle extends AppCompatActivity {
    private EditText etNomArticle;
    private EditText etPrixArticle;
    private EditText etDesignationArticle;
    private RatingBar rbNoteArticle;
    private EditText etURLArticle;

    private ArticleDAO articleDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_article);

        articleDAO = new ArticleDAO(this);

        etNomArticle = findViewById(R.id.add_article_et_nom);
        etPrixArticle = findViewById(R.id.add_article_et_prix);
        etDesignationArticle = findViewById(R.id.add_article_et_designation);
        rbNoteArticle = findViewById(R.id.add_article_rb_note);
        etURLArticle = findViewById(R.id.add_article_et_url);


        SharedPreferences sp = getSharedPreferences("pref", MODE_PRIVATE);
        etPrixArticle.setText(sp.getString("prix_defaut",null));
    }

    public void ajouterArticle(View view) {


        Article article = new Article();
        article.setNom(etNomArticle.getText().toString());
        article.setPrix(Float.parseFloat(etPrixArticle.getText().toString()));
        article.setDesignation(etDesignationArticle.getText().toString());
        article.setNote((int) rbNoteArticle.getRating());
        article.setUrl(etURLArticle.getText().toString());
        article.setAchete(false);

        Log.i("toto", "AddArticle - ajouterArticle - " + article);

        articleDAO.insert(article);

        // Retourne sur page précédente
        finish();
        Toast.makeText(this, "Article : " + article.getNom() + " ajouté avec succés.", Toast.LENGTH_SHORT).show();
    }
}