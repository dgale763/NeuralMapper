/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralmapper;
import neuralmapper.model.NetworkControl;
import neuralmapper.view.GUIControl;
import neuralmapper.controller.MainControl;


/**
 *
 * @author David
 */
public class NeuralMapper {

    /**
     * @param args the command line arguments
     */
    private static NetworkControl network;
    private static GUIControl gui;
    private static MainControl controller;
    
    public static void main(String[] args) {
        network = new NetworkControl();
        gui = new GUIControl();
        controller = new MainControl(network, gui);
    }
    
}
