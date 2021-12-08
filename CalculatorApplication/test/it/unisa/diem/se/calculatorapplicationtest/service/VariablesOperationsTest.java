/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.se.calculatorapplicationtest.service;

import it.unisa.diem.se.calculatorapplication.entity.ComplexNumber;
import it.unisa.diem.se.calculatorapplication.service.NullVariableException;
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
        assertTrue(op.containsKey("+"));
        assertTrue(op.containsKey("-"));
        assertTrue(op.containsKey("<"));
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
        assertNotNull(operations.getTemporanySave());
        stackNumbers = new Stack<>();
        assertNotNull(stackNumbers);
    }
    
    @After
    public void tearDown() {
        stackNumbers.clear();
        assertEquals("Stack clear error", stackNumbers.size(), 0);
        HashMap<Character, ComplexNumber> variables = operations.getVariables();
        Set<Character> keys = variables.keySet();
        for(Character c: keys){
            variables.put(c, null);
        }
        
    }
    
    @Test
    public void testExecuteifExistsInvalidOperation() throws Exception{
        Boolean r = operations.executeIfExists("nogood", stackNumbers);
        assertFalse("Operation not existing",r);
    }
    
    @Test(expected = StackBadSizeException.class)
    public void testExecuteifExistsEmptyStackMajorX() throws Exception{
        operations.executeIfExists(">a", stackNumbers);
    }
    
    @Test()
    public void testExecuteIfExistsMajorX() throws Exception{
        stackNumbers.push(new ComplexNumber(0,0));
        stackNumbers.push(new ComplexNumber(1,1));
        Boolean r = operations.executeIfExists(">c", stackNumbers);
        assertTrue("The operation not exists",r);
        assertEquals("MajorX not executed", operations.getVariables().get('c'), new ComplexNumber(1, 1));
        assertEquals("MajorX not executed", 1, stackNumbers.size());
        Boolean r2 = operations.executeIfExists(">d", stackNumbers);
        assertTrue("The operation not exists",r2);
        assertEquals("MajorX not executed", operations.getVariables().get('d'), new ComplexNumber(0, 0));
        assertEquals("MajorX not executed", 0, stackNumbers.size());
    }
    

    @Test
    public void testExecuteifExistsPlusX() throws Exception{
        stackNumbers.push(new ComplexNumber(4));
        stackNumbers.push(new ComplexNumber(2));
        operations.getVariables().put('x', new ComplexNumber(3));
        Boolean r = operations.executeIfExists("+x", stackNumbers);
        assertTrue("The operation not exists",r);
        assertEquals("PlusX not executed", operations.getVariables().get('x'), new ComplexNumber(5, 0));
        assertEquals("PlusX not executed", 1, stackNumbers.size());
        
    }
    
    @Test (expected = StackBadSizeException.class)
    public void testExecuteifExistsEmptyStackPlusX() throws Exception{
        operations.executeIfExists("+k", stackNumbers);
    }
    

    @Test(expected = StackBadSizeException.class)
    public void testExecuteifExistsEmptyStackMinusrX() throws Exception{
        operations.executeIfExists("-x", stackNumbers);
    }
    
    @Test
    public void testExecuteIfExistsMinusX() throws Exception{
        stackNumbers.push(new ComplexNumber(0,0));
        stackNumbers.push(new ComplexNumber(1,1));
        operations.getVariables().put('x', new ComplexNumber(3,3));
        Boolean r = operations.executeIfExists("-x", stackNumbers);
        assertTrue("The operation not exists",r);
        assertEquals("MinusX not executed", 1, stackNumbers.size());assertEquals("MajorX not executed", 1, stackNumbers.size());
        assertEquals("MinusX not executed", operations.getVariables().get('x'), new ComplexNumber(2, 2));

    }
    
    @Test (expected = NullVariableException.class)
    public void testExecuteifExistsMinorXNullVariable() throws Exception{
        operations.executeIfExists("<x", stackNumbers);
    }
    
    @Test
    public void testExecuteifExistsMinorX() throws Exception{
        operations.getVariables().put('x', new ComplexNumber(1,2));
        Boolean r = operations.executeIfExists("<x", stackNumbers);
        assertTrue("The operation not exists",r);
        assertEquals("MinorX not executed",stackNumbers.peek(), new ComplexNumber(1,2));
    }
    
    @Test 
    public void testContainsOperationInvalidOperation() throws Exception{
        Boolean r = operations.containsOperation("plas");
        assertFalse("It returns true, but operations doesn't exists",r); 
    }
    
    @Test 
    public void testContainsOperationValidOperation() throws Exception{
        Boolean r = operations.containsOperation("<a");
        assertTrue("Operation doesn't exists",r);
    }
    
    @Test
    public void testSave(){
        operations.save();
        assertEquals("The size of temporanySave stack is not 1", 1, operations.getTemporanySave().size());
        operations.save();
        assertEquals("The size of temporanySave stack is not 2", 2, operations.getTemporanySave().size());
    }
    
    @Test
    public void testRestore(){
        stackNumbers.push(new ComplexNumber(0,1));
        assertEquals("Restore not executed", operations.getTemporanySave().get('a'), new ComplexNumber(0, 1));
    }
}
