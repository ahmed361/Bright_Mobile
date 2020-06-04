/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Gui;

import Entites.Categorie;
import Entites.stock;
import Service.ServiceCateg;
import Service.StockService;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
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
public class HomeCForm extends BaseForm{
    
    private Label titreEror;
    TextField ttitre;
   
    TextField tEmplacement;
    TextField tcategorie; //categorie
   
    TextField nbplaceeLabel;
    Picker datedeb ;
    Picker datefin;
    Container descriptionContainer;
    Button btnajout,btnaff;
     
    Form f;
    TextField ttype;
    


    Button btnajoutt,btnafff;
    
    
    
      /* public HomeCForm()  {
        f = new Form("Welcome");
        ttype = new TextField("","type");
        
        btnajoutt = new Button("Ajouter");
        btnafff=new Button("Affichage");
        
        f.add(ttype);
        
        f.add(btnajoutt);
        f.add(btnafff);
 
        
      
      
       btnajoutt.addActionListener((e) -> {
            ServiceCateg ser = new ServiceCateg();
             if (ttype.getText().isEmpty()){
                  Dialog.show("Alert", "remplir les champs", "ok", null);
             }else{
                 Categorie c = new Categorie( ttype.getText());
            
            ser.ajoutCat(c);
            
           Dialog.show("Alert", "Categorie "+" Ajouté", "ok", null);

             }});
btnafff.addActionListener((e)->{
        AffichageC a=new AffichageC(theme);
       
        });
        }*/
        
    
    
    
    
    
    
    
    

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public TextField getTtype() {
        return ttype;
    }

    public void setTtype(TextField ttype) {
        this.ttype = ttype;
    }

    

   

    public Button getBtnajout() {
        return btnajoutt;
    }

    public void setBtnajout(Button btnajout) {
        this.btnajoutt = btnajout;
    }

    public Button getBtnaff() {
        return btnafff;
    }

    public void setBtnaff(Button btnaff) {
        this.btnafff = btnaff;
    }
    
    
    
    
    
    public HomeCForm(Resources theme)  {
       
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

            
            ServiceCateg ser = new ServiceCateg();
 try{
            Categorie ev1 = new Categorie(ttitre.getText());
            
           
            ser.ajoutCat(ev1);
           Dialog.show("felicitation", " votre Type a ete ajoute", "ok", null);            
        }catch(Exception ex){}
            }
            
        });
       
        btnaff.addActionListener((e)->{
        AffichageC a;
        
                a = new AffichageC(theme);
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
