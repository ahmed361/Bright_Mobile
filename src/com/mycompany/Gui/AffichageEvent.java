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
import Service.StockService;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
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
//import java.util.Date;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.l10n.DateFormat;
import com.codename1.ui.plaf.Style;
//import java.sql.Date;

/**
 *
 * @author Dell
 */
public class AffichageEvent extends BaseForm {

    Button btnn = new Button("Supprimer Don");

    public AffichageEvent(Resources theme) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(theme);

        tb.addSearchCommand(e -> {
        });

        Image img = theme.getImage("profile-background.jpg");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
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

        ServiceEvent serviceTask = new ServiceEvent();
        for (Evenement ev : serviceTask.getList2()) {
            Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label titre = new Label("titre: " + ev.getTitre());

            Label description = new Label("description: " + ev.getDescription());
            Label ville = new Label("ville: " + ev.getVille());
            // DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            //String strDate = dateFormat.format(ev.getDate()); 
            //Label date = new Label("date: " + ev.getDate());
            //Label Categorie = new Label("Type Categorie: "+ev.getSto()); 

            titre.getStyle().setFgColor(0x1e3642);
            Font smallPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
            titre.getStyle().setFont(smallPlainSystemFont);

            description.getStyle().setFgColor(0x1e3642);
            description.getStyle().setFont(smallPlainSystemFont);
            ville.getStyle().setFgColor(0x1e3642);
            ville.getStyle().setFont(smallPlainSystemFont);

            //Date.getStyle().setFgColor(0x1e3642);
            // Date.getStyle().setFont(smallPlainSystemFont);
            // Date.getStyle().setFgColor(0x1e3642);
            // Categorie.getStyle().setFgColor(0x1e3642);
            ////Categorie.getStyle().setFont(smallPlainSystemFont);
            Button btnn = new Button("Supprimer event");

            btnn.getAllStyles().setBorder(Border.createRoundBorder(13, 12));
//      C1.add(BorderLayout.center(type));                       
// 
            C1.add(BorderLayout.center(titre));

            C1.add(BorderLayout.center(description));
            C1.add(BorderLayout.center(ville));
            //C1.add(BorderLayout.center(Categorie));

//   C1.add(BorderLayout.center(Date));
            // C1.add(BorderLayout.center(stock));
            C1.add(btnn);
            add(C1);

            btnn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Dialog d = new Dialog();

                    if (Dialog.show("Confirmation", "delete this event??", "Ok", "Annuler")) {

                        ServiceEvent event = new ServiceEvent();
                        event.Supprimer(ev.getRef());
                        Dialog.show("Alert", "event " + ev.getTitre() + " supprimé", "ok", null);
                        new AffichageEvent(theme).show();;

                    }
                }
            });

        }
    }

}
