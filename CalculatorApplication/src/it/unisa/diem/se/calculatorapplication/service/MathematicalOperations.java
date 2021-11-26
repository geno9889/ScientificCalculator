/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package it.unisa.diem.se.calculatorapplication.service;

import java.util.List;
import it.unisa.diem.se.calculatorapplication.entity.ComplexNumber;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Giuseppe
 */
public class MathematicalOperations implements SingleOperationsInterface{
    
    private ArrayList<String> operations = new ArrayList();

    public MathematicalOperations() {
        this.operations.add("+");
        this.operations.add("-");
        this.operations.add("*");
        this.operations.add("/");
    }

    public ArrayList<String> getOperations() {
        return operations;
    }

    @Override
    public boolean executeifExists(String operation, Stack stackNumbers) {
        if (operation.length()==1){
            if (operation.contentEquals(operations.get(0))){
               // ComplexNumbers.sum(stackNumbers.elementAt(1),stackNumbers.elementAt(0));
                return true;
            }
            else if(operation.contentEquals(operations.get(1))){
               // ComplexNumbers.substraction(stackNumbers.elementAt(1),stackNumbers.elementAt(0));
                return true;
            }
            else if(operation.contentEquals(operations.get(2))){
               // ComplexNumbers.multiplication(stackNumbers.elementAt(1),stackNumbers.elementAt(0));
                return true;
            }
            
        }
        return false;
    }
    
    
}



