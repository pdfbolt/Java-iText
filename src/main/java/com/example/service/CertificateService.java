package com.example.service;

import com.example.model.Certificate;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public class CertificateService {

    private final CertificateGenerator certificateGenerator;
    private final PdfConverter pdfConverter;

    public CertificateService() {
        this.certificateGenerator = new CertificateGenerator();
        this.pdfConverter = new PdfConverter();
    }

    public void generateCertificatePdf(Certificate certificate, String outputPath) throws IOException, TemplateException {
        // Generate HTML from template with certificate data
        String html = certificateGenerator.generateHtml(certificate);

        // Convert HTML to PDF with resource base path
        String baseDir = new File("src/main/resources").getAbsolutePath();
        pdfConverter.convertHtmlToPdf(html, outputPath, baseDir);
    }
}