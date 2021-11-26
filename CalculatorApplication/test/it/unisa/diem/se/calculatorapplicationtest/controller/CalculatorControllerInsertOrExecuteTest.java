/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.se.calculatorapplicationtest.controller;

import it.unisa.diem.se.calculatorapplication.controller.CalculatorController;
import it.unisa.diem.se.calculatorapplication.service.MathematicalOperations;
import it.unisa.diem.se.calculatorapplication.service.SingleOperationsInterface;
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
public class CalculatorControllerInsertOrExecuteTest {
    
    private static CalculatorController controller;
    
    public CalculatorControllerInsertOrExecuteTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        controller = new CalculatorController();
        
    }
    
    @Test
    public void testConstructor(){
        List<SingleOperationsInterface> singleOperations = controller.getSingleOperations();
        assertNotNull("controller is null", controller);
        assertNotNull("stackNumbers is null", controller.getStackNumbers());
        assertNotNull("singleOperations is null" , singleOperations);
        assertEquals("singleOperations doesn't have one argument", 1, singleOperations.size());
        assertEquals("first item of singleOperations is not a MathematicalOperations", MathematicalOperations.class, singleOperations.get(0).getClass());
    }
    
    
}
