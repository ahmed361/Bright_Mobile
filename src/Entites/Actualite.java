/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import java.util.Date;

/**
 *
 * @author RedBox
 */
public class Actualite {
    private int id;
    int id_categorie ;
    private String titre;
    private String description;
    private String img;
     private Date date;
     
  public Actualite() {
    }  

    public Actualite(int id, int id_categorie, String titre, String description, String img, Date date) {
        this.id = id;
        this.id_categorie = id_categorie;
        this.titre = titre;
        this.description = description;
        this.img = img;
        this.date = date;
    }

    public Actualite(int id, String titre, String description, String img, Date date) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.img = img;
        this.date = date;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Actualite{" + "id=" + id + ", id_categorie=" + id_categorie + " titre=" + titre + ", description=" + description + ", img=" + img + ", date=" + date + '}';
    }
  
}
