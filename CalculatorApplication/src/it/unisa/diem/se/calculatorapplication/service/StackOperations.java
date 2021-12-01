/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.se.calculatorapplication.service;

import it.unisa.diem.se.calculatorapplication.entity.ComplexNumber;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Giuseppe
 */
public class StackOperations {
    
    private HashMap<String,String> operations;
    
    public StackOperations(){
        operations = new HashMap<>();
        operations.put("clear", "clear");
    }
    
    public boolean executeifExists(String operation, Stack stackNumbers){
        Method m1;
        if (operations.containsKey(operation)){
            try {
                m1 = StackOperations.class.getDeclaredMethod(operations.get(operation), Stack.class);
                m1.invoke(this, stackNumbers);
            } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException ex1) {
                Logger.getLogger(MathematicalOperations.class.getName()).log(Level.SEVERE, null, ex1);
            } catch(InvocationTargetException ex2){
                Logger.getLogger(MathematicalOperations.class.getName()).log(Level.SEVERE, null, ex2);
            }
            return true;
        }
        return false;
    }
    
    private void clear(Stack stackNumbers){
        stackNumbers.clear();
    }

}
