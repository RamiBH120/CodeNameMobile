package com.org.tn.entities;

import java.util.Date;

public class Offre {
    private int id;
    private String type;
    private String image;
    private String description;
    private float prix;
    private float promotion;
    private Date dateDebut;
    private Date dateFin;

    public Offre(int id, String type, String image, String description, float prix, float promotion, Date dateDebut, Date dateFin) {
        this.id = id;
        this.type = type;
        this.image = image;
        this.description = description;
        this.prix = prix;
        this.promotion = promotion;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public Offre(String type, String image, String description, float prix, float promotion, Date dateDebut, Date dateFin) {
        this.type = type;
        this.image = image;
        this.description = description;
        this.prix = prix;
        this.promotion = promotion;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public Offre() {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public float getPromotion() {
        return promotion;
    }

    public void setPromotion(float promotion) {
        this.promotion = promotion;
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

    @Override
    public String toString() {
        return "Offre{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                ", promotion=" + promotion +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                '}';
    }
}
