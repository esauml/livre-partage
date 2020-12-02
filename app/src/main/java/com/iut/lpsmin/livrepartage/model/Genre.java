package com.iut.lpsmin.livrepartage.model;

public class Genre {
    String nom;

    public Genre() {
    }

    public Genre(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "nom='" + nom + '\'' +
                '}';
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
