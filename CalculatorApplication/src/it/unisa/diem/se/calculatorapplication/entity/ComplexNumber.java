/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unisa.diem.se.calculatorapplication.entity;

import java.math.BigDecimal;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Giuseppe
 */

@Getter
@Setter
@EqualsAndHashCode
@ToString

public class ComplexNumber {

    private double real, imaginary;
    
    public ComplexNumber (double real) {
        this.real = real;
        this.imaginary = 0.0;
    }
    
    public ComplexNumber (double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }
     

    public static ComplexNumber sum(ComplexNumber c1, ComplexNumber c2){
        BigDecimal real1 = BigDecimal.valueOf(c1.real);
        BigDecimal real2 = BigDecimal.valueOf(c2.real);
        BigDecimal imaginary1 = BigDecimal.valueOf(c1.imaginary);
        BigDecimal imaginary2 = BigDecimal.valueOf(c2.imaginary);
        BigDecimal substractionReal = real1.add(real2);
        BigDecimal substractionImaginary = imaginary1.add(imaginary2);
        return new ComplexNumber(substractionReal.doubleValue(), substractionImaginary.doubleValue());
    
    }
    
    public static ComplexNumber substraction(ComplexNumber c1, ComplexNumber c2){
        BigDecimal real1 = BigDecimal.valueOf(c1.real);
        BigDecimal real2 = BigDecimal.valueOf(c2.real);
        BigDecimal imaginary1 = BigDecimal.valueOf(c1.imaginary);
        BigDecimal imaginary2 = BigDecimal.valueOf(c2.imaginary);
        BigDecimal substractionReal = real1.subtract(real2);
        BigDecimal substractionImaginary = imaginary1.subtract(imaginary2);
        return new ComplexNumber(substractionReal.doubleValue(), substractionImaginary.doubleValue());
    
    }
}