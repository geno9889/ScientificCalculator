/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.se.calculatorapplication.service;

import it.unisa.diem.se.calculatorapplication.entity.MathematicalException;
import java.util.Stack;

/**
 *
 * @author Giuseppe
 */
public interface MultipleOperationsInterface {
    
    public boolean addOperation(String name, String operations);
    public boolean modifyOperation(String newName, String oldName, String newOperation);
    public boolean deleteOperation(String name);
    public boolean executeIfExists(String operationName, Stack stackNumbers) throws StackBadSizeException, MathematicalException, NullVariableException;
}
