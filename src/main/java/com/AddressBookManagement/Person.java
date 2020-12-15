package com.AddressBookManagement;

public class Person {


    private String firstname;
    private String lastname;
    private String contactno;
    private String emailid;
    private String address;
    private String city;
    private String state;
    private String zip;

    public Person(String firstname, String lastname, String contactno, String emailid, String address, String city, String state, String zip) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.contactno = contactno;
        this.emailid = emailid;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

}