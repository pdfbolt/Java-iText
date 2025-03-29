package com.example.model;

import java.util.Date;

public record Certificate(
        String recipientName,
        String courseName,
        Date issueDate,
        String instructor,
        String logoUrl,
        String badgeUrl
) {
    // Compact constructor for validation if needed
    public Certificate {
        if (recipientName == null || recipientName.isBlank()) {
            throw new IllegalArgumentException("Recipient name cannot be null or blank");
        }
        if (courseName == null || courseName.isBlank()) {
            throw new IllegalArgumentException("Course name cannot be null or blank");
        }
        if (issueDate == null) {
            throw new IllegalArgumentException("Issue date cannot be null");
        }
        if (instructor == null || instructor.isBlank()) {
            throw new IllegalArgumentException("Instructor cannot be null or blank");
        }
    }
}