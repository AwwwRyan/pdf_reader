package com.example.reader_pdf;

public class PdfDocument {
    private String name;
    private String uri;

    public PdfDocument(String name, String uri) {
        this.name = name;
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public String getUri() {
        return uri;
    }
} 