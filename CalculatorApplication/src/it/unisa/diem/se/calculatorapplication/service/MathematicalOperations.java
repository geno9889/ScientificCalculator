/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package it.unisa.diem.se.calculatorapplication.service;

import it.unisa.diem.se.calculatorapplication.entity.ComplexNumber;
import it.unisa.diem.se.calculatorapplication.entity.MathematicalException;
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
        operations = new HashMap();
        operations.put("+", "sum");
        operations.put("-", "substraction");
        operations.put("*", "multiplication");
        operations.put("/", "division");
        operations.put("sqrt", "squareRoot");
        operations.put("+-", "invertSign");
    }

    @Override
    //check if name of the operation is an existing key and execute corresponding method(value in hashmap)
    public boolean executeifExists(String operation, Stack stackNumbers) throws StackBadSizeException, MathematicalException{
        Method m1;
        if (operations.containsKey(operation)){
            try {
                m1 = MathematicalOperations.class.getDeclaredMethod(operations.get(operation), Stack.class);
                m1.invoke(this, stackNumbers);
            } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException ex1) {
                Logger.getLogger(MathematicalOperations.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (InvocationTargetException ex2) {
                if(ex2.getCause() instanceof StackBadSizeException)
                    throw (StackBadSizeException)ex2.getCause();
                if(ex2.getCause() instanceof MathematicalException)
                    throw (MathematicalException)ex2.getCause();
            }
            
            return true;
        }
        return false;
    }
    
    private void sum(Stack stackNumbers) throws StackBadSizeException{       
        ComplexNumber temp;
        if(stackNumbers.size()<2) throw new StackBadSizeException("Can't do sum operation with only one argument");
        temp = ComplexNumber.sum((ComplexNumber) stackNumbers.pop(), (ComplexNumber) stackNumbers.pop());
        stackNumbers.push(temp);
        
    }
    
    private void substraction(Stack stackNumbers)throws StackBadSizeException{
        ComplexNumber temp;
        if(stackNumbers.size()<2) throw new StackBadSizeException("Can't do substraction operation with only one argument");
        ComplexNumber operator2 = (ComplexNumber) stackNumbers.pop();
        ComplexNumber operator1 = (ComplexNumber) stackNumbers.pop();
        temp = ComplexNumber.substraction(operator1,operator2);
        stackNumbers.push(temp);
    }
    
    private void multiplication(Stack stackNumbers)throws StackBadSizeException{
        ComplexNumber temp;
        if(stackNumbers.size()<2) throw new StackBadSizeException("Can't do multiplication operation with only one argument");
        temp = ComplexNumber.multiplication((ComplexNumber) stackNumbers.pop(), (ComplexNumber) stackNumbers.pop());
        stackNumbers.push(temp);
    }
    
    private void division(Stack stackNumbers)throws StackBadSizeException, MathematicalException{
        ComplexNumber temp;
        if(stackNumbers.size()<2) throw new StackBadSizeException("Can't do division operation with only one argument");
        temp = ComplexNumber.division((ComplexNumber) stackNumbers.elementAt(stackNumbers.size()-2), (ComplexNumber) stackNumbers.elementAt(stackNumbers.size()-1));
        stackNumbers.pop();
        stackNumbers.pop();
        stackNumbers.push(temp);
    }
    
    private void squareRoot(Stack stackNumbers)throws StackBadSizeException{
        ComplexNumber temp;
        if(stackNumbers.size()<1) throw new StackBadSizeException("Can't do division operation with no argument");
        temp = ComplexNumber.squareRoot((ComplexNumber) stackNumbers.pop());
        stackNumbers.push(temp);
    }
    
    private void invertSign(Stack stackNumbers)throws StackBadSizeException{
        ComplexNumber temp;
        if(stackNumbers.size()<1) throw new StackBadSizeException("Can't do division operation with no argument");
        temp = ComplexNumber.invertSign((ComplexNumber) stackNumbers.pop());
        stackNumbers.push(temp);
    }

    @Override
    public boolean containsOperation(String operation) {
        return operations.containsKey(operation);
    }
}



