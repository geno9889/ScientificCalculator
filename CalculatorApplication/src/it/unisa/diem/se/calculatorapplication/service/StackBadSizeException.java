/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.se.calculatorapplication.service;

/**
 *
 * @author marcograziano
 */
public class StackBadSizeException extends Exception {

    /**
     * Creates a new instance of <code>StackBadSizeException</code> without
     * detail message.
     */
    public StackBadSizeException() {
    }

    /**
     * Constructs an instance of <code>StackBadSizeException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public StackBadSizeException(String msg) {
        super(msg);
    }
}
