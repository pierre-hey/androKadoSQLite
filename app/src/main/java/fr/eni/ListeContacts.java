package fr.eni;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fr.eni.adapter.ContactAdapter;
import fr.eni.bo.Article;
import fr.eni.bo.Contact;

public class ListeContacts extends AppCompatActivity {

    private static Article article;

    RecyclerView rv_contacts;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_contacts);

        intent = getIntent();
        article = intent.getParcelableExtra("article");

        /*
            pour demander la permission à la création de la vue, si accepte une fois
            => ne redemande pas, si refus plusieurs fois, peut ne plus demander
         */
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.SEND_SMS},
                123);

        // Création du RecyclerView
        rv_contacts = findViewById(R.id.rv_contacts);
        // empeche redimension
        rv_contacts.setHasFixedSize(true);
        // Ajout d'un linear layout pour expliciter que les éléments s'affichent l'un au dessus de l'autre
        RecyclerView.LayoutManager myLayout = new LinearLayoutManager(this);
        //Affecte le layout au recycler View
        rv_contacts.setLayoutManager(myLayout);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 123: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    bindCardView();
                }
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + requestCode);
        }
    }

    private void bindCardView() {
        List<Contact> contacts = new ArrayList<>();
        ContentResolver cr = getContentResolver();

        // Cursor pour contact
        Cursor cursorContact = cr.query(
                ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);

        // tant qu'il y a des contacts : récupère le l'id et le nom
        while (cursorContact.moveToNext()) {
            String numTel = "";
            String id = cursorContact.getString(cursorContact.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
            String nom = cursorContact.getString(cursorContact.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));

            // Cursor pour num tel : on récupére le num en fonction de l'id du contact, si le contact n'a pas de numéro => num tel = ""
            Cursor cursorPhones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id, null, null);

            while (cursorPhones.moveToNext()) {
                numTel = cursorPhones.getString(cursorPhones.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
            }
            cursorPhones.close();
            // création du contact et ajout dans la liste
            Contact contact = new Contact(Integer.parseInt(id), nom, numTel);
            contacts.add(contact);
        }
        ContactAdapter contactAdapter = new ContactAdapter(contacts, this);
        cursorContact.close();
        rv_contacts.setAdapter(contactAdapter);
    }


    public static void sendMessage(Context context, Contact contact) {
        SmsManager manager = SmsManager.getDefault();
        manager.sendTextMessage(contact.getNumTel(),
                null,
                "Bonjour " + contact.getNom() + ", j'aimerais bien avoir ceci : " + article.getNom(),
                null, null);

        Toast.makeText(context, "Message envoyé à : " + contact.getNom() +
                " pour l'article : " + article.getNom(), Toast.LENGTH_SHORT).show();

    }
}