/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unisa.diem.se.calculatorapplication.entity;

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
        ComplexNumber temp = new ComplexNumber(0, 0);
        temp.real = c1.real + c2.real;
        temp.imaginary = c1.imaginary + c2.imaginary;
        return temp;
    }
    
    public static ComplexNumber substraction(ComplexNumber c1, ComplexNumber c2){
        ComplexNumber temp = new ComplexNumber(0, 0);
        temp.real = c1.real - c2.real;
        temp.imaginary = c1.imaginary - c2.imaginary;
        return temp;
    
    }
}