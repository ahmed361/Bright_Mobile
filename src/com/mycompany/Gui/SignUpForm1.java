package com.mycompany.Gui;

import Entites.User;
import Service.ServiceUser;
import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.Resources;

/**
 * Signup UI
 *
 * @author AHMED
 */
public class SignUpForm1 extends BaseForm {

    public SignUpForm1(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");

        TextField username = new TextField("", "Username", 20, TextField.ANY);
        TextField email = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        TextField confirmPassword = new TextField("", "Confirm Password", 20, TextField.PASSWORD);
        TextField city = new TextField("", "city", 20, TextField.ANY);
        ComboBox<String> roles = new ComboBox<>();
        username.setSingleLineTextArea(false);
        email.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        confirmPassword.setSingleLineTextArea(false);
        Button next = new Button("Next");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");
        roles.addItem("Choisissez un role");
        roles.addItem("Association");
        roles.addItem("Membre");

        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(email),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                new FloatingHint(confirmPassword),
                createLineSeparator(),
                new FloatingHint(city),
                createLineSeparator(),
                roles
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        next.requestFocus();
        next.addActionListener(e -> {

            ServiceUser ser = ServiceUser.getInstance();
            if (username.getText().isEmpty()) {
                Dialog dlg = new Dialog(" saisir votre username ");
                Button ok = new Button(new Command("OK"));
                dlg.add(ok);
                dlg.showDialog();
                return;
            } else if (email.getText().isEmpty()) {

                Dialog dlg = new Dialog(" saisir votre adresse mail ");
                Button ok = new Button(new Command("OK"));
                dlg.add(ok);
                dlg.showDialog();
                return;
            } else if (password.getText().isEmpty()) {

                Dialog dlg = new Dialog("saisir votre mot de passe ");
                Button ok = new Button(new Command("OK"));
                dlg.add(ok);
                dlg.showDialog();
                return;
            } else if (roles.getSelectedIndex() <= 0) {
                Dialog dlg = new Dialog("Choississez un role");
                Button ok = new Button(new Command("OK"));
                dlg.add(ok);
                dlg.showDialog();
                return;
            } else if (!password.getText().equals(confirmPassword.getText())) {
                Dialog dlg = new Dialog("Mots de passe non identiques");
                Button ok = new Button(new Command("OK"));
                dlg.add(ok);
                dlg.showDialog();
                return;
            }

//            User t = new User(username.getText(), password.getText(), email.getText(), city.getText());
//            if (roles.getSelectedItem().equals("Membre")) {
//                t.setRole("ROLE_USER");
//            } else if (roles.getSelectedItem().equals("Association")) {
//                t.setRole("ROLE_USER");
//            }
//
//            System.out.println("a" + t.toString());
//
//            ser.addUser(t);
//            Button ok = new Button(new Command("OK"));
//
//            Dialog dlg = new Dialog("Votre compte a ete cree" + " " + t.getUsername());
//
//            TextArea taa = new TextArea("Bienvenue  ");
//            taa.setEditable(false);
//            taa.setUIID("DialogBody");
//            taa.getAllStyles().setFgColor(0);
//            dlg.add(taa);
//
//            ok.getAllStyles().setBorder(Border.createEmpty());
//            ok.getAllStyles().setFgColor(0);
//            ok.getUnselectedStyle().setFgColor(100000);
//
//            dlg.add(ok);
//            dlg.showDialog();
//
//           new SignInForm(res).show();
            User t = new User(username.getText(), password.getText(), email.getText(), city.getText(), roles.getSelectedItem());
            if (roles.getSelectedItem().equals("Membre")) {
              //  t.setRole("ROLE_USER");
                new ProfileForm(res).show();
            } else if (roles.getSelectedItem().equals("Association")) {
              //  t.setRole("ASSOCIATION");
                new ProfileForm(res).show();
            }

            System.out.println("a" + t.toString());
            ser.addUser(t);
            Button ok = new Button(new Command("OK"));

            Dialog dlg = new Dialog("Votre compte a ete cree" + " " + t.getUsername());

            TextArea taa = new TextArea("Bienvenue dans votre espace" + " " + t.getRole());
            taa.setEditable(false);
            taa.setUIID("DialogBody");
            taa.getAllStyles().setFgColor(0);
            dlg.add(taa);

            ok.getAllStyles().setBorder(Border.createEmpty());
            ok.getAllStyles().setFgColor(0);
            ok.getUnselectedStyle().setFgColor(100000);

            dlg.add(ok);
            dlg.showDialog();

        });
    }

}
