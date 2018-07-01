package com.example.nizamuddinshamrat.contacts;

public class PersonInfo {

    private int personId;
    private String personName;
    private String personNumber;
    private String personEmail;
    private String personAddress;

    public PersonInfo(int personId, String personName, String personNumber, String personEmail, String personAddress) {
        this.personId = personId;
        this.personName = personName;
        this.personNumber = personNumber;
        this.personEmail = personEmail;
        this.personAddress = personAddress;
    }

    public PersonInfo(String personName, String personNumber, String personEmail, String personAddress) {
        this.personName = personName;
        this.personNumber = personNumber;
        this.personEmail = personEmail;
        this.personAddress = personAddress;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(String personNumber) {
        this.personNumber = personNumber;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public String getPersonAddress() {
        return personAddress;
    }

    public void setPersonAddress(String personAddress) {
        this.personAddress = personAddress;
    }
}
