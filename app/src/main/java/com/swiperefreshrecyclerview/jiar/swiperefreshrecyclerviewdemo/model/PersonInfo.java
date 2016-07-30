package com.swiperefreshrecyclerview.jiar.swiperefreshrecyclerviewdemo.model;

/**
 * Created by Jiar on 16/7/30.
 */
public class PersonInfo {
    private String firstName;
    private String lastName;
    private String motto;

    public PersonInfo() {
        this("firstName", "lastName", "motto");
    }

    public PersonInfo(String firstName, String lastName, String motto) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.motto = motto;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    @Override
    public String toString() {
        return "PersonInfo{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", motto='" + motto + '\'' +
                '}';
    }
}
