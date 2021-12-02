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
public class ComplexNumberTest {
    
    private static ComplexNumber complexNumber1, complexNumber2;
 
    @Test
    public void Constructor() {        
        complexNumber1 = new ComplexNumber(10,2);
        assertNotNull (complexNumber1);
        assertEquals("Real part not expected", 10, complexNumber1.getReal(), 0);
        assertEquals("Imaginary part not expected", 2, complexNumber1.getImaginary(), 0);
        complexNumber2 = new ComplexNumber(10);
        assertNotNull(complexNumber2);
        assertEquals("Real part not expected", 10, complexNumber2.getReal(), 0);
        assertEquals("Imaginary part not expected", 0, complexNumber2.getImaginary(), 0);
    }

}
