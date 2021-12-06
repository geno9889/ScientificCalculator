/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.se.calculatorapplication.service;

/**
 *
 * @author ASUS
 */
public class NullVariableException extends Exception {

    /**
     * Creates a new instance of <code>NullVariableException</code> without
     * detail message.
     */
    public NullVariableException() {
    }

    /**
     * Constructs an instance of <code>NullVariableException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NullVariableException(String msg) {
        super(msg);
    }
}
