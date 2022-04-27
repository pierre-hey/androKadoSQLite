package fr.eni.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fr.eni.bo.Article;
import fr.eni.contract.ArticleContract;

public class ArticleDAO {
    private SQLiteDatabase db;

    // Constructeur du DAO
    public ArticleDAO(Context context) {
        // init le vddhelper et recupere instance bddhelper
        BddHelper helper = new BddHelper(context);
        db = helper.getWritableDatabase(); // Permet lecture/écriture/suppression
    }

    public void insert(Article article) {
        // Equivalent à un HashMap avec un systeme de clé/valeur, on nourrit chaque champ avec les valeurs reçues
        ContentValues cv = new ContentValues();
        cv.put(ArticleContract.COL_NOM, article.getNom());
        cv.put(ArticleContract.COL_PRIX, article.getPrix());
        cv.put(ArticleContract.COL_DESIGNATION, article.getDesignation());
        cv.put(ArticleContract.COL_NOTE, article.getNote());
        cv.put(ArticleContract.COL_URL, article.getUrl());
        cv.put(ArticleContract.COL_IS_ACHETE, article.getAchete());

        // On insert le ContentValues dans la bdd
        db.insert(ArticleContract.TABLE_NAME, null, cv);
    }

    public Article getById(int id) {
        Article article = null;
        // Pour récupérer un article dans bdd, on utilise le curseur.
        Cursor cursor = db.query(ArticleContract.TABLE_NAME,
                null, // -> correspond à un SELECT *
                ArticleContract.COL_ID + " = ?", // WHERE ID = ?
                new String[]{String.valueOf(id)}, // valeur de l'id
                null, null, null
        );
        // cursor = resultSet en Java (= tableau qui pointe sur la 1ere ligne,
        // puis on doit parcourir ligne par ligne) ici 1 seule ligne (findbyid)
        if (cursor.moveToNext()) {
            article = new Article();
            article.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ArticleContract.COL_ID)));
            article.setNom(cursor.getString(cursor.getColumnIndexOrThrow(ArticleContract.COL_NOM)));
            article.setPrix(cursor.getFloat(cursor.getColumnIndexOrThrow(ArticleContract.COL_PRIX)));
            article.setDesignation(cursor.getString(cursor.getColumnIndexOrThrow(ArticleContract.COL_DESIGNATION)));
            article.setNote(cursor.getInt(cursor.getColumnIndexOrThrow(ArticleContract.COL_NOTE)));
            article.setUrl(cursor.getString(cursor.getColumnIndexOrThrow(ArticleContract.COL_URL)));
            // val != 0 => (=1)-> True ||||| val != 1 => (=0)-> False
            article.setAchete(cursor.getShort(cursor.getColumnIndexOrThrow(ArticleContract.COL_IS_ACHETE)) != 0);
        }

        // On ferme le curseur et on retourne l'article
        cursor.close();
        return article;
    }

    // Ou prend un boolean en param de méthode pour le tri
    public List<Article> getAll(boolean triActif) {
        List<Article> articles = new ArrayList<>();

        Cursor cursor = db.query(ArticleContract.TABLE_NAME,
                null,
                null,
                null,
                null, null,
                triActif ? ArticleContract.COL_PRIX + " DESC" : null           // -> si tri actif, tri sur la col de prix
        );
        // cursor = resultSet en Java (= tableau qui pointe sur la 1ere ligne,
        // tant qu'il y a une ligne suivante on récupère les articles et on les ajoute dans la liste que l'on retournera
        while (cursor.moveToNext()) {
            Article article = new Article();
            article.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ArticleContract.COL_ID)));
            article.setNom(cursor.getString(cursor.getColumnIndexOrThrow(ArticleContract.COL_NOM)));
            article.setPrix(cursor.getFloat(cursor.getColumnIndexOrThrow(ArticleContract.COL_PRIX)));
            article.setDesignation(cursor.getString(cursor.getColumnIndexOrThrow(ArticleContract.COL_DESIGNATION)));
            article.setNote(cursor.getInt(cursor.getColumnIndexOrThrow(ArticleContract.COL_NOTE)));
            article.setUrl(cursor.getString(cursor.getColumnIndexOrThrow(ArticleContract.COL_URL)));
            // val != 0 => (=1)-> True ||||| val != 1 => (=0)-> False
            article.setAchete(cursor.getShort(cursor.getColumnIndexOrThrow(ArticleContract.COL_IS_ACHETE)) != 0);

            articles.add(article);
        }
        // On ferme le curseur et on retourne la liste d'article
        cursor.close();
        return articles;
    }


    public void update(Article article) {
        ContentValues cv = new ContentValues();

        cv.put(ArticleContract.COL_NOM, article.getNom());
        cv.put(ArticleContract.COL_PRIX, article.getPrix());
        cv.put(ArticleContract.COL_DESIGNATION, article.getDesignation());
        cv.put(ArticleContract.COL_NOTE, article.getNote());
        cv.put(ArticleContract.COL_URL, article.getUrl());
        cv.put(ArticleContract.COL_IS_ACHETE, article.getAchete());

        // update renvoie le nb de ligne modifié
        db.update(ArticleContract.TABLE_NAME,
                cv,
                ArticleContract.COL_ID + " = ?",
                new String[]{String.valueOf(article.getId())});
    }

    public void delete(Article article) {
        // delete renvoie le nb de ligne supprimées
        db.delete(ArticleContract.TABLE_NAME,
                ArticleContract.COL_ID + " = ?",
                new String[]{String.valueOf(article.getId())}
        );
    }


}
