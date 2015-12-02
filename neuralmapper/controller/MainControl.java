/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralmapper.controller;
import neuralmapper.model.NetworkControl;
import neuralmapper.view.GUIControl;
import neuralmapper.model.Network;

/**
 *
 * @author David
 */
public class MainControl {
    public MainControl(NetworkControl network, GUIControl gui){   
        // Build Network
        network.build(7,2,6,10); // (Number of Inputs, Number of Deep Columns, Number of Deep Rows, Number of Outputs)

        // Translate network to GUI
        
        
        // Show GUI
        gui.createAndShowGUI();
        try{
            gui.getContentPane().setNetBase((Network)network.getNetwork().clone());
        }
        catch(Exception e){
            System.out.println("Error Cloning Network in transfer to GUI");
        }
    }
}
