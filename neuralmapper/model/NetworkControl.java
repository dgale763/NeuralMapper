/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralmapper.model;


/**
 *
 * @author David
 */
public class NetworkControl {

    /**
     * @param args the command line arguments
     */
    
    public static Network network;
    
    public static void main(String[] args) {
        network = new Network();
        network.buildNetwork(2,1,3,1); // Inputs, Deep Columns, Deep Rows, Outputs
        network.printNumNodes();
        network.printOutputs();
    }
    
}
