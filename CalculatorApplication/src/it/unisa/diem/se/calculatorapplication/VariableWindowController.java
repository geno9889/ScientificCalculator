/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.se.calculatorapplication;

import it.unisa.diem.se.calculatorapplication.controller.CalculatorController;
import it.unisa.diem.se.calculatorapplication.entity.ComplexNumber;
import it.unisa.diem.se.calculatorapplication.service.VariablesOperations;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Marco Graziano
 */
public class VariableWindowController {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TableView<Variable> variableTab;
    @FXML
    private TableColumn<Variable, Character> nameColumn;
    @FXML
    private TableColumn<Variable, String> valueColumn;
    
    private ObservableList<Variable> list1;
    private CalculatorController controller;

    /**
     * Initializes the controller class.
     * @param c
     */


    public void initialize(CalculatorController c) {
        controller = c;
        list1 = FXCollections.observableArrayList();
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        valueColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        variableTab.setItems(list1);
        setList();
    }

    
    private void setList(){
        VariablesOperations variables = (VariablesOperations)controller.getSingleOperations().get(2);
        for(int i = 97; i<=122; i++){   
            ComplexNumber value = variables.getVariables().get((char)i);
            if (value == null)
                value = new ComplexNumber(0);
            String s = String.valueOf(value.getReal());
            if(value.getImaginary()>=0)
                s += "+";
            s += String.valueOf(value.getImaginary()) + "j";
            list1.add(new Variable((char)i, s));
        }
    }
    
    
    @FXML
    private void reload(ActionEvent event) {
        list1.clear();
        setList();
    }
    
    
    public class Variable {
        private char name;
        private String value;

        public Variable(char name, String value) {
            this.name = name;
            this.value = value;
        }

        public char getName() {
            return name;
        }

        public String getValue() {
            return value;
        }

    }
}
