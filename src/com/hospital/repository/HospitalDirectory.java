package com.hospital.repository;

import com.hospital.model.Patient;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class HospitalDirectory {
    // Map and TreeSet collections matching Class Diagram requirements
        private Map<String, Patient> patientDatabase = new HashMap<>();
        // Use the ID as a tie-breaker so patients of the same age are not lost.
            private TreeSet<Patient> sortedPatientsByAge = new TreeSet<>(
                Comparator.comparingInt(Patient::getAge).thenComparing(Patient::getId));

    public void registerPatient(Patient p) {
        if (p != null) {
            patientDatabase.put(p.getId(), p);
            sortedPatientsByAge.add(p);
            System.out.println("[Directory] Successfully registered: " + p.getName());
        }
    }

    public Patient findPatient(String id) {
        return patientDatabase.get(id);
    }

    public void printSortedPatients() {
        System.out.println("\n--- Patient Database Sorted By Age (TreeSet) ---");
        for (Patient p : sortedPatientsByAge) {
            System.out.println(" > " + p.getName() + " (Age: " + p.getAge() + ", ID: " + p.getId() + ")");
        }
    }
}
