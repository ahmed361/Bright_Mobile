
package Service;

import Entites.User;
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
import utils.Statics;

/**
 *
 * @author RedBox
 */
public class ServiceUser {

    private User loggedUser = new User();

    public ArrayList<User> users;
    public static ServiceUser instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceUser() {
        req = new ConnectionRequest();
    }

    public static ServiceUser getInstance() {
        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
    }
  public void ajoutUser(User ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = Statics.BASE_URL +"/json/register?email=" + ta.getEmail()
                + "&password=" + ta.getPassword() + "&username=" + ta.getUsername() +"&role=" + ta.getRole();
        con.setUrl(Url);
        System.out.println(ta.toString());

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
    public boolean addUser(User t) {
        String url = "http://127.0.0.1:8000/api/tasks/new"
                + "?username=" + t.getUsername()
                + "&password=" + t.getPassword()
                + "&email=" + t.getEmail()
                + "&city=" + t.getCity()
                + "&role=" + t.getRole();

        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<User> parseUsers(String jsonText) {
        try {
            users = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
             */
            Map<String, Object> usersListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.               
            
            Le format Json impose que l'objet soit définit sous forme
            de clé valeur avec la valeur elle même peut être un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus adéquate en Java pour stocker des couples Key/Value.
            
            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
             */
            List<Map<String, Object>> list = (List<Map<String, Object>>) usersListJson.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                User t = new User();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                //   t.setUsername(((int)Float.parseFloat(obj.get("status").toString())));
                t.setUsername(obj.get("username").toString());
                t.setPassword(obj.get("password").toString());
                t.setUsername(obj.get("email").toString());
                t.setUsername(obj.get("city").toString());
                t.setRole(obj.get("role").toString());
                //Ajouter la tâche extraite de la réponse Json à la liste
                users.add(t);
            }

        } catch (IOException ex) {

        }
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
         */
        return users;
    }

    public ArrayList<User> getAllusers() {
        String url = Statics.BASE_URL + "/users/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseUsers(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;
    }

//    public User Authentification(String username, String password) {
//        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
//        String Url = "http://127.0.0.1:8000/api/tasks/find/" + username + "/" + password;// création de l'URL
//        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
//
//        con.addResponseListener((e) -> {
//            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
//            if (str.equals("false")) {
//                loggedUser = null;
//            } else {
//                ServiceUser ser = new ServiceUser();
//                // try {
//                // loggedUser = ser.parseUsers(new String(con.getResponseData()));
//                // } catch (ParseException ex) 
//                {
//
//                }
//                // Session.getInstance().setLoggedInUser(loggedUser);
//            }
//
//        });
//        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
//        return loggedUser;
//    }

//    public void envoyermail(String email, String code) {
//        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
//
//        String Url = "http://localhost/CBE/web/app_dev.php/djo/envoyezmail?email=" + email + "&code=" + code;
//        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
//
//        con.addResponseListener((e) -> {
//            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
//            System.out.println(str);//Affichage de la réponse serveur sur la console
//
//        });
//        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
//    }

}
