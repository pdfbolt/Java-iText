package com.example.service;

import com.example.model.Certificate;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Locale;

public class CertificateGenerator {

    private final Configuration freemarkerConfig;

    public CertificateGenerator() {
        // Initialize FreeMarker configuration
        freemarkerConfig = new Configuration(Configuration.VERSION_2_3_31);
        freemarkerConfig.setClassLoaderForTemplateLoading(getClass().getClassLoader(), "templates");
        freemarkerConfig.setDefaultEncoding("UTF-8");
        freemarkerConfig.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }

    public String generateHtml(Certificate certificate) throws IOException, TemplateException {
        // Load the template
        Template template = freemarkerConfig.getTemplate("certificate-template.html");

        // Create a data model and populate it with certificate data
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("recipient_name", certificate.recipientName());
        dataModel.put("course_name", certificate.courseName());

        // Format the date
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH);
        dataModel.put("date", dateFormat.format(certificate.issueDate()));

        dataModel.put("instructor", certificate.instructor());

        // Use relative paths for images
        dataModel.put("logo_url", certificate.logoUrl());
        dataModel.put("badge_url", certificate.badgeUrl());

        // Process the template with the data model
        Writer out = new StringWriter();
        template.process(dataModel, out);
        return out.toString();
    }
}