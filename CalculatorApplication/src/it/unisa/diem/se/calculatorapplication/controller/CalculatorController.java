/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unisa.diem.se.calculatorapplication.controller;

import it.unisa.diem.se.calculatorapplication.entity.ComplexNumber;
import it.unisa.diem.se.calculatorapplication.service.MathematicalOperations;
import it.unisa.diem.se.calculatorapplication.service.SingleOperationsInterface;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
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
public class CalculatorController {
    
    private Stack<ComplexNumber> stackNumbers;
    private List<SingleOperationsInterface> singleOperations;
    
    public CalculatorController(){
        stackNumbers = new Stack<>();
        singleOperations = new LinkedList<>();
        singleOperations.add(new MathematicalOperations());
    }
    
    public boolean insertOrExecute(String input){
        return true;
    }
    
}

