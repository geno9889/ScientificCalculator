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
        this.operations = new HashMap();
        this.operations.put("+", "sum");
        this.operations.put("-", "substraction");
        this.operations.put("*", "multiplication");
        this.operations.put("/", "division");
    }

    @Override
    public boolean executeifExists(String operation, Stack stackNumbers) throws StackBadSizeException{
        Method m1;
        if (operations.containsKey(operation)){
            try {
                m1 = MathematicalOperations.class.getDeclaredMethod(operations.get(operation), Stack.class);
                m1.invoke(this, stackNumbers);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(MathematicalOperations.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(MathematicalOperations.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchMethodException ex) {
                Logger.getLogger(MathematicalOperations.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(MathematicalOperations.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                if(ex.getCause() instanceof StackBadSizeException)
                    throw (StackBadSizeException)ex.getCause();
                
            }
            
            return true;
        }
        return false;
    }
    
    private void sum(Stack stackNumbers) throws StackBadSizeException{       
        ComplexNumber temp;
        if(stackNumbers.size()<2) throw new StackBadSizeException("Stack can't do operation sum with only one argument");
        temp = ComplexNumber.sum((ComplexNumber) stackNumbers.pop(), (ComplexNumber) stackNumbers.pop());
        stackNumbers.push(temp);
        
    }
    
    private void substraction(Stack stackNumbers)throws StackBadSizeException{
        ComplexNumber temp;
        if(stackNumbers.size()<2) throw new StackBadSizeException("Stack can't do operation substraction with only one argument");
        temp = ComplexNumber.substraction((ComplexNumber) stackNumbers.pop(), (ComplexNumber) stackNumbers.pop());
        stackNumbers.push(temp);
    }
    
    private void multiplication(Stack stackNumbers)throws StackBadSizeException{
        ComplexNumber temp;
        if(stackNumbers.size()<2) throw new StackBadSizeException("Stack can't do operation multiplication with only one argument");
        temp = ComplexNumber.multiplication((ComplexNumber) stackNumbers.pop(), (ComplexNumber) stackNumbers.pop());
        stackNumbers.push(temp);
    }
    
    private void division(Stack stackNumbers)throws StackBadSizeException, MathematicalException{
        ComplexNumber temp;
        if(stackNumbers.size()<2) throw new StackBadSizeException("Stack can't do operation division with only one argument");
        ComplexNumber operand1 = (ComplexNumber) stackNumbers.pop();
        ComplexNumber operand2 = (ComplexNumber) stackNumbers.pop();
        temp = ComplexNumber.division(operand2, operand1);
        stackNumbers.push(temp);
    }
}



