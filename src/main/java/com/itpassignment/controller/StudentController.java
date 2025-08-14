package com.itpassignment.controller;

import com.itpassignment.dao.StudentDAO;
import com.itpassignment.model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StudentController implements Initializable {

    @FXML private TableView<Student> studentTable;
    @FXML private TableColumn<Student, Integer> colStudentId;
    @FXML private TableColumn<Student, String> colStudentName;
    @FXML private TableColumn<Student, Double> colGPA;
    @FXML private TableColumn<Student, Integer> colSizeHS;

    @FXML private TextField txtStudentId;
    @FXML private TextField txtStudentName;
    @FXML private TextField txtGPA;
    @FXML private TextField txtSizeHS;
    @FXML private TextField txtMinGPA;
    @FXML private TextField txtMaxGPA;

    @FXML private Label lblStatus;

    private StudentDAO studentDAO;
    private ObservableList<Student> studentList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        studentDAO = new StudentDAO();
        studentList = FXCollections.observableArrayList();

        // Initialize table columns
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("sID"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("sName"));
        colGPA.setCellValueFactory(new PropertyValueFactory<>("GPA"));
        colSizeHS.setCellValueFactory(new PropertyValueFactory<>("sizeHS"));

        // Load data
        loadStudentData();

        // Table selection listener
        studentTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        populateFields(newValue);
                    }
                }
        );
    }

    // a. Retrieve data
    @FXML
    private void handleShowAllStudents() {
        loadStudentData();
        lblStatus.setText("Showing all students.");
    }

    // b. Insert new record
    @FXML
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
    @FXML
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
    @FXML
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
    @FXML
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

    @FXML
    private void handleClearFields() {
        clearFields();
    }

    private void loadStudentData() {
        studentList.clear();
        List<Student> students = studentDAO.getAllStudents();
        studentList.addAll(students);
        studentTable.setItems(studentList);
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
    }
}