package com.example.firebase02;

public class DataModel {

    public DataModel() {
    }

    private String Key;
    private String Name;
    private String AGE;
    private String CNIC;
    private String SEMESTER;
    private String CGPA;

    public void setKey(String key) {
        Key = key;
    }

    public void setAGE(String AGE) {
        this.AGE = AGE;
    }

    public void setName(String name) {
        Name = name;
    }


    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }

    public void setSEMESTER(String SEMESTER) {
        this.SEMESTER = SEMESTER;
    }

    public void setCGPA(String CGPA) {
        this.CGPA = CGPA;
    }

    public String getKey() {
        return Key;
    }

    public String getName() {
        return Name;
    }

    public String getAGE() {
        return AGE;
    }

    public String getCNIC() {
        return CNIC;
    }

    public String getSEMESTER() {
        return SEMESTER;
    }

    public String getCGPA() {
        return CGPA;
    }

    public DataModel(String key, String name, String AGE, String CNIC, String SEMESTER, String CGPA) {
        Key = key;
        Name = name;
        this.AGE = AGE;
        this.CNIC = CNIC;
        this.SEMESTER = SEMESTER;
        this.CGPA = CGPA;
    }

}
