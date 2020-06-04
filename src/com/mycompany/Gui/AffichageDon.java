/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Gui;

import Entites.don;
import Entites.stock;
import Service.StockService;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import java.io.IOException;

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
import com.codename1.ui.Font;

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
import Service.ServiceDon;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;

/**
 *
 * @author HP
 */
public class AffichageDon extends BaseForm {

    Button btnn = new Button("Supprimer Don");

    public AffichageDon(Resources theme) {
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

        ServiceDon serviceTask = new ServiceDon();
        for (don ev : serviceTask.getList2()) {
            Label Libelle = new Label("Libelle: " + ev.getLibelle());
            Label Quantite = new Label("Quantite: " + ev.getQuantite());
            //Picker date = new Picker();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = dateFormat.format(ev.getDate());
            Label Date = new Label("Date: " + strDate);
            Label stock = new Label("Type Stock: " + ev.getSto());

            Libelle.getStyle().setFgColor(0x1e3642);
            Font smallPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
            Libelle.getStyle().setFont(smallPlainSystemFont);
            Quantite.getStyle().setFgColor(0x1e3642);
            Quantite.getStyle().setFont(smallPlainSystemFont);
            Date.getStyle().setFgColor(0x1e3642);
            Date.getStyle().setFont(smallPlainSystemFont);
            stock.getStyle().setFgColor(0x1e3642);
            stock.getStyle().setFont(smallPlainSystemFont);
            Button btnn = new Button("Supprimer Don");
            Button modifier = new Button("Modifier Type");

            modifier.getAllStyles().setBorder(Border.createRoundBorder(13, 12));
            btnn.getAllStyles().setBorder(Border.createRoundBorder(13, 12));
            btnn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Dialog d = new Dialog();

                    if (Dialog.show("Confirmation", "delete this Don??", "Ok", "Annuler")) {

                        ServiceDon stock = new ServiceDon();
                        stock.Supprimer(ev.getReference());
                        Dialog.show("Alert", "stock " + ev.getLibelle() + " supprimé", "ok", null);
                        new AffichageDon(theme).show();;

                    }
                }
            });

            modifier.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ee) {
                    //   f.setHidden(true);

                    TextField eNom = new TextField("", "Quantite");

                    Button b = new Button("Modifier la Quantite");
                    eNom.setText(String.valueOf(ev.getQuantite()));
                    eNom.getStyle().setFgColor(0x1e3642);
                    Font smallPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
                    eNom.getStyle().setFont(smallPlainSystemFont);
                    add(eNom);

                    add(b);
                    show();
                    b.addActionListener((e) -> {

                        if (eNom.getText().isEmpty()) {
                            Dialog.show("Information Dialog", "Quantite Doit étre Rempli", "OK", "Cancel");
                        } else {
                            don s = new don();

                            int reference = ev.getReference();
                            s.setQuantite(Integer.parseInt(eNom.getText()));

                            s.setReference(reference);

                            ServiceDon ser = new ServiceDon();
                            ser.modifierEspace(s);

                            Dialog.show("Information Dialog", "La Quantite '" + eNom.getText() + "' A été Modifier Avec Succés ", "OK", "Cancel");

                            eNom.clear();
                        }

                        AffichageDon h = new AffichageDon(theme);
                        h.show();

                    });
                }
            });

// 
            add(Libelle);

            add(Quantite);

            add(BorderLayout.center(Date));

            add(stock);

            add(btnn);
            add(modifier);

        }
    }
}
