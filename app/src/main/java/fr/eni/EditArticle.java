package fr.eni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import fr.eni.bo.Article;
import fr.eni.dal.ArticleDAO;

public class EditArticle extends AppCompatActivity {
    Article article;
    Intent intent;

    EditText etNomArticle;
    EditText etPrixArticle;
    EditText etDesignationArticle;
    RatingBar rbNoteArticle;
    EditText etURLArticle;

    ArticleDAO articleDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_article);

        articleDAO = new ArticleDAO(this);
        intent = getIntent();
        // Recup de l'extra article
        article = intent.getParcelableExtra("article");
        //Log.i("toto", "EditeArticle - onCreate -  "+ article.toString() );

        etNomArticle = findViewById(R.id.edit_article_et_nom);
        etPrixArticle = findViewById(R.id.edit_article_et_prix);
        etDesignationArticle = findViewById(R.id.edit_article_et_designation);
        rbNoteArticle = findViewById(R.id.edit_article_rb_note);
        etURLArticle = findViewById(R.id.edit_article_et_url);

        etNomArticle.setText(article.getNom());
        etPrixArticle.setText(String.valueOf(article.getPrix()));
        etDesignationArticle.setText(article.getDesignation());
        rbNoteArticle.setRating(article.getNote());
        etURLArticle.setText(article.getUrl());
    }

    public void editArticle(View view) {
        //article.setId(article.getId());
        article.setNom(etNomArticle.getText().toString());
        article.setPrix(Float.parseFloat(etPrixArticle.getText().toString()));
        article.setDesignation(etDesignationArticle.getText().toString());
        article.setNote((int) rbNoteArticle.getRating());
        article.setUrl(etURLArticle.getText().toString());
        article.setAchete(false);

        articleDAO.update(article);

        // Retourne sur page précédente
        finish();
        Toast.makeText(this, "Article modifié avec succés.", Toast.LENGTH_SHORT).show();
    }

    public void editCancel(View view) {
        finish();
    }
}