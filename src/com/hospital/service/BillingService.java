package com.hospital.service;

import com.hospital.model.Patient;

public class BillingService {
    // FR4: Dynamic Billing via StringBuilder & String.format()
    public String generateInvoice(Patient patient, double consultationFee, double medicineCost) {
        StringBuilder sb = new StringBuilder();
        double tax = (consultationFee + medicineCost) * 0.05; // 5% tax
        double grandTotal = consultationFee + medicineCost + tax;

        sb.append("==================================================\n");
        sb.append("         URBANCARE GENERAL HOSPITAL INVOICE       \n");
        sb.append("==================================================\n");
        sb.append("Patient ID   : ").append(patient.getId()).append("\n");
        sb.append("Patient Name : ").append(patient.getName()).append("\n");
        sb.append(String.format("Consultation : $%.2f\n", consultationFee));
        sb.append(String.format("Medicine Cost: $%.2f\n", medicineCost));
        sb.append(String.format("Taxes (5%%)   : $%.2f\n", tax));
        sb.append("--------------------------------------------------\n");
        sb.append(String.format("Grand Total  : $%.2f\n", grandTotal));
        sb.append("==================================================");

        return sb.toString();
    }
}
