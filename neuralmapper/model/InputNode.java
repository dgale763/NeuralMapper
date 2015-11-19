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
public class InputNode {
    
    private float value;
    
    public InputNode(){
        value = 0;
    }
    
    public void setValue(float in){
        value = in;
    }
    
    public float getValue(){
        return value;
    }
}
