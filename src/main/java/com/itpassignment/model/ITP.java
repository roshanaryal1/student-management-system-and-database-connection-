package com.itpassignment.model;

public class ITP {
    private String itpName;
    private String region;
    private int enrollment;

    public ITP() {}

    public ITP(String itpName, String region, int enrollment) {
        this.itpName = itpName;
        this.region = region;
        this.enrollment = enrollment;
    }

    // JavaFX PropertyValueFactory compatible getters
    public String getItpName() { return itpName; }
    public void setItpName(String itpName) { this.itpName = itpName; }
    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }
    public int getEnrollment() { return enrollment; }
    public void setEnrollment(int enrollment) { this.enrollment = enrollment; }

    @Override
    public String toString() {
        return "ITP{itpName='" + itpName + "', region='" + region + "', enrollment=" + enrollment + '}';
    }
}