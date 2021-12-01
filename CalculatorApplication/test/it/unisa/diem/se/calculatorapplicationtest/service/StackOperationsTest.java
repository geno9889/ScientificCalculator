/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.se.calculatorapplicationtest.service;

import it.unisa.diem.se.calculatorapplication.entity.ComplexNumber;
import it.unisa.diem.se.calculatorapplication.service.MathematicalOperations;
import it.unisa.diem.se.calculatorapplication.service.StackBadSizeException;
import it.unisa.diem.se.calculatorapplication.service.StackOperations;
import java.util.Stack;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Giuseppe
 */
public class StackOperationsTest {
    
    private static StackOperations operations;
    
    private static Stack<ComplexNumber> stack;
    
    public StackOperationsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        operations = new StackOperations();
        assertNotNull("StackOperations initialization error", operations);
        stack = new Stack(); 
        assertNotNull("Stack initialization error",stack);
    }

    @After
    public void tearDown() {
        stack.clear();
        assertEquals("Stack clear error",stack.size(),0);
    }
    
    @Test
    public void testExecuteIfExistsClearEmptyStack(){
        Boolean r = operations.executeifExists("clear", stack);
        assertTrue(r);
        assertTrue(stack.empty());
    }

    @Test
    public void testExecuteIfExistsClearNotEmptyStack(){
        stack.push(new ComplexNumber(0,0));
        stack.push(new ComplexNumber(1,1));
        stack.push(new ComplexNumber(2,2));
        stack.push(new ComplexNumber(3,3));
        stack.push(new ComplexNumber(4,4));
        stack.push(new ComplexNumber(5,5));
        Boolean r = operations.executeifExists("clear", stack);
        assertTrue(r);
        assertTrue(stack.empty());
    }
}
