/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.se.calculatorapplication.service;

import it.unisa.diem.se.calculatorapplication.entity.MathematicalException;
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
public class CustomOperations{
    
    private HashMap<String,String[]> multipleOperations;
    private List<SingleOperationsInterface> singleOperations;
    
    public CustomOperations(List<SingleOperationsInterface> singleOperations){
        multipleOperations = new HashMap();
        this.singleOperations = singleOperations;
    }
    
    
    public boolean addOperation(String name, String operation) {
        if(!name.contains(",") && !multipleOperations.containsKey(name)){
            String[] singleOpSplit = operation.split("\\s+");
            if(checkOperation(name,singleOpSplit)){
                multipleOperations.put(name, singleOpSplit);
                return true;
            }
        }
        return false;
    }

    
    public boolean modifyOperation(String newName, String oldName, String newOperation){
        if(multipleOperations.containsKey(oldName) && (!multipleOperations.containsKey(newName) || oldName.equals(newName))){
            String[] singleOpSplit = newOperation.split("\\s+");
            if(checkOperation(newName, singleOpSplit)){
                if(!oldName.equals(newName)){
                    multipleOperations.remove(oldName);
                    multipleOperations.put(newName, singleOpSplit);
                }
                else{
                    multipleOperations.put(newName, singleOpSplit);
                }
                return true;
            }
        }
        return false;
    }
    
    // check if name of the operation already exists.
    private boolean checkOperation(String name, String[] singleOpSplit){
        for(SingleOperationsInterface op : singleOperations){
                if(op.containsOperation(name))
                    return false;
        }
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
        return true;
    }
    

    
    public boolean deleteOperation(String name) {
        if(multipleOperations.containsKey(name)){
            multipleOperations.remove(name);
        return true;
        }
        else return false;
    }

    
    public boolean executeIfExists(String operationName, Stack stackNumbers) throws StackBadSizeException, MathematicalException, NullVariableException{
        if(multipleOperations.containsKey(operationName)){
            String[] singleOpSplit = multipleOperations.get(operationName);
            for(String singleOperation : singleOpSplit){
                for(SingleOperationsInterface op : singleOperations){
                    if(op.executeIfExists(singleOperation, stackNumbers)){
                        break;
                    }
                }
            }
            return true;
        }
        return false;
    }
    
}
