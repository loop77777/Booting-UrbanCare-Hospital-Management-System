package com.hospital.model;

public class Nurse extends Person {
    private String wardAssigned;

    public Nurse(String id, String name, int age, BloodGroup bloodGroup, String wardAssigned) {
        super(id, name, age, bloodGroup);
        this.wardAssigned = wardAssigned;
    }

    @Override
    public void getDetails() {
        System.out.println("Nurse [Name=" + getName() + ", Ward=" + wardAssigned + "]");
    }
}
