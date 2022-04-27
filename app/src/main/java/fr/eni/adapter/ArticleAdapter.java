package fr.eni.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.eni.InfoArticle;
import fr.eni.R;
import fr.eni.bo.Article;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    private List<Article> listeArticles;
    private Context activity;

    public ArticleAdapter(List<Article> listeArticles, Context activity) {
        this.listeArticles = listeArticles;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Log.i("toto","Création view holder");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_ligne_article, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Log.i("toto","MAJ du viewholder num : " + position);
        Article data = listeArticles.get(position);
        holder.tv_nom_article.setText(data.getNom());
        holder.rb_note_article.setRating(data.getNote());
    }

    @Override
    public int getItemCount() {
        return listeArticles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tv_nom_article;
        public RatingBar rb_note_article;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_nom_article = itemView.findViewById(R.id.cv_tv_nomArticle);
            rb_note_article = itemView.findViewById(R.id.cv_rb_article);
            // this désigne le view holder capable de gérer un clic puisqu'il implement OnClickListener
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Article article = listeArticles.get(position);
            //Toast.makeText(activity, article.getNom() + " - " + String.valueOf(position), Toast.LENGTH_SHORT).show();

            // activity correspond à "this" (context) qui est récupéré dans l'adapter
            // Envoi l'intention a l'activité InfoArticle pour affichage des détails de l'article
            Intent intent = new Intent(activity, InfoArticle.class);
            // Envoi de l'extra article
            intent.putExtra("article", article);
            // Et démarre l'activité qui suit l'intention
            activity.startActivity(intent);

        }
    }
}
