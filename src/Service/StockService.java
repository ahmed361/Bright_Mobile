/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entites.stock;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
/**
 *
 * @author HP
 */
public class StockService {

   public void modifierEspace(stock es) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/PI9/web/app_dev.php/stock/updateComM/"+es.getId()+"?type="+es.getType();// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
    
   public void ajoutEvent(stock ev) {
        ConnectionRequest con = new ConnectionRequest();
 SimpleDateFormat tempss = new SimpleDateFormat("yyyy-MM-dd");
               
        String Url = "http://localhost/PI9/web/app_dev.php/stock/tasks/newd"
                +"?type=" + ev.getType()
             
                
                 ;
        
        System.out.println("L'URL est : : :" + Url);
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
    
  
     
    
    public ArrayList<stock> parseListClubJson(String json) {
          System.out.println("DEBUG, 48, parseListClubJSON:" + json);

        ArrayList<stock> listStocks = new ArrayList<>();
       JSONArray jsonArray = new JSONArray(json);
        for (int i = 0; i < jsonArray.length(); i++) {
            listStocks.add(jsonToClub(jsonArray.getJSONObject(i)));
            
        }
         System.out.println(listStocks);
        return listStocks;

    }
       
    public ArrayList<stock> parseListClubJsonn(String json) {
          System.out.println("DEBUG, 48, parseListClubJSON:" + json);

        ArrayList<stock> listStocks = new ArrayList<>();
       JSONArray jsonArray = new JSONArray(json);
        for (int i = 0; i < jsonArray.length(); i++) {
            listStocks.add(jsonToClubb(jsonArray.getJSONObject(i)));
            
        }
         System.out.println(listStocks);
        return listStocks;

    }
       
    ArrayList<stock> listStocks = new ArrayList<>();
    
    public ArrayList<stock> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/stock/tasks/afficher");  
         con.setPost(false);
        con.addResponseListener((NetworkEvent evt) -> {
            System.out.println(con.getResponseData());
            listStocks = this.parseListClubJson(new String(con.getResponseData()));
        });
      
    
    
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listStocks;
    }
 public static void main(String[] args) {
        (new StockService()).getList2();
    }
 
 private stock jsonToClub(JSONObject jsonObject) {
        Integer id = jsonObject.getInt("id");
        String type = jsonObject.getString("type");
       
        
        
        
        return  new stock(id,type);
    } 
private stock jsonToClubb(JSONObject jsonObject) {
        Integer id = jsonObject.getInt("id");
        //String type = jsonObject.getString("type");
       
        
        
        
        return  new stock(id);
    } 

      
   public void Supprimer(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PI9/web/app_dev.php/stock/"+id+"/deleteStock");
 con.setPost(false);
        con.addResponseListener((evt) -> {
            System.out.println(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
    public ArrayList<stock> getList3(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PI9/web/app_dev.php/stock/");  
         con.setPost(false);
        con.addResponseListener((NetworkEvent evt) -> {
            System.out.println(con.getResponseData());
            listStocks = this.parseListClubJsonn(new String(con.getResponseData()));
        });
      
    
    
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listStocks;
    }

   
}
