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
     

  public static ComplexNumber sum(ComplexNumber number1, ComplexNumber number2){
        return new ComplexNumber(number1.real+number2.real, number1.imaginary+number2.imaginary);
    }
    
    public static ComplexNumber substraction(ComplexNumber number1, ComplexNumber number2){
        return new ComplexNumber(number1.real-number2.real, number1.imaginary-number2.imaginary);
    }
    
    public static ComplexNumber multiplication(ComplexNumber number1, ComplexNumber number2){
        return new ComplexNumber(((number1.real * number2.real) - (number1.imaginary * number2.imaginary)), (number1.real * number2.imaginary) + (number2.real * number1.imaginary));
    }
        
    public static ComplexNumber division(ComplexNumber number1, ComplexNumber number2) throws MathematicalException{
        if(number2.real == 0 && number2.imaginary == 0) throw new MathematicalException("Math error, a number can't be divided by zero");
        double realNumerator = (number1.real * number2.real) + (number1.imaginary * number2.imaginary);
        double imaginaryNumerator = (number1.imaginary * number2.real) - (number1.real * number2.imaginary);
        double denominator = (number2.real * number2.real) + (number2.imaginary * number2.imaginary);
        DecimalFormat df = new DecimalFormat("#.########");  //first 8 decimal digits
        String formatReal = df.format((realNumerator)/(denominator));
        String formatImaginary = df.format(imaginaryNumerator/denominator);
        double resultReal = Double.valueOf(formatReal.replace(",", "."));
        double resultImaginary = new Double(formatImaginary.replace(",", "."));
        return new ComplexNumber(resultReal, resultImaginary);
    }
    
    public static ComplexNumber invertSign(ComplexNumber number){
        if(number.real == 0 && number.imaginary==0)
            return new ComplexNumber(number.real, number.imaginary);
        if(number.imaginary==0)
            return new ComplexNumber(-1*(number.real), 0.0);
        if(number.real == 0)
            return new ComplexNumber(0, -1*(number.imaginary));
        else
            return new ComplexNumber(-1*(number.real), -1*(number.imaginary));
    }
    
     public static ComplexNumber squareRoot(ComplexNumber number) {
        
        double abs = 0;
        double arg = 0;
        double resultReal = number.real; 
        double resultImaginary = number.imaginary;  
           
        if (resultReal!=0 || resultImaginary!=0) {
            abs = Math.sqrt((resultReal*resultReal) + (resultImaginary*resultImaginary));
        }
        arg = Math.atan2(resultImaginary, resultReal);
        double fase = arg/2;
        DecimalFormat df = new DecimalFormat("#.########");  //first 8 decimal digits
        String formatReal = df.format(Math.sqrt(abs)*Math.cos(fase));
        String formatImaginary = df.format(Math.sqrt(abs)*Math.sin(fase));
        double realPart = Double.valueOf(formatReal.replace(",", "."));
        double imaginaryPart = new Double(formatImaginary.replace(",", "."));
        if(imaginaryPart == new Double(-0.0)){
        return new ComplexNumber(realPart, new Double(0.0));}
        return new ComplexNumber(realPart, imaginaryPart);}
    
}
