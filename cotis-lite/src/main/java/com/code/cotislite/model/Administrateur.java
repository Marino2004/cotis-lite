package com.code.cotislite.model;

public class Administrateur {
    private int idAdmin;
    private String pseudoAdmin;
    private String passwordAdmin;
    private String contactAdmin;

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getPseudoAdmin() {
        return pseudoAdmin;
    }

    public void setPseudoAdmin(String pseudoAdmin) {
        this.pseudoAdmin = pseudoAdmin;
    }

    public String getPasswordAdmin() {
        return passwordAdmin;
    }

    public void setPasswordAdmin(String passwordAdmin) {
        this.passwordAdmin = passwordAdmin;
    }

    public String getContactAdmin() {
        return contactAdmin;
    }

    public void setContactAdmin(String contactAdmin) {
        this.contactAdmin = contactAdmin;
    }


    public Administrateur(String pseudoAdmin, String passwordAdmin, String contactAdmin) {
        this.pseudoAdmin = pseudoAdmin;
        this.passwordAdmin = passwordAdmin;
        this.contactAdmin = contactAdmin;
    }
    public Administrateur(int idAdmin,String pseudoAdmin, String passwordAdmin, String contactAdmin) {
        this.idAdmin = idAdmin;
        this.pseudoAdmin = pseudoAdmin;
        this.passwordAdmin = passwordAdmin;
        this.contactAdmin = contactAdmin;
    }
}
