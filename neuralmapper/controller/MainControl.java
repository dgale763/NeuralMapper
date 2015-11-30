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
        network.build(2,1,2,1);

        // Translate network to GUI
        try{
            gui.setNetBase((Network)network.getNetwork().clone());
        }
        catch(Exception e){
            System.out.println("Error Cloning Network in transfer to GUI");
        }
        
        // Show GUI
        gui.createAndShowGUI();
    }
}
