/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.se.calculatorapplication.controller;

/**
 *
 * @author Giuseppe
 */
public class InvalidInputException extends Exception {

    /**
     * Creates a new instance of <code>InvalidInputException</code> without
     * detail message.
     */
    public InvalidInputException() {
    }

    /**
     * Constructs an instance of <code>InvalidInputException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidInputException(String msg) {
        super(msg);
    }
}
