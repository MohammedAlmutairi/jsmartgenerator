/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jsmartgenerator.gui;


import javax.swing.JFrame;

/**
 *
 * @author Dell
 */
public class Run   {
    
    ProjectData data = new ProjectData();
    EventHandler handler;
    
    public Run()
    {
        handler = new EventHandler(data);
        showFrame();
    }
    
    public void showFrame()
    {
        JFrame frame = new JFrame("JSmartGenerator");
        frame.add(handler.getMainPanel());
        frame.setSize(700,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
         
    }
    public static void main(String[] args) {
        new Run();
    }
    
}
