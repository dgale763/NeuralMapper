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
public class NetworkControl{

    /**
     * @param args the command line arguments
     */
    
    public Network network;
    
    public NetworkControl() {
    }
    
    public void build(int in, int dC, int dR, int out){
        network = new Network();
        network.buildNetwork(in, dC, dR, out); // Inputs, Deep Columns, Deep Rows, Outputs
        network.printNumNodes();
        network.printOutputs();
    }
    
    public Network getNetwork(){
        return network;
    }
    
    public int getIn(){
        return network.getIn();
    }
    
    public int getDeepColumns(){
        return network.getDeepColumns();
    }
    
    public int getDeepRows(){
        return network.getDeepRows();
    }
    
    public int getOut(){
        return network.getOut();
    }
    
}
