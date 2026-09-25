package com.hospital.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Patient extends Person {
    private String medicalHistorySummary;
    private List<Appointment> appointments = new ArrayList<>(); // 1-to-Many via Collections
    private List<String> treatments = new ArrayList<>();        // Many-to-Many via Collections

    public Patient(String id, String name, int age, BloodGroup bloodGroup) {
        super(id, name, age, bloodGroup);
        this.medicalHistorySummary = "No prior critical records.";
    }

    public void addAppointment(Appointment appt) {
        appointments.add(appt);
    }

    public void addTreatment(String treatment) {
        treatments.add(treatment);
    }

    public void setMedicalHistorySummary(String summary) {
        this.medicalHistorySummary = summary;
    }

    @Override
    public void getDetails() {
        System.out.println("Patient [ID=" + getId() + ", Name=" + getName() +
                ", Age=" + getAge() + ", Blood Group=" + getBloodGroup() +
                ", History=" + medicalHistorySummary +
                ", Appointments=" + appointments.size() +
                ", Treatments=" + treatments + "]");
    }

    // Overriding equals() and hashCode() as specified in Class Diagram
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(getId(), patient.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}