/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package it.unisa.diem.se.calculatorapplication;

import it.unisa.diem.se.calculatorapplication.controller.CalculatorController;
import it.unisa.diem.se.calculatorapplication.entity.ComplexNumber;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


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
    
    private ObservableList<ComplexNumber> stackNumbers;
    @FXML
    private ListView<ComplexNumber> list;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         stackNumbers = FXCollections.observableArrayList();
         list.setItems(stackNumbers);
         insertbtn.disableProperty().bind(Bindings.isEmpty(txtfield.textProperty()));
        
    }    

   @FXML
    private void insertValue(ActionEvent event) {
        CalculatorController c = new CalculatorController();
        /*if(c.insertOrExecute(txtfield.getText()) == true){
           for(int i = c.getStackNumbers().size()-1; i==0 ; i++)
               stackNumbers.add(c.getStackNumbers().get(i));
           
            }*/
        }
    }
    
    

