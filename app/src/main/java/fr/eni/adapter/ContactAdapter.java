package fr.eni.adapter;

import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.eni.R;
import fr.eni.bo.Article;
import fr.eni.bo.Contact;
import androidx.recyclerview.widget.RecyclerView;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    private List<Contact> listeContacts;
    private Context activity;

    public ContactAdapter(List<Contact> listeContacts, Context activity) {
        this.listeContacts = listeContacts;
        this.activity = activity;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_ligne_contact, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Contact contact = listeContacts.get(position);
        holder.tv_nom_contact.setText(contact.getNom());
        holder.tv_numTel_contact.setText(contact.getNumTel());
    }

    @Override
    public int getItemCount() {
        return listeContacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView tv_nom_contact;
        public TextView tv_numTel_contact;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_nom_contact = itemView.findViewById(R.id.cardview_tv_nom_contact);
            tv_numTel_contact = itemView.findViewById(R.id.cardview_tv_tel_contact);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
           // Contact contact = listeContacts.get(position);
            // Sur ce onClick on pourra selectionner le contact pour envoyer le message
            /*Intent intent = new Intent(activity, )*/
           // Toast.makeText(activity, "Envoyer un message a : "+ contact.getNom(), Toast.LENGTH_SHORT).show();

          /*  Intent intent = new Intent(activity,null);
            intent.putExtra("contact",contact);*/

        }
    }
}
