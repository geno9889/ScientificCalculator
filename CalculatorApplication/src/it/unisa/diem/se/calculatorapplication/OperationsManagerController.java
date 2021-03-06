/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.se.calculatorapplication;

import it.unisa.diem.se.calculatorapplication.controller.CalculatorController;
import it.unisa.diem.se.calculatorapplication.controller.InvalidInputException;
import it.unisa.diem.se.calculatorapplication.entity.ComplexNumber;
import it.unisa.diem.se.calculatorapplication.service.CustomOperations;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Marco Graziano
 */
public class OperationsManagerController {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TextField operationNameField;
    @FXML
    private TextField sequenceField;
    @FXML
    private Button addButton;
    @FXML
    private Button modifyButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TableView<CustomOperation> mainTab;
    @FXML
    private TableColumn<CustomOperation, String> nameColumn;
    @FXML
    private TableColumn<CustomOperation, String> sequenceColumn;
    @FXML
    private Label nameLabel;
    @FXML
    private Label sequenceLabel;
    @FXML
    private VBox settingBox;
    
    private CalculatorController controller;
    private ObservableList<CustomOperation> list1;
   
    
    
    
    /**
     * Initializes the controller class.
     * @param c
     */
    public void initialize(CalculatorController c) {
        controller=c;
        list1 = FXCollections.observableArrayList();
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        sequenceColumn.setCellValueFactory(new PropertyValueFactory<>("sequence"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        sequenceColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        mainTab.setItems(list1);
        addButton.disableProperty().bind(Bindings.isNotNull(mainTab.getSelectionModel().selectedItemProperty()));
        modifyButton.disableProperty().bind(Bindings.isNull(mainTab.getSelectionModel().selectedItemProperty()));                                                         
        deleteButton.disableProperty().bind(Bindings.isNull(mainTab.getSelectionModel().selectedItemProperty()));
        substitute(); 
        recreateList();
        
    }    

    
    @FXML
    private void add(ActionEvent event){
        try {
            controller.addCustomOperations(operationNameField.getText(), sequenceField.getText());
            recreateList();
            operationNameField.clear();
            sequenceField.clear();
        } catch (InvalidInputException ex) {
            Alert al= new Alert(Alert.AlertType.ERROR);
            al.setTitle("Error");
            al.setHeaderText("Input error");
            al.setContentText(ex.getMessage());
            al.show();
            operationNameField.clear();
            sequenceField.clear();
        }
    }

    @FXML
    private void modify(ActionEvent event) {
        try {
            controller.modifyCustomOperation(operationNameField.getText(), String.valueOf(mainTab.getSelectionModel().getSelectedItem().getName()), sequenceField.getText());
            recreateList();
            settingBox.setStyle("-fx-border-color:BLACK;");
            nameLabel.setStyle("-fx-text-fill:WHITE;");
            sequenceLabel.setStyle("-fx-text-fill:WHITE;");
            operationNameField.clear();
            sequenceField.clear();
        } catch (InvalidInputException ex) {
            Alert al= new Alert(Alert.AlertType.ERROR);
            al.setTitle("Error");
            al.setHeaderText("Input error");
            al.setContentText(ex.getMessage());
            al.show();
            operationNameField.clear();
            sequenceField.clear();
        }
    }

    @FXML
    private void delete(ActionEvent event) {
        try {
            controller.deleteCustomOperation(String.valueOf(mainTab.getSelectionModel().getSelectedItem().getName()));
            recreateList();
            settingBox.setStyle("-fx-border-color:BLACK;");
            nameLabel.setStyle("-fx-text-fill:WHITE;");
            sequenceLabel.setStyle("-fx-text-fill:WHITE;");
            operationNameField.clear();
            sequenceField.clear();
        } catch (InvalidInputException ex) {
            Alert al= new Alert(Alert.AlertType.ERROR);
            al.setTitle("Error");
            al.setHeaderText("Input error");
            al.setContentText(ex.getMessage());
            al.show();
            operationNameField.clear();
            sequenceField.clear();
        }
    }

    @FXML
    private void save(ActionEvent event) {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save as...");
        File file = fileChooser.showSaveDialog(rootPane.getScene().getWindow());
        if (file != null) {
            try {
                controller.saveCustomOperationToFile(file);
            } catch (IOException ex) {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("Error");
                al.setHeaderText("Save error");
                al.setContentText("Save on file not successful");
                al.show();
                operationNameField.clear();
                sequenceField.clear();
            }
        }
    }

    @FXML
    private void restore(ActionEvent event) {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open...");
        File file = fileChooser.showOpenDialog(rootPane.getScene().getWindow());
        if (file != null) {
            try {
                controller.reloadCustomOperationFromFile(file);
                recreateList();
            } catch (IOException ex) {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("Error");
                al.setHeaderText("Restore error");
                al.setContentText("Restore by file not successful");
                al.show();
                operationNameField.clear();
                sequenceField.clear();
            }
        }
    }

    private void recreateList(){
        list1.clear();
        for(Map.Entry<String, String[]> entry : ((CustomOperations)controller.getCustomOperations()).getMultipleOperations().entrySet()){
            String s = "";
            for(int i = 0; i < entry.getValue().length; i++){
                    s+=entry.getValue()[i];
                    s+=" ";
                }
        list1.add(new CustomOperation(entry.getKey(),s));
        }
    }   
    
    private void substitute(){
        mainTab.setOnMousePressed(new EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent event){
            if(mainTab.getSelectionModel().getSelectedItem()!=null){
                operationNameField.setText(String.valueOf(mainTab.getSelectionModel().getSelectedItem().getName()));
                sequenceField.setText(String.valueOf(mainTab.getSelectionModel().getSelectedItem().getSequence()));
                settingBox.setStyle("-fx-border-color:YELLOW;");
                nameLabel.setStyle("-fx-text-fill:YELLOW;");
                sequenceLabel.setStyle("-fx-text-fill:YELLOW;");
            }
           }
        });
    }
        

    @FXML
    private void refresh(MouseEvent event) {
        substitute();
    }

    private void refresh(KeyEvent event) {
        substitute();
    }


    
    public class CustomOperation {
        private final String name;
        private final String sequence;

        public CustomOperation(String name, String sequence) {
            this.name = name;
            this.sequence = sequence; 
        }

        public String getName() {
            return name;
        }

        public String getSequence() {
            return sequence;
        }
    }
    
}
