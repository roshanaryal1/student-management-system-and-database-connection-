package com.itpassignment.model;

public class Apply {
    private int sID;
    private String itpName;
    private String major;
    private String decision;

    // Constructors
    public Apply() {}

    public Apply(int sID, String itpName, String major, String decision) {
        this.sID = sID;
        this.itpName = itpName;
        this.major = major;
        this.decision = decision;
    }

    // Getters and Setters
    public int getsID() { return sID; }
    public void setsID(int sID) { this.sID = sID; }
    public String getItpName() { return itpName; }
    public void setItpName(String itpName) { this.itpName = itpName; }
    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }
    public String getDecision() { return decision; }
    public void setDecision(String decision) { this.decision = decision; }

    @Override
    public String toString() {
        return "Apply{sID=" + sID + ", itpName='" + itpName + "', major='" + major + "', decision='" + decision + "'}";
    }
}