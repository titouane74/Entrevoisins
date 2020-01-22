package com.openclassrooms.entrevoisins.model;

import java.util.Objects;

/**
 * Model object representing a Neighbour
 */
public class Neighbour {

    /** Identifier */
    private Integer id;

    /** Full name */
    private String name;

    /** Avatar */
    private String avatarUrl;

    /** Address */
    private String address;

    /** Phone */
    private String phone;

    /** Web site  */
    private String webSite;


    /** Favori */
    private boolean favori;

    /** Texte A propose de moi */
    private String aboutMe;

    /**
     * Constructeur de l'objet
     * @param id : integer : identifiant du voisin
     * @param name : string : nom
     * @param avatarUrl : string : adresse url de l'image de l'avatar
     * @param address : string : adresse
     * @param phone : string : téléphone
     * @param webSite : string : adresse du site web
     * @param favori : boolean : indicateur d'appartenance aux favoris
     * @param aboutMe : string : descriptif du voisin
     */
    public Neighbour(Integer id, String name, String avatarUrl, String address,
             String phone, String webSite, boolean favori, String aboutMe) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.address = address;
        this.phone = phone;
        this.webSite = webSite;
        this.favori = favori;
        this.aboutMe = aboutMe;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getAddress() { return address; }

    public void setAddress(String pAddress) { address = pAddress; }

    public String getPhone() { return phone; }

    public void setPhone(String pPhone) { phone = pPhone; }

    public void setWebSite(String pWebSite) { webSite = pWebSite; }

    public String getWebSite() { return webSite; }

    public boolean isFavori() { return favori;}

    public void setFavori(boolean pFavori) {
        favori = pFavori;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String pAboutMe) {
        aboutMe = pAboutMe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Neighbour neighbour = (Neighbour) o;
        return Objects.equals(id, neighbour.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
