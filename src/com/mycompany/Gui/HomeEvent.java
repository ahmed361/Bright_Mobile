/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Gui;

import Entites.Evenement;
import Entites.don;
import Service.ServiceDon;
import Service.ServiceEvent;
import com.codename1.components.ScaleImageLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Dell
 */
public class HomeEvent extends BaseForm{
    
    private Label titreEror;
    TextField tnomarticle;
    TextField titre;
    TextField description;

   
    TextField ville;
    Picker datedeb ;
    TextField categorie_id;

  
    Container descriptionContainer;
    Button btajout,btnaff,btemail;
    
    
      public HomeEvent(Resources theme)  {
               super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(theme);
        
        tb.addSearchCommand(e -> {});
        
        
        Image img = theme.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        
//Toolbar tb = showF.getToolbar();
       
        
        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                    GridLayout.encloseIn(3, 
                           
                            FlowLayout.encloseCenter(
                                new Label(theme.getImage("profile-pic.jpg"), "PictureWhiteBackgrond"))
                    )
                )
        ));
        
   ///     f = new Form("home");
       titre = new TextField("","titre.........!",20,TextField.ANY);
       titre.getUnselectedStyle().setFgColor(621200);
       description = new TextField("","description.....!",20,TextField.ANY);
       description.getUnselectedStyle().setFgColor(621200);
       ville = new TextField("","ville.....!",20,TextField.ANY);
       ville.getUnselectedStyle().setFgColor(621200);
       categorie_id = new TextField("","id_categorie.....!",20,TextField.ANY);
       categorie_id.getUnselectedStyle().setFgColor(621200);
       //categorie_id = new TextField("","categorie_id.....!",20,TextField.ANY);
       //categorie_id.getUnselectedStyle().setFgColor(621200);

    
        btajout = new Button("Ajouter evenement");
        btemail = new Button("email");
        btnaff=new Button("Listes Des evenements");
        

        
        
        btajout.getAllStyles().setBorder(Border.createRoundBorder(13, 12));
        btnaff.getAllStyles().setBorder(Border.createRoundBorder(15, 12));
        btemail.getAllStyles().setBorder(Border.createRoundBorder(13, 12));


        
        /*Container dateContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Picker datedeb = new Picker();
        datedeb.getUnselectedStyle().setFgColor(621200);
        Label temp = new Label("date ");
        dateContainer.add(temp);
        
        
      dateContainer.add(datedeb);
      
       SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");*/
       
        
       

       add(titre);
       add(description);
       add(ville);
       add(categorie_id);
       add(btajout);
       add(btnaff);
       add(btemail);
         
        btajout.addActionListener((e) -> {
            
            if (titre.getText().equals("")) {
                
                Dialog.show("ERREUR SAISIE","titre VIDE","OK","ANNULER");
            }
           
            else if (description.getText().equals("")) {
                
                Dialog.show("ERREUR SAISIE","quantite VIDE","OK","ANNULER");
            }
            else if (ville.getText().equals("")) {
                
                Dialog.show("ERREUR SAISIE","ville VIDE ","OK","ANNULER");
            }
          
             
            
        
           
             
            else{

            
            ServiceEvent ser = new ServiceEvent();
            Evenement article = new Evenement(titre.getText(),description.getText(),ville.getText(),Integer.parseInt(categorie_id.getText()));
           
            ser.ajoutArticle(article);
           Dialog.show("felicitation", " votre evenement a ete ajoute", "ok", null);            
        }
        });
       
        btnaff.addActionListener((e)->{
       AffichageEvent a =new AffichageEvent(theme);
        a.show();
        });
        
        
        
        
        
        btemail.addActionListener((e) -> {
            
             ServiceEvent ser = new ServiceEvent();
            Evenement article = new Evenement(titre.getText(),description.getText(),ville.getText(),Integer.parseInt(categorie_id.getText()));
           
            
            Message m = new Message("l'event est :" +article.getTitre()+"\n"+" "+article.getDescription()+"\n"+" "+article.getVille());
        

            m.getAttachments().put("", "text/plain");
 

 

    Display.getInstance () .sendMessage(new String[]{"wafa.benrhouma@esprit.tn"
    }
     , "Je vous invite a notre evenement ", m

);

            
        });
    }
    
    
}
