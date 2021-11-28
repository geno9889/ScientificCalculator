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
            if(existsOperation == false){
                throw new InvalidInputException("Invalid input");
            }
        }
    }
    
    private boolean insertComplexNumber(String input){
        Pattern pattern = Pattern.compile("([+|-]\\d+)?\\d*([.]\\d+)?[j]?(([+|-]\\d*)([.]\\d*)?[j])?$");  //pattern to match complex number
        Matcher matcher = pattern.matcher(input);
        if(matcher.matches()){
            char firstSign = input.charAt(0);
            String inputWithoutFirstSign;
            int indexImaginaryPart;
            String realPart;
            String imaginaryPart;
            if(input.charAt(input.length()-1) == 'j'){   //checking if is complete complex number or only real part
                if(input.length() == 1){   //only j
                    return addDoubleToStack("0", "1");
                }
                if(input.length() == 2 && (firstSign == '+' || firstSign == '-')){  //only j with sign
                    if(firstSign == '+'){
                        return addDoubleToStack("0", "1");
                    }
                    else{
                        return addDoubleToStack("0", "-1");
                    }
                }
                if(firstSign == '+' || firstSign == '-'){    //complex number with imaginary part and/or real part(both signed)
                    inputWithoutFirstSign = input.substring(1, input.length());
                    if(inputWithoutFirstSign.contains("+")){
                        indexImaginaryPart = inputWithoutFirstSign.indexOf("+") + 1;
                    }
                    else if(inputWithoutFirstSign.contains("-")){
                        indexImaginaryPart = inputWithoutFirstSign.indexOf("-") + 1;
                    }
                    else{  //only imaginary part signed
                        return addDoubleToStack("0", input.substring(0, input.length()-1));
                    }
                }
                else{  //complex number with imaginary part(signed) and/or real part(unsigned)
                    if(input.contains("+")){
                        indexImaginaryPart = input.indexOf("+");
                    }
                    else if(input.contains("-")){
                        indexImaginaryPart = input.indexOf("-");
                    }
                    else{ //only imaginary part unsigned
                        return addDoubleToStack("0", input.substring(0, input.length()-1));
                    }
                }
                realPart = input.substring(0, indexImaginaryPart);
                imaginaryPart = input.substring(indexImaginaryPart, input.length()-1);
                if(imaginaryPart.length() == 1){  //complex number with real part and imaginary part with only j
                    return addDoubleToStack(realPart, imaginaryPart.concat("1"));
                }
                return addDoubleToStack(realPart, imaginaryPart);
            }
            else{
                return addDoubleToStack(input, "0");
            }
        }
        return false;
    }
    
    private boolean addDoubleToStack(String real, String imaginary){
        stackNumbers.add(new ComplexNumber(new Double(real), new Double(imaginary)));
        return true;
    }
    

}

