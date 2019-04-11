/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jsmartgenerator.freemaker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
 
import org.jsmartgenerator.datastruture.EntityInfo;
import org.jsmartgenerator.gui.ProjectData;
import org.jsmartgenerator.gui.Test;

/**
 *
 * @author Dell
 */
public class Templating {

    Configuration cfg;
    Template template;
    Map<String, Object> data;

    ProjectData projectData;


    public Templating(ProjectData projectData) {
        try {
            this.projectData = projectData;

            cfg = new Configuration();

            cfg.setClassForTemplateLoading(this.getClass(), "/TEMPLATES");


        } catch (Exception ex) {
            Logger.getLogger(Templating.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void jsfConvertersTemplate(List<EntityInfo> list) {

        for (EntityInfo a : list) {
            jsfConverterTemplate(a);
            primeFacesForm(a);
            manageBean(a);
            compsite(a);
        }
        indexPage(list);

    }

    private void jsfConverterTemplate(EntityInfo entityInfo) {

        try {
        
                
          

            template = cfg.getTemplate("converter.ftl");
          

            data = new HashMap<>();
            data.put("converterPackage", MyProrprties.removeSrcMainJava(projectData.getConvertrPackage()));
            data.put("entityPackage", MyProrprties.removeSrcMainJava(projectData.getEntityPackage()));

            data.put("facadePackage", MyProrprties.removeSrcMainJava(projectData.getFacadePackage()));
            data.put("entity", entityInfo);
            WriteToFile(String.format("%sConverter.java", entityInfo.getJavaClass().getSimpleName()), projectData.getConvertrPackage());

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void primeFacesForm(EntityInfo entityInfo) {

        try {

            template = cfg.getTemplate("form.ftl");
            data = new HashMap<>();
            data.put("object", entityInfo.getObjectName());

            data.put("mb", entityInfo.getManageBean());
            data.put("entity", entityInfo);
            WriteToFile(String.format("%sForm.xhtml", entityInfo.getJavaClass().getSimpleName()), projectData.getPrimefacesForm());

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void indexPage(List<EntityInfo> entityInfos) {

        try {

            template = cfg.getTemplate("index.ftl");
            data = new HashMap<>();

            data.put("entities", entityInfos);
            data.put("primefacesForm", MyProrprties.removeSrcMainWebApp(projectData.getPrimefacesForm()));
            WriteToFile("index.xhtml", MyProrprties.primefacesForm);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void compsite(EntityInfo entityInfo) {

        try {

            template = cfg.getTemplate("compsite.ftl");
            data = new HashMap<>();
            data.put("entity", entityInfo);
            data.put("mb", entityInfo.getManageBean());
            WriteToFile(String.format("%s.xhtml", entityInfo.getSimpleName2()), "src//main//webapp//resources//form");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void manageBean(EntityInfo entityInfo) {

        try {

            template = cfg.getTemplate("mb.ftl");
            data = new HashMap<>();
            data.put("entity", entityInfo);
            data.put("facadePackage", MyProrprties.removeSrcMainJava(projectData.getFacadePackage()));
            data.put("entityPackage", MyProrprties.removeSrcMainJava(projectData.getEntityPackage()));
            data.put("manageBeanPackage", MyProrprties.removeSrcMainJava(projectData.getManageBean()));
            WriteToFile(String.format("%sMB.java", entityInfo.getJavaClass().getSimpleName()), projectData.getManageBean());

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void WriteToFile(String name, String subDirectory) {

        try {
            String dir = projectData.getOutputDirctory();
            if (subDirectory != null) {
                if (subDirectory.contains(".")) {
                    subDirectory = subDirectory.replace(".", "\\");
                }
                dir = dir + "\\" + subDirectory;
                File f = new File(dir);
                if (!f.exists()) {
                    f.mkdirs();
                }

            }
            String filename = dir + "\\" + name;
            try (Writer file = new FileWriter(new File(filename))) {
                template.process(data, file);
                file.flush();
            }
        } catch (IOException | TemplateException ex) {
            ex.printStackTrace();
        }

    }



}
