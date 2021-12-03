/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.se.calculatorapplicationtest.service;

import it.unisa.diem.se.calculatorapplication.entity.ComplexNumber;
import it.unisa.diem.se.calculatorapplication.service.StackBadSizeException;
import it.unisa.diem.se.calculatorapplication.service.VariablesOperations;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
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
@Getter
@EqualsAndHashCode
@ToString
public class VariablesOperationsTest {
    
    private static VariablesOperations operations;
    
    private static Stack<ComplexNumber> stackNumbers;
    
    @BeforeClass
    public static void setUpClass() {
        operations = new VariablesOperations();
        assertNotNull(operations);
        HashMap<String, String> op = operations.getOperations();
        assertNotNull(op);
        assertTrue(op.containsKey(">"));
        HashMap<Character, ComplexNumber> variables = operations.getVariables();
        assertNotNull(variables);
        Set<Character> alphabet = new HashSet<>();
        alphabet.add('a');
        alphabet.add('b');
        alphabet.add('c');
        alphabet.add('d');
        alphabet.add('e');
        alphabet.add('f');
        alphabet.add('g');
        alphabet.add('h');
        alphabet.add('i');
        alphabet.add('j');
        alphabet.add('k');
        alphabet.add('l');
        alphabet.add('m');
        alphabet.add('n');
        alphabet.add('o');
        alphabet.add('p');
        alphabet.add('q');
        alphabet.add('r');
        alphabet.add('s');
        alphabet.add('t');
        alphabet.add('u');
        alphabet.add('v');
        alphabet.add('w');
        alphabet.add('x');
        alphabet.add('y');
        alphabet.add('z');
        assertEquals(alphabet, variables.keySet());
        stackNumbers = new Stack<>();
        assertNotNull(stackNumbers);
    }
    
    @After
    public void tearDown() {
        stackNumbers.clear();
        assertEquals("Stack clear error", stackNumbers.size(), 0);
    }
    
    @Test
    public void testExecuteifExistsInvalidOperation() throws StackBadSizeException{
        Boolean r = operations.executeifExists("nogood", stackNumbers);
        assertFalse("Operation not existing",r);
    }
    
    @Test(expected = StackBadSizeException.class)
    public void testExecuteifExistsEmptyStackMajorX() throws StackBadSizeException{
        Boolean r = operations.executeifExists(">a", stackNumbers);
    }
    
    @Test()
    public void testExecuteIfExistsMajorX() throws StackBadSizeException{
        stackNumbers.push(new ComplexNumber(0,0));
        stackNumbers.push(new ComplexNumber(1,1));
        Boolean r = operations.executeifExists(">c", stackNumbers);
        assertTrue("The operation not exists",r);
        assertEquals("MajorX not executed", operations.getVariables().get('c'), new ComplexNumber(1, 1));
        assertEquals("MajorX not executed", 1, stackNumbers.size());
        Boolean r2 = operations.executeifExists(">d", stackNumbers);
        assertTrue("The operation not exists",r2);
        assertEquals("MajorX not executed", operations.getVariables().get('d'), new ComplexNumber(0, 0));
        assertEquals("MajorX not executed", 0, stackNumbers.size());
    }
}
