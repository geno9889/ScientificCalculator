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
    public void testSumAllNegative() {
        complexNumber1 = new ComplexNumber(-10,-10);
        complexNumber2 = new ComplexNumber(-20,-20);
        assertEquals("sum failed",new ComplexNumber(-30,-30),ComplexNumber.sum(complexNumber1,complexNumber2));
    }
    
    @Test
    public void testSumAllPositive() {
        complexNumber1 = new ComplexNumber(10,10);
        complexNumber2 = new ComplexNumber(20,20);
        assertEquals("sum failed",new ComplexNumber(30,30),ComplexNumber.sum(complexNumber1,complexNumber2));
    }
    
    @Test
    public void testSumOnePositiveOneNegative() {
        complexNumber1 = new ComplexNumber(10,10);
        complexNumber2 = new ComplexNumber(-20,-20);
        assertEquals("sum failed",new ComplexNumber(-10,-10),ComplexNumber.sum(complexNumber1,complexNumber2));
    }

    @Test
    public void testSumOppositeSign() {
        complexNumber1 = new ComplexNumber(10,-10);
        complexNumber2 = new ComplexNumber(-20,20);
        assertEquals("sum failed",new ComplexNumber(-10,10),ComplexNumber.sum(complexNumber1,complexNumber2));
    }
    
    @Test
    public void testSumAllFloat() {
        complexNumber1 = new ComplexNumber(10.1,10.1);
        complexNumber2 = new ComplexNumber(20.1,20.1);
        assertEquals("sum failed",new ComplexNumber(30.2,30.2),ComplexNumber.sum(complexNumber1,complexNumber2));
    }
    
    @Test
    public void testSumFloatWithDifferentNumberOfDecimal() {
        complexNumber1 = new ComplexNumber(10.01,10.1);
        complexNumber2 = new ComplexNumber(20.1,20.001);
        assertEquals("sum failed",new ComplexNumber(30.11,30.101),ComplexNumber.sum(complexNumber1,complexNumber2));
    }
        
    @Test
    public void testSumOneFloatOneInt() {
        complexNumber1 = new ComplexNumber(10,10);
        complexNumber2 = new ComplexNumber(20.1,20.01);
        assertEquals("sum failed",new ComplexNumber(30.1,30.01),ComplexNumber.sum(complexNumber1,complexNumber2));
    }

}
