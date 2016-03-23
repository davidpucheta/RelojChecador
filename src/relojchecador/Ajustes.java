/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relojchecador;

import java.util.HashMap;
import java.util.prefs.Preferences;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author davidreyespucheta
 */
public class Ajustes {

    public static void setConfig(String usuario, String email, String clave, String departamento, String oficina) {
                        
        Preferences prefsRoot = Preferences.userRoot();
        Preferences myPrefs = prefsRoot.node("relojchecador.preference.staticPreferenceLoader");
        myPrefs.put("usuario", usuario);
        myPrefs.put("clave", clave);
        myPrefs.put("email", email);
        myPrefs.put("departamento", departamento);
        myPrefs.put("oficina", oficina);
        //return prefsRoot;
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Guardar");
        alert.setHeaderText("Guardar configuración");
        String s ="La configuración se guardo correctamente";
        alert.setContentText(s);
        alert.show();

     }
    

    public static HashMap<String, String> getConfig() {

       Preferences myfilePrefs = Preferences.userRoot();
        myfilePrefs = myfilePrefs.node("relojchecador.preference.staticPreferenceLoader");
        
        String usuario = myfilePrefs.get("usuario", "no encontrado");
        String clave = myfilePrefs.get("clave", "no encontrado");
        String email = myfilePrefs.get("email", "no encontrado");
        String departamento = myfilePrefs.get("departamento", "no encontrado");
        String oficina = myfilePrefs.get("oficina", "no encontrado");
        
        
        //System.out.println(usuario + " " + "email " + email + clave + " " + departamento + " " + oficina );
        
        HashMap<String, String> user = new HashMap<>();
        
        user.put("usuario",usuario);
        user.put("clave", clave);
        user.put("email", email);
        user.put("departamento", departamento);
        user.put("oficina", oficina);
        
        return user;
     }


}
