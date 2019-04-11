package org.jsmartgenerator.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import javax.swing.JTextField;
import org.jsmartgenerator.freemaker.MyProrprties;
import org.jsmartgenerator.reflections.ReadEntityClassFromJar;

public class DataController extends JPanel {

    JPanel panel3;
    JButton Button1;
    JButton Button2;
    JPanel panel4;
    JLabel Label1;
    JLabel Label2;
    JLabel Label3;
    JLabel Label4;
    JLabel Label5;
    JLabel Label6;
    JTextField t_outout;
    JTextField t_entity;
    JTextField t_converter;
    JTextField t_managebean;
    JTextField t_primefaces;
    JTextField t_facade;
    JButton Button5;
    
    
    ProjectData data;
 
    EventHandler handler;
    
    JTextField compilesPath = new JTextField(20);
    

    public DataController(EventHandler handler,ProjectData data) {
        this.setLayout(new BorderLayout(1, 1));
        
        this.data = data;
        this.handler = handler;
        

        panel3 = new JPanel();
        add(panel3, BorderLayout.NORTH);
        panel3.setLayout(new FlowLayout(FlowLayout.LEFT));

//        Button1 = new JButton();
//
//        Button1.addActionListener((e) -> handler.selectJarFile() );
//        Button1.setText("Load Jar");
//        panel3.add(Button1);
        
        panel3.add(new JLabel("Compiled Classes"));
        compilesPath.setText("//target//classes");
        
        panel3.add(compilesPath);

        Button2 = new JButton();

        Button2.setText("Generate Code");
        Button2.addActionListener((e) -> handler.converter());
        
        panel3.add(Button2);

        panel4 = new JPanel();
        add(panel4, BorderLayout.CENTER);
        panel4.setLayout(null);

        Label1 = new JLabel();
        Label1.setText("output directory");
        Label1.setBounds(42, 36, 90, 20);
        panel4.add(Label1);

        Label2 = new JLabel();
        Label2.setText("entity package");
        Label2.setBounds(41, 61, 83, 20);
        panel4.add(Label2);

        Label3 = new JLabel();
        Label3.setText("converter  package");
        Label3.setBounds(41, 86, 110, 20);
        panel4.add(Label3);

        Label4 = new JLabel();
        Label4.setText("managebean package");
        Label4.setBounds(41, 111, 126, 20);
        panel4.add(Label4);

        Label5 = new JLabel();
        Label5.setText("primefaces dirctory");
        Label5.setBounds(41, 136, 112, 20);
        panel4.add(Label5);

        Label6 = new JLabel();
        Label6.setText("facade package");
        Label6.setBounds(41, 161, 91, 20);
        panel4.add(Label6);

        t_outout = new JTextField();
        t_outout.setColumns(10);
        t_outout.setBounds(186, 36, 311, 20);
        panel4.add(t_outout);

        t_entity = new JTextField();
        t_entity.setColumns(10);
        t_entity.setBounds(188, 61, 310, 20);
        panel4.add(t_entity);

        t_converter = new JTextField();
        t_converter.setColumns(10);
        t_converter.setBounds(187, 86, 310, 20);
        panel4.add(t_converter);

        t_managebean = new JTextField();
        t_managebean.setColumns(10);
        t_managebean.setBounds(187, 111, 310, 20);
        panel4.add(t_managebean);

        t_primefaces = new JTextField();
        t_primefaces.setColumns(10);
        t_primefaces.setBounds(187, 136, 310, 20);
        panel4.add(t_primefaces);

        t_facade = new JTextField();
        t_facade.setColumns(10);
        t_facade.setBounds(187, 161, 310, 20);
        panel4.add(t_facade);

        Button5 = new JButton();

        Button5.setText("...");
        Button5.addActionListener(e -> handler.selectOutputDirctory());

        Button5.setBounds(502, 36, 43, 20);
        panel4.add(Button5);

        //code here add after create componets
        setVisible(true);
    }

 

    public void fillData(ProjectData var) {
        this.data = var;
        t_outout.setText(var.getOutputDirctory());
        t_entity.setText(var.getEntityPackage());
        t_converter.setText(var.getConvertrPackage());
        t_managebean.setText(var.getManageBean());
        t_primefaces.setText(var.getPrimefacesForm());
        t_facade.setText(var.getFacadePackage());

    }

    public ProjectData getTheData() {
        data.setOutputDirctory(t_outout.getText());
        data.setEntityPackage(t_entity.getText());
        data.setConvertrPackage(t_converter.getText());
        data.setManageBean(t_managebean.getText());
        data.setPrimefacesForm(t_primefaces.getText());
        data.setFacadePackage(t_facade.getText());
        return data;
    }

    
    public String getCompiledPath()
    {
        return compilesPath.getText();
    }

}
