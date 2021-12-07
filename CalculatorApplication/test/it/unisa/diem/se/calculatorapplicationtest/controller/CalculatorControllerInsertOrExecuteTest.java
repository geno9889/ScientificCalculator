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
public class CalculatorControllerInsertOrExecuteTest {
    
    private static CalculatorController controller;
    
    public CalculatorControllerInsertOrExecuteTest() {
    }
    
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
    
    @Test
    public void testIntPositiveRealPositiveImaginary() throws Exception{
        controller.insertOrExecute("43+120j");
        Stack<ComplexNumber> stackNumbers = controller.getStackNumbers();
        assertNotNull("stack is null", stackNumbers);
        assertFalse("stack is empty", stackNumbers.isEmpty());
        ComplexNumber number = stackNumbers.get(0);
        assertNotNull("number is null", number);
        assertEquals("Real part not expected", 43, number.getReal(), 0);
        assertEquals("Imaginary part not expected", 120, number.getImaginary(), 0);
    }
    
    @Test
    public void testSignIntPositiveRealPositiveImaginary() throws Exception{
        controller.insertOrExecute("+43+120j");
        Stack<ComplexNumber> stackNumbers = controller.getStackNumbers();
        assertNotNull("stack is null", stackNumbers);
        assertFalse("stack is empty", stackNumbers.isEmpty());
        ComplexNumber number = stackNumbers.get(0);
        assertNotNull("number is null", number);
        assertEquals("Real part not expected", 43, number.getReal(), 0);
        assertEquals("Imaginary part not expected", 120, number.getImaginary(), 0);
    }
    
    @Test
    public void testSignIntNegativeRealPositiveImaginary() throws Exception{
        controller.insertOrExecute("-43+120j");
        Stack<ComplexNumber> stackNumbers = controller.getStackNumbers();
        assertNotNull("stack is null", stackNumbers);
        assertFalse("stack is empty", stackNumbers.isEmpty());
        ComplexNumber number = stackNumbers.get(0);
        assertNotNull("number is null", number);
        assertEquals("Real part not expected", -43, number.getReal(), 0);
        assertEquals("Imaginary part not expected", 120, number.getImaginary(), 0);
    }
    
    @Test
    public void testIntPositiveRealNegativeImaginary() throws Exception{
        controller.insertOrExecute("43-120j");
        Stack<ComplexNumber> stackNumbers = controller.getStackNumbers();
        assertNotNull("stack is null", stackNumbers);
        assertFalse("stack is empty", stackNumbers.isEmpty());
        ComplexNumber number = stackNumbers.get(0);
        assertNotNull("number is null", number);
        assertEquals("Real part not expected", 43, number.getReal(), 0);
        assertEquals("Imaginary part not expected", -120, number.getImaginary(), 0);
    }
    
    @Test
    public void testIntPositiveRealOnlyJImaginary() throws Exception{
        controller.insertOrExecute("43-j");
        Stack<ComplexNumber> stackNumbers = controller.getStackNumbers();
        assertNotNull("stack is null", stackNumbers);
        assertFalse("stack is empty", stackNumbers.isEmpty());
        ComplexNumber number = stackNumbers.get(0);
        assertNotNull("number is null", number);
        assertEquals("Real part not expected", 43, number.getReal(), 0);
        assertEquals("Imaginary part not expected", -1, number.getImaginary(), 0);
    }
    
    @Test
    public void testWhiteSpacesValidInput() throws Exception{
        controller.insertOrExecute("  43  + 120  j");
        Stack<ComplexNumber> stackNumbers = controller.getStackNumbers();
        assertNotNull("stack is null", stackNumbers);
        assertFalse("stack is empty", stackNumbers.isEmpty());
        ComplexNumber number = stackNumbers.get(0);
        assertNotNull("number is null", number);
        assertEquals("Real part not expected", 43, number.getReal(), 0);
        assertEquals("Imaginary part not expected", 120, number.getImaginary(), 0);
    }
    
    @Test
    public void testPositiveRealNullImaginary() throws Exception{
        controller.insertOrExecute("4396");
        Stack<ComplexNumber> stackNumbers = controller.getStackNumbers();
        assertNotNull("stack is null", stackNumbers);
        assertFalse("stack is empty", stackNumbers.isEmpty());
        ComplexNumber number = stackNumbers.get(0);
        assertNotNull("number is null", number);
        assertEquals("Real part not expected", 4396, number.getReal(), 0);
        assertEquals("Imaginary part not expected", 0.0, number.getImaginary(), 0);
    }
    
    @Test
    public void testSignPositiveRealNullImaginary() throws Exception{
        controller.insertOrExecute("+4396");
        Stack<ComplexNumber> stackNumbers = controller.getStackNumbers();
        assertNotNull("stack is null", stackNumbers);
        assertFalse("stack is empty", stackNumbers.isEmpty());
        ComplexNumber number = stackNumbers.get(0);
        assertNotNull("number is null", number);
        assertEquals("Real part not expected", 4396, number.getReal(), 0);
        assertEquals("Imaginary part not expected", 0.0, number.getImaginary(), 0);
    }
    
    @Test
    public void testNegativeRealNullImaginary() throws Exception{
        controller.insertOrExecute("-4396");
        Stack<ComplexNumber> stackNumbers = controller.getStackNumbers();
        assertNotNull("stack is null", stackNumbers);
        assertFalse("stack is empty", stackNumbers.isEmpty());
        ComplexNumber number = stackNumbers.get(0);
        assertNotNull("number is null", number);
        assertEquals("Real part not expected", -4396, number.getReal(), 0);
        assertEquals("Imaginary part not expected", 0.0, number.getImaginary(), 0);
    }
    
    @Test
    public void testFloatRealImaginary() throws Exception{
        controller.insertOrExecute("72.5+120.189j");
        Stack<ComplexNumber> stackNumbers = controller.getStackNumbers();
        assertNotNull("stack is null", stackNumbers);
        assertFalse("stack is empty", stackNumbers.isEmpty());
        ComplexNumber number = stackNumbers.get(0);
        assertNotNull("number is null", number);
        assertEquals("Real part not expected", 72.5, number.getReal(), 0);
        assertEquals("Imaginary part not expected", 120.189, number.getImaginary(), 0);
    }
    
    @Test
    public void testFloatRealNullImmaginary() throws Exception{
        controller.insertOrExecute("72.5");
        Stack<ComplexNumber> stackNumbers = controller.getStackNumbers();
        assertNotNull("stack is null", stackNumbers);
        assertFalse("stack is empty", stackNumbers.isEmpty());
        ComplexNumber number = stackNumbers.get(0);
        assertNotNull("number is null", number);
        assertEquals("Real part not expected", 72.5, number.getReal(), 0);
        assertEquals("Imaginary part not expected", 0.0, number.getImaginary(), 0);
    }
    
    @Test(expected = InvalidInputException.class)
    public void testInvalidInput() throws Exception{
        controller.insertOrExecute("-aoww+ewomsj");
    }
    
    @Test(expected = InvalidInputException.class)
    public void testInputWithoutJ() throws Exception{
        controller.insertOrExecute("43+120");
    }
    
    @Test(expected = StackBadSizeException.class)
    public void testMathematicalOperationsInValidSum() throws Exception{
        controller.insertOrExecute("+");
    }
    
    @Test
    public void testMathematicalOperationsValidSum() throws Exception{
        Stack stackNumbers = controller.getStackNumbers();
        stackNumbers.push(new ComplexNumber(0, 0));
        stackNumbers.push(new ComplexNumber(0, 0));
        controller.insertOrExecute("+");
        assertEquals("MathematicalOperation not executed", 1, stackNumbers.size());
    }
    
    @Test(expected = StackBadSizeException.class)
    public void testStackOperationsInvalidDup() throws Exception{
        controller.insertOrExecute("dup");
    }
    
    @Test
    public void testStackOperationsValidDup() throws Exception{
        Stack stackNumbers = controller.getStackNumbers();
        stackNumbers.push(new ComplexNumber(0, 0));
        stackNumbers.push(new ComplexNumber(0, 0));
        controller.insertOrExecute("dup");
        assertEquals("StackOperation not executed", 3, stackNumbers.size());
    }
    
    @Test(expected = StackBadSizeException.class)
    public void testCustomOperationsInvalidSum() throws Exception{
        CustomOperations customOperations = (CustomOperations) controller.getMultipleOperations().get(0);
        customOperations.getCustomOperations().put("Op1", ">b +".split("\\s+"));
        Stack stack = controller.getStackNumbers();
        stack.add(new ComplexNumber(-12, 89));
        controller.insertOrExecute("Op1");
    }
    
    @Test
    public void testCustomOperationValidSum() throws Exception{
        Stack stack = controller.getStackNumbers();
        stack.add(new ComplexNumber(-12, 89));
        stack.add(new ComplexNumber(3.5, -2));
        stack.add(new ComplexNumber(3.5, -2));
        CustomOperations customOperations = (CustomOperations) controller.getMultipleOperations().get(0);
        customOperations.getCustomOperations().put("Op1", ">b +".split("\\s+"));
        controller.insertOrExecute("Op1");
        ComplexNumber number = controller.getStackNumbers().peek();
        assertEquals("Real part not expected", -8.5, number.getReal(), 0);
        assertEquals("ImaginaryPart part not expected", 87, number.getImaginary(), 0);
    }
    
    public void testVariableOperationsInvalidMajorX() throws Exception{
        controller.insertOrExecute(">x");
    }
    
    @Test
    public void testVariableOperationsValidMajorX() throws Exception{
        Stack stackNumbers = controller.getStackNumbers();
        stackNumbers.push(new ComplexNumber(0, 0));
        stackNumbers.push(new ComplexNumber(0, 0));
        controller.insertOrExecute(">x");
        assertEquals("VariableOperation not executed", 1, stackNumbers.size());
    }
}
