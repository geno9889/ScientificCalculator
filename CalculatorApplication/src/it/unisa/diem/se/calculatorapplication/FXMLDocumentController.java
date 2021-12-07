/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */

package it.unisa.diem.se.calculatorapplication;

import it.unisa.diem.se.calculatorapplication.controller.CalculatorController;
import it.unisa.diem.se.calculatorapplication.controller.InvalidInputException;
import it.unisa.diem.se.calculatorapplication.entity.ComplexNumber;
import it.unisa.diem.se.calculatorapplication.entity.MathematicalException;
import it.unisa.diem.se.calculatorapplication.service.NullVariableException;
import it.unisa.diem.se.calculatorapplication.service.StackBadSizeException;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 *
 * @author Giuseppe
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private AnchorPane rootPane;
    @FXML
    private TextField txtfield;
    @FXML
    private Button insertbtn;
    @FXML
    private ListView<String> list;
    @FXML
    private Label resultLabel;
    @FXML
    private MenuButton variableSetter;
    @FXML
    private Label settedVariable;
        
    private CalculatorController c;
    private ObservableList<String> stackNumbers;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         stackNumbers = FXCollections.observableArrayList();
         stackNumbers.add("");
         list.setItems(stackNumbers);
         insertbtn.disableProperty().bind(Bindings.isEmpty(txtfield.textProperty()));
         c = new CalculatorController();
         
    }    

    private void recreateStack(){
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
    
    @FXML
    private void insertValue(ActionEvent event) throws NullVariableException {
        try {
            c.insertOrExecute(txtfield.getText());
            if(c.getStackNumbers().empty()){
                    stackNumbers.clear();
                    stackNumbers.add("");
                }
            else{
                recreateStack();
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
    private void deleteLastCharacter(ActionEvent event) {      // button backspace function
        if (txtfield.getLength() != 0)          //avoid lenght exception
            txtfield.setText(txtfield.getText(0, txtfield.getLength()-1));
    }
    
    @FXML
    private void clearAll(ActionEvent event) {      // button canc function
        txtfield.clear();
    }

    @FXML
    private void write(ActionEvent event) {    // number, "j", "." buttons fiunction
        Button sourceButton = (Button) event.getSource();       //understand which button is clicked 
        txtfield.setText(txtfield.getText().concat(sourceButton.getText()));    //concat textField string with the value insert by the button
    }

    @FXML
    private void eventOp(ActionEvent event) throws NullVariableException{    // operations buttons function
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
                c.executeSingleOperation(sourceButton.getText());
                if (sourceButton.getText().contentEquals("clear") || ( sourceButton.getText().contentEquals("drop") && c.getStackNumbers().size() == 1)){     //case of operation "clear" and particulare case of drop  of StackOperations
                    stackNumbers.clear();
                    stackNumbers.add("");
                }
                if(!c.getStackNumbers().empty())
                    recreateStack();
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
        if(checked.getText() == settedVariable.getText()){
            settedVariable.setText("None");
        }
        else{
            settedVariable.setText(checked.getText());
            while(i < variableSetter.getItems().size()){
                CheckMenuItem controlled = (CheckMenuItem) variableSetter.getItems().get(i);
                if(controlled.isSelected() && controlled.getText().compareTo(checked.getText()) !=0 ){
                    controlled.setSelected(false);
                }
                i++;
            }
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
                c.executeSingleOperation(op);
                if(c.getStackNumbers().empty()){
                    stackNumbers.clear();
                    stackNumbers.add("");
                }
                else
                    recreateStack();
        } catch (NullVariableException | StackBadSizeException | MathematicalException ex) {
            al.setTitle("Error");
            al.setHeaderText("Variable error");
            al.setContentText(ex.getMessage());
            al.show();
        }
        this.clearAll(event);
        resultLabel.setText(stackNumbers.get(0));
    }

    @FXML
    private void showOperationsManager(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("customOperationsManager.fxml"));
        Parent root = loader.load();
        Stage addStage = new Stage();
        addStage.setTitle("Custom operations manager");
        Image image = new Image("/images/calculatorimage.png");
        addStage.getIcons().add(image);
        addStage.setScene(new Scene(root,600,450));
        addStage.setResizable(false);
        OperationsManagerController controller= loader.getController();
        controller.initialize(c);
        addStage.show();
    }

    @FXML
    private void showVariablesData(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("variableWindow.fxml"));
        Parent root = loader.load();
        Stage addStage = new Stage();
        addStage.setTitle("Variables datas");
        Image image = new Image("/images/calculatorimage.png");
        addStage.getIcons().add(image);
        addStage.setScene(new Scene(root,600,740));
        addStage.setResizable(false);
        VariableWindowController controller= loader.getController();
        controller.initialize(c);
        addStage.show();
        
    }

    @FXML
    private void variableStackOp(ActionEvent event) {
        try {
                Button sourceButton = (Button) event.getSource();       //understand which button is clicked 
                String op = sourceButton.getText();
                c.executeSingleOperation(op);
                if(c.getStackNumbers().empty()){
                    stackNumbers.clear();
                    stackNumbers.add("");
                }
                else
                    recreateStack();
        } catch (NullVariableException | StackBadSizeException | MathematicalException ex) {
            Alert al= new Alert(Alert.AlertType.ERROR);
            al.setTitle("Error");
            al.setHeaderText("Variable error");
            al.setContentText(ex.getMessage());
            al.show();
        }
        this.clearAll(event);
    }
}
    
    

