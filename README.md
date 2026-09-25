# Hospital Management System

A simple Java-based hospital management system built to model patient registration, doctor/nurse profiles, appointment scheduling, and invoice generation.

## Project Structure

```text
hospital/
├── README.md
├── hospital.iml
├── .gitignore
├── src/
│   └── com/
│       └── hospital/
│           ├── main/
│           │   └── HospitalManagementApp.java
│           ├── model/
│           │   ├── Appointment.java
│           │   ├── AppointmentStatus.java
│           │   ├── BloodGroup.java
│           │   ├── ConsultingRoom.java
│           │   ├── Doctor.java
│           │   ├── Nurse.java
│           │   ├── Patient.java
│           │   └── Person.java
│           ├── repository/
│           │   └── HospitalDirectory.java
│           ├── service/
│           │   └── BillingService.java
│           └── controller/
│               └── HospitalController.java
└── out/
```

## Features

- Patient onboarding and registration
- Blood group enum support
- Doctor and nurse profile creation
- Appointment tracking with status values
- Patient sorting by age using a `TreeSet`
- Invoice generation with tax calculation and formatted output

## Main Classes

### `Person`
- Abstract base class for common fields: `id`, `name`, `age`, and `bloodGroup`
- Declared as `abstract` to support shared behavior in subclasses

### `Patient`
- Extends `Person`
- Stores medical history, appointments, and treatments
- Implements `equals()` and `hashCode()` based on patient ID

### `Doctor`
- Extends `Person`
- Stores specialization and consulting room
- Can assign appointments to doctors

### `Nurse`
- Extends `Person`
- Stores assigned ward information

### `HospitalDirectory`
- Stores patients in a `Map`
- Maintains a sorted `TreeSet` for age-based listing
- Helps register and search patient records

### `BillingService`
- Generates professional invoice strings
- Calculates consultation fee + medicine cost + tax
- Uses `StringBuilder` and `String.format()` for formatted output

## Fixes Applied

The project was adjusted to ensure it works correctly and remains simple:

- Fixed invoice formatting error caused by `%` in the label `Taxes (5%)`
- Fixed the doctor display output to avoid duplicated `Dr.` text
- Fixed `TreeSet` ordering logic for patients with the same age
- Removed duplicate import from the main entry class
- Kept the code structure basic and readable with short comments

## How to Run

From the project root, compile all Java files and run the application using its package-qualified class name:

```bash
javac -d out $(find src -name "*.java")
java -cp out com.hospital.main.HospitalManagementApp
```

On Windows PowerShell, you can use:

```powershell
javac -d out (Get-ChildItem -Recurse -Filter *.java -Path .\src).FullName
java -cp .\out com.hospital.main.HospitalManagementApp
```

## Notes

This is a beginner-friendly Java project that demonstrates core OOP concepts, collections, enums, and basic hospital workflow modeling without unnecessary complexity.
