/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */

package it.unisa.diem.se.calculatorapplication;

import it.unisa.diem.se.calculatorapplication.controller.CalculatorController;
import it.unisa.diem.se.calculatorapplication.controller.InvalidInputException;
import it.unisa.diem.se.calculatorapplication.entity.ComplexNumber;
import it.unisa.diem.se.calculatorapplication.entity.MathematicalException;
import it.unisa.diem.se.calculatorapplication.service.StackBadSizeException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


/**
 *
 * @author Giuseppe
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    private ListView<String> listView;
    @FXML
    private TextField txtfield;
    @FXML
    private Button insertbtn;
    
    private ObservableList<String> stackNumbers;
    @FXML
    private ListView<String> list;
    
    private CalculatorController c;
    @FXML
    private Label resultLabel;
    @FXML
    private MenuButton variableSetter;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         stackNumbers = FXCollections.observableArrayList();
         stackNumbers.add("");
         list.setItems(stackNumbers);
         insertbtn.disableProperty().bind(Bindings.isEmpty(txtfield.textProperty()));
         c = new CalculatorController();
         
    }    

    @FXML
    private void insertValue(ActionEvent event) {
        try {
            c.insertOrExecute(txtfield.getText());
            if(!c.getStackNumbers().empty()){
            stackNumbers.clear();
            for(int i=c.getStackNumbers().size()-1; i>=0; i--){     // recreating stack with new elements
                String s = String.valueOf(c.getStackNumbers().elementAt(i).getReal());
                if(c.getStackNumbers().elementAt(i).getImaginary()>=0){
                    s += "+";
                }
                s += String.valueOf(c.getStackNumbers().elementAt(i).getImaginary()) + "j";
                stackNumbers.add(s);
            }
        }
        } catch (InvalidInputException | StackBadSizeException | MathematicalException ex) {
            Alert al= new Alert(Alert.AlertType.ERROR);
            al.setTitle("Error");
            al.setHeaderText("Input error");
            al.setContentText(ex.getMessage());
            al.show();
        }
        this.clearAll(event);
        resultLabel.setText(stackNumbers.get(0));
    }


    @FXML
    private void insertValue2(ActionEvent event) throws MathematicalException {
        this.insertValue(event);
    }

    @FXML
    private void deleteLastCharacter(ActionEvent event) {      // button backspace function
        if (txtfield.getLength() != 0)          //avoid lenght exception
            txtfield.setText(txtfield.getText(0, txtfield.getLength()-1));
    }
    
    @FXML
    private void clearAll(ActionEvent event) {      // button canc function
        txtfield.clear();
        resultLabel.setText(null);
    }


    @FXML
    private void write(ActionEvent event) {    // number, "j", "." buttons fiunction
        Button sourceButton = (Button) event.getSource();       //understand which button is clicked 
        txtfield.setText(txtfield.getText().concat(sourceButton.getText()));    //concat textField string with the value insert by the button
    }

    @FXML
    private void eventOp(ActionEvent event){    // operations buttons function
        Alert al= new Alert(Alert.AlertType.ERROR);
        if(txtfield.getText().compareTo("") != 0){
            al.setTitle("Error");
            al.setHeaderText("Input error");
            al.setContentText("Can't use operations in input");
            al.show();
            return;
        }
        try {
                Button sourceButton = (Button) event.getSource();       //understand which button is clicked 
                c.execute(sourceButton.getText());
                if (sourceButton.getText().contentEquals("clear") || ( sourceButton.getText().contentEquals("drop") && c.getStackNumbers().size() == 1)){     //case of operation "clear" and particulare case of drop  of StackOperations
                    stackNumbers.clear();
                    stackNumbers.add("");
                }
                if(!c.getStackNumbers().empty()){
                    stackNumbers.clear();
                    for(int i=c.getStackNumbers().size()-1; i>=0; i--){         // recreating stack with new elements
                        String s = String.valueOf(c.getStackNumbers().elementAt(i).getReal());
                        if(c.getStackNumbers().elementAt(i).getImaginary()>=0){
                            s += "+";
                        }
                        s += String.valueOf(c.getStackNumbers().elementAt(i).getImaginary()) + "j";
                        stackNumbers.add(s);
                    }
            }
        } catch (StackBadSizeException | MathematicalException ex) {
            al.setTitle("Error");
            al.setHeaderText("Input error");
            al.setContentText(ex.getMessage());
            al.show();
        }
        this.clearAll(event);
        resultLabel.setText(stackNumbers.get(0));

    }
    
    @FXML
    private void selectedOption(Event event) {
        int i=0;
        CheckMenuItem checked = (CheckMenuItem) event.getSource();
        while(i < variableSetter.getItems().size()){
            CheckMenuItem controlled = (CheckMenuItem) variableSetter.getItems().get(i);
            if(controlled.isSelected() && controlled.getText().compareTo(checked.getText()) !=0 ){
                controlled.setSelected(false);
            }
            i++;
        }
    }

    @FXML
    private void variableOp(ActionEvent event) {
        Alert al= new Alert(Alert.AlertType.ERROR);
        String varName = "";
        int j=0;
        while(j<variableSetter.getItems().size()){
            CheckMenuItem controlled = (CheckMenuItem) variableSetter.getItems().get(j);
            if(controlled.isSelected()){
                varName = controlled.getText();
            }
            j++;
        }
        if(varName.compareTo("")==0){
            al.setTitle("Error");
            al.setHeaderText("Variable error");
            al.setContentText("Can't use variable operations without setted variable");
            al.show();
            return;
        }
        try {
                Button sourceButton = (Button) event.getSource();       //understand which button is clicked 
                String op = sourceButton.getText().replace("x", varName);
                c.execute(op);
                if(c.getStackNumbers().empty()){
                    stackNumbers.clear();
                    stackNumbers.add("");
                }
                else{
                    stackNumbers.clear();
                    for(int i=c.getStackNumbers().size()-1; i>=0; i--){         // recreating stack with new elements
                        String s = String.valueOf(c.getStackNumbers().elementAt(i).getReal());
                        if(c.getStackNumbers().elementAt(i).getImaginary()>=0){
                            s += "+";
                        }
                        s += String.valueOf(c.getStackNumbers().elementAt(i).getImaginary()) + "j";
                        stackNumbers.add(s);
                    }
            }
        } catch (StackBadSizeException | MathematicalException ex) {
            al.setTitle("Error");
            al.setHeaderText("Input error");
            al.setContentText(ex.getMessage());
            al.show();
        }
        this.clearAll(event);
        resultLabel.setText(stackNumbers.get(0));

    }

}
    
    

