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
import java.util.Arrays;
import java.util.HashMap;
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
        customOperations.getMultipleOperations().clear();
    }
    
    @Test
    public void testAddOperationValidInput(){
        Boolean result = customOperations.addOperation("ValidTest", "+ clear dup sqrt >e");
        assertTrue("Addition of the operation not successful", result);
        HashMap<String,String[]> mapOperations = customOperations.getMultipleOperations();
        assertTrue("Name of the operation doesn't exists", mapOperations.containsKey("ValidTest"));
        String[] singleOp = "+ clear dup sqrt >e".split("\\s+");
        assertTrue("Sequence of operations doesn't correspond to name", Arrays.equals(singleOp,mapOperations.get("ValidTest")));
    }
    
    @Test
    public void testAddOperationInvalidInputName(){
        Boolean result1 = customOperations.addOperation("ValidTest", "+ clear dup sqrt >e");
        assertTrue("Addition of the operation not successful", result1);
        Boolean result2 = customOperations.addOperation("ValidTest", "+ clear dup sqrt >e");
        assertFalse("The name of the operation doesn't exists", result2);
    }
    
    @Test
    public void testAddOperationInvalidInputOperation(){
        Boolean result = customOperations.addOperation("ValidTest", "+ clear dop sqrt >e");
        assertFalse("Sequence of the operation is valid", result);
    }
    
    @Test
    public void testAddOperationCustomInCustom(){
        HashMap<String,String[]> multipleOperations = customOperations.getMultipleOperations();
        multipleOperations.put("Op1", "+".split("\\s+"));
        Boolean result = customOperations.addOperation("Op2", "dup Op1 - +");
        assertTrue("Sequence of operations is not valid", result);
        assertTrue("Sequence of operations is not valid", multipleOperations.containsKey("Op2"));
    }
    
    @Test
    public void testModifyOperationValidInput(){
        customOperations.getMultipleOperations().put("ValidTest", "+ clear dup sqrt >e".split("\\s+"));
        Boolean result = customOperations.modifyOperation("ValidTest", "ValidTest", "+ clear drop sqrt >e");
        assertTrue("Modification of the operation not success",result);
        HashMap<String,String[]> mapOperations = customOperations.getMultipleOperations();
        assertTrue("Name of the operation doesn't exists", mapOperations.containsKey("ValidTest"));
        assertTrue("Sequence of operations doesn't correspond to name", Arrays.equals("+ clear drop sqrt >e".split("\\s+"), mapOperations.get("ValidTest")));
    }
    
    @Test
    public void testModifyOperationInvalidOldName(){
        Boolean result = customOperations.modifyOperation("ValidTest", "ValidTest", "+ clear drop sqrt >e");
        assertFalse("The name of the operation exists",result);
    }
    
    @Test
    public void testModifyOperationInvalidNewOperation(){
        customOperations.getMultipleOperations().put("ValidTest", "+ clear dup sqrt >e".split("\\s+"));
        Boolean result = customOperations.modifyOperation("ValidTest", "ValidTest", "+ cleardrop sqrt >e");
        assertFalse("The sequence of the operation is valid",result);
    }
    
    @Test
    public void testModifyOperationInvalidNewName(){
        customOperations.getMultipleOperations().put("ValidTest", "+ clear dup sqrt >e".split("\\s+"));
        customOperations.getMultipleOperations().put("ValidTest2", "+ clear dup sqrt >e".split("\\s+"));
        Boolean result = customOperations.modifyOperation("ValidTest", "ValidTest2", "+ clear swap sqrt >e");
        assertFalse("The name doesn't exists",result);
    }
    
    @Test
    public void testDeleteOperationValid(){
    customOperations.getMultipleOperations().put("ValidTest", "+".split("\\s+"));
    customOperations.deleteOperation("ValidTest");
    assertEquals("Deletion not success",0,customOperations.getMultipleOperations().size());
    }
    
    @Test
    public void testDeleteOperationInvalid(){
    customOperations.getMultipleOperations().put("ValidTest", "+".split("\\s+"));
    Boolean result = customOperations.deleteOperation("ValidTest1");
    assertFalse("Deletion success", result);
    }
    
    public void testExecuteExistingOperation() throws Exception{
        stack.add(new ComplexNumber(-12, 89));
        stack.add(new ComplexNumber(3.5, -2));
        stack.add(new ComplexNumber(1,0));
        stack.add(new ComplexNumber(33, 21));
        customOperations.getMultipleOperations().put("Op1", "+ >b dup * <b swap over".split("\\s+"));
        Boolean result = customOperations.executeIfExists("Op1", stack);
        assertTrue("Operation not executed", result);
        ComplexNumber number = stack.peek();
        assertEquals("Real part not expected", 34, number.getReal(), 0);
        assertEquals("ImaginaryPart part not expected", 21, number.getImaginary(), 0);
    }
    
    @Test
    public void testExecuteNotExistingOperation() throws Exception{
        stack.add(new ComplexNumber(-12, 89));
        stack.add(new ComplexNumber(3.5, -2));
        stack.add(new ComplexNumber(1,0));
        stack.add(new ComplexNumber(33, 21));
        customOperations.getMultipleOperations().put("Op1", "+ >b dup * <b swap over".split("\\s+"));
        Boolean result = customOperations.executeIfExists("Op2", stack);
        assertFalse("Operation executed but not existing", result);
        assertEquals("Stack updated with operation not existing", 4, stack.size());
    }
    
    
}
