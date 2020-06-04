/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import java.util.Date;

/**
 *
 * @author Dell
 */
public class Evenement {
    
    
    
    private int ref ;
    private int nbrPart ;
    private String titre ;
    private String description ;
    private Date date;   //Date.valueOf("yyyy-mm-dd")
    private String ville ;
    private String sto ;

    private int categorie_id;
    private Categorie cat;
    
    
    public Evenement()
    {
        //**********
    }
    
    
    public Evenement(int ref, int nbrPart, String titre, String description, Date date, String ville, int categorie_id) {
        this.ref = ref;
        this.nbrPart = nbrPart;
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.ville = ville;
        this.categorie_id = categorie_id;
    }
    
    public Evenement( String titre, String description, String ville, int categorie_id) {
        this.titre = titre;
        this.description = description;
        this.ville = ville;
        this.categorie_id = categorie_id;
    }
    
    
     public Evenement( int ref,String titre, String description, String ville, Categorie cat) {
        this.ref=ref;
        this.titre = titre;
        this.description = description;
        this.ville = ville;
        this.cat=cat;
    }
    
     
     
      public Evenement( int ref,String titre, String description, String ville) {
        this.ref=ref;
        this.titre = titre;
        this.description = description;
        this.ville = ville;
        //this.sto = sto;
    }
    
     public Evenement( String titre, String description,  String ville) {
       
        this.titre = titre;
        this.description = description;
        
        this.ville = ville;
        
    }

    

    public int getRef() {
        return ref;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }

    public int getNbrPart() {
        return nbrPart;
    }

    public void setNbrPart(int nbrPart) {
        this.nbrPart = nbrPart;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
    
    public String getSto() {
        return sto;
    }

    public void setSto(String sto) {
        this.sto = sto;
    }
    
     public int getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
    }
    

    @Override
    public String toString() {
        return "Evenement{" + "ref=" + ref + ", nbrPart=" + nbrPart + ", titre=" + titre + ", description=" + description + ", date=" + date + ", ville=" + ville + ", categorie_id=" + categorie_id + '}';
    }

   
    
}
