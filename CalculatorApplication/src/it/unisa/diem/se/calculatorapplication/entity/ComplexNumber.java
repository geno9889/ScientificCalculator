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

    double real, img;
	
    ComplexNumber(double r, double i){
	this.real = r;
	this.img = i;
   }

    public double getReal() {
        return real;
    }

    public double getImg() {
        return img;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public void setImg(double img) {
        this.img = img;
    }
    
    
}
