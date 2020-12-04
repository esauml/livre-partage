package com.iut.lpsmin.livrepartage.model;

public class Echange {
    private String dateExpedition;
    private Publication publication;

    public Echange() {
    }

    public Echange(String dateExpedition, Publication publication) {
        this.dateExpedition = dateExpedition;
        this.publication = publication;
    }

    @Override
    public String toString() {
        return "Echange{" +
                "dateExpedition='" + dateExpedition + '\'' +
                ", publication=" + publication +
                '}';
    }

    public String getDateExpedition() {
        return dateExpedition;
    }

    public void setDateExpedition(String dateExpedition) {
        this.dateExpedition = dateExpedition;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }
}
