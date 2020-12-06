package com.iut.lpsmin.livrepartage.model;

public class Livre {
    private String titre;
    private String auteur;
    private String edition;
    private Genre genre;
    private String uri;
    public Livre() {
    }

    public Livre(String titre, String auteur, String edition, Genre genre,String uri) {
        this.titre = titre;
        this.auteur = auteur;
        this.edition = edition;
        this.genre = genre;
        this.uri=uri;

    }

    @Override
    public String toString() {
        return "Livre{" +
                "titre='" + titre + '\'' +
                ", auteur='" + auteur + '\'' +
                ", edition='" + edition + '\'' +
                ", genre=" + genre +
                ", uri='" + uri + '\'' +
                '}';
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
