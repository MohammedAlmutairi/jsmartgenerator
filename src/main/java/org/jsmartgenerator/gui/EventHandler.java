/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jsmartgenerator.gui;

import java.io.File;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.jsmartgenerator.converter.Converter;
import org.jsmartgenerator.datastruture.EntityInfo;
import org.jsmartgenerator.freemaker.Templating;
import org.jsmartgenerator.reflections.ReadEntityClassFromJar;


/**
 *
 * @author Dell
 */
public class EventHandler {

  
    
    ReadEntityClassFromJar classPathManager = new ReadEntityClassFromJar();
    
    DataController mainPanel;
    ProjectData data = new ProjectData();
    List<Class<?>> selectedClasses;
    public EventHandler(ProjectData data)
    {
        this.data = data;
        mainPanel = new DataController(this,data);
        
    }
    

    public void converter() {        
        if (selectedClasses == null || selectedClasses.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Load jar  file containing Entities classes before generated code", "JsmartGenerator", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        
        Converter c = new Converter(selectedClasses);

        List<EntityInfo> i = c.convertToEntityInfo();

        
        mainPanel.getTheData();
        
        Templating templating = new Templating(data);
        templating.jsfConvertersTemplate(i);
        
        
        

    }
        public void selectOutputDirctory() {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();

            String s = f.getAbsolutePath();
            
            File compiledDircetory = new File(f.getAbsolutePath() + mainPanel.getCompiledPath());
            if (compiledDircetory.exists() && compiledDircetory.isDirectory()) {
                
                selectedClasses = classPathManager.reader(compiledDircetory, data);
            }

          
            data.setOutputDirctory(s);
            
            mainPanel.fillData(data);

        }
    }
    
    
    public void selectJarFile()
    {
     
        try {
            selectedClasses = classPathManager.reader();
            List<String> packgesName = classPathManager.getPackgesName();
            if (packgesName.size() == 1)
            {
                data.setEntityPackage(packgesName.get(0));
             
                 mainPanel.fillData(data);
                
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }

    public void loadJar() {
        try {
            selectedClasses = classPathManager.reader();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    
   

    public DataController getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(DataController mainPanel) {
        this.mainPanel = mainPanel;
    }

    public ProjectData getData() {
        return data;
    }

    public void setData(ProjectData data) {
        this.data = data;
    }
    
    
}


