/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.se.calculatorapplicationtest.controller;

import it.unisa.diem.se.calculatorapplication.controller.CalculatorController;
import it.unisa.diem.se.calculatorapplication.controller.InvalidInputException;
import it.unisa.diem.se.calculatorapplication.entity.ComplexNumber;
import it.unisa.diem.se.calculatorapplication.entity.MathematicalException;
import it.unisa.diem.se.calculatorapplication.service.CustomOperations;
import it.unisa.diem.se.calculatorapplication.service.MathematicalOperations;
import it.unisa.diem.se.calculatorapplication.service.MultipleOperationsInterface;
import it.unisa.diem.se.calculatorapplication.service.NullVariableException;
import it.unisa.diem.se.calculatorapplication.service.SingleOperationsInterface;
import it.unisa.diem.se.calculatorapplication.service.StackBadSizeException;
import it.unisa.diem.se.calculatorapplication.service.StackOperations;
import it.unisa.diem.se.calculatorapplication.service.VariablesOperations;
import java.util.List;
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
public class CalculatorControllerExecuteTest {
    
    private static CalculatorController controller;
    
    @BeforeClass
    public static void setUpClass() {
        controller = new CalculatorController();
        assertNotNull("controller is null", controller);
        List<SingleOperationsInterface> singleOperations = controller.getSingleOperations();
        List<MultipleOperationsInterface> multipleOperations = controller.getMultipleOperations();
        assertNotNull("singleOperations is null" , singleOperations);
        assertEquals("singleOperations doesn't have three argument", 3, singleOperations.size());
        assertEquals("first item of singleOperations is not a MathematicalOperations", MathematicalOperations.class, singleOperations.get(0).getClass());
        assertEquals("first item of singleOperations is not a StackOperations", StackOperations.class, singleOperations.get(1).getClass());
        assertEquals("first item of singleOperations is not a VariablesOperations", VariablesOperations.class, singleOperations.get(2).getClass());
        assertEquals("first item of singleOperations is not a VariablesOperations", CustomOperations.class, multipleOperations.get(0).getClass());
        assertNotNull("stackNumbers is null", controller.getStackNumbers());
        
    }
    
    @After
    public void clearStack(){
        controller.getStackNumbers().clear();
        assertEquals(0, controller.getStackNumbers().size());
    }
    
    @Test(expected = StackBadSizeException.class)
    public void testMathematicalOperationsInValidSum() throws Exception{
        controller.executeSingleOperation("+");
    }
    
    @Test
    public void testMathematicalOperationsValidSum() throws Exception{
        Stack stackNumbers = controller.getStackNumbers();
        stackNumbers.push(new ComplexNumber(0, 0));
        stackNumbers.push(new ComplexNumber(0, 0));
        controller.executeSingleOperation("+");
        assertEquals("MathematicalOperation not executed", 1, stackNumbers.size());
    }
    
    @Test(expected = StackBadSizeException.class)
    public void testStackOperationsInvalidDup() throws Exception{
        controller.executeSingleOperation("dup");
    }
    
    @Test
    public void testStackOperationsValidDup() throws Exception{
        Stack stackNumbers = controller.getStackNumbers();
        stackNumbers.push(new ComplexNumber(0, 0));
        stackNumbers.push(new ComplexNumber(0, 0));
        controller.executeSingleOperation("dup");
        assertEquals("StackOperation not executed", 3, stackNumbers.size());
    }
    
    @Test(expected = StackBadSizeException.class)
    public void testVariableOperationsInvalidMajorX() throws Exception{
        controller.executeSingleOperation(">x");
    }
    
    @Test
    public void testVariableOperationsValidMajorX() throws Exception{
        Stack stackNumbers = controller.getStackNumbers();
        stackNumbers.push(new ComplexNumber(0, 0));
        stackNumbers.push(new ComplexNumber(0, 0));
        controller.executeSingleOperation(">x");
        assertEquals("VariableOperation not executed", 1, stackNumbers.size());
    }
}
