/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Gui;

import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Form;
import com.codename1.ui.util.Resources;
import com.mycompany.Gui.BaseForm;
/**
 *
 * @author AHMED
 */
public class Facebook extends BaseForm {

    Form current;

    public Facebook(Resources res) {

        current = this;
        setTitle("Notre Facebook");
        // setLayout(BoxLayout.y());

        // final WebEngine web = viewweb.getEngine();
        //String urlweb = "http://google.com";
        // web.load(urlweb);
        BrowserComponent browser = new BrowserComponent();
        browser.setURL("https://www.instagram.com/");

//current.add(BorderLayout.CENTER, browser);
        current.add(browser);
    }


}
