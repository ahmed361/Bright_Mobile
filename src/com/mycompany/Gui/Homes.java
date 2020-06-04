/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package com.mycompany.Gui;
import Entites.stock;
import Service.StockService;
import com.codename1.components.ScaleImageLabel;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.components.ScaleImageLabel;

import com.codename1.ui.Display;

import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;

import Service.StockService;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;

import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;


//import rest.file.uploader.tn.FileUploader;



/**
 *
 * @author Administrator
 */
public class Homes extends BaseForm{
    //Form hi = new Form();
    //Form f;
    
    private Label titreEror;
    TextField ttitre;
   
    TextField tEmplacement;
    TextField tcategorie; //categorie
   
    TextField nbplaceeLabel;
    Picker datedeb ;
    Picker datefin;
    Container descriptionContainer;
    Button btnajout,btnaff;
   Button modifier;
    public Homes(Resources theme)  {
       
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
      ttitre = new TextField("","Type...!",20,TextField.ANY);
       ttitre.getUnselectedStyle().setFgColor(621200);
       
 
        btnajout = new Button("Ajouter Type");
        btnaff=new Button("Listes Des Types");
      
        
      btnajout.getAllStyles().setBorder(Border.createRoundBorder(13, 12));
              btnaff.getAllStyles().setBorder(Border.createRoundBorder(15, 12));

       
        
        
      
        
        
        

        add(ttitre);
       
        
        add(btnajout);
        add(btnaff);
         
        btnajout.addActionListener((e) -> {
            
            if (ttitre.getText().equals("")) {
                
                Dialog.show("ERREUR SAISIE","TYPE VIDE","OK","ANNULER");
            }
            
          
            
             
         else{

            
            StockService ser = new StockService();
 try{
            stock ev1 = new stock(ttitre.getText());
            
           
            ser.ajoutEvent(ev1);
           Dialog.show("felicitation", " votre Type a ete ajoute", "ok", null);            
        }catch(Exception ex){}
            }
            
        });
       
        btnaff.addActionListener((e)->{
        Affichage a;
        
                a = new Affichage(theme);
                   a.show();
         
     
        });
    }


    public Label getTitreEror() {
        return titreEror;
    }

    public void setTitreEror(Label titreEror) {
        this.titreEror = titreEror;
    }

    public TextField getTtitre() {
        return ttitre;
    }

    public void setTtitre(TextField ttitre) {
        this.ttitre = ttitre;
    }

  
    

    public Button getBtnajout() {
        return btnajout;
    }

    public void setBtnajout(Button btnajout) {
        this.btnajout = btnajout;
    }

    public Button getBtnaff() {
        return btnaff;
    }

    public void setBtnaff(Button btnaff) {
        this.btnaff = btnaff;
    }

   

    
   

    public boolean isAFloat(String x) {
        try {
            Float.parseFloat(x);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    
public boolean isAEntier(String x) {
        try {
            Integer.parseInt(x);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}
