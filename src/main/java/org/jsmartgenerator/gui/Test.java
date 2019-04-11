/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jsmartgenerator.gui;

import freemarker.template.Configuration;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                  
 
        try {
                   ClassLoader loader =  Test.class.getClassLoader();
            URL url = loader.getResource("TEMPLATES");
            
            File f = new File(url.toURI());
                    
           Configuration cfg = new Configuration();
         
            
            cfg.getTemplate("converter.ftl");
            
        } catch (URISyntaxException|IOException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
