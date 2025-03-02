package com.example.reader_pdf;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.rajat.pdfviewer.PdfRendererView;

import java.io.FileNotFoundException;

public class PdfDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_detail);

        PdfRendererView pdfView = findViewById(R.id.pdfView);
        String pdfUriString = getIntent().getStringExtra("pdfUri");

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        if (pdfUriString != null) {
            Uri pdfUri = Uri.parse(pdfUriString);
            try {
                pdfView.initWithUri(pdfUri);
            } catch (FileNotFoundException e) {
                Toast.makeText(this, "File not found", Toast.LENGTH_SHORT).show();
                finish();
            }
        } else {
            Toast.makeText(this, "No PDF found", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

}
