/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralmapper.model;
import neuralmapper.model.DeepNode;
import neuralmapper.model.InputNode;
import java.util.ArrayList;
import neuralmapper.model.OutputNode;

/**
 *
 * @author David
 */
public class OutputNode {
    
    private ArrayList<DeepNode> inputList;
    
    public OutputNode(){
        inputList = new ArrayList<DeepNode>();
    }
    
    public void setInputs(ArrayList<DeepNode> L){
        inputList = L;
    }
    
    public ArrayList<DeepNode> getInputs(){
        return inputList;
    }
    
    public float getActivatedValue(){
        ArrayList<Float> inputValues = new ArrayList<>();
        for(DeepNode iN : inputList){
            inputValues.add(iN.getActivatedValue());
        }
        inputValues = activate(inputValues);
        return batch(inputValues);
    }
    
    public ArrayList<Float> activate(ArrayList<Float> in){
        ArrayList<Float> out = in;
        
        return out;
    }
    
    public float batch(ArrayList<Float> in){
        float out = 0;
        for(Float each : in){
            out += each;
        }
        // Pack Float list into Single float for return
        return out;
    }
}
