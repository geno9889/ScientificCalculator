/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.se.calculatorapplicationtest.impl;

import it.unisa.diem.se.calculatorapplication.impl.*;
import java.util.ArrayList;
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
    private Stack stack;
    private String op0;
    private String op1;
    
    @Before
    public void setUp() {
        a = new MathematicalOperations();
        assertNotNull(a);
        stack = new Stack();
        stack.add(1);
        stack.add(2);
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
    public void TestGetOperations() {
        ArrayList<String> s = a.getOperations();
        assertNotNull(s);
    }
    
    @Test
    public void TestExecuteifExistsFalse() {
        Boolean r = a.executeifExists(op0,stack);
        assertFalse(r);
    }
    
    @Test
    public void TestExecuteifExistsSum() {
        Boolean r = a.executeifExists(op1,stack);
        assertTrue(r);
    }
    
}
