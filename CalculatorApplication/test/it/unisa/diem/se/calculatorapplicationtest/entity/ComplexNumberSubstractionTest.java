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
        assertEquals("wrong complex number", new ComplexNumber(10,2), complexNumber1);
        complexNumber2 = new ComplexNumber(10);
        assertNotNull(complexNumber2);
        assertEquals("wrong real part", new ComplexNumber(10,0), complexNumber2);
    }
    
    
    @Test
    public void testSubstractionAllNegative() {
        complexNumber1 = new ComplexNumber(-10,-10);
        complexNumber2 = new ComplexNumber(-20,-20);
        assertEquals("substraction failed",new ComplexNumber(10, 10),ComplexNumber.substraction(complexNumber1,complexNumber2));
    }
    
    @Test
    public void testSumbstractionAllPositive() {
        complexNumber1 = new ComplexNumber(10,10);
        complexNumber2 = new ComplexNumber(20,20);
        assertEquals("substraction failed",new ComplexNumber(-10 , -10),ComplexNumber.substraction(complexNumber1,complexNumber2));
    }
       
    @Test
    public void testSubstractionOnePositiveOneNegative() {
        complexNumber1 = new ComplexNumber(10,10);
        complexNumber2 = new ComplexNumber(-20,-20);
        assertEquals("substraction failed",new ComplexNumber(30,30),ComplexNumber.substraction(complexNumber1,complexNumber2));
    }
    
    
    @Test
    public void testSubstractionOppositeSign() {
        complexNumber1 = new ComplexNumber(10,-10);
        complexNumber2 = new ComplexNumber(-20,+20);
        assertEquals("substraction failed",new ComplexNumber(30,-30),ComplexNumber.substraction(complexNumber1,complexNumber2));
    }
    
    
    
    @Test
    public void testSubstractionOneFloatOneInt() {
        complexNumber1 = new ComplexNumber(10.3, 10.4);
        complexNumber2 = new ComplexNumber(20, 20);
        assertEquals("substraction failed",new ComplexNumber(-9.7, -9.6),ComplexNumber.substraction(complexNumber1,complexNumber2));
    }
      
       @Test
    public void testSubstractionAllFloat(){
        complexNumber1 = new ComplexNumber(10.9, 10.6);
        complexNumber2 = new ComplexNumber(20.1, 20.1);
        assertEquals("substraction failed",new ComplexNumber(-9.2, -9.5),ComplexNumber.substraction(complexNumber1,complexNumber2));
    }
  
        
        
        
        
}
