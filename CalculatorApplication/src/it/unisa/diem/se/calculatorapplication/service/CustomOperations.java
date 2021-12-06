/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.se.calculatorapplication.service;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Giuseppe
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CustomOperations implements MultipleOperationsInterface{
    
    private HashMap<String,String[]> customOperations;
    private List<SingleOperationsInterface> singleOperations;
    
    public CustomOperations(List<SingleOperationsInterface> singleOperations){
        customOperations = new HashMap();
        this.singleOperations = singleOperations;
    }
    
    @Override
    public boolean addOperation(String name, String operation) {
        if(!customOperations.containsKey(name)){
            for(SingleOperationsInterface op : singleOperations){  //check if name of custom operation is a name of an existing single operation
                if(op.containsOperation(name)){
                    return false;
                }
            }
            String[] singleOpSplit = operation.split("\\s+");
            boolean flag = false;
            for(String singleOp : singleOpSplit){
                for(SingleOperationsInterface op : singleOperations){
                    if(op.containsOperation(singleOp)){
                        flag = true;
                        break;
                    }
                }
                if(flag == false){
                    return false;
                }else{
                    flag = false;
                }
            }
            customOperations.put(name, singleOpSplit);
            return true;
        }
        return false;
    }

    @Override
    public boolean modifyOperation(String newName, String oldName, String newOperation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteOperation(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean execute(String operation, Stack stackNumbers) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
