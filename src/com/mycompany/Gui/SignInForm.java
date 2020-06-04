/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package com.mycompany.Gui;

import com.codename1.components.FloatingHint;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.Map;
import utils.SessionManager;
import utils.Statics;

/**
 * Sign in UI
 *
 * @author Shai Almog
 */
public class SignInForm extends BaseForm {

    ConnectionRequest connectionRequest;

    public SignInForm(Resources res) {
        super(new BorderLayout());

        if (!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout) getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
        getTitleArea().setUIID("Container");
        setUIID("SignIn");

        add(BorderLayout.NORTH, new Label(res.getImage("logo.png"), "LogoLabel"));

        TextField username = new TextField("", "Username", 20, TextField.ANY);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        username.setSingleLineTextArea(true);
        password.setSingleLineTextArea(true);

        Button signIn = new Button("Sign In");
        Button signUp = new Button("Sign Up");
        signUp.addActionListener(e -> new SignUpForm1(res).show());
        signUp.setUIID("Link");
        Label doneHaveAnAccount = new Label("Don't have an account?");

        Container content = BoxLayout.encloseY(
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                signIn,
                FlowLayout.encloseCenter(doneHaveAnAccount, signUp)
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
        signIn.requestFocus();
//        signIn.addActionListener(e -> {
//            if (!username.getText().isEmpty() && !password.getText().isEmpty()) {
//                String url = "http://127.0.0.1:8000/api/json/login";
//                
//                connectionRequest = new ConnectionRequest(url, false);
//                connectionRequest.addArgument("username", username.getText());
//                connectionRequest.addArgument("password", password.getText());
//                System.out.println(connectionRequest.getUrl());
//                connectionRequest.addResponseListener((action) -> {
//                    try {
//                        System.out.println("test");
//                        JSONParser j = new JSONParser();
//                        String json = new String(connectionRequest.getResponseData()) + "";
//                        System.out.println(json);
//                        if (json.equals("failed")) {
//                            Dialog.show("Echec d'authenfication", "username ou mot de passe éronné", "Ok", null);
//                        } else {
//
//                            Map<String, Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
//                            float id = Float.parseFloat(user.get("id").toString());
//
//                            SessionManager.setId((int) id);
//                            SessionManager.setPass(password.getText());
//
//                            if (user.size() > 0) {
//                                SessionManager.setEmail(user.get("email").toString());
//                                SessionManager.setPassword(password.getText());
//                                SessionManager.setUserName(user.get("username").toString());
//
//                                if (user.get("roles").toString().contains("MEMBRE")) {
//                                    new ProfileForm(res).show();
//                                    
//                                }   else if (user.get("roles").toString().contains("ROLE_USER")) {
//                                    new  ProfileForm(res).show();
//                                }  
////                                else if (user.get("roles").toString().contains("ROLE_ELEVE")) {
////                                    new ProfileForm(res).show();
////                                }
////                                else if (user.get("roles").toString().contains("ROLE_PARENT")) {
////                                    new ProfileForm(res).show();
////                                }
//                            }
//                        }
//                    } catch (IOException ex) {
//                    }
//                });
//            } else {
//                Dialog.show("Erreur", "Saisissez votre username et votre mot de passe", "ok", null);
//                return;
//            }
//           
//        }
//        );

        signIn.addActionListener(e -> {
            if (!username.getText().isEmpty() && !password.getText().isEmpty()) {
                String url = "http://127.0.0.1:8000/api/json/login";
                connectionRequest = new ConnectionRequest(url, false);
                connectionRequest.addArgument("username", username.getText());
                connectionRequest.addArgument("password", password.getText());
                connectionRequest.addResponseListener((action) -> {
                    try {
                        JSONParser j = new JSONParser();
                        String json = new String(connectionRequest.getResponseData()) + "";
                        if (json.equals("failed")) {
                            Dialog.show("Echec d'authenfication", "username ou mot de passe éronné", "Ok", null);
                        } else {

                            Map<String, Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                            float id = Float.parseFloat(user.get("id").toString());

                            SessionManager.setId((int) id);
                            SessionManager.setPass(password.getText());

                            if (user.size() > 0) {
                                SessionManager.setEmail(user.get("email").toString());
                                SessionManager.setPassword(password.getText());
                                SessionManager.setUserName(user.get("username").toString());

                                if (user.get("roles").toString().contains("MEMBRE")) {
                                    new ProfileForm(res).show();
                                } else if (user.get("roles").toString().contains("ROLE_STOCKMANAGER")) {
                                    new ProfileForm(res).show();
                                }
                                else if (user.get("roles").toString().contains("ROLE_ADMIN")) {
                                    new ProfileForm(res).show();
                                }
                                                                
                            }
                        }
                    } catch (IOException ex) {
                    }
                });
            } else {
                Dialog.show("Erreur", "Saisissez votre username et votre mot de passe", "ok", null);
                return;
            }
            NetworkManager.getInstance().addToQueue(connectionRequest);
        }
        );
    }

}
