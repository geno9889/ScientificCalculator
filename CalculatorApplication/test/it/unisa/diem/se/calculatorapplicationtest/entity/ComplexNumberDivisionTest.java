/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.se.calculatorapplicationtest.entity;

import it.unisa.diem.se.calculatorapplication.entity.ComplexNumber;
import it.unisa.diem.se.calculatorapplication.entity.MathematicalException;
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
    public void testIntAllNegativeValue() throws MathematicalException {
        complexNumber1 = new ComplexNumber(-189,-75);
        complexNumber2 = new ComplexNumber(-21,-2);
        ComplexNumber division = ComplexNumber.division(complexNumber1,complexNumber2);
        assertEquals("Real part not expected", 9.25617978, division.getReal(),0);
        assertEquals("Imaginary part not expected", 2.68988764, division.getImaginary(),0);
    }
    
    @Test
    public void testIntPositiveFirstRealNegativeOthers() throws MathematicalException {
        complexNumber1 = new ComplexNumber(189,-75);
        complexNumber2 = new ComplexNumber(-21,-2);
        ComplexNumber division = ComplexNumber.division(complexNumber1,complexNumber2);
        assertEquals("Real part not expected", -8.58202247, division.getReal(),0);
        assertEquals("Imaginary part not expected", 4.38876404, division.getImaginary(),0);
    }
    
    @Test
    public void testIntPositiveFirstImaginaryNegativeOthers() throws MathematicalException {
        complexNumber1 = new ComplexNumber(-189,-75);
        complexNumber2 = new ComplexNumber(21,-2);
        ComplexNumber division = ComplexNumber.division(complexNumber1,complexNumber2);
        assertEquals("Real part not expected", -8.58202247, division.getReal(),0);
        assertEquals("Imaginary part not expected", -4.38876404, division.getImaginary(),0);
    }
    
    @Test
    public void testIntPositiveSecondRealNegativeOthers() throws MathematicalException {
        complexNumber1 = new ComplexNumber(-189,-75);
        complexNumber2 = new ComplexNumber(21,-2);
        ComplexNumber division = ComplexNumber.division(complexNumber1,complexNumber2);
        assertEquals("Real part not expected", -8.58202247, division.getReal(),0);
        assertEquals("Imaginary part not expected", -4.38876404, division.getImaginary(),0);
    }
    
    @Test
    public void testIntPositiveSecondImaginaryNegativeOthers() throws MathematicalException {
        complexNumber1 = new ComplexNumber(-189,-75);
        complexNumber2 = new ComplexNumber(21,-2);
        ComplexNumber division = ComplexNumber.division(complexNumber1,complexNumber2);
        assertEquals("Real part not expected", -8.58202247, division.getReal(),0);
        assertEquals("Imaginary part not expected", -4.38876404, division.getImaginary(),0);
    }
    
    @Test
    public void testIntPositiveFirstNegativeSecond() throws MathematicalException {
        complexNumber1 = new ComplexNumber(189,75);
        complexNumber2 = new ComplexNumber(-21,-2);
        ComplexNumber division = ComplexNumber.division(complexNumber1,complexNumber2);
        assertEquals("Real part not expected", -9.25617978, division.getReal(),0);
        assertEquals("Imaginary part not expected", -2.68988764, division.getImaginary(),0);
    }
    
    @Test
    public void testIntNegativeFirstPositiveSecond() throws MathematicalException {
        complexNumber1 = new ComplexNumber(189,75);
        complexNumber2 = new ComplexNumber(-21,-2);
        ComplexNumber division = ComplexNumber.division(complexNumber1,complexNumber2);
        assertEquals("Real part not expected", -9.25617978, division.getReal(),0);
        assertEquals("Imaginary part not expected", -2.68988764, division.getImaginary(),0);
    }
    
    @Test
    public void testIntZeroFirstRealNegativeOthers() throws MathematicalException {
        complexNumber1 = new ComplexNumber(0,-75);
        complexNumber2 = new ComplexNumber(-2,-30);
        ComplexNumber division = ComplexNumber.division(complexNumber1,complexNumber2);
        assertEquals("Real part not expected", 2.48893805, division.getReal(),0);
        assertEquals("Imaginary part not expected", 0.16592920, division.getImaginary(),0);
    }
    
    @Test
    public void testIntZeroFirstImaginaryNegativeOthers() throws MathematicalException {
        complexNumber1 = new ComplexNumber(-75,0);
        complexNumber2 = new ComplexNumber(-2,-30);
        ComplexNumber division = ComplexNumber.division(complexNumber1,complexNumber2);
        assertEquals("Real part not expected", 0.16592920, division.getReal(),0);
        assertEquals("Imaginary part not expected", -2.48893805, division.getImaginary(),0);
    }
    
    @Test
    public void testIntZeroSecondRealNegativeOthers() throws MathematicalException {
        complexNumber1 = new ComplexNumber(-75,-2);
        complexNumber2 = new ComplexNumber(0,-30);
        ComplexNumber division = ComplexNumber.division(complexNumber1,complexNumber2);
        assertEquals("Real part not expected", 0.06666667, division.getReal(),0);
        assertEquals("Imaginary part not expected", -2.5, division.getImaginary(),0);
    }
    
    @Test
    public void testIntZeroSecondImaginaryNegativeOthers() throws MathematicalException {
        complexNumber1 = new ComplexNumber(-75,-2);
        complexNumber2 = new ComplexNumber(-30,0);
        ComplexNumber division = ComplexNumber.division(complexNumber1,complexNumber2);
        assertEquals("Real part not expected", 2.5, division.getReal(),0);
        assertEquals("Imaginary part not expected", 0.06666667, division.getImaginary(),0);
    }
    
    @Test
    public void testIntAllPositiveFewDecimal() throws MathematicalException {
        complexNumber1 = new ComplexNumber(17,131);
        complexNumber2 = new ComplexNumber(2,20);
        ComplexNumber division = ComplexNumber.division(complexNumber1,complexNumber2);
        assertEquals("Real part not expected", 6.56930693, division.getReal(),0);
        assertEquals("Imaginary part not expected", -0.19306931, division.getImaginary(),0);
    }
    @Test
    public void testIntZeroFirst() throws MathematicalException {
        complexNumber1 = new ComplexNumber(0,0);
        complexNumber2 = new ComplexNumber(2,2);
        ComplexNumber division = ComplexNumber.division(complexNumber1,complexNumber2);
        assertEquals("Real part not expected", 0, division.getReal(),0);
        assertEquals("Imaginary part not expected", 0, division.getImaginary(),0);
    }
    
    @Test (expected = MathematicalException.class)
    public void testIntZeroSecond() throws MathematicalException {
        complexNumber1 = new ComplexNumber(2,2);
        complexNumber2 = new ComplexNumber(0,0);
        ComplexNumber.division(complexNumber1,complexNumber2);
    }
    
    @Test (expected = MathematicalException.class)
    public void testAllZero() throws MathematicalException {
        complexNumber1 = new ComplexNumber(0,0);
        complexNumber2 = new ComplexNumber(0,0);
        ComplexNumber.division(complexNumber1,complexNumber2);
    }
    
    @Test
    public void testAllFloatFewDecimal() throws MathematicalException {
        complexNumber1 = new ComplexNumber(1.7,3.45);
        complexNumber2 = new ComplexNumber(7.12,4.89);
        ComplexNumber division = ComplexNumber.division(complexNumber1,complexNumber2);
        assertEquals("Real part not expected", 0.38836428, division.getReal(),0);
        assertEquals("Imaginary part not expected", 0.21782284, division.getImaginary(),0);
    }
    
    @Test
    public void testAllFloatManyDecimal() throws MathematicalException {
        complexNumber1 = new ComplexNumber(1.710249426,3.45);
        complexNumber2 = new ComplexNumber(7.1211199222222,4.8990149999999);
        ComplexNumber division = ComplexNumber.division(complexNumber1,complexNumber2);
        assertEquals("Real part not expected", 0.38924136, division.getReal(),0);
        assertEquals("Imaginary part not expected", 0.21669355, division.getImaginary(),0);
    }
    
    @Test
    public void testMixFloatInt() throws MathematicalException {
        complexNumber1 = new ComplexNumber(1.7,10);
        complexNumber2 = new ComplexNumber(22,4.89);
        ComplexNumber division = ComplexNumber.division(complexNumber1,complexNumber2);
        assertEquals("Real part not expected", 0.16991129, division.getReal(),0);
        assertEquals("Imaginary part not expected", 0.41677881, division.getImaginary(),0);
    }
}
