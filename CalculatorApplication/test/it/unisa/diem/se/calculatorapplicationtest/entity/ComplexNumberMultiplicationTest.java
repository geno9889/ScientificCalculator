/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.se.calculatorapplicationtest.entity;

import it.unisa.diem.se.calculatorapplication.entity.ComplexNumber;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marcograziano
 */
public class ComplexNumberMultiplicationTest {
    
    private static ComplexNumber complexNumber1, complexNumber2;
         
    @BeforeClass
    public static void setUp() {        
        complexNumber1 = new ComplexNumber(1,2);
        assertNotNull (complexNumber1);
        assertEquals("Real part not expected", 1, complexNumber1.getReal(), 0);
        assertEquals("Imaginary part not expected", 2, complexNumber1.getImaginary(), 0);
        complexNumber2 = new ComplexNumber(1);
        assertNotNull(complexNumber2);
        assertEquals("Real part not expected", 1, complexNumber2.getReal(), 0);
        assertEquals("Imaginary part not expected", 0, complexNumber2.getImaginary(), 0);
    }
    
    
     @Test
    public void testAllNegativeValue() {
        complexNumber1 = new ComplexNumber(-1,-1);
        complexNumber2 = new ComplexNumber(-2,-2);
        ComplexNumber multiplication = ComplexNumber.multiplication(complexNumber1,complexNumber2);
        assertEquals("Real part not expected", 0, multiplication.getReal(), 0);
        assertEquals("Imaginary part not expected", 4, multiplication.getImaginary(), 0);
    }
    
    @Test
    public void testRealPartIsZero() {
        complexNumber1 = new ComplexNumber(0,-1);
        complexNumber2 = new ComplexNumber(-2,-2);
        ComplexNumber multiplication = ComplexNumber.multiplication(complexNumber1,complexNumber2);
        assertNotNull (multiplication);
        assertEquals("Real part not expected", -2, multiplication.getReal(), 0);
        assertEquals("Imaginary part not expected", 2, multiplication.getImaginary(), 0);
    }
       
    @Test
    public void testRealPartIsPositive() {
        complexNumber1 = new ComplexNumber(1,-1);
        complexNumber2 = new ComplexNumber(-2,-2);
        ComplexNumber multiplication = ComplexNumber.multiplication(complexNumber1,complexNumber2);
        assertNotNull (multiplication);
        assertEquals("Real part not expected", -4, multiplication.getReal(), 0);
        assertEquals("Imaginary part not expected", 0, multiplication.getImaginary(), 0);
    }
    
    @Test
    public void testImaginaryPartIsZero() {
        complexNumber1 = new ComplexNumber(-1,0);
        complexNumber2 = new ComplexNumber(-2,-2);
        ComplexNumber multiplication = ComplexNumber.multiplication(complexNumber1,complexNumber2);
        assertNotNull (multiplication);
        assertEquals("Real part not expected", 2, multiplication.getReal(), 0);
        assertEquals("Imaginary part not expected", 2, multiplication.getImaginary(), 0);
    }
    
    @Test
    public void testImaginaryPartIsPositive() {
        complexNumber1 = new ComplexNumber(-1,1);
        complexNumber2 = new ComplexNumber(-2,-2);
        ComplexNumber multiplication = ComplexNumber.multiplication(complexNumber1,complexNumber2);
        assertNotNull (multiplication);
        assertEquals("Real part not expected", 4, multiplication.getReal(), 0);
        assertEquals("Imaginary part not expected", 0, multiplication.getImaginary(), 0);
    }
    
    @Test
    public void testRealPartIsZeroSecondComplexNumber() {
        complexNumber1 = new ComplexNumber(-1,-1);
        complexNumber2 = new ComplexNumber(0,-2);
        ComplexNumber multiplication = ComplexNumber.multiplication(complexNumber1,complexNumber2);
        assertNotNull (multiplication);
        assertEquals("Real part not expected", -2, multiplication.getReal(), 0);
        assertEquals("Imaginary part not expected", 2, multiplication.getImaginary(), 0);
    }
    
    @Test
    public void testRealPartIsPositiveSecondComplexNumber() {
        complexNumber1 = new ComplexNumber(-1,-1);
        complexNumber2 = new ComplexNumber(1,-2);
        ComplexNumber multiplication = ComplexNumber.multiplication(complexNumber1,complexNumber2);
        assertNotNull (multiplication);
        assertEquals("Real part not expected", -3, multiplication.getReal(), 0);
        assertEquals("Imaginary part not expected", 1, multiplication.getImaginary(), 0);
    }
    
    @Test
    public void testImaginaryPartIsZeroSecondComplexNumber() {
        complexNumber1 = new ComplexNumber(-1,-1);
        complexNumber2 = new ComplexNumber(-2,0);
        ComplexNumber multiplication = ComplexNumber.multiplication(complexNumber1,complexNumber2);
        assertNotNull (multiplication);
        assertEquals("Real part not expected", 2, multiplication.getReal(), 0);
        assertEquals("Imaginary part not expected", 2, multiplication.getImaginary(), 0);
    }
    
    @Test
    public void testImaginaryPartIsPositiveSecondComplexNumber() {
        complexNumber1 = new ComplexNumber(-1,-1);
        complexNumber2 = new ComplexNumber(-2,2);
        ComplexNumber multiplication = ComplexNumber.multiplication(complexNumber1,complexNumber2);
        assertNotNull (multiplication);
        assertEquals("Real part not expected", 4, multiplication.getReal(), 0);
        assertEquals("Imaginary part not expected", 0, multiplication.getImaginary(), 0);
    }
      
    @Test
    public void testAllPositive() {
        complexNumber1 = new ComplexNumber(1,1);
        complexNumber2 = new ComplexNumber(2,2);
        ComplexNumber multiplication = ComplexNumber.multiplication(complexNumber1,complexNumber2);
        assertNotNull (multiplication);
        assertEquals("Real part not expected", 0, multiplication.getReal(), 0);
        assertEquals("Imaginary part not expected", 4, multiplication.getImaginary(), 0);
    }
    
    @Test
    public void testFirstComplexNumberIsAllPositive() {
        complexNumber1 = new ComplexNumber(1,1);
        complexNumber2 = new ComplexNumber(-2,-2);
        ComplexNumber multiplication = ComplexNumber.multiplication(complexNumber1,complexNumber2);
        assertNotNull (multiplication);
        assertEquals("Real part not expected", 0, multiplication.getReal(), 0);
        assertEquals("Imaginary part not expected", -4, multiplication.getImaginary(), 0);
    }
    
    @Test
    public void testSecondComplexNumberIsAllPositive() {
        complexNumber1 = new ComplexNumber(-1,-1);
        complexNumber2 = new ComplexNumber(2,2);
        ComplexNumber multiplication = ComplexNumber.multiplication(complexNumber1,complexNumber2);
        assertNotNull (multiplication);
        assertEquals("Real part not expected", 0, multiplication.getReal(), 0);
        assertEquals("Imaginary part not expected", -4, multiplication.getImaginary(), 0);
    }
    
    @Test
    public void testFirstComplexZero() {
        complexNumber1 = new ComplexNumber(0,0);
        complexNumber2 = new ComplexNumber(2,2);
        ComplexNumber multiplication = ComplexNumber.multiplication(complexNumber1,complexNumber2);
        assertNotNull (multiplication);
        assertEquals("Real part not expected", 0, multiplication.getReal(), 0);
        assertEquals("Imaginary part not expected", 0, multiplication.getImaginary(), 0);
    }
    
    @Test
    public void testSecondComplexZero() {
        complexNumber1 = new ComplexNumber(2,2);
        complexNumber2 = new ComplexNumber(0,0);
        ComplexNumber multiplication = ComplexNumber.multiplication(complexNumber1,complexNumber2);
        assertNotNull (multiplication);
        assertEquals("Real part not expected", 0, multiplication.getReal(), 0);
        assertEquals("Imaginary part not expected", 0, multiplication.getImaginary(), 0);
    }
    
    @Test
    public void testAllComplexZero() {
        complexNumber1 = new ComplexNumber(0,0);
        complexNumber2 = new ComplexNumber(0,0);
        ComplexNumber multiplication = ComplexNumber.multiplication(complexNumber1,complexNumber2);
        assertNotNull (multiplication);
        assertEquals("Real part not expected", 0, multiplication.getReal(), 0);
        assertEquals("Imaginary part not expected", 0, multiplication.getImaginary(), 0);
    }
    
    @Test
    public void testAllDouble() {
        complexNumber1 = new ComplexNumber(1.2,2.2);
        complexNumber2 = new ComplexNumber(2.5,0.8);
        ComplexNumber multiplication = ComplexNumber.multiplication(complexNumber1,complexNumber2);
        assertNotNull (multiplication);
        assertEquals("Real part not expected", 1.24, multiplication.getReal(), 0.00000001);
        assertEquals("Imaginary part not expected", 6.46, multiplication.getImaginary(), 0.00000001);
    }
    
}