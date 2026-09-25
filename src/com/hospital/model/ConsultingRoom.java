package com.hospital.model;

public class ConsultingRoom {
    private int roomNumber;
    private String floor;

    public ConsultingRoom(int roomNumber, String floor) {
        this.roomNumber = roomNumber;
        this.floor = floor;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " (" + floor + ")";
    }
}