package com.iut.lpsmin.livrepartage.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Publication {
    private String datePublication;
    private boolean status;
    private Utilisateur utilisateur;
    private Livre livre;

    public Publication() {
    }

    public Publication( Utilisateur utilisateur, Livre livre) {
        this.datePublication = new SimpleDateFormat("yyyy.MM.dd").format(Calendar.getInstance().getTime());
        this.status = false;
        this.utilisateur = utilisateur;
        this.livre = livre;
    }

    @Override
    public String toString() {
        return "Publication{" +
                "datePublication='" + datePublication + '\'' +
                ", status=" + status +
                ", utilisateur=" + utilisateur +
                ", livre=" + livre +
                '}';
    }

    public String getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(String datePublication) {
        this.datePublication = datePublication;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }
}
