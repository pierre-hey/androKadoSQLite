package fr.eni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import fr.eni.bo.Article;
import fr.eni.dal.ArticleDAO;

public class InfoArticle extends AppCompatActivity {

    Article article;
    Intent intent;
    ArticleDAO articleDAO;

    TextView tvNomArticle;
    TextView tvPrixArticle;
    TextView tvDesignationArticle;
    RatingBar tvNoteArticle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_article);

        articleDAO = new ArticleDAO(this);

        // Récupére l'intention envoyée par MyAdapter (de la vue listArticle)
        intent = getIntent();
        // Recup de l'extra article
        article = intent.getParcelableExtra("article");
        Log.i("toto", "Article : " + article.getNom());


        // Toast.makeText(this, article.getUrl(), Toast.LENGTH_SHORT).show();

        // Recup champs de l'affichage
        tvNomArticle = findViewById(R.id.textView_nomArticle);
        tvPrixArticle = findViewById(R.id.textView_prix);
        tvDesignationArticle = findViewById(R.id.textView_designation);
        tvNoteArticle = findViewById(R.id.ratingBar);
        ToggleButton tbAchete = findViewById(R.id.tb_achete);

        tbAchete.setChecked(article.getAchete());

        // Création toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_info_article);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();

        article = articleDAO.getById(article.getId());

        // Nourrit les champs de la vue dans le resume, quand retour de la modification de l'article, met à jour l'affichage
        tvNomArticle.setText(article.getNom());
        String prixAvecDevise = article.getPrix() + " €";
        tvPrixArticle.setText(prixAvecDevise);
        tvDesignationArticle.setText(article.getDesignation());
        tvNoteArticle.setRating(article.getNote());
    }

    /**
     * Envoie sur la vue du "lien" de l'article
     *
     * @param view
     */
    public void onClickPlanet(View view) {
        // Récupére l'intention
        // Intent intentR = getIntent();
        //Article article =intentR.getParcelableExtra("article");

        // Toast.makeText(this, article.getUrl(), Toast.LENGTH_SHORT).show();
        // Envoie d'une nouvelle intention ver infoUrlActivity
        Intent intentURL = new Intent(this, InfoUrlActivity.class);

        // Envoi de l'extra article
        intentURL.putExtra("article", article);
        startActivity(intentURL);

    }

    /**
     * Change l'état de l'article
     *
     * @param view
     */
    public void clickAcheter(View view) {
        // ok mais risque désaccord avec front et modele
        //article.setEtat(!article.getEtat());

        //Intent intentR = getIntent();
        // Article article =intentR.getParcelableExtra("article");

        // variante je récupère le togglebutton que l'user a cliqué
        ToggleButton toggle = (ToggleButton) view;

        // et j'affecte sa valeur a mon article
        article.setAchete(toggle.isChecked());
        // Persist le nouvel état en bdd
        articleDAO.update(article);
        //Log.i("toto", "Article acheté : " + article.getAchete());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Injecte le menu 'menu_list_article' dans le menu vide créé pour la toolbar
        getMenuInflater().inflate(R.menu.menu_info_article, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_edit: {
                Toast.makeText(this, "Clic edit", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, EditArticle.class);
                // Envoi de l'extra article
                intent.putExtra("article", article);
                // Et démarre l'activité qui suit l'intention
                startActivity(intent);
                break;
            }
            case R.id.action_send: {
                Toast.makeText(this, "Clic send", Toast.LENGTH_SHORT).show();
                break;
            }
            default: {
                Log.i("toto", "I'm superman");
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void deleteArticle(View view) {
        articleDAO.delete(article);
        finish();
        Toast.makeText(this, "Article : " + article.getNom() + " supprimé avec succés.", Toast.LENGTH_SHORT).show();
    }
}