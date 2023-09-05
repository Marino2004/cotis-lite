package com.code.cotislite.model;

public class Vehicule {
    private int idVehicule;
    private String numeroVehicule;
    private String marqueVehicule;
    private int nbPlaceVehicule;
    private int placeLeftVehicule;

    public int getIdVehicule() {
        return idVehicule;
    }

    public void setIdVehicule(int idVehicule) {
        this.idVehicule = idVehicule;
    }

    public String getNumeroVehicule() {
        return numeroVehicule;
    }

    public void setNumeroVehicule(String numeroVehicule) {
        this.numeroVehicule = numeroVehicule;
    }

    public String getMarqueVehicule() {
        return marqueVehicule;
    }

    public void setMarqueVehicule(String marqueVehicule) {
        this.marqueVehicule = marqueVehicule;
    }

    public int getNbPlaceVehicule() {
        return nbPlaceVehicule;
    }

    public void setNbPlaceVehicule(int nbPlaceVehicule) {
        this.nbPlaceVehicule = nbPlaceVehicule;
    }

    public int getPlaceLeftVehicule() {
        return placeLeftVehicule;
    }

    public void setPlaceLeftVehicule(int placeLeftVehicule) {
        this.placeLeftVehicule = placeLeftVehicule;
    }

    public Vehicule(String numeroVehicule, String marqueVehicule, int nbPlaceVehicule,int placeLeftVehiculeaceLeft) {
        this.idVehicule = idVehicule;
        this.numeroVehicule = numeroVehicule;
        this.marqueVehicule = marqueVehicule;
        this.nbPlaceVehicule = nbPlaceVehicule;
        this.placeLeftVehicule = this.nbPlaceVehicule;
    }

    public Vehicule(int idVehicule,String numeroVehicule, String marqueVehicule, int nbPlaceVehicule) {
        this.idVehicule = idVehicule;
        this.numeroVehicule = numeroVehicule;
        this.marqueVehicule = marqueVehicule;
        this.nbPlaceVehicule = nbPlaceVehicule;
        this.placeLeftVehicule = this.nbPlaceVehicule;
    }
}
