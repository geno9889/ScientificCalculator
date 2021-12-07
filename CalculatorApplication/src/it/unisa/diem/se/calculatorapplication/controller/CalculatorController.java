/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unisa.diem.se.calculatorapplication.controller;

import it.unisa.diem.se.calculatorapplication.entity.ComplexNumber;
import it.unisa.diem.se.calculatorapplication.entity.MathematicalException;
import it.unisa.diem.se.calculatorapplication.service.CustomOperations;
import it.unisa.diem.se.calculatorapplication.service.MathematicalOperations;
import it.unisa.diem.se.calculatorapplication.service.NullVariableException;
import it.unisa.diem.se.calculatorapplication.service.SingleOperationsInterface;
import it.unisa.diem.se.calculatorapplication.service.StackBadSizeException;
import it.unisa.diem.se.calculatorapplication.service.StackOperations;
import it.unisa.diem.se.calculatorapplication.service.VariablesOperations;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
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
    private final CustomOperations customOperations;
    
    public CalculatorController(){
        stackNumbers = new Stack<>();
        singleOperations = new LinkedList<>();
        customOperations = new CustomOperations(singleOperations);
        singleOperations.add(new MathematicalOperations());
        singleOperations.add(new StackOperations());
        singleOperations.add(new VariablesOperations());
    }
    
    public void insertOrExecute(String input) throws InvalidInputException, StackBadSizeException, MathematicalException, NullVariableException{
        if(input == null || input.isEmpty()){
            throw new InvalidInputException("Input cannot be empty");
        }
        input = input.replaceAll("\\s", "");
        boolean existsOperation = false;  
        if(!insertComplexNumber(input)){
            for(SingleOperationsInterface op : singleOperations){
                if(op.executeIfExists(input, stackNumbers)){
                    existsOperation = true;
                    break;
                }
            }
            if(existsOperation == false){
                    if(customOperations.executeIfExists(input, stackNumbers)){
                        existsOperation = true;
                    }
                if(existsOperation == false){
                    throw new InvalidInputException("Invalid input");
                }
            }
        }
    }

    private boolean insertComplexNumber(String input){
        Pattern pattern = Pattern.compile("[+|-]?\\d+([.]\\d+)?([+|-]\\d*([.]?\\d+)?[j])?$");  //pattern to match number(complex and not)
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
            if(op.executeIfExists(input, stackNumbers)){
                break;
            }
        }    
    }
    
    public void addCustomOperations(String inputName, String inputOperation) throws InvalidInputException{
        if(!customOperations.addOperation(inputName, inputOperation)){
            throw new InvalidInputException("Possible causes:\n"
                    + "- calculator doesn't support one operation in sequence\n "
                    + "- name of custom operation is equal to name of an already existing operation\n"
                    + "- operation name contains the character \",\" (it's not allowed)");
        }
    }
    
    public void modifyCustomOperation(String newNameOperation, String oldNameOperation, String newOperation) throws InvalidInputException{ 
        if(!customOperations.modifyOperation(newNameOperation, oldNameOperation, newOperation)){
            throw new InvalidInputException("Possible causes:\n"
                    + "- new name of custom operation is equal to name of an already existing operation\n"
                    + "- calculator doesn't support one operation in sequence\n "
                    + "- operation name contains the character \",\" (it's not allowed)");
        }
    }
    
    public void deleteCustomOperation(String nameOperation) throws InvalidInputException {
        if(!customOperations.deleteOperation(nameOperation)){
            throw new InvalidInputException("The operation name doesn't exist in data.");
        }      
    }
    
    public void executeMultipleOperation(String input) throws StackBadSizeException, MathematicalException, NullVariableException{
        customOperations.executeIfExists(input, stackNumbers);
    }
    
    public void saveCustomOperationToFile(File file) throws IOException{
        BufferedWriter bf = null;
        bf = new BufferedWriter(new FileWriter(file));
        for (Map.Entry<String, String[]> entry : (customOperations).getMultipleOperations().entrySet()) {
            String s = "";
            for (int i = 0; i < entry.getValue().length; i++) {
                s += " ";
                s += entry.getValue()[i];
            }
            bf.write(entry.getKey() + "," + s);
            bf.newLine();
        }
        bf.flush();

        bf.close();
    }

    
    public void reloadCustomOperationFromFile(File file) throws IOException{
        HashMap<String, String[]> multipleOperations = customOperations.getMultipleOperations();
        multipleOperations.clear();

        BufferedReader bf = new BufferedReader(new FileReader(file));
        Scanner scanner = new Scanner(bf);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] pairNameOperations = line.split(",");
            multipleOperations.put(pairNameOperations[0], pairNameOperations[1].split("\\s+"));
        }
        bf.close();
    }
    
}
