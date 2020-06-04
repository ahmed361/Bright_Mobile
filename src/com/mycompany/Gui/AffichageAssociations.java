package com.mycompany.Gui;

import Entites.Membre;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.Dialog;
import com.codename1.ui.plaf.Border;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Font;
import com.codename1.ui.Display;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.Button;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import Service.ServiceMembre;

/**
 *
 * @author AHMED
 */
public class AffichageAssociations extends BaseForm {

    Button btnn = new Button("Supprimer Don");

    public AffichageAssociations(Resources theme) {
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

        ServiceMembre serviceTask = new ServiceMembre();
        for (Membre ev : serviceTask.getList2()) {
            Label Libelle = new Label("Nom: " + ev.getNom());
            Label Quantite = new Label("Prenom: " + ev.getPrenom());
            Label Date = new Label("Ville: " + ev.getVille());
            Label email = new Label("Email: " + ev.getEmail());
            Label tel = new Label("Tel: " + ev.getTel());

            Libelle.getStyle().setFgColor(0x1e3642);
            Font smallPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
            Libelle.getStyle().setFont(smallPlainSystemFont);
            Quantite.getStyle().setFgColor(0x1e3642);
            Quantite.getStyle().setFont(smallPlainSystemFont);
            tel.getStyle().setFgColor(0x1e3642);
            tel.getStyle().setFont(smallPlainSystemFont);
            email.getStyle().setFgColor(0x1e3642);
            email.getStyle().setFont(smallPlainSystemFont);
            Date.getStyle().setFgColor(0x1e3642);
            Date.getStyle().setFont(smallPlainSystemFont);
            //stock.getStyle().setFgColor(0x1e3642);
            //stock.getStyle().setFont(smallPlainSystemFont);
            Button btnn = new Button("Supprimer Membre");

            btnn.getAllStyles().setBorder(Border.createRoundBorder(13, 12));
//      C1.add(BorderLayout.center(type));                       
// 
            add(BorderLayout.center(Libelle));
            add(BorderLayout.center(Quantite));

            add(BorderLayout.center(Date));

            add(BorderLayout.center(email));
            add(BorderLayout.center(tel));

            add(btnn);

            btnn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Dialog d = new Dialog();

                    if (Dialog.show("Confirmation", "delete this Member??", "Ok", "Annuler")) {

                        ServiceMembre stock = new ServiceMembre();
                        stock.Supprimer(ev.getId());
                        Dialog.show("Alert", "Membre " + ev.getNom() + " supprimé", "ok", null);
                        new AffichageAssociations(theme).show();
                    }
                }
            });

        }
    }

}
