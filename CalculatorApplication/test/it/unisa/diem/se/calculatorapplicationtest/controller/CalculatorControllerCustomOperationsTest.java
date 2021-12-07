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
import it.unisa.diem.se.calculatorapplication.service.SingleOperationsInterface;
import it.unisa.diem.se.calculatorapplication.service.StackOperations;
import it.unisa.diem.se.calculatorapplication.service.VariablesOperations;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
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
        CustomOperations customOperations = controller.getCustomOperations();
        assertNotNull("singleOperations is null" , singleOperations);
        assertEquals("singleOperations doesn't have three argument", 3, singleOperations.size());
        assertEquals("first item of singleOperations is not a MathematicalOperations", MathematicalOperations.class, singleOperations.get(0).getClass());
        assertEquals("first item of singleOperations is not a StackOperations", StackOperations.class, singleOperations.get(1).getClass());
        assertEquals("first item of singleOperations is not a VariablesOperations", VariablesOperations.class, singleOperations.get(2).getClass());
        assertNotNull("CustomeOperations is null", customOperations);
        assertNotNull("stackNumbers is null", controller.getStackNumbers());
    }
    
    @After
    public void clearStack(){
        controller.getStackNumbers().clear();
        controller.getCustomOperations().getMultipleOperations().clear();
    }
    
    
    @Test
    public void testAddOperationValid() throws InvalidInputException{
        controller.addCustomOperations("Op1", "+ clear >x");
        assertEquals("CustomOperation not added", 1, controller.getCustomOperations().getMultipleOperations().size());
    }
    
    @Test(expected = InvalidInputException.class)
    public void testAddOperationInvalid() throws InvalidInputException{
        controller.addCustomOperations("Op1", "+ dop >x");
    }
    
    @Test
    public void testModifyOperationValid() throws InvalidInputException{
        HashMap<String,String[]> multipleOperations = controller.getCustomOperations().getMultipleOperations();
        multipleOperations.put("Op1", "+ clear >x".split("\\s+"));
        controller.modifyCustomOperation("Op1", "Op1", "* clear >x");
        assertTrue("ModifyOperation not executed", Arrays.equals("* clear >x".split("\\s+"), multipleOperations.get("Op1")));
    }
    
    @Test(expected = InvalidInputException.class)
    public void testModifyOperationInvalid() throws InvalidInputException{
        HashMap<String,String[]> multipleOperations = controller.getCustomOperations().getMultipleOperations();
        multipleOperations.put("Op1", "+ clear >x".split("\\s+"));
        controller.modifyCustomOperation("Op1", "Op2", "* clear >x");
    }

    @Test
    public void testDeleteOperationValid() throws InvalidInputException{
        HashMap<String,String[]> multipleOperations = controller.getCustomOperations().getMultipleOperations();
        multipleOperations.put("Op1", "+ clear >x".split("\\s+"));
        controller.deleteCustomOperation("Op1");
        assertEquals("DeleteOperation not executed", 0, multipleOperations.size());
    }
    
    @Test(expected = InvalidInputException.class)
    public void testDeleteOperationInvalid() throws InvalidInputException{
        HashMap<String,String[]> multipleOperations = controller.getCustomOperations().getMultipleOperations();
        multipleOperations.put("Op1", "+ clear >x".split("\\s+"));
        controller.deleteCustomOperation("Op2");
    }    
    
    @Test
    public void testSaveCustomOperationToFileEmptyContent() throws IOException{
        File file = new File("File.txt");
        controller.saveCustomOperationToFile(file);
        BufferedReader bf = new BufferedReader(new FileReader(file));
        assertEquals("The file is not empty", -1, bf.read());
    }
    
    @Test
    public void testSaveCustomOperationToFileNotEmptyContent() throws IOException{
        File file = new File("File.txt");
        HashMap<String,String[]> customOperations = controller.getCustomOperations().getMultipleOperations();
        customOperations.put("Op1", "clear + dup >e".split("\\s+"));
        customOperations.put("Op2", "clear".split("\\s+"));
        controller.saveCustomOperationToFile(file);
        BufferedReader bf = new BufferedReader(new FileReader(file));
        String line = bf.readLine();
        assertTrue("Line1 not existing", (line.equals("Op1, clear + dup >e")) || (line.equals("Op2, clear")));
        String line2 = bf.readLine();
        assertTrue("Line2 not existing", (line2.equals("Op1, clear + dup >e")) || (line2.equals("Op2, clear")));
        assertEquals("Number of lines not expected", -1, bf.read());
        bf.close();
        file.delete();
    }
    
    @Test
    public void testReloadCustomOperationFromFileEmpty() throws IOException{
        File file = new File("File.txt");
        BufferedWriter bf = new BufferedWriter(new FileWriter(file));
        HashMap<String,String[]> customOperations = controller.getCustomOperations().getMultipleOperations();
        customOperations.put("Op1", "clear + dup >e".split("\\s+"));
        customOperations.put("Op2", "clear".split("\\s+"));
        bf.close();
        controller.reloadCustomOperationFromFile(file);
        assertEquals("Reload not executed" ,0,customOperations.size());
        file.delete();
       
    }
    
    @Test
    public void testReloadCustomOperationFromFileNotEmpty() throws IOException{
        File file = new File("File1.txt");
        BufferedWriter bf = new BufferedWriter(new FileWriter(file));
        HashMap<String,String[]> customOperations = controller.getCustomOperations().getMultipleOperations();
        customOperations.put("Op1", "clear + dup >e".split("\\s+"));
        customOperations.put("Op2", "clear".split("\\s+"));
        bf.write("Op3, * + over >e");
        bf.newLine();
        bf.write("Op4, / <b over >e");
        bf.newLine();
        bf.write("Op5, <c + clear sqrt");
        bf.close();
        controller.reloadCustomOperationFromFile(file);
        assertEquals("Reload not executed" , 3, customOperations.size());
        file.delete();
    }
}
