/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.se.calculatorapplicationtest.service;

import it.unisa.diem.se.calculatorapplication.entity.ComplexNumber;
import it.unisa.diem.se.calculatorapplication.service.CustomOperations;
import it.unisa.diem.se.calculatorapplication.service.MathematicalOperations;
import it.unisa.diem.se.calculatorapplication.service.SingleOperationsInterface;
import it.unisa.diem.se.calculatorapplication.service.StackOperations;
import it.unisa.diem.se.calculatorapplication.service.VariablesOperations;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import org.apache.hadoop.mapreduce.lib.input.InvalidInputException;
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
public class CustomOperationTest {
    
    private static CustomOperations customOperations;
    private static Stack <ComplexNumber> stack;
    
    @BeforeClass
    public static void setUpClass() {
        List<SingleOperationsInterface> singleOperations = new ArrayList<>();
        singleOperations.add(new MathematicalOperations());
        singleOperations.add(new StackOperations());
        singleOperations.add(new VariablesOperations());
        customOperations = new CustomOperations(singleOperations);
        assertNotNull("CustomOperations initialization error", customOperations);
        assertEquals("CustomOperations initialization error", customOperations.getSingleOperations().size(), 3);
        stack = new Stack(); 
        assertNotNull("Stack initialization error",stack);
    }
    
    @After
    public void tearDown() {
        stack.clear();
        assertEquals("Stack clear error",stack.size(),0);
    }
    
    @Test
    public void testAddOperationValidInput(){
        Boolean result = customOperations.addOperation("+ clear dup sqrt >e", "ValidTest");
        assertTrue("Addition of the operation not successful", result);
        HashMap<String,String> mapOperations = customOperations.getCustomOperations();
        assertTrue("Name of the operation doesn't exists", mapOperations.containsKey("ValidTest"));
        assertEquals("Sequence of operations doesn't correspond to name", "+ clear dup sqrt >e", mapOperations.get("ValidTest"));
    }
    
    @Test
    public void testAddOperationInvalidInputName(){
        Boolean result1 = customOperations.addOperation("+ clear dup sqrt >e", "ValidTest");
        assertTrue("Addition of the operation not successful", result1);
        Boolean result2 = customOperations.addOperation("+ clear dup sqrt >e", "ValidTest");
        assertFalse("The name of the operation doesn't exists", result2);
    }
    
    @Test
    public void testAddOperationInvalidInputOperation(){
        Boolean result = customOperations.addOperation("+ clear dop sqrt >e", "ValidTest");
        assertFalse("Sequence of the operation is valid", result);
    }
}
