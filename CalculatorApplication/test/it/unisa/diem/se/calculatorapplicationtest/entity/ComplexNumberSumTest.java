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
        assertEquals("wrong complex number", new ComplexNumber(10,2), complexNumber1);
        complexNumber2 = new ComplexNumber(10);
        assertNotNull(complexNumber2);
        assertEquals("wrong real part", new ComplexNumber(10,0), complexNumber2);
    }
    
    
    @Test
    public void testSumAllNegativeValue() {
        complexNumber1 = new ComplexNumber(-10,-10);
        complexNumber2 = new ComplexNumber(-20,-20);
        assertEquals("sum failed",new ComplexNumber(-30,-30),ComplexNumber.sum(complexNumber1,complexNumber2));
    }
    
    @Test
    public void testSumRealPartIsZero() {
        complexNumber1 = new ComplexNumber(0,-10);
        complexNumber2 = new ComplexNumber(-20,-20);
        assertEquals("sum failed",new ComplexNumber(-20,-30),ComplexNumber.sum(complexNumber1,complexNumber2));
    }
       
    @Test
    public void testSumRealPartIsPositive() {
        complexNumber1 = new ComplexNumber(10,-10);
        complexNumber2 = new ComplexNumber(-20,-20);
        assertEquals("sum failed",new ComplexNumber(-10,-30),ComplexNumber.sum(complexNumber1,complexNumber2));
    }
    
    @Test
    public void testSumImaginaryPartIsZero() {
        complexNumber1 = new ComplexNumber(-10,0);
        complexNumber2 = new ComplexNumber(-20,-20);
        assertEquals("sum failed",new ComplexNumber(-30,-20),ComplexNumber.sum(complexNumber1,complexNumber2));
    }
    
    @Test
    public void testSumImaginaryPartIsPositive() {
        complexNumber1 = new ComplexNumber(-10,10);
        complexNumber2 = new ComplexNumber(-20,-20);
        assertEquals("sum failed",new ComplexNumber(-30,-10),ComplexNumber.sum(complexNumber1,complexNumber2));
    }
    
    @Test
    public void testSumRealPartIsZeroSecondComplexNumber() {
        complexNumber1 = new ComplexNumber(-10,-10);
        complexNumber2 = new ComplexNumber(0,-20);
        assertEquals("sum failed",new ComplexNumber(-10,-30),ComplexNumber.sum(complexNumber1,complexNumber2));
    }
    
    @Test
    public void testSumRealPartIsPositiveSecondComplexNumber() {
        complexNumber1 = new ComplexNumber(-10,-10);
        complexNumber2 = new ComplexNumber(10,-20);
        assertEquals("sum failed",new ComplexNumber(0,-30),ComplexNumber.sum(complexNumber1,complexNumber2));
    }
    
    @Test
    public void testSumImaginaryPartIsZeroSecondComplexNumber() {
        complexNumber1 = new ComplexNumber(-10,-10);
        complexNumber2 = new ComplexNumber(-20,0);
        assertEquals("sum failed",new ComplexNumber(-30,-10),ComplexNumber.sum(complexNumber1,complexNumber2));
    }
    
    @Test
    public void testSumImaginaryPartIsPositiveSecondComplexNumber() {
        complexNumber1 = new ComplexNumber(-10,-10);
        complexNumber2 = new ComplexNumber(-20,20);
        assertEquals("sum failed",new ComplexNumber(-30,10),ComplexNumber.sum(complexNumber1,complexNumber2));
    }
      
    @Test
    public void testSumAllPositive() {
        complexNumber1 = new ComplexNumber(10,10);
        complexNumber2 = new ComplexNumber(20,20);
        assertEquals("sum failed",new ComplexNumber(30,30),ComplexNumber.sum(complexNumber1,complexNumber2));
    }
    
    @Test
    public void testSumFirstComplexNumberIsAllPositive() {
        complexNumber1 = new ComplexNumber(10,10);
        complexNumber2 = new ComplexNumber(-20,-20);
        assertEquals("sum failed",new ComplexNumber(-10,-10),ComplexNumber.sum(complexNumber1,complexNumber2));
    }
        
        
        
        
}
