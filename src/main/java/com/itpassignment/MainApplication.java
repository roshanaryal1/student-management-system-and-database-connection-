package com.itpassignment;

import com.itpassignment.dao.StudentDAO;
import com.itpassignment.model.Student;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

public class MainApplication extends Application {

    private StudentDAO studentDAO;
    private TableView<Student> studentTable;
    private TextField txtStudentId, txtStudentName, txtGPA, txtSizeHS;
    private TextField txtMinGPA, txtMaxGPA;
    private Label lblStatus;
    private ObservableList<Student> studentList;

    @Override
    public void start(Stage primaryStage) {
        studentDAO = new StudentDAO();
        studentList = FXCollections.observableArrayList();

        // Create the main layout
        BorderPane root = new BorderPane();

        // Title
        Label title = new Label("IA608001 Practical 2 - Student Management System");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        BorderPane.setAlignment(title, Pos.CENTER);
        BorderPane.setMargin(title, new Insets(10));
        root.setTop(title);

        // Create table
        createTable();
        root.setCenter(studentTable);

        // Create controls
        VBox controls = createControls();
        root.setRight(controls);

        // Load initial data
        loadStudentData();

        // Create scene and show
        Scene scene = new Scene(root, 1000, 700);
        primaryStage.setTitle("Student Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createTable() {
        studentTable = new TableView<>();

        // Create columns
        TableColumn<Student, Integer> colId = new TableColumn<>("Student ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("sID"));
        colId.setPrefWidth(100);

        TableColumn<Student, String> colName = new TableColumn<>("Name");
        colName.setCellValueFactory(new PropertyValueFactory<>("sName"));
        colName.setPrefWidth(150);

        TableColumn<Student, Double> colGPA = new TableColumn<>("GPA");
        colGPA.setCellValueFactory(new PropertyValueFactory<>("GPA"));
        colGPA.setPrefWidth(80);

        TableColumn<Student, Integer> colSize = new TableColumn<>("HS Size");
        colSize.setCellValueFactory(new PropertyValueFactory<>("sizeHS"));
        colSize.setPrefWidth(100);

        studentTable.getColumns().addAll(colId, colName, colGPA, colSize);

        // Selection listener
        studentTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        populateFields(newValue);
                    }
                }
        );

        BorderPane.setMargin(studentTable, new Insets(10));
    }

    private VBox createControls() {
        VBox controls = new VBox(10);
        controls.setPadding(new Insets(10));
        controls.setPrefWidth(300);

        // Title
        Label detailsTitle = new Label("Student Details");
        detailsTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        // Input fields
        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);

        inputGrid.add(new Label("Student ID:"), 0, 0);
        txtStudentId = new TextField();
        inputGrid.add(txtStudentId, 1, 0);

        inputGrid.add(new Label("Name:"), 0, 1);
        txtStudentName = new TextField();
        inputGrid.add(txtStudentName, 1, 1);

        inputGrid.add(new Label("GPA:"), 0, 2);
        txtGPA = new TextField();
        inputGrid.add(txtGPA, 1, 2);

        inputGrid.add(new Label("HS Size:"), 0, 3);
        txtSizeHS = new TextField();
        inputGrid.add(txtSizeHS, 1, 3);

        // CRUD Buttons
        HBox crudButtons = new HBox(5);
        Button btnAdd = new Button("Add");
        Button btnUpdate = new Button("Update");
        Button btnDelete = new Button("Delete");
        Button btnClear = new Button("Clear");

        btnAdd.setOnAction(e -> handleAddStudent());
        btnUpdate.setOnAction(e -> handleUpdateStudent());
        btnDelete.setOnAction(e -> handleDeleteStudent());
        btnClear.setOnAction(e -> clearFields());

        crudButtons.getChildren().addAll(btnAdd, btnUpdate, btnDelete, btnClear);

        // Search section
        Label searchTitle = new Label("Search by GPA Range");
        searchTitle.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        GridPane searchGrid = new GridPane();
        searchGrid.setHgap(10);
        searchGrid.setVgap(10);

        searchGrid.add(new Label("Min GPA:"), 0, 0);
        txtMinGPA = new TextField();
        searchGrid.add(txtMinGPA, 1, 0);

        searchGrid.add(new Label("Max GPA:"), 0, 1);
        txtMaxGPA = new TextField();
        searchGrid.add(txtMaxGPA, 1, 1);

        HBox searchButtons = new HBox(5);
        Button btnSearch = new Button("Search");
        Button btnShowAll = new Button("Show All");

        btnSearch.setOnAction(e -> handleSearchByGPA());
        btnShowAll.setOnAction(e -> loadStudentData());

        searchButtons.getChildren().addAll(btnSearch, btnShowAll);

        // Status label
        lblStatus = new Label("Ready");
        lblStatus.setStyle("-fx-text-fill: blue;");

        // Add all to controls
        controls.getChildren().addAll(
                detailsTitle,
                inputGrid,
                crudButtons,
                new Separator(),
                searchTitle,
                searchGrid,
                searchButtons,
                new Separator(),
                lblStatus
        );

        return controls;
    }

    // a. Retrieve data
    private void loadStudentData() {
        studentList.clear();
        List<Student> students = studentDAO.getAllStudents();
        studentList.addAll(students);
        studentTable.setItems(studentList);
        lblStatus.setText("Showing all students. Total: " + students.size());
    }

    // b. Insert new record
    private void handleAddStudent() {
        try {
            Student student = new Student(
                    Integer.parseInt(txtStudentId.getText()),
                    txtStudentName.getText(),
                    Double.parseDouble(txtGPA.getText()),
                    Integer.parseInt(txtSizeHS.getText())
            );

            if (studentDAO.insertStudent(student)) {
                lblStatus.setText("Student added successfully!");
                loadStudentData();
                clearFields();
            } else {
                lblStatus.setText("Error: Failed to add student.");
            }

        } catch (NumberFormatException e) {
            lblStatus.setText("Error: Please enter valid numeric values.");
        }
    }

    // c. Delete record
    private void handleDeleteStudent() {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent == null) {
            lblStatus.setText("Error: Please select a student to delete.");
            return;
        }

        if (studentDAO.deleteStudent(selectedStudent.getsID())) {
            lblStatus.setText("Student deleted successfully!");
            loadStudentData();
            clearFields();
        } else {
            lblStatus.setText("Error: Failed to delete student.");
        }
    }

    // d. Update record
    private void handleUpdateStudent() {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent == null) {
            lblStatus.setText("Error: Please select a student to update.");
            return;
        }

        try {
            selectedStudent.setsName(txtStudentName.getText());
            selectedStudent.setGPA(Double.parseDouble(txtGPA.getText()));
            selectedStudent.setSizeHS(Integer.parseInt(txtSizeHS.getText()));

            if (studentDAO.updateStudent(selectedStudent)) {
                lblStatus.setText("Student updated successfully!");
                loadStudentData();
                clearFields();
            } else {
                lblStatus.setText("Error: Failed to update student.");
            }

        } catch (NumberFormatException e) {
            lblStatus.setText("Error: Please enter valid numeric values.");
        }
    }

    // e. Custom operation - Search by GPA range
    private void handleSearchByGPA() {
        try {
            double minGPA = Double.parseDouble(txtMinGPA.getText());
            double maxGPA = Double.parseDouble(txtMaxGPA.getText());

            List<Student> filteredStudents = studentDAO.getStudentsByGPARange(minGPA, maxGPA);
            studentList.clear();
            studentList.addAll(filteredStudents);
            studentTable.setItems(studentList);

            lblStatus.setText("Found " + filteredStudents.size() + " students in GPA range.");

        } catch (NumberFormatException e) {
            lblStatus.setText("Error: Please enter valid GPA values.");
        }
    }

    private void populateFields(Student student) {
        txtStudentId.setText(String.valueOf(student.getsID()));
        txtStudentName.setText(student.getsName());
        txtGPA.setText(String.valueOf(student.getGPA()));
        txtSizeHS.setText(String.valueOf(student.getSizeHS()));
    }

    private void clearFields() {
        txtStudentId.clear();
        txtStudentName.clear();
        txtGPA.clear();
        txtSizeHS.clear();
        txtMinGPA.clear();
        txtMaxGPA.clear();
        studentTable.getSelectionModel().clearSelection();
        lblStatus.setText("Fields cleared.");
    }

    public static void main(String[] args) {
        launch(args);
    }
}