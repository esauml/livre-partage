package com.iut.lpsmin.livrepartage.recyclerview.profile;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.textfield.TextInputLayout;
import com.iut.lpsmin.livrepartage.R;
import com.iut.lpsmin.livrepartage.fragments.profile.LivresFragment;
import com.iut.lpsmin.livrepartage.model.Livre;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class LivreAdapter extends FirebaseRecyclerAdapter<
        Livre, LivreAdapter.LivreViewholder> {

    public LivreAdapter(
            @NonNull FirebaseRecyclerOptions<Livre> options)
    {
        super(options);
    }

    // Function to bind the view in Card view(here
    // "person.xml") iwth data in
    // model class(here "person.class")
    @Override
    protected void
    onBindViewHolder(@NonNull LivreViewholder holder,
                     int position, @NonNull Livre model)
    {

        Log.d(LivresFragment.TAG_CLASS, model.toString());

        // add image from url: firebase to ImageView
        //
        try {
            URL url = null;
            url = new URL(model.getUri());
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            holder.imgBook.setImageBitmap(bmp);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        holder.titreTextInputLayout.getEditText().setText(model.getTitre());
        holder.genreTextInputLayout.getEditText().setText(model.getGenre().getNom());
        holder.auteurTextInputLayout.getEditText().setText(model.getAuteur());
        holder.editionTextInputLayout.getEditText().setText(model.getEdition());

        // Add lastname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")

        //holder.lastname.setText(model.getLastname());
    }

    // Function to tell the class about the Card view
    // which the data will be shown
    @NonNull
    @Override
    public LivreViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_book, parent, false);
        return new LivreAdapter.LivreViewholder(view);
    }

    // Sub Class to create references of the views in Card
    // view (here "card_book.xml")
    class LivreViewholder
            extends RecyclerView.ViewHolder {
        ImageView imgBook;
        private TextInputLayout auteurTextInputLayout;
        private TextInputLayout editionTextInputLayout;
        private TextInputLayout genreTextInputLayout;
        private TextInputLayout titreTextInputLayout;

        public LivreViewholder(@NonNull View itemView)
        {
            super(itemView);

            imgBook = itemView.findViewById(R.id.livre_img);
            auteurTextInputLayout = itemView.findViewById(R.id.livre_auteur);
            editionTextInputLayout = itemView.findViewById(R.id.livre_edition);
            genreTextInputLayout = itemView.findViewById(R.id.livre_genre);
            titreTextInputLayout = itemView.findViewById(R.id.livre_titre);
        }
    }
}
