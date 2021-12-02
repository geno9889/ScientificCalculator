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
public class ComplexNumberInvertSignTest {
    
    private static ComplexNumber complexNumber1, complexNumber2;
    
    @Test
    public void testAllNegative() {
        complexNumber1 = new ComplexNumber(-10,-10);
        ComplexNumber invertSign = ComplexNumber.invertSign(complexNumber1);
        assertEquals("Real part not expected",10, invertSign.getReal(),0);
        assertEquals("Imaginary part not expected",10, invertSign.getImaginary(),0);
    }
    
    @Test
    public void testAllPositive() {
        complexNumber1 = new ComplexNumber(10,10);
        ComplexNumber invertSign = ComplexNumber.invertSign(complexNumber1);
        assertEquals("Real part not expected",-10, invertSign.getReal(),0);
        assertEquals("Imaginary part not expected",-10, invertSign.getImaginary(),0);
    }
    
    @Test
    public void testNegativeRealPositiveImaginary() {
        complexNumber1 = new ComplexNumber(-10,10);
        ComplexNumber invertSign = ComplexNumber.invertSign(complexNumber1);
        assertEquals("Real part not expected",10, invertSign.getReal(),0);
        assertEquals("Imaginary part not expected",-10, invertSign.getImaginary(),0);
    }

    @Test
    public void testPositiveRealNegativeImaginary() {
      complexNumber1 = new ComplexNumber(10,-10);
        ComplexNumber invertSign = ComplexNumber.invertSign(complexNumber1);
        assertEquals("Real part not expected",-10, invertSign.getReal(),0);
        assertEquals("Imaginary part not expected",10, invertSign.getImaginary(),0);
    }
    
    @Test
    public void testAllFloat() {
        complexNumber1 = new ComplexNumber(10.4,10.6);
        ComplexNumber invertSign = ComplexNumber.invertSign(complexNumber1);
        assertEquals("Real part not expected",-10.4, invertSign.getReal(),0);
        assertEquals("Imaginary part not expected",-10.6, invertSign.getImaginary(),0);
    }
    
    @Test
    public void testFloatWithDifferentNumberOfDecimal() {
        complexNumber1 = new ComplexNumber(10.465,10.64564);
        ComplexNumber invertSign = ComplexNumber.invertSign(complexNumber1);
        assertEquals("Real part not expected",-10.465, invertSign.getReal(),0);
        assertEquals("Imaginary part not expected",-10.64564, invertSign.getImaginary(),0);
    }
        
    @Test
    public void testOneFloatOneInt() {
        complexNumber1 = new ComplexNumber(10.4,10);
        ComplexNumber invertSign = ComplexNumber.invertSign(complexNumber1);
        assertEquals("Real part not expected",-10.4, invertSign.getReal(),0);
        assertEquals("Imaginary part not expected",-10, invertSign.getImaginary(),0);
    }

}
