package fr.eni.bo;

import android.os.Parcel;
import android.os.Parcelable;

public class Article implements Parcelable {
    private int id;
    private String nom;
    private Float prix;
    private String designation;
    private Integer note;
    private String url;
    private Boolean isAchete;

    public Article() {
    }

    public Article(int id, String nom, Float prix, String designation, Integer note, String url, Boolean isAchete) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.designation = designation;
        this.note = note;
        this.url = url;
        this.isAchete = isAchete;
    }

    public Article(String nom, Float prix, String designation, Integer note, String url, Boolean isAchete) {
        this.nom = nom;
        this.prix = prix;
        this.designation = designation;
        this.note = note;
        this.url = url;
        this.isAchete = isAchete;
    }


    protected Article(Parcel in) {
        id = in.readInt();
        nom = in.readString();
        if (in.readByte() == 0) {
            prix = null;
        } else {
            prix = in.readFloat();
        }
        designation = in.readString();
        if (in.readByte() == 0) {
            note = null;
        } else {
            note = in.readInt();
        }
        url = in.readString();
        byte tmpIsAchete = in.readByte();
        isAchete = tmpIsAchete == 0 ? null : tmpIsAchete == 1;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nom);
        if (prix == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(prix);
        }
        dest.writeString(designation);
        if (note == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(note);
        }
        dest.writeString(url);
        dest.writeByte((byte) (isAchete == null ? 0 : isAchete ? 1 : 2));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    public Boolean getAchete() {
        return isAchete;
    }

    public void setAchete(Boolean achete) {
        isAchete = achete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prix=" + prix +
                ", designation='" + designation + '\'' +
                ", note=" + note +
                ", url='" + url + '\'' +
                ", isAchete=" + isAchete +
                '}';
    }
}
