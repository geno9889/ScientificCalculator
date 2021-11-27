/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.se.calculatorapplicationtest.controller;

import it.unisa.diem.se.calculatorapplication.controller.CalculatorController;
import it.unisa.diem.se.calculatorapplication.controller.InvalidInputException;
import it.unisa.diem.se.calculatorapplication.entity.ComplexNumber;
import it.unisa.diem.se.calculatorapplication.service.MathematicalOperations;
import it.unisa.diem.se.calculatorapplication.service.SingleOperationsInterface;
import it.unisa.diem.se.calculatorapplication.service.StackBadSizeException;
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
        assertNotNull("singleOperations is null" , singleOperations);
        assertEquals("singleOperations doesn't have one argument", 1, singleOperations.size());
        assertEquals("first item of singleOperations is not a MathematicalOperations", MathematicalOperations.class, singleOperations.get(0).getClass());
        assertNotNull("stackNumbers is null", controller.getStackNumbers());
    }
    
    @After
    public void clearStack(){
        controller.getStackNumbers().clear();
        assertEquals(0, controller.getStackNumbers().size());
    }
    
    @Test
    public void testIntPositiveRealPositiveImaginary() throws InvalidInputException, StackBadSizeException{
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
    public void testSignIntPositiveRealPositiveImaginary() throws InvalidInputException, StackBadSizeException{
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
    public void testSignIntNegativeRealPositiveImaginary() throws InvalidInputException, StackBadSizeException{
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
    public void testIntPositiveRealNegativeImaginary() throws InvalidInputException, StackBadSizeException{
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
    public void testWhiteSpacesValidInput() throws InvalidInputException, StackBadSizeException{
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
    public void testPositiveRealNullImaginary() throws InvalidInputException, StackBadSizeException{
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
    public void testSignPositiveRealNullImaginary() throws InvalidInputException, StackBadSizeException{
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
    public void testNegativeRealNullImaginary() throws InvalidInputException, StackBadSizeException{
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
    public void testFloatRealImaginary() throws InvalidInputException, StackBadSizeException{
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
    public void testFloatRealNullImmaginary() throws InvalidInputException, StackBadSizeException{
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
    public void testInvalidInput() throws InvalidInputException, StackBadSizeException{
        controller.insertOrExecute("-aoww+ewomsj");
        Stack<ComplexNumber> stackNumbers = controller.getStackNumbers();
        assertNotNull("stack is null", stackNumbers);
        assertEquals("stack is not empty", 0, stackNumbers.size());
    }
    
    @Test(expected = InvalidInputException.class)
    public void testInputWithoutJ() throws InvalidInputException, StackBadSizeException{
        controller.insertOrExecute("43+120");
        Stack<ComplexNumber> stackNumbers = controller.getStackNumbers();
        assertNotNull("stack is null", stackNumbers);
        assertEquals("stack is not empty", 0, stackNumbers.size());
    }
    
    @Test(expected = InvalidInputException.class)
    public void testNullRealSignNegativeImaginary() throws InvalidInputException, StackBadSizeException{
        controller.insertOrExecute("-120j");
        Stack<ComplexNumber> stackNumbers = controller.getStackNumbers();
        assertNotNull("stack is null", stackNumbers);
        assertEquals("stack is not empty", 0, stackNumbers.size());
    }
    
    @Test(expected = InvalidInputException.class)
    public void testNullRealSignPositiveImaginary() throws InvalidInputException, StackBadSizeException{
        controller.insertOrExecute("+120j");
        Stack<ComplexNumber> stackNumbers = controller.getStackNumbers();
        assertNotNull("stack is null", stackNumbers);
        assertEquals("stack is not empty", 0, stackNumbers.size());
    }
    
    @Test(expected = InvalidInputException.class)
    public void testNullRealPositiveImaginary() throws InvalidInputException, StackBadSizeException{
        controller.insertOrExecute("120j");
        Stack<ComplexNumber> stackNumbers = controller.getStackNumbers();
        assertNotNull("stack is null", stackNumbers);
        assertEquals("stack is not empty", 0, stackNumbers.size());
    }
}
