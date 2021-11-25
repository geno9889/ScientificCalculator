/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unisa.diem.se.calculatorapplication.entity;

/**
 *
 * @author Giuseppe
 */
public class ComplexNumber {

    private float real, imaginary;
    
    public ComplexNumber (float real) {
        this.real = real;
        this.imaginary = 0;
    }
    
    public ComplexNumber (float real, float imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }
    
    public float getReal () {
        return (real);
    }
    
    public void setReal (float real) {
        this.real = real;
    }
    
    public float getImaginary () {
        return (imaginary);
    }
    
    public void setImaginary (float imaginary) {
        this.imaginary = imaginary;
    }
    
     
    /*public String toString () {
        String string;
        
        string = String.format ("%.2f + %.2fi", real, imaginary);
        
        return (string);
    }
    
    public void print () {
        System.out.println (toString ());
    }
    */
}
