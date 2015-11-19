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
public class Network {
    
    private ArrayList<InputNode> inputList;
    private ArrayList<ArrayList<DeepNode>> deepList;
    private ArrayList<OutputNode> outputList;
    private ArrayList<DeepNode> lastDeepList;
    
    public Network(){
        inputList = new ArrayList<>();
        deepList = new ArrayList<>();
        outputList = new ArrayList<>();
        lastDeepList = new ArrayList<>();
    }
    
    public ArrayList<InputNode> getInputNodes(){
        return inputList;
    }
    public ArrayList<ArrayList<DeepNode>> getDeepNodes(){
        return deepList;
    }
    public ArrayList<OutputNode> getOutputNodes(){
        return outputList;
    }
    
    public void buildNetwork(int numInputs,int numDeepColumns,int numDeepRows,int numOutputs){
        // Build Inputs first. Then Deep. Then Output.
        // Each output asks all of the leaf deep nodes for their outputs.
        // Each leaf deep node asks its input deep nodes for their outputs.
        // Each starting deep node asks its input nodes for their outputs.
        // This way, the input nodes data gets passed all the way through the network,
        // And propogates out to the outputs.
        generateInputNodes(numInputs);
        generateDeepNodes(numDeepColumns, numDeepRows);
        generateOutputNodes(numOutputs);
        
        // ----------
        // Test Cases
        // ----------
        
//        int tempDepth = deepList.get(0).get(0).getDepth();
//        System.out.println(tempDepth);
//        for(OutputNode oL : outputList){
//            for(DeepNode dN : oL.getInputs()){
//                System.out.println(dN);
//            }
//        }
    }
    
    public void generateOutputNodes(int numOutputs){
        for(int i=0;i<numOutputs;i++){
            OutputNode tempNode = new OutputNode();
            tempNode.setInputs(lastDeepList);
            outputList.add(tempNode);
        }
    }
    
    public void generateDeepNodes(int numDeepColumns, int numDeepRows){
        ArrayList<DeepNode> prevDeepCol = new ArrayList<>();
        for(int i=0;i<numDeepColumns;i++){
            ArrayList<DeepNode> tempDeepCol = new ArrayList<>();
            for(int j=0;j<numDeepRows;j++){
                if(i==0){
                    DeepNode tempNode = new DeepNode(i);
                    tempNode.setInputs(inputList);
                    tempDeepCol.add(tempNode);
                }
                else{
                    DeepNode tempNode = new DeepNode(i);
                    tempNode.setDeepInputs(prevDeepCol);
                    tempDeepCol.add(tempNode);
                }
            }
            deepList.add(tempDeepCol);
            prevDeepCol = tempDeepCol;
        }
        lastDeepList = prevDeepCol;
    }
    
    public void generateInputNodes(int numInputs){
        for(int i=0;i<numInputs;i++){
            InputNode tempNode = new InputNode();
            tempNode.setValue(1);
            inputList.add(tempNode);
        }
    }
    
    public void setInput(int index,float val){
        inputList.get(index).setValue(val);
    }
    
    public void printNumNodes(){
        System.out.println("Inputs: " + inputList.size());
        System.out.println("Deep Columns: " + deepList.size());
        if(!deepList.isEmpty()){
            if(!deepList.get(0).isEmpty()){
                System.out.println("Deep Rows: " + deepList.get(0).size());
            }
        }
        System.out.println("Outputs: " + outputList.size());
    }
    
    public void printOutputs(){
        System.out.println("-- Outputs --");
        for(OutputNode oN : outputList){
            System.out.println(oN.getActivatedValue());
        }
    }
}
