package com.example.service;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PdfConverter {

    public void convertHtmlToPdf(String html, String outputPath, String baseUri) throws IOException {
        // Ensure output directory exists
        Path path = Paths.get(outputPath);
        Path parent = path.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }

        try (FileOutputStream outputStream = new FileOutputStream(outputPath)) {
            // Create PDF writer and document
            PdfWriter writer = new PdfWriter(outputStream);
            PdfDocument pdf = new PdfDocument(writer);

            // Set to A4 landscape
            pdf.setDefaultPageSize(PageSize.A4.rotate());

            // Configure converter properties
            ConverterProperties properties = new ConverterProperties();
            DefaultFontProvider fontProvider = new DefaultFontProvider(true, true, false);
            properties.setFontProvider(fontProvider);

            // Set base URI for resolving image paths
            if (baseUri != null) {
                properties.setBaseUri(baseUri);
            }

            // Convert HTML to PDF
            HtmlConverter.convertToPdf(html, pdf, properties);
        }
    }
}