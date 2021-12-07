/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.se.calculatorapplicationtest.controller;

import it.unisa.diem.se.calculatorapplication.controller.CalculatorController;
import it.unisa.diem.se.calculatorapplication.controller.InvalidInputException;
import it.unisa.diem.se.calculatorapplication.service.CustomOperations;
import it.unisa.diem.se.calculatorapplication.service.MathematicalOperations;
import it.unisa.diem.se.calculatorapplication.service.MultipleOperationsInterface;
import it.unisa.diem.se.calculatorapplication.service.SingleOperationsInterface;
import it.unisa.diem.se.calculatorapplication.service.StackOperations;
import it.unisa.diem.se.calculatorapplication.service.VariablesOperations;
import java.util.Arrays;
import java.util.List;
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
public class CalculatorControllerCustomOperationsTest {
    
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
        CustomOperations customOperations = (CustomOperations)controller.getMultipleOperations().get(0);
        customOperations.getCustomOperations().clear();
    }
    
    
    @Test
    public void testAddOperationValid() throws InvalidInputException{
        controller.addCustomOperations("Op1", "+ clear >x");
        CustomOperations custom = (CustomOperations)controller.getMultipleOperations().get(0);
        assertEquals("CustomOperation not added", 1, custom.getCustomOperations().size());
    }
    
    @Test(expected = InvalidInputException.class)
    public void testAddOperationInvalid() throws InvalidInputException{
        controller.addCustomOperations("Op1", "+ dop >x");
    }
    
    @Test
    public void testModifyOperationValid() throws InvalidInputException{
        CustomOperations custom = (CustomOperations)controller.getMultipleOperations().get(0);
        custom.getCustomOperations().put("Op1", "+ clear >x".split("\\s+"));
        controller.modifyOperation("Op1", "Op1", "* clear >x");
        assertTrue("ModifyOperation not executed", Arrays.equals("* clear >x".split("\\s+"), custom.getCustomOperations().get("Op1")));
    }
    
    @Test(expected = InvalidInputException.class)
    public void testModifyOperationInvalid() throws InvalidInputException{
        CustomOperations custom = (CustomOperations)controller.getMultipleOperations().get(0);
        custom.getCustomOperations().put("Op1", "+ clear >x".split("\\s+"));
        controller.modifyOperation("Op1", "Op2", "* clear >x");
    }

    @Test
    public void testDeleteOperationValid() throws InvalidInputException{
        CustomOperations custom = (CustomOperations)controller.getMultipleOperations().get(0);
        custom.getCustomOperations().put("Op1", "+ clear >x".split("\\s+"));
        controller.deleteOperation("Op1");
        assertEquals("DeleteOperation not executed", 0, custom.getCustomOperations().size());
    }
    
    @Test(expected = InvalidInputException.class)
    public void testDeleteOperationInvalid() throws InvalidInputException{
        CustomOperations custom = (CustomOperations)controller.getMultipleOperations().get(0);
        custom.getCustomOperations().put("Op1", "+ clear >x".split("\\s+"));
        controller.deleteOperation("Op2");
    }    
    
}
