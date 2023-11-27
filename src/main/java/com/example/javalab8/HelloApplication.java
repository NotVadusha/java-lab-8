package com.example.javalab8;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import CalculatingTool.CalculatingTool;

import java.util.Objects;

public class HelloApplication extends Application {

    private ObservableList<CalculatingTool> toolList = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tool Manager App");

        // UI components
        Label nameLabel = new Label("Tool Name:");
        TextField nameField = new TextField();

        Label costLabel = new Label("Tool Cost:");
        TextField costField = new TextField();

        Label weightLabel = new Label("Tool Weight:");
        TextField weightField = new TextField();

        Button createButton = new Button("Create Tool");
        Button viewDetailsButton = new Button("View Details");
        Button deleteButton = new Button("Delete Tool");

        ListView<CalculatingTool> toolListView = new ListView<>();
        toolListView.setPrefHeight(150);

        // Event handling for creating a tool
        createButton.setOnAction(e -> {
            String name = nameField.getText();
            double cost = -1;
            double weight = -1;

            if (Objects.equals(name, "")){
                Alert errAl = new Alert(Alert.AlertType.ERROR);
                errAl.setHeaderText("Name is empty!");
                errAl.setContentText("Input name");
                errAl.showAndWait();
            }

            try {
                cost = Double.parseDouble(costField.getText());
                weight = Double.parseDouble(weightField.getText());
            }catch (Exception exept) {
                Alert errAl = new Alert(Alert.AlertType.ERROR);
                errAl.setHeaderText("Type error!");
                errAl.setContentText(exept.getMessage());
                errAl.showAndWait();
            }
            if (cost == -1){
                Alert errAl = new Alert(Alert.AlertType.ERROR);
                errAl.setHeaderText("Cost is empty!");
                errAl.setContentText("Input cost!");
                errAl.showAndWait();
            }else if (weight == -1){
                Alert errAl = new Alert(Alert.AlertType.ERROR);
                errAl.setHeaderText("Weight is empty!");
                errAl.setContentText("Input weight!");
                errAl.showAndWait();
            }else {
                CalculatingTool tool = new CalculatingTool(name, cost, weight);
                toolList.add(tool);
            }
        });

        // Event handling for viewing details
        viewDetailsButton.setOnAction(e -> {
            CalculatingTool selectedTool = toolListView.getSelectionModel().getSelectedItem();

            if (selectedTool != null) {
                // Display details in a new window
                displayDetails(selectedTool);
            } else {
                showAlert("Please select a tool to view details.");
            }
        });

        // Event handling for deleting a tool
        deleteButton.setOnAction(e -> {
            CalculatingTool selectedTool = toolListView.getSelectionModel().getSelectedItem();

            if (selectedTool != null) {
                toolList.remove(selectedTool);
                showAlert("Tool deleted successfully.");
            } else {
                showAlert("Please select a tool to delete.");
            }
        });

        // Layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(nameLabel, nameField, costLabel, costField, weightLabel, weightField,
                createButton, toolListView, viewDetailsButton, deleteButton);

        // Bind tool list to the ListView
        toolListView.setItems(toolList);

        // Scene
        Scene scene = new Scene(layout, 400, 400);

        // Set the scene
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();
    }

    // Display details in a new window
    private void displayDetails(CalculatingTool tool) {
        Stage detailsStage = new Stage();
        detailsStage.setTitle("Tool Details");

        Label nameLabel = new Label("Name: " + tool.getToolName());
        Label costLabel = new Label("Cost: " + tool.getToolCost());
        Label weightLabel = new Label("Weight: " + tool.getToolWeight());

        VBox detailsLayout = new VBox(10);
        detailsLayout.getChildren().addAll(nameLabel, costLabel, weightLabel);
        detailsLayout.setPadding(new Insets(10));

        Scene detailsScene = new Scene(detailsLayout, 250, 150);

        detailsStage.setScene(detailsScene);

        detailsStage.show();
    }

    // Show alert dialog
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
