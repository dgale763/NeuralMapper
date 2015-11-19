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
public class DeepNode {
    
    private ArrayList<InputNode> inputList;
    private ArrayList<DeepNode> inputDeepList;
    private ArrayList<Float> weightList;
    private final int depth;
    
    public DeepNode(int d){
        depth=d;
        inputList = new ArrayList<>();
        inputDeepList = new ArrayList<>();
        weightList = new ArrayList<>();
    }
    
    public void setInputs(ArrayList<InputNode> L){
        inputList = L;
        weightList = new ArrayList<>();
        for(InputNode iN : inputList){
            float tempFloat = 0;
            weightList.add(tempFloat);
        }
    }
    
    public void setDeepInputs(ArrayList<DeepNode> L){
        inputDeepList = L;
        weightList = new ArrayList<>();
        for(DeepNode iD : inputDeepList){
            float tempFloat = 0;
            weightList.add(tempFloat);
        }
    }
    
    public int getDepth(){
        return depth;
    }
    
    public float getActivatedValue(){
        ArrayList<Float> inputValues = new ArrayList<>();
        if(depth == 0){
            for(InputNode iN : inputList){
                inputValues.add(iN.getValue());
            }
        }
        else{
            for(DeepNode dN : inputDeepList){
                inputValues.add(dN.getActivatedValue());
            }
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
