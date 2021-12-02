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
import java.lang.Math;

/**
 *
 * @author ASUS
 */
public class ComplexNumberSquareRootTest {
    
    private static ComplexNumber complexNumber1;
    
    @BeforeClass
    public static void setUp() {        
        complexNumber1 = new ComplexNumber(1,2);
        assertNotNull (complexNumber1);
        assertEquals("Real part not expected", 1, complexNumber1.getReal(), 0);
        assertEquals("Imaginary part not expected", 2, complexNumber1.getImaginary(), 0);
    }

    @Test
    public void testAllZero() {
        complexNumber1 = new ComplexNumber(0,0);
        ComplexNumber radix = ComplexNumber.squareRoot(complexNumber1);
        assertEquals("Real part not expected", 0, radix.getReal(),0);
        assertEquals("Imaginary part not expected", 0, radix.getImaginary(),0);
    }
    
    @Test
    public void testRealIsZero() {
        complexNumber1 = new ComplexNumber(0,4);
        ComplexNumber radix = ComplexNumber.squareRoot(complexNumber1);
        assertEquals("Real part not expected", 1.41421356, radix.getReal(),0);
        assertEquals("Imaginary part not expected", 1.41421356, radix.getImaginary(),0);
    }
    
    @Test
    public void testImaginaryIsZero() {
        complexNumber1 = new ComplexNumber(4,0);
        ComplexNumber radix = ComplexNumber.squareRoot(complexNumber1);
        assertEquals("Real part not expected", 2, radix.getReal(),0);
        assertEquals("Imaginary part not expected", 0, radix.getImaginary(),0);
    }
    
    @Test
    public void testAllNegative() {
        complexNumber1 = new ComplexNumber(-4,-7);
        ComplexNumber radix = ComplexNumber.squareRoot(complexNumber1);
        assertEquals("Real part not expected", 1.42517679, radix.getReal(),0);
        assertEquals("Imaginary part not expected", -2.45583568, radix.getImaginary(),0);
    }
    
    @Test
    public void testAllPositive() {
        complexNumber1 = new ComplexNumber(4,6);
        ComplexNumber radix = ComplexNumber.squareRoot(complexNumber1);
        assertEquals("Real part not expected", 2.36760454, radix.getReal(),0);
        assertEquals("Imaginary part not expected", 1.26710350, radix.getImaginary(),0);
    }
    
    @Test
    public void testRealIsNegative() {
        complexNumber1 = new ComplexNumber(-3,9);
        ComplexNumber radix = ComplexNumber.squareRoot(complexNumber1);
        assertEquals("Real part not expected", 1.80094878, radix.getReal(),0);
        assertEquals("Imaginary part not expected", 2.49868295, radix.getImaginary(),0);
    }
    
    @Test
    public void testImaginaryIsNegative() {
        complexNumber1 = new ComplexNumber(7,-5);
        ComplexNumber radix = ComplexNumber.squareRoot(complexNumber1);
        assertEquals("Real part not expected", 2.79305615, radix.getReal(),0);
        assertEquals("Imaginary part not expected", -0.89507689, radix.getImaginary(),0);
    }
    
    @Test
    public void testAllFloat() {
        complexNumber1 = new ComplexNumber(4.16,5.32);
        ComplexNumber radix = ComplexNumber.squareRoot(complexNumber1);
        assertEquals("Real part not expected", 2.33595479, radix.getReal(),0);
        assertEquals("Imaginary part not expected", 1.13872067, radix.getImaginary(),0);
    }
    
    @Test
    public void testFloatWithDifferentDecimal() {
        complexNumber1 = new ComplexNumber(4.165,8.26816);
        ComplexNumber radix = ComplexNumber.squareRoot(complexNumber1);
        assertEquals("Real part not expected", 2.59065176, radix.getReal(),0);
        assertEquals("Imaginary part not expected", 1.59576832, radix.getImaginary(),0);
    }
  
    
    @Test
    public void testFloatManyDecimal() {
        complexNumber1 = new ComplexNumber(4.16258489,33.94346182);
        ComplexNumber radix = ComplexNumber.squareRoot(complexNumber1);
        assertEquals("Real part not expected", 4.37951648, radix.getReal(),0);
        assertEquals("Imaginary part not expected", 3.87525221, radix.getImaginary(),0);
    }
    
    
    
    
}
    
    
    
   

  
