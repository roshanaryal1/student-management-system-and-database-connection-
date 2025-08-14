package com.itpassignment.model;

public class Student {
    private int sID;
    private String sName;
    private double GPA;
    private int sizeHS;

    // Constructors
    public Student() {}

    public Student(int sID, String sName, double GPA, int sizeHS) {
        this.sID = sID;
        this.sName = sName;
        this.GPA = GPA;
        this.sizeHS = sizeHS;
    }

    // FIXED: Correct getter/setter names for JavaFX PropertyValueFactory
    public int getSID() { return sID; }
    public void setSID(int sID) { this.sID = sID; }

    public String getSName() { return sName; }
    public void setSName(String sName) { this.sName = sName; }

    public double getGPA() { return GPA; }
    public void setGPA(double GPA) { this.GPA = GPA; }

    public int getSizeHS() { return sizeHS; }
    public void setSizeHS(int sizeHS) { this.sizeHS = sizeHS; }

    // Keep the old method names for backward compatibility with DAO
    public int getsID() { return sID; }
    public void setsID(int sID) { this.sID = sID; }
    public String getsName() { return sName; }
    public void setsName(String sName) { this.sName = sName; }

    @Override
    public String toString() {
        return "Student{sID=" + sID + ", sName='" + sName + "', GPA=" + GPA + ", sizeHS=" + sizeHS + '}';
    }
}