/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.se.calculatorapplicationtest.service;

import it.unisa.diem.se.calculatorapplication.entity.ComplexNumber;
import it.unisa.diem.se.calculatorapplication.service.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Stack;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author marcograziano
 */

public class MathematicalOperationsTest {
    private static MathematicalOperations a;
    private static Stack <ComplexNumber> stack;
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @BeforeClass
    public static void setUp() {
        a = new MathematicalOperations();
        assertNotNull("MathematicalOperation initialization error",a);
        stack = new Stack(); 
        assertNotNull("Stack initialization error",stack);
    }
    
    
    @After
    public void clearStack() {
        stack.clear();
        assertEquals("Stack clear error",stack.size(),0);
    }
    
    @AfterClass
    public static void tearDown() {
        a = null;
        stack = null;
        assertNull("MathematicalOperation deallocation error",a);
        assertNull("Stack deallocation error",stack);
        
    }
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void testExecuteifExistsEmpty() throws StackBadSizeException {
        Boolean r = a.executeifExists("nogood",stack);
        assertFalse("Empty stack check failed",r);
    }
    
    @Test
    public void testExecuteifExistsFalse() throws StackBadSizeException {
        stack.push(new ComplexNumber(1));
        stack.push(new ComplexNumber(2));
        Boolean r = a.executeifExists("nogood",stack);
        assertFalse("Wrong operation check failed",r);
    }
    
    @Test
    public void testExecuteifExistsSum() throws StackBadSizeException {
        stack.push(new ComplexNumber(1));
        stack.push(new ComplexNumber(2));
        Boolean r = a.executeifExists("+",stack);
        assertTrue(r);
        assertEquals("Sum execution error",new ComplexNumber(3),stack.peek());
        assertEquals("Stack resize error",1,stack.size());
    }
    
    @Test (expected = StackBadSizeException.class)
    public void testExecuteifExistsExceptionSum() throws StackBadSizeException {
        stack.push(new ComplexNumber(2));
        Boolean r = a.executeifExists("+",stack);
    }    
    
    @Test
    public void testExecuteifExistsSubstraction() throws StackBadSizeException {
        stack.push(new ComplexNumber(1));
        stack.push(new ComplexNumber(2));
        Boolean r = a.executeifExists("-",stack);
        assertTrue(r);
        assertEquals("Substraction execution error",new ComplexNumber(1),stack.peek());
        assertEquals("Stack resize error",1,stack.size());
    }
    
    @Test (expected = StackBadSizeException.class)
    public void testExecuteifExistsExceptionSubstraction() throws StackBadSizeException {
        stack.push(new ComplexNumber(2));
        Boolean r = a.executeifExists("-",stack);
    }    
    
    @Test
    public void testExecuteifExistsMultiplication() throws StackBadSizeException {
        stack.push(new ComplexNumber(1,2));
        stack.push(new ComplexNumber(2));
        Boolean r = a.executeifExists("*",stack);
        assertTrue(r);
        assertEquals("Multiplication execution error",new ComplexNumber(2,4),stack.peek());
        assertEquals("Stack resize error",1,stack.size());
    }
    
    @Test (expected = StackBadSizeException.class)
    public void testExecuteifExistsExceptionMultiplication() throws StackBadSizeException {
        stack.push(new ComplexNumber(2));
        Boolean r = a.executeifExists("*",stack);
    }    
    
}