/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import Entites.Categorie;
import Entites.stock;
import com.codename1.io.CharArrayReader;
import com.codename1.io.JSONParser;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
//import com.mycompany.Entite.Evenement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Dell
 */
public class ServiceCateg {
    
    
    
   /* public void ajoutCat(Categorie c) {
         
        
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/PI9/web/app_dev.php/volunteer/new";// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
         con.setPost(true);
          con.addArgument("type", c.getType());
     
       // con.addArgument("id", Integer.toString(c.getId()));

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
    
    
    
    
    
    public ArrayList<Categorie> parseListCategJson(String json) {
          System.out.println("DEBUG, 48, parseListCategJSON:" + json);

        ArrayList<Categorie> listClubs = new ArrayList<>();
       JSONArray jsonArray = new JSONArray(json);
        for (int i = 0; i < jsonArray.length(); i++) {
            listClubs.add(jsonToCateg(jsonArray.getJSONObject(i)));
            
        }
         System.out.println(listClubs);
        return listClubs;

    }
    
    
    
    
    
    
    
     ArrayList<Categorie> listClubs = new ArrayList<>();
    
    public ArrayList<Categorie> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PI9/web/app_dev.php/volunteer/readCat");  
         con.setPost(false);
        
        con.addResponseListener((NetworkEvent evt) -> {
            System.out.println(con.getResponseData());
            listClubs = this.parseListCategJson(new String(con.getResponseData()));
        });
      
    
    
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listClubs;
    }
    
    public static void main(String[] args) {
        (new ServiceCateg()).getList2();
    }
    
    
    
    private Categorie jsonToCateg(JSONObject jsonObject) {
        Integer id = jsonObject.getInt("id");
        
        String type = jsonObject.getString("type");
        //Integer id = jsonObject.getInt("id");
        //Date date=jsonObject.getDate
        
        
        return  new Categorie(id,type);
    }
    
    
    
    
    
    
    public void Supprimer(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PI9/web/app_dev.php/volunteer/"+id+"/deleteCat");
    
        con.setPost(false);
        con.addResponseListener((evt) -> {
            System.out.println(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }*/
      
    public void modifierEspace(Categorie es) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/PI9/web/app_dev.php/volunteer/updateCom/"+es.getId()+"?type="+es.getType();// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
    
    
       public void ajoutCat(Categorie ev) {
        ConnectionRequest con = new ConnectionRequest();
 SimpleDateFormat tempss = new SimpleDateFormat("yyyy-MM-dd");
               
        String Url = "http://localhost/PI9/web/app_dev.php/volunteer/new"
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
    
     
    
     
     
     
     
    /* public ArrayList<Categorie> getlistE(String json) {

         
         System.err.println(json);
         
         
        ArrayList<Categorie> listTasks = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            
            Map<String, Object> events = j.parseJSON(new CharArrayReader(json.toCharArray()));
                       
            
            List<Map<String, Object>> list = (List<Map<String, Object>>) events.get("root");

            for (Map<String, Object> obj : list) {
                
                Categorie ev = new Categorie();

                //float id = Float.parseFloat(obj.get("id").toString());
     
//                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
//            String datedeb = formater.format(ev.getDATED_EVENT());           
//            String datefin = formater.format(ev.getDATEF_EVENT());        
//                
            
                ev.setType(obj.get("type").toString());
               
                
                
                
//                ev.setDATED_EVENT((Date) obj.get(datedeb));
//                ev.setDATEF_EVENT((Date) obj.get(datefin));
                
               
                
                System.out.println(ev);
                
                listTasks.add(ev);

            }

        } catch (IOException ex) {
        }
        
        System.out.println(listTasks);
        return listTasks;

    }*/
        public ArrayList<Categorie> parseListCategJson(String json) {
          System.out.println("DEBUG, 48, parseListCategJSON:" + json);

        ArrayList<Categorie> listClubs = new ArrayList<>();
       JSONArray jsonArray = new JSONArray(json);
        for (int i = 0; i < jsonArray.length(); i++) {
            listClubs.add(jsonToCateg(jsonArray.getJSONObject(i)));
            
        }
         System.out.println(listClubs);
        return listClubs;

    }
         public static void main(String[] args) {
        (new ServiceCateg()).getList2();
    }
    
    
    
    private Categorie jsonToCateg(JSONObject jsonObject) {
        Integer id = jsonObject.getInt("id");
        
        String type = jsonObject.getString("type");
        //Integer id = jsonObject.getInt("id");
        //Date date=jsonObject.getDate
        
        
        return  new Categorie(id,type);
    }
    
    
     
     ArrayList<Categorie> listTasks = new ArrayList<>();
     public ArrayList<Categorie> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PI9/web/app_dev.php/volunteer/readCatt");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceCateg ser = new ServiceCateg();
                listTasks = ser.parseListCategJson(new String(con.getResponseData()));
            }
        });
        
            NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
     public void Supprimerr(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PI9/web/app_dev.php/volunteer/"+id+"/deleteCatt");
    
        con.setPost(false);
        con.addResponseListener((evt) -> {
            System.out.println(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
    
}
