/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
 * @author marcograziano
 */

@Getter
@EqualsAndHashCode
@ToString
public class MathematicalOperations implements SingleOperationsInterface{
    
    private HashMap<String,String> operations;

    public MathematicalOperations() {
        this.operations = new HashMap();
        this.operations.put("+", "sum");
        this.operations.put("-", "substraction");
        this.operations.put("*", "moltiplication");
        this.operations.put("/", "division");
    }

    @Override
    public boolean executeifExists(String operation, Stack stackNumbers) {
        Method m1;
        
        if (operations.containsKey(operation) && !stackNumbers.isEmpty()){
            try {
                m1 = MathematicalOperations.class.getDeclaredMethod(operations.get(operation), String.class, Stack.class);
                m1.invoke(this, operation, stackNumbers);
            } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(MathematicalOperations.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }
        return false;
    }
    
    private void sum(String operation, Stack stackNumbers) throws StackBadSizeException{ 
        ComplexNumber temp;
        if(stackNumbers.size()==1){
            throw new StackBadSizeException("Stack can't do operation " + operation + "with only one argument\n");
        }
        temp = ComplexNumber.sum((ComplexNumber) stackNumbers.pop(), (ComplexNumber) stackNumbers.pop());
        stackNumbers.push(temp);
    }
    
    private void substraction(String operation, Stack stackNumbers) throws StackBadSizeException{
        
        ComplexNumber temp;
        if(stackNumbers.size()==1){
            throw new StackBadSizeException("Stack can't do operation " + operation + "with only one argument\n");
        }
        temp = ComplexNumber.substraction((ComplexNumber) stackNumbers.pop(), (ComplexNumber) stackNumbers.pop());
        stackNumbers.push(temp);
        
    }
    
}



