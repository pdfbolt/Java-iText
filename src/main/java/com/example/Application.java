package com.example;

import com.example.model.Certificate;
import com.example.service.CertificateService;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Application {

    public static void main(String[] args) {
        try {
            // Use file paths to reference images
            String logoPath = "images/logo.png";
            String badgePath = "images/badge.png";

            // Certificate data
            String recipientName = "John Arabica";
            String courseName = "Advanced Barista Techniques";

            // Format date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date issueDate = dateFormat.parse("2025-03-28");

            String instructor = "Prof. James Bean";

            // Create certificate using the record constructor
            Certificate certificate = new Certificate(
                    recipientName,
                    courseName,
                    issueDate,
                    instructor,
                    logoPath,
                    badgePath
            );

            // Generate timestamp for the filename
            SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String timestamp = timestampFormat.format(new Date());

            // Create filename with recipient name and timestamp
            String sanitizedName = recipientName.replaceAll("\\s+", "_");
            String filename = sanitizedName + "_" + timestamp + ".pdf";

            // Set output path with the dynamic filename
            String outputPath = "certificates/" + filename;

            // Create the directory if it doesn't exist
            File outputDir = new File("certificates");
            if (!outputDir.exists()) {
                outputDir.mkdirs();
            }

            // Generate the certificate
            CertificateService certificateService = new CertificateService();
            certificateService.generateCertificatePdf(certificate, outputPath);

            System.out.println("Certificate generated successfully for " + recipientName);
            System.out.println("PDF saved to: " + new File(outputPath).getAbsolutePath());

        } catch (Exception e) {
            System.err.println("Error generating certificate: " + e.getMessage());
            e.printStackTrace();
        }
    }
}