package com.iut.lpsmin.livrepartage.model;

import java.util.List;

public class Utilisateur {
    private String email;
    private String motPasse;
    private String dateDernierAccess;
    private String dateAccess;
    private List<Livre> livres;

    public Utilisateur() {
    }

    public Utilisateur(String email, String motPasse, String dateDernierAccess, String dateAccess, List<Livre> livres) {
        this.email = email;
        this.motPasse = motPasse;
        this.dateDernierAccess = dateDernierAccess;
        this.dateAccess = dateAccess;
        this.livres = livres;
    }

    public Utilisateur(String email, String motPasse, String dateDernierAccess, String dateAccess) {
        this.email = email;
        this.motPasse = motPasse;
        this.dateDernierAccess = dateDernierAccess;
        this.dateAccess = dateAccess;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotPasse() {
        return motPasse;
    }

    public void setMotPasse(String motPasse) {
        this.motPasse = motPasse;
    }

    public String getDateDernierAccess() {
        return dateDernierAccess;
    }

    public void setDateDernierAccess(String dateDernierAccess) {
        this.dateDernierAccess = dateDernierAccess;
    }

    public String getDateAccess() {
        return dateAccess;
    }

    public void setDateAccess(String dateAccess) {
        this.dateAccess = dateAccess;
    }

    public List<Livre> getLivres() {
        return livres;
    }

    public void setLivres(List<Livre> livres) {
        this.livres = livres;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "email='" + email + '\'' +
                ", motPasse='" + motPasse + '\'' +
                ", dateDernierAccess='" + dateDernierAccess + '\'' +
                ", dateAccess='" + dateAccess + '\'' +
                ", livres=" + livres +
                '}';
    }
}
