/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Gui;

import Entites.Categorie;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Form;
import Service.ServiceCateg;
import Service.StockService;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import Service.ServiceEvent;
import com.codename1.ui.TextField;

/**
 *
 * @author Dell
 */
public class AffichageC extends BaseForm{
    
    
    Form ff;
    SpanLabel lbb;//retour a la ligne
    
    
    
    /* public AffichageC()   { 
         
    
        
        ff = new Form();
        lbb = new SpanLabel("");
        ff.add(lbb);
        
        ServiceCateg serviceCat=new ServiceCateg();
        //ViewListCat viewListCat = new ViewListCat(serviceCat.getList2());
       // viewListCat.afficherr();
       // lb.setText(serviceClub.getList2().toString());
        
          ff.getToolbar().addCommandToRightBar("back", null, (ev)->{HomeCForm h=new HomeCForm();
          h.getF().show();
          });
    }*/

    public Form getF() {
        return ff;
    }

    public void setF(Form f) {
        this.ff = f;
    }

    public SpanLabel getLb() {
        return lbb;
    }

    public void setLb(SpanLabel lb) {
        this.lbb = lb;
    }
     
    
    
    
    Label titre;
        Label animateur;
        Label lieu;
        Label lien;
        Label datedeb;
        Label datefin;
        Label nbp;
        Label frais;
        Label description;
       Label affiche;
   Button btnn =  new Button("Supprimer Type");
       EncodedImage imc;
    Image img;
    ImageViewer imv;
    
    public AffichageC(Resources theme) {
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

           ServiceCateg serviceTask = new ServiceCateg();
                   for (Categorie ev : serviceTask.getList2()) {
                    Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                Label type = new Label("type: " + ev.getType());
                 
              type.getStyle().setFgColor(0x1e3642);
        Font smallPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        type.getStyle().setFont(smallPlainSystemFont);
           Button btnn =  new Button("Supprimer Type");
           Button modifier = new Button ("Modifier Type");

                                   
        
      btnn.getAllStyles().setBorder(Border.createRoundBorder(13, 12));
        
      modifier.getAllStyles().setBorder(Border.createRoundBorder(13, 12));
//      C1.add(BorderLayout.center(type));                       
// 
 //C1.add(BorderLayout.center(type));
   
      
         btnn.addActionListener(new ActionListener() {
               @Override
         public void actionPerformed(ActionEvent evt) 
         {          Dialog d = new Dialog();

                    if (Dialog.show("Confirmation", "delete this Type??", "Ok", "Annuler")) {

       
         ServiceCateg stock = new ServiceCateg();
                stock.Supprimerr(ev.getId());
                Dialog.show("Alert", "categorie "+ev.getType()+" supprimé", "ok", null);
                new AffichageC(theme).show();;
    }
         }
              });   
         
         
         
         
         modifier.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ee) {
                 //   f.setHidden(true);
                    
      TextField  eNom = new TextField("","Type");
    
      Button b = new Button("Modifier Type");
      eNom.setText(ev.getType());
       eNom.getStyle().setFgColor(0x1e3642);
        Font smallPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        eNom.getStyle().setFont(smallPlainSystemFont);
                    add(eNom);
                   
                    add(b);
                   show();
              b.addActionListener((e)->{
                  
                      if (eNom.getText().isEmpty()) {
              Dialog.show("Information Dialog", " le type  Doit étre Rempli", "OK", "Cancel");  
            }
                 
               else {
             Categorie s = new Categorie ();
                  
                    int   id = ev.getId();
                    s.setType(eNom.getText());
                   
                    s.setId(id);
                    
                   
                    ServiceCateg ser = new ServiceCateg();
                     ser.modifierEspace(s);
            
            Dialog.show("Information Dialog", "le Type  '"+eNom.getText()+"' A été Modifier Avec Succés ", "OK", "Cancel");
            
       eNom.clear();
       }
                 
                     
                     AffichageC h=new AffichageC(theme);
                   h.show();
                     
                    });}
            });
          
          
   C1.add(BorderLayout.center(type));
               C1.add(btnn);                   

            C1.add(modifier);                   

  
   
                              

    //add(C1);
         
         
         
         

             //C1.add(btnn);
    add(C1);
              }
    }
    
    
    
    
}
