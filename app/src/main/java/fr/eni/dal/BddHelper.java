package fr.eni.dal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import fr.eni.contract.ArticleContract;

// gére connexion à la BDD
public class BddHelper extends SQLiteOpenHelper {
    private static final String BDD_NAME = "AndroKado_bd";
    // Version x de la BDD
    private static final int VERSION = 1;

    // Seule contexte peut changer
    public BddHelper(@Nullable Context context) {
        super(context, BDD_NAME, null, VERSION);
    }

    // onCreate de la BDD (pas de l'application)
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ArticleContract.CREATE_TABLE);
    }

    // Si changement de version de la BDD (pour ajout colonne, modif type d'un champ...)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Supprime les données, la base puis recréer la table
        db.execSQL(ArticleContract.DROP_TABLE);
        onCreate(db);
    }
}
