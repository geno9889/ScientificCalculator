/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
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
 * @author ASUS
 */
public class ComplexNumberSumTest {
    
    private static ComplexNumber complexNumber1, complexNumber2;
         
    @BeforeClass
    public static void setUp() {        
        complexNumber1 = new ComplexNumber(10,2);
        assertNotNull (complexNumber1);
        assertEquals("Real part not expected", 10, complexNumber1.getReal(),0);
        assertEquals("Imaginary part not expected", 2, complexNumber1.getImaginary(),0);
        complexNumber2 = new ComplexNumber(10);
        assertNotNull(complexNumber2);
        assertEquals("Real part not expected", 10, complexNumber2.getReal(),0);
        assertEquals("Imaginary part not expected", 0, complexNumber2.getImaginary(),0);
    }
    
    
    @Test
    public void testAllNegative() {
        complexNumber1 = new ComplexNumber(-10,-10);
        complexNumber2 = new ComplexNumber(-20,-20);
        ComplexNumber sum = ComplexNumber.sum(complexNumber1,complexNumber2);
        assertEquals("Real part not expected",-30, sum.getReal(),0);
        assertEquals("Imaginary part not expected",-30, sum.getImaginary(),0);
    }
    
    @Test
    public void testAllPositive() {
        complexNumber1 = new ComplexNumber(10,10);
        complexNumber2 = new ComplexNumber(20,20);
        ComplexNumber sum = ComplexNumber.sum(complexNumber1,complexNumber2);
        assertEquals("Real part not expected",30, sum.getReal(),0);
        assertEquals("Imaginary part not expected",30, sum.getImaginary(),0);
    }
    
    @Test
    public void testOnePositiveOneNegative() {
        complexNumber1 = new ComplexNumber(10,10);
        complexNumber2 = new ComplexNumber(-20,-20);
        ComplexNumber sum = ComplexNumber.sum(complexNumber1,complexNumber2);
        assertEquals("Real part not expected",-10, sum.getReal(),0);
        assertEquals("Imaginary part not expected",-10, sum.getImaginary(),0);
    }

    @Test
    public void testOppositeSign() {
        complexNumber1 = new ComplexNumber(10,-10);
        complexNumber2 = new ComplexNumber(-20,20);
        ComplexNumber sum = ComplexNumber.sum(complexNumber1,complexNumber2);
        assertEquals("Real part not expected",-10, sum.getReal(),0);
        assertEquals("Imaginary part not expected",10, sum.getImaginary(),0);
    }
    
    @Test
    public void testAllFloat() {
        complexNumber1 = new ComplexNumber(10.1,10.1);
        complexNumber2 = new ComplexNumber(20.1,20.1);
        ComplexNumber sum = ComplexNumber.sum(complexNumber1,complexNumber2);
        assertEquals("Real part not expected",30.2, sum.getReal(),0);
        assertEquals("Imaginary part not expected",30.2, sum.getImaginary(),0);
    }
    
    @Test
    public void testFloatWithDifferentNumberOfDecimal() {
        complexNumber1 = new ComplexNumber(10.01,10.1);
        complexNumber2 = new ComplexNumber(20.1,20.001);
        ComplexNumber sum = ComplexNumber.sum(complexNumber1,complexNumber2);
        assertEquals("Real part not expected",30.11, sum.getReal(),0);
        assertEquals("Imaginary part not expected",30.101, sum.getImaginary(),0);
    }
        
    @Test
    public void testOneFloatOneInt() {
        complexNumber1 = new ComplexNumber(10,10);
        complexNumber2 = new ComplexNumber(20.1,20.01);
        ComplexNumber sum = ComplexNumber.sum(complexNumber1,complexNumber2);
        assertEquals("Real part not expected",30.1, sum.getReal(),0);
        assertEquals("Imaginary part not expected",30.01, sum.getImaginary(),0);
    }

}
