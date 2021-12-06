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
import java.util.HashMap;
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
        HashMap<String, String> op = operations.getOperations();
        assertTrue(op.containsKey("clear"));
        assertTrue(op.containsKey("drop"));
        assertTrue(op.containsKey("dup"));
        assertTrue(op.containsKey("swap"));
        stack = new Stack(); 
        assertNotNull("Stack initialization error",stack);
    }

    @After
    public void tearDown() {
        stack.clear();
        assertEquals("Stack clear error",stack.size(),0);
    }
    
    @Test
    public void testExecuteIfExistsClearEmptyStack() throws Exception{
        Boolean r = operations.executeifExists("clear", stack);
        assertTrue("The operation dup doesn't exists" ,r);
        assertTrue("The stack is not empty", stack.empty());
    }

    @Test
    public void testExecuteIfExistsClearNotEmptyStack() throws Exception{
        stack.push(new ComplexNumber(0,0));
        stack.push(new ComplexNumber(1,1));
        stack.push(new ComplexNumber(2,2));
        stack.push(new ComplexNumber(3,3));
        stack.push(new ComplexNumber(4,4));
        stack.push(new ComplexNumber(5,5));
        Boolean r = operations.executeifExists("clear", stack);
        assertTrue("The operation dup doesn't exists", r);
        assertTrue("The stack is not empty", stack.empty());
    }
    
    @Test (expected = StackBadSizeException.class)
    public void testExecuteIfExistsDupEmptyStack() throws Exception{
        operations.executeifExists("dup", stack);
    }
    
    @Test 
    public void testExecuteIfExistsDupNotEmptyStack() throws Exception{
        stack.push(new ComplexNumber(0,0));
        stack.push(new ComplexNumber(1,1));
        stack.push(new ComplexNumber(2,2));
        stack.push(new ComplexNumber(3,3));
        stack.push(new ComplexNumber(4,4));
        stack.push(new ComplexNumber(5,5));
        Boolean r = operations.executeifExists("dup", stack);
        assertTrue("The operation dup not exists",r);
        ComplexNumber number = stack.peek();
        assertEquals("Duplication part real of top element error",5,number.getReal(),0);
        assertEquals("Duplication part imaginary of top element error",5,number.getImaginary(),0);
        assertEquals("Stack resize error",7,stack.size(),0);
    }
    
    @Test(expected = StackBadSizeException.class)
    public void testExecuteIfExistsSwapEmptyStack() throws Exception{
       operations.executeifExists("swap", stack);
    }
    
    @Test(expected = StackBadSizeException.class)
    public void testExecuteIfExistsSwapOneElementInStack() throws Exception{
        stack.push(new ComplexNumber(0,0));
        operations.executeifExists("swap", stack);
    }
    
    @Test
    public void testExecuteIfExistsSwapValidStack() throws Exception{
        stack.push(new ComplexNumber(0,0));
        stack.push(new ComplexNumber(1,1));
        stack.push(new ComplexNumber(2,2));
        stack.push(new ComplexNumber(3,3));
        stack.push(new ComplexNumber(4,4));
        stack.push(new ComplexNumber(5,5));
        Boolean r = operations.executeifExists("swap", stack);
        assertTrue("The operation swap not exists",r);
        ComplexNumber number = stack.peek();
        assertEquals("Swap not executed", new ComplexNumber(4,4),number);
        number = stack.elementAt(stack.size() - 2);
        assertEquals("Swap not executed", new ComplexNumber(5,5),number);
        assertEquals("Stack resize error",6,stack.size(),0);
    }
    
    @Test (expected = StackBadSizeException.class)
    public void testExecuteIfExistsDropEmptyStack() throws StackBadSizeException{
        operations.executeifExists("drop", stack);
    }

    @Test
    public void testExecuteIfExistsDropNotEmptyStack() throws Exception{
        stack.push(new ComplexNumber(0,0));
        stack.push(new ComplexNumber(1,1));
        stack.push(new ComplexNumber(2,2));
        stack.push(new ComplexNumber(3,3));
        stack.push(new ComplexNumber(4,4));
        stack.push(new ComplexNumber(5,5));
        Boolean r = operations.executeifExists("drop", stack);
        assertTrue("The operation drop not exists",r);
        ComplexNumber number = stack.peek();
        assertEquals("Drop not executed", new ComplexNumber(4, 4), number);
        assertEquals("Stack resize error",5,stack.size(),0);
    }
    
        @Test(expected = StackBadSizeException.class)
    public void testExecuteIfExistsOverOneElementInStack() throws Exception{
        stack.push(new ComplexNumber(0,0));
        operations.executeifExists("over", stack);
    }
    
    @Test
    public void testExecuteIfExistsOverValidStack() throws Exception{
        stack.push(new ComplexNumber(0,0));
        stack.push(new ComplexNumber(1,1));
        stack.push(new ComplexNumber(2,2));
        stack.push(new ComplexNumber(3,3));
        stack.push(new ComplexNumber(4,4));
        stack.push(new ComplexNumber(5,5));
        Boolean r = operations.executeifExists("over", stack);
        assertTrue("The operation over not exists",r);
        ComplexNumber number = stack.peek();
        assertEquals("Over not executed", new ComplexNumber(4,4),number);
        assertEquals("Stack resize error",7,stack.size(),0);
    }
    
    @Test 
    public void testContainsOperationInvalidOperation() throws Exception{
        Boolean r = operations.containsOperation("dop");
        assertFalse("It returns true, but operations doesn't exists",r); 
    }
    
    @Test 
    public void testContainsOperationValidOperation() throws Exception{
        Boolean r = operations.containsOperation("dup");
        assertTrue("Operation doesn't exists",r);
    }
}
