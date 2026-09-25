package com.hospital.main;

import com.hospital.model.*;
import com.hospital.repository.HospitalDirectory;
import com.hospital.service.BillingService;

// Problem Statement
//1. Domain Context & Background
//UrbanCare General Hospital requires a modular, enterprise-grade Hospital Management System (HMS) to streamline daily clinical operations, patient record management, appointment scheduling, and automated billing workflows. The system must be engineered using Core Java, strictly adhering to SOLID Design Principles, the Four Pillars of Object-Oriented Programming (OOP), and core Design Patterns (such as Singleton, Factory, and Strategy).
//
//2. System Actors
//Patient: Registers into the system, books and manages appointments, and reviews medical prescriptions and bills.
//
//Doctor: Manages personal consultation schedules, accesses assigned patient medical records, and prescribes treatments.
//
//Receptionist / Front Desk: Handles initial patient onboarding, directory lookups, and front-desk appointment allocation.
//
//Administrator: Oversees system configurations, staff directories, and global reporting.
//
//3. Functional Requirements
//FR1: Patient Onboarding & Management
//
//The system must allow registration of patients with attributes such as ID, Name, Age, Blood Group (using enums), and contact details.
//
//The system must maintain unique identifiers and support fast searches using Collections (HashMap and TreeSet).
//
//FR2: Appointment Scheduling (One-to-Many & Many-to-Many)
//
//Patients can schedule multiple appointments with different doctors.
//
//Doctors can view and manage multiple appointments.
//
//Appointments must track status changes (SCHEDULED, COMPLETED, CANCELLED).
//
//FR3: Staff Hierarchy & Specializations (Inheritance & Abstract Classes)
//
//A generic abstract Person class must handle common fields (id, name, age).
//
//Subclasses (Patient, Doctor, Nurse) must inherit from Person and override polymorphism methods (e.g., getDetails()).
//
//FR4: Dynamic Billing & Invoicing (Design Patterns & String Manipulation)
//
//The system must calculate total charges (Consultation Fee + Medicine Cost + Taxes).
//
//It must use StringBuilder and String.format() to generate formatted, professional invoice receipts.
//
//Part 2: System Class Diagram
//Below is a structural UML class diagram representation mapping the classes, attributes, methods, inheritance hierarchies, and relationships.

//+---------------------------------------------------------------+
//|                      <<abstract>> Person                      |
//|---------------------------------------------------------------|
//| - id: String                                                  |
//| - name: String                                                |
//| - age: int                                                    |
//| - bloodGroup: BloodGroup                                      |
//|---------------------------------------------------------------|
//| + Person()                                                    |
//| + Person(id, name, age, bloodGroup)                           |
//| + {abstract} getDetails(): void                               |
//| + getId(): String                                             |
//| + getName(): String                                           |
//+---------------------------------------------------------------+
//         ^                                       ^
//         | (Inheritance)                         | (Inheritance)
//         |                                       |
//+-------------------------------+       +--------------------------------+
//|            Patient            |       |             Doctor             |
//|-------------------------------|       |--------------------------------|
//| - medicalHistorySummary: String|      | - specialization: String       |
//| - appointments: List<Appt>    |       | - room: ConsultingRoom (1-1)   |
//| - treatments: List<String>    |       | - doctorAppts: List<Appt>      |
//|-------------------------------|       |--------------------------------|
//| + getDetails(): void          |       | + getDetails(): void           |
//| + equals(Object): boolean     |       | + getSpecialization(): String  |
//| + hashCode(): int             |       |                                |
//+-------------------------------+       +--------------------------------+
//         |                                               |
//         | 1-to-Many (Collections)                       | 1-to-1 Association
//         v                                               v
//+-------------------------------+       +--------------------------------+
//|          Appointment          |       |        ConsultingRoom          |
//|-------------------------------|       |--------------------------------|
//| - appointmentId: String       |       | - roomNumber: int              |
//| - date: LocalDate             |       | - floor: String                |
//| - status: AppointmentStatus   |       +--------------------------------+
//+-------------------------------+
//         ^
//         | Managed by
//+---------------------------------------------------------------+
//|                       HospitalDirectory                       |
//|---------------------------------------------------------------|
//| - patientDatabase: Map<String, Patient>                       |
//| - sortedPatientsByAge: TreeSet<Patient>                       |
//|---------------------------------------------------------------|
//| + registerPatient(Patient p): void                            |
//| + findPatient(String id): Patient                             |
//| + printSortedPatients(): void                                 |
//+---------------------------------------------------------------+
//         |
//         | Utilizes
//         v
//+---------------------------------------------------------------+
//|                        BillingService                         |
//|---------------------------------------------------------------|
//|---------------------------------------------------------------|
//| + generateInvoice(Patient p, double fee, double med): String  |
//+---------------------------------------------------------------+

//src/
//└── com/
//    └── hospital/
//        ├── model/
//        │   ├── BloodGroup.java
//        │   ├── AppointmentStatus.java
//        │   ├── Person.java
//        │   ├── ConsultingRoom.java
//        │   ├── Appointment.java
//        │   ├── Patient.java
//        │   └── Doctor.java
//        ├── repository/
//        │   ├── PatientRepository.java
//        │   └── HospitalDirectory.java
//        ├── service/
//        │   ├── PatientService.java
//        │   └── BillingService.java
//        ├── controller/
//        │   └── HospitalController.java
//        └── main/
//            └── HospitalManagementApp.java

public class HospitalManagementApp {
        public static void main(String[] args) {
            System.out.println("=== Booting UrbanCare Hospital Management System ===\n");

            // Initialize Services & Directories
            HospitalDirectory directory = new HospitalDirectory();
            BillingService billingService = new BillingService();

            // 1. Patient Onboarding (FR1)
            Patient p1 = new Patient("P101", "Alice Smith", 29, BloodGroup.A_POSITIVE);
            p1.setMedicalHistorySummary("Mild Hypertension");
            p1.addTreatment("Lisinopril 10mg");

            Patient p2 = new Patient("P102", "Bob Johnson", 42, BloodGroup.O_NEGATIVE);
            p2.setMedicalHistorySummary("Asthma");
            p2.addTreatment("Albuterol Inhaler");

            directory.registerPatient(p1);
            directory.registerPatient(p2);

            // 2. Doctor & Staff Setup (FR3)
            ConsultingRoom room = new ConsultingRoom(302, "Floor 3");
            Doctor doctor = new Doctor("D001", "Dr. Gregory House", 48, BloodGroup.AB_POSITIVE, "Chief Diagnostician", room);
            Nurse nurse = new Nurse("N001", "Nurse Jackie", 35, BloodGroup.B_POSITIVE, "Emergency Ward");

            System.out.println("\n--- Staff Profiles ---");
            doctor.getDetails();
            nurse.getDetails();

            // 3. Appointment Scheduling (FR2: One-to-Many & Many-to-Many)
            Appointment appt1 = new Appointment("A-5001", "2026-09-21", AppointmentStatus.SCHEDULED);
            p1.addAppointment(appt1);
            doctor.assignAppointment(appt1);

            System.out.println("\n--- Updated Patient Profile ---");
            p1.getDetails();

            // 4. Directory Navigational Search & Sorting (FR1)
            directory.printSortedPatients();

            // 5. Dynamic Billing & Invoicing (FR4)
            System.out.println("\n--- Generating Professional Invoice ---");
            String invoice = billingService.generateInvoice(p1, 250.00, 85.50);
            System.out.println(invoice);
        }
    }

