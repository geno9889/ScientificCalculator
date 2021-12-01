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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
            for(int i=c.getStackNumbers().size()-1; i>=0; i--){
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
    private void clearAll(ActionEvent event) {
        txtfield.clear();
        resultLabel.setText(null);
    }


    @FXML
    private void write(ActionEvent event) {
        Button sourceButton = (Button) event.getSource();
        txtfield.setText(txtfield.getText().concat(sourceButton.getText()));
    }

    @FXML
    private void eventOp(ActionEvent event){
        if(txtfield.getText().compareTo("") != 0){
            this.write(event);
        }
        else{
            try {
                Button sourceButton = (Button) event.getSource();
                c.execute(sourceButton.getText());
                    if(!c.getStackNumbers().empty()){
                    stackNumbers.clear();
                    for(int i=c.getStackNumbers().size()-1; i>=0; i--){
                        String s = String.valueOf(c.getStackNumbers().elementAt(i).getReal());
                        if(c.getStackNumbers().elementAt(i).getImaginary()>=0){
                            s += "+";
                        }
                        s += String.valueOf(c.getStackNumbers().elementAt(i).getImaginary()) + "j";
                        stackNumbers.add(s);
                    }
                }
            } catch (StackBadSizeException | MathematicalException ex) {
                Alert al= new Alert(Alert.AlertType.ERROR);
                al.setTitle("Error");
                al.setHeaderText("Input error");
                al.setContentText(ex.getMessage());
                al.show();
            }
            this.clearAll(event);
            resultLabel.setText(stackNumbers.get(0));
        }
    }
    
    
    }
    
    

