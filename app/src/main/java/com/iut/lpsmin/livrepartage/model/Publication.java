package com.iut.lpsmin.livrepartage.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Publication {
    private String datePublication;
    private boolean status;

    private Livre livre;

    public Publication() {
    }

    public Publication(  Livre livre) {
        this.datePublication = new SimpleDateFormat("yyyy.MM.dd").format(Calendar.getInstance().getTime());
        this.status = false;

        this.livre = livre;
    }

    @Override
    public String toString() {
        return "Publication{" +
                "datePublication='" + datePublication + '\'' +
                ", status=" + status +
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


    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }
}
