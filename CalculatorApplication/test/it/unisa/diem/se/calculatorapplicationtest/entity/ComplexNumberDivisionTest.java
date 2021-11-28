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
 * @author Giuseppe
 */
public class ComplexNumberDivisionTest {
    
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
    public void testIntAllNegativeValue() {
        complexNumber1 = new ComplexNumber(-189,-75);
        complexNumber2 = new ComplexNumber(-21,-2);
        ComplexNumber division = ComplexNumber.division(complexNumber1,complexNumber2);
        assertEquals("Real part not expected", 9.256, division.getReal(),0);
        assertEquals("Imaginary part not expected", 2.690, division.getImaginary(),0);
    }
    
    @Test
    public void testIntPositiveFirstRealNegativeOthers() {
        complexNumber1 = new ComplexNumber(189,-75);
        complexNumber2 = new ComplexNumber(-21,-2);
        ComplexNumber division = ComplexNumber.division(complexNumber1,complexNumber2);
        assertEquals("Real part not expected", -8.582, division.getReal(),0);
        assertEquals("Imaginary part not expected", 4.389, division.getImaginary(),0);
    }
    
    @Test
    public void testIntPositiveFirstImaginaryNegativeOthers() {
        complexNumber1 = new ComplexNumber(-189,-75);
        complexNumber2 = new ComplexNumber(21,-2);
        ComplexNumber division = ComplexNumber.division(complexNumber1,complexNumber2);
        assertEquals("Real part not expected", -8.582, division.getReal(),0);
        assertEquals("Imaginary part not expected", -4.389, division.getImaginary(),0);
    }
    
    @Test
    public void testIntPositiveSecondRealNegativeOthers() {
        complexNumber1 = new ComplexNumber(-189,-75);
        complexNumber2 = new ComplexNumber(21,-2);
        ComplexNumber division = ComplexNumber.division(complexNumber1,complexNumber2);
        assertEquals("Real part not expected", -8.582, division.getReal(),0);
        assertEquals("Imaginary part not expected", -4.389, division.getImaginary(),0);
    }
    
    @Test
    public void testIntPositiveSecondImaginaryNegativeOthers() {
        complexNumber1 = new ComplexNumber(-189,-75);
        complexNumber2 = new ComplexNumber(21,-2);
        ComplexNumber division = ComplexNumber.division(complexNumber1,complexNumber2);
        assertEquals("Real part not expected", -8.582, division.getReal(),0);
        assertEquals("Imaginary part not expected", -4.389, division.getImaginary(),0);
    }
    
    @Test
    public void testIntPositiveFirstNegativeSecond() {
        complexNumber1 = new ComplexNumber(189,75);
        complexNumber2 = new ComplexNumber(-21,-2);
        ComplexNumber division = ComplexNumber.division(complexNumber1,complexNumber2);
        assertEquals("Real part not expected", -9.256, division.getReal(),0);
        assertEquals("Imaginary part not expected", -2.690, division.getImaginary(),0);
    }
    
    @Test
    public void testIntNegativeFirstPositiveSecond() {
        complexNumber1 = new ComplexNumber(189,75);
        complexNumber2 = new ComplexNumber(-21,-2);
        ComplexNumber division = ComplexNumber.division(complexNumber1,complexNumber2);
        assertEquals("Real part not expected", -9.256, division.getReal(),0);
        assertEquals("Imaginary part not expected", -2.690, division.getImaginary(),0);
    }
    
    @Test
    public void testIntZeroFirstRealNegativeOthers() {
        complexNumber1 = new ComplexNumber(0,-75);
        complexNumber2 = new ComplexNumber(-2,-30);
        ComplexNumber division = ComplexNumber.division(complexNumber1,complexNumber2);
        assertEquals("Real part not expected", 2.489, division.getReal(),0);
        assertEquals("Imaginary part not expected", 0.166, division.getImaginary(),0);
    }
    
    @Test
    public void testIntZeroFirstImaginaryNegativeOthers() {
        complexNumber1 = new ComplexNumber(-75,0);
        complexNumber2 = new ComplexNumber(-2,-30);
        ComplexNumber division = ComplexNumber.division(complexNumber1,complexNumber2);
        assertEquals("Real part not expected", 0.166, division.getReal(),0);
        assertEquals("Imaginary part not expected", -2.489, division.getImaginary(),0);
    }
    
    @Test
    public void testIntZeroSecondRealNegativeOthers() {
        complexNumber1 = new ComplexNumber(-75,-2);
        complexNumber2 = new ComplexNumber(0,-30);
        ComplexNumber division = ComplexNumber.division(complexNumber1,complexNumber2);
        assertEquals("Real part not expected", 0.067, division.getReal(),0);
        assertEquals("Imaginary part not expected", -2.5, division.getImaginary(),0);
    }
    
    @Test
    public void testIntZeroSecondImaginaryNegativeOthers() {
        complexNumber1 = new ComplexNumber(-75,-2);
        complexNumber2 = new ComplexNumber(-30,0);
        ComplexNumber division = ComplexNumber.division(complexNumber1,complexNumber2);
        assertEquals("Real part not expected", 2.5, division.getReal(),0);
        assertEquals("Imaginary part not expected", 0.067, division.getImaginary(),0);
    }
    
    @Test
    public void testIntAllPositive() {
        complexNumber1 = new ComplexNumber(17,131);
        complexNumber2 = new ComplexNumber(2,20);
        ComplexNumber division = ComplexNumber.division(complexNumber1,complexNumber2);
        assertEquals("Real part not expected", 6.569, division.getReal(),0);
        assertEquals("Imaginary part not expected", -0.193, division.getImaginary(),0);
    }
    
    @Test
    public void testIntZeroFirst() {
        complexNumber1 = new ComplexNumber(0,0);
        complexNumber2 = new ComplexNumber(2,2);
        ComplexNumber division = ComplexNumber.division(complexNumber1,complexNumber2);
        assertEquals("Real part not expected", 0, division.getReal(),0);
        assertEquals("Imaginary part not expected", 0, division.getImaginary(),0);
    }
    
    @Test (expected = ArithmeticException.class)
    public void testIntSecondComplexZero() {
        complexNumber1 = new ComplexNumber(2,2);
        complexNumber2 = new ComplexNumber(0,0);
        ComplexNumber.division(complexNumber1,complexNumber2);
    }
    
    @Test (expected = ArithmeticException.class)
    public void testDivisionAllComplexZero() {
        complexNumber1 = new ComplexNumber(0,0);
        complexNumber2 = new ComplexNumber(0,0);
        ComplexNumber.division(complexNumber1,complexNumber2);
    }
    
    @Test
    public void testDivisionAllFloat() {
        complexNumber1 = new ComplexNumber(1.7,3.45);
        complexNumber2 = new ComplexNumber(7.12,4.89);
        ComplexNumber division = ComplexNumber.division(complexNumber1,complexNumber2);
        assertEquals("Real part not expected", 0.388, division.getReal(),0);
        assertEquals("Imaginary part not expected", 0.218, division.getImaginary(),0);
    }
    
    @Test
    public void testDivisionMixFloatInt() {
        complexNumber1 = new ComplexNumber(1.7,10);
        complexNumber2 = new ComplexNumber(22,4.89);
        ComplexNumber division = ComplexNumber.division(complexNumber1,complexNumber2);
        assertEquals("Real part not expected", 0.17, division.getReal(),0);
        assertEquals("Imaginary part not expected", 0.417, division.getImaginary(),0);
    }
}
