package org.jsmartgenerator.reflections;


import java.io.File;
import java.io.FileInputStream;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
 
import java.util.List;
 
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import javax.persistence.Embeddable;
 
 
import javax.persistence.Entity;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
 
import org.jsmartgenerator.freemaker.MyProrprties;
import org.jsmartgenerator.gui.ProjectData;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dell
 */
public class ReadEntityClassFromJar {
 
 
    public static List<String> packagesName = new  ArrayList<>();
    
    ClassLoader loader;
    ProjectData projectData;
  
    public List<Class<?>> reader(File compiledDirectory, ProjectData projectData)  {
        this.projectData = projectData;
        loadDirctory(compiledDirectory);
        List<Class<?>> mylist = new ArrayList<>();
        
        try
        {
            readClassInDirctory(compiledDirectory, "", mylist);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Check than your porject only contain entity classes and facade classes", "JSmartGenerator", JOptionPane.ERROR_MESSAGE);
            
        }
        
        if (packagesName.size() == 1) {
            if (projectData != null) {
                projectData.setEntityPackage(packagesName.get(0));

            }
        }
        return mylist;
    }
    
    public   List<Class<?>>  reader() throws Exception
    {
             
  
        List<Class<?>> list = new ArrayList<>();
        
        JFileChooser chooser = new JFileChooser(new File("..//JPALearning/target"));
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        
        int s = chooser.showOpenDialog(null);
        
        
        if (s == JFileChooser.APPROVE_OPTION)
        {
            File f = chooser.getSelectedFile();
            

 
            
          
//            URL url = new URL("file:///"+f.getAbsolutePath()+(f.isDirectory()?"//":""));
             
            
           
            
//            Thread.currentThread().setContextClassLoader(loader);
               
            if (f.isDirectory())
            {
                List<Class<?>> mylist = new ArrayList<>();
               
                loadDirctory(f);
                readClassInDirctory(f, "", mylist);
                
                if (packagesName.size() == 1) {
                    if (projectData != null)
                    {
                        projectData.setEntityPackage(packagesName.get(0));
                        
                    }
                }
                
                return mylist;
            }
            URL url = f.toURI().toURL();
              loader = new URLClassLoader(new URL[]{url},ReadEntityClassFromJar.class.getClassLoader());
            
                        
            JarInputStream jarFile = new JarInputStream(new FileInputStream(f));
            JarEntry jarEntry;
            packagesName = new  ArrayList<>();
            while( (jarEntry = jarFile.getNextJarEntry()) != null  )
            {
                String name= jarEntry.getName();
                if (name.endsWith(".class"))
                {
                    name = name.replace("/", ".").replace(".class", "");  
                    Class<?> t = loader.loadClass(name);
                    addEntityToList(t, list);
                   
                }
               
            }

        }
        if (packagesName.size() == 1)
        {
            MyProrprties.entityPackage = packagesName.get(0);
        }
        return list;
        

    }
    
    public   boolean isEntityClass(Class<?> myclass)
    {
        Annotation[] annotations = myclass.getAnnotations();
        for (Annotation a:annotations)
        {
            if (a.annotationType() == Entity.class || a.annotationType() == Embeddable.class)
            {
                return true;
            }
        }
        return false;
    }
    
    
    public   List<String> getPackgesName()
    {
 
  
        return packagesName;
    }
    

    public void loadDirctory(File f) {
        try {
            URL url = f.toURI().toURL();
            loader = new URLClassLoader(new URL[]{url});
        } catch (Exception e) {
           
            
//            e.printStackTrace();
        }

    }

    private List<Class<?>> readClassInDirctory(File f,String packageName, List<Class<?>> list) {


        File[] files = f.listFiles();
      
        for (File s : files) {
            if (s.isDirectory()) {
                readClassInDirctory(s, packageName + (packageName.equals("") ? "" : ".")  + s.getName(),list);
            } 
            else if (s.getName().endsWith(".class")) {
             
                String className = packageName + "." + s.getName().replace("/", ".").replace(".class", "");
                try {
                    Class<?> c = loader.loadClass(className);
                    if (addEntityToList(c, list) == false)
                    {
                        if (projectData != null)
                        {
                            if (s.getName().endsWith("AbstractFacade.class"))
                            {
                                projectData.setFacadePackage(packageName);
                                
                            }
                        }
                    }
                } catch (Exception ex) { 
                    ex.printStackTrace();
                }
            }
        }
         
         return list;
    }
    
    
    
    public boolean addEntityToList(Class<?> t, List<Class<?>> list) {
        boolean isEntity = isEntityClass(t);
        if (isEntity) {
            list.add(t);
            if (packagesName.indexOf(t.getPackage().getName()) == -1) {
                packagesName.add(t.getPackage().getName());
                

            }

            return true;
        }
        return false;
    }
    
    public static void main(String[] args) {
        File f = new  File("D:\\NetBeans 8 Workspace\\JPALearning\\target\\classes\\");
        ReadEntityClassFromJar jar = new ReadEntityClassFromJar();
        jar.loadDirctory(f);
          List<Class<?>> list = new ArrayList<>();
               
        jar.readClassInDirctory(f,"",list);
    }
}
