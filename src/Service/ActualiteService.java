/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entites.Actualite;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author RedBox
 */
public class ActualiteService {
    
   ArrayList<Actualite> listActualites = new ArrayList<>();

    public ArrayList<Actualite> getList2() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PI2/web/app_dev.php/stock/tasks/all");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ActualiteService ser = new ActualiteService();
                listActualites = (ArrayList<Actualite>) ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listActualites;
    }

    private ArrayList<Actualite> getListTask(String json) {
        ArrayList<Actualite> listActualites = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");
            System.out.println(list);
            for (Map<String, Object> obj : list) {
                Actualite e = new Actualite();

                // System.out.println(obj.get("id"));
                float id = Float.parseFloat(obj.get("id").toString());
                System.out.println(id);
                e.setId((int) id);
//             e.setId_categorie(Integer.parseInt(obj.get("idcategrie").toString().trim()));
                e.setTitre(obj.get("titre").toString());
                e.setDescription(obj.get("description").toString());
                e.setDate(new Date((((Double) ((Map<String, Object>) obj.get("date")).get("timestamp")).longValue() * 1000)));

               // e.setNb_personnes((int) Float.parseFloat(obj.get("nbPersonnes").toString().trim()));
               
                e.setImg(obj.get("img").toString());
                try {
                    e.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(obj.get("date").toString()));
                } catch (ParseException ex) {
                }

                //System.out.println(e);
                listActualites.add(e);

            }

        } catch (IOException ex) {
        }
        //System.out.println(listRecettes);
        return listActualites;

    }
   
     ArrayList<Actualite> listActualite  = new ArrayList<>();
    
    public ArrayList<Actualite> getDetails2(){    
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/CBE/web/app_dev.php/djo/find");
        con.addResponseListener((NetworkEvent evt) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console
            ActualiteService ser = new ActualiteService();
            listActualite = ser.getListTask(new String(con.getResponseData()));
        });
        NetworkManager.getInstance().addToQueueAndWait(con);

        return listActualite;
     
    }
     ArrayList<Actualite> listSearch = new ArrayList<>();
     public ArrayList<Actualite> getListSearch(String name) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/CBE/web/app_dev.php/djo/find" + "/searche/" + name);
        con.addResponseListener((NetworkEvent evt) -> {
            ActualiteService ser = new ActualiteService();
           // listActualite = ser.getDe(new String(con.getResponseData()));
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listActualite;
    }

   
}
         