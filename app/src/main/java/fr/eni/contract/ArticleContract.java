package fr.eni.contract;

public class ArticleContract {
    // On définit le nom de la table qui stock les articles
    public static final String TABLE_NAME = "articles";

    // On définit les noms des colonnes des arguments des articles
    public static final String COL_ID = "id";
    public static final String COL_NOM = "nom";
    public static final String COL_PRIX = "prix";
    public static final String COL_DESIGNATION = "designation";
    public static final String COL_NOTE = "note";
    public static final String COL_URL = "url";
    public static final String COL_IS_ACHETE = "is_achete";


    // Requete pour création de la table
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " ("
                    + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COL_NOM + " TEXT,"
                    + COL_PRIX + " REAL,"
                    + COL_DESIGNATION + " TEXT,"
                    + COL_NOTE + " INTEGER,"
                    + COL_URL + " TEXT,"
                    + COL_IS_ACHETE + " NUMERIC"
                    + ");";

    // requete pour suppression de la table
    public static final String DROP_TABLE = "DROP TABLE " + TABLE_NAME;
}
