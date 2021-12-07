/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unisa.diem.se.calculatorapplication.controller;

import it.unisa.diem.se.calculatorapplication.entity.ComplexNumber;
import it.unisa.diem.se.calculatorapplication.entity.MathematicalException;
import it.unisa.diem.se.calculatorapplication.service.CustomOperations;
import it.unisa.diem.se.calculatorapplication.service.MathematicalOperations;
import it.unisa.diem.se.calculatorapplication.service.MultipleOperationsInterface;
import it.unisa.diem.se.calculatorapplication.service.NullVariableException;
import it.unisa.diem.se.calculatorapplication.service.SingleOperationsInterface;
import it.unisa.diem.se.calculatorapplication.service.StackBadSizeException;
import it.unisa.diem.se.calculatorapplication.service.StackOperations;
import it.unisa.diem.se.calculatorapplication.service.VariablesOperations;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
    
    private final Stack<ComplexNumber> stackNumbers;
    private final List<SingleOperationsInterface> singleOperations;
    private final List<MultipleOperationsInterface> multipleOperations;
    
    public CalculatorController(){
        stackNumbers = new Stack<>();
        singleOperations = new LinkedList<>();
        multipleOperations = new LinkedList<>();
        singleOperations.add(new MathematicalOperations());
        singleOperations.add(new StackOperations());
        singleOperations.add(new VariablesOperations());
        multipleOperations.add(new CustomOperations(singleOperations));
    }
    
    public void insertOrExecute(String input) throws InvalidInputException, StackBadSizeException, MathematicalException, NullVariableException{
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
        Pattern pattern = Pattern.compile("[+|-]?\\d+([.]\\d+)?(([+|-]\\d*)([.]\\d*)?[j])?$");  //pattern to match number(complex and not)
        Matcher matcher = pattern.matcher(input);
        if(matcher.matches()){
            char firstSign = input.charAt(0);
            int indexImaginaryPart;
            if(input.charAt(input.length()-1) == 'j'){   //with imaginary part
                if(firstSign == '+' || firstSign == '-'){    //search imaginary part in cases (±)a(±)bj or(±)bj
                    String inputWithoutFirstSign = input.substring(1, input.length());
                    indexImaginaryPart = searchIndexImaginaryPart(inputWithoutFirstSign) + 1;
                }
                else{  //search imaginary part in cases a(±)bj
                    indexImaginaryPart = searchIndexImaginaryPart(input);
                }
                if(indexImaginaryPart <= 0){  //insert if is bj
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
    
    //when press the button execute directly this method, without check the input string    
    public void executeSingleOperation(String input) throws StackBadSizeException, MathematicalException, NullVariableException{
        for(SingleOperationsInterface op : singleOperations){
            if(op.executeifExists(input, stackNumbers)){
                break;
            }
        }    
    }
    
    public void addCustomOperations(String inputName, String inputOperation) throws InvalidInputException{  
        CustomOperations custom = null;
        for(MultipleOperationsInterface op : multipleOperations){
            if(op instanceof CustomOperations){
                if(!op.addOperation(inputName, inputOperation)){
                    throw new InvalidInputException("Invalid sequence of operations or invalid operation name");
                }
                break;
            }
        }
    }
    
    public void modifyOperation(String newNameOperation, String oldNameOperation, String newOperation) throws InvalidInputException{ 
        CustomOperations custom = null;
        for(MultipleOperationsInterface op : multipleOperations){
            if(op instanceof CustomOperations){
                if(!op.modifyOperation(newNameOperation, oldNameOperation, newOperation)){
                    throw new InvalidInputException("Invalid sequence of operations or invalid operation name");
                }
                break;
            }
        }
    }
    
    public void deleteOperation(String nameOperation) throws InvalidInputException {
        CustomOperations custom = null;
        for(MultipleOperationsInterface op : multipleOperations){
            if(op instanceof CustomOperations){
                if(!op.deleteOperation(nameOperation)){
                    throw new InvalidInputException("Invalid operation name");
                }
                break;
            }
        }
    }
    
    public void executeMultipleOperation(String operation){
    }
    
    public void saveOperationToFile(File file){
        BufferedWriter bf = null;;
        try {
            bf = new BufferedWriter(new FileWriter(file));
            for (Map.Entry<String, String[]> entry : ((CustomOperations) multipleOperations.get(0)).getCustomOperations().entrySet()) {
                String s = "";
                for (int i = 0; i < entry.getValue().length; i++) {
                    s += entry.getValue()[i];
                    s += " ";
                }
                bf.write(entry.getKey() + ", " + s);
                bf.newLine();
            }
            bf.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bf.close();
            } catch (Exception e) {
            }
        }
    }

    
    public void reloadOperationFromFile(File file){
    }
    
}