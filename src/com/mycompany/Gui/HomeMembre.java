/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Gui;

import Entites.Membre;

import Service.ServiceMembre;

import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.plaf.Border;
import com.codename1.components.ScaleImageLabel;

import com.codename1.ui.Display;

import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;

import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

/**
 *
 * @author HP
 */
public class HomeMembre extends BaseForm {

    //Form hi = new Form();
    //Form f;
    private Label titreEror;
    TextField tnomarticle;
    TextField nom;
    TextField tel;
    TextField prenom;
    TextField ville;
    TextField email;

    Picker datedeb;
    TextField tncontenueArticle;
    TextField Stock_id;

    Container descriptionContainer;
    Button btajout, btnaff;

    public HomeMembre(Resources theme) {
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

        ///     f = new Form("home");
        nom = new TextField("", "Nom.........!", 20, TextField.ANY);
        nom.getUnselectedStyle().setFgColor(621200);
        tel = new TextField("", "Tel.....!", 20, TextField.ANY);
        tel.getUnselectedStyle().setFgColor(621200);
        prenom = new TextField("", "Prenom.....!", 20, TextField.ANY);
        prenom.getUnselectedStyle().setFgColor(621200);
        ville = new TextField("", "Ville.....!", 20, TextField.ANY);
        ville.getUnselectedStyle().setFgColor(621200);
        email = new TextField("", "E-mail.....!", 20, TextField.ANY);
        email.getUnselectedStyle().setFgColor(621200);

        btajout = new Button("Ajouter Membre");
        btnaff = new Button("Listes Des Membres");

        btajout.getAllStyles().setBorder(Border.createRoundBorder(13, 12));
        btnaff.getAllStyles().setBorder(Border.createRoundBorder(15, 12));

        add(nom);
        add(prenom);
        add(ville);

        add(email);
        add(tel);

        add(btajout);
        add(btnaff);

        btajout.addActionListener((e) -> {

            if (nom.getText().equals("")) {

                Dialog.show("ERREUR SAISIE", "nom  VIDE", "OK", "ANNULER");
            }
            if (prenom.getText().equals("")) {

                Dialog.show("ERREUR SAISIE", "prenom  VIDE", "OK", "ANNULER");
            }
            if (ville.getText().equals("")) {

                Dialog.show("ERREUR SAISIE", "Ville  VIDE", "OK", "ANNULER");
            }
            if (email.getText().equals("")) {

                Dialog.show("ERREUR SAISIE", "Ville  VIDE", "OK", "ANNULER");
            } else if (tel.getText().equals("")) {

                Dialog.show("ERREUR SAISIE", "tel VIDE", "OK", "ANNULER");
            } else if (!isAEntier(tel.getText())) {

                Dialog.show("ERREUR SAISIE", "teldoit etre un nombre ", "OK", "ANNULER");
            } else {

                ServiceMembre ser = new ServiceMembre();
                Membre article = new Membre(1, nom.getText(), prenom.getText(), ville.getText(), email.getText(), Integer.parseInt(tel.getText()));

                ser.ajoutArticle(article);
                Dialog.show("felicitation", " votre Membre a ete ajoute", "ok", null);
            }
        });

        btnaff.addActionListener((e) -> {
            AffichageMembre a = new AffichageMembre(theme);
            a.show();
        });
    }
//
//    public Form getF() {
//        return f;
//    }
//
//    public void setF(Form f) {
//        this.f = f;
//    }

    public Label getTitreEror() {
        return titreEror;
    }

    public void setTitreEror(Label titreEror) {
        this.titreEror = titreEror;
    }

    public TextField getTnomarticle() {
        return tnomarticle;
    }

    public void setTnomarticle(TextField tnomarticle) {
        this.tnomarticle = tnomarticle;
    }

    public TextField getCombo() {
        return Stock_id;
    }

    public void setCombo(TextField combo) {
        this.Stock_id = combo;
    }

    public Container getDescriptionContainer() {
        return descriptionContainer;
    }

    public void setDescriptionContainer(Container descriptionContainer) {
        this.descriptionContainer = descriptionContainer;
    }

    public Button getBtajout() {
        return btajout;
    }

    public void setBtajout(Button btajout) {
        this.btajout = btajout;
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
