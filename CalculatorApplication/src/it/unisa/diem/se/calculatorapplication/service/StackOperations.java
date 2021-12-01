/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.se.calculatorapplication.service;

import it.unisa.diem.se.calculatorapplication.entity.ComplexNumber;
import java.util.HashMap;
import java.util.Stack;

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
        return true;
    }

}
