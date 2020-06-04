package com.mycompany.Gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import utils.SessionManager;

public class BaseForm extends Form {

    public BaseForm() {
    }

    public BaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public BaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    public Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    public void addSideMenu(Resources res) {
        Toolbar tb = getToolbar();
        Image img = res.getImage("profile-background.jpg");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        tb.addComponentToSideMenu(LayeredLayout.encloseIn(
                sl,
                FlowLayout.encloseCenterBottom(
                        new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond"))
        ));
        // tb.addMaterialCommandToSideMenu("Actualite", FontImage.MATERIAL_WEB, (ActionListener) (ActionEvent evt) -> {
        // ActualiteForm h = new ActualiteForm(res);
        //h.getF().show();
        // });

        tb.addMaterialCommandToSideMenu("Membres", FontImage.MATERIAL_UPDATE, e -> new HomeMembre(res).show());
        tb.addMaterialCommandToSideMenu("Evenements", FontImage.MATERIAL_UPDATE, e -> new HomeEvent(res).show());
        tb.addMaterialCommandToSideMenu("Categories", FontImage.MATERIAL_UPDATE, e -> new HomeCForm(res).show());
        tb.addMaterialCommandToSideMenu("Stock", FontImage.MATERIAL_UPDATE, e -> new Homes(res).show());
        //if( SessionManager.getRole()== "MEMBRE"){
        tb.addMaterialCommandToSideMenu("Page FB & Insta", FontImage.MATERIAL_SETTINGS, e -> new Facebook(res).show());
        // }
        tb.addMaterialCommandToSideMenu("Informations", FontImage.MATERIAL_SETTINGS, e -> new ProfileForm(res).show());
        tb.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> new WalkthruForm(res).show());

    }
}
