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
    private Stack<HashMap<Character, ComplexNumber>> temporanyStack;
    public VariablesOperations(){
        operations = new HashMap<>();
        variables = new HashMap<>();
        temporanyStack = new Stack<>();
        
        for(int i = 97; i<=122; i++){   //initialize variables in Map through ASCII code
            variables.put((char) i, new ComplexNumber(0, 0));
        }
        operations.put(">", "majorX");
        operations.put("<", "minorX");
        operations.put("+", "plusX");
        operations.put("-", "minusX");
        operations.put("save", "save");
    }
    
    @Override
    public boolean executeIfExists(String operationName, Stack stackNumbers) throws StackBadSizeException, NullVariableException {
        Method m1;
       if(operationName.equals("save")){
            try {
                m1 = VariablesOperations.class.getDeclaredMethod(operations.get(operationName));
                m1.invoke(this);
            } catch (IllegalAccessException | IllegalArgumentException | SecurityException | InvocationTargetException | NoSuchMethodException ex1) {
                Logger.getLogger(VariablesOperations.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return true;
        }        
        if (containsOperation(operationName) ){
            String op = operationName.substring(0, 1);
            char variable = operationName.charAt(1);
            try {
                m1 = VariablesOperations.class.getDeclaredMethod(operations.get(op), Stack.class, Character.class);
                m1.invoke(this, stackNumbers, variable);
            } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException ex1) {
                Logger.getLogger(VariablesOperations.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (InvocationTargetException ex2) {
                if(ex2.getCause() instanceof StackBadSizeException)
                    throw (StackBadSizeException)ex2.getCause();
                if(ex2.getCause() instanceof NullVariableException)
                    throw (NullVariableException) ex2.getCause();
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
    
    private void minorX (Stack stackNumbers, Character variable) throws NullVariableException{
        ComplexNumber x = (ComplexNumber) variables.get(variable);
        if(x == null) throw new NullVariableException("The variable is not initialized");
        stackNumbers.push(x);
    }

    @Override
    public boolean containsOperation(String operationName) {
        if(operationName.length() != 2){
            if(operations.containsKey(operationName)){
                return true;
            }
            return false;
        }   
        String op = operationName.substring(0, 1);
        char variable = operationName.charAt(1);      
        return operations.containsKey(op) && variables.containsKey(variable);
    }
    
    private void save(){
        temporanyStack.push(variables);
    }
    
    
    
}