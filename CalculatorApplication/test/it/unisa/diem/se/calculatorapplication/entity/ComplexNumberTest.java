/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package it.unisa.diem.se.calculatorapplication.entity;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ASUS
 */
public class ComplexNumberTest {
    
    ComplexNumber complexNumber1, complexNumber2;
         
        
    @Before
    public void setUp() {        
        complexNumber1 = new ComplexNumber(10,2);
        complexNumber2 = new ComplexNumber(10);
        assertNotNull (complexNumber1);
        assertNotNull (complexNumber2);
        
    }
    
    @After
    public void tearDown() {
        complexNumber1 = null;
        complexNumber2 = null;
        assertNull(complexNumber1);
        assertNull(complexNumber2);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
        public void testGetters () {
        assertNotNull(complexNumber1.getReal());
        assertNotNull(complexNumber1.getImaginary());
        assertNotNull(complexNumber2.getReal());
        assertNotNull(complexNumber2.getImaginary());
    }
    
    @Test
        public void testSetters () {
        ComplexNumber complexNumber3 = new ComplexNumber(1);
        assertNotNull(complexNumber3);
        complexNumber3.setReal(78);
        complexNumber3.setImaginary(78);
        assertNotNull(complexNumber3);
    }
        
        
}
