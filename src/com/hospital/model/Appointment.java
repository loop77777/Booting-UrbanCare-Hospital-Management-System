package com.hospital.model;

public class Appointment {
    private String appointmentId;
    private String date;
    private AppointmentStatus status;

    public Appointment(String appointmentId, String date, AppointmentStatus status) {
        this.appointmentId = appointmentId;
        this.date = date;
        this.status = status;
    }

    public String getAppointmentId() { return appointmentId; }
    public AppointmentStatus getStatus() { return status; }
    public String getDate() { return date; }
}