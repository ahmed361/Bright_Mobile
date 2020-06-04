/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

/**
 *
 * @author RedBox
 */
public class Categorie {
     private int id;
     private String Type;
     

    public Categorie(int id, String Type) {
        this.id = id;
        this.Type = Type;
      
    }

    
    public Categorie() {
        
      
    }

    
     public Categorie( String Type) {
        
        this.Type = Type;
      
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return Type;
    }

        public void setType(String Type) {
        this.Type = Type;
    }

   

    @Override
    public String toString() {
        return "Categorie{" + "id=" + id + ", Type=" + Type +  '}';
    }

    
}
