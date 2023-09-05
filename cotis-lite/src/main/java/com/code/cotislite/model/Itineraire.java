package com.code.cotislite.model;

public class Itineraire {
    private String codeItineraire;
    private String nomItineraire;
    private int distanceItineraire;
    private float prixItineraire;


    public String getCodeItineraire(){
        return   codeItineraire;
    }
    public void setCodeItineraire(String codeItineraire) {
        this.codeItineraire = codeItineraire;
    }

    public String getNomItineraire() {
        return nomItineraire;
    }

    public void setNomItineraire(String nomItineraire) {
        this.nomItineraire = nomItineraire;
    }

    public int getDistanceItineraire() {
        return distanceItineraire;
    }

    public void setDistanceItineraire(int distanceItineraire) {
        this.distanceItineraire = distanceItineraire;
    }

    public float getPrixItineraire() {
        return prixItineraire;
    }

    public void setPrixItineraire(float prixItineraire) {
        this.prixItineraire = prixItineraire;
    }

    public Itineraire(String codeItineraire, String nomItineraire, int distanceItineraire, float prixItineraire) {
        this.codeItineraire = codeItineraire;
        this.nomItineraire = nomItineraire;
        this.distanceItineraire = distanceItineraire;
        this.prixItineraire = prixItineraire;
    }
}
