/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entites.don;
import Entites.stock;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.spinner.Picker;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Administrator
 */
public class ServiceDon {

    
  
    
    public void ajoutArticle(don ar) {
        ConnectionRequest con = new ConnectionRequest();
 SimpleDateFormat tempss = new SimpleDateFormat("yyyy-MM-dd");
               // String datedeb = tempss.format(ev.getDATED_EVENT());
             
        String Url = "http://localhost/PI9/web/app_dev.php/stock/add"
                +"?libelle=" + ar.getLibelle()
             
                + "&quantite=" + ar.getQuantite()
                 + "&Stock_id=" + ar.getStock_id()
               
                 ;
        
        System.out.println("L'URL est : : :" + Url);
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
    
     
    
     
     
    public ArrayList<don> parseListClubJson(String json) {
          System.out.println("DEBUG, 48, parseListClubJSON:" + json);

        ArrayList<don> listDon = new ArrayList<>();
       JSONArray jsonArray = new JSONArray(json);
        for (int i = 0; i < jsonArray.length(); i++) {
            listDon.add(jsonToClub(jsonArray.getJSONObject(i)));
            
        }
         System.out.println(listDon);
        return listDon;

    }
       
    
    ArrayList<don> listDon = new ArrayList<>();
    
    public ArrayList<don> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PI9/web/app_dev.php/stock/AffichageDon");  
         con.setPost(false);
        con.addResponseListener((NetworkEvent evt) -> {
            System.out.println(con.getResponseData());
            listDon = this.parseListClubJson(new String(con.getResponseData()));
        });
      
    
    
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listDon;
    }
 public static void main(String[] args) {
        (new ServiceDon()).getList2();
    }
 private don jsonToClub(JSONObject jsonObject) {
        Integer reference = jsonObject.getInt("reference");
        String libelle = jsonObject.getString("libelle");
        Integer quantite =jsonObject.getInt("quantite");
       // Integer id =jsonObject.getInt("Stock");
        String type = jsonObject.getString("type");

           //SimpleDateFormat date=new SimpleDateFormat("yyyy-mm-dd");

            
       //Date  date = (Date) jsonObject.get("date");

   



        
        
       return  new don(reference,libelle,quantite,type);
    }

     public void Supprimer(int reference) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PI9/web/app_dev.php/stock/"+reference+"/deleted");
    
        con.setPost(false);
        con.addResponseListener((evt) -> {
            System.out.println(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
 
      
      public void addCom(don p,int id) {
                  ConnectionRequest con = new ConnectionRequest();

        String url ="http://localhost/PI9/web/app_dev.php/stock/tasks/"+id+"?libelle=" + p.getLibelle()
             
                + "&quantite=" + p.getQuantite()
                 + "&Stock_id=" + p.getStock_id()
               ;
        System.out.println("L'URL est : : :" + url);
        con.setUrl(url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
                  ArrayList<stock> stock = new ArrayList<>();

 public void modifierEspace(don s) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/PI9/web/app_dev.php/stock/modifierRefg/"+s.getReference()+"?type="+s.getQuantite();// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
   
            
}



