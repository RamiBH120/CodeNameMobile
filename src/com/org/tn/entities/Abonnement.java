package com.org.tn.entities;

import java.util.Date;

public class Abonnement {

    private int id;
    private String type;
    private float prix;
    private Date dateDebut;
    private Date dateFin;
    private String user;
    private String offre;

    public Abonnement(int id, String type, float prix, Date dateDebut, Date dateFin, String user, String offre) {
        this.id = id;
        this.type = type;
        this.prix = prix;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.user = user;
        this.offre = offre;
    }

    public Abonnement(String type, float prix, Date dateDebut, Date dateFin, String user, String offre) {
        this.type = type;
        this.prix = prix;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.user = user;
        this.offre = offre;
    }

    public Abonnement() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getOffre() {
        return offre;
    }

    public void setOffre(String offre) {
        this.offre = offre;
    }

    @Override
    public String toString() {
        return "Abonnement{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", prix=" + prix +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", user='" + user + '\'' +
                ", offre='" + offre + '\'' +
                '}';
    }
}
