/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralmapper.controller;
import javax.swing.JOptionPane;
import neuralmapper.model.NetworkControl;
import neuralmapper.view.GUIControl;
import neuralmapper.model.Network;

/**
 *
 * @author David
 */
public class MainControl {
    
    private NetworkControl network;
    private GUIControl gui;
    
    public MainControl(NetworkControl net, GUIControl g){   
        // Build Network
        network = net;
        gui = g;
        
         // (Number of Inputs, Number of Deep Columns, Number of Deep Rows, Number of Outputs)
        builder(3,2,4,3);
        
        // Show GUI
        gui.createAndShowGUI();
        try{
            gui.getContentPane().setNetBase((Network)network.getNetwork().clone());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        gui.getContentPane().getChangeButton().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeNodesActionPerformed(evt);
            }
        });
    }
    
    private void builder(int i, int c, int r, int o){
        network.build(i,c,r,o);
    }
    
    private void changeNodesActionPerformed(java.awt.event.ActionEvent evt){
//        JOptionPane lastChance = new JOptionPane("Are You Sure? All Training Will Be Lost!!!", );
        
        int reply = JOptionPane.showConfirmDialog(null, "Are You Sure? All Training Will Be Lost!!!", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            builder(gui.getContentPane().getNewIn(), gui.getContentPane().getNewDeepC(), gui.getContentPane().getNewDeepR(), gui.getContentPane().getNewOut());
            try{
                gui.getContentPane().setNetBase((Network)network.getNetwork().clone());
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
            gui.getContentPane().repaint();
        }
        else {
             
        }
    }
}
