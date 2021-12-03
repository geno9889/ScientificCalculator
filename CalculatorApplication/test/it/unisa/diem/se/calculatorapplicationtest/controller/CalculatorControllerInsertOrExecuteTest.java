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
import it.unisa.diem.se.calculatorapplication.service.MathematicalOperations;
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
        assertNotNull("singleOperations is null" , singleOperations);
        assertEquals("singleOperations doesn't have three argument", 3, singleOperations.size());
        assertEquals("first item of singleOperations is not a MathematicalOperations", MathematicalOperations.class, singleOperations.get(0).getClass());
        assertEquals("first item of singleOperations is not a StackOperations", StackOperations.class, singleOperations.get(1).getClass());
        assertEquals("first item of singleOperations is not a VariablesOperations", VariablesOperations.class, singleOperations.get(2).getClass());
        assertNotNull("stackNumbers is null", controller.getStackNumbers());
        
    }
    
    @After
    public void clearStack(){
        controller.getStackNumbers().clear();
        assertEquals(0, controller.getStackNumbers().size());
    }
    
    @Test
    public void testIntPositiveRealPositiveImaginary() throws InvalidInputException, StackBadSizeException, MathematicalException{
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
    public void testSignIntPositiveRealPositiveImaginary() throws InvalidInputException, StackBadSizeException, MathematicalException{
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
    public void testSignIntNegativeRealPositiveImaginary() throws InvalidInputException, StackBadSizeException, MathematicalException{
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
    public void testIntPositiveRealNegativeImaginary() throws InvalidInputException, StackBadSizeException, MathematicalException{
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
    public void testIntPositiveRealOnlyJImaginary() throws InvalidInputException, StackBadSizeException, MathematicalException{
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
    public void testWhiteSpacesValidInput() throws InvalidInputException, StackBadSizeException, MathematicalException{
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
    public void testPositiveRealNullImaginary() throws InvalidInputException, StackBadSizeException, MathematicalException{
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
    public void testSignPositiveRealNullImaginary() throws InvalidInputException, StackBadSizeException, MathematicalException{
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
    public void testNegativeRealNullImaginary() throws InvalidInputException, StackBadSizeException, MathematicalException{
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
    public void testFloatRealImaginary() throws InvalidInputException, StackBadSizeException, MathematicalException{
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
    public void testFloatRealNullImmaginary() throws InvalidInputException, StackBadSizeException, MathematicalException{
        controller.insertOrExecute("72.5");
        Stack<ComplexNumber> stackNumbers = controller.getStackNumbers();
        assertNotNull("stack is null", stackNumbers);
        assertFalse("stack is empty", stackNumbers.isEmpty());
        ComplexNumber number = stackNumbers.get(0);
        assertNotNull("number is null", number);
        assertEquals("Real part not expected", 72.5, number.getReal(), 0);
        assertEquals("Imaginary part not expected", 0.0, number.getImaginary(), 0);
    }
    
    @Test
    public void testOnlyJ() throws InvalidInputException, StackBadSizeException, MathematicalException{
        controller.insertOrExecute("j");
        Stack<ComplexNumber> stackNumbers = controller.getStackNumbers();
        assertNotNull("stack is null", stackNumbers);
        assertFalse("stack is empty", stackNumbers.isEmpty());
        ComplexNumber number = stackNumbers.get(0);
        assertNotNull("number is null", number);
        assertEquals("Real part not expected", 0, number.getReal(), 0);
        assertEquals("Imaginary part not expected", 1, number.getImaginary(), 0);
    }
    
    @Test
    public void testSignPositiveOnlyJ() throws InvalidInputException, StackBadSizeException, MathematicalException{
        controller.insertOrExecute("+j");
        Stack<ComplexNumber> stackNumbers = controller.getStackNumbers();
        assertNotNull("stack is null", stackNumbers);
        assertFalse("stack is empty", stackNumbers.isEmpty());
        ComplexNumber number = stackNumbers.get(0);
        assertNotNull("number is null", number);
        assertEquals("Real part not expected", 0, number.getReal(), 0);
        assertEquals("Imaginary part not expected", 1, number.getImaginary(), 0);
    }
    
    @Test
    public void testSignNegativeOnlyJ() throws InvalidInputException, StackBadSizeException, MathematicalException{
        controller.insertOrExecute("-j");
        Stack<ComplexNumber> stackNumbers = controller.getStackNumbers();
        assertNotNull("stack is null", stackNumbers);
        assertFalse("stack is empty", stackNumbers.isEmpty());
        ComplexNumber number = stackNumbers.get(0);
        assertNotNull("number is null", number);
        assertEquals("Real part not expected", 0, number.getReal(), 0);
        assertEquals("Imaginary part not expected", -1, number.getImaginary(), 0);
    }
    
    @Test
    public void testOnlyJWithNumber() throws InvalidInputException, StackBadSizeException, MathematicalException{
        controller.insertOrExecute("1239j");
        Stack<ComplexNumber> stackNumbers = controller.getStackNumbers();
        assertNotNull("stack is null", stackNumbers);
        assertFalse("stack is empty", stackNumbers.isEmpty());
        ComplexNumber number = stackNumbers.get(0);
        assertNotNull("number is null", number);
        assertEquals("Real part not expected", 0, number.getReal(), 0);
        assertEquals("Imaginary part not expected", 1239, number.getImaginary(), 0);
    }
    
    @Test(expected = InvalidInputException.class)
    public void testInvalidInput() throws InvalidInputException, StackBadSizeException, MathematicalException{
        controller.insertOrExecute("-aoww+ewomsj");
    }
    
    @Test(expected = InvalidInputException.class)
    public void testInputWithoutJ() throws InvalidInputException, StackBadSizeException, MathematicalException{
        controller.insertOrExecute("43+120");
    }
    
    @Test(expected = StackBadSizeException.class)
    public void testMathematicalOperationsInValidSum() throws InvalidInputException, StackBadSizeException, MathematicalException{
        controller.insertOrExecute("+");
    }
    
    @Test
    public void testMathematicalOperationsValidSum() throws InvalidInputException, StackBadSizeException, MathematicalException{
        Stack stackNumbers = controller.getStackNumbers();
        stackNumbers.push(new ComplexNumber(0, 0));
        stackNumbers.push(new ComplexNumber(0, 0));
        controller.insertOrExecute("+");
        assertEquals("MathematicalOperation not executed", 1, stackNumbers.size());
    }
    
    @Test(expected = StackBadSizeException.class)
    public void testStackOperationsInvalidDup() throws InvalidInputException, StackBadSizeException, MathematicalException{
        controller.insertOrExecute("dup");
    }
    
    @Test
    public void testStackOperationsValidDup() throws InvalidInputException, StackBadSizeException, MathematicalException{
        Stack stackNumbers = controller.getStackNumbers();
        stackNumbers.push(new ComplexNumber(0, 0));
        stackNumbers.push(new ComplexNumber(0, 0));
        controller.insertOrExecute("dup");
        assertEquals("StackOperation not executed", 3, stackNumbers.size());
    }
}
