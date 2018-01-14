package com.poc;

/**
 * configuration à externaliser
 */
public enum Conf {
    clientId("<< your client id >>"), 
    clientSecret("<< your client secret >>"),
    redirectUri("<< your uri >>");

    private String valeur;

    Conf(String valeur) {
        this.valeur = valeur;
    }

    public String getValue() {
        return valeur;
    }
}