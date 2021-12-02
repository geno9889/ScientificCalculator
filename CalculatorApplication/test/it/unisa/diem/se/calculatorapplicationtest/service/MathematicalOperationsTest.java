/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.se.calculatorapplicationtest.service;

import it.unisa.diem.se.calculatorapplication.entity.ComplexNumber;
import it.unisa.diem.se.calculatorapplication.entity.MathematicalException;
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
    
    @Test
    public void testExecuteifExistsEmpty() throws StackBadSizeException, MathematicalException {
        Boolean r = a.executeifExists("nogood",stack);
        assertFalse("Empty stack check failed",r);
    }
    
    @Test
    public void testExecuteifExistsFalse() throws StackBadSizeException, MathematicalException {
        stack.push(new ComplexNumber(1));
        stack.push(new ComplexNumber(2));
        Boolean r = a.executeifExists("nogood",stack);
        assertFalse("Wrong operation check failed",r);
    }
    
    @Test
    public void testExecuteifExistsSum() throws StackBadSizeException, MathematicalException {
        stack.push(new ComplexNumber(1));
        stack.push(new ComplexNumber(2));
        Boolean r = a.executeifExists("+",stack);
        assertTrue(r);
        assertEquals("Sum real part execution error",3,stack.peek().getReal(),0);
        assertEquals("Sum imaginary part execution error",0,stack.peek().getImaginary(),0);
        assertEquals("Stack resize error",1,stack.size());
    }
    
    @Test (expected = StackBadSizeException.class)
    public void testExecuteifExistsExceptionSum() throws StackBadSizeException, MathematicalException {
        stack.push(new ComplexNumber(2));
        Boolean r = a.executeifExists("+",stack);
    }    
    
    @Test
    public void testExecuteifExistsSubstraction() throws StackBadSizeException, MathematicalException {
        stack.push(new ComplexNumber(2));
        stack.push(new ComplexNumber(1));
        Boolean r = a.executeifExists("-",stack);
        assertTrue(r);
        assertEquals("Substraction real part execution error",1,stack.peek().getReal(),0);
        assertEquals("Substraction imaginary part execution error",0,stack.peek().getImaginary(),0);
        assertEquals("Stack resize error",1,stack.size());
    }
    
    @Test (expected = StackBadSizeException.class)
    public void testExecuteifExistsExceptionSubstraction() throws StackBadSizeException, MathematicalException {
        stack.push(new ComplexNumber(2));
        Boolean r = a.executeifExists("-",stack);
    }    
    
    @Test
    public void testExecuteifExistsMultiplication() throws StackBadSizeException, MathematicalException {
        stack.push(new ComplexNumber(1,2));
        stack.push(new ComplexNumber(2));
        Boolean r = a.executeifExists("*",stack);
        assertTrue(r);
        assertEquals("Multiplication real part execution error",2,stack.peek().getReal(),0);
        assertEquals("Multiplication imaginary part execution error",4,stack.peek().getImaginary(),0);
        assertEquals("Stack resize error",1,stack.size());
    }
    
    @Test (expected = StackBadSizeException.class)
    public void testExecuteifExistsExceptionMultiplication() throws StackBadSizeException, MathematicalException {
        stack.push(new ComplexNumber(2));
        Boolean r = a.executeifExists("*",stack);
    }    
    
    @Test
    public void testExecuteifExistsDivision() throws StackBadSizeException, MathematicalException {
        stack.push(new ComplexNumber(3,2));
        stack.push(new ComplexNumber(4,1));
        stack.push(new ComplexNumber(2,2));
        stack.push(new ComplexNumber(1,1));
        Boolean r = a.executeifExists("/",stack);
        assertTrue(r);
        assertEquals("Division real part execution error",2,stack.peek().getReal(),0);
        assertEquals("Division imaginary part execution error",0,stack.peek().getImaginary(),0);
        assertEquals("Stack resize error",3,stack.size(),0);
    }
    
    @Test (expected = StackBadSizeException.class)
    public void testExecuteifExistsExceptionDivision() throws StackBadSizeException, MathematicalException {
        stack.push(new ComplexNumber(2));
        Boolean r = a.executeifExists("/",stack);
    }   
    
    @Test (expected = MathematicalException.class)
    public void testExecuteifExistsMathematicalExceptionDivision() throws StackBadSizeException, MathematicalException {
        stack.push(new ComplexNumber(0,0));
        stack.push(new ComplexNumber(0,0));
        Boolean r = a.executeifExists("/",stack);
    } 
 
    @Test
    public void testExecuteifExistsSquareRoot() throws StackBadSizeException, MathematicalException {
        stack.push(new ComplexNumber(4,4));
        Boolean r = a.executeifExists("sqrt",stack);
        assertTrue(r);
        assertEquals("Invert Sign real part execution error",2.19736823,stack.peek().getReal(),0);
        assertEquals("Invert Sign imaginary part execution error",0.91017972,stack.peek().getImaginary(),0);
        assertEquals("Stack resize error",1,stack.size());
    }
    
    @Test (expected = StackBadSizeException.class)
    public void testExecuteifExistsExceptionSquareRoot() throws StackBadSizeException, MathematicalException {
        Boolean r = a.executeifExists("sqrt",stack);
    }    
  
    @Test
    public void testExecuteifExistsInvertSign() throws StackBadSizeException, MathematicalException {
        stack.push(new ComplexNumber(2,2));
        Boolean r = a.executeifExists("+/-",stack);
        assertTrue(r);
        assertEquals("Invert Sign real part execution error",-2,stack.peek().getReal(),0);
        assertEquals("Invert Sign imaginary part execution error",-2,stack.peek().getImaginary(),0);
        assertEquals("Stack resize error",1,stack.size());
    }
    
    @Test (expected = StackBadSizeException.class)
    public void testExecuteifExistsExceptionInvertSign() throws StackBadSizeException, MathematicalException {
        Boolean r = a.executeifExists("+/-",stack);
    }    
    
}