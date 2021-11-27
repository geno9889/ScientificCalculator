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
        assertEquals("Wrong complex number", new ComplexNumber(1,2), complexNumber1);
        complexNumber2 = new ComplexNumber(1);
        assertNotNull(complexNumber2);
        assertEquals("Wrong real number", new ComplexNumber(1,0), complexNumber2);
    }
    
    
     @Test
    public void testMultiplicationAllNegativeValue() {
        complexNumber1 = new ComplexNumber(-1,-1);
        complexNumber2 = new ComplexNumber(-2,-2);
        assertEquals("Multiplication failed",new ComplexNumber(0,4),ComplexNumber.multiplication(complexNumber1,complexNumber2));
    }
    
    @Test
    public void testMultiplicationRealPartIsZero() {
        complexNumber1 = new ComplexNumber(0,-1);
        complexNumber2 = new ComplexNumber(-2,-2);
        assertEquals("Multiplication failed",new ComplexNumber(-2,2),ComplexNumber.multiplication(complexNumber1,complexNumber2));
    }
       
    @Test
    public void testMultiplicationRealPartIsPositive() {
        complexNumber1 = new ComplexNumber(1,-1);
        complexNumber2 = new ComplexNumber(-2,-2);
        assertEquals("Multiplication failed",new ComplexNumber(-4,0),ComplexNumber.multiplication(complexNumber1,complexNumber2));
    }
    
    @Test
    public void testMultiplicationImaginaryPartIsZero() {
        complexNumber1 = new ComplexNumber(-1,0);
        complexNumber2 = new ComplexNumber(-2,-2);
        assertEquals("Multiplication failed",new ComplexNumber(2,2),ComplexNumber.multiplication(complexNumber1,complexNumber2));
    }
    
    @Test
    public void testMultiplicationImaginaryPartIsPositive() {
        complexNumber1 = new ComplexNumber(-1,1);
        complexNumber2 = new ComplexNumber(-2,-2);
        assertEquals("Multiplication failed",new ComplexNumber(4,0),ComplexNumber.multiplication(complexNumber1,complexNumber2));
    }
    
    @Test
    public void testMultiplicationRealPartIsZeroSecondComplexNumber() {
        complexNumber1 = new ComplexNumber(-1,-1);
        complexNumber2 = new ComplexNumber(0,-2);
        assertEquals("Multiplication failed",new ComplexNumber(-2,2),ComplexNumber.multiplication(complexNumber1,complexNumber2));
    }
    
    @Test
    public void testMultiplicationRealPartIsPositiveSecondComplexNumber() {
        complexNumber1 = new ComplexNumber(-1,-1);
        complexNumber2 = new ComplexNumber(1,-2);
        assertEquals("Multiplication failed",new ComplexNumber(-3,1),ComplexNumber.multiplication(complexNumber1,complexNumber2));
    }
    
    @Test
    public void testMultiplicationImaginaryPartIsZeroSecondComplexNumber() {
        complexNumber1 = new ComplexNumber(-1,-1);
        complexNumber2 = new ComplexNumber(-2,0);
        assertEquals("Multiplication failed",new ComplexNumber(2,2),ComplexNumber.multiplication(complexNumber1,complexNumber2));
    }
    
    @Test
    public void testMultiplicationImaginaryPartIsPositiveSecondComplexNumber() {
        complexNumber1 = new ComplexNumber(-1,-1);
        complexNumber2 = new ComplexNumber(-2,2);
        assertEquals("Multiplication failed",new ComplexNumber(4,0),ComplexNumber.multiplication(complexNumber1,complexNumber2));
    }
      
    @Test
    public void testMultiplicationAllPositive() {
        complexNumber1 = new ComplexNumber(1,1);
        complexNumber2 = new ComplexNumber(2,2);
        assertEquals("Multiplication failed",new ComplexNumber(0,4),ComplexNumber.multiplication(complexNumber1,complexNumber2));
    }
    
    @Test
    public void testMultiplicationFirstComplexNumberIsAllPositive() {
        complexNumber1 = new ComplexNumber(1,1);
        complexNumber2 = new ComplexNumber(-2,-2);
        assertEquals("Multiplication failed",new ComplexNumber(0,-4),ComplexNumber.multiplication(complexNumber1,complexNumber2));
    }
    
    @Test
    public void testMultiplicationSecondComplexNumberIsAllPositive() {
        complexNumber1 = new ComplexNumber(-1,-1);
        complexNumber2 = new ComplexNumber(2,2);
        assertEquals("Multiplication failed",new ComplexNumber(0,-4),ComplexNumber.multiplication(complexNumber1,complexNumber2));
    }
    
    @Test
    public void testMultiplicationFirstComplexZero() {
        complexNumber1 = new ComplexNumber(0,0);
        complexNumber2 = new ComplexNumber(2,2);
        assertEquals("Multiplication failed",new ComplexNumber(0,0),ComplexNumber.multiplication(complexNumber1,complexNumber2));
    }
    
    @Test
    public void testMultiplicationSecondComplexZero() {
        complexNumber1 = new ComplexNumber(2,2);
        complexNumber2 = new ComplexNumber(0,0);
        assertEquals("Multiplication failed",new ComplexNumber(0,0),ComplexNumber.multiplication(complexNumber1,complexNumber2));
    }
    
    @Test
    public void testMultiplicationAllComplexZero() {
        complexNumber1 = new ComplexNumber(0,0);
        complexNumber2 = new ComplexNumber(0,0);
        assertEquals("Multiplication failed",new ComplexNumber(0,0),ComplexNumber.multiplication(complexNumber1,complexNumber2));
    }
    
    @Test
    public void testMultiplicationAllDouble() {
        complexNumber1 = new ComplexNumber(1.2,2.2);
        complexNumber2 = new ComplexNumber(2.5,0.8);
        assertEquals("Multiplication failed",new ComplexNumber(1.24,6.46),ComplexNumber.multiplication(complexNumber1,complexNumber2));
    }
    
}