/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.se.calculatorapplication.entity;

/**
 *
 * @author Giuseppe
 */
public class MathematicalException extends Exception {

    /**
     * Creates a new instance of <code>MathematicalException</code> without
     * detail message.
     */
    public MathematicalException() {
    }

    /**
     * Constructs an instance of <code>MathematicalException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public MathematicalException(String msg) {
        super(msg);
    }
}
