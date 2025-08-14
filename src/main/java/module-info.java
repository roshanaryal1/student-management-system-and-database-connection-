module com.itpassignment.itpstudentmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    exports com.itpassignment;
    exports com.itpassignment.model;
    exports com.itpassignment.controller;
    exports com.itpassignment.dao;
    exports com.itpassignment.test;
}