/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.codename1.io.Preferences;

public class SessionManager {

    public static Preferences pref;

    private static int id;
    private static String  role;

    
    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        SessionManager.role = role;
    }

    public static void setId(int id) {
        pref.set("id", id);
    }

    public static int getId() {
        return pref.get("id", id);
    }
    private static int tel;

    public static Preferences getPref() {
        return pref;
    }

    public static void setPref(Preferences pref) {
        SessionManager.pref = pref;
    }

    public static int getTel() {
        return pref.get("telephone", tel);
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        SessionManager.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        SessionManager.password = password;
    }

    private static String username;

    public static void setUserName(String username) {
        pref.set("username", username);
    }

    public static String getUserName() {
        return pref.get("username", username);
    }

    private static String password;

    public static void setPass(String password) {
        pref.set("password", password);
    }

    public static String getPass() {
        return pref.get("password", password);
    }

    private static String email;

    public static void setEmail(String email) {
        pref.set("email", email);
    }

    public static String getEmail() {
        return pref.get("email", email);
    }

 
    
} 
