package com.hospital.model;

import java.util.ArrayList;
import java.util.List;

public class Doctor extends Person {
    private String specialization;
    private ConsultingRoom room;                             // 1-to-1 Association
    private List<Appointment> doctorAppointments = new ArrayList<>(); // 1-to-Many

    public Doctor(String id, String name, int age, BloodGroup bloodGroup, String specialization, ConsultingRoom room) {
        super(id, name, age, bloodGroup);
        this.specialization = specialization;
        this.room = room;
    }

    public void assignAppointment(Appointment appt) {
        doctorAppointments.add(appt);
    }

    public String getSpecialization() {
        return specialization;
    }

    @Override
    public void getDetails() {
        System.out.println("Doctor [" + getName() + ", Specialization=" + specialization +
                ", Location=" + room + ", Total Appts=" + doctorAppointments.size() + "]");
    }
}

