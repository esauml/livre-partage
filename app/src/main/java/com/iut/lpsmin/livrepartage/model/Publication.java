package com.iut.lpsmin.livrepartage.model;

public class Publication {
    private String datePublication;
    private int status;
    private Utilisateur utilisateur;
    private Livre livre;

    public Publication() {
    }

    public Publication(String datePublication, int status, Utilisateur utilisateur, Livre livre) {
        this.datePublication = datePublication;
        this.status = status;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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
