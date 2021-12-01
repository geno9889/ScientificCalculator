/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.se.calculatorapplicationtest.entity;

import it.unisa.diem.se.calculatorapplication.entity.ComplexNumber;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Francesco
 */
public class ComplexNumberSubstractionTest {
    
    private static ComplexNumber complexNumber1, complexNumber2;
         
        
    @BeforeClass
    public static void setUp() {        
        complexNumber1 = new ComplexNumber(10,2);
        assertNotNull (complexNumber1);
        assertEquals("Real part not expected", 10, complexNumber1.getReal(), 0);
        assertEquals("Imaginary part not expected", 2, complexNumber1.getImaginary(), 0);
        complexNumber2 = new ComplexNumber(10);
        assertNotNull(complexNumber2);
        assertEquals("Real part not expected", 10, complexNumber2.getReal(), 0);
        assertEquals("Imaginary part not expected", 0, complexNumber2.getImaginary(), 0);
    }
    
    
    @Test
    public void testAllNegative() {
        complexNumber1 = new ComplexNumber(-10,-10);
        complexNumber2 = new ComplexNumber(-20,-20);
        ComplexNumber substraction = ComplexNumber.substraction(complexNumber2,complexNumber1);
        assertNotNull(substraction);
        assertEquals("Real part not expected", 10, substraction.getReal(), 0);
        assertEquals("Imaginary part not expected",10, substraction.getImaginary(), 0);
    }
    
    @Test
    public void testAllPositive() {
        complexNumber1 = new ComplexNumber(10,10);
        complexNumber2 = new ComplexNumber(20,20);
        ComplexNumber substraction = ComplexNumber.substraction(complexNumber2,complexNumber1);
        assertNotNull(substraction);
        assertEquals("Real part not expected", -10, substraction.getReal(), 0);
        assertEquals("Imaginary part not expected",-10, substraction.getImaginary(), 0);
    }
       
    @Test
    public void testOnePositiveOneNegative() {
        complexNumber1 = new ComplexNumber(10,10);
        complexNumber2 = new ComplexNumber(-20,-20);
        ComplexNumber substraction = ComplexNumber.substraction(complexNumber2,complexNumber1);
        assertNotNull(substraction);
        assertEquals("Real part not expected", 30, substraction.getReal(), 0);
        assertEquals("Imaginary part not expected",30, substraction.getImaginary(), 0);
    }
    
    
    @Test
    public void testOppositeSign() {
        complexNumber1 = new ComplexNumber(10,-10);
        complexNumber2 = new ComplexNumber(-20,+20);
        ComplexNumber substraction = ComplexNumber.substraction(complexNumber2,complexNumber1);
        assertNotNull(substraction);
        assertEquals("Real part not expected", 30, substraction.getReal(), 0);
        assertEquals("Imaginary part not expected",-30, substraction.getImaginary(), 0);
    }
    
    
    
    @Test
    public void testOneFloatOneInt() {
        complexNumber1 = new ComplexNumber(10.3, 10.4);
        complexNumber2 = new ComplexNumber(20, 20);
        assertEquals("substraction failed",new ComplexNumber(-9.7, -9.6),ComplexNumber.substraction(complexNumber2,complexNumber1));ComplexNumber substraction = ComplexNumber.substraction(complexNumber2,complexNumber1);
        assertNotNull(substraction);
        assertEquals("Real part not expected", -9.7, substraction.getReal(), 0);
        assertEquals("Imaginary part not expected",-9.6, substraction.getImaginary(), 0);
    }
      
       @Test
    public void testAllFloat(){
        complexNumber1 = new ComplexNumber(10.9, 10.6);
        complexNumber2 = new ComplexNumber(20.1, 20.1);
        ComplexNumber substraction = ComplexNumber.substraction(complexNumber2,complexNumber1);
        assertNotNull(substraction);
        assertEquals("Real part not expected", -9.2, substraction.getReal(), 0.00000001);
        assertEquals("Imaginary part not expected",-9.5, substraction.getImaginary(), 0.00000001);
    }
        
        
        
        
}
