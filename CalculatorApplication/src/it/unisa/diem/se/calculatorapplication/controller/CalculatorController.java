/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unisa.diem.se.calculatorapplication.controller;

import it.unisa.diem.se.calculatorapplication.entity.ComplexNumber;
import it.unisa.diem.se.calculatorapplication.entity.MathematicalException;
import it.unisa.diem.se.calculatorapplication.service.MathematicalOperations;
import it.unisa.diem.se.calculatorapplication.service.SingleOperationsInterface;
import it.unisa.diem.se.calculatorapplication.service.StackBadSizeException;
import it.unisa.diem.se.calculatorapplication.service.StackOperations;
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
        singleOperations.add(new StackOperations());
    }
    
    public void insertOrExecute(String input) throws InvalidInputException, StackBadSizeException, MathematicalException{
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
            if(existsOperation == false){
                throw new InvalidInputException("Invalid input");
            }
        }
    }

    private boolean insertComplexNumber(String input){
        Pattern pattern = Pattern.compile("([+|-]\\d+)?\\d*([.]\\d+)?[j]?(([+|-]\\d*)([.]\\d*)?[j])?$");  //pattern to match number(complex and not)
        Matcher matcher = pattern.matcher(input);
        if(matcher.matches()){
            char firstSign = input.charAt(0);
            int indexImaginaryPart;
            if(input.charAt(input.length()-1) == 'j'){   //with imaginary part
                if(input.length() == 1 || (input.length() == 2 && firstSign == '+')){   //insert if is j or +j
                    return addDoubleToStack("0", "1");
                }
                if(input.length() == 2 &&  firstSign == '-'){  //insert if is -j
                    return addDoubleToStack("0", "-1");
                }
                if(firstSign == '+' || firstSign == '-'){    //search imaginary part in cases (±)a(±)bj or(±)bj
                    String inputWithoutFirstSign = input.substring(1, input.length());
                    indexImaginaryPart = searchIndexImaginaryPart(inputWithoutFirstSign) + 1;
                }
                else{  //search imaginary part in cases a(±)bj or bj
                    indexImaginaryPart = searchIndexImaginaryPart(input);
                }
                if(indexImaginaryPart <= 0){  //insert if is bj or (±)bj
                    return addDoubleToStack("0", input.substring(0, input.length()-1));
                }
                String realPart = input.substring(0, indexImaginaryPart);
                String imaginaryPart = input.substring(indexImaginaryPart, input.length()-1);
                if(imaginaryPart.length() == 1){  //insert if is (±)a(±)j or a(±)j
                    return addDoubleToStack(realPart, imaginaryPart.concat("1"));
                }
                return addDoubleToStack(realPart, imaginaryPart);  //insert if is (±)a(±)bj or a(±)bj
            }
            else{  //with only real part
                return addDoubleToStack(input, "0");
            }
        }
        return false;
    }
    
    private boolean addDoubleToStack(String real, String imaginary){
        stackNumbers.add(new ComplexNumber(new Double(real), new Double(imaginary)));
        return true;
    }
    
    private int searchIndexImaginaryPart(String inputWithoutRealSign){
        int indexImaginaryPart = -1;
        if(inputWithoutRealSign.contains("+")){
            indexImaginaryPart = inputWithoutRealSign.indexOf("+");
        }
        else if(inputWithoutRealSign.contains("-")){
            indexImaginaryPart = inputWithoutRealSign.indexOf("-");
        }
        return indexImaginaryPart;
    }
    
        
    public void execute(String input) throws StackBadSizeException, MathematicalException{
        for(SingleOperationsInterface op : singleOperations){
            op.executeifExists(input, stackNumbers);
        }    
    }
}