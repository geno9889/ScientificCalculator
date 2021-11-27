/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.se.calculatorapplicationtest.service;

import it.unisa.diem.se.calculatorapplication.entity.ComplexNumber;
import it.unisa.diem.se.calculatorapplication.service.*;
import java.util.Stack;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marcograziano
 */

public class MathematicalOperationsTest {
    private MathematicalOperations a;
    private Stack <ComplexNumber> stack;
    private String op0;
    private String op1;
    
    @Before
    public void setUp() {
        a = new MathematicalOperations();
        assertNotNull(a);
        stack = new Stack();
        stack.push(new ComplexNumber(1));
        assertNotNull(stack);
        op0 = "nogood";
        assertNotNull(op0);
        op1 = "+";
        assertNotNull(op1);
    }
    
    @After
    public void tearDown() {
        a = null;
        stack = null;
        op0 = null;
        op1 = null;
        assertNull(a);
        assertNull(stack);
        assertNull(op0);
        assertNull(op1);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void testExecuteifExistsEmpty() {
        stack.pop();
        Boolean r = a.executeifExists(op0,stack);
        assertFalse(r);
    }
    
    @Test
    public void testExecuteifExistsFalse() {
        stack.push(new ComplexNumber(2));
        Boolean r = a.executeifExists(op0,stack);
        assertFalse(r);
    }
    
    @Test
    public void testExecuteifExistsSum() {
        stack.push(new ComplexNumber(2));
        Boolean r = a.executeifExists(op1,stack);
        assertTrue(r);
        assertEquals(new ComplexNumber(3),stack.peek());
    }
    
}