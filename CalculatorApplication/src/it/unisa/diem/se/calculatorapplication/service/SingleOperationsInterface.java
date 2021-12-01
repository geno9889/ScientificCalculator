/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unisa.diem.se.calculatorapplication.service;

import it.unisa.diem.se.calculatorapplication.entity.MathematicalException;
import java.util.Stack;

/**
 *
 * @author Giuseppe
 */
public interface SingleOperationsInterface {
    
    public boolean executeifExists(String operation, Stack stackNumbers) throws StackBadSizeException, MathematicalException;
           
}
