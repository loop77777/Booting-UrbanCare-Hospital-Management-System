package com.hospital.model;

public abstract class Person {
    private String id;
    private String name;
    private int age;
    private BloodGroup bloodGroup;

    public Person(String id, String name, int age, BloodGroup bloodGroup) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.bloodGroup = bloodGroup;
    }

    public abstract void getDetails();

    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public BloodGroup getBloodGroup() { return bloodGroup; }
}