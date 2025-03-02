package com.example.reader_pdf;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;

import com.rajat.pdfviewer.*;

import java.io.FileNotFoundException;


public class PdfDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.rajat.pdfviewer.R.layout.activity_pdf_viewer);

        PdfRendererView pdfView = findViewById(com.rajat.pdfviewer.R.id.pdfView);
        String pdfUriString = getIntent().getStringExtra("pdfUri");

        if (pdfUriString != null) {
            Uri pdfUri = Uri.parse(pdfUriString);
            try {
                pdfView.initWithUri(pdfUri);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
