/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Gui;

import Entites.stock;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.plaf.Border;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Font;

import com.codename1.ui.Display;

import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;

import Service.StockService;

import com.codename1.ui.Button;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;

import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

/**
 *
 * @author bhk
 */
public class Affichage extends BaseForm {

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
    TextField espaceNom;
    Button btnAffiche;

    public Affichage(Resources theme) {
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

        StockService serviceTask = new StockService();
        for (stock ev : serviceTask.getList2()) {
            Label type = new Label("type: " + ev.getType());

            type.getStyle().setFgColor(0x1e3642);
            Font smallPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
            type.getStyle().setFont(smallPlainSystemFont);
            Button btnn = new Button("Supprimer Type");
            Button modifier = new Button("Modifier Type");

            btnn.getAllStyles().setBorder(Border.createRoundBorder(13, 12));

            modifier.getAllStyles().setBorder(Border.createRoundBorder(13, 12));
//      C1.add(BorderLayout.center(type));                       
// 

            btnn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    if (Dialog.show("Confirmation", "Vous Voulez Vraiment Supprimer ce Type ", "Ok", "Annuler")) {
                        StockService stock = new StockService();
                        stock.Supprimer(ev.getId());
                        Dialog.show("Alert", "stock " + ev.getType() + " supprimé", "ok", null);
                        new Affichage(theme).show();;

                    }
                }
            });

            modifier.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ee) {
                    //   f.setHidden(true);

                    TextField eNom = new TextField("", "Type");

                    Button b = new Button("Modifier Type");
                    eNom.setText(ev.getType());
                    eNom.getStyle().setFgColor(0x1e3642);
                    Font smallPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
                    eNom.getStyle().setFont(smallPlainSystemFont);
                    add(eNom);

                    add(b);
                    show();
                    b.addActionListener((e) -> {

                        if (eNom.getText().isEmpty()) {
                            Dialog.show("Information Dialog", " le type  Doit étre Rempli", "OK", "Cancel");
                        } else {
                            stock s = new stock();

                            int id = ev.getId();
                            s.setType(eNom.getText());

                            s.setId(id);

                            StockService ser = new StockService();
                            ser.modifierEspace(s);

                            Dialog.show("Information Dialog", "le Type  '" + eNom.getText() + "' A été Modifier Avec Succés ", "OK", "Cancel");

                            eNom.clear();
                        }

                        Affichage h = new Affichage(theme);
                        h.show();

                    });
                }
            });

            add(BorderLayout.center(type));
            add(btnn);

            add(modifier);

        }
    }
}
