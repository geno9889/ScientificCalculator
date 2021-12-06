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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 *
 * @author Giuseppe
 */
@Getter
@EqualsAndHashCode
@ToString
public class VariablesOperations implements SingleOperationsInterface{

    private HashMap<String, String> operations;
    private HashMap<Character, ComplexNumber> variables;
    
    public VariablesOperations(){
        operations = new HashMap<>();
        variables = new HashMap<>();
        
        for(int i = 97; i<=122; i++){   //initialize variables in Map through ASCII code
            variables.put((char) i, null);
        }
        
        operations.put(">", "majorX");
        operations.put("+", "plusX");
        operations.put("-", "minusX");
        operations.put("<", "minorX");

        
    }
    
    @Override
    public boolean executeifExists(String operation, Stack stackNumbers) throws StackBadSizeException {
        Method m1;
        if(operation.length() != 2){
            return false;
        }
        String op = operation.substring(0, 1);
        char variable = operation.charAt(1);
        if (operations.containsKey(op) && variables.containsKey(variable)){
            try {
                m1 = VariablesOperations.class.getDeclaredMethod(operations.get(op), Stack.class, Character.class);
                m1.invoke(this, stackNumbers, variable);
            } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException ex1) {
                Logger.getLogger(VariablesOperations.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (InvocationTargetException ex2) {
                if(ex2.getCause() instanceof StackBadSizeException)
                    throw (StackBadSizeException)ex2.getCause();
            }
            return true;
        }
        return false;
    }
    
    
    private void majorX(Stack stackNumbers, Character variable) throws StackBadSizeException{
        if(stackNumbers.isEmpty()) throw new StackBadSizeException("There is no one element into stack");
        ComplexNumber number = (ComplexNumber) stackNumbers.pop();
        variables.put(variable, number);
    }
    

    private void plusX(Stack stackNumbers, Character variable) throws StackBadSizeException{
        if(stackNumbers.isEmpty()) throw new StackBadSizeException("There is no one element into stack");
        ComplexNumber varValue = variables.get(variable);
        ComplexNumber finalVarValue = ComplexNumber.sum(varValue, (ComplexNumber) stackNumbers.pop());
        variables.put(variable, finalVarValue);
    }

    private void minusX(Stack stackNumbers, Character variable) throws StackBadSizeException{
        if(stackNumbers.isEmpty()) throw new StackBadSizeException("There is no one element into stack");
        ComplexNumber topElement = (ComplexNumber) stackNumbers.pop();
        variables.put(variable, ComplexNumber.substraction(variables.get(variable), topElement));
    } 
    
    private void minorX (Stack stackNumbers, char variable){
        ComplexNumber x = (ComplexNumber) variables.get(variable);
        stackNumbers.push(x);
    }

}
