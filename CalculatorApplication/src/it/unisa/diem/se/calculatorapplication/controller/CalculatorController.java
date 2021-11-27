/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unisa.diem.se.calculatorapplication.controller;

import it.unisa.diem.se.calculatorapplication.entity.ComplexNumber;
import it.unisa.diem.se.calculatorapplication.service.MathematicalOperations;
import it.unisa.diem.se.calculatorapplication.service.SingleOperationsInterface;
import it.unisa.diem.se.calculatorapplication.service.StackBadSizeException;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    
    public void insertOrExecute(String input) throws InvalidInputException, StackBadSizeException{
        if(input == null || input.isEmpty()){
            throw new InvalidInputException("Input cannot be empty");
        }
        input = input.replaceAll("\\s", "");
        boolean existsOperation = false;
        if(!insertComplexNumber(input)){
            for(SingleOperationsInterface op : singleOperations){
                if(op.executeifExists(input, stackNumbers)){
                    existsOperation = true;
                    break;
                }
            }
            if(!existsOperation){
                throw new InvalidInputException("Operation not supported");
            }
            throw new InvalidInputException("Invalid format of complex number");
        }
    }
    
    private boolean insertComplexNumber(String input){
        Pattern pattern = Pattern.compile("[+|-]?\\d+([.]\\d+)?(([+|-]\\d+)([.]\\d*)?[j])?$");
        Matcher matcher = pattern.matcher(input);
        if(matcher.matches()){
            char signReal = input.charAt(0);
            String inputWithoutSignReal;
            int indexImaginaryPart;
            String realPart;
            String imaginaryPart;
            if(input.charAt(input.length()-1) == 'j'){
                if(signReal == '+' || signReal == '-'){
                    inputWithoutSignReal = input.substring(1, input.length());
                    if(inputWithoutSignReal.contains("+")){
                        indexImaginaryPart = inputWithoutSignReal.indexOf("+") + 1;
                    }
                    else{
                        indexImaginaryPart = inputWithoutSignReal.indexOf("-") + 1;
                    }
                }
                else{
                    if(input.contains("+")){
                        indexImaginaryPart = input.indexOf("+");
                    }
                    else{
                        indexImaginaryPart = input.indexOf("-");
                    }
                }
                realPart = input.substring(0, indexImaginaryPart);
                imaginaryPart = input.substring(indexImaginaryPart, input.length()-1);
                stackNumbers.add(new ComplexNumber(new Double(realPart), new Double(imaginaryPart)));
                return true;
            }
            else{
                stackNumbers.add(new ComplexNumber(new Double(input), new Double(0)));
                return true;
            }
            
        }
        return false;
    }
    
    
}

