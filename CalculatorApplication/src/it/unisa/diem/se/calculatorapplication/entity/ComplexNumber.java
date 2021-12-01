/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unisa.diem.se.calculatorapplication.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
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
        return new ComplexNumber(c1.real+c2.real, c1.imaginary+c2.imaginary);
    }
    
    public static ComplexNumber substraction(ComplexNumber c1, ComplexNumber c2){
        return new ComplexNumber(c1.real-c2.real, c1.imaginary-c2.imaginary);
    }
    
    public static ComplexNumber multiplication(ComplexNumber c1, ComplexNumber c2){
        return new ComplexNumber(((c1.real * c2.real) - (c1.imaginary * c2.imaginary)), (c1.real * c2.imaginary) + (c2.real * c1.imaginary));
    }
        
    public static ComplexNumber division(ComplexNumber c1, ComplexNumber c2) throws MathematicalException{
        if(c2.real == 0 && c2.imaginary == 0) throw new MathematicalException("Math error, a number can't be divided by zero");
        double realNumerator = (c1.real * c2.real) + (c1.imaginary * c2.imaginary);
        double realDenominator = (c2.real * c2.real) + (c2.imaginary * c2.imaginary);
        double imaginaryNumerator = (c1.imaginary * c2.real) - (c1.real * c2.imaginary);
        double imaginaryDenominator = (c2.real * c2.real) + (c2.imaginary * c2.imaginary);
        DecimalFormat df = new DecimalFormat("#.########");  //first 8 decimal digits
        String formatReal = df.format((realNumerator)/(realDenominator));
        String formatImaginary = df.format((imaginaryNumerator)/(imaginaryDenominator));
        double resultReal = Double.valueOf(formatReal.replace(",", "."));
        double resultImaginary = new Double(formatImaginary.replace(",", "."));
        return new ComplexNumber(resultReal, resultImaginary);
    }   
}