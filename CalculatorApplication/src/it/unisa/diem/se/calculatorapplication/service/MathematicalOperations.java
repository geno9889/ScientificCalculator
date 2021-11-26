/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package it.unisa.diem.se.calculatorapplication.service;

import java.util.List;
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
        if (operations.containsKey(operation)){
            ComplexNumber temp = new ComplexNumber(0);
            try {
                Method m = ComplexNumber.class.getClass().getMethod(operations.get(operation));
                m.invoke(temp, stackNumbers.elementAt(1), stackNumbers.elementAt(0));
                Method m1 = getClass().getMethod(operation);
                m1.invoke(temp, stackNumbers);
            } catch (NoSuchMethodException ex) {
                Logger.getLogger(MathematicalOperations.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(MathematicalOperations.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(MathematicalOperations.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(MathematicalOperations.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(MathematicalOperations.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }
        return false;
    }
    
    private void sum(ComplexNumber result, Stack stackNumbers){
        stackNumbers.pop();
        stackNumbers.pop();
        stackNumbers.push(result);
    }
}




