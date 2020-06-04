package Service;

import Entites.Membre;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author AHMED
 */
public class ServiceMembre {

    public ConnectionRequest req;

    public boolean resultOK;
    public ArrayList<Membre> tasks;
    public static ServiceMembre instance = null;

    public static ServiceMembre getInstance() {
        if (instance == null) {
            instance = new ServiceMembre();
        }
        return instance;
    }

    public void ajoutArticle(Membre ar) {
        ConnectionRequest con = new ConnectionRequest();

        String Url = "http://127.0.0.1:8000/volunteer/membre/ajout"
                + "?nom=" + ar.getNom()
                + "&prenom=" + ar.getPrenom()
                + "&ville=" + ar.getVille()
                + "&email=" + ar.getEmail()
                + "&tel=" + ar.getTel();

        System.out.println("L'URL est : : :" + Url);
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }

    public ArrayList<Membre> getlistE(String json) {

        System.err.println(json);

        ArrayList<Membre> listTasks = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> events = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) events.get("root");

            for (Map<String, Object> obj : list) {

                Membre ev = new Membre();

                float id = Float.parseFloat(obj.get("id").toString());
                float tel = Float.parseFloat(obj.get("tel").toString());
                ev.setId((int) id);

                ev.setNom(obj.get("nom").toString());
                ev.setPrenom(obj.get("prenom").toString());
                ev.setVille(obj.get("ville").toString());
                ev.setEmail(obj.get("email").toString());
                ev.setTel((int) tel);

                System.out.println(ev);

                listTasks.add(ev);

            }

        } catch (IOException ex) {
        }

        System.out.println(listTasks);
        return listTasks;

    }

    ArrayList<Membre> listTasks = new ArrayList<>();

    public ArrayList<Membre> getList2() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/volunteer/membre/all");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceMembre ser = new ServiceMembre();
                listTasks = ser.getlistE(new String(con.getResponseData()));
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }

    public void Supprimer(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/volunteer/" + id + "/deleteS");

        con.setPost(false);
        con.addResponseListener((evt) -> {
            System.out.println(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
//zedtou hethi l methode pck me ye9belch json f l affichage url hedha mayemchych

    public ArrayList<Membre> getlistnew() {
        ConnectionRequest con = new ConnectionRequest();
        String url = "http://127.0.0.1:8000/volunteer/membre/all";
        //System.out.println(url);
        con.setUrl(url);
        //
        con.setPost(false);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    // System.out.println(url);
                    tasks = parseRefugie(new String(con.getResponseData()));
                    con.removeResponseListener(this);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return tasks;
    }

    public ArrayList<Membre> parseRefugie(String jsonText) throws Exception {
        try {

            tasks = new ArrayList<Membre>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            System.out.print("size :" + list.size());
            for (Map<String, Object> obj : list) {
                Membre t = new Membre();
                float id = Float.parseFloat(obj.get("id").toString());
                float tel = Float.parseFloat(obj.get("tel").toString());
                t.setId((int) id);
                t.setNom(obj.get("nom").toString());
                t.setPrenom(obj.get("prenom").toString());
                t.setVille(obj.get("ville").toString());
                t.setTel((int) tel);

                /*  Map<String, Object> dateF = (Map<String, Object>) obj.get("datePub");
                float ZonedDateTime= Float.parseFloat(dateF.get("timestamp").toString());
                
                Date date = new Date((long) ZonedDateTime);
                t.setDatepub(date);*/
                tasks.add(t);
            }

        } catch (IOException ex) {

        }
        return tasks;
    }

}
